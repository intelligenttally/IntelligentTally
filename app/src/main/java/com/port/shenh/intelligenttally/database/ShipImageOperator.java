package com.port.shenh.intelligenttally.database;
/**
 * Created by sh on 2016/11/24.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.port.shenh.intelligenttally.bean.ShipImage;

import org.json.JSONException;
import org.json.JSONObject;
import org.mobile.library.global.Global;
import org.mobile.library.global.GlobalApplication;
import org.mobile.library.model.database.BaseOperator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 船图数据库操作工具
 *
 * @author 超悟空
 * @version 1.0 2015/12/14
 * @since 1.0
 */
public class ShipImageOperator extends BaseOperator<ShipImage> {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "ShiftChangeOperator.";

    /**
     * 构造函数
     *
     * @param context 上下文
     */
    public ShipImageOperator(Context context) {
        super(context);
        onCreateTableName();
    }

    @Override
    protected SQLiteOpenHelper onCreateDatabaseHelper(Context context) {
        return new ShipImageSQLiteHelper(context);
    }

    @Override
    protected SQLiteOpenHelper onCreateWriteDatabaseHelper(Context context) {
        return ShipImageSQLiteHelper.getSqLiteOpenHelper(context);
    }

    @Override
    protected String onCreateTableName() {
        return TableConst.ShipImageConst.TABLE_NAME;
    }

    @Override
    protected void onCreateTable(SQLiteOpenHelper sqLiteHelper) {
        /**
         * 建表语句
         */
        String createTableSql = String.format("CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY, " +
                "%s TEXT NOT NULL" +
                "%s TEXT NOT NULL" +
                "%s TEXT NOT NULL" +
                "%s TEXT NOT NULL" +
                "%s TEXT NOT NULL" +
                "%s TEXT NOT NULL" +
                "%s TEXT" +
                "%s TEXT" +
                "%s TEXT" +
                "%s TEXT" +
                "%s TEXT" +
                "%s TEXT" +
                "%s TEXT" +
                "%s TEXT" +
                "%s TEXT" +
                "%s TEXT" +
                "%s TEXT" +
                "%s TEXT)",
                CommonConst.DB_NAME, CommonConst._ID,
                TableConst.ShipImage.BAY_NUM,
                TableConst.ShipImage.BAY_COL,
                TableConst.ShipImage.BAY_ROW,
                TableConst.ShipImage.CONTAINER_NO,
                TableConst.ShipImage.SIZE_CON,
                TableConst.ShipImage.CONTAINER_TYPE,
                TableConst.ShipImage.CODE_EMPTY,
                TableConst.ShipImage.WEIGHT,
                TableConst.ShipImage.WORK_DATE,
                TableConst.ShipImage.SEALNO,
                TableConst.ShipImage.MOVED_NAME,
                TableConst.ShipImage.INOUTMARK,
                TableConst.ShipImage.TRANSMARK,
                TableConst.ShipImage.HOLIDAYS,
                TableConst.ShipImage.NIGHT,
                TableConst.ShipImage.CODE_CRANE,
                TableConst.ShipImage.NAME);


        Log.i(LOG_TAG + "onCreateTable", "sql is " + createTableSql);
        sqLiteHelper.getWritableDatabase().execSQL(createTableSql);

        close(sqLiteHelper);
    }

    @Override
    protected ContentValues onFillData(ShipImage data) {
        // 数据库值对象
        ContentValues cv = new ContentValues();
        cv.put(CommonConst.CODE, data.getToken());
        cv.put(TableConst.ShiftChange.SEND_NAME, data.getSend());
        cv.put(TableConst.ShiftChange.RECEIVE_NAME, data.getReceive());
        cv.put(TableConst.ShiftChange.CONTENT, data.getContent());
        cv.put(TableConst.ShiftChange.MY_SEND, data.isMySend() ? 1 : 0);

        SimpleDateFormat formatTarget = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatSource = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        try {
            cv.put(TableConst.ShiftChange.TIME, formatTarget.format(formatSource.parse(data
                    .getTime())));
        } catch (ParseException e) {
            Log.e(LOG_TAG + "onFillData", "ParseException is " + e.getMessage());
        }

        if (data.getImageUrlList() != null) {
            JSONObject imageUrl = new JSONObject(data.getImageUrlList());
            cv.put(TableConst.ShiftChange.IMAGE_URL, imageUrl.toString());
        }

        if (data.getAudioUrlList() != null) {
            JSONObject audioUrl = new JSONObject(data.getAudioUrlList());
            cv.put(TableConst.ShiftChange.AUDIO_URL, audioUrl.toString());
        }

        return cv;
    }

    @Override
    protected List<ShipImage> query(String sql) {
        Log.i(LOG_TAG + "query", "sql is " + sql);
        // 查询数据
        Cursor cursor = sqLiteHelper.getReadableDatabase().rawQuery(sql, null);

        // 列索引
        int code = cursor.getColumnIndex(CommonConst.CODE);
        int sendName = cursor.getColumnIndex(TableConst.ShiftChange.SEND_NAME);
        int receiveName = cursor.getColumnIndex(TableConst.ShiftChange.RECEIVE_NAME);
        int time = cursor.getColumnIndex(TableConst.ShiftChange.TIME);
        int content = cursor.getColumnIndex(TableConst.ShiftChange.CONTENT);
        int mySend = cursor.getColumnIndex(TableConst.ShiftChange.MY_SEND);
        int imageUrl = cursor.getColumnIndex(TableConst.ShiftChange.IMAGE_URL);
        int audioUrl = cursor.getColumnIndex(TableConst.ShiftChange.AUDIO_URL);

        // 数据填充
        List<ShiftChange> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            // 一条记录
            ShiftChange data = new ShiftChange();
            data.setToken(cursor.getString(code));
            data.setSend(cursor.getString(sendName));
            data.setReceive(cursor.getString(receiveName));
            data.setTime(cursor.getString(time).replace('-', '/'));
            data.setContent(cursor.getString(content));
            data.setMySend(cursor.getInt(mySend) == 1);

            try {
                String imageString = cursor.getString(imageUrl);

                if (imageString != null && !imageString.isEmpty()) {

                    JSONObject jsonObject = new JSONObject(imageString);

                    Map<String, String> map = new HashMap<>();

                    Iterator<String> iterator = jsonObject.keys();

                    while (iterator.hasNext()) {
                        String key = iterator.next();
                        map.put(key, jsonObject.getString(key));
                    }

                    data.setImageUrlList(map);
                }

                String audioString = cursor.getString(audioUrl);

                if (audioString != null && !audioString.isEmpty()) {

                    JSONObject jsonObject = new JSONObject(audioString);

                    Map<String, String> map = new HashMap<>();

                    Iterator<String> iterator = jsonObject.keys();

                    while (iterator.hasNext()) {
                        String key = iterator.next();
                        map.put(key, jsonObject.getString(key));
                    }

                    data.setAudioUrlList(map);
                }
            } catch (JSONException e) {
                Log.e(LOG_TAG + "query", "JSONException is " + e.getMessage());
            }

            list.add(data);
        }

        Log.i(LOG_TAG + "query", "row count is " + list.size());

        // 关闭数据库
        cursor.close();
        close(sqLiteHelper);

        return list;
    }

    @Override
    public List<ShipImage> queryWithCondition(String... parameters) {
        Log.i(LOG_TAG + "queryWithCondition", "queryWithCondition is invoked");

        // 查询语句
        String sql;

        if (parameters.length == 1) {
            sql = String.format("select * from %s where %s='%s'", tableName, CommonConst.CODE,
                    parameters[0]);
        } else {
            sql = String.format("select * from %s order by %s desc limit %s,%s", tableName,
                    TableConst.ShiftChange.TIME, parameters[0], parameters[1]);
        }
        return query(sql);
    }

    /**
     * 根据行ID查询结果
     *
     * @param id 行ID
     *
     * @return 数据对象，没有返回null
     */
    public ShipImage queryById(long id) {
        Log.i(LOG_TAG + "queryById", "query id is " + id);

        // 查询语句
        String sql = String.format("select * from %s where %s=%s", tableName, CommonConst._ID, id);

        List<ShiftChange> list = query(sql);

        if (!list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    protected String onWhereSql(ShipImage data) {
        return String.format("%s='%s'", CommonConst.CODE, data.getToken());
    }
}

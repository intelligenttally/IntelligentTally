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
import org.mobile.library.model.database.BaseOperator;
import java.util.ArrayList;
import java.util.List;

/**
 * 船图数据库操作工具
 *
 * @author sh
 * @version 1.0 2016/11/25
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
        return TableConst.ShipImage.TABLE_NAME;
    }

    @Override
    protected void onCreateTable(SQLiteOpenHelper sqLiteHelper) {
        /**
         * 建表语句
         */
        String createTableSql = String.format("CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY, " +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT)",
                tableName, CommonConst._ID,
                TableConst.ShipImage.IMAGE_ID,
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
        cv.put(TableConst.ShipImage.IMAGE_ID, data.getIMAGE_ID());
        cv.put(TableConst.ShipImage.BAY_NUM, data.getBay_no());
        cv.put(TableConst.ShipImage.BAY_COL, data.getBay_col());
        cv.put(TableConst.ShipImage.BAY_ROW, data.getBay_row());
        cv.put(TableConst.ShipImage.CONTAINER_NO, data.getContainer_no());
        cv.put(TableConst.ShipImage.SIZE_CON, data.getSize_con());
        cv.put(TableConst.ShipImage.CONTAINER_TYPE, data.getContainer_type());
        cv.put(TableConst.ShipImage.CODE_EMPTY, data.getCode_empty());
        cv.put(TableConst.ShipImage.WEIGHT, data.getWeight());
        cv.put(TableConst.ShipImage.WORK_DATE, data.getWork_date());
        cv.put(TableConst.ShipImage.SEALNO, data.getSealno());
        cv.put(TableConst.ShipImage.MOVED_NAME, data.getMoved_name());
        cv.put(TableConst.ShipImage.INOUTMARK, data.getInoutmark());
        cv.put(TableConst.ShipImage.TRANSMARK, data.getTransmark());
        cv.put(TableConst.ShipImage.HOLIDAYS, data.getHolidays());
        cv.put(TableConst.ShipImage.NIGHT, data.getHolidays());
        cv.put(TableConst.ShipImage.CODE_CRANE, data.getCode_crane());
        cv.put(TableConst.ShipImage.NAME, data.getName());

        return cv;
    }

    @Override
    protected List<ShipImage> query(String sql) {
        Log.i(LOG_TAG + "query", "sql is " + sql);
        // 查询数据
        Cursor cursor = sqLiteHelper.getReadableDatabase().rawQuery(sql, null);

        // 列索引
        int _id = cursor.getColumnIndex(CommonConst._ID);
        int image_id = cursor.getColumnIndex(TableConst.ShipImage.IMAGE_ID);
        int bay_no = cursor.getColumnIndex(TableConst.ShipImage.BAY_NUM);
        int bay_col = cursor.getColumnIndex(TableConst.ShipImage.BAY_COL);
        int bay_row = cursor.getColumnIndex(TableConst.ShipImage.BAY_ROW);
        int container_no = cursor.getColumnIndex(TableConst.ShipImage.CONTAINER_NO);
        int size_con = cursor.getColumnIndex(TableConst.ShipImage.SIZE_CON);
        int container_type = cursor.getColumnIndex(TableConst.ShipImage.CONTAINER_TYPE);
        int code_empty = cursor.getColumnIndex(TableConst.ShipImage.CODE_EMPTY);
        int weight = cursor.getColumnIndex(TableConst.ShipImage.WEIGHT);
        int work_date = cursor.getColumnIndex(TableConst.ShipImage.WORK_DATE);
        int sealno = cursor.getColumnIndex(TableConst.ShipImage.SEALNO);
        int moved_name = cursor.getColumnIndex(TableConst.ShipImage.MOVED_NAME);
        int inoutmark = cursor.getColumnIndex(TableConst.ShipImage.INOUTMARK);
        int transmark = cursor.getColumnIndex(TableConst.ShipImage.TRANSMARK);
        int holidays = cursor.getColumnIndex(TableConst.ShipImage.HOLIDAYS);
        int night = cursor.getColumnIndex(TableConst.ShipImage.NIGHT);
        int code_crane = cursor.getColumnIndex(TableConst.ShipImage.CODE_CRANE);
        int name = cursor.getColumnIndex(TableConst.ShipImage.NAME);

        // 数据填充
        List<ShipImage> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            // 一条记录
            ShipImage data = new ShipImage();
            data.setId(cursor.getString(_id));
            data.setIMAGE_ID(cursor.getString(image_id));
            data.setBay_no(cursor.getString(bay_no));
            data.setBay_col(cursor.getString(bay_col));
            data.setBay_row(cursor.getString(bay_row));
            data.setContainer_no(cursor.getString(container_no));
            data.setSealno(cursor.getString(size_con));
            data.setContainer_type(cursor.getString(container_type));
            data.setCode_empty(cursor.getString(code_empty));
            data.setWeight(cursor.getString(weight));
            data.setWork_date(cursor.getString(work_date));
            data.setSealno(cursor.getString(sealno));
            data.setMoved_name(cursor.getString(moved_name));
            data.setInoutmark(cursor.getString(inoutmark));
            data.setTransmark(cursor.getString(transmark));
            data.setHolidays(cursor.getString(holidays));
            data.setNight(cursor.getString(night));
            data.setCode_crane(cursor.getString(code_crane));
            data.setNight(cursor.getString(name));

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
        String sql = null;

        if (parameters.length == 1) {
            sql = String.format("select * from %s where %s='%s'", tableName, TableConst.ShipImage.IMAGE_ID,
                    parameters[0]);
        } else {
//            sql = String.format("select * from %s order by %s desc limit %s,%s", tableName,
//                    TableConst.ShiftChange.TIME, parameters[0], parameters[1]);
        }
        return query(sql);
    }

    /**
     * 根据行ID查询结果
     *
     * @param id 行ID
     * @return 数据对象，没有返回null
     */
    public ShipImage queryById(long id) {
        Log.i(LOG_TAG + "queryById", "query id is " + id);

        // 查询语句
        String sql = String.format("select * from %s where %s=%s", tableName, CommonConst._ID, id);

        List<ShipImage> list = query(sql);

        if (!list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    protected String onWhereSql(ShipImage data) {
        return String.format("%s='%s'", TableConst.ShipImage.IMAGE_ID, data.getIMAGE_ID());
    }
}

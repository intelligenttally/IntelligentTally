package com.port.shenh.intelligenttally.database;
/**
 * Created by sh on 2016/11/24.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.port.shenh.intelligenttally.bean.Voyage;
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
public class VoyageOperator extends BaseOperator<Voyage> {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "VoyageOperator.";

    /**
     * 构造函数
     *
     * @param context 上下文
     */
    public VoyageOperator(Context context) {
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
        return TableConst.Voyage.TB_VOYAGE;
    }

    @Override
    protected void onCreateTable(SQLiteOpenHelper sqLiteHelper) {
        /**
         * 建表语句
         */
        String createTableSql = String.format("CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY" +
                        " KEY, " +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT)", tableName, CommonConst._ID,

                TableConst.Voyage.SHIP_ID, TableConst.Voyage.V_ID, TableConst.Voyage
                        .BERTHNO, TableConst.Voyage.VOYAGE, TableConst.Voyage
                        .CHI_VESSEL, TableConst.Voyage.CODEINOUT, TableConst.Voyage.TRADE,
                TableConst.Voyage.WHEEL);


        Log.i(LOG_TAG + "onCreateTable", "sql is " + createTableSql);
        sqLiteHelper.getWritableDatabase().execSQL(createTableSql);

        close(sqLiteHelper);
    }

    @Override
    protected ContentValues onFillData(Voyage data) {
        // 数据库值对象
        ContentValues cv = new ContentValues();

        cv.put(TableConst.Voyage.SHIP_ID, data.getShip_id());
        cv.put(TableConst.Voyage.V_ID, data.getV_id());
        cv.put(TableConst.Voyage.BERTHNO, data.getBerthno());
        cv.put(TableConst.Voyage.VOYAGE, data.getChi_vessel());
        cv.put(TableConst.Voyage.CHI_VESSEL, data.getChi_vessel());
        cv.put(TableConst.Voyage.CODEINOUT, data.getCodeInOut());
        cv.put(TableConst.Voyage.TRADE, data.getTrade());
        cv.put(TableConst.Voyage.WHEEL, data.getWheel());

        return cv;
    }

    @Override
    protected List<Voyage> query(String sql) {
        Log.i(LOG_TAG + "query", "sql is " + sql);
        // 查询数据
        Cursor cursor = sqLiteHelper.getReadableDatabase().rawQuery(sql, null);

        // 列索引
        int _id = cursor.getColumnIndex(CommonConst._ID);

        int ship_id = cursor.getColumnIndex(TableConst.Voyage.SHIP_ID);
        int v_id = cursor.getColumnIndex(TableConst.Voyage.V_ID);
        int berthno = cursor.getColumnIndex(TableConst.Voyage.BERTHNO);
        int voyage = cursor.getColumnIndex(TableConst.Voyage.VOYAGE);
        int chi_vessel = cursor.getColumnIndex(TableConst.Voyage.CHI_VESSEL);
        int codeinout = cursor.getColumnIndex(TableConst.Voyage.CODEINOUT);
        int trade = cursor.getColumnIndex(TableConst.Voyage.TRADE);
        int wheel = cursor.getColumnIndex(TableConst.Voyage.WHEEL);


        // 数据填充
        List<Voyage> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            // 一条记录
            Voyage data = new Voyage();
            data.setId(cursor.getString(_id));
            data.setShip_id(cursor.getString(ship_id));
            data.setV_id(cursor.getString(v_id));
            data.setBerthno(cursor.getString(berthno));
            data.setVoyage(cursor.getString(voyage));
            data.setChi_vessel(cursor.getString(chi_vessel));
            data.setCodeInOut(cursor.getString(codeinout));
            data.setTrade(cursor.getString(trade));
            data.setWheel(cursor.getString(wheel));

            list.add(data);
        }

        Log.i(LOG_TAG + "query", "row count is " + list.size());

        // 关闭数据库
        cursor.close();
        close(sqLiteHelper);

        return list;
    }

    @Override
    protected String onWhereSql(Voyage data) {
        return String.format("%s='%s'", TableConst.ShipImage.SHIP_ID, data.getShip_id());
    }

    @Override
    public List<Voyage> queryWithCondition(String... parameters) {
        Log.i(LOG_TAG + "queryWithCondition", "queryWithCondition is invoked");

        // 查询语句
        String sql = null;

        if (parameters.length == 1) {
            sql = String.format("select * from %s where %s='%s'", tableName, TableConst.Voyage
                    .SHIP_ID, parameters[0]);
        } else {
            //            sql = String.format("select * from %s order by %s desc limit %s,%s",
            // tableName,
            //                    TableConst.ShiftChange.TIME, parameters[0], parameters[1]);
        }
        return query(sql);
    }

    /**
     * 查询航次列表数据
     * @return 对象
     */
    public List<Voyage> queryAllVoyageList()
    {
        Log.i(LOG_TAG + "queryAllVoyageList", "queryAllVoyageList is invoked");

        // 查询语句
        String sql = String.format("select * from %s order by ship_id desc", tableName);

        return query(sql);
    }

    /**
     * 查询航次进出口
     *
     * * @param shipId 航次编码
     *
     * @return 进出口编码
     */
    public String queryCodeInOutOfVoyage(String shipId)
    {
        Log.i(LOG_TAG + "queryInOutOfVoyage", "query shipId is " + shipId);

       String codeInOut = null;

        // 查询语句
        String sql = String.format("select codeinout from %s where %s='%s'", tableName, "ship_id", shipId);
        Log.i(LOG_TAG + "queryCodeInOutOfVoyage", "sql is " + sql);
        // 查询数据
        Cursor cursor = sqLiteHelper.getReadableDatabase().rawQuery(sql, null);

        // 列索引
        int codeInout = cursor.getColumnIndex("codeInOut");

        while (cursor.moveToNext()) {

            codeInOut = cursor.getString(codeInout);
            break;

        }

        // 关闭数据库
        cursor.close();
        close(sqLiteHelper);

        return codeInOut;
    }
}
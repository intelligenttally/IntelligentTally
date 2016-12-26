package com.port.shenh.intelligenttally.database;
/**
 * Created by sh on 2016/11/24.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.port.shenh.intelligenttally.bean.Bay;
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
    private static final String LOG_TAG = "ShipImageOperator.";

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
        String createTableSql = String.format("CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY" +
                        " KEY, " +
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
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s INTEGER," +
                        "%s INTEGER," +
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
                        "%s TEXT," +
                        "%s TEXT)", tableName, CommonConst._ID,

                TableConst.ShipImage.SHIP_ID, TableConst.ShipImage.V_ID, TableConst.ShipImage
                        .ENG_VESSEL, TableConst.ShipImage.CHI_VESSEL, TableConst.ShipImage
                        .LOCATION, TableConst.ShipImage.BAY_NUM, TableConst.ShipImage.BAY_COL,
                TableConst.ShipImage.BAY_ROW, TableConst.ShipImage.SBAYNO, TableConst.ShipImage
                        .TBAYNO, TableConst.ShipImage.JBAYNO, TableConst.ShipImage.USER_CHAR,
                TableConst.ShipImage.SCREEN_ROW, TableConst.ShipImage.SCREEN_COL, TableConst
                        .ShipImage.JOINT, TableConst.ShipImage.CODE_LOAD_PORT, TableConst
                        .ShipImage.CODE_UNLOAD_PORT, TableConst.ShipImage.DELIVERY, TableConst
                        .ShipImage.MOVED, TableConst.ShipImage.UNLOAD_MARK, TableConst.ShipImage
                        .WORK_NO, TableConst.ShipImage.DANGER_GRAGE, TableConst.ShipImage
                        .DEGREE_SETTING, TableConst.ShipImage.DEGREE_UNIT, TableConst.ShipImage
                        .MIN_DEGREE, TableConst.ShipImage.MAX_DEGREE, TableConst.ShipImage.BAYNO,
                TableConst.ShipImage.OLDBAYNO, TableConst.ShipImage.CODE_CRANE, TableConst
                        .ShipImage.IMAGE_ID, TableConst.ShipImage.BAYNUM, TableConst.ShipImage
                        .BAYCOL, TableConst.ShipImage.BAYROW, TableConst.ShipImage.CONTAINER_NO,
                TableConst.ShipImage.SIZE_CON, TableConst.ShipImage.CONTAINER_TYPE, TableConst
                        .ShipImage.CODE_EMPTY, TableConst.ShipImage.WEIGHT, TableConst.ShipImage
                        .WORK_DATE, TableConst.ShipImage.SEALNO, TableConst.ShipImage.MOVED_NAME,
                TableConst.ShipImage.INOUTMARK, TableConst.ShipImage.TRANSMARK, TableConst
                        .ShipImage.HOLIDAYS, TableConst.ShipImage.NIGHT, TableConst.ShipImage.NAME);


        Log.i(LOG_TAG + "onCreateTable", "sql is " + createTableSql);
        sqLiteHelper.getWritableDatabase().execSQL(createTableSql);

        close(sqLiteHelper);
    }

    @Override
    protected ContentValues onFillData(ShipImage data) {
        // 数据库值对象
        ContentValues cv = new ContentValues();

        cv.put(TableConst.ShipImage.SHIP_ID, data.getShip_id());
        cv.put(TableConst.ShipImage.V_ID, data.getV_id());
        cv.put(TableConst.ShipImage.ENG_VESSEL, data.getEng_vessel());
        cv.put(TableConst.ShipImage.CHI_VESSEL, data.getChi_vessel());
        cv.put(TableConst.ShipImage.LOCATION, data.getLocation());
        cv.put(TableConst.ShipImage.BAY_NUM, data.getBay_num());
        cv.put(TableConst.ShipImage.BAY_COL, data.getBay_col());
        cv.put(TableConst.ShipImage.BAY_ROW, data.getBay_row());
        cv.put(TableConst.ShipImage.SBAYNO, data.getSealno());
        cv.put(TableConst.ShipImage.TBAYNO, data.getTbayno());
        cv.put(TableConst.ShipImage.JBAYNO, data.getJbayno());
        cv.put(TableConst.ShipImage.USER_CHAR, data.getUser_char());
        cv.put(TableConst.ShipImage.SCREEN_ROW, data.getScreen_row());
        cv.put(TableConst.ShipImage.SCREEN_COL, data.getScreen_col());
        cv.put(TableConst.ShipImage.JOINT, data.getJoint());
        cv.put(TableConst.ShipImage.CODE_LOAD_PORT, data.getCode_load_port());
        cv.put(TableConst.ShipImage.CODE_UNLOAD_PORT, data.getCode_unload_port());
        cv.put(TableConst.ShipImage.DELIVERY, data.getDelivery());
        cv.put(TableConst.ShipImage.MOVED, data.getMoved());
        cv.put(TableConst.ShipImage.UNLOAD_MARK, data.getUnload_mark());
        cv.put(TableConst.ShipImage.WORK_NO, data.getWork_no());
        cv.put(TableConst.ShipImage.DANGER_GRAGE, data.getDanger_grade());
        cv.put(TableConst.ShipImage.DEGREE_SETTING, data.getDegree_setting());
        cv.put(TableConst.ShipImage.DEGREE_UNIT, data.getDegree_unit());
        cv.put(TableConst.ShipImage.MIN_DEGREE, data.getMin_degree());
        cv.put(TableConst.ShipImage.MAX_DEGREE, data.getMax_degree());
        cv.put(TableConst.ShipImage.BAYNO, data.getBayno());
        cv.put(TableConst.ShipImage.OLDBAYNO, data.getOldbayno());
        cv.put(TableConst.ShipImage.CODE_CRANE, data.getCode_crane());
        cv.put(TableConst.ShipImage.IMAGE_ID, data.getImage_id());
        cv.put(TableConst.ShipImage.BAYNUM, data.getBaynum());
        cv.put(TableConst.ShipImage.BAYCOL, data.getBaycol());
        cv.put(TableConst.ShipImage.BAYROW, data.getBayrow());
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

        int ship_id = cursor.getColumnIndex(TableConst.ShipImage.SHIP_ID);
        int v_id = cursor.getColumnIndex(TableConst.ShipImage.V_ID);
        int eng_vessel = cursor.getColumnIndex(TableConst.ShipImage.ENG_VESSEL);
        int chi_vessel = cursor.getColumnIndex(TableConst.ShipImage.CHI_VESSEL);
        int location = cursor.getColumnIndex(TableConst.ShipImage.LOCATION);
        int bay_num = cursor.getColumnIndex(TableConst.ShipImage.BAY_NUM);
        int bay_col = cursor.getColumnIndex(TableConst.ShipImage.BAY_COL);
        int bay_row = cursor.getColumnIndex(TableConst.ShipImage.BAY_ROW);
        int sbayno = cursor.getColumnIndex(TableConst.ShipImage.SBAYNO);
        int tbayno = cursor.getColumnIndex(TableConst.ShipImage.TBAYNO);
        int jbayno = cursor.getColumnIndex(TableConst.ShipImage.JBAYNO);
        int user_char = cursor.getColumnIndex(TableConst.ShipImage.USER_CHAR);
        int screen_row = cursor.getColumnIndex(TableConst.ShipImage.SCREEN_ROW);
        int screen_col = cursor.getColumnIndex(TableConst.ShipImage.SCREEN_COL);
        int joint = cursor.getColumnIndex(TableConst.ShipImage.JOINT);
        int code_load_port = cursor.getColumnIndex(TableConst.ShipImage.CODE_LOAD_PORT);
        int code_unload_port = cursor.getColumnIndex(TableConst.ShipImage.CODE_UNLOAD_PORT);
        int delivery = cursor.getColumnIndex(TableConst.ShipImage.DELIVERY);
        int moved = cursor.getColumnIndex(TableConst.ShipImage.MOVED);
        int unload_mark = cursor.getColumnIndex(TableConst.ShipImage.UNLOAD_MARK);
        int work_no = cursor.getColumnIndex(TableConst.ShipImage.WORK_NO);
        int danger_grade = cursor.getColumnIndex(TableConst.ShipImage.DANGER_GRAGE);
        int degree_setting = cursor.getColumnIndex(TableConst.ShipImage.DEGREE_SETTING);
        int degree_unit = cursor.getColumnIndex(TableConst.ShipImage.DEGREE_UNIT);
        int min_degree = cursor.getColumnIndex(TableConst.ShipImage.MIN_DEGREE);
        int max_degree = cursor.getColumnIndex(TableConst.ShipImage.MAX_DEGREE);
        int bayno = cursor.getColumnIndex(TableConst.ShipImage.BAYNO);
        int oldbayno = cursor.getColumnIndex(TableConst.ShipImage.OLDBAYNO);
        int code_crane = cursor.getColumnIndex(TableConst.ShipImage.CODE_CRANE);

        int image_id = cursor.getColumnIndex(TableConst.ShipImage.IMAGE_ID);
        int baynum = cursor.getColumnIndex(TableConst.ShipImage.BAYNUM);
        int baycol = cursor.getColumnIndex(TableConst.ShipImage.BAYCOL);
        int bayrow = cursor.getColumnIndex(TableConst.ShipImage.BAYROW);
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
        int name = cursor.getColumnIndex(TableConst.ShipImage.NAME);

        // 数据填充
        List<ShipImage> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            // 一条记录
            ShipImage data = new ShipImage();
            data.setId(cursor.getString(_id));
            data.setShip_id(cursor.getString(ship_id));
            data.setV_id(cursor.getString(v_id));
            data.setEng_vessel(cursor.getString(eng_vessel));
            data.setChi_vessel(cursor.getString(chi_vessel));
            data.setLocation(cursor.getString(location));
            data.setBay_num(cursor.getString(bay_num));
            data.setBay_col(cursor.getString(bay_col));
            data.setBay_row(cursor.getString(bay_row));
            data.setSbayno(cursor.getString(sbayno));
            data.setTbayno(cursor.getString(tbayno));
            data.setJbayno(cursor.getString(jbayno));
            data.setUser_char(cursor.getString(user_char));
            data.setScreen_row(cursor.getInt(screen_row));
            data.setScreen_col(cursor.getInt(screen_col));
            data.setJoint(cursor.getString(joint));
            data.setCode_load_port(cursor.getString(code_load_port));
            data.setCode_unload_port(cursor.getString(code_unload_port));
            data.setDelivery(cursor.getString(delivery));
            data.setMoved(cursor.getString(moved));
            data.setUnload_mark(cursor.getString(unload_mark));
            data.setWork_no(cursor.getString(work_no));
            data.setDanger_grade(cursor.getString(danger_grade));
            data.setDegree_setting(cursor.getString(degree_setting));
            data.setDegree_unit(cursor.getString(degree_unit));
            data.setMin_degree(cursor.getString(min_degree));
            data.setMax_degree(cursor.getString(max_degree));
            data.setBayno(cursor.getString(bayno));
            data.setOldbayno(cursor.getString(oldbayno));
            data.setCode_crane(cursor.getString(code_crane));

            data.setImage_id(cursor.getString(image_id));
            data.setBaynum(cursor.getString(baynum));
            data.setBaycol(cursor.getString(baycol));
            data.setBayrow(cursor.getString(bayrow));
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
            sql = String.format("select * from %s where %s='%s'", tableName, TableConst.ShipImage
                    .IMAGE_ID, parameters[0]);
        } else {
            //            sql = String.format("select * from %s order by %s desc limit %s,%s",
            // tableName,
            //                    TableConst.ShiftChange.TIME, parameters[0], parameters[1]);
        }
        return query(sql);
    }

    /**
     * 根据行航次编码查询结果
     *
     * @param shipId 航次编码
     * @return 数据对象，没有返回null
     */
    public ShipImage queryByShipId(String shipId) {
        Log.i(LOG_TAG + "queryByShipId", "query shipId is " + shipId);

        // 查询语句
        String sql = String.format("select * from %s where %s=%s", tableName, "ship_id", shipId);

        Log.i(LOG_TAG + "queryByShipId", "query sql is " + sql);

        List<ShipImage> list = query(sql);

        if (!list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 根据航次编码判断数据是否存在
     *
     * @param shipId 航次编码
     * @return true/flase
     */
    public boolean isExistByShipId(String shipId) {

        Log.i(LOG_TAG + "isExistByShipId", "isExist shipId is " + shipId);

        // 查询语句
        String sql = String.format("select  count(*) from %s where %s=%s", tableName, "ship_id",
                shipId);

        Log.i(LOG_TAG + "queryByShipId", "query sql is " + sql);

        Cursor cursor = sqLiteHelper.getReadableDatabase().rawQuery(sql, null);
        if (cursor.moveToNext()) {
            if (cursor.getInt(0) > 0) {
                cursor.close();
                close(sqLiteHelper);
                return true;
            }
        }
        cursor.close();
        close(sqLiteHelper);
        return false;
    }


    @Override
    protected String onWhereSql(ShipImage data) {
        return String.format("%s='%s'", TableConst.ShipImage.SHIP_ID, data.getShip_id());
    }

    /**
     * 根据航次编码查询单号列表
     *
     * @param shipId 航次编码
     *
     * @return 数据对象，没有返回null
     */
    public List<String> queryBayNumList(String shipId) {
        Log.i(LOG_TAG + "queryBayNumList", "query shipId is " + shipId);

        List<String> bayList = new ArrayList<>();

        // 查询语句
        String sql = String.format("select distinct bay_num from %s where %s=%s order by bay_num " +
                "asc", tableName, "ship_id", shipId);
        Log.i(LOG_TAG + "queryBayNumList", "sql is " + sql);
        // 查询数据
        Cursor cursor = sqLiteHelper.getReadableDatabase().rawQuery(sql, null);

        // 列索引
        int bay_num = cursor.getColumnIndex("bay_num");

        while (cursor.moveToNext()) {
            bayList.add(cursor.getString(bay_num));
        }

        // 关闭数据库
        cursor.close();
        close(sqLiteHelper);

        return bayList;
    }

    /**
     * 根据航次编码、贝号查询单贝船图结果
     *
     * @param shipId 航次编码
     * @param bayNum 贝号
     * @return 数据对象，没有返回null
     */
    public List<ShipImage> queryShipImage(String shipId, String bayNum) {
        Log.i(LOG_TAG + "queryShipImage", "query param is " + shipId + " " + bayNum);

        // 查询语句
        String sql = String.format("select * from %s where %s=%s and %s='%s'", tableName,
                "ship_id", shipId, "bay_num", bayNum);

        Log.i(LOG_TAG + "queryShipImage", "query sql is " + sql);

        List<ShipImage> list = query(sql);

        return list;
    }

    /**
     * 根据航次编码、贝号查询贝数据
     *
     * @param bayNum 贝号
     * @param shipId 航次编码
     * @return 数据对象，没有返回null
     */
    public Bay queryBay(String shipId, String bayNum) {
        Log.i(LOG_TAG + "queryBay", "query param is " + shipId + " " + bayNum);

        Bay bay = new Bay();
        String bay_num = bayNum;

        // 查询语句
        String sql = String.format("select Max(SCREEN_ROW) as sum_screen_row_board,Min(SCREEN_ROW) as min_screen_row_board,Max" +
                "(SCREEN_COL) as sum_screen_col_board from %s where %s='%s' and %s='%s' and " +
                "%s='%s'", tableName, "ship_id", shipId, "bay_num", bayNum, "location", "board");
        Log.i(LOG_TAG + "queryBay", "sql is " + sql);
        SQLiteDatabase sqLiteDatabase = sqLiteHelper.getReadableDatabase();

        // 查询数据
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        // 列索引
        int sumScreenRow_board = cursor.getColumnIndex(TableConst.Bay.SUM_SCREEN_ROW_BOARD);
        int minScreenRow_board = cursor.getColumnIndex(TableConst.Bay.MIN_SCREEN_ROW_BOARD);
        int sumScreenCol_board = cursor.getColumnIndex(TableConst.Bay.SUM_SCREEN_COL_BOARD);

        while (cursor.moveToNext()) {
            bay.setSumScreenRow_board(cursor.getInt(sumScreenRow_board));
            bay.setMinScreenRow_board(cursor.getInt(minScreenRow_board));
            bay.setSumScreenCol_board(cursor.getInt(sumScreenCol_board));
        }

        // 查询语句
        sql = String.format("select Max(SCREEN_ROW) as sum_screen_row_cabin,Max(SCREEN_COL) as " +
                        "sum_screen_col_cabin from %s where %s='%s' and %s='%s' and %s='%s'", tableName,
                "ship_id", shipId, "bay_num", bayNum, "location", "cabin");
        Log.i(LOG_TAG + "queryBay", "sql is " + sql);
        // 查询数据
        cursor = sqLiteDatabase.rawQuery(sql, null);

        // 列索引
        int sumScreenRow_cabin = cursor.getColumnIndex(TableConst.Bay.SUM_SCREEN_ROW_CABIN);
        int sumScreenCol_cabin = cursor.getColumnIndex(TableConst.Bay.SUM_SCREEN_COL_CABIN);

        while (cursor.moveToNext()) {
            bay.setSumScreenRow_cabin(cursor.getInt(sumScreenRow_cabin));
            bay.setSumScreenCol_cabin(cursor.getInt(sumScreenCol_cabin));
        }

        // 查询语句
        sql = String.format("select joint from %s where %s='%s' and %s='%s' and %s='%s'", tableName,
                "ship_id", shipId, "bay_num", bayNum, "location", "cabin");
        Log.i(LOG_TAG + "queryBay", "sql is " + sql);
        // 查询数据
        cursor = sqLiteDatabase.rawQuery(sql, null);

        // 列索引
        int joint = cursor.getColumnIndex(TableConst.Bay.JOINT);

        while (cursor.moveToNext()) {
            if (cursor.getString(joint).equals("1") == true)
            {
                int tempBayNum = Integer.parseInt(bayNum) + 1;
                Log.i(LOG_TAG + "queryBay", "tempBayNum is " + tempBayNum);

                if (tempBayNum < 10){
                    bay_num = bayNum + "(" + "0" + Integer.toString(tempBayNum) + ")";
                }else {
                    bay_num =  bayNum + "(" + Integer.toString(tempBayNum) + ")";
                }
                break;
            }
        }

        bay.setBay_num(bay_num);

        // 关闭数据库
        cursor.close();
        close(sqLiteHelper);

        return bay;
    }
}

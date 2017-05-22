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
import com.port.shenh.intelligenttally.function.CodeUnloadPortSubListFunction;

import org.mobile.library.global.Global;
import org.mobile.library.model.database.BaseOperator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.port.shenh.intelligenttally.database.TableConst.ShipImage.CONTAINER_NO;
import static com.port.shenh.intelligenttally.database.TableConst.ShipImage.MARK_MODIFY;

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
        return TableConst.ShipImage.TB_SHIP_IMAGE;
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
                        "%s TEXT," +
                        "%s TEXT DEFAULT '0'," +
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
                        .BAYCOL, TableConst.ShipImage.BAYROW, CONTAINER_NO, TableConst.ShipImage
                        .SIZE_CON, TableConst.ShipImage.CONTAINER_TYPE, TableConst.ShipImage
                        .CODE_EMPTY, TableConst.ShipImage.WEIGHT, TableConst.ShipImage.WORK_DATE,
                TableConst.ShipImage.SEALNO, TableConst.ShipImage.MOVED_NAME, TableConst
                        .ShipImage.INOUTMARK, TableConst.ShipImage.TRANSMARK, TableConst
                        .ShipImage.HOLIDAYS, TableConst.ShipImage.NIGHT, TableConst.ShipImage
                        .NAME, MARK_MODIFY, TableConst.ShipImage.MODIFIER, TableConst.ShipImage
                        .MODIFYTIME);


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
        cv.put(TableConst.ShipImage.SBAYNO, data.getSbayno());
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
        cv.put(CONTAINER_NO, data.getContainer_no());
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
        int container_no = cursor.getColumnIndex(CONTAINER_NO);
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
        int mark_modify = cursor.getColumnIndex(MARK_MODIFY);
        int modifier = cursor.getColumnIndex(TableConst.ShipImage.MODIFIER);
        int modifytime = cursor.getColumnIndex(TableConst.ShipImage.MODIFYTIME);

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
            data.setSize_con(cursor.getString(size_con));
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
            data.setMark_modify(cursor.getString(mark_modify));
            data.setModifier(cursor.getString(modifier));
            data.setModifytime(cursor.getString(modifytime));

            Log.i(LOG_TAG + "query", "container_no is " + cursor.getString(container_no));
            Log.i(LOG_TAG + "query", "ship_id is " + cursor.getString(ship_id));
            Log.i(LOG_TAG + "query", "bay_num is " + cursor.getString(bay_num));
            Log.i(LOG_TAG + "query", "bay_col is " + cursor.getString(bay_col));
            Log.i(LOG_TAG + "query", "bay_row is " + cursor.getString(bay_row));
            Log.i(LOG_TAG + "query", "sbayno is " + cursor.getString(sbayno));
            Log.i(LOG_TAG + "query", "bayno is " + cursor.getString(bayno));
            Log.i(LOG_TAG + "query", "baynum is " + cursor.getString(baynum));
            Log.i(LOG_TAG + "query", "baycol is " + cursor.getString(baycol));
            Log.i(LOG_TAG + "query", "bayrow is " + cursor.getString(bayrow));

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
     *
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
     *
     * @return true/flase
     */
    public boolean isExistByShipId(String shipId) {

        Log.i(LOG_TAG + "isExistByShipId", "isExist shipId is " + shipId);

        // 查询语句
        String sql = String.format("select  count(*) from %s a, %s b where a.%s=b.%s and a" + "" +
                ".%s=%s", "tb_voyage", tableName, "ship_id", "ship_id", "ship_id", shipId);

        Log.i(LOG_TAG + "queryByShipId", "query sql is " + sql);

        Cursor cursor = sqLiteHelper.getReadableDatabase().rawQuery(sql, null);
        if (cursor.moveToNext()) {
            if (cursor.getInt(0) > 0) {
                cursor.close();
                close(sqLiteHelper);
                Log.i(LOG_TAG + "isExistByShipId", "isExist shipId is " + shipId + " true");
                return true;
            }
        }
        cursor.close();
        close(sqLiteHelper);

        Log.i(LOG_TAG + "isExistByShipId", "isExist shipId is " + shipId + " false");
        return false;
    }


    @Override
    protected String onWhereSql(ShipImage data) {
        return String.format("%s='%s'", TableConst.ShipImage.SHIP_ID, data.getShip_id());
    }

    /**
     * 删除数据（航次船图数据）
     *
     * @param shipId 航次编码
     *
     * @return 删除的记录数
     */
    public int deleteShipImage(String shipId) {
        Log.v(LOG_TAG + "delete", "delete is invoked");

        if (shipId == null) {
            Log.v(LOG_TAG + "delete", "data is null");
            return 0;
        }

        // 得到数据库写对象
        SQLiteDatabase dbWriter = writeSqLiteHelper.getWritableDatabase();

        // where子句
        String whereSql = String.format("%s='%s'", TableConst.ShipImage.SHIP_ID, shipId);

        Log.v(LOG_TAG + "delete", "where sql is " + whereSql);

        // 执行删除
        int rowCount = dbWriter.delete(tableName, whereSql, null);

        Log.v(LOG_TAG + "delete", "delete row count is " + rowCount);

        close(writeSqLiteHelper);

        return rowCount;
    }

    /**
     * 删除数据（贝船图数据）
     *
     * @param shipId 航次编码
     * @param bayNum 贝号
     *
     * @return 删除的记录数
     */
    public int deleteShipImage(String shipId, String bayNum) {
        Log.v(LOG_TAG + "delete", "delete is invoked");

        if (shipId == null) {
            Log.v(LOG_TAG + "delete", "data is null");
            return 0;
        }

        // 得到数据库写对象
        SQLiteDatabase dbWriter = writeSqLiteHelper.getWritableDatabase();

        // where子句
        String whereSql = String.format("%s='%s' and %s='%s' ", TableConst.ShipImage.SHIP_ID,
                shipId, TableConst.ShipImage.BAY_NUM, bayNum);

        Log.v(LOG_TAG + "delete", "where sql is " + whereSql);

        // 执行删除
        int rowCount = dbWriter.delete(tableName, whereSql, null);

        Log.v(LOG_TAG + "delete", "delete row count is " + rowCount);

        close(writeSqLiteHelper);

        return rowCount;
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
        String sql = String.format("select distinct bay_num from %s where %s='%s' order by " +
                "bay_num " + "asc", tableName, "ship_id", shipId);
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
     * 根据航次编码查询卸货港简写列表
     *
     * @param shipId 航次编码
     *
     * @return 数据对象，没有返回null
     */
    public Map<String, String> queryCodeUnloadPortSubList(String shipId) {
        Log.i(LOG_TAG + "queryCodeUnloadPortSubList", "query shipId is " + shipId);

        List<String> codeUnloadPortList = new ArrayList<>();

        // 查询语句
        String sql = String.format("select distinct code_unload_port from %s where %s='%s'  and "
                + "code_unload_port is not null and ltrim(code_unload_port) <> ''", tableName,
                "ship_id", shipId);
        Log.i(LOG_TAG + "queryCodeUnloadPortSubList", "sql is " + sql);
        // 查询数据
        Cursor cursor = sqLiteHelper.getReadableDatabase().rawQuery(sql, null);

        // 列索引
        int code_unload_port = cursor.getColumnIndex("code_unload_port");

        while (cursor.moveToNext()) {
            codeUnloadPortList.add(cursor.getString(code_unload_port));
        }

        // 关闭数据库
        cursor.close();
        close(sqLiteHelper);

        if (codeUnloadPortList.size() > 0) {
            return CodeUnloadPortSubListFunction.getCodeUnloadPortSubList(codeUnloadPortList);
        } else {
            return null;
        }


    }

    /**
     * 根据航次编码、贝号查询单贝船图结果
     *
     * @param shipId 航次编码
     * @param bayNum 贝号
     *
     * @return 数据对象，没有返回null
     */
    public List<ShipImage> queryShipImage(String shipId, String bayNum) {
        Log.i(LOG_TAG + "queryShipImage", "query param is " + shipId + " " + bayNum);

        // 查询语句
        String sql = String.format("select * from %s where %s='%s' and %s='%s' order by %s asc",
                tableName, "ship_id", shipId, "bay_num", bayNum, "unload_mark");

        Log.i(LOG_TAG + "queryShipImage", "query sql is " + sql);

        List<ShipImage> list = query(sql);

        return list;
    }

    /**
     * 根据航次编码、贝号查询贝数据
     *
     * @param bayNum 贝号
     * @param shipId 航次编码
     *
     * @return 数据对象，没有返回null
     */
    public Bay queryBay(String shipId, String bayNum) {
        Log.i(LOG_TAG + "queryBay", "query param is " + shipId + " " + bayNum);

        Bay bay = new Bay();
        String bay_num = bayNum;

        // 查询语句
        String sql = String.format("select Max(SCREEN_ROW) as sum_screen_row_board,Min" +
                "(SCREEN_ROW) as min_screen_row_board,Max" +
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
        sql = String.format("select Max(SCREEN_ROW) as sum_screen_row_cabin,Min(SCREEN_ROW) as " +
                "min_screen_row_cabin,Max(SCREEN_COL) as " + "sum_screen_col_cabin from %s where " +
                "%s='%s' and %s='%s' and %s='%s'", tableName, "ship_id", shipId, "bay_num",
                bayNum, "location", "cabin");
        Log.i(LOG_TAG + "queryBay", "sql is " + sql);
        // 查询数据
        cursor = sqLiteDatabase.rawQuery(sql, null);

        // 列索引
        int sumScreenRow_cabin = cursor.getColumnIndex(TableConst.Bay.SUM_SCREEN_ROW_CABIN);
        int sumScreenCol_cabin = cursor.getColumnIndex(TableConst.Bay.SUM_SCREEN_COL_CABIN);
        int minScreenCol_cabin = cursor.getColumnIndex(TableConst.Bay.MIN_SCREEN_ROW_CABIN);

        while (cursor.moveToNext()) {
            bay.setSumScreenRow_cabin(cursor.getInt(sumScreenRow_cabin));
            bay.setSumScreenCol_cabin(cursor.getInt(sumScreenCol_cabin));
            bay.setMinScreenRow_cabin(cursor.getInt(minScreenCol_cabin));
        }

        //        Log.i(LOG_TAG + "queryBay", "cabin is " + bayNum + " " + bay
        // .getSumScreenRow_cabin() + " " + bay.getSumScreenCol_cabin());

        if (isJoint(shipId, bayNum)) {
            int tempBayNum = Integer.parseInt(bayNum) + 1;
            Log.i(LOG_TAG + "queryBay", "tempBayNum is " + tempBayNum);

            if (tempBayNum < 10) {
                bay_num = bayNum + "(" + "0" + Integer.toString(tempBayNum) + ")";
            } else {
                bay_num = bayNum + "(" + Integer.toString(tempBayNum) + ")";
            }
        }

        bay.setBay_num(bay_num);

        // 关闭数据库
        cursor.close();
        close(sqLiteHelper);

        return bay;
    }


    /**
     * 根据航次编码、贝号判断是否通贝
     *
     * @param shipId 航次编码
     * @param bayNum 贝号
     *
     * @return false/true
     */
    public boolean isJoint(String shipId, String bayNum) {

        Log.i(LOG_TAG + "queryBay", "shipId param is " + shipId);
        Log.i(LOG_TAG + "queryBay", "bayNum param is " + bayNum);

        boolean isJoint = false;

        // 查询语句
        String sql = String.format("select joint from %s where %s='%s' and %s='%s' and %s='%s'",
                tableName, "ship_id", shipId, "bay_num", bayNum, "location", "cabin");
        Log.i(LOG_TAG + "queryBay", "sql is " + sql);
        // 查询数据
        Cursor cursor = sqLiteHelper.getReadableDatabase().rawQuery(sql, null);

        // 列索引
        int joint = cursor.getColumnIndex(TableConst.Bay.JOINT);

        while (cursor.moveToNext()) {
            if (cursor.getString(joint).equals("1") == true) {

                isJoint = true;

            }
        }

        // 关闭数据库
        cursor.close();
        close(sqLiteHelper);

        return isJoint;
    }

    /**
     * 根据航次编码和标准贝位号查询船图数据
     *
     * @param shipId 航次编码
     * @param sbayno 标准贝位号
     *
     * @return 数据对象
     */
    public List<ShipImage> getShipImageList(String shipId, String sbayno) {
        Log.i(LOG_TAG + "getShipImageList", "query param is " + sbayno);

        // 查询语句
        String sql = String.format("select * from %s where %s='%s' and %s='%s'", tableName,
                "ship_id", shipId, "sbayno", sbayno);

        Log.i(LOG_TAG + "queryShipImage", "query sql is " + sql);

        List<ShipImage> list = query(sql);

        return list;
    }

    /**
     * 根据航次编码更新船图数据修改标志
     *
     * @param shipId 航次编码
     */
    public void updateModifyMark(String shipId) {

        Log.i(LOG_TAG + "updateModifyMark", "shipId is " + shipId);

        String sql = String.format("update %s set mark_modify='%s' " + "where ship_id='%s' and " +
                "mark_modify='%s'", tableName, "0", shipId, "1");

        Log.i(LOG_TAG + "updateModifyMark", "sql1 is " + sql);

        sqLiteHelper.getWritableDatabase().execSQL(sql);

        close(sqLiteHelper);
    }

    /**
     * 判断修改标志是否为1
     *
     * @param shipId 航次编码
     *
     * @return true/false
     */
    public boolean isExistModifyMark(String shipId) {
        Log.i(LOG_TAG + "isExistModifyMark", "shipId is " + shipId);

        // 查询语句
        String sql = String.format("select  count(*) from %s where ship_id=%s and " +
                "mark_modify=%s", tableName, shipId, "1");

        Log.i(LOG_TAG + "isExistModifyMark", "sql is " + sql);

        Cursor cursor = sqLiteHelper.getReadableDatabase().rawQuery(sql, null);
        if (cursor.moveToNext()) {
            if (cursor.getInt(0) > 0) {
                cursor.close();
                close(sqLiteHelper);
                Log.i(LOG_TAG + "isExistModifyMark", "isExistModifyMark is " + "true");
                return true;
            }
        }
        cursor.close();
        close(sqLiteHelper);

        Log.i(LOG_TAG + "isExistModifyMark", "isExistModifyMark is " + "false");
        return false;
    }

    /**
     * 对调贝位
     *
     * @param b1        贝位1
     * @param b2        贝位2
     * @param codeInOut 进出口编码
     */
    public void swapBay(ShipImage b1, ShipImage b2, String codeInOut) {

        Log.i(LOG_TAG + "swapBay", "query param is " + b1.getSbayno() + " " + b2.getSbayno());

        Log.i(LOG_TAG + "swapBay", "b1 getSbayno is " + b1.getSbayno());
        Log.i(LOG_TAG + "swapBay", "b1 getBaynum is " + b1.getBaynum());
        Log.i(LOG_TAG + "swapBay", "b1 getBaycol is " + b1.getBaycol());
        Log.i(LOG_TAG + "swapBay", "b1 getBayrow is " + b1.getBayrow());

        Log.i(LOG_TAG + "swapBay", "b2 getSbayno is " + b2.getSbayno());
        Log.i(LOG_TAG + "swapBay", "b2 getBaynum is " + b2.getBaynum());
        Log.i(LOG_TAG + "swapBay", "b2 getBaycol is " + b2.getBaycol());
        Log.i(LOG_TAG + "swapBay", "b2 getBayrow is " + b2.getBayrow());


        String moved1 = getMoved(b1, codeInOut);
        String moved2 = getMoved(b2, codeInOut);
        String moveName1 = moved1.equals("1") == true ? "捣箱" : "";
        String moveName2 = moved2.equals("1") == true ? "捣箱" : "";
        String oldbayno1 = b2.getOldbayno().isEmpty() == true ? b2.getBayno() : "";
        String oldbayno2 = b1.getOldbayno().isEmpty() == true ? b1.getBayno() : "";

        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        String date = sDateFormat.format(new java.util.Date());

        Log.i(LOG_TAG + "swapBay", "date is " + date);


        Log.i(LOG_TAG + "swapBay", "Size_con is " + b1.getSize_con() + " " + b2.getSize_con());

        if (b1.getSize_con().equals("20") && (b2.getSize_con().equals("20") || b2.getSize_con()
                .isEmpty())) {

            String bayno1 = b2.getSbayno();
            String bayno2 = b2.getBayno().isEmpty() == true ? "" : b1.getSbayno();

            String bayNum1 = b2.getBay_num();
            String bayNum2 = b2.getBayno().isEmpty() == true ? "" : b1.getBay_num();

            String bayCol1 = b2.getBay_col();
            String bayCol2 = b2.getBayno().isEmpty() == true ? "" : b1.getBay_col();

            String bayRow1 = b2.getBay_row();
            String bayRow2 = b2.getBayno().isEmpty() == true ? "" : b1.getBay_row();

            Log.i(LOG_TAG + "swapBay", "bayno1 is " + bayno1);
            Log.i(LOG_TAG + "swapBay", "bayno2 is " + bayno2);
            Log.i(LOG_TAG + "swapBay", "bayNum1 is " + bayNum1);
            Log.i(LOG_TAG + "swapBay", "bayNum2 is " + bayNum2);
            Log.i(LOG_TAG + "swapBay", "bayCol1 is " + bayCol1);
            Log.i(LOG_TAG + "swapBay", "bayCol2 is " + bayCol2);
            Log.i(LOG_TAG + "swapBay", "bayRow1 is " + bayRow1);
            Log.i(LOG_TAG + "swapBay", "bayRow2 is " + bayRow2);

            String sql1 = String.format("update %s set CODE_LOAD_PORT='%s'," +
                    "CODE_UNLOAD_PORT='%s'," +
                    "DELIVERY='%s'," +
                    "MOVED='%s'," +
                    "UNLOAD_MARK='%s'," +
                    "WORK_NO='%s'," +
                    "DANGER_GRADE='%s'," +
                    "DEGREE_SETTING='%s'," +
                    "DEGREE_UNIT='%s'," +
                    "MIN_DEGREE='%s'," +
                    "MAX_DEGREE='%s'," +
                    "BAYNO='%s'," +
                    "OLDBAYNO='%s'," +
                    "CODE_CRANE='%s'," +
                    "BAYNUM='%s'," +
                    "BAYCOL='%s'," +
                    "BAYROW='%s'," +
                    "CONTAINER_NO='%s'," +
                    "SIZE_CON='%s'," +
                    "CONTAINER_TYPE='%s'," +
                    "CODE_EMPTY='%s'," +
                    "WEIGHT='%s'," +
                    "SEALNO='%s'," +
                    "MOVED_NAME='%s'," +
                    "INOUTMARK='%s'," +
                    "TransMark='%s'," +
                    "HOLIDAYS='%s'," +
                    "NIGHT='%s'," +
                    "NAME='%s'," +
                    "MARK_MODIFY='%s'," +
                    "MODIFIER='%s'," +
                    "MODIFYTIME='%s'" +
                    "where SHIP_ID='%s' and SBAYNO='%s' and CONTAINER_NO='%s'", tableName, b2
                    .getCode_load_port(), b2.getCode_unload_port(), b2.getDelivery(), moved2, b2
                    .getUnload_mark(), b2.getWork_no(), b2.getDanger_grade(), b2
                    .getDegree_setting(), b2.getDegree_unit(), b2.getMin_degree(), b2
                    .getMax_degree(), bayno2, oldbayno2, b2.getCode_crane(), bayNum2, bayCol2,
                    bayRow2, b2.getContainer_no(), b2.getSize_con(), b2.getContainer_type(), b2
                            .getCode_empty(), b2.getWeight(), b2.getSealno(), moveName2, b2
                            .getInoutmark(), b2.getTransmark(), b2.getHolidays(), b2.getNight(),
                    b2.getName(), "1", Global.getLoginStatus().getUserID(), date, b1.getShip_id()
                    , b1.getSbayno(), b1.getContainer_no());

            Log.i(LOG_TAG + "swapBay", "sql1 is " + sql1);

            String sql2 = null;
            if (b2.getBayno().isEmpty()) {
                sql2 = String.format("update %s set CODE_LOAD_PORT='%s'," +
                        "CODE_UNLOAD_PORT='%s'," +
                        "DELIVERY='%s'," +
                        "MOVED='%s'," +
                        "UNLOAD_MARK='%s'," +
                        "WORK_NO='%s'," +
                        "DANGER_GRADE='%s'," +
                        "DEGREE_SETTING='%s'," +
                        "DEGREE_UNIT='%s'," +
                        "MIN_DEGREE='%s'," +
                        "MAX_DEGREE='%s'," +
                        "BAYNO='%s'," +
                        "OLDBAYNO='%s'," +
                        "CODE_CRANE='%s'," +
                        "BAYNUM='%s'," +
                        "BAYCOL='%s'," +
                        "BAYROW='%s'," +
                        "CONTAINER_NO='%s'," +
                        "SIZE_CON='%s'," +
                        "CONTAINER_TYPE='%s'," +
                        "CODE_EMPTY='%s'," +
                        "WEIGHT='%s'," +
                        "SEALNO='%s'," +
                        "MOVED_NAME='%s'," +
                        "INOUTMARK='%s'," +
                        "TransMark='%s'," +
                        "HOLIDAYS='%s'," +
                        "NIGHT='%s'," +
                        "NAME='%s'," +
                        "MARK_MODIFY='%s'," +
                        "MODIFIER='%s'," +
                        "MODIFYTIME='%s'" +
                        "where SHIP_ID='%s' and SBAYNO='%s' and (CONTAINER_NO is null or " +
                        "CONTAINER_NO='')", tableName, b1.getCode_load_port(), b1
                        .getCode_unload_port(), b1.getDelivery(), moved1, b1.getUnload_mark(), b1
                        .getWork_no(), b1.getDanger_grade(), b1.getDegree_setting(), b1
                        .getDegree_unit(), b1.getMin_degree(), b1.getMax_degree(), bayno1,
                        oldbayno1, b1.getCode_crane(), bayNum1, bayCol1, bayRow1, b1
                                .getContainer_no(), b1.getSize_con(), b1.getContainer_type(), b1
                                .getCode_empty(), b1.getWeight(), b1.getSealno(), moveName1, b1
                                .getInoutmark(), b1.getTransmark(), b1.getHolidays(), b1.getNight
                                (), b1.getName(), "1", Global.getLoginStatus().getUserID(), date,
                        b2.getShip_id(), b2.getSbayno());
            } else {

                sql2 = String.format("update %s set CODE_LOAD_PORT='%s'," +
                        "CODE_UNLOAD_PORT='%s'," +
                        "DELIVERY='%s'," +
                        "MOVED='%s'," +
                        "UNLOAD_MARK='%s'," +
                        "WORK_NO='%s'," +
                        "DANGER_GRADE='%s'," +
                        "DEGREE_SETTING='%s'," +
                        "DEGREE_UNIT='%s'," +
                        "MIN_DEGREE='%s'," +
                        "MAX_DEGREE='%s'," +
                        "BAYNO='%s'," +
                        "OLDBAYNO='%s'," +
                        "CODE_CRANE='%s'," +
                        "BAYNUM='%s'," +
                        "BAYCOL='%s'," +
                        "BAYROW='%s'," +
                        "CONTAINER_NO='%s'," +
                        "SIZE_CON='%s'," +
                        "CONTAINER_TYPE='%s'," +
                        "CODE_EMPTY='%s'," +
                        "WEIGHT='%s'," +
                        "SEALNO='%s'," +
                        "MOVED_NAME='%s'," +
                        "INOUTMARK='%s'," +
                        "TransMark='%s'," +
                        "HOLIDAYS='%s'," +
                        "NIGHT='%s'," +
                        "NAME='%s'," +
                        "MARK_MODIFY='%s'," +
                        "MODIFIER='%s'," +
                        "MODIFYTIME='%s'" +
                        "where SHIP_ID='%s' and SBAYNO='%s' and CONTAINER_NO='%s'", tableName, b1
                        .getCode_load_port(), b1.getCode_unload_port(), b1.getDelivery(), moved1,
                        b1.getUnload_mark(), b1.getWork_no(), b1.getDanger_grade(), b1
                                .getDegree_setting(), b1.getDegree_unit(), b1.getMin_degree(), b1
                                .getMax_degree(), bayno1, oldbayno1, b1.getCode_crane(), bayNum1,
                        bayCol1, bayRow1, b1.getContainer_no(), b1.getSize_con(), b1
                                .getContainer_type(), b1.getCode_empty(), b1.getWeight(), b1
                                .getSealno(), moveName1, b1.getInoutmark(), b1.getTransmark(), b1
                                .getHolidays(), b1.getNight(), b1.getName(), "1", Global
                                .getLoginStatus().getUserID(), date, b2.getShip_id(), b2
                                .getSbayno(), b2.getContainer_no());

            }


            Log.i(LOG_TAG + "swapBay", "sql2 is " + sql2);

            List<String> sqlList = new ArrayList<>();
            sqlList.add(sql1);
            sqlList.add(sql2);
            execSQL(sqlList);

        } else if (b1.getSize_con().equals("20") && (b2.getSize_con().equals("40") || b2
                .getSize_con().equals("45"))) {

            String bayno1 = b2.getSbayno();
            String bayno1Next = null;
            if ((Integer.parseInt(b2.getBay_num()) + 2) < 10) {

                bayno1Next = "0" + Integer.toString(Integer.parseInt(b2.getBay_num()) + 2) + b2
                        .getBay_col() + b2.getBay_row();

            } else {

                bayno1Next = Integer.toString(Integer.parseInt(b2.getBay_num()) + 2) + b2
                        .getBay_col() + b2.getBay_row();

            }
            String bayno2 = null;
            if ((Integer.parseInt(b1.getBay_num()) + 1) < 10) {

                bayno2 = "0" + Integer.toString(Integer.parseInt(b1.getBay_num()) + 1) + b1
                        .getBay_col() + b1.getBay_row();
            } else {

                bayno2 = Integer.toString(Integer.parseInt(b1.getBay_num()) + 1) + b1.getBay_col
                        () + b1.getBay_row();

            }
            String bayno2Next = bayno2;

            String sbayno1Next = null;
            if ((Integer.parseInt(b1.getBay_num()) + 2) < 10) {

                sbayno1Next = "0" + Integer.toString(Integer.parseInt(b1.getBay_num()) + 2) + b1
                        .getBay_col() + b1.getBay_row();
            } else {

                sbayno1Next = Integer.toString(Integer.parseInt(b1.getBay_num()) + 2) + b1
                        .getBay_col() + b1.getBay_row();

            }
            String sbayno2Next = null;
            if ((Integer.parseInt(b2.getBay_num()) + 2) < 10) {

                sbayno2Next = "0" + Integer.toString(Integer.parseInt(b2.getBay_num()) + 2) + b2
                        .getBay_col() + b2.getBay_row();
            } else {

                sbayno2Next = Integer.toString(Integer.parseInt(b2.getBay_num()) + 2) + b2
                        .getBay_col() + b2.getBay_row();

            }

            String bayNum1 = b2.getBaynum();
            String bayNum1Next = null;
            if ((Integer.parseInt(b2.getBay_num()) + 2) < 10) {

                bayNum1Next = "0" + Integer.toString(Integer.parseInt(b2.getBay_num()) + 2);
            } else {

                bayNum1Next = Integer.toString(Integer.parseInt(b2.getBay_num()) + 2);

            }
            String bayNum2 = null;
            if ((Integer.parseInt(b1.getBay_num()) + 1) < 10) {

                bayNum2 = "0" + Integer.toString(Integer.parseInt(b1.getBay_num()) + 1);
            } else {

                bayNum2 = Integer.toString(Integer.parseInt(b1.getBay_num()) + 1);

            }
            String bayNum2Next = bayNum2;

            Log.i(LOG_TAG + "swapBay", "bayno1 is " + bayno1);
            Log.i(LOG_TAG + "swapBay", "bayno1Next is " + bayno1Next);
            Log.i(LOG_TAG + "swapBay", "bayno2 is " + bayno2);
            Log.i(LOG_TAG + "swapBay", "bayno2Next is " + bayno2Next);

            Log.i(LOG_TAG + "swapBay", "sbayno1Next is " + sbayno1Next);
            Log.i(LOG_TAG + "swapBay", "sbayno2Next is " + sbayno2Next);

            Log.i(LOG_TAG + "swapBay", "bayNum1 is " + bayNum1);
            Log.i(LOG_TAG + "swapBay", "bayNum1Next is " + bayNum1Next);
            Log.i(LOG_TAG + "swapBay", "bayNum2 is " + bayNum2);
            Log.i(LOG_TAG + "swapBay", "bayNum2Next is " + bayNum2Next);


            String sql1 = String.format("update %s set CODE_LOAD_PORT='%s'," +
                    "CODE_UNLOAD_PORT='%s'," +
                    "DELIVERY='%s'," +
                    "MOVED='%s'," +
                    "UNLOAD_MARK='%s'," +
                    "WORK_NO='%s'," +
                    "DANGER_GRADE='%s'," +
                    "DEGREE_SETTING='%s'," +
                    "DEGREE_UNIT='%s'," +
                    "MIN_DEGREE='%s'," +
                    "MAX_DEGREE='%s'," +
                    "BAYNO='%s'," +
                    "OLDBAYNO='%s'," +
                    "CODE_CRANE='%s'," +
                    "BAYNUM='%s'," +
                    "BAYCOL='%s'," +
                    "BAYROW='%s'," +
                    "CONTAINER_NO='%s'," +
                    "SIZE_CON='%s'," +
                    "CONTAINER_TYPE='%s'," +
                    "CODE_EMPTY='%s'," +
                    "WEIGHT='%s'," +
                    "SEALNO='%s'," +
                    "MOVED_NAME='%s'," +
                    "INOUTMARK='%s'," +
                    "TransMark='%s'," +
                    "HOLIDAYS='%s'," +
                    "NIGHT='%s'," +
                    "NAME='%s'," +
                    "MARK_MODIFY='%s'," +
                    "MODIFIER='%s'," +
                    "MODIFYTIME='%s'" +
                    "where SHIP_ID='%s' and SBAYNO='%s' and CONTAINER_NO='%s'", tableName, b2
                    .getCode_load_port(), b2.getCode_unload_port(), b2.getDelivery(), moved2, b2
                    .getUnload_mark(), b2.getWork_no(), b2.getDanger_grade(), b2
                    .getDegree_setting(), b2.getDegree_unit(), b2.getMin_degree(), b2
                    .getMax_degree(), bayno2, oldbayno2, b2.getCode_crane(), bayNum2, b2
                    .getBay_col(), b2.getBay_row(), b2.getContainer_no(), b2.getSize_con(), b2
                    .getContainer_type(), b2.getCode_empty(), b2.getWeight(), b2.getSealno(),
                    moveName2, b2.getInoutmark(), b2.getTransmark(), b2.getHolidays(), b2
                            .getNight(), b2.getName(), "1", Global.getLoginStatus().getUserID(),
                    date, b1.getShip_id(), b1.getSbayno(), b1.getContainer_no());

            Log.i(LOG_TAG + "swapBay", "sql1 is " + sql1);

            String sql1Next = String.format("update %s set CODE_LOAD_PORT='%s'," +
                    "CODE_UNLOAD_PORT='%s'," +
                    "DELIVERY='%s'," +
                    "MOVED='%s'," +
                    "UNLOAD_MARK='%s'," +
                    "WORK_NO='%s'," +
                    "DANGER_GRADE='%s'," +
                    "DEGREE_SETTING='%s'," +
                    "DEGREE_UNIT='%s'," +
                    "MIN_DEGREE='%s'," +
                    "MAX_DEGREE='%s'," +
                    "BAYNO='%s'," +
                    "OLDBAYNO='%s'," +
                    "CODE_CRANE='%s'," +
                    "BAYNUM='%s'," +
                    "BAYCOL='%s'," +
                    "BAYROW='%s'," +
                    "CONTAINER_NO='%s'," +
                    "SIZE_CON='%s'," +
                    "CONTAINER_TYPE='%s'," +
                    "CODE_EMPTY='%s'," +
                    "WEIGHT='%s'," +
                    "SEALNO='%s'," +
                    "MOVED_NAME='%s'," +
                    "INOUTMARK='%s'," +
                    "TransMark='%s'," +
                    "HOLIDAYS='%s'," +
                    "NIGHT='%s'," +
                    "NAME='%s'," +
                    "MARK_MODIFY='%s'," +
                    "MODIFIER='%s'," +
                    "MODIFYTIME='%s'" +
                    "where SHIP_ID='%s' and SBAYNO='%s' and (CONTAINER_NO is null or " +
                    "CONTAINER_NO='')", tableName, b2.getCode_load_port(), b2.getCode_unload_port
                    (), b2.getDelivery(), moved2, b2.getUnload_mark(), b2.getWork_no(), b2
                    .getDanger_grade(), b2.getDegree_setting(), b2.getDegree_unit(), b2
                    .getMin_degree(), b2.getMax_degree(), bayno2Next, oldbayno2, b2.getCode_crane
                    (), bayNum2Next, b2.getBay_col(), b2.getBay_row(), b2.getContainer_no(), b2
                    .getSize_con(), b2.getContainer_type(), b2.getCode_empty(), b2.getWeight(),
                    b2.getSealno(), moveName2, b2.getInoutmark(), b2.getTransmark(), b2
                            .getHolidays(), b2.getNight(), b2.getName(), "1", Global
                            .getLoginStatus().getUserID(), date, b1.getShip_id(), sbayno1Next);

            Log.i(LOG_TAG + "swapBay", "sql1Next is " + sql1Next);

            String sql2 = String.format("update %s set CODE_LOAD_PORT='%s'," +
                    "CODE_UNLOAD_PORT='%s'," +
                    "DELIVERY='%s'," +
                    "MOVED='%s'," +
                    "UNLOAD_MARK='%s'," +
                    "WORK_NO='%s'," +
                    "DANGER_GRADE='%s'," +
                    "DEGREE_SETTING='%s'," +
                    "DEGREE_UNIT='%s'," +
                    "MIN_DEGREE='%s'," +
                    "MAX_DEGREE='%s'," +
                    "BAYNO='%s'," +
                    "OLDBAYNO='%s'," +
                    "CODE_CRANE='%s'," +
                    "BAYNUM='%s'," +
                    "BAYCOL='%s'," +
                    "BAYROW='%s'," +
                    "CONTAINER_NO='%s'," +
                    "SIZE_CON='%s'," +
                    "CONTAINER_TYPE='%s'," +
                    "CODE_EMPTY='%s'," +
                    "WEIGHT='%s'," +
                    "SEALNO='%s'," +
                    "MOVED_NAME='%s'," +
                    "INOUTMARK='%s'," +
                    "TransMark='%s'," +
                    "HOLIDAYS='%s'," +
                    "NIGHT='%s'," +
                    "NAME='%s'," +
                    "MARK_MODIFY='%s'," +
                    "MODIFIER='%s'," +
                    "MODIFYTIME='%s'" +
                    "where SHIP_ID='%s' and SBAYNO='%s' and CONTAINER_NO='%s'", tableName, b1
                    .getCode_load_port(), b1.getCode_unload_port(), b1.getDelivery(), moved1, b1
                    .getUnload_mark(), b1.getWork_no(), b1.getDanger_grade(), b1
                    .getDegree_setting(), b1.getDegree_unit(), b1.getMin_degree(), b1
                    .getMax_degree(), bayno1, oldbayno1, b1.getCode_crane(), bayNum1, b1
                    .getBay_col(), b1.getBay_row(), b1.getContainer_no(), b1.getSize_con(), b1
                    .getContainer_type(), b1.getCode_empty(), b1.getWeight(), b1.getSealno(),
                    moveName1, b1.getInoutmark(), b1.getTransmark(), b1.getHolidays(), b1
                            .getNight(), b1.getName(), "1", Global.getLoginStatus().getUserID(),
                    date, b2.getShip_id(), b2.getSbayno(), b2.getContainer_no());

            Log.i(LOG_TAG + "swapBay", "sql2 is " + sql2);


            String sql2Next = String.format("update %s set CODE_LOAD_PORT='%s'," +
                    "CODE_UNLOAD_PORT='%s'," +
                    "DELIVERY='%s'," +
                    "MOVED='%s'," +
                    "UNLOAD_MARK='%s'," +
                    "WORK_NO='%s'," +
                    "DANGER_GRADE='%s'," +
                    "DEGREE_SETTING='%s'," +
                    "DEGREE_UNIT='%s'," +
                    "MIN_DEGREE='%s'," +
                    "MAX_DEGREE='%s'," +
                    "BAYNO='%s'," +
                    "OLDBAYNO='%s'," +
                    "CODE_CRANE='%s'," +
                    "BAYNUM='%s'," +
                    "BAYCOL='%s'," +
                    "BAYROW='%s'," +
                    "CONTAINER_NO='%s'," +
                    "SIZE_CON='%s'," +
                    "CONTAINER_TYPE='%s'," +
                    "CODE_EMPTY='%s'," +
                    "WEIGHT='%s'," +
                    "SEALNO='%s'," +
                    "MOVED_NAME='%s'," +
                    "INOUTMARK='%s'," +
                    "TransMark='%s'," +
                    "HOLIDAYS='%s'," +
                    "NIGHT='%s'," +
                    "NAME='%s'," +
                    "MARK_MODIFY='%s'," +
                    "MODIFIER='%s'," +
                    "MODIFYTIME='%s'" +
                    "where SHIP_ID='%s' and SBAYNO='%s' and CONTAINER_NO='%s'", tableName, b1
                    .getCode_load_port(), b1.getCode_unload_port(), b1.getDelivery(), moved1, b1
                    .getUnload_mark(), b1.getWork_no(), b1.getDanger_grade(), b1
                    .getDegree_setting(), b1.getDegree_unit(), b1.getMin_degree(), b1
                    .getMax_degree(), bayno1Next, oldbayno1, b1.getCode_crane(), bayNum1Next, b1
                    .getBay_col(), b1.getBay_row(), b1.getContainer_no(), b1.getSize_con(), b1
                    .getContainer_type(), b1.getCode_empty(), b1.getWeight(), b1.getSealno(),
                    moveName1, b1.getInoutmark(), b1.getTransmark(), b1.getHolidays(), b1
                            .getNight(), b1.getName(), "1", Global.getLoginStatus().getUserID(),
                    date, b2.getShip_id(), sbayno2Next, b2.getContainer_no());

            Log.i(LOG_TAG + "swapBay", "sql2Next is " + sql2Next);

            List<String> sqlList = new ArrayList<>();
            sqlList.add(sql1);
            sqlList.add(sql2);
            sqlList.add(sql1Next);
            sqlList.add(sql2Next);
            execSQL(sqlList);

        } else if (b1.getSize_con().equals("40")) {

            String bayno1 = null;
            if ((Integer.parseInt(b2.getBay_num()) + 1) < 10) {

                bayno1 = "0" + Integer.toString(Integer.parseInt(b2.getBay_num()) + 1) + b2
                        .getBay_col() + b2.getBay_row();

            } else {

                bayno1 = Integer.toString(Integer.parseInt(b2.getBay_num()) + 1) + b2.getBay_col
                        () + b2.getBay_row();

            }
            String bayno1Next = bayno1;
            String bayno2 = b2.getBayno().isEmpty() == true ? "" : b1.getSbayno();
            String bayno2Next = "";


            String sbayno1Next = null;
            if ((Integer.parseInt(b1.getBay_num()) + 2) < 10) {

                sbayno1Next = "0" + Integer.toString(Integer.parseInt(b1.getBay_num()) + 2) + b1
                        .getBay_col() + b1.getBay_row();

            } else {

                sbayno1Next = Integer.toString(Integer.parseInt(b1.getBay_num()) + 2) + b1
                        .getBay_col() + b1.getBay_row();

            }
            String sbayno2Next = null;
            if ((Integer.parseInt(b2.getBay_num()) + 2) < 10) {

                sbayno2Next = "0" + Integer.toString(Integer.parseInt(b2.getBay_num()) + 2) + b2
                        .getBay_col() + b2.getBay_row();

            } else {

                sbayno2Next = Integer.toString(Integer.parseInt(b2.getBay_num()) + 2) + b2
                        .getBay_col() + b2.getBay_row();

            }

            String bayNum1 = null;
            if ((Integer.parseInt(b2.getBay_num()) + 1) < 10) {

                bayNum1 = "0" + Integer.toString(Integer.parseInt(b2.getBay_num()) + 1);

            } else {

                bayNum1 = Integer.toString(Integer.parseInt(b2.getBay_num()) + 1);

            }
            String bayNum1Next = bayNum1;
            String bayNum2 = b2.getBayno().isEmpty() == true ? "" : b1.getBaynum();
            String bayNum2Next = "";

            String bayCol1 = b2.getBay_col();
            String bayCol1Next = bayCol1;
            String bayCol2 = b2.getBayno().isEmpty() == true ? "" : b1.getBay_col();
            String bayCol2Next = "";

            String bayRow1 = b2.getBay_row();
            String bayRow1Next = bayRow1;
            String bayRow2 = b2.getBayno().isEmpty() == true ? "" : b1.getBay_row();
            String bayRow2Next = "";

            if (b2.getSize_con().equals("40")) {

                if ((Integer.parseInt(b1.getBay_num()) + 1) < 10) {

                    bayno2 = "0" + Integer.toString(Integer.parseInt(b1.getBay_num()) + 1) + b1
                            .getBay_col() + b1.getBay_row();
                    bayNum2 = "0" + Integer.toString(Integer.parseInt(b1.getBay_num()) + 1);

                } else {

                    bayno2 = Integer.toString(Integer.parseInt(b1.getBay_num()) + 1) + b1
                            .getBay_col() + b1.getBay_row();

                    bayNum2 = Integer.toString(Integer.parseInt(b1.getBay_num()) + 1);
                }
                bayCol2 = b1.getBay_col();
                bayRow2 = b1.getBay_row();

                bayno2Next = bayno2;
                bayNum2Next = bayNum2;
                bayCol2Next = bayCol2;
                bayRow2Next = bayRow2;
            }

            Log.i(LOG_TAG + "swapBay", "bayno1 is " + bayno1);
            Log.i(LOG_TAG + "swapBay", "bayno1Next is " + bayno1Next);
            Log.i(LOG_TAG + "swapBay", "bayno2 is " + bayno2);
            Log.i(LOG_TAG + "swapBay", "bayno2Next is " + bayno2Next);

            Log.i(LOG_TAG + "swapBay", "sbayno1Next is " + sbayno1Next);
            Log.i(LOG_TAG + "swapBay", "sbayno2Next is " + sbayno2Next);

            Log.i(LOG_TAG + "swapBay", "bayNum1 is " + bayNum1);
            Log.i(LOG_TAG + "swapBay", "bayNum1Next is " + bayNum1Next);
            Log.i(LOG_TAG + "swapBay", "bayNum2 is " + bayNum2);
            Log.i(LOG_TAG + "swapBay", "bayNum2Next is " + bayNum2Next);

            Log.i(LOG_TAG + "swapBay", "bayCol1 is " + bayCol1);
            Log.i(LOG_TAG + "swapBay", "bayCol1Next is " + bayCol1Next);
            Log.i(LOG_TAG + "swapBay", "bayCol2 is " + bayCol2);
            Log.i(LOG_TAG + "swapBay", "bayCol2Next is " + bayCol2Next);

            Log.i(LOG_TAG + "swapBay", "bayRow1 is " + bayRow1);
            Log.i(LOG_TAG + "swapBay", "bayRow1Next is " + bayRow1Next);
            Log.i(LOG_TAG + "swapBay", "bayRow2 is " + bayRow2);
            Log.i(LOG_TAG + "swapBay", "bayRow2Next is " + bayRow2Next);

            String sql1 = String.format("update %s set CODE_LOAD_PORT='%s'," +
                    "CODE_UNLOAD_PORT='%s'," +
                    "DELIVERY='%s'," +
                    "MOVED='%s'," +
                    "UNLOAD_MARK='%s'," +
                    "WORK_NO='%s'," +
                    "DANGER_GRADE='%s'," +
                    "DEGREE_SETTING='%s'," +
                    "DEGREE_UNIT='%s'," +
                    "MIN_DEGREE='%s'," +
                    "MAX_DEGREE='%s'," +
                    "BAYNO='%s'," +
                    "OLDBAYNO='%s'," +
                    "CODE_CRANE='%s'," +
                    "BAYNUM='%s'," +
                    "BAYCOL='%s'," +
                    "BAYROW='%s'," +
                    "CONTAINER_NO='%s'," +
                    "SIZE_CON='%s'," +
                    "CONTAINER_TYPE='%s'," +
                    "CODE_EMPTY='%s'," +
                    "WEIGHT='%s'," +
                    "SEALNO='%s'," +
                    "MOVED_NAME='%s'," +
                    "INOUTMARK='%s'," +
                    "TransMark='%s'," +
                    "HOLIDAYS='%s'," +
                    "NIGHT='%s'," +
                    "NAME='%s'," +
                    "MARK_MODIFY='%s'," +
                    "MODIFIER='%s'," +
                    "MODIFYTIME='%s'" +
                    "where SHIP_ID='%s' and SBAYNO='%s' and CONTAINER_NO='%s'", tableName, b2
                    .getCode_load_port(), b2.getCode_unload_port(), b2.getDelivery(), moved2, b2
                    .getUnload_mark(), b2.getWork_no(), b2.getDanger_grade(), b2
                    .getDegree_setting(), b2.getDegree_unit(), b2.getMin_degree(), b2
                    .getMax_degree(), bayno2, oldbayno2, b2.getCode_crane(), bayNum2, bayCol2, b2
                    .getBay_row(), b2.getContainer_no(), b2.getSize_con(), b2.getContainer_type()
                    , b2.getCode_empty(), b2.getWeight(), b2.getSealno(), moveName2, b2
                            .getInoutmark(), b2.getTransmark(), b2.getHolidays(), b2.getNight(),
                    b2.getName(), "1", Global.getLoginStatus().getUserID(), date, b1.getShip_id()
                    , b1.getSbayno(), b1.getContainer_no());

            Log.i(LOG_TAG + "swapBay", "sql1 is " + sql1);

            String sql1Next = String.format("update %s set CODE_LOAD_PORT='%s'," +
                    "CODE_UNLOAD_PORT='%s'," +
                    "DELIVERY='%s'," +
                    "MOVED='%s'," +
                    "UNLOAD_MARK='%s'," +
                    "WORK_NO='%s'," +
                    "DANGER_GRADE='%s'," +
                    "DEGREE_SETTING='%s'," +
                    "DEGREE_UNIT='%s'," +
                    "MIN_DEGREE='%s'," +
                    "MAX_DEGREE='%s'," +
                    "BAYNO='%s'," +
                    "OLDBAYNO='%s'," +
                    "CODE_CRANE='%s'," +
                    "BAYNUM='%s'," +
                    "BAYCOL='%s'," +
                    "BAYROW='%s'," +
                    "CONTAINER_NO='%s'," +
                    "SIZE_CON='%s'," +
                    "CONTAINER_TYPE='%s'," +
                    "CODE_EMPTY='%s'," +
                    "WEIGHT='%s'," +
                    "SEALNO='%s'," +
                    "MOVED_NAME='%s'," +
                    "INOUTMARK='%s'," +
                    "TransMark='%s'," +
                    "HOLIDAYS='%s'," +
                    "NIGHT='%s'," +
                    "NAME='%s'," +
                    "MARK_MODIFY='%s'," +
                    "MODIFIER='%s'," +
                    "MODIFYTIME='%s'" +
                    "where SHIP_ID='%s' and SBAYNO='%s' and CONTAINER_NO='%s'", tableName, b2
                    .getCode_load_port(), b2.getCode_unload_port(), b2.getDelivery(), moved2, b2
                    .getUnload_mark(), b2.getWork_no(), b2.getDanger_grade(), b2
                    .getDegree_setting(), b2.getDegree_unit(), b2.getMin_degree(), b2
                    .getMax_degree(), bayno2Next, oldbayno2, b2.getCode_crane(), bayNum2Next, b2
                    .getBay_col(), b2.getBay_row(), b2.getContainer_no(), b2.getSize_con(), b2
                    .getContainer_type(), b2.getCode_empty(), b2.getWeight(), b2.getSealno(),
                    moveName2, b2.getInoutmark(), b2.getTransmark(), b2.getHolidays(), b2
                            .getNight(), b2.getName(), "1", Global.getLoginStatus().getUserID(),
                    date, b1.getShip_id(), sbayno1Next, b1.getContainer_no());

            Log.i(LOG_TAG + "swapBay", "sql1Next is " + sql1Next);

            String sql2 = null;
            String sql2Next = null;
            if (b2.getSize_con().equals("40")) {

                sql2 = String.format("update %s set CODE_LOAD_PORT='%s'," +
                        "CODE_UNLOAD_PORT='%s'," +
                        "DELIVERY='%s'," +
                        "MOVED='%s'," +
                        "UNLOAD_MARK='%s'," +
                        "WORK_NO='%s'," +
                        "DANGER_GRADE='%s'," +
                        "DEGREE_SETTING='%s'," +
                        "DEGREE_UNIT='%s'," +
                        "MIN_DEGREE='%s'," +
                        "MAX_DEGREE='%s'," +
                        "BAYNO='%s'," +
                        "OLDBAYNO='%s'," +
                        "CODE_CRANE='%s'," +
                        "BAYNUM='%s'," +
                        "BAYCOL='%s'," +
                        "BAYROW='%s'," +
                        "CONTAINER_NO='%s'," +
                        "SIZE_CON='%s'," +
                        "CONTAINER_TYPE='%s'," +
                        "CODE_EMPTY='%s'," +
                        "WEIGHT='%s'," +
                        "SEALNO='%s'," +
                        "MOVED_NAME='%s'," +
                        "INOUTMARK='%s'," +
                        "TransMark='%s'," +
                        "HOLIDAYS='%s'," +
                        "NIGHT='%s'," +
                        "NAME='%s'," +
                        "MARK_MODIFY='%s'," +
                        "MODIFIER='%s'," +
                        "MODIFYTIME='%s'" +
                        "where SHIP_ID='%s' and SBAYNO='%s' and CONTAINER_NO='%s'", tableName, b1
                        .getCode_load_port(), b1.getCode_unload_port(), b1.getDelivery(), moved1,
                        b1.getUnload_mark(), b1.getWork_no(), b1.getDanger_grade(), b1
                                .getDegree_setting(), b1.getDegree_unit(), b1.getMin_degree(), b1
                                .getMax_degree(), bayno1, oldbayno1, b1.getCode_crane(), bayNum1,
                        b1.getBay_col(), b1.getBay_row(), b1.getContainer_no(), b1.getSize_con(),
                        b1.getContainer_type(), b1.getCode_empty(), b1.getWeight(), b1.getSealno
                                (), moveName1, b1.getInoutmark(), b1.getTransmark(), b1
                                .getHolidays(), b1.getNight(), b1.getName(), "1", Global
                                .getLoginStatus().getUserID(), date, b2.getShip_id(), b2
                                .getSbayno(), b2.getContainer_no());

                Log.i(LOG_TAG + "swapBay", "sql2 is " + sql2);

                sql2Next = String.format("update %s set CODE_LOAD_PORT='%s'," +
                        "CODE_UNLOAD_PORT='%s'," +
                        "DELIVERY='%s'," +
                        "MOVED='%s'," +
                        "UNLOAD_MARK='%s'," +
                        "WORK_NO='%s'," +
                        "DANGER_GRADE='%s'," +
                        "DEGREE_SETTING='%s'," +
                        "DEGREE_UNIT='%s'," +
                        "MIN_DEGREE='%s'," +
                        "MAX_DEGREE='%s'," +
                        "BAYNO='%s'," +
                        "OLDBAYNO='%s'," +
                        "CODE_CRANE='%s'," +
                        "BAYNUM='%s'," +
                        "BAYCOL='%s'," +
                        "BAYROW='%s'," +
                        "CONTAINER_NO='%s'," +
                        "SIZE_CON='%s'," +
                        "CONTAINER_TYPE='%s'," +
                        "CODE_EMPTY='%s'," +
                        "WEIGHT='%s'," +
                        "SEALNO='%s'," +
                        "MOVED_NAME='%s'," +
                        "INOUTMARK='%s'," +
                        "TransMark='%s'," +
                        "HOLIDAYS='%s'," +
                        "NIGHT='%s'," +
                        "NAME='%s'," +
                        "MARK_MODIFY='%s'," +
                        "MODIFIER='%s'," +
                        "MODIFYTIME='%s'" +
                        "where SHIP_ID='%s' and SBAYNO='%s' and CONTAINER_NO='%s'", tableName, b1
                        .getCode_load_port(), b1.getCode_unload_port(), b1.getDelivery(), moved1,
                        b1.getUnload_mark(), b1.getWork_no(), b1.getDanger_grade(), b1
                                .getDegree_setting(), b1.getDegree_unit(), b1.getMin_degree(), b1
                                .getMax_degree(), bayno1Next, oldbayno1, b1.getCode_crane(),
                        bayNum1Next, b1.getBay_col(), b1.getBay_row(), b1.getContainer_no(), b1
                                .getSize_con(), b1.getContainer_type(), b1.getCode_empty(), b1
                                .getWeight(), b1.getSealno(), moveName1, b1.getInoutmark(), b1
                                .getTransmark(), b1.getHolidays(), b1.getNight(), b1.getName(),
                        "1", Global.getLoginStatus().getUserID(), date, b2.getShip_id(),
                        sbayno2Next, b2.getContainer_no());

                Log.i(LOG_TAG + "swapBay", "sql2Next is " + sql2Next);

            } else if (b2.getSize_con().equals("20")) {

                sql2 = String.format("update %s set CODE_LOAD_PORT='%s'," +
                        "CODE_UNLOAD_PORT='%s'," +
                        "DELIVERY='%s'," +
                        "MOVED='%s'," +
                        "UNLOAD_MARK='%s'," +
                        "WORK_NO='%s'," +
                        "DANGER_GRADE='%s'," +
                        "DEGREE_SETTING='%s'," +
                        "DEGREE_UNIT='%s'," +
                        "MIN_DEGREE='%s'," +
                        "MAX_DEGREE='%s'," +
                        "BAYNO='%s'," +
                        "OLDBAYNO='%s'," +
                        "CODE_CRANE='%s'," +
                        "BAYNUM='%s'," +
                        "BAYCOL='%s'," +
                        "BAYROW='%s'," +
                        "CONTAINER_NO='%s'," +
                        "SIZE_CON='%s'," +
                        "CONTAINER_TYPE='%s'," +
                        "CODE_EMPTY='%s'," +
                        "WEIGHT='%s'," +
                        "SEALNO='%s'," +
                        "MOVED_NAME='%s'," +
                        "INOUTMARK='%s'," +
                        "TransMark='%s'," +
                        "HOLIDAYS='%s'," +
                        "NIGHT='%s'," +
                        "NAME='%s'," +
                        "MARK_MODIFY='%s'," +
                        "MODIFIER='%s'," +
                        "MODIFYTIME='%s'" +
                        "where SHIP_ID='%s' and SBAYNO='%s' and CONTAINER_NO='%s'", tableName, b1
                        .getCode_load_port(), b1.getCode_unload_port(), b1.getDelivery(), moved1,
                        b1.getUnload_mark(), b1.getWork_no(), b1.getDanger_grade(), b1
                                .getDegree_setting(), b1.getDegree_unit(), b1.getMin_degree(), b1
                                .getMax_degree(), bayno1, oldbayno1, b1.getCode_crane(), bayNum1,
                        b1.getBay_col(), b1.getBay_row(), b1.getContainer_no(), b1.getSize_con(),
                        b1.getContainer_type(), b1.getCode_empty(), b1.getWeight(), b1.getSealno
                                (), moveName1, b1.getInoutmark(), b1.getTransmark(), b1
                                .getHolidays(), b1.getNight(), b1.getName(), "1", Global
                                .getLoginStatus().getUserID(), date, b2.getShip_id(), b2
                                .getSbayno(), b2.getContainer_no());

                Log.i(LOG_TAG + "swapBay", "sql2 is " + sql2);

                sql2Next = String.format("update %s set CODE_LOAD_PORT='%s'," +
                        "CODE_UNLOAD_PORT='%s'," +
                        "DELIVERY='%s'," +
                        "MOVED='%s'," +
                        "UNLOAD_MARK='%s'," +
                        "WORK_NO='%s'," +
                        "DANGER_GRADE='%s'," +
                        "DEGREE_SETTING='%s'," +
                        "DEGREE_UNIT='%s'," +
                        "MIN_DEGREE='%s'," +
                        "MAX_DEGREE='%s'," +
                        "BAYNO='%s'," +
                        "OLDBAYNO='%s'," +
                        "CODE_CRANE='%s'," +
                        "BAYNUM='%s'," +
                        "BAYCOL='%s'," +
                        "BAYROW='%s'," +
                        "CONTAINER_NO='%s'," +
                        "SIZE_CON='%s'," +
                        "CONTAINER_TYPE='%s'," +
                        "CODE_EMPTY='%s'," +
                        "WEIGHT='%s'," +
                        "SEALNO='%s'," +
                        "MOVED_NAME='%s'," +
                        "INOUTMARK='%s'," +
                        "TransMark='%s'," +
                        "HOLIDAYS='%s'," +
                        "NIGHT='%s'," +
                        "NAME='%s'," +
                        "MARK_MODIFY='%s'," +
                        "MODIFIER='%s'," +
                        "MODIFYTIME='%s'" +
                        "where SHIP_ID='%s' and SBAYNO='%s' and (CONTAINER_NO is null or " +
                        "CONTAINER_NO='')", tableName, b1.getCode_load_port(), b1
                        .getCode_unload_port(), b1.getDelivery(), moved1, b1.getUnload_mark(), b1
                        .getWork_no(), b1.getDanger_grade(), b1.getDegree_setting(), b1
                        .getDegree_unit(), b1.getMin_degree(), b1.getMax_degree(), bayno1Next,
                        oldbayno1, b1.getCode_crane(), bayNum1Next, b1.getBay_col(), b1
                                .getBay_row(), b1.getContainer_no(), b1.getSize_con(), b1
                                .getContainer_type(), b1.getCode_empty(), b1.getWeight(), b1
                                .getSealno(), moveName1, b1.getInoutmark(), b1.getTransmark(), b1
                                .getHolidays(), b1.getNight(), b1.getName(), "1", Global
                                .getLoginStatus().getUserID(), date, b2.getShip_id(), sbayno2Next);

                Log.i(LOG_TAG + "swapBay", "sql2Next is " + sql2Next);

            } else {


                sql2 = String.format("update %s set CODE_LOAD_PORT='%s'," +
                        "CODE_UNLOAD_PORT='%s'," +
                        "DELIVERY='%s'," +
                        "MOVED='%s'," +
                        "UNLOAD_MARK='%s'," +
                        "WORK_NO='%s'," +
                        "DANGER_GRADE='%s'," +
                        "DEGREE_SETTING='%s'," +
                        "DEGREE_UNIT='%s'," +
                        "MIN_DEGREE='%s'," +
                        "MAX_DEGREE='%s'," +
                        "BAYNO='%s'," +
                        "OLDBAYNO='%s'," +
                        "CODE_CRANE='%s'," +
                        "BAYNUM='%s'," +
                        "BAYCOL='%s'," +
                        "BAYROW='%s'," +
                        "CONTAINER_NO='%s'," +
                        "SIZE_CON='%s'," +
                        "CONTAINER_TYPE='%s'," +
                        "CODE_EMPTY='%s'," +
                        "WEIGHT='%s'," +
                        "SEALNO='%s'," +
                        "MOVED_NAME='%s'," +
                        "INOUTMARK='%s'," +
                        "TransMark='%s'," +
                        "HOLIDAYS='%s'," +
                        "NIGHT='%s'," +
                        "NAME='%s'," +
                        "MARK_MODIFY='%s'," +
                        "MODIFIER='%s'," +
                        "MODIFYTIME='%s'" +
                        "where SHIP_ID='%s' and SBAYNO='%s' and (CONTAINER_NO is null or " +
                        "CONTAINER_NO='')", tableName, b1.getCode_load_port(), b1
                        .getCode_unload_port(), b1.getDelivery(), moved1, b1.getUnload_mark(), b1
                        .getWork_no(), b1.getDanger_grade(), b1.getDegree_setting(), b1
                        .getDegree_unit(), b1.getMin_degree(), b1.getMax_degree(), bayno1,
                        oldbayno1, b1.getCode_crane(), bayNum1, b1.getBay_col(), b1.getBay_row(),
                        b1.getContainer_no(), b1.getSize_con(), b1.getContainer_type(), b1
                                .getCode_empty(), b1.getWeight(), b1.getSealno(), moveName1, b1
                                .getInoutmark(), b1.getTransmark(), b1.getHolidays(), b1.getNight
                                (), b1.getName(), "1", Global.getLoginStatus().getUserID(), date,
                        b2.getShip_id(), b2.getSbayno());

                Log.i(LOG_TAG + "swapBay", "sql2 is " + sql2);

                sql2Next = String.format("update %s set CODE_LOAD_PORT='%s'," +
                        "CODE_UNLOAD_PORT='%s'," +
                        "DELIVERY='%s'," +
                        "MOVED='%s'," +
                        "UNLOAD_MARK='%s'," +
                        "WORK_NO='%s'," +
                        "DANGER_GRADE='%s'," +
                        "DEGREE_SETTING='%s'," +
                        "DEGREE_UNIT='%s'," +
                        "MIN_DEGREE='%s'," +
                        "MAX_DEGREE='%s'," +
                        "BAYNO='%s'," +
                        "OLDBAYNO='%s'," +
                        "CODE_CRANE='%s'," +
                        "BAYNUM='%s'," +
                        "BAYCOL='%s'," +
                        "BAYROW='%s'," +
                        "CONTAINER_NO='%s'," +
                        "SIZE_CON='%s'," +
                        "CONTAINER_TYPE='%s'," +
                        "CODE_EMPTY='%s'," +
                        "WEIGHT='%s'," +
                        "SEALNO='%s'," +
                        "MOVED_NAME='%s'," +
                        "INOUTMARK='%s'," +
                        "TransMark='%s'," +
                        "HOLIDAYS='%s'," +
                        "NIGHT='%s'," +
                        "NAME='%s'," +
                        "MARK_MODIFY='%s'," +
                        "MODIFIER='%s'," +
                        "MODIFYTIME='%s'" +
                        "where SHIP_ID='%s' and SBAYNO='%s' and (CONTAINER_NO is null or " +
                        "CONTAINER_NO='')", tableName, b1.getCode_load_port(), b1
                        .getCode_unload_port(), b1.getDelivery(), moved1, b1.getUnload_mark(), b1
                        .getWork_no(), b1.getDanger_grade(), b1.getDegree_setting(), b1
                        .getDegree_unit(), b1.getMin_degree(), b1.getMax_degree(), bayno1Next,
                        oldbayno1, b1.getCode_crane(), bayNum1Next, b1.getBay_col(), b1
                                .getBay_row(), b1.getContainer_no(), b1.getSize_con(), b1
                                .getContainer_type(), b1.getCode_empty(), b1.getWeight(), b1
                                .getSealno(), moveName1, b1.getInoutmark(), b1.getTransmark(), b1
                                .getHolidays(), b1.getNight(), b1.getName(), "1", Global
                                .getLoginStatus().getUserID(), date, b2.getShip_id(), sbayno2Next);


                Log.i(LOG_TAG + "swapBay", "sql2Next is " + sql2Next);
            }

            List<String> sqlList = new ArrayList<>();
            sqlList.add(sql1);
            sqlList.add(sql2);
            sqlList.add(sql1Next);
            sqlList.add(sql2Next);
            execSQL(sqlList);
        }
    }

    //    /**
    //     * 对调贝位
    //     *
    //     * @param b1        贝位1
    //     * @param b2        贝位2
    //     * @param codeInOut 进出口编码
    //     */
    //    public void swapBay(ShipImage b1, ShipImage b2, String codeInOut) {
    //
    //        Log.i(LOG_TAG + "swapBay", "query param is " + b1.getSbayno() + " " + b2.getSbayno());
    //
    //        Log.i(LOG_TAG + "swapBay", "b1 getSbayno is " + b1.getSbayno());
    //        Log.i(LOG_TAG + "swapBay", "b1 getBaynum is " + b1.getBaynum());
    //        Log.i(LOG_TAG + "swapBay", "b1 getBaycol is " + b1.getBaycol());
    //        Log.i(LOG_TAG + "swapBay", "b1 getBayrow is " + b1.getBayrow());
    //
    //        Log.i(LOG_TAG + "swapBay", "b2 getSbayno is " + b2.getSbayno());
    //        Log.i(LOG_TAG + "swapBay", "b2 getBaynum is " + b2.getBaynum());
    //        Log.i(LOG_TAG + "swapBay", "b2 getBaycol is " + b2.getBaycol());
    //        Log.i(LOG_TAG + "swapBay", "b2 getBayrow is " + b2.getBayrow());
    //
    //
    //        String moved1 = getMoved(b1, codeInOut);
    //        String moved2 = getMoved(b2, codeInOut);
    //        String moveName1 = moved1.equals("1") == true ? "捣箱" : "";
    //        String moveName2 = moved2.equals("1") == true ? "捣箱" : "";
    //        String oldbayno1 = b2.getOldbayno().isEmpty() == true ? b2.getBayno() : "";
    //        String oldbayno2 = b1.getOldbayno().isEmpty() == true ? b1.getBayno() : "";
    //
    //        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
    //        String date = sDateFormat.format(new java.util.Date());
    //
    //        Log.i(LOG_TAG + "swapBay", "date is " +date);
    //
    //
    //        Log.i(LOG_TAG + "swapBay", "Size_con is " + b1.getSize_con() + " " + b2.getSize_con
    // ());
    //
    //        if (b1.getSize_con().equals("20") && (b2.getSize_con().equals("20") || b2
    // .getSize_con().isEmpty())) {
    //
    //            String bayno1 = b2.getSbayno();
    //            String bayno2 = b2.getBayno().isEmpty() == true ? "" : b1.getSbayno();
    //
    //            String bayNum1 = b2.getBay_num();
    //            String bayNum2 = b2.getBayno().isEmpty() == true ? "" : b1.getBay_num();
    //
    //            String bayCol1 = b2.getBay_col();
    //            String bayCol2 = b2.getBayno().isEmpty() == true ? "" : b1.getBay_col();
    //
    //            String bayRow1 = b2.getBay_row();
    //            String bayRow2 = b2.getBayno().isEmpty() == true ? "" : b1.getBay_row();
    //
    //            Log.i(LOG_TAG + "swapBay", "bayno1 is " + bayno1);
    //            Log.i(LOG_TAG + "swapBay", "bayno2 is " + bayno2);
    //            Log.i(LOG_TAG + "swapBay", "bayNum1 is " + bayNum1);
    //            Log.i(LOG_TAG + "swapBay", "bayNum2 is " + bayNum2);
    //            Log.i(LOG_TAG + "swapBay", "bayCol1 is " + bayCol1);
    //            Log.i(LOG_TAG + "swapBay", "bayCol2 is " + bayCol2);
    //            Log.i(LOG_TAG + "swapBay", "bayRow1 is " + bayRow1);
    //            Log.i(LOG_TAG + "swapBay", "bayRow2 is " + bayRow2);
    //
    //            String sql1 = String.format("update %s set CODE_LOAD_PORT='%s'," +
    //                            "CODE_UNLOAD_PORT='%s'," +
    //                            "DELIVERY='%s'," +
    //                            "MOVED='%s'," +
    //                            "UNLOAD_MARK='%s'," +
    //                            "WORK_NO='%s'," +
    //                            "DANGER_GRADE='%s'," +
    //                            "DEGREE_SETTING='%s'," +
    //                            "DEGREE_UNIT='%s'," +
    //                            "MIN_DEGREE='%s'," +
    //                            "MAX_DEGREE='%s'," +
    //                            "BAYNO='%s'," +
    //                            "OLDBAYNO='%s'," +
    //                            "CODE_CRANE='%s'," +
    //                            "BAYNUM='%s'," +
    //                            "BAYCOL='%s'," +
    //                            "BAYROW='%s'," +
    //                            "CONTAINER_NO='%s'," +
    //                            "SIZE_CON='%s'," +
    //                            "CONTAINER_TYPE='%s'," +
    //                            "CODE_EMPTY='%s'," +
    //                            "WEIGHT='%s'," +
    //                            "SEALNO='%s'," +
    //                            "MOVED_NAME='%s'," +
    //                            "INOUTMARK='%s'," +
    //                            "TransMark='%s'," +
    //                            "HOLIDAYS='%s'," +
    //                            "NIGHT='%s'," +
    //                            "NAME='%s'," +
    //                            "MARK_MODIFY='%s'," +
    //                            "MODIFIER='%s'," +
    //                            "MODIFYTIME='%s'" +
    //                            "where SHIP_ID='%s' and SBAYNO='%s' and CONTAINER_NO='%s'",
    //                    tableName,
    //                    b2.getCode_load_port(), b2.getCode_unload_port(), b2.getDelivery(),
    // moved2, b2.getUnload_mark(), b2.getWork_no(),
    //                    b2.getDanger_grade(), b2.getDegree_setting(), b2.getDegree_unit(), b2
    // .getMin_degree(), b2.getMax_degree(), bayno2,
    //                    oldbayno2, b2.getCode_crane(), bayNum2, bayCol2, bayRow2, b2
    // .getContainer_no(), b2.getSize_con(),
    //                    b2.getContainer_type(), b2.getCode_empty(), b2.getWeight(), b2
    // .getSealno(), moveName2, b2.getInoutmark(),
    //                    b2.getTransmark(), b2.getHolidays(), b2.getNight(), b2.getName(), "1",
    // Global.getApplicationConfig().getUserName(), date,
    //                    b1.getShip_id(), b1.getSbayno(), b1.getContainer_no());
    //
    //            Log.i(LOG_TAG + "swapBay", "sql1 is " + sql1);
    //
    //            String sql2 = String.format("update %s set CODE_LOAD_PORT='%s'," +
    //                            "CODE_UNLOAD_PORT='%s'," +
    //                            "DELIVERY='%s'," +
    //                            "MOVED='%s'," +
    //                            "UNLOAD_MARK='%s'," +
    //                            "WORK_NO='%s'," +
    //                            "DANGER_GRADE='%s'," +
    //                            "DEGREE_SETTING='%s'," +
    //                            "DEGREE_UNIT='%s'," +
    //                            "MIN_DEGREE='%s'," +
    //                            "MAX_DEGREE='%s'," +
    //                            "BAYNO='%s'," +
    //                            "OLDBAYNO='%s'," +
    //                            "CODE_CRANE='%s'," +
    //                            "BAYNUM='%s'," +
    //                            "BAYCOL='%s'," +
    //                            "BAYROW='%s'," +
    //                            "CONTAINER_NO='%s'," +
    //                            "SIZE_CON='%s'," +
    //                            "CONTAINER_TYPE='%s'," +
    //                            "CODE_EMPTY='%s'," +
    //                            "WEIGHT='%s'," +
    //                            "SEALNO='%s'," +
    //                            "MOVED_NAME='%s'," +
    //                            "INOUTMARK='%s'," +
    //                            "TransMark='%s'," +
    //                            "HOLIDAYS='%s'," +
    //                            "NIGHT='%s'," +
    //                            "NAME='%s'," +
    //                            "MARK_MODIFY='%s'," +
    //                            "MODIFIER='%s'," +
    //                            "MODIFYTIME='%s'" +
    //                            "where SHIP_ID='%s' and SBAYNO='%s' and CONTAINER_NO='%s'",
    //                    tableName,
    //                    b1.getCode_load_port(), b1.getCode_unload_port(), b1.getDelivery(),
    // moved1, b1.getUnload_mark(), b1.getWork_no(),
    //                    b1.getDanger_grade(), b1.getDegree_setting(), b1.getDegree_unit(), b1
    // .getMin_degree(), b1.getMax_degree(), bayno1,
    //                    oldbayno1, b1.getCode_crane(), bayNum1, bayCol1, bayRow1, b1
    // .getContainer_no(), b1.getSize_con(),
    //                    b1.getContainer_type(), b1.getCode_empty(), b1.getWeight(), b1
    // .getSealno(), moveName1, b1.getInoutmark(),
    //                    b1.getTransmark(), b1.getHolidays(), b1.getNight(), b1.getName(), "1",
    // Global.getApplicationConfig().getUserName(), date,
    //                    b2.getShip_id(), b2.getSbayno(), b2.getContainer_no());
    //
    //            Log.i(LOG_TAG + "swapBay", "sql2 is " + sql2);
    //
    //            List<String> sqlList = new ArrayList<>();
    //            sqlList.add(sql1);
    //            sqlList.add(sql2);
    //            execSQL(sqlList);
    //
    //        } else if (b1.getSize_con().equals("20") && (b2.getSize_con().equals("40") || b2
    // .getSize_con().equals("45"))) {
    //
    //            String bayno1 = b2.getSbayno();
    //            String bayno1Next = null;
    //            if ((Integer.parseInt(b2.getBay_num()) + 2) < 10){
    //
    //                bayno1Next = "0" + Integer.toString(Integer.parseInt(b2.getBay_num()) + 2)
    // + b2.getBay_col() + b2.getBay_row();
    //
    //            }else {
    //
    //                bayno1Next = Integer.toString(Integer.parseInt(b2.getBay_num()) + 2) + b2
    // .getBay_col() + b2.getBay_row();
    //
    //            }
    //            String bayno2 = null;
    //            if ((Integer.parseInt(b1.getBay_num()) + 1) < 10){
    //
    //                bayno2 = "0" + Integer.toString(Integer.parseInt(b1.getBay_num()) + 1) + b1
    // .getBay_col() + b1.getBay_row();
    //            }else {
    //
    //                bayno2 = Integer.toString(Integer.parseInt(b1.getBay_num()) + 1) + b1
    // .getBay_col() + b1.getBay_row();
    //
    //            }
    //            String bayno2Next = bayno2;
    //
    //            String sbayno1Next = null;
    //            if ((Integer.parseInt(b1.getBay_num()) + 2) < 10){
    //
    //                sbayno1Next = "0" + Integer.toString(Integer.parseInt(b1.getBay_num()) + 2)
    // + b1.getBay_col() + b1.getBay_row();
    //            }else {
    //
    //                sbayno1Next = Integer.toString(Integer.parseInt(b1.getBay_num()) + 2) + b1
    // .getBay_col() + b1.getBay_row();
    //
    //            }
    //            String sbayno2Next = null;
    //            if ((Integer.parseInt(b2.getBay_num()) + 2) < 10){
    //
    //                sbayno2Next = "0" + Integer.toString(Integer.parseInt(b2.getBay_num()) + 2)
    // + b2.getBay_col() + b2.getBay_row();
    //            }else {
    //
    //                sbayno2Next = Integer.toString(Integer.parseInt(b2.getBay_num()) + 2) + b2
    // .getBay_col() + b2.getBay_row();
    //
    //            }
    //
    //            String bayNum1 = b2.getBaynum();
    //            String bayNum1Next = null;
    //            if ((Integer.parseInt(b2.getBay_num()) + 2) < 10){
    //
    //                bayNum1Next = "0" + Integer.toString(Integer.parseInt(b2.getBay_num()) + 2);
    //            }else {
    //
    //                bayNum1Next = Integer.toString(Integer.parseInt(b2.getBay_num()) + 2);
    //
    //            }
    //            String bayNum2 = null;
    //            if ((Integer.parseInt(b1.getBay_num()) + 1) < 10){
    //
    //                bayNum2 = "0" + Integer.toString(Integer.parseInt(b1.getBay_num()) + 1);
    //            }else {
    //
    //                bayNum2 = Integer.toString(Integer.parseInt(b1.getBay_num()) + 1);
    //
    //            }
    //            String bayNum2Next = bayNum2;
    //
    //            Log.i(LOG_TAG + "swapBay", "bayno1 is " + bayno1);
    //            Log.i(LOG_TAG + "swapBay", "bayno1Next is " + bayno1Next);
    //            Log.i(LOG_TAG + "swapBay", "bayno2 is " + bayno2);
    //            Log.i(LOG_TAG + "swapBay", "bayno2Next is " + bayno2Next);
    //
    //            Log.i(LOG_TAG + "swapBay", "sbayno1Next is " + sbayno1Next);
    //            Log.i(LOG_TAG + "swapBay", "sbayno2Next is " + sbayno2Next);
    //
    //            Log.i(LOG_TAG + "swapBay", "bayNum1 is " + bayNum1);
    //            Log.i(LOG_TAG + "swapBay", "bayNum1Next is " + bayNum1Next);
    //            Log.i(LOG_TAG + "swapBay", "bayNum2 is " + bayNum2);
    //            Log.i(LOG_TAG + "swapBay", "bayNum2Next is " + bayNum2Next);
    //
    //
    //            String sql1 = String.format("update %s set CODE_LOAD_PORT='%s'," +
    //                            "CODE_UNLOAD_PORT='%s'," +
    //                            "DELIVERY='%s'," +
    //                            "MOVED='%s'," +
    //                            "UNLOAD_MARK='%s'," +
    //                            "WORK_NO='%s'," +
    //                            "DANGER_GRADE='%s'," +
    //                            "DEGREE_SETTING='%s'," +
    //                            "DEGREE_UNIT='%s'," +
    //                            "MIN_DEGREE='%s'," +
    //                            "MAX_DEGREE='%s'," +
    //                            "BAYNO='%s'," +
    //                            "OLDBAYNO='%s'," +
    //                            "CODE_CRANE='%s'," +
    //                            "BAYNUM='%s'," +
    //                            "BAYCOL='%s'," +
    //                            "BAYROW='%s'," +
    //                            "CONTAINER_NO='%s'," +
    //                            "SIZE_CON='%s'," +
    //                            "CONTAINER_TYPE='%s'," +
    //                            "CODE_EMPTY='%s'," +
    //                            "WEIGHT='%s'," +
    //                            "SEALNO='%s'," +
    //                            "MOVED_NAME='%s'," +
    //                            "INOUTMARK='%s'," +
    //                            "TransMark='%s'," +
    //                            "HOLIDAYS='%s'," +
    //                            "NIGHT='%s'," +
    //                            "NAME='%s'," +
    //                            "MARK_MODIFY='%s'," +
    //                            "MODIFIER='%s'," +
    //                            "MODIFYTIME='%s'" +
    //                            "where SHIP_ID='%s' and SBAYNO='%s' and CONTAINER_NO='%s'",
    //                    tableName,
    //                    b2.getCode_load_port(), b2.getCode_unload_port(), b2.getDelivery(),
    // moved2, b2.getUnload_mark(), b2.getWork_no(),
    //                    b2.getDanger_grade(), b2.getDegree_setting(), b2.getDegree_unit(), b2
    // .getMin_degree(), b2.getMax_degree(), bayno2,
    //                    oldbayno2, b2.getCode_crane(), bayNum2, b2.getBay_col(), b2.getBay_row
    // (), b2.getContainer_no(), b2.getSize_con(),
    //                    b2.getContainer_type(), b2.getCode_empty(), b2.getWeight(), b2
    // .getSealno(), moveName2, b2.getInoutmark(),
    //                    b2.getTransmark(), b2.getHolidays(), b2.getNight(), b2.getName(), "1",
    // Global.getApplicationConfig().getUserName(), date,
    //                    b1.getShip_id(), b1.getSbayno(), b1.getContainer_no());
    //
    //            Log.i(LOG_TAG + "swapBay", "sql1 is " + sql1);
    //
    //            String sql2 = String.format("update %s set CODE_LOAD_PORT='%s'," +
    //                            "CODE_UNLOAD_PORT='%s'," +
    //                            "DELIVERY='%s'," +
    //                            "MOVED='%s'," +
    //                            "UNLOAD_MARK='%s'," +
    //                            "WORK_NO='%s'," +
    //                            "DANGER_GRADE='%s'," +
    //                            "DEGREE_SETTING='%s'," +
    //                            "DEGREE_UNIT='%s'," +
    //                            "MIN_DEGREE='%s'," +
    //                            "MAX_DEGREE='%s'," +
    //                            "BAYNO='%s'," +
    //                            "OLDBAYNO='%s'," +
    //                            "CODE_CRANE='%s'," +
    //                            "BAYNUM='%s'," +
    //                            "BAYCOL='%s'," +
    //                            "BAYROW='%s'," +
    //                            "CONTAINER_NO='%s'," +
    //                            "SIZE_CON='%s'," +
    //                            "CONTAINER_TYPE='%s'," +
    //                            "CODE_EMPTY='%s'," +
    //                            "WEIGHT='%s'," +
    //                            "SEALNO='%s'," +
    //                            "MOVED_NAME='%s'," +
    //                            "INOUTMARK='%s'," +
    //                            "TransMark='%s'," +
    //                            "HOLIDAYS='%s'," +
    //                            "NIGHT='%s'," +
    //                            "NAME='%s'," +
    //                            "MARK_MODIFY='%s'," +
    //                            "MODIFIER='%s'," +
    //                            "MODIFYTIME='%s'" +
    //                            "where SHIP_ID='%s' and SBAYNO='%s' and CONTAINER_NO='%s'",
    //                    tableName,
    //                    b1.getCode_load_port(), b1.getCode_unload_port(), b1.getDelivery(),
    // moved1, b1.getUnload_mark(), b1.getWork_no(),
    //                    b1.getDanger_grade(), b1.getDegree_setting(), b1.getDegree_unit(), b1
    // .getMin_degree(), b1.getMax_degree(), bayno1,
    //                    oldbayno1, b1.getCode_crane(), bayNum1, b1.getBay_col(), b1.getBay_row
    // (), b1.getContainer_no(), b1.getSize_con(),
    //                    b1.getContainer_type(), b1.getCode_empty(), b1.getWeight(), b1
    // .getSealno(), moveName1, b1.getInoutmark(),
    //                    b1.getTransmark(), b1.getHolidays(), b1.getNight(), b1.getName(), "1",
    // Global.getApplicationConfig().getUserName(), date,
    //                    b2.getShip_id(), b2.getSbayno(), b2.getContainer_no());
    //
    //            Log.i(LOG_TAG + "swapBay", "sql2 is " + sql2);
    //
    //
    //            String sql1Next = String.format("update %s set CODE_LOAD_PORT='%s'," +
    //                            "CODE_UNLOAD_PORT='%s'," +
    //                            "DELIVERY='%s'," +
    //                            "MOVED='%s'," +
    //                            "UNLOAD_MARK='%s'," +
    //                            "WORK_NO='%s'," +
    //                            "DANGER_GRADE='%s'," +
    //                            "DEGREE_SETTING='%s'," +
    //                            "DEGREE_UNIT='%s'," +
    //                            "MIN_DEGREE='%s'," +
    //                            "MAX_DEGREE='%s'," +
    //                            "BAYNO='%s'," +
    //                            "OLDBAYNO='%s'," +
    //                            "CODE_CRANE='%s'," +
    //                            "BAYNUM='%s'," +
    //                            "BAYCOL='%s'," +
    //                            "BAYROW='%s'," +
    //                            "CONTAINER_NO='%s'," +
    //                            "SIZE_CON='%s'," +
    //                            "CONTAINER_TYPE='%s'," +
    //                            "CODE_EMPTY='%s'," +
    //                            "WEIGHT='%s'," +
    //                            "SEALNO='%s'," +
    //                            "MOVED_NAME='%s'," +
    //                            "INOUTMARK='%s'," +
    //                            "TransMark='%s'," +
    //                            "HOLIDAYS='%s'," +
    //                            "NIGHT='%s'," +
    //                            "NAME='%s'," +
    //                            "MARK_MODIFY='%s'," +
    //                            "MODIFIER='%s'," +
    //                            "MODIFYTIME='%s'" +
    //                            "where SHIP_ID='%s' and SBAYNO='%s' and CONTAINER_NO='%s'",
    //                    tableName,
    //                    b2.getCode_load_port(), b2.getCode_unload_port(), b2.getDelivery(),
    // moved2, b2.getUnload_mark(), b2.getWork_no(),
    //                    b2.getDanger_grade(), b2.getDegree_setting(), b2.getDegree_unit(), b2
    // .getMin_degree(), b2.getMax_degree(), bayno2Next,
    //                    oldbayno2, b2.getCode_crane(), bayNum2Next, b2.getBay_col(), b2
    // .getBay_row(), b2.getContainer_no(), b2.getSize_con(),
    //                    b2.getContainer_type(), b2.getCode_empty(), b2.getWeight(), b2
    // .getSealno(), moveName2, b2.getInoutmark(),
    //                    b2.getTransmark(), b2.getHolidays(), b2.getNight(), b2.getName(), "1",
    // Global.getApplicationConfig().getUserName(), date,
    //                    b1.getShip_id(), sbayno1Next, b2.getContainer_no());
    //
    //            Log.i(LOG_TAG + "swapBay", "sql1Next is " + sql1Next);
    //
    //
    //            String sql2Next = String.format("update %s set CODE_LOAD_PORT='%s'," +
    //                            "CODE_UNLOAD_PORT='%s'," +
    //                            "DELIVERY='%s'," +
    //                            "MOVED='%s'," +
    //                            "UNLOAD_MARK='%s'," +
    //                            "WORK_NO='%s'," +
    //                            "DANGER_GRADE='%s'," +
    //                            "DEGREE_SETTING='%s'," +
    //                            "DEGREE_UNIT='%s'," +
    //                            "MIN_DEGREE='%s'," +
    //                            "MAX_DEGREE='%s'," +
    //                            "BAYNO='%s'," +
    //                            "OLDBAYNO='%s'," +
    //                            "CODE_CRANE='%s'," +
    //                            "BAYNUM='%s'," +
    //                            "BAYCOL='%s'," +
    //                            "BAYROW='%s'," +
    //                            "CONTAINER_NO='%s'," +
    //                            "SIZE_CON='%s'," +
    //                            "CONTAINER_TYPE='%s'," +
    //                            "CODE_EMPTY='%s'," +
    //                            "WEIGHT='%s'," +
    //                            "SEALNO='%s'," +
    //                            "MOVED_NAME='%s'," +
    //                            "INOUTMARK='%s'," +
    //                            "TransMark='%s'," +
    //                            "HOLIDAYS='%s'," +
    //                            "NIGHT='%s'," +
    //                            "NAME='%s'," +
    //                            "MARK_MODIFY='%s'," +
    //                            "MODIFIER='%s'," +
    //                            "MODIFYTIME='%s'" +
    //                            "where SHIP_ID='%s' and SBAYNO='%s'",
    //                    tableName,
    //                    b1.getCode_load_port(), b1.getCode_unload_port(), b1.getDelivery(),
    // moved1, b1.getUnload_mark(), b1.getWork_no(),
    //                    b1.getDanger_grade(), b1.getDegree_setting(), b1.getDegree_unit(), b1
    // .getMin_degree(), b1.getMax_degree(), bayno1Next,
    //                    oldbayno1, b1.getCode_crane(), bayNum1Next, b1.getBay_col(), b1
    // .getBay_row(), b1.getContainer_no(), b1.getSize_con(),
    //                    b1.getContainer_type(), b1.getCode_empty(), b1.getWeight(), b1
    // .getSealno(), moveName1, b1.getInoutmark(),
    //                    b1.getTransmark(), b1.getHolidays(), b1.getNight(), b1.getName(), "1",
    // Global.getApplicationConfig().getUserName(), date,
    //                    b2.getShip_id(), sbayno2Next);
    //
    //            Log.i(LOG_TAG + "swapBay", "sql2Next is " + sql2Next);
    //
    //            List<String> sqlList = new ArrayList<>();
    //            sqlList.add(sql1);
    //            sqlList.add(sql2);
    //            sqlList.add(sql1Next);
    //            sqlList.add(sql2Next);
    //            execSQL(sqlList);
    //
    //        } else if (b1.getSize_con().equals("40")) {
    //
    //            String bayno1 = null;
    //            if ((Integer.parseInt(b2.getBay_num()) + 1) < 10){
    //
    //                bayno1 = "0" + Integer.toString(Integer.parseInt(b2.getBay_num()) + 1) + b2
    // .getBay_col() + b2.getBay_row();
    //
    //            }else {
    //
    //                bayno1 = Integer.toString(Integer.parseInt(b2.getBay_num()) + 1) + b2
    // .getBay_col() + b2.getBay_row();
    //
    //            }
    //            String bayno1Next = bayno1;
    //            String bayno2 = b2.getBayno().isEmpty() == true ? "" : b1.getSbayno();
    //            String bayno2Next = "";
    //
    //
    //            String sbayno1Next = null;
    //            if ((Integer.parseInt(b1.getBay_num()) + 2) < 10){
    //
    //                sbayno1Next = "0" + Integer.toString(Integer.parseInt(b1.getBay_num()) + 2)
    // + b1.getBay_col() + b1.getBay_row();
    //
    //            }else {
    //
    //                sbayno1Next = Integer.toString(Integer.parseInt(b1.getBay_num()) + 2) + b1
    // .getBay_col() + b1.getBay_row();
    //
    //            }
    //            String sbayno2Next = null;
    //            if ((Integer.parseInt(b2.getBay_num()) + 2) < 10){
    //
    //                sbayno2Next = "0" + Integer.toString(Integer.parseInt(b2.getBay_num()) + 2)
    // + b2.getBay_col() + b2.getBay_row();
    //
    //            }else {
    //
    //                sbayno2Next = Integer.toString(Integer.parseInt(b2.getBay_num()) + 2) + b2
    // .getBay_col() + b2.getBay_row();
    //
    //            }
    //
    //            String bayNum1 = null;
    //            if ((Integer.parseInt(b2.getBay_num()) + 1) < 10){
    //
    //                bayNum1 = "0" + Integer.toString(Integer.parseInt(b2.getBay_num()) + 1);
    //
    //            }else {
    //
    //                bayNum1 = Integer.toString(Integer.parseInt(b2.getBay_num()) + 1);
    //
    //            }
    //            String bayNum1Next = bayNum1;
    //            String bayNum2 = b2.getBayno().isEmpty() == true ? "" : b1.getBaynum();
    //            String bayNum2Next = "";
    //
    //            String bayCol1 = b2.getBay_col();
    //            String bayCol1Next = bayCol1;
    //            String bayCol2 = b2.getBayno().isEmpty() == true ? "" : b1.getBay_col();
    //            String bayCol2Next = "";
    //
    //            String bayRow1 = b2.getBay_row();
    //            String bayRow1Next = bayRow1;
    //            String bayRow2 = b2.getBayno().isEmpty() == true ? "" : b1.getBay_row();
    //            String bayRow2Next = "";
    //
    //            Log.i(LOG_TAG + "swapBay", "bayno1 is " + bayno1);
    //            Log.i(LOG_TAG + "swapBay", "bayno1Next is " + bayno1Next);
    //            Log.i(LOG_TAG + "swapBay", "bayno2 is " + bayno2);
    //            Log.i(LOG_TAG + "swapBay", "bayno2Next is " + bayno2Next);
    //
    //            Log.i(LOG_TAG + "swapBay", "sbayno1Next is " + sbayno1Next);
    //            Log.i(LOG_TAG + "swapBay", "sbayno2Next is " + sbayno2Next);
    //
    //            Log.i(LOG_TAG + "swapBay", "bayNum1 is " + bayNum1);
    //            Log.i(LOG_TAG + "swapBay", "bayNum1Next is " + bayNum1Next);
    //            Log.i(LOG_TAG + "swapBay", "bayNum2 is " + bayNum2);
    //            Log.i(LOG_TAG + "swapBay", "bayNum2Next is " + bayNum2Next);
    //
    //            Log.i(LOG_TAG + "swapBay", "bayCol1 is " + bayCol1);
    //            Log.i(LOG_TAG + "swapBay", "bayCol1Next is " + bayCol1Next);
    //            Log.i(LOG_TAG + "swapBay", "bayCol2 is " + bayCol2);
    //            Log.i(LOG_TAG + "swapBay", "bayCol2Next is " + bayCol2Next);
    //
    //            Log.i(LOG_TAG + "swapBay", "bayRow1 is " + bayRow1);
    //            Log.i(LOG_TAG + "swapBay", "bayRow1Next is " + bayRow1Next);
    //            Log.i(LOG_TAG + "swapBay", "bayRow2 is " + bayRow2);
    //            Log.i(LOG_TAG + "swapBay", "bayRow2Next is " + bayRow2Next);
    //
    //            String sql1 = String.format("update %s set CODE_LOAD_PORT='%s'," +
    //                            "CODE_UNLOAD_PORT='%s'," +
    //                            "DELIVERY='%s'," +
    //                            "MOVED='%s'," +
    //                            "UNLOAD_MARK='%s'," +
    //                            "WORK_NO='%s'," +
    //                            "DANGER_GRADE='%s'," +
    //                            "DEGREE_SETTING='%s'," +
    //                            "DEGREE_UNIT='%s'," +
    //                            "MIN_DEGREE='%s'," +
    //                            "MAX_DEGREE='%s'," +
    //                            "BAYNO='%s'," +
    //                            "OLDBAYNO='%s'," +
    //                            "CODE_CRANE='%s'," +
    //                            "BAYNUM='%s'," +
    //                            "BAYCOL='%s'," +
    //                            "BAYROW='%s'," +
    //                            "CONTAINER_NO='%s'," +
    //                            "SIZE_CON='%s'," +
    //                            "CONTAINER_TYPE='%s'," +
    //                            "CODE_EMPTY='%s'," +
    //                            "WEIGHT='%s'," +
    //                            "SEALNO='%s'," +
    //                            "MOVED_NAME='%s'," +
    //                            "INOUTMARK='%s'," +
    //                            "TransMark='%s'," +
    //                            "HOLIDAYS='%s'," +
    //                            "NIGHT='%s'," +
    //                            "NAME='%s'," +
    //                            "MARK_MODIFY='%s'," +
    //                            "MODIFIER='%s'," +
    //                            "MODIFYTIME='%s'" +
    //                            "where SHIP_ID='%s' and SBAYNO='%s' and CONTAINER_NO='%s'",
    //                    tableName,
    //                    b2.getCode_load_port(), b2.getCode_unload_port(), b2.getDelivery(),
    // moved2, b2.getUnload_mark(), b2.getWork_no(),
    //                    b2.getDanger_grade(), b2.getDegree_setting(), b2.getDegree_unit(), b2
    // .getMin_degree(), b2.getMax_degree(), bayno2,
    //                    oldbayno2, b2.getCode_crane(), bayNum2, bayCol2, b2.getBay_row(), b2
    // .getContainer_no(), b2.getSize_con(),
    //                    b2.getContainer_type(), b2.getCode_empty(), b2.getWeight(), b2
    // .getSealno(), moveName2, b2.getInoutmark(),
    //                    b2.getTransmark(), b2.getHolidays(), b2.getNight(), b2.getName(), "1",
    // Global.getApplicationConfig().getUserName(), date,
    //                    b1.getShip_id(), b1.getSbayno());
    //
    //            Log.i(LOG_TAG + "swapBay", "sql1 is " + sql1);
    //
    //            String sql2 = String.format("update %s set CODE_LOAD_PORT='%s'," +
    //                            "CODE_UNLOAD_PORT='%s'," +
    //                            "DELIVERY='%s'," +
    //                            "MOVED='%s'," +
    //                            "UNLOAD_MARK='%s'," +
    //                            "WORK_NO='%s'," +
    //                            "DANGER_GRADE='%s'," +
    //                            "DEGREE_SETTING='%s'," +
    //                            "DEGREE_UNIT='%s'," +
    //                            "MIN_DEGREE='%s'," +
    //                            "MAX_DEGREE='%s'," +
    //                            "BAYNO='%s'," +
    //                            "OLDBAYNO='%s'," +
    //                            "CODE_CRANE='%s'," +
    //                            "BAYNUM='%s'," +
    //                            "BAYCOL='%s'," +
    //                            "BAYROW='%s'," +
    //                            "CONTAINER_NO='%s'," +
    //                            "SIZE_CON='%s'," +
    //                            "CONTAINER_TYPE='%s'," +
    //                            "CODE_EMPTY='%s'," +
    //                            "WEIGHT='%s'," +
    //                            "SEALNO='%s'," +
    //                            "MOVED_NAME='%s'," +
    //                            "INOUTMARK='%s'," +
    //                            "TransMark='%s'," +
    //                            "HOLIDAYS='%s'," +
    //                            "NIGHT='%s'," +
    //                            "NAME='%s'," +
    //                            "MARK_MODIFY='%s'," +
    //                            "MODIFIER='%s'," +
    //                            "MODIFYTIME='%s'" +
    //                            "where SHIP_ID='%s' and SBAYNO='%s' and CONTAINER_NO='%s'",
    //                    tableName,
    //                    b1.getCode_load_port(), b1.getCode_unload_port(), b1.getDelivery(),
    // moved1, b1.getUnload_mark(), b1.getWork_no(),
    //                    b1.getDanger_grade(), b1.getDegree_setting(), b1.getDegree_unit(), b1
    // .getMin_degree(), b1.getMax_degree(), bayno1,
    //                    oldbayno1, b1.getCode_crane(), bayNum1, b1.getBay_col(), b1.getBay_row
    // (), b1.getContainer_no(), b1.getSize_con(),
    //                    b1.getContainer_type(), b1.getCode_empty(), b1.getWeight(), b1
    // .getSealno(), moveName1, b1.getInoutmark(),
    //                    b1.getTransmark(), b1.getHolidays(), b1.getNight(), b1.getName(), "1",
    // Global.getApplicationConfig().getUserName(), date,
    //                    b2.getShip_id(), b2.getSbayno());
    //
    //            Log.i(LOG_TAG + "swapBay", "sql2 is " + sql2);
    //
    //
    //            String sql1Next = String.format("update %s set CODE_LOAD_PORT='%s'," +
    //                            "CODE_UNLOAD_PORT='%s'," +
    //                            "DELIVERY='%s'," +
    //                            "MOVED='%s'," +
    //                            "UNLOAD_MARK='%s'," +
    //                            "WORK_NO='%s'," +
    //                            "DANGER_GRADE='%s'," +
    //                            "DEGREE_SETTING='%s'," +
    //                            "DEGREE_UNIT='%s'," +
    //                            "MIN_DEGREE='%s'," +
    //                            "MAX_DEGREE='%s'," +
    //                            "BAYNO='%s'," +
    //                            "OLDBAYNO='%s'," +
    //                            "CODE_CRANE='%s'," +
    //                            "BAYNUM='%s'," +
    //                            "BAYCOL='%s'," +
    //                            "BAYROW='%s'," +
    //                            "CONTAINER_NO='%s'," +
    //                            "SIZE_CON='%s'," +
    //                            "CONTAINER_TYPE='%s'," +
    //                            "CODE_EMPTY='%s'," +
    //                            "WEIGHT='%s'," +
    //                            "SEALNO='%s'," +
    //                            "MOVED_NAME='%s'," +
    //                            "INOUTMARK='%s'," +
    //                            "TransMark='%s'," +
    //                            "HOLIDAYS='%s'," +
    //                            "NIGHT='%s'," +
    //                            "NAME='%s'," +
    //                            "MARK_MODIFY='%s'," +
    //                            "MODIFIER='%s'," +
    //                            "MODIFYTIME='%s'" +
    //                            "where SHIP_ID='%s' and SBAYNO='%s' and CONTAINER_NO='%s'",
    //                    tableName,
    //                    b2.getCode_load_port(), b2.getCode_unload_port(), b2.getDelivery(),
    // moved2, b2.getUnload_mark(), b2.getWork_no(),
    //                    b2.getDanger_grade(), b2.getDegree_setting(), b2.getDegree_unit(), b2
    // .getMin_degree(), b2.getMax_degree(), bayno2Next,
    //                    oldbayno2, b2.getCode_crane(), bayNum2Next, b2.getBay_col(), b2
    // .getBay_row(), b2.getContainer_no(), b2.getSize_con(),
    //                    b2.getContainer_type(), b2.getCode_empty(), b2.getWeight(), b2
    // .getSealno(), moveName2, b2.getInoutmark(),
    //                    b2.getTransmark(), b2.getHolidays(), b2.getNight(), b2.getName(), "1",
    // Global.getApplicationConfig().getUserName(), date,
    //                    b1.getShip_id(), sbayno1Next);
    //
    //            Log.i(LOG_TAG + "swapBay", "sql1Next is " + sql1Next);
    //
    //
    //            String sql2Next = String.format("update %s set CODE_LOAD_PORT='%s'," +
    //                            "CODE_UNLOAD_PORT='%s'," +
    //                            "DELIVERY='%s'," +
    //                            "MOVED='%s'," +
    //                            "UNLOAD_MARK='%s'," +
    //                            "WORK_NO='%s'," +
    //                            "DANGER_GRADE='%s'," +
    //                            "DEGREE_SETTING='%s'," +
    //                            "DEGREE_UNIT='%s'," +
    //                            "MIN_DEGREE='%s'," +
    //                            "MAX_DEGREE='%s'," +
    //                            "BAYNO='%s'," +
    //                            "OLDBAYNO='%s'," +
    //                            "CODE_CRANE='%s'," +
    //                            "BAYNUM='%s'," +
    //                            "BAYCOL='%s'," +
    //                            "BAYROW='%s'," +
    //                            "CONTAINER_NO='%s'," +
    //                            "SIZE_CON='%s'," +
    //                            "CONTAINER_TYPE='%s'," +
    //                            "CODE_EMPTY='%s'," +
    //                            "WEIGHT='%s'," +
    //                            "SEALNO='%s'," +
    //                            "MOVED_NAME='%s'," +
    //                            "INOUTMARK='%s'," +
    //                            "TransMark='%s'," +
    //                            "HOLIDAYS='%s'," +
    //                            "NIGHT='%s'," +
    //                            "NAME='%s'," +
    //                            "MARK_MODIFY='%s'," +
    //                            "MODIFIER='%s'," +
    //                            "MODIFYTIME='%s'" +
    //                            "where SHIP_ID='%s' and SBAYNO='%s' and CONTAINER_NO='%s'",
    //                    tableName,
    //                    b1.getCode_load_port(), b1.getCode_unload_port(), b1.getDelivery(),
    // moved1, b1.getUnload_mark(), b1.getWork_no(),
    //                    b1.getDanger_grade(), b1.getDegree_setting(), b1.getDegree_unit(), b1
    // .getMin_degree(), b1.getMax_degree(), bayno1Next,
    //                    oldbayno1, b1.getCode_crane(), bayNum1Next, b1.getBay_col(), b1
    // .getBay_row(), b1.getContainer_no(), b1.getSize_con(),
    //                    b1.getContainer_type(), b1.getCode_empty(), b1.getWeight(), b1
    // .getSealno(), moveName1, b1.getInoutmark(),
    //                    b1.getTransmark(), b1.getHolidays(), b1.getNight(), b1.getName(), "1",
    // Global.getApplicationConfig().getUserName(), date,
    //                    b2.getShip_id(), sbayno2Next);
    //
    //            Log.i(LOG_TAG + "swapBay", "sql2Next is " + sql2Next);
    //
    //            List<String> sqlList = new ArrayList<>();
    //            sqlList.add(sql1);
    //            sqlList.add(sql2);
    //            sqlList.add(sql1Next);
    //            sqlList.add(sql2Next);
    //            execSQL(sqlList);
    //        }
    //    }

    private void execSQL(List<String> sqlList) {

        Log.i(LOG_TAG + "execSQL", "execSQL is invoked");

        SQLiteDatabase sqLiteDatabase = sqLiteHelper.getReadableDatabase();

        try {

            //开始设置事物
            sqLiteDatabase.beginTransaction();

            for (int i = 0; i < sqlList.size(); i++) {

                Log.i(LOG_TAG + "execSQL", "sql is " + sqlList.get(i));
                sqLiteDatabase.execSQL(sqlList.get(i));

            }

            //设置事务处理成功，不设置会自动回滚不提交
            sqLiteDatabase.setTransactionSuccessful();


        } catch (Exception e) {

            Log.e(LOG_TAG + "execSQL", "exception type is " + e);
            Log.e(LOG_TAG + "execSQL", "exception message is " + e.getMessage());

        } finally {

            Log.v(LOG_TAG + "execSQL", "parse end");

            sqLiteDatabase.endTransaction();

            close(sqLiteHelper);


        }
    }

    /**
     * 获取捣箱
     *
     * @param b         贝位
     * @param codeInOut 进出口编码
     *
     * @return 捣箱
     */
    private String getMoved(ShipImage b, String codeInOut) {
        String moved = "0";

        Log.i(LOG_TAG + "getMoved", "params is " + codeInOut + " " + b.getCode_unload_port() + " " +
                "" + b.getCode_load_port());

        if (codeInOut.equals("0") && !b.getCode_unload_port().equals("CNLYG")) {

            moved = "1";

        } else if (codeInOut.equals("1") && !b.getCode_load_port().equals("CNLYG")) {

            moved = "1";

        }

        Log.i(LOG_TAG + "getMoved", "moved is " + moved);


        return moved;

    }


    /**
     * 根据航次编码查询已修改的船图数据
     *
     * @param shipId 航次编码
     *
     * @return 数据对象，没有返回null
     */
    public List<ShipImage> queryShipImageOfModify(String shipId) {
        Log.i(LOG_TAG + "queryShipImageOfModify", "query param is " + shipId);

        // 查询语句
        String sql = String.format("select * from %s where %s='%s' and %s='%s'", tableName,
                "ship_id", shipId, "mark_modify", "1");

        Log.i(LOG_TAG + "queryShipImageOfModify", "query sql is " + sql);

        List<ShipImage> list = query(sql);

        return list;
    }
}

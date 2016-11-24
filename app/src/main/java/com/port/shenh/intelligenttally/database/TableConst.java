package com.port.shenh.intelligenttally.database;
/**
 * Created by sh on 2016/11/24.
 */

/**
 * 各表数据库常量
 *
 * @author sh
 * @version 1.0 2016/11/24
 * @since 1.0
 */
public interface TableConst {

    /**
     * 所有需要数据库初始化时创建的数据表的建表语句集合
     */
    String[] CREATE_TABLE_SQL_ARRAY = {CargoType.CREATE_TABLE_SQL};

    /**
     * 货物类别
     */
    interface CargoType {
        /**
         * 表名
         */
        String TABLE_NAME = "tb_ship_chart";

        /**
         * 建表语句
         */
        String CREATE_TABLE_SQL = String.format("CREATE TABLE IF NOT EXISTS %s ( %s INTEGER " +
                "PRIMARY KEY, %s TEXT)", TABLE_NAME, CommonConst._ID, CommonConst.NAME);
    }


}

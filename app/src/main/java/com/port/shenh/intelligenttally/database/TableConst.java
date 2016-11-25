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
    String[] CREATE_TABLE_SQL_ARRAY = {ShipImage.CREATE_TABLE_SQL};

    /**
     * 船图
     */
    interface ShipImage {
        /**
         * 表名
         */
        String TABLE_NAME = "tb_ship_chart";

        /**
         * 船图ID
         */
        String IMAGE_ID = "image_id";

        /**
         * 贝
         */
        String BAY_NUM = "bay_no";

        /**
         * 列
         */
        String BAY_COL = "bay_col";

        /**
         * 层
         */
        String BAY_ROW = "bay_row";

        /**
         * 箱号
         */
        String CONTAINER_NO = "container_no";

        /**
         * 尺寸
         */
        String SIZE_CON = "size_con";

        /**
         * 箱型
         */
        String CONTAINER_TYPE = "container_type";

        /**
         * 空/重
         */
        String CODE_EMPTY = "code_empty";

        /**
         * 重量
         */
        String WEIGHT = "weight";

        /**
         * 工作时间
         */
        String WORK_DATE = "work_date";

        /**
         * 铅封号
         */
        String SEALNO = "sealno";

        /**
         * 捣箱
         */
        String MOVED_NAME = "moved_name";

        /**
         * 内外贸
         */
        String INOUTMARK = "inoutmark";

        /**
         * 中转箱
         */
        String TRANSMARK = "transmark";

        /**
         * 节假日
         */
        String HOLIDAYS = "holidays";

        /**
         * 夜班
         */
        String NIGHT = "night";

        /**
         * 桥吊号
         */
        String CODE_CRANE = "code_crane";

        /**
         * 理货员
         */
        String NAME = "name";

        /**
         * 建表语句
         */
        String CREATE_TABLE_SQL = String.format("CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY, " +
                        "%s TEXT NOT NULL" +
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
                ShipImage.IMAGE_ID,
                ShipImage.BAY_NUM,
                ShipImage.BAY_COL,
                ShipImage.BAY_ROW,
                ShipImage.CONTAINER_NO,
                ShipImage.SIZE_CON,
                ShipImage.CONTAINER_TYPE,
                ShipImage.CODE_EMPTY,
                ShipImage.WEIGHT,
                ShipImage.WORK_DATE,
                ShipImage.SEALNO,
                ShipImage.MOVED_NAME,
                ShipImage.INOUTMARK,
                ShipImage.TRANSMARK,
                ShipImage.HOLIDAYS,
                ShipImage.NIGHT,
                ShipImage.CODE_CRANE,
                ShipImage.NAME);
    }
}

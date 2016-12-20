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
     * 船图
     */
    interface ShipImage {
        /**
         * 表名
         */
        String TABLE_NAME = "tb_ship_Image";

        /**
         * 航次编码
         */
        String SHIP_ID = "ship_id";

        /**
         * 船舶编码
         */
        String V_ID = "v_id";

        /**
         * 英文船名
         */
        String ENG_VESSEL = "eng_vessel";

        /**
         * 中文船名
         */
        String CHI_VESSEL = "chi_vessel";

        /**
         * 甲板/舱内
         */
        String LOCATION = "location";


        /**
         * 贝号
         */
        String BAY_NUM = "bay_num";

        /**
         * 贝列
         */
        String BAY_COL = "bay_col";

        /**
         * 贝层
         */
        String BAY_ROW = "bay_row";

        /**
         * 标准贝位
         */
        String SBAYNO = "sbayno";

        /**
         * 理论通贝
         */
        String TBAYNO = "tbayno";

        /**
         * 被通时贝位
         */
        String JBAYNO = "jbayno";

        /**
         * 用户标志
         */
        String USER_CHAR = "user_char";

        /**
         * 屏幕行
         */
        String SCREEN_ROW = "screen_row";

        /**
         * 屏幕列
         */
        String SCREEN_COL = "screen_col";

        /**
         * 通贝标志
         */
        String JOINT = "joint";

        /**
         * 装货港
         */
        String CODE_LOAD_PORT = "code_load_port";

        /**
         * 卸货港
         */
        String CODE_UNLOAD_PORT = "code_unload_port";

        /**
         * 交界地
         */
        String DELIVERY = "delivery";

        /**
         * 捣箱标志
         */
        String MOVED = "moved";

        /**
         * 卸箱标志
         */
        String UNLOAD_MARK = "unload_mark";

        /**
         * 理货员工号
         */
        String WORK_NO = "work_no";

        /**
         * 危险品等级
         */
        String DANGER_GRAGE = "danger_grade";

        /**
         * 设置温度
         */
        String DEGREE_SETTING = "degree_setting";

        /**
         * 温度单位
         */
        String DEGREE_UNIT = "degree_unit";

        /**
         * 最小温度
         */
        String MIN_DEGREE = "min_degree";

        /**
         * 最大温度
         */
        String MAX_DEGREE = "max_degree";

        /**
         * 贝位号
         */
        String BAYNO = "bayno";

        /**
         * 原贝
         */
        String OLDBAYNO = "oldbayno";

        /**
         * 桥吊号
         */
        String CODE_CRANE = "code_crane";

        /**
         * 船图ID
         */
        String IMAGE_ID = "image_id";

        /**
         * 贝
         */
        String BAYNUM = "bayum";

        /**
         * 列
         */
        String BAYCOL = "baycol";

        /**
         * 层
         */
        String BAYROW = "bayrow";

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
         * 理货员
         */
        String NAME = "name";
    }

    /**
     * Bay
     */
    interface Bay{

        /**
         * 屏幕甲板最大行数
         */
        String SUM_SCREEN_ROW_BOARD = "sum_screen_row_board";

        /**
         * 屏幕甲板最小行数
         */
        String MIN_SCREEN_ROW_BOARD = "min_screen_row_board";

        /**
         * 屏幕甲板最大列数
         */
        String SUM_SCREEN_COL_BOARD = "sum_screen_col_board";

        /**
         * 屏幕船舱最大行数
         */
        String SUM_SCREEN_ROW_CABIN = "sum_screen_row_cabin";

        /**
         * 屏幕船舱最大列数
         */
        String SUM_SCREEN_COL_CABIN = "sum_screen_col_cabin";
    }
}

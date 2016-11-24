package com.port.shenh.intelligenttally.database;
/**
 * Created by sh on 2016/11/24.
 */

/**
 * 通用数据库表常量
 *
 * @author sh
 * @version 1.0 2016/11/24
 * @since 1.0
 */
public interface CommonConst {

    /**
     * ID列
     */
    String _ID = "_id";

    /**
     * 数据库名
     */
    String DB_NAME = "tally_ship.db";

    /**
     * 数据库版本
     */
    int DB_VERSION = 1;

    /**
     * 编码列，一般作为功能主键
     */
    String CODE = "code";

    /**
     * 名称列
     */
    String NAME = "name";

    /**
     * 速记码列
     */
    String SHORT_CODE = "short_code";

    /**
     * 公司编码列
     */
    String COMPANY_CODE = "company_code";
}

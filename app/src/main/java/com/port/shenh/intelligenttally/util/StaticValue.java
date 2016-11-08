package com.port.shenh.intelligenttally.util;
/**
 * Created by sh on 2015/11/7.
 */

/**
 * 全局常量
 *
 * @author sh
 * @version 1.0 2016/11/7
 * @since 1.0
 */
public interface StaticValue {

    /**
     * 应用代码
     */
    String APP_CODE = "ZNLH";

    /**
     * 应用令牌
     */
    String APP_TOKEN = "3D24DC97C489B3EAE053A864016AB3EA";

    /**
     * 意图数据传递标签
     */
    interface IntentTag {

    }

    /**
     * 网络请求接口地址
     */
    interface Url {
        /**
         * IP
         */
        String HTTP_IP_URL = "http://218.92.115.55/";

        /**
         * 登录
         */
        String HTTP_LOGIN_URL = HTTP_IP_URL + "M_Znlh/Entrance/Login.aspx";
    }

}

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
        /**
         * 贝位号选择取值标签
         */
        String BAYNUM_SELECT_TAG = "baynum_select_tag";

        /**
         * 航次
         */
        String VOYAGE_TAG = "voyage_tag";

        /**
         * 最大贝号
         */
        String MAX_BAY_NUMBER_TAG = "max_bay_number_tag";
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

        /**
         * 获取航次（在港）
         */
        String HTTP_VOYAGE_LIST_OF_IN_PORT_URL = HTTP_IP_URL + "M_Znlh/Voyage/GetVoyageOfInPort.aspx";


        /**
         * 获取航次（已下载）
         */
        String HTTP_VOYAGE_LIST_OF_DOWNLOADED_URL = HTTP_IP_URL + "M_Znlh/Voyage/GetVoyage.aspx";

        /**
         * 获取船图数据（航次）
         */
        String HTTP_SHIP_IMAGE_URL = HTTP_IP_URL + "M_Znlh/Ship/GetShipImagesOfVoyage.aspx";

        /**
         * 获取船图数据（贝）
         */
        String HTTP_SHIP_IMAGE_OF_BAY_URL = HTTP_IP_URL + "M_Znlh/Ship/GetShipImagesOfBay.aspx";

        /**
         * 上传船图数据（贝）
         */
        String HTTP_UPLOAD_SHIP_IMAGE_URL = HTTP_IP_URL + "M_Znlh/Ship/UploadShipImages.aspx";
    }

}

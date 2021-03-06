package com.port.shenh.intelligenttally.util;
/**
 * Created by sh on 2015/11/7.
 */

import com.port.shenh.intelligenttally.function.HttpIpSet;

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
         * 航次ID
         */
        String VOYAGE_TAG = "voyage_tag";

        /**
         * 航船ID
         */
        String V_ID_TAG = "v_id_tag";

        /**
         * 最大贝号
         */
        String MAX_BAY_NUMBER_TAG = "max_bay_number_tag";

        /**
         * 进出口
         */
        String CODE_INOUT_TAG = "inout_tag";

        /**
         * 箱号
         */
        String CONTAINER_NO_TAG = "container_no_tag";
    }

    /**
     * 网络请求接口地址
     */
    interface Url {
        /**
         * IP
         */
        String HTTP_IP_URL = HttpIpSet.getHttp_ip();

        /**
         * 登录
         */
        String HTTP_LOGIN_URL = "M_Znlh/Entrance/Login.aspx";

        /**
         * 获取航次（在港）
         */
        String HTTP_VOYAGE_LIST_OF_IN_PORT_URL = "M_Znlh/Voyage/GetVoyageOfInPort" + ".aspx";


        /**
         * 获取航次（已下载）
         */
        String HTTP_VOYAGE_LIST_OF_DOWNLOADED_URL = "M_Znlh/Voyage/GetVoyage.aspx";

        /**
         * 获取船图数据（航次）
         */
        String HTTP_SHIP_IMAGE_URL = "M_Znlh/Ship/GetShipImagesOfVoyage.aspx";

        /**
         * 获取船图数据（贝）
         */
        String HTTP_SHIP_IMAGE_OF_BAY_URL = "M_Znlh/Ship/GetShipImagesOfBay.aspx";

        /**
         * 上传船图数据（贝）
         */
        String HTTP_UPLOAD_SHIP_IMAGE_URL = "M_Znlh/Ship/UploadShipImages.aspx";

        /**
         * 获取普通全统计数据
         */
        String HTTP_FULL_STATISTICS_URL = "M_Znlh/Statistics/GetFullStatisticsOfVoyage.aspx";

        /**
         * 获取捣箱全统计数据
         */
        String HTTP_MOVED_FULL_STATISTICS_URL = "M_Znlh/Statistics/GetMovedFullStatisticsOfVoyage" +
                ".aspx";


        /**
         * 获取普通个统计理货员数据
         */
        String HTTP_SINGLE_STATISTICS_TALLY_URL =
                "M_Znlh/Statistics/GetSingleStatisticsTallyOfVoyage.aspx";

        /**
         * 获取普通个统计操作员数据
         */
        String HTTP_SINGLE_STATISTICS_OPERATE_URL =
                "M_Znlh/Statistics/GetSingleStatisticsOperateOfVoyage.aspx";

        /**
         * 获取捣箱个统计理货员数据
         */
        String HTTP_MOVED_SINGLE_STATISTICS_TALLY_URL =
                "M_Znlh/Statistics/GetMovedSingleStatisticsTallyOfVoyage.aspx";

        /**
         * 获取捣箱个统计操作员数据
         */
        String HTTP_MOVED_SINGLE_STATISTICS_OPERATE_URL =
                "M_Znlh/Statistics/GetMoveSingleStatisticsOperateOfVoyage.aspx";


        /**
         * 通过关键字获取匹配的集装箱列表
         */
        String HTTP_CONTAINER_NO_LIST_URL = "M_Znlh/Container/GetContainerNoListByKeyword.aspx";

        /**
         * 集装箱信息
         */
        String HTTP_CONTAINER_INFO_URL = "M_Znlh/Container/GetContainerInfo.aspx";


        /**
         * 移贝
         */
        String HTTP_MOVE_BAY_URL = "M_Znlh/Move/MoveBay.aspx";

        /**
         * 双吊
         */
        String HTTP_DOUBLE_LIFT_URL = "M_Znlh/Move/DoubleLift.aspx";

    }

}

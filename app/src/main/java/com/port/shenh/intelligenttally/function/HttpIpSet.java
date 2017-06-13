package com.port.shenh.intelligenttally.function;

import android.util.Log;

/**
 * 访问Ip设置
 *
 * @author shenh
 * @version 1.0 2017/6/6
 * @since 1.0 2017/6/6
 */
public class HttpIpSet {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "HttpIpSet.";

    /**
     * 4G IP
     */
    final static private String http_ip_extranet = "http://www.boea.cn/";

    /**
     * 4G IP
     */
    final static private String http_ip_intranet = "http://172.17.2.31/";

    private static String http_ip = http_ip_extranet;

    static public String getHttp_ip() {


        Log.i(LOG_TAG + "getHttp_ip", "getHttp_ip is " + http_ip);


        return http_ip;
    }

    static public void setHttp_ip(int ipIndex) {

        switch (ipIndex){
            case 0:
                http_ip = http_ip_extranet;
                break;
            case 1:
                http_ip = http_ip_intranet;
                break;
            default:
                http_ip = http_ip_extranet;
                break;
        }
    }




}

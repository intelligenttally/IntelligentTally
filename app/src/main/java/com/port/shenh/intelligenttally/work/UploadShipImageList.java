package com.port.shenh.intelligenttally.work;


import android.util.Log;

import com.google.gson.Gson;
import com.port.shenh.intelligenttally.bean.ShipImage;
import com.port.shenh.intelligenttally.function.HttpIpSet;
import com.port.shenh.intelligenttally.util.StaticValue;

import org.json.JSONObject;
import org.mobile.library.model.work.SimpleWorkModel;
import org.mobile.library.network.factory.NetworkType;

import java.util.List;
import java.util.Map;

/**
 * Created by shenh on 2017/1/19.
 */


/**
 * 上传船图列表
 *
 * @author sh
 * @version 1.0 2016/10/13
 * @since 1.0
 */

public class UploadShipImageList extends SimpleWorkModel<Object, Void> {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "UploadShipImageList";


    @Override
    protected void onFill(Map<String, String> dataMap, Object... parameters) {

        List<ShipImage> list = (List<ShipImage>) parameters[0];

        Gson gson = new Gson();
        dataMap.put("ShipImageList", gson.toJson(list));

        Log.i(LOG_TAG + " onFillRequestParameters", " List<ShipImage> count is " + list.size());
        Log.i(LOG_TAG + " onFillRequestParameters", " ShipImageList is " +  gson.toJson(list));

    }

    @Override
    protected Void onSuccessExtract(JSONObject jsonResult) throws Exception {
        return null;
    }

    @Override
    protected NetworkType onNetworkType() {
        return NetworkType.POST;
    }

    @Override
    protected String onTaskUri() {
        return HttpIpSet.getHttp_ip() + StaticValue.Url.HTTP_UPLOAD_SHIP_IMAGE_URL;
    }


}

package com.port.shenh.intelligenttally.work;

import android.util.Log;

import com.port.shenh.intelligenttally.function.HttpIpSet;
import com.port.shenh.intelligenttally.util.StaticValue;

import org.json.JSONObject;
import org.mobile.library.model.work.SimpleWorkModel;

import java.util.Map;

/**
 * 移贝提交
 *
 * @author shenh
 * @version 1.0 2017/6/24
 * @since 1.0 2017/6/24
 */
public class MoveBay extends SimpleWorkModel<String, Void>{

    /**
     * 日志标签前缀
     */
    private  static final String LOG_TAG = "MoveBay.";

    @Override
    protected void onFill(Map<String, String> dataMap, String... parameters) {
        dataMap.put("ShipId", parameters[0]);
        dataMap.put("V_Id", parameters[1]);
        dataMap.put("ContainerNo", parameters[2]);
        dataMap.put("EndBayno", parameters[3]);
        dataMap.put("UserId", parameters[4]);
        Log.i(LOG_TAG + "onFillRequestParameters", " shipId is " + parameters[0]);
        Log.i(LOG_TAG + "onFillRequestParameters", " v_Id is " + parameters[1]);
        Log.i(LOG_TAG + "onFillRequestParameters", " containerNo is " + parameters[2]);
        Log.i(LOG_TAG + "onFillRequestParameters", " endBayno is " + parameters[3]);
        Log.i(LOG_TAG + "onFillRequestParameters", " UserId is " + parameters[4]);

    }

    @Override
    protected Void onSuccessExtract(JSONObject jsonResult) throws Exception {

        return null;
    }

    @Override
    protected String onTaskUri() {
        return HttpIpSet.getHttp_ip() + StaticValue.Url.HTTP_MOVE_BAY_URL;
    }
}

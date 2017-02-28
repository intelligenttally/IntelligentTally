package com.port.shenh.intelligenttally.work;


import android.util.Log;

import com.port.shenh.intelligenttally.util.StaticValue;
import org.json.JSONObject;
import org.mobile.library.model.work.SimpleWorkModel;

import java.util.Map;

/**
 * Created by shenh on 2017/1/19.
 */

public class UploadShipImageList extends SimpleWorkModel<String, String> {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "UploadShipImageList";


    @Override
    protected void onFill(Map<String, String> dataMap, String... parameters) {

        dataMap.put("ShipImageList", parameters[0]);
        Log.i(LOG_TAG + " onFillRequestParameters", " ShipImageList is " + parameters[0]);

    }

    @Override
    protected String onSuccessExtract(JSONObject jsonResult) throws Exception {
        return null;
    }

    @Override
    protected String onTaskUri() {
        return StaticValue.Url.HTTP_UPLOAD_SHIP_IMAGE_URL;
    }
}

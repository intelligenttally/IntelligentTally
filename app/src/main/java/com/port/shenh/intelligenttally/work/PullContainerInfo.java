package com.port.shenh.intelligenttally.work;

import android.util.Log;

import com.port.shenh.intelligenttally.bean.ShipImage;
import com.port.shenh.intelligenttally.function.HttpIpSet;
import com.port.shenh.intelligenttally.util.StaticValue;

import org.json.JSONArray;
import org.json.JSONObject;
import org.mobile.library.model.work.SimpleWorkModel;

import java.util.Map;

/**
 * 通过集装箱信息
 *
 * @author shenh
 * @version 1.0 2017/6/20
 * @since 1.0 2017/7/20
 */
public class PullContainerInfo extends SimpleWorkModel<String, ShipImage> {

    /**
     * 日志标签前缀
     */
    private  static final String LOG_TAG = "PullContainerNoList.";

    @Override
    protected void onFill(Map<String, String> dataMap, String... parameters) {
        dataMap.put("Ship_Id", parameters[0]);
        dataMap.put("ContainerNo", parameters[1]);
        Log.i(LOG_TAG + "onFillRequestParameters", " ship_Id is " + parameters[0]);
        Log.i(LOG_TAG + "onFillRequestParameters", " containerNo is " + parameters[1]);

    }

    @Override
    protected ShipImage onSuccessExtract(JSONObject jsonResult) throws Exception {

        //数据源
        JSONArray jsonArray = jsonResult.getJSONArray(DATA_TAG);
        Log.i(LOG_TAG + "onSuccessExtract", " get containerNo count is " + jsonArray.length());

        ShipImage shipImage = new ShipImage();
        shipImage.setBayno(jsonArray.getString(0));
        shipImage.setSealno(jsonArray.getString(1));
        shipImage.setMoved(jsonArray.getString(2));
        shipImage.setCode_unload_port(jsonArray.getString(3));
        shipImage.setSize_con(jsonArray.getString(4));
        shipImage.setContainer_type(jsonArray.getString(5));
        shipImage.setCode_empty(jsonArray.getString(6));
        shipImage.setUnload_mark(jsonArray.getString(7));


        return shipImage;
    }

    @Override
    protected String onTaskUri() {

        return HttpIpSet.getHttp_ip() + StaticValue.Url.HTTP_CONTAINER_INFO_URL;
    }


}

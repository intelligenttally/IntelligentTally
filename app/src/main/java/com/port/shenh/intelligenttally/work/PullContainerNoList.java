package com.port.shenh.intelligenttally.work;

import android.util.Log;

import com.port.shenh.intelligenttally.function.HttpIpSet;
import com.port.shenh.intelligenttally.util.StaticValue;

import org.json.JSONArray;
import org.json.JSONObject;
import org.mobile.library.model.work.SimpleWorkModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 通过关键字获取匹配的集装箱号列表
 *
 * @author shenh
 * @version 1.0 2017/6/20
 * @since 1.0 2017/7/20
 */
public class PullContainerNoList extends SimpleWorkModel<String, List<String>> {

    /**
     * 日志标签前缀
     */
    private  static final String LOG_TAG = "PullContainerNoList";

    @Override
    protected void onFill(Map<String, String> dataMap, String... parameters) {
        dataMap.put("Ship_Id", parameters[0]);
        dataMap.put("Keyword", parameters[1]);
        Log.i(LOG_TAG + "onFillRequestParameters", " ship_Id is " + parameters[0]);
        Log.i(LOG_TAG + "onFillRequestParameters", " keyword is " + parameters[1]);

    }

    @Override
    protected List<String> onSuccessExtract(JSONObject jsonResult) throws Exception {

        //数据源
        JSONArray jsonArray = jsonResult.getJSONArray(DATA_TAG);
        Log.i(LOG_TAG + "onSuccessExtract", " get containerNo count is " + jsonArray.length());

        List<String> list = new ArrayList<String>();
        for (int i=0;i<jsonArray.length();i++){
            list.add(jsonArray.getString(i));
        }

        return list;
    }

    @Override
    protected String onTaskUri() {

        return HttpIpSet.getHttp_ip() + StaticValue.Url.HTTP_CONTAINER_NO_LIST_URL;
    }


}

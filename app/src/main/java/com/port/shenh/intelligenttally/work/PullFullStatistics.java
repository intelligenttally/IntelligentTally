package com.port.shenh.intelligenttally.work;

import android.util.Log;

import com.port.shenh.intelligenttally.bean.FullStatistics;
import com.port.shenh.intelligenttally.function.HttpIpSet;
import com.port.shenh.intelligenttally.util.StaticValue;

import org.json.JSONArray;
import org.json.JSONObject;
import org.mobile.library.model.work.SimpleWorkModel;

import java.util.Map;

/**
 * 获取全统计数据
 *
 * @author shenh
 * @version 1.0 2017/3/21
 * @since 1.0 2017/3/21
 */
public class PullFullStatistics extends SimpleWorkModel<String, FullStatistics> {

    /**
     * 日志标签前缀
     */
    private  static final String LOG_TAG = "PullFullStatistics";

    @Override
    protected void onFill(Map<String, String> dataMap, String... parameters) {
        dataMap.put("Ship_Id", parameters[0]);
        dataMap.put("CodeInout", parameters[1]);
        Log.i(LOG_TAG + "onFillRequestParameters", " Ship_Id is " + parameters[0]);
        Log.i(LOG_TAG + "onFillRequestParameters", " CodeInout is " + parameters[1]);

    }

    @Override
    protected FullStatistics onSuccessExtract(JSONObject jsonResult) throws Exception {

        //数据源
        JSONArray jsonArray = jsonResult.getJSONArray(DATA_TAG);
        Log.i(LOG_TAG + "onSuccessExtract", " get fullStatistics count is " + jsonArray.length());

        FullStatistics fullStatistics = new FullStatistics();

        fullStatistics.setForecast_total(jsonArray.getInt(0));
        fullStatistics.setForecast_E_20(jsonArray.getInt(1));
        fullStatistics.setForecast_E_40(jsonArray.getInt(2));
        fullStatistics.setForecast_E_other(jsonArray.getInt(3));
        fullStatistics.setForecast_E_total(jsonArray.getInt(4));
        fullStatistics.setForecast_F_20(jsonArray.getInt(5));
        fullStatistics.setForecast_F_40(jsonArray.getInt(6));
        fullStatistics.setForecast_F_other(jsonArray.getInt(7));
        fullStatistics.setForecast_F_total(jsonArray.getInt(8));

        fullStatistics.setTally_total(jsonArray.getInt(9));
        fullStatistics.setTally_E_20(jsonArray.getInt(10));
        fullStatistics.setTally_E_40(jsonArray.getInt(11));
        fullStatistics.setTally_E_other(jsonArray.getInt(12));
        fullStatistics.setTally_E_total(jsonArray.getInt(13));
        fullStatistics.setTally_F_20(jsonArray.getInt(14));
        fullStatistics.setTally_F_40(jsonArray.getInt(15));
        fullStatistics.setTally_F_other(jsonArray.getInt(16));
        fullStatistics.setTally_F_total(jsonArray.getInt(17));


        fullStatistics.setAbnormal_total(jsonArray.getInt(18));
        fullStatistics.setAbnormal_E_20(jsonArray.getInt(19));
        fullStatistics.setAbnormal_E_40(jsonArray.getInt(20));
        fullStatistics.setAbnormal_E_other(jsonArray.getInt(21));
        fullStatistics.setAbnormal_E_total(jsonArray.getInt(22));
        fullStatistics.setAbnormal_F_20(jsonArray.getInt(23));
        fullStatistics.setAbnormal_F_40(jsonArray.getInt(24));
        fullStatistics.setAbnormal_F_other(jsonArray.getInt(25));
        fullStatistics.setAbnormal_F_total(jsonArray.getInt(26));



        return fullStatistics;
    }

    @Override
    protected String onTaskUri() {

        return HttpIpSet.getHttp_ip() + StaticValue.Url.HTTP_FULL_STATISTICS_URL;
    }
}

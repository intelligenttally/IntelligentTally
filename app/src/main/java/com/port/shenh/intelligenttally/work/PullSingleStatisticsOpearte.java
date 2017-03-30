package com.port.shenh.intelligenttally.work;

import android.util.Log;

import com.port.shenh.intelligenttally.bean.SingleStatistics;
import com.port.shenh.intelligenttally.util.StaticValue;

import org.json.JSONArray;
import org.json.JSONObject;
import org.mobile.library.model.work.SimpleWorkModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 获取操作员统计数据
 *
 * @author shenh
 * @version 1.0 2017/3/21
 * @since 1.0 2017/3/21
 */
public class PullSingleStatisticsOpearte extends SimpleWorkModel<String, List<SingleStatistics>> {

    /**
     * 日志标签前缀
     */
    private  static final String LOG_TAG = "PullSingleStatisticsOpearte.";

    /**
     * 航次列表
     */
    private List<SingleStatistics> singleStatisticsList = null;

    @Override
    protected void onFill(Map<String, String> dataMap, String... parameters) {
        dataMap.put("StartRow", parameters[0]);
        Log.i(LOG_TAG + " onFillRequestParameters", " StartRow is " + parameters[0]);
        dataMap.put("Count", parameters[1]);
        Log.i(LOG_TAG + " onFillRequestParameters", " Count is " + parameters[1]);
        dataMap.put("Ship_Id", parameters[2]);
        Log.i(LOG_TAG + "onFillRequestParameters", " Ship_Id is " + parameters[2]);
        dataMap.put("CodeInout", parameters[3]);
        Log.i(LOG_TAG + "onFillRequestParameters", " CodeInout is " + parameters[3]);

    }

    @Override
    protected List<SingleStatistics> onSuccessExtract(JSONObject jsonResult) throws Exception {

        // 数据源
        JSONArray jsonArray = jsonResult.getJSONArray(DATA_TAG);
        Log.i(LOG_TAG + "onSuccessExtract", " get singleStatisticsList count is " + jsonArray.length());

        // 新建航次列表
        singleStatisticsList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONArray jsonRow = jsonArray.getJSONArray(i);
            Log.i(LOG_TAG + "onSuccessExtract", " singleStatisticsList jsonRow length() is " + jsonRow.length());

            if (jsonRow.length() > 6) {
                // 一条航次数据
                SingleStatistics singleStatistics = new SingleStatistics();

                singleStatistics.setName(jsonRow.getInt(0));
                singleStatistics.setE_20(jsonRow.getInt(1));
                singleStatistics.setF_20(jsonRow.getInt(2));
                singleStatistics.setE_40(jsonRow.getInt(3));
                singleStatistics.setF_40(jsonRow.getInt(4));
                singleStatistics.setE_other(jsonRow.getInt(5));
                singleStatistics.setF_other(jsonRow.getInt(6));



                // 添加到列表
                singleStatisticsList.add(singleStatistics);
            }
        }

        Log.i(LOG_TAG + "onSuccessExtract", " singleStatistics list count is " + singleStatisticsList.size());

        return singleStatisticsList;
    }

    @Override
    protected String onTaskUri() {

        return StaticValue.Url.HTTP_SINGLE_STATISTICS_OPERATE_URL;
    }
}

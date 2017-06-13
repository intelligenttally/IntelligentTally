package com.port.shenh.intelligenttally.work;

import android.util.Log;
import com.port.shenh.intelligenttally.bean.SingleStatistics;
import com.port.shenh.intelligenttally.function.HttpIpSet;
import com.port.shenh.intelligenttally.util.StaticValue;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mobile.library.model.work.SimpleWorkModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 获取理货员统计数据
 *
 * @author shenh
 * @version 1.0 2017/3/21
 * @since 1.0 2017/3/21
 */
public class PullSingleStatisticsTally extends SimpleWorkModel<String, List<SingleStatistics>> {

    /**
     * 日志标签前缀
     */
    private  static final String LOG_TAG = "PullSingleStatisticsTally.";

    /**
     * 航次列表
     */
    private List<SingleStatistics> singleStatisticsList = null;

    @Override
    protected void onFill(Map<String, String> dataMap, String... parameters) {
        dataMap.put("Ship_Id", parameters[0]);
        Log.i(LOG_TAG + "onFillRequestParameters", " Ship_Id is " + parameters[0]);
        dataMap.put("CodeInout", parameters[1]);
        Log.i(LOG_TAG + "onFillRequestParameters", " CodeInout is " + parameters[1]);

    }

    @Override
    protected List<SingleStatistics> onSuccessExtract(JSONObject jsonResult) throws Exception {

        // 数据源
        JSONArray jsonArray = jsonResult.getJSONArray(DATA_TAG);
        Log.i(LOG_TAG + "onSuccessExtract", " get singleStatisticsList count is " + jsonArray.length());

        // 新建航次列表
        singleStatisticsList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Log.i(LOG_TAG + "onSuccessExtract", " singleStatisticsList jsonObject length() is " + jsonObject.length());

            if (jsonObject.length() > 6) {
                // 一条航次数据
                SingleStatistics singleStatistics = new SingleStatistics();

                singleStatistics.setName(jsonObject.getString("Name"));
                singleStatistics.setE_20(jsonObject.getInt("E_20"));
                singleStatistics.setF_20(jsonObject.getInt("F_20"));
                singleStatistics.setE_40(jsonObject.getInt("E_40"));
                singleStatistics.setF_40(jsonObject.getInt("F_40"));
                singleStatistics.setE_other(jsonObject.getInt("E_other"));
                singleStatistics.setF_other(jsonObject.getInt("F_other"));



                // 添加到列表
                singleStatisticsList.add(singleStatistics);
            }
        }

        Log.i(LOG_TAG + "onSuccessExtract", " singleStatistics list count is " + singleStatisticsList.size());

        return singleStatisticsList;
    }

    @Override
    protected String onTaskUri() {

        return HttpIpSet.getHttp_ip() + StaticValue.Url.HTTP_SINGLE_STATISTICS_TALLY_URL;
    }
}

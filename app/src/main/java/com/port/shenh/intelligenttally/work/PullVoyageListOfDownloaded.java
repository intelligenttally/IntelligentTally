package com.port.shenh.intelligenttally.work;
/**
 * Created by sh on 2016/11/15.
 */

import android.util.Log;

import com.port.shenh.intelligenttally.bean.Voyage;
import com.port.shenh.intelligenttally.function.HttpIpSet;
import com.port.shenh.intelligenttally.util.StaticValue;

import org.json.JSONArray;
import org.json.JSONObject;
import org.mobile.library.model.work.SimpleWorkModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 获取航次列表
 *
 * @author sh
 * @version 1.0 2016/10/13
 * @since 1.0
 */
public class PullVoyageListOfDownloaded extends SimpleWorkModel<String, List<Voyage>> {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "PullVoyageListOfDownloaded";

    /**
     * 航次列表
     */
    private List<Voyage> voyageList = null;

    @Override
    protected void onFill(Map<String, String> dataMap, String... parameters) {
        dataMap.put("Ship_Id", parameters[0]);
        Log.i(LOG_TAG + " onFillRequestParameters", " Ship_Id is " + parameters[0]);
    }

    @Override
    protected List<Voyage> onSuccessExtract(JSONObject jsonResult) throws Exception {
        // 数据源
        JSONArray jsonArray = jsonResult.getJSONArray(DATA_TAG);
        Log.i(LOG_TAG + "onSuccessExtract", " get voyageList count is " + jsonArray.length());

        // 新建航次列表
        voyageList = new ArrayList<>();

        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        String date = sDateFormat.format(new java.util.Date());

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONArray jsonRow = jsonArray.getJSONArray(i);
            Log.i(LOG_TAG + "onSuccessExtract", " voyage jsonRow length() is " + jsonRow.length());

            if (jsonRow.length() > 6) {
                // 一条航次数据
                Voyage voyage = new Voyage();

                voyage.setShip_id(jsonRow.getString(0));
                voyage.setV_id(jsonRow.getString(1));
                voyage.setBerthno(jsonRow.getString(2));
                voyage.setVoyage(jsonRow.getString(3));
                voyage.setChi_vessel(jsonRow.getString(4));
                voyage.setCodeInOut(jsonRow.getString(5));
                voyage.setTrade(jsonRow.getString(6));
                voyage.setWheel(jsonRow.getString(7));
                voyage.setDownloadTime(date);

                // 添加到列表
                voyageList.add(voyage);
            }
        }

        Log.i(LOG_TAG + "onSuccessExtract", " voyage list count is " + voyageList.size());

        return voyageList;
    }

    @Override
    protected String onTaskUri() {
        return HttpIpSet.getHttp_ip() + StaticValue.Url.HTTP_VOYAGE_LIST_OF_DOWNLOADED_URL;
    }
}



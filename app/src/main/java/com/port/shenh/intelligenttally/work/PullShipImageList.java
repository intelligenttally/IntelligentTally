package com.port.shenh.intelligenttally.work;
/**
 * Created by sh on 2016/11/15.
 */

import android.util.Log;

import com.port.shenh.intelligenttally.bean.ShipImage;
import com.port.shenh.intelligenttally.bean.Voyage;
import com.port.shenh.intelligenttally.database.TableConst;
import com.port.shenh.intelligenttally.util.StaticValue;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mobile.library.model.work.SimpleWorkModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 获取船图列表
 *
 * @author sh
 * @version 1.0 2016/10/13
 * @since 1.0
 */
public class PullShipImageList extends SimpleWorkModel<String, List<ShipImage>> {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "PullShipImageList";

    /**
     * 航次列表
     */
    private List<ShipImage> shipImageList = null;

    @Override
    protected void onFill(Map<String, String> dataMap, String... parameters) {
        dataMap.put("Ship_Id", parameters[0]);
        Log.i(LOG_TAG + " onFillRequestParameters", " Ship_Id is " + parameters[0]);
    }

    @Override
    protected List<ShipImage> onSuccessExtract(JSONObject jsonResult) throws Exception {
        // 数据源
        JSONArray jsonArray = jsonResult.getJSONArray(DATA_TAG);
        Log.i(LOG_TAG + "onSuccessExtract", " get voyageList count is " + jsonArray.length());

        // 新建航次列表
        shipImageList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONArray jsonRow = jsonArray.getJSONArray(i);
            Log.i(LOG_TAG + "onSuccessExtract", " voyage jsonRow length() is " + jsonRow.length());

            if (jsonRow.length() > 17) {
                // 一条航次数据
                ShipImage shipImage = new ShipImage();

                shipImage.setIMAGE_ID(jsonRow.getString(0));
                shipImage.setBay_no(jsonRow.getString(1));
                shipImage.setBay_col(jsonRow.getString(2));
                shipImage.setBay_row(jsonRow.getString(3));
                shipImage.setContainer_no(jsonRow.getString(4));
                shipImage.setSize_con(jsonRow.getString(5));
                shipImage.setContainer_type(jsonRow.getString(6));
                shipImage.setCode_empty(jsonRow.getString(7));
                shipImage.setWeight(jsonRow.getString(8));
                shipImage.setWork_date(jsonRow.getString(9));
                shipImage.setSealno(jsonRow.getString(10));
                shipImage.setMoved_name(jsonRow.getString(11));
                shipImage.setInoutmark(jsonRow.getString(12));
                shipImage.setTransmark(jsonRow.getString(13));
                shipImage.setHolidays(jsonRow.getString(14));
                shipImage.setNight(jsonRow.getString(15));
                shipImage.setCode_crane(jsonRow.getString(16));
                shipImage.setNight(jsonRow.getString(17));

                // 添加到列表
                shipImageList.add(shipImage);
            }
        }

        Log.i(LOG_TAG + "onSuccessExtract", " voyage list count is " + shipImageList.size());

        return shipImageList;
    }

    @Override
    protected String onTaskUri() {
        return StaticValue.Url.HTTP_SHIP_IMAGE_LIST_URL;
    }
}



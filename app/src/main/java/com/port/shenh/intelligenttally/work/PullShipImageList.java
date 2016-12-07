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

                shipImage.setShip_id(jsonRow.getString(0));
                shipImage.setV_id(jsonRow.getString(1));
                shipImage.setEng_vessel(jsonRow.getString(2));
                shipImage.setChi_vessel(jsonRow.getString(3));
                shipImage.setLocation(jsonRow.getString(4));
                shipImage.setBay_num(jsonRow.getString(5));
                shipImage.setBay_col(jsonRow.getString(6));
                shipImage.setBay_row(jsonRow.getString(7));
                shipImage.setSbayno(jsonRow.getString(8));
                shipImage.setTbayno(jsonRow.getString(9));
                shipImage.setJbayno(jsonRow.getString(10));
                shipImage.setUser_char(jsonRow.getString(11));
                shipImage.setScreen_row(jsonRow.getString(12));
                shipImage.setScreen_col(jsonRow.getString(13));
                shipImage.setJoint(jsonRow.getString(14));
                shipImage.setCode_load_port(jsonRow.getString(15));
                shipImage.setCode_unload_port(jsonRow.getString(16));
                shipImage.setDelivery(jsonRow.getString(17));
                shipImage.setMoved(jsonRow.getString(18));
                shipImage.setUnload_mark(jsonRow.getString(19));
                shipImage.setWork_no(jsonRow.getString(20));
                shipImage.setDanger_grade(jsonRow.getString(21));
                shipImage.setDegree_setting(jsonRow.getString(22));
                shipImage.setDegree_unit(jsonRow.getString(23));
                shipImage.setMin_degree(jsonRow.getString(24));
                shipImage.setMax_degree(jsonRow.getString(25));
                shipImage.setBayno(jsonRow.getString(26));
                shipImage.setOldbayno(jsonRow.getString(27));
                shipImage.setCode_crane(jsonRow.getString(28));

                shipImage.setImage_id(jsonRow.getString(29));
                shipImage.setBaynum(jsonRow.getString(30));
                shipImage.setBaycol(jsonRow.getString(31));
                shipImage.setBayrow(jsonRow.getString(32));
                shipImage.setContainer_no(jsonRow.getString(33));
                shipImage.setSize_con(jsonRow.getString(34));
                shipImage.setContainer_type(jsonRow.getString(35));
                shipImage.setCode_empty(jsonRow.getString(36));
                shipImage.setWeight(jsonRow.getString(37));
                shipImage.setWork_date(jsonRow.getString(38));
                shipImage.setSealno(jsonRow.getString(39));
                shipImage.setMoved_name(jsonRow.getString(40));
                shipImage.setInoutmark(jsonRow.getString(41));
                shipImage.setTransmark(jsonRow.getString(42));
                shipImage.setHolidays(jsonRow.getString(43));
                shipImage.setNight(jsonRow.getString(44));
                shipImage.setName(jsonRow.getString(45));

                // 添加到列表
                shipImageList.add(shipImage);
            }
        }

        Log.i(LOG_TAG + "onSuccessExtract", " voyage list count is " + shipImageList.size());

        return shipImageList;
    }

    @Override
    protected String onTaskUri() {
        return StaticValue.Url.HTTP_SHIP_IMAGE_URL;
    }
}



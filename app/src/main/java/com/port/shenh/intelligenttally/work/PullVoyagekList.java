package com.port.shenh.intelligenttally.work;
/**
 * Created by sh on 2016/11/15.
 */

import com.port.shenh.intelligenttally.bean.Voyage;
import com.port.tally.management.bean.Stock;
import com.port.tally.management.data.StockListData;
import com.port.tally.management.util.StaticValue;

import org.mobile.library.model.work.DefaultWorkModel;
import org.mobile.library.model.work.SimpleWorkModel;

import java.util.List;

/**
 * 获取航次列表
 *
 * @author sh
 * @version 1.0 2016/10/13
 * @since 1.0
 */
public class PullVoyagekList extends SimpleWorkModel<String, List<Voyage>, VoyageListData> {


}

//
//    @Override
//    protected boolean onCheckParameters(String... parameters) {
//        return parameters != null && parameters.length >= 3;
//    }
//
//    @Override
//    protected String onTaskUri() {
//        return StaticValue.STOCK_LIST_URL;
//    }
//
//    @Override
//    protected List<Stock> onRequestSuccessSetResult(StockListData data) {
//        return data.getStockList();
//    }
//
//    @Override
//    protected List<Stock> onRequestFailedSetResult(StockListData data) {
//        return null;
//    }
//
//    @Override
//    protected StockListData onCreateDataModel(String... parameters) {
//        // 新建数据模型
//        StockListData data = new StockListData();
//
//        // 设置参数
//        data.setCompany(parameters[0]);
//        data.setStartRow(parameters[1]);
//        data.setCountRow(parameters[2]);
//        data.setCargo(parameters.length > 3 ? parameters[3] : null);
//        data.setCargoOwner(parameters.length > 4 ? parameters[4] : null);
//        data.setForwarder(parameters.length > 5 ? parameters[5] : null);
//        data.setStorage(parameters.length > 6 ? parameters[6] : null);
//
//        return data;
//    }

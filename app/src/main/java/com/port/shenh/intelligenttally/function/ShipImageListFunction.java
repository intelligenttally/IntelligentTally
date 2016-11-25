package com.port.shenh.intelligenttally.function;
/**
 * Created by sh on 2016/11/25.
 */

import android.content.Context;

import com.port.shenh.intelligenttally.bean.ShipImage;
import com.port.shenh.intelligenttally.database.ShipImageOperator;
import com.port.shenh.intelligenttally.work.PullShipImageList;
import org.mobile.library.model.database.BaseOperator;

/**
 * 船图数据列表工具
 *
 * @author sh
 * @version 1.0 2016/11/25
 * @since 1.0
 */
public class ShipImageListFunction extends BaseDataListFunction<ShipImage,String> {

    public ShipImageListFunction(Context context) {
        super(context);
    }

    @Override
    protected BaseOperator<ShipImage> onCreateOperator(Context context) {
        return new ShipImageOperator(context);
    }

    @Override
    protected void onLoadFromNetWork(String parameter) {
        PullShipImageList workModel = new PullShipImageList();

        boolean state = workModel.execute(parameter);

        netWorkEndSetData(state, workModel.getResult());
    }

    @Override
    protected void onNotify(Context context) {
//        BroadcastUtil.sendBroadcast(context, StaticValue.DataListTag.CARGO_TYPE_LIST);
    }
}

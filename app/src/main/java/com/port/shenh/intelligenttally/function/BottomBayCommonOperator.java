package com.port.shenh.intelligenttally.function;
/**
 * Created by 超悟空 on 2017/1/3.
 */

import com.port.shenh.intelligenttally.adapter.BayGridAdapter;
import com.port.shenh.intelligenttally.bean.ShipImage;

/**
 * 贝图底部操作栏的常规操作接口
 *
 * @author 超悟空
 * @version 1.0 2017/1/3
 * @since 1.0
 */
public interface BottomBayCommonOperator {

    /**
     * 点击了一个有效贝位
     *
     * @param holder 贝位控件管理器
     * @param data   船图数据
     */
    void onBayClick(BayGridAdapter.ViewHolder holder, ShipImage data);

    /**
     * 切换贝图
     */
    void onBaySwitch();
}

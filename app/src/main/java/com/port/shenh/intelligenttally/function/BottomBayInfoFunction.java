package com.port.shenh.intelligenttally.function;
/**
 * Created by 超悟空 on 2016/12/28.
 */

import android.view.View;
import android.widget.TextView;

import com.port.shenh.intelligenttally.R;
import com.port.shenh.intelligenttally.bean.ShipImage;

/**
 * 用于填充和处理贝图底部信息布局的工具
 *
 * @author 超悟空
 * @version 1.0 2016/12/28
 * @since 1.0
 */
public class BottomBayInfoFunction {

    /**
     * 当前视图填充的贝位数据
     */
    private ShipImage data = null;

    /**
     * 贝位
     */
    private TextView bayNumber = null;

    /**
     * 箱号
     */
    private TextView boxNumber = null;

    /**
     * 捣箱
     */
    private TextView inverted = null;

    /**
     * 重量
     */
    private TextView weight = null;

    /**
     * 空重
     */
    private TextView empty = null;

    /**
     * 尺寸
     */
    private TextView size = null;

    /**
     * 箱型
     */
    private TextView type = null;

    /**
     * 构造函数
     *
     * @param rootView 包含信息布局的根布局
     */
    public BottomBayInfoFunction(View rootView) {
        initView(rootView);
    }

    /**
     * 初始化布局
     *
     * @param rootView 包含信息布局的根布局
     */
    private void initView(View rootView) {
        bayNumber = (TextView) rootView.findViewById(R.id.layout_bottom_sheet_info_bay_textView);
        boxNumber = (TextView) rootView.findViewById(R.id
                .layout_bottom_sheet_info_box_number_textView);
        inverted = (TextView) rootView.findViewById(R.id
                .layout_bottom_sheet_info_inverted_textView);
        weight = (TextView) rootView.findViewById(R.id.layout_bottom_sheet_info_weight_textView);
        empty = (TextView) rootView.findViewById(R.id.layout_bottom_sheet_info_empty_textView);
        size = (TextView) rootView.findViewById(R.id.layout_bottom_sheet_info_size_textView);
        type = (TextView) rootView.findViewById(R.id.layout_bottom_sheet_info_type_textView);
    }

    /**
     * 绑定数据
     *
     * @param data 贝位数据
     */
    public void bindData(ShipImage data) {
        this.data = data;
        onBind();
    }

    /**
     * 执行数据绑定
     */
    private void onBind() {
        if (data == null) {
            return;
        }

        bayNumber.setText(data.getBayno());
        boxNumber.setText(data.getContainer_no());
        inverted.setText(data.getMoved_name());
        weight.setText(data.getWeight());
        empty.setText(data.getCode_empty());
        size.setText(data.getSize_con());
        type.setText(data.getContainer_type());
    }

    /**
     * 获取当前绑定的数据
     *
     * @return 数据实例
     */
    public ShipImage getData() {
        return data;
    }
}

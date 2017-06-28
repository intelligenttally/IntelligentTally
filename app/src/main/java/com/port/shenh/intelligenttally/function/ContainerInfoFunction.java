package com.port.shenh.intelligenttally.function;
/**
 * Created by 超悟空 on 2016/12/28.
 */

import android.view.View;
import android.widget.TextView;

import com.port.shenh.intelligenttally.R;
import com.port.shenh.intelligenttally.bean.ShipImage;

/**
 * 用于填充和处理集装箱信息布局的工具
 *
 * @author shenh
 * @version 1.0 2017/06/21
 * @since 1.0
 */
public class ContainerInfoFunction {

    /**
     * 日志前缀
     */
    private static final String LOG_TAG = "ContainerInfoFunction.";

    /**
     * 当前视图填充的贝位数据
     */
    private ShipImage data = null;

    /**
     * 贝位
     */
    private TextView bayNumber = null;

    /**
     * 捣箱
     */
    private TextView inverted = null;

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
     * 卸货港
     */
    private TextView code_unload_port = null;

    /**
     * 铅封号
     */
    private TextView sealno = null;

    /**
     * 已作业
     */
    private TextView unload = null;

    /**
     * 构造函数
     *
     * @param rootView 包含信息布局的根布局
     */
    public ContainerInfoFunction(View rootView) {
        initView(rootView);
    }

    /**
     * 初始化布局
     *
     * @param rootView 包含信息布局的根布局
     */
    private void initView(View rootView) {
        bayNumber = (TextView) rootView.findViewById(R.id.layout_container_info_bay_textView);
        inverted = (TextView) rootView.findViewById(R.id
                .layout_container_info_inverted_textView);
        empty = (TextView) rootView.findViewById(R.id.layout_container_info_empty_textView);
        size = (TextView) rootView.findViewById(R.id.layout_container_info_size_textView);
        type = (TextView) rootView.findViewById(R.id.layout_container_info_type_textView);
        code_unload_port = (TextView) rootView.findViewById(R.id.layout_container_info_code_unload_port_textView);
        unload = (TextView) rootView.findViewById(R.id.layout_container_info_unload_textView);
        sealno = (TextView) rootView.findViewById(R.id.layout_container_info_sealno_textView);
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
        inverted.setText(data.getMoved().equals("1") == true?"捣箱" : "");
        empty.setText(data.getCode_empty());
        size.setText(data.getSize_con());
        type.setText(data.getContainer_type());
        code_unload_port.setText(data.getCode_unload_port());
        sealno.setText(data.getSealno());
        unload.setText(data.getUnload_mark().equals("1")==true?"已作业":"未作业");
    }

    /**
     * 清空控件
     */
    public void clearData(){
        bayNumber.setText("");
        inverted.setText("");
        empty.setText("");
        size.setText("");
        type.setText("");
        code_unload_port.setText("");
        sealno.setText("");
        unload.setText("");
    }

}

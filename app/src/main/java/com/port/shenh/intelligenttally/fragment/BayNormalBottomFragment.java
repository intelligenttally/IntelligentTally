package com.port.shenh.intelligenttally.fragment;
/**
 * Created by 超悟空 on 2016/12/28.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.port.shenh.intelligenttally.R;
import com.port.shenh.intelligenttally.activity.BayActivity;
import com.port.shenh.intelligenttally.adapter.BayGridAdapter;
import com.port.shenh.intelligenttally.bean.ShipImage;
import com.port.shenh.intelligenttally.function.BottomBayCommonOperator;
import com.port.shenh.intelligenttally.function.BottomBayInfoFunction;

import org.mobile.library.model.operate.EmptyParameterListener;

/**
 * 正常状态的贝图底部布局
 *
 * @author 超悟空
 * @version 1.0 2016/12/28
 * @since 1.0
 */
public class BayNormalBottomFragment extends Fragment implements BottomBayCommonOperator {

    /**
     * 信息布局填充工具
     */
    private BottomBayInfoFunction function = null;

    /**
     * 父activity
     */
    private BayActivity activity = null;

    /**
     * 移贝状态的底部布局
     */
    private BayMoveBottomFragment moveBottomFragment = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bottom_sheet_normal, container, false);

        initView(rootView);

        return rootView;
    }

    /**
     * 初始化布局
     *
     * @param rootView 根布局
     */
    private void initView(View rootView) {
        function = new BottomBayInfoFunction(rootView);
        activity = (BayActivity) getActivity();
        initMove(rootView);
    }

    /**
     * 初始化移贝操作
     *
     * @param rootView 根布局
     */
    private void initMove(View rootView) {
        View view = rootView.findViewById(R.id.fragment_bottom_sheet_normal_move_bay_layout);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (moveBottomFragment == null) {
                    moveBottomFragment = new BayMoveBottomFragment();
                    moveBottomFragment.setOnMoveListener(new EmptyParameterListener() {
                        @Override
                        public void onInvoke() {
                            activity.onChangeBottomFragment(BayNormalBottomFragment.this);
                        }
                    });
                }

                activity.onChangeBottomFragment(moveBottomFragment);
                moveBottomFragment.setBayData(function.getData());
            }
        });
    }

    /**
     * 设置显示的贝数据
     *
     * @param data 贝数据
     */
    private void setBayData(ShipImage data) {
        function.bindData(data);
    }

    @Override
    public void onBayClick(BayGridAdapter.ViewHolder holder, ShipImage data) {

        if (holder != activity.beforeHolder) {
            if (!TextUtils.isEmpty(data.getBayno())) {
                setBayData(data);
                activity.showBottomLayout();
            } else {
                activity.hideBottomLayout();
            }

        } else {
            activity.hideBottomLayout();
        }
    }

    @Override
    public void onBaySwitch() {
        activity.hideBottomLayout();
    }
}

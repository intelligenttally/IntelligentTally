package com.port.shenh.intelligenttally.fragment;
/**
 * Created by 超悟空 on 2016/12/28.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
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
     * 日志标签前缀
     */
    private static final String LOG_TAG = "BayNormalBottomFragment.";

    /**
     * 控件集
     */
    private class ViewHolder {

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

        /**
         * 移贝按钮控件
         */
        private View bayMoveView = null;
    }

    /**
     * 控件集实例
     */
    private ViewHolder holder = new ViewHolder();

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
        holder.function = new BottomBayInfoFunction(rootView);
        holder.activity = (BayActivity) getActivity();
        initMove(rootView);
    }

    /**
     * 初始化移贝操作
     *
     * @param rootView 根布局
     */
    private void initMove(View rootView) {
        holder.bayMoveView = rootView.findViewById(R.id
                .fragment_bottom_sheet_normal_move_bay_layout);

        holder.bayMoveView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.activity.hideBottomLayout(new EmptyParameterListener() {
                        @Override
                    public void onInvoke() {
                        if (holder.moveBottomFragment == null) {
                            holder.moveBottomFragment = new BayMoveBottomFragment();
                            holder.moveBottomFragment.setOnMoveListener(new EmptyParameterListener() {
                                @Override
                                public void onInvoke() {
                                    holder.moveBottomFragment = null;
                                    onBackMe();
                                }
                            });
                        }

                        Log.i(LOG_TAG + "initMove", "initMove is invoked");
                        Log.i(LOG_TAG + "initMove", "bayno is " + holder.function.getData().getBayno());
                        Log.i(LOG_TAG + "initMove", "container_no is " + holder.function.getData().getContainer_no());

                        holder.moveBottomFragment.setBayData(holder.function.getData());
                        holder.activity.onChangeBottomFragment(holder.moveBottomFragment);
                        holder.activity.showBottomLayout();
                    }
                });
            }
        });
    }

    /**
     * 从子功能片段返回自身
     */
    private void onBackMe() {
        if (holder.activity.beforeHolder != null) {
            holder.activity.beforeHolder.itemView.setSelected(false);
            holder.activity.beforeHolder = null;
        }
        holder.activity.onChangeBottomFragment(BayNormalBottomFragment.this);
        holder.activity.hideBottomLayout();
    }

    /**
     * 设置显示的贝数据
     *
     * @param data 贝数据
     */
    private void setBayData(ShipImage data) {
        holder.function.bindData(data);
        Log.i(LOG_TAG + "setBayData", "bayno is " + data.getBayno());
        Log.i(LOG_TAG + "setBayData", "container_no is " + data.getContainer_no());

    }

    @Override
    public void onBayClick(BayGridAdapter.ViewHolder holder, ShipImage data) {

        if (holder != this.holder.activity.beforeHolder) {
            if (!TextUtils.isEmpty(data.getBayno())) {
                setBayData(data);
                Log.i(LOG_TAG + "onBayClick", "bayno is " + data.getBayno());
                Log.i(LOG_TAG + "onBayClick", "container_no is " + data.getContainer_no());


                if (data.getBaynum().compareTo(data.getBay_num()) < 0) {
                    this.holder.bayMoveView.setVisibility(View.INVISIBLE);
                } else {
                    this.holder.bayMoveView.setVisibility(View.VISIBLE);
                }

                this.holder.activity.showBottomLayout();
            } else {
                this.holder.activity.hideBottomLayout();
            }
        } else {
            this.holder.activity.hideBottomLayout();
        }
    }

    @Override
    public void onBaySwitch() {
        if (holder.activity != null) {
            holder.activity.hideBottomLayout();
        }
    }

    @Override
    public void onBack() {
        onBackMe();
    }
}

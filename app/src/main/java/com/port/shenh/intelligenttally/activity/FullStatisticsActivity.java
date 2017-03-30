package com.port.shenh.intelligenttally.activity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.port.shenh.intelligenttally.R;
import com.port.shenh.intelligenttally.bean.FullStatistics;
import com.port.shenh.intelligenttally.holder.FullStatisticsViewHolder;
import com.port.shenh.intelligenttally.util.StaticValue;
import com.port.shenh.intelligenttally.work.PullFullStatistics;

import org.mobile.library.common.function.ToolbarInitialize;
import org.mobile.library.model.work.DefaultWorkModel;
import org.mobile.library.model.work.WorkBack;

public class FullStatisticsActivity extends AppCompatActivity {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "FullStatisticsActivity.";

    /**
     * 航次
     */
    String ship_id = null;

    /**
     * 进出口编码
     */
    String code_inout = null;

    /**
     * 下拉刷新控件
     */
    public SwipeRefreshLayout refreshLayout = null;

    /**
     * 上一个执行的加载任务
     */
    public volatile DefaultWorkModel beforeLoadWork = null;

    /**
     * 全统计ViewHolder
     */
    FullStatisticsViewHolder viewHolder = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_full);

        // 初始化控件引用
        initViewHolder();
        // 加载界面
        initView();
    }

    /**
     * 初始化空间引用
     */
    private void initViewHolder() {

        // 创建Item根布局
        View view = getWindow().getDecorView();

        viewHolder = new FullStatisticsViewHolder(view);

        ship_id = (String) getIntent().getSerializableExtra(StaticValue.IntentTag.VOYAGE_TAG);

        code_inout = (String) getIntent().getSerializableExtra(StaticValue.IntentTag.CODE_INOUT_TAG);

        refreshLayout = (SwipeRefreshLayout) findViewById(R.id
                .activity_statistics_full_swipeRefreshLayout);

    }

    /**
     * 初始化控件
     */
    private void initView() {
        // 初始化Toolbar
        ToolbarInitialize.initToolbar(this, R.string.full_statistics, true, true);

        // 初始化数据
        loadData(false);
        //初始化刷新控件
        initSwipeRefresh();
    }


    /**
     * 初始化刷新控件
     */
    private void initSwipeRefresh() {

        TypedArray typedArray = obtainStyledAttributes(new int[]{R.attr.colorPrimary});
        refreshLayout.setColorSchemeResources(typedArray.getResourceId(0, 0));
        typedArray.recycle();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        loadData(true);
    }

    /**
     * 加载数据
     *
     * @param reload 表示是否为全新加载，true表示为全新加载
     */
    private void loadData(boolean reload) {
        Log.i(LOG_TAG + "loadData", "reload tag is " + reload);

        if (reload) {
            // 中断上次请求
            if (beforeLoadWork != null) {
                beforeLoadWork.cancel();
            }
        }

        PullFullStatistics pullFullStatistics = new PullFullStatistics();
        pullFullStatistics.setWorkEndListener(new WorkBack<FullStatistics>() {
            @Override
            public void doEndWork(boolean state, FullStatistics data) {


                Log.i(LOG_TAG + "loadData", "PullFullStatistics state is " + state);

                if (state && data != null) {

                    fillData(data);
                }

                // 停止动画
                refreshLayout.setRefreshing(false);

            }
        });

        // 执行任务
        pullFullStatistics.beginExecute(ship_id, code_inout);

        // 保存新的加载任务对象
        beforeLoadWork = pullFullStatistics;


    }


    /**
     * 填充数据
     *
     * @param data 数据源
     */
    private void fillData(FullStatistics data) {

        viewHolder.forecast_totalTextView.setText(Integer.toString(data.getForecast_total()));
        viewHolder.forecast_E_20TextView.setText(Integer.toString(data.getForecast_E_20()));
        viewHolder.forecast_E_40TextView.setText(Integer.toString(data.getForecast_E_40()));
        viewHolder.forecast_E_otherTextView.setText(Integer.toString(data.getForecast_E_other()));
        viewHolder.forecast_E_totalTextView.setText(Integer.toString(data.getForecast_E_total()));
        viewHolder.forecast_F_20TextView.setText(Integer.toString(data.getForecast_F_20()));
        viewHolder.forecast_F_40TextView.setText(Integer.toString(data.getForecast_F_40()));
        viewHolder.forecast_F_otherTextView.setText(Integer.toString(data.getForecast_F_other()));
        viewHolder.forecast_F_totalTextView.setText(Integer.toString(data.getForecast_F_total()));

        viewHolder.tally_totalTextView.setText(Integer.toString(data.getTally_total()));
        viewHolder.tally_E_20TextView.setText(Integer.toString(data.getTally_E_20()));
        viewHolder.tally_E_40TextView.setText(Integer.toString(data.getTally_E_40()));
        viewHolder.tally_E_otherTextView.setText(Integer.toString(data.getTally_E_other()));
        viewHolder.tally_E_totalTextView.setText(Integer.toString(data.getTally_E_total()));
        viewHolder.tally_F_20TextView.setText(Integer.toString(data.getTally_F_20()));
        viewHolder.tally_F_40TextView.setText(Integer.toString(data.getTally_F_40()));
        viewHolder.tally_F_otherTextView.setText(Integer.toString(data.getTally_F_other()));
        viewHolder.tally_F_totalTextView.setText(Integer.toString(data.getTally_F_total()));

        viewHolder.abnormal_totalTextView.setText(Integer.toString(data.getAbnormal_total()));
        viewHolder.abnormal_E_20TextView.setText(Integer.toString(data.getAbnormal_E_20()));
        viewHolder.abnormal_E_40TextView.setText(Integer.toString(data.getAbnormal_E_40()));
        viewHolder.abnormal_E_otherTextView.setText(Integer.toString(data.getAbnormal_E_other()));
        viewHolder.abnormal_E_totalTextView.setText(Integer.toString(data.getAbnormal_E_total()));
        viewHolder.abnormal_F_20TextView.setText(Integer.toString(data.getAbnormal_F_20()));
        viewHolder.abnormal_F_40TextView.setText(Integer.toString(data.getAbnormal_F_40()));
        viewHolder.abnormal_F_otherTextView.setText(Integer.toString(data.getAbnormal_F_other()));
        viewHolder.abnormal_F_totalTextView.setText(Integer.toString(data.getAbnormal_F_total()));


    }
}

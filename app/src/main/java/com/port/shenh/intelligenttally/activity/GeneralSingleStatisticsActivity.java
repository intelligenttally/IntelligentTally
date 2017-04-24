package com.port.shenh.intelligenttally.activity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.port.shenh.intelligenttally.R;
import com.port.shenh.intelligenttally.fragment.SingleStatisticsOperateFragment;
import com.port.shenh.intelligenttally.fragment.SingleStatisticsTallyFragment;
import com.port.shenh.intelligenttally.util.StaticValue;

import org.mobile.library.common.function.ToolbarInitialize;

public class GeneralSingleStatisticsActivity extends AppCompatActivity {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "SingleStatisticsActivity.";

    /**
     * 个统计理货员布局
     */
    private SingleStatisticsTallyFragment singleStatisticsTallyFragment;

    /**
     * 个统计操作员布局
     */
    private SingleStatisticsOperateFragment singleStatisticsOperateFragment;

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
     * 下拉刷新控件
     */
    //    public SwipeRefreshLayout refreshLayout = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_single_statistics);

        // 初始化控件引用
        initViewHolder();
        // 加载界面
        initView();
    }

    /**
     * 初始化控件引用
     */
    private void initViewHolder() {

        ship_id = (String) getIntent().getSerializableExtra(StaticValue.IntentTag.VOYAGE_TAG);

        code_inout = (String) getIntent().getSerializableExtra(StaticValue.IntentTag.CODE_INOUT_TAG);

        Log.i(LOG_TAG + "initViewHolder", "ship_id is " + ship_id);
        Log.i(LOG_TAG + "initViewHolder", "code_inout is " + code_inout);

        singleStatisticsTallyFragment = new SingleStatisticsTallyFragment();
        singleStatisticsTallyFragment.setShipId(ship_id);
        singleStatisticsTallyFragment.setCodeInout(code_inout);
        singleStatisticsOperateFragment = new SingleStatisticsOperateFragment();
        singleStatisticsOperateFragment.setShipId(ship_id);
        singleStatisticsOperateFragment.setCodeInout(code_inout);

        refreshLayout = (SwipeRefreshLayout) findViewById(R.id
                .activity_statistics_single_swipeRefreshLayout);

    }

    /**
     * 加载界面
     */
    private void initView() {

        // 初始化Toolbar
        ToolbarInitialize.initToolbar(this, R.string.single_statistics, true, true);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_statistics_single_tally,
                singleStatisticsTallyFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id
                .fragment_statistics_single_operate, singleStatisticsOperateFragment).commit();

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
                RefreshView();
            }
        });
    }

    /**
     * 刷新界面
     */
    private void RefreshView() {

        Log.i(LOG_TAG + "RefreshView", "RefreshView is invoked");

        getSupportFragmentManager().beginTransaction().remove(singleStatisticsTallyFragment)
                .commit();
        getSupportFragmentManager().beginTransaction().remove(singleStatisticsOperateFragment)
                .commit();

        singleStatisticsTallyFragment = new SingleStatisticsTallyFragment();
        singleStatisticsTallyFragment.setShipId(ship_id);
        singleStatisticsTallyFragment.setCodeInout(code_inout);
        singleStatisticsOperateFragment = new SingleStatisticsOperateFragment();
        singleStatisticsOperateFragment.setShipId(ship_id);
        singleStatisticsOperateFragment.setCodeInout(code_inout);

        getSupportFragmentManager().beginTransaction().add(R.id
                .fragment_statistics_single_tally, singleStatisticsTallyFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id
                .fragment_statistics_single_operate, singleStatisticsOperateFragment).commit();

//        getSupportFragmentManager().beginTransaction().hide(singleStatisticsTallyFragment).show
//                (singleStatisticsTallyFragment).commit();
//        getSupportFragmentManager().beginTransaction().hide(singleStatisticsOperateFragment).show
//                (singleStatisticsOperateFragment).commit();

        // 停止动画
        refreshLayout.setRefreshing(false);
    }

    //    /**
    //     * 刷新
    //     */
    //    private void refresh() {
    //        finish();
    //        Intent intent = new Intent(SingleStatisticsActivity.this, SingleStatisticsActivity
    // .class);
    //        startActivity(intent);
    //    }
}

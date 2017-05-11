package com.port.shenh.intelligenttally.activity;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.port.shenh.intelligenttally.R;
import com.port.shenh.intelligenttally.bean.FullStatistics;
import com.port.shenh.intelligenttally.bean.Voyage;
import com.port.shenh.intelligenttally.function.VoyageDownloadedSelectList;
import com.port.shenh.intelligenttally.holder.FullStatisticsViewHolder;
import com.port.shenh.intelligenttally.work.PullMovedFullStatistics;

import org.mobile.library.common.function.ToolbarInitialize;
import org.mobile.library.model.function.ISelectList;
import org.mobile.library.model.work.DefaultWorkModel;
import org.mobile.library.model.work.WorkBack;

public class MovedFullStatisticsActivity extends AppCompatActivity {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "MovedFullStatisticsActivity.";

    /**
     * 当前航次
     */
    Voyage cunVoyage = null;

    /**
     * 航次编码
     */
    String ship_id = null;

    /**
     * 进出口编码
     */
    String code_inOut = null;

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

    /**
     * 已下载航次选择列表
     */
    private VoyageDownloadedSelectList voyageDownloadedSelectList = null;

    /**
     * 标题文本框
     */
    private TextView titleTextView = null;


    /**
     * 用于显示选择列表的窗口
     */
    public PopupWindow popupWindow = null;

    /**
     * 弹出窗口的内容布局
     */
    public CardView cardView = null;

    /**
     * 根布局
     */
    private View rootView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_general_full_statistics);

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
        rootView = getWindow().getDecorView();

        viewHolder = new FullStatisticsViewHolder(rootView);

        //        ship_id = (String) getIntent().getSerializableExtra(StaticValue.IntentTag
        // .VOYAGE_TAG);
        //
        //        code_inout = (String) getIntent().getSerializableExtra(StaticValue.IntentTag
        // .CODE_INOUT_TAG);

        refreshLayout = (SwipeRefreshLayout) findViewById(R.id
                .activity_statistics_full_swipeRefreshLayout);

        // 弹出窗口布局
        cardView = (CardView) LayoutInflater.from(this).inflate(R.layout.layout_popup_window, null);
        popupWindow = new PopupWindow(this);

    }

    /**
     * 初始化控件
     */
    private void initView() {
        // 初始化Toolbar
        ToolbarInitialize.initToolbar(this, R.string.full_statistics, true, true);
        titleTextView = (TextView) findViewById(R.id.toolbar_title);

        // 初始化弹出框
        initPopupWindow();
        //初始化已下载航次选择列表
        InitVoyageDownloadedSelectList();
        // 初始化数据
        loadData(false);
        //初始化刷新控件
        initSwipeRefresh();
    }

    /**
     * 初始化已下载航次选择列表
     */
    private void InitVoyageDownloadedSelectList() {

        voyageDownloadedSelectList = new VoyageDownloadedSelectList(this);

        voyageDownloadedSelectList.setOnSelectedListener(new ISelectList.OnSelectedListener<View,
                Voyage>() {


            @Override
            public void onFinish(Voyage voyage) {

                cunVoyage = voyage;

                loadData(true);

                popupWindow.dismiss();
            }

            @Override
            public void onCancel(View view) {

            }
        });
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

        String title = "";

        if (reload) {
            // 中断上次请求
            if (beforeLoadWork != null) {
                beforeLoadWork.cancel();
            }

            if (cunVoyage != null) {
                ship_id = cunVoyage.getShip_id();
                code_inOut = cunVoyage.getCodeInOut();
                String inOut = code_inOut.equals("1") == true ? "出" : "进";
                title = cunVoyage.getBerthno() + " " + inOut + " " + cunVoyage.getVoyage() + " "
                        + cunVoyage.getChi_vessel();
            }
        }

        PullMovedFullStatistics pullMovedFullStatistics = new PullMovedFullStatistics();
        pullMovedFullStatistics.setWorkEndListener(new WorkBack<FullStatistics>() {
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
        pullMovedFullStatistics.beginExecute(ship_id);

        // 保存新的加载任务对象
        beforeLoadWork = pullMovedFullStatistics;

        titleTextView.setText(title);


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

    /**
     * 初始化弹出框
     */
    private void initPopupWindow() {
        popupWindow.setContentView(cardView);
        popupWindow.setWidth(getResources().getDimensionPixelOffset(R.dimen
                .filter_popup_window_weight2));
        popupWindow.setHeight(getResources().getDimensionPixelOffset(R.dimen
                .filter_popup_window_height));
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    /**
     * 显示PopupWindow
     *
     * @param anchor 依附的布局
     * @param view   要显示的布局
     */
    private void showPopupWindow(View anchor, View view) {

        if (!popupWindow.isShowing()) {
            cardView.removeAllViews();
            cardView.addView(view);
            popupWindow.showAtLocation(view, Gravity.RIGHT, 0, 0);
            popupWindow.showAsDropDown(anchor);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_full_statistics, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_voyage_select:
                //已下载航次选择
                doVoyageSelect();
                break;
        }
        return true;
    }

    /**
     * 已下载航次选择
     */
    private void doVoyageSelect() {

        showPopupWindow(rootView, voyageDownloadedSelectList.loadSelect());

    }
}

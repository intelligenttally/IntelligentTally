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
import com.port.shenh.intelligenttally.bean.Voyage;
import com.port.shenh.intelligenttally.fragment.MovedSingleStatisticsOperateFragment;
import com.port.shenh.intelligenttally.fragment.MovedSingleStatisticsTallyFragment;
import com.port.shenh.intelligenttally.function.VoyageDownloadedSelectList;

import org.mobile.library.common.function.ToolbarInitialize;
import org.mobile.library.model.function.ISelectList;

public class MovedSingleStatisticsActivity extends AppCompatActivity {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "MovedSingleStatisticsActivity.";

    /**
     * 捣箱个统计理货员布局
     */
    private MovedSingleStatisticsTallyFragment movedSingleStatisticsTallyFragment;

    /**
     * 捣箱个统计操作员布局
     */
    private MovedSingleStatisticsOperateFragment movedSingleStatisticsOperateFragment;

    /**
     * 当前航次
     */
    Voyage cunVoyage = null;

    /**
     * 航次
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

        // 创建Item根布局
        rootView = getWindow().getDecorView();

//        ship_id = (String) getIntent().getSerializableExtra(StaticValue.IntentTag.VOYAGE_TAG);
//        code_inout = (String) getIntent().getSerializableExtra(StaticValue.IntentTag.CODE_INOUT_TAG);

        Log.i(LOG_TAG + "initViewHolder", "ship_id is " + ship_id);
        Log.i(LOG_TAG + "initViewHolder", "code_inout is " + code_inOut);

        movedSingleStatisticsTallyFragment = new MovedSingleStatisticsTallyFragment();
        movedSingleStatisticsTallyFragment.setShipId(ship_id);
        movedSingleStatisticsOperateFragment = new MovedSingleStatisticsOperateFragment();
        movedSingleStatisticsOperateFragment.setShipId(ship_id);

        refreshLayout = (SwipeRefreshLayout) findViewById(R.id
                .activity_statistics_single_swipeRefreshLayout);

        // 弹出窗口布局
        cardView = (CardView) LayoutInflater.from(this).inflate(R.layout.layout_popup_window, null);
        popupWindow = new PopupWindow(this);

    }

    /**
     * 加载界面
     */
    private void initView() {

        // 初始化Toolbar
        ToolbarInitialize.initToolbar(this, R.string.single_statistics, true, true);
        titleTextView = (TextView) findViewById(R.id.toolbar_title);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_statistics_single_tally,
                movedSingleStatisticsTallyFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id
                .fragment_statistics_single_operate, movedSingleStatisticsOperateFragment).commit();
        titleTextView.setText("");

        // 初始化弹出框
        initPopupWindow();
        //初始化已下载航次选择列表
        InitVoyageDownloadedSelectList();
        //初始化刷新控件
        initSwipeRefresh();


    }

    /**
     * 初始化已下载航次选择列表
     */
    private void InitVoyageDownloadedSelectList(){

        voyageDownloadedSelectList = new VoyageDownloadedSelectList(this);

        voyageDownloadedSelectList.setOnSelectedListener(new ISelectList.OnSelectedListener<View, Voyage>() {


            @Override
            public void onFinish(Voyage voyage) {

                cunVoyage = voyage;

                RefreshView();

                popupWindow.dismiss();
            }

            @Override
            public void onCancel(View view) {

            }
        });
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
    private void doVoyageSelect(){

        showPopupWindow(rootView, voyageDownloadedSelectList.loadSelect());

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

        String title = "";
        if (cunVoyage != null){
            ship_id = cunVoyage.getShip_id();
            code_inOut = cunVoyage.getCodeInOut();
            String inOut = code_inOut.equals("1") == true ? "出" : "进";
            title = cunVoyage.getBerthno() + " " + inOut + " " +cunVoyage
                    .getVoyage() + " " + cunVoyage.getChi_vessel();
            titleTextView.setText(title);
        }

        getSupportFragmentManager().beginTransaction().remove(movedSingleStatisticsTallyFragment)
                .commit();
        getSupportFragmentManager().beginTransaction().remove(movedSingleStatisticsOperateFragment)
                .commit();

        movedSingleStatisticsTallyFragment = new MovedSingleStatisticsTallyFragment();
        movedSingleStatisticsTallyFragment.setShipId(ship_id);
        movedSingleStatisticsOperateFragment = new MovedSingleStatisticsOperateFragment();
        movedSingleStatisticsOperateFragment.setShipId(ship_id);

        getSupportFragmentManager().beginTransaction().add(R.id
                .fragment_statistics_single_tally, movedSingleStatisticsTallyFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id
                .fragment_statistics_single_operate, movedSingleStatisticsOperateFragment).commit();

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

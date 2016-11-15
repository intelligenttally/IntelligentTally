//package com.port.shenh.intelligenttally.activity;
///**
// * Created by sh on 2016/11/15.
// */
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.Toolbar;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.View;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.EditText;
//
//import com.port.shenh.intelligenttally.adapter.VoyageRecyclerViewAdapter;
//import com.port.shenh.intelligenttally.bean.Voyage;
//import com.port.shenh.intelligenttally.holder.VoyageItemViewHolder;
//
//import org.mobile.library.common.function.InputMethodController;
//import org.mobile.library.common.function.ToolbarInitialize;
//import org.mobile.library.model.operate.OnItemClickListenerForRecyclerViewItem;
//import org.mobile.library.model.work.DefaultWorkModel;
//
//import java.util.List;
//import java.util.Timer;
//import java.util.TimerTask;
//
///**
// * 航次下载Activity
// *
// * @author sh
// * @version 1.0 2016/11/15
// * @since 1.0
// */
//public class VoyageDownloadActivity extends AppCompatActivity {
//
//    /**
//     * 日志标签前缀
//     */
//    private static final String LOG_TAG = "VoyageDownloadActivity.";
//
//    /**
//     * 一次性加载的数据行数
//     */
//    private static final int ROW_COUNT = 30;
//
//    /**
//     * 加载更多数据的触发剩余行数
//     */
//    private static final int LAST_ROW_COUNT = 15;
//
//    /**
//     * 控件集
//     */
//    private class LocalViewHolder {
//
//        /**
//         * 堆存列表数据适配器
//         */
//        public VoyageRecyclerViewAdapter recyclerViewAdapter = null;
//
//        /**
//         * 上一个执行的加载任务
//         */
//        public volatile DefaultWorkModel beforeLoadWork = null;
//
//        /**
//         * 表示是否还有更多数据
//         */
//        public volatile boolean hasMoreData = false;
//
//        /**
//         * 表示是否正在加载
//         */
//        public volatile boolean loading = false;
//
//        /**
//         * 保留上次查询数据
//         */
//        public String oldParameter = null;
//    }
//
//    /**
//     * 控件集对象
//     */
//    private LocalViewHolder viewHolder = null;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_stock_query);
//
//        // 初始化控件引用
//        initViewHolder();
//        // 加载界面
//        initView();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        // 初始化数据
//        initData();
//    }
//
//    /**
//     * 初始化控件引用
//     */
//    private void initViewHolder() {
//        viewHolder = new LocalViewHolder();
//
//        // 堆存列表适配器
//        viewHolder.recyclerViewAdapter = new VoyageRecyclerViewAdapter();
//    }
//
//    /**
//     * 初始化控件
//     */
//    private void initView() {
//        // 初始化Toolbar
//        ToolbarInitialize.initToolbar(R.string.voyage_download, true, true);
//        // 初始化列表
//        initListView();
//    }
//
//    /**
//     * 初始化列表
//     */
//    private void initListView() {
//
//        // RecyclerView列表对象
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id
//                .activity_stock_query_recyclerView);
//
//        // 设置item动画
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//
//        // 创建布局管理器
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        // 设置布局管理器
//        recyclerView.setLayoutManager(layoutManager);
//
//        // 设置点击事件
//        viewHolder.recyclerViewAdapter.setOnItemClickListener(new OnItemClickListenerForRecyclerViewItem<List<Voyage>, VoyageItemViewHolder>() {
//            @Override
//            public void onClick(List<Voyage> stocks, VoyageItemViewHolder voyageItemViewHolder) {
//                Voyage stock = stocks.get(voyageItemViewHolder.getAdapterPosition());
//
////                // 跳转意图
////                Intent intent = new Intent(VoyageDownloadActivity.this, StockContentActivity.class);
////
////                // 加入堆存编号
////                intent.putExtra(StaticValue.IntentTag.STOCK_ID_TAG, stock.getId());
////                // 加入货场编号
////                intent.putExtra(StaticValue.IntentTag.STORAGE_CODE_TAG, stock.getStorageCode());
////                // 加入货位编号
////                intent.putExtra(StaticValue.IntentTag.POSITION_CODE_TAG, stock.getPositionCode());
////
////                // 跳转到详情页面
////                startActivity(intent);
//            }
//        });
//
//        // 设置列表适配器
//        recyclerView.setAdapter(viewHolder.recyclerViewAdapter);
//
//        // 设置加载更多
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//
//                int lastCount = recyclerView.getAdapter().getItemCount() - recyclerView
//                        .getChildAdapterPosition(recyclerView.getChildAt(0)) - recyclerView
//                        .getChildCount();
//                Log.i(LOG_TAG + "initListView", "onScrolled lastCount is " + lastCount);
//                if (dy > 0 && !viewHolder.loading && viewHolder.hasMoreData && lastCount <=
//                        LAST_ROW_COUNT) {
//                    // 有必要加载更多
//                    Log.i(LOG_TAG + "initListView", "onScrolled now load more");
//                    loadData(false);
//                }
//            }
//        });
//    }
//
//    /**
//     * 初始化数据
//     */
//    private void initData() {
//        loadData(true);
//    }
//
//    /**
//     * 加载数据
//     *
//     * @param reload 表示是否为全新加载，true表示为全新加载
//     */
//    private void loadData(boolean reload) {
//        Log.i(LOG_TAG + "loadData", "reload tag is " + reload);
//
//        if (reload) {
//            // 属于全新加载数据，清空原数据
//            viewHolder.recyclerViewAdapter.clear();
//            // 中断上次请求
//            if (viewHolder.beforeLoadWork != null) {
//                viewHolder.beforeLoadWork.cancel();
//            }
//        }
//
//        // 堆存列表任务
//        PullStockList pullStockList = new PullStockList();
//
//        pullStockList.setWorkEndListener(new WorkBack<List<Stock>>() {
//            @Override
//            public void doEndWork(boolean state, List<Stock> data) {
//                if (state && data != null) {
//                    // 插入新数据
//                    viewHolder.recyclerViewAdapter.addData(viewHolder.recyclerViewAdapter
//                            .getItemCount(), data);
//
//                    if (data.size() == ROW_COUNT) {
//                        // 取到了预期条数的数据
//                        viewHolder.hasMoreData = true;
//                    }
//                }
//
//                // 改变请求状态
//                viewHolder.loading = false;
//            }
//        });
//
//        // 改变请求状态
//        viewHolder.loading = true;
//        // 初始化更多预期
//        viewHolder.hasMoreData = false;
//
//        // 执行任务
//        pullStockList.beginExecute("14", String.valueOf(viewHolder.recyclerViewAdapter
//                .getItemCount()), String.valueOf(ROW_COUNT), viewHolder.cargoTypeEditText.getText
//                ().toString(), viewHolder.cargoOwnerEditText.getText().toString(), viewHolder
//                .forwarderEditText.getText().toString(), viewHolder.storageEditText.getText()
//                .toString());
//
//        // 保存新的加载任务对象
//        viewHolder.beforeLoadWork = pullStockList;
//    }
//
//    /**
//     * 关闭导航抽屉
//     *
//     * @return 成功关闭返回true，未打开则返回false
//     */
//    public boolean closeDrawer() {
//        if (viewHolder.drawerLayout != null) {
//            if (viewHolder.drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
//                viewHolder.drawerLayout.closeDrawer(Gravity.RIGHT);
//                return true;
//            }
//            if (viewHolder.drawerLayout.isDrawerOpen(Gravity.LEFT)) {
//                viewHolder.drawerLayout.closeDrawer(Gravity.LEFT);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public void onBackPressed() {
//
//        // 如果抽屉已打开，则先关闭抽屉
//        if (!closeDrawer()) {
//            super.onBackPressed();
//        }
//    }
//
//}

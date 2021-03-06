package com.port.shenh.intelligenttally.activity;
/**
 * Created by sh on 2016/11/15.
 */

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.port.shenh.intelligenttally.R;
import com.port.shenh.intelligenttally.adapter.VoyageOfInPortRecyclerViewAdapter;
import com.port.shenh.intelligenttally.bean.Voyage;
import com.port.shenh.intelligenttally.function.ShipImageListFunction;
import com.port.shenh.intelligenttally.function.VoyageListFunction;
import com.port.shenh.intelligenttally.holder.VoyageItemViewHolder;
import com.port.shenh.intelligenttally.work.PullVoyageListOfInPort;

import org.mobile.library.common.function.ToolbarInitialize;
import org.mobile.library.global.Global;
import org.mobile.library.model.operate.OnItemClickListenerForRecyclerViewItem;
import org.mobile.library.model.work.DefaultWorkModel;
import org.mobile.library.model.work.WorkBack;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static android.widget.Toast.makeText;

/**
 * 航次下载Activity
 *
 * @author sh
 * @version 1.0 2016/11/15
 * @since 1.0
 */
public class VoyageDownloadActivity extends AppCompatActivity {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "VoyageDownloadActivity.";

    /**
     * 一次性加载的数据行数
     */
    private static final int ROW_COUNT = 30;

    /**
     * 加载更多数据的触发剩余行数
     */
    private static final int LAST_ROW_COUNT = 15;

    /**
     * 控件集
     */
    private class LocalViewHolder {

        /**
         * 堆存列表数据适配器
         */
        public VoyageOfInPortRecyclerViewAdapter recyclerViewAdapter = null;

        /**
         * 上一个执行的加载任务
         */
        public volatile DefaultWorkModel beforeLoadWork = null;

        /**
         * 表示是否还有更多数据
         */
        public volatile boolean hasMoreData = false;

        /**
         * 表示是否正在加载
         */
        public volatile boolean loading = false;

        /**
         * 保留上次查询数据
         */
        public String oldParameter = null;

        /**
         * 船图数据功能类
         */
        public ShipImageListFunction shipImageListFunction = null;

        /**
         * 航次数据功能类
         */
        public VoyageListFunction voyageListFunction = null;

        /**
         * 下拉刷新控件
         */
        public SwipeRefreshLayout refreshLayout = null;
    }

    /**
     * 控件集对象
     */
    private LocalViewHolder viewHolder = null;

    /**
     * 进度条
     */
    private ProgressDialog progressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voyage_download);

        // 初始化控件引用
        initViewHolder();
        // 加载界面
        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();

        // 初始化数据
        initData();
    }

    /**
     * 初始化控件引用
     */
    private void initViewHolder() {
        viewHolder = new LocalViewHolder();

        // 堆存列表适配器
        viewHolder.recyclerViewAdapter = new VoyageOfInPortRecyclerViewAdapter();

        viewHolder.shipImageListFunction = new ShipImageListFunction(this);

        viewHolder.voyageListFunction = new VoyageListFunction(this);

        viewHolder.refreshLayout = (SwipeRefreshLayout) findViewById(R.id
                .activity_voyage_download_swipeRefreshLayout);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        // 初始化Toolbar
        ToolbarInitialize.initToolbar(this, R.string.voyage_download, true, true);
        // 初始化列表
        initListView();
        //初始化刷新控件
        initSwipeRefresh();
    }

    /**
     * 初始化列表
     */
    private void initListView() {

        // RecyclerView列表对象
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id
                .activity_voyage_download_recyclerView);

        // 设置item动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // 创建布局管理器
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        // 设置点击事件
        viewHolder.recyclerViewAdapter.setOnItemClickListener(new OnItemClickListenerForRecyclerViewItem<List<Voyage>, VoyageItemViewHolder>() {
            @Override
            public void onClick(List<Voyage> voyages, VoyageItemViewHolder voyageItemViewHolder) {

                viewHolder.recyclerViewAdapter.switchSelectedState(voyageItemViewHolder);
            }
        });

        // 设置列表适配器
        recyclerView.setAdapter(viewHolder.recyclerViewAdapter);

        // 设置加载更多
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                int lastCount = recyclerView.getAdapter().getItemCount() - recyclerView
                        .getChildAdapterPosition(recyclerView.getChildAt(0)) - recyclerView
                        .getChildCount();
                Log.i(LOG_TAG + "initListView", "onScrolled lastCount is " + lastCount);
                if (dy > 0 && !viewHolder.loading && viewHolder.hasMoreData && lastCount <=
                        LAST_ROW_COUNT) {
                    // 有必要加载更多
                    Log.i(LOG_TAG + "initListView", "onScrolled now load more");
                    loadData(false);
                }
            }
        });
    }

    /**
     * 初始化刷新控件
     */
    private void initSwipeRefresh() {
        TypedArray typedArray = obtainStyledAttributes(new int[]{R.attr.colorPrimary});
        viewHolder.refreshLayout.setColorSchemeResources(typedArray.getResourceId(0, 0));
        typedArray.recycle();

        viewHolder.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
            // 属于全新加载数据，清空原数据
            viewHolder.recyclerViewAdapter.clear();
            // 中断上次请求
            if (viewHolder.beforeLoadWork != null) {
                viewHolder.beforeLoadWork.cancel();
            }
        }

        // 堆存列表任务
        PullVoyageListOfInPort pullVoyageList = new PullVoyageListOfInPort();

        pullVoyageList.setWorkEndListener(new WorkBack<List<Voyage>>() {
            @Override
            public void doEndWork(boolean state, List<Voyage> data) {

                if (state) {
                    if (data != null) {

                        for (int i = 0; i < data.size(); i++) {
                            Voyage voyage = data.get(i);

                            Log.i(LOG_TAG + "loadData", "voyage ship_id is " + voyage.getShip_id());

                            if (viewHolder.shipImageListFunction.isDownloaded(voyage.getShip_id()
                            )) {
                                voyage.setDownloaded(true);
                            }
                        }

                        // 插入新数据
                        viewHolder.recyclerViewAdapter.addData(viewHolder.recyclerViewAdapter
                                .getItemCount(), data);

                        if (data.size() == ROW_COUNT) {
                            // 取到了预期条数的数据
                            viewHolder.hasMoreData = true;
                        }
                    }
                } else {

                    makeText(getBaseContext(), R.string.error_field_required, Toast.LENGTH_SHORT)
                            .show();

                }


                //停止进度条
                stopProgressDialog();
                // 改变请求状态
                viewHolder.loading = false;

                // 停止动画
                viewHolder.refreshLayout.setRefreshing(false);
            }
        });

        // 改变请求状态
        viewHolder.loading = true;
        // 初始化更多预期
        viewHolder.hasMoreData = false;

        // 打开加载动画
        viewHolder.refreshLayout.setRefreshing(true);

        // 执行任务
        pullVoyageList.beginExecute(String.valueOf(viewHolder.recyclerViewAdapter.getItemCount())
                , String.valueOf(ROW_COUNT), Global.getLoginStatus().getUserID());

        // 保存新的加载任务对象
        viewHolder.beforeLoadWork = pullVoyageList;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_vovage_download, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_download:
                // 下载
                doDownload();
                break;
        }
        return true;
    }

    /**
     * 下载操作
     */
    private void doDownload() {


        if (viewHolder.recyclerViewAdapter.getSelectedItemCount() == 0) {

            makeText(this, R.string.not_selected, Toast.LENGTH_SHORT).show();

            return;

        }

        new AlertDialog.Builder(VoyageDownloadActivity.this).setTitle("确认").setIcon(android.R
                .drawable.ic_dialog_info).setMessage("是否下载？").setPositiveButton("确定", new
                DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Log.i(LOG_TAG + "doDownload", " is invoked");

                List<Voyage> selectedDataList = viewHolder.recyclerViewAdapter
                        .getSelectedDataList();
                startProgressDialog();

                final AtomicInteger count = new AtomicInteger(1);

                viewHolder.shipImageListFunction.setOnLoadEndListener(new ShipImageListFunction
                        .OnLoadEndListener() {
                    @Override
                    public void OnLoadEnd(boolean state) {
                        Log.i(LOG_TAG + "OnLoadEnd", "count is" + count.get());

                        if (state) {
                            if (count.decrementAndGet() == 0) {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        loadData(true);
                                        makeText(getBaseContext(), R.string.download_success,
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } else {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //停止进度条
                                    stopProgressDialog();
                                    Toast.makeText(getBaseContext(), R.string
                                            .download_error_field_required, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                    }
                });

                viewHolder.voyageListFunction.setOnLoadEndListener(new VoyageListFunction
                        .OnLoadEndListener() {
                    @Override
                    public void OnLoadEnd(boolean state) {
                        Log.i(LOG_TAG + "OnLoadEnd", "count is" + count.get());
                        if (count.decrementAndGet() == 0) {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    loadData(true);
                                    makeText(getBaseContext(), R.string.download_success, Toast
                                            .LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });

                for (int i = 0; i < selectedDataList.size(); i++) {
                    count.incrementAndGet();
                    Voyage voyage = selectedDataList.get(i);

                    Log.i(LOG_TAG + "doDownload", "Ship_Id is" + voyage.getShip_id());

                    //此处需要返回是否有加载数据
                    viewHolder.shipImageListFunction.onLoad(voyage.getShip_id());
                }

                for (int i = 0; i < selectedDataList.size(); i++) {
                    count.incrementAndGet();
                    Voyage voyage = selectedDataList.get(i);

                    Log.i(LOG_TAG + "doDownload", "Ship_Id is" + voyage.getShip_id());

                    //此处需要返回是否有加载数据
                    viewHolder.voyageListFunction.onLoad(voyage.getShip_id());
                }

                count.decrementAndGet();


            }
        }).setNegativeButton("取消", null).show();

    }


    /**
     * 打开进度条
     */
    protected void startProgressDialog() {

        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            // 设置提醒
            progressDialog.setMessage("数据正在下载中....");
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }

    /**
     * 停止进度条
     */
    protected void stopProgressDialog() {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
    }
}


//                            new Thread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    try {
//                                        Thread.sleep(3000);
//                                        Log.i(LOG_TAG + "doDownload", "----is invoked");
//
//                                        //停止进度条
//                                        stopProgressDialog();
//
//
//                                    } catch (InterruptedException e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }).start();

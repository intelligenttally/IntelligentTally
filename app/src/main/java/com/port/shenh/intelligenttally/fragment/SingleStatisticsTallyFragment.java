package com.port.shenh.intelligenttally.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.port.shenh.intelligenttally.R;
import com.port.shenh.intelligenttally.adapter.SingleStatisticsRecyclerViewAdapter;
import com.port.shenh.intelligenttally.bean.SingleStatistics;
import com.port.shenh.intelligenttally.work.PullSingleStatisticsTally;

import org.mobile.library.model.work.DefaultWorkModel;
import org.mobile.library.model.work.WorkBack;

import java.util.List;

/**
 * 个统计理货员布局
 *
 * @author shenh
 * @version 1.0 2017/3/29
 * @since 1.0 2017/3/29
 */
public class SingleStatisticsTallyFragment extends Fragment {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "SingleStatisticsTallyFragment.";

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
         * 理货员统计数据适配器
         */
        public SingleStatisticsRecyclerViewAdapter recyclerViewAdapter = null;

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
         * 航次
         */
        public String ship_id = null;

        /**
         * 进出口编码
         */
        public String code_inout = null;
    }

    /**
     * 控件集对象
     */
    private LocalViewHolder viewHolder = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_statistics_single_tally, container, false);

        // 初始化控件引用
        initViewHolder();

        initView(view);

        return view;
    }

    /**
     * 初始化控件引用
     */
    private void initViewHolder() {

        viewHolder = new LocalViewHolder();

        // 堆存列表适配器
        viewHolder.recyclerViewAdapter = new SingleStatisticsRecyclerViewAdapter();

    }


    /**
     * 初始化布局
     *
     * @param view 根布局
     */
    private void initView(View view) {

        // 初始化列表
        initListView(view);

        //初始化数据
        initData();

    }


    /**
     * 初始化列表
     */
    private void initListView(View view) {

        Log.i(LOG_TAG + "initListView", "initListView is invoked");

        // RecyclerView列表对象
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id
                .fragment_statistics_single_tally_recyclerView);

        // 设置item动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // 创建布局管理器
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        // 设置列表适配器
        recyclerView.setAdapter(viewHolder.recyclerViewAdapter);

        Log.i(LOG_TAG + "initListView", "initListView2 is invoked");

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

        // 理货员统计列表任务
        PullSingleStatisticsTally pullSingleStatisticsTally = new PullSingleStatisticsTally();

        pullSingleStatisticsTally.setWorkEndListener(new WorkBack<List<SingleStatistics>>() {
            @Override
            public void doEndWork(boolean state, List<SingleStatistics> data) {
                if (state && data != null) {

                    // 插入新数据
                    viewHolder.recyclerViewAdapter.addData(viewHolder.recyclerViewAdapter
                            .getItemCount(), data);

                    if (data.size() == ROW_COUNT) {
                        // 取到了预期条数的数据
                        viewHolder.hasMoreData = true;
                    }
                }

                // 改变请求状态
                viewHolder.loading = false;
            }
        });

        // 改变请求状态
        viewHolder.loading = true;
        // 初始化更多预期
        viewHolder.hasMoreData = false;

        // 执行任务
        pullSingleStatisticsTally.beginExecute(String.valueOf(viewHolder.recyclerViewAdapter
                .getItemCount()), String.valueOf(ROW_COUNT), viewHolder.ship_id, viewHolder
                .code_inout);

        // 保存新的加载任务对象
        viewHolder.beforeLoadWork = pullSingleStatisticsTally;
    }

    /**
     * 设置航次数据
     *
     * @param ship_id 贝数据
     */
    public void setShipId(String ship_id) {
        viewHolder.ship_id = ship_id;
    }

    /**
     * 设置进出口编码数据
     *
     * @param code_inout 贝数据
     */
    public void setCodeInout(String code_inout) {
        viewHolder.code_inout = code_inout;
    }


}

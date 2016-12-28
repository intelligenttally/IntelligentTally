package com.port.shenh.intelligenttally.activity;
/**
 * Created by sh on 2016/11/15.
 */

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;

import com.port.shenh.intelligenttally.R;
import com.port.shenh.intelligenttally.adapter.BayNumRecyclerViewAdapter;
import com.port.shenh.intelligenttally.function.ShipImageListFunction;
import com.port.shenh.intelligenttally.holder.BayNumItemViewHolder;
import com.port.shenh.intelligenttally.util.StaticValue;

import org.mobile.library.common.function.ToolbarInitialize;
import org.mobile.library.model.operate.OnItemClickListenerForRecyclerViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 贝位号选择Activity
 *
 * @author sh
 * @version 1.0 2016/12/17
 * @since 1.0
 */
public class BayNumSelectActivity extends AppCompatActivity {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "BayNumSelectActivity1.";

    /**
     * 控件集
     */
    private class LocalViewHolder {

        /**
         * 堆存列表数据适配器
         */
        public BayNumRecyclerViewAdapter recyclerViewAdapter = null;

        /**
         * 表示是否正在加载
         */
        public volatile boolean loading = false;

        /**
         * 船图数据功能类
         */
        public ShipImageListFunction shipImageListFunction = null;

        /**
         * 航次
         */
        String ship_id = null;
    }

    /**
     * 控件集对象
     */
    private LocalViewHolder viewHolder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baynum_select);

        // 初始化控件引用
        initViewHolder();
        // 加载界面
        initView();
    }

    /**
     * 初始化控件引用
     */
    private void initViewHolder() {
        viewHolder = new LocalViewHolder();

        // 堆存列表适配器
        viewHolder.recyclerViewAdapter = new BayNumRecyclerViewAdapter();

        viewHolder.shipImageListFunction = new ShipImageListFunction(this);

        viewHolder.ship_id = (String) getIntent().getSerializableExtra(StaticValue.IntentTag
                .BAYNUM_SELECT_TAG);
        Log.i(LOG_TAG + " initViewAdapter", "ship_d is " + viewHolder.ship_id);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        // 初始化Toolbar
        ToolbarInitialize.initToolbar(this, R.string.baynum, true, true);
        // 初始化列表
        initListView();
    }

    /**
     * 初始化列表
     */
    private void initListView() {

        // RecyclerView列表对象
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id
                .activity_baynum_select_recyclerView);

        // 设置item动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // 创建布局管理器
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        if (viewHolder.shipImageListFunction != null && viewHolder.ship_id != null) {

            List<String> dataList;

            if (viewHolder.shipImageListFunction.onLoadBayNumListFromDataBase(viewHolder.ship_id)
                    != null) {
                dataList = new ArrayList<>(viewHolder.shipImageListFunction
                        .onLoadBayNumListFromDataBase(viewHolder.ship_id));
            } else {
                dataList = new ArrayList<>();
            }

            Log.i(LOG_TAG + "loadData", "dataList count is " + dataList.size());

            viewHolder.recyclerViewAdapter = new BayNumRecyclerViewAdapter(dataList);

            // 设置列表适配器
            recyclerView.setAdapter(viewHolder.recyclerViewAdapter);

            // 绑定点击事件
            viewHolder.recyclerViewAdapter.setOnItemClickListener(new OnItemClickListenerForRecyclerViewItem<List<String>, BayNumItemViewHolder>() {
                @Override
                public void onClick(List<String> dataSource, BayNumItemViewHolder holder) {

                    int position = holder.getAdapterPosition();

                    viewHolder.recyclerViewAdapter.switchSelectedState(holder);

                    // 跳转意图
                    Intent intent = new Intent(BayNumSelectActivity.this, BayActivity.class);
                    intent.putExtra(StaticValue.IntentTag.BAYNUM_SELECT_TAG, position);
                    intent.putExtra(StaticValue.IntentTag.VOYAGE_TAG, viewHolder.ship_id);

                    // 跳转到详情页面
                    startActivity(intent);
                }
            });

        }
    }
}

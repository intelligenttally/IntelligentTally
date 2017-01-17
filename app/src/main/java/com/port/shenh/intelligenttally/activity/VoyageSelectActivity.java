package com.port.shenh.intelligenttally.activity;
/**
 * Created by sh on 2016/11/15.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.port.shenh.intelligenttally.R;
import com.port.shenh.intelligenttally.adapter.VoyageOfInPortRecyclerViewAdapter;
import com.port.shenh.intelligenttally.adapter.VoyageRecyclerViewAdapter;
import com.port.shenh.intelligenttally.bean.Voyage;
import com.port.shenh.intelligenttally.function.ShipImageListFunction;
import com.port.shenh.intelligenttally.function.VoyageListFunction;
import com.port.shenh.intelligenttally.holder.VoyageItemViewHolder;
import com.port.shenh.intelligenttally.util.StaticValue;

import org.mobile.library.common.function.ToolbarInitialize;
import org.mobile.library.model.operate.OnItemClickListenerForRecyclerViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 航次选择Activity
 *
 * @author sh
 * @version 1.0 2016/11/15
 * @since 1.0
 */
public class VoyageSelectActivity extends AppCompatActivity {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "VoyageSelectActivity.";


    /**
     * 控件集
     */
    private class LocalViewHolder {

        /**
         * 堆存列表数据适配器
         */
        public VoyageRecyclerViewAdapter recyclerViewAdapter = null;


        /**
         * 航次列表数据功能类
         */
        public VoyageListFunction voyageListFunction = null;

        /**
         * 船图数据功能类
         */
        public ShipImageListFunction shipImageListFunction = null;
    }

    /**
     * 控件集对象
     */
    private LocalViewHolder viewHolder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voyage_select);

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

        viewHolder.shipImageListFunction = new ShipImageListFunction(this);

        viewHolder.voyageListFunction = new VoyageListFunction(this);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        // 初始化Toolbar
        ToolbarInitialize.initToolbar(this, R.string.vayage_downloaded, true, true);
        // 初始化列表
        initListView();
    }

    /**
     * 初始化列表
     */
    private void initListView() {

        // RecyclerView列表对象
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.activity_voyage_download_recyclerView);

        // 设置item动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // 创建布局管理器
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        if (viewHolder.shipImageListFunction != null) {

            List<Voyage> dataList;

            if (viewHolder.voyageListFunction.onLoadVoyageListFromDataBase()
                    != null) {
                dataList = new ArrayList<>(viewHolder.voyageListFunction
                        .onLoadVoyageListFromDataBase());
            } else {
                dataList = new ArrayList<>();
            }

            Log.i(LOG_TAG + "loadData", "dataList count is " + dataList.size());

            viewHolder.recyclerViewAdapter = new VoyageRecyclerViewAdapter(dataList);


            // 设置列表适配器
            recyclerView.setAdapter(viewHolder.recyclerViewAdapter);

            // 设置点击事件
            viewHolder.recyclerViewAdapter.setOnItemClickListener(new OnItemClickListenerForRecyclerViewItem<List<Voyage>, VoyageItemViewHolder>() {
                @Override
                public void onClick(List<Voyage> voyages, VoyageItemViewHolder voyageItemViewHolder) {

                    Voyage voyage = voyages.get(voyageItemViewHolder.getAdapterPosition());

                    viewHolder.recyclerViewAdapter.switchSelectedState(voyageItemViewHolder);

                    // 跳转意图
                    Intent intent = new Intent(VoyageSelectActivity.this, BayNumSelectActivity.class);
                    intent.putExtra(StaticValue.IntentTag.BAYNUM_SELECT_TAG, voyage.getShip_id());
                    // 跳转到详情页面
                    startActivity(intent);
                }
            });

        }
    }
}

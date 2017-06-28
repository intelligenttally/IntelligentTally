package com.port.shenh.intelligenttally.function;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.port.shenh.intelligenttally.R;
import com.port.shenh.intelligenttally.adapter.VoyageRecyclerViewAdapter;
import com.port.shenh.intelligenttally.bean.Voyage;
import com.port.shenh.intelligenttally.holder.VoyageItemViewHolder;

import org.mobile.library.model.function.ISelectList;
import org.mobile.library.model.operate.OnItemClickListenerForRecyclerViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 已下载航次选择列表描述
 *
 * @author shenh
 * @version 1.0 2017/4/24
 * @since 1.0 2017/4/24
 */
public class VoyageDownloadedSelectList implements ISelectList<View, Voyage> {

    /**
     * 日志前缀
     */
    private static final String LOG_TAG = "VoyageDownloadedSelectList.";

    /**
     * 上下文
     */
    private Context context = null;

    /**
     * 结果监听器
     */
    private OnSelectedListener<View, Voyage> onSelectedListener = null;

    /**
     * 当前的选择布局
     */
    private View view = null;


    public VoyageDownloadedSelectList(Context context) {

        this.context = context;

        // 初始化控件引用
        initViewHolder();
    }


    /**
     * 航次列表数据功能类
     */
    public VoyageListFunction voyageListFunction = null;

    /**
     * 船图数据功能类
     */
    public ShipImageListFunction shipImageListFunction = null;



    @Override
    public void setOnSelectedListener(OnSelectedListener<View, Voyage> onSelectedListener) {

        this.onSelectedListener = onSelectedListener;
    }

    @Override
    public View loadSelect() {

        if (view == null) {
            view = onCreateView();
        }
        return view;
    }

    /**
     * 初始化控件引用
     */
    private void initViewHolder() {

        shipImageListFunction = new ShipImageListFunction(this.context);

        voyageListFunction = new VoyageListFunction(this.context);
    }

    /**
     * 创建布局
     */
    private View onCreateView() {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_voyage_downloaded, null);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id
                .activity_voyage_downloaded_recyclerView);

        // 创建布局管理器
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);

        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);


        if (shipImageListFunction != null) {


            final List<Voyage> dataList;


            if (voyageListFunction.onLoadVoyageListFromDataBase()
                    != null) {
                dataList = new ArrayList<>(voyageListFunction
                        .onLoadVoyageListFromDataBase());
            } else {
                dataList = new ArrayList<>();
            }

            Log.i(LOG_TAG + "loadData", "dataList count is " + dataList.size());

            VoyageRecyclerViewAdapter adapter = new VoyageRecyclerViewAdapter(dataList);

            if (onSelectedListener != null) {

                adapter.setOnItemClickListener(new OnItemClickListenerForRecyclerViewItem<List<Voyage>, VoyageItemViewHolder>() {


                    @Override
                    public void onClick(List<Voyage> dataSource, VoyageItemViewHolder holder) {

                        Voyage voyage = dataSource.get(holder.getAdapterPosition());

                        Log.i(LOG_TAG + "loadData", "position is " + holder.getAdapterPosition());

                        onSelectedListener.onFinish(voyage);
                    }
                });

            }

            recyclerView.setAdapter(adapter);
        }

        return view;

        }
}

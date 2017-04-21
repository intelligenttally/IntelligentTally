package com.port.shenh.intelligenttally.function;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.port.shenh.intelligenttally.R;
import com.port.shenh.intelligenttally.adapter.OnlyTextItemViewHolder;
import com.port.shenh.intelligenttally.adapter.OnlyTextRecyclerViewAdapter;

import org.mobile.library.model.function.ISelectList;
import org.mobile.library.model.operate.OnItemClickListenerForRecyclerViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 贝号选择列表
 *
 * @author shenh
 * @version 1.0 2017/4/19
 * @since 1.0 2017/4/19
 */
public class BayNumSelectList implements ISelectList<View, String> {

    /**
     * 日志前缀
     */
    private static final String LOG_TAG = "BayNumSelectList.";

    /**
     * 结果监听器
     */
    private OnSelectedListener<View, String> onSelectedListener = null;

    /**
     * 上下文
     */
    private Context context = null;

    /**
     * 当前的选择布局
     */
    private View view = null;

    /**
     * 船图数据功能类
     */
    public ShipImageListFunction shipImageListFunction = null;

    /**
     * 航次
     */
    String ship_id = null;



    public BayNumSelectList(Context context, String ship_id) {
        this.context = context;

        this.ship_id = ship_id;

        shipImageListFunction = new ShipImageListFunction(context);
    }




    @Override
    public void setOnSelectedListener(OnSelectedListener<View, String> onSelectedListener) {

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
     * 创建布局
     *
     * @return 布局实例
     */
    private View onCreateView() {
        View view = LayoutInflater.from(context).inflate(R.layout.only_recycler_view_layout, null);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id
                .only_recycler_view_layout_recyclerView);

        // 创建布局管理器
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);

        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);


        if (shipImageListFunction != null && ship_id != null) {

            List<String> dataList;

            if (shipImageListFunction.onLoadBayNumListFromDataBase(ship_id)
                    != null) {
                dataList = new ArrayList<>(shipImageListFunction
                        .onLoadBayNumListFromDataBase(ship_id));
            } else {
                dataList = new ArrayList<>();
            }

            Log.i(LOG_TAG + "loadData", "dataList count is " + dataList.size());

            OnlyTextRecyclerViewAdapter adapter = new OnlyTextRecyclerViewAdapter(dataList);

            if (onSelectedListener != null) {
                // 绑定点击事件
                adapter.setOnItemClickListener(new OnItemClickListenerForRecyclerViewItem<List<String>, OnlyTextItemViewHolder>() {
                    @Override
                    public void onClick(List<String> dataSource, OnlyTextItemViewHolder holder) {

                        int position = holder.getAdapterPosition();

                        Log.i(LOG_TAG + "loadData", "position is " + position);

                        onSelectedListener.onFinish(Integer.toString(position));
                    }
                });
            }

            recyclerView.setAdapter(adapter);
        }

        return view;
    }
}

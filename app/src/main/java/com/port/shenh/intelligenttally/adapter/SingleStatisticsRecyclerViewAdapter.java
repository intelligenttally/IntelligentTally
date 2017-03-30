package com.port.shenh.intelligenttally.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.port.shenh.intelligenttally.R;
import com.port.shenh.intelligenttally.bean.SingleStatistics;
import com.port.shenh.intelligenttally.holder.SingleStatisticsViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 个统计列表数据适配器
 *
 * @author shenh
 * @version 1.0 2017/3/29
 * @since 1.0 2017/3/29
 */
public class SingleStatisticsRecyclerViewAdapter extends RecyclerView.Adapter<SingleStatisticsViewHolder> {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "SingleStatisticsRecyclerViewAdapter.";

    /**
     * 数据源
     */
    private List<SingleStatistics> dataList = null;


    public SingleStatisticsRecyclerViewAdapter() {
        this.dataList = new ArrayList<>();
    }


    /**
     * 添加一组数据
     *
     * @param position 添加位置
     * @param data     数组
     */
    public void addData(int position, List<SingleStatistics> data) {
        this.dataList.addAll(position, data);
        notifyItemRangeInserted(position, data.size());
    }

    /**
     * 重置数据
     *
     * @param data 新数据
     */
    public void reset(List<SingleStatistics> data) {
        this.dataList.clear();
        if (data != null) {
            this.dataList.addAll(data);
        }
        notifyDataSetChanged();
    }

    /**
     * 清空数据
     */
    public void clear() {

        Log.i(LOG_TAG + "clear", "is invoked");
        if (dataList.size() > 0) {
            int count = dataList.size();
            dataList.clear();
            notifyItemRangeRemoved(0, count);
        }
    }

    @Override
    public SingleStatisticsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // 创建Item根布局
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_statistics_list_item
                , parent, false);

        // 创建Item布局管理器
        return new SingleStatisticsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SingleStatisticsViewHolder holder, int position) {

        SingleStatistics singleStatistics = this.dataList.get(position);
        holder.nameTextView.setText(Integer.toString(singleStatistics.getName()));
        holder.E_20_TextView.setText(Integer.toString(singleStatistics.getE_20()));
        holder.F_20_TextView.setText(Integer.toString(singleStatistics.getF_20()));
        holder.E_40_TextView.setText(Integer.toString(singleStatistics.getE_40()));
        holder.F_40_TextView.setText(Integer.toString(singleStatistics.getF_40()));
        holder.E_other_TextView.setText(Integer.toString(singleStatistics.getE_other()));
        holder.F_other_TextView.setText(Integer.toString(singleStatistics.getF_other()));

    }

    @Override
    public int getItemCount() {

        return dataList.size();
    }
}

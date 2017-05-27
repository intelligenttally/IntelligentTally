package com.port.shenh.intelligenttally.adapter;
/**
 * Created by 超悟空 on 2016/3/16.
 */

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.port.shenh.intelligenttally.R;
import com.port.shenh.intelligenttally.holder.BayNumItemViewHolder;

import org.mobile.library.model.operate.OnItemClickListenerForRecyclerViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表项仅包含文本框的列表适配器
 *
 * @author 超悟空
 * @version 1.0 2016/3/16
 * @since 1.0
 */
public class BayNumRecyclerViewAdapter extends RecyclerView.Adapter<BayNumItemViewHolder> {

    /**
     * 数据源列表
     */
    private List<String> dataList = null;

    /**
     * 记录选中的position
     */
    private SparseBooleanArray selectedItems;

    /**
     * Item点击事件监听器
     */
    private OnItemClickListenerForRecyclerViewItem<List<String>, BayNumItemViewHolder>
            onItemClickListener = null;

    /**
     * 构造函数
     */
    public BayNumRecyclerViewAdapter() {

        this.dataList = new ArrayList<>();
        this.selectedItems = new SparseBooleanArray();
    }

    /**
     * 构造函数
     *
     * @param dataList 数据源
     */
    public BayNumRecyclerViewAdapter(List<String> dataList) {

        this.dataList = dataList;
        this.selectedItems = new SparseBooleanArray();
    }

    /**
     * 设置数据源
     *
     * @param dataList 数据源列表
     */
    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
    }

    /**
     * 设置列表项点击事件监听器
     *
     * @param onItemClickListener 监听器
     */
    public void setOnItemClickListener(OnItemClickListenerForRecyclerViewItem<List<String>,
            BayNumItemViewHolder> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 在指定位置添加一条数据
     *
     * @param position 添加位置
     * @param data     数据
     */
    public void add(int position, String data) {
        this.dataList.add(position, data);
        this.notifyItemInserted(position);
    }

    /**
     * 在指定位置添加一组数据
     *
     * @param position 添加位置
     * @param dataList 数据集
     */
    public void addAll(int position, List<String> dataList) {
        this.dataList.addAll(position, dataList);
        this.notifyItemRangeInserted(position, dataList.size());
    }

    /**
     * 移除一条数据
     *
     * @param position 移除位置
     */
    public void remove(int position) {
        this.dataList.remove(position);
        this.notifyItemRemoved(position);
    }

    /**
     * 移除一条数据
     *
     * @param position 移除位置
     * @param count    移除数量
     */
    public void remove(int position, int count) {
        for (int i = 0; i < count; i++) {
            this.dataList.remove(position + i);
        }
        this.notifyItemRangeRemoved(position, count);
    }

    /**
     * 切换选择状态
     *
     * @param bayNumItemViewHolder 贝位列表的ViewHolder
     */
    public void switchSelectedState(BayNumItemViewHolder bayNumItemViewHolder) {

        int position = bayNumItemViewHolder.getLayoutPosition();

        selectedItems.clear();
        selectedItems.put(position, true);
        notifyDataSetChanged();
    }


    @Override
    public BayNumItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 创建Item根布局
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .baynum_list_item, parent, false);

        // 创建Item布局管理器
        return new BayNumItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final BayNumItemViewHolder holder, int position) {
        holder.textView.setText(dataList.get(position));

        int color = 0;
        if (selectedItems.get(position, false)) {
            color = Color.parseColor("#00008B");
        } else {
            color = Color.parseColor("#FFFFFF");
        }
        holder.itemView.setBackgroundColor(color);

        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(dataList, holder);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

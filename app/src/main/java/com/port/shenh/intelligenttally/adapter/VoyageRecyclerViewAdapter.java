package com.port.shenh.intelligenttally.adapter;
/**
 * Created by sh on 2016/11/23.
 */

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.port.shenh.intelligenttally.R;
import com.port.shenh.intelligenttally.bean.Voyage;
import com.port.shenh.intelligenttally.holder.VoyageItemViewHolder;

import org.mobile.library.model.operate.OnItemClickListenerForRecyclerViewItem;

import java.util.ArrayList;
import java.util.List;


/**
 * 已下载航次列表数据适配器
 *
 * @author sh
 * @version 1.0 2016/1/15
 * @since 1.0
 */
public class VoyageRecyclerViewAdapter extends RecyclerView.Adapter<VoyageItemViewHolder> {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "VoyageRecyclerViewAdapter.";

    /**
     * 数据源
     */
    private List<Voyage> dataList = null;

    /**
     * 记录选中的position
     */
    private SparseBooleanArray selectedItems;


    /**
     * item点击事件监听器
     */
    private OnItemClickListenerForRecyclerViewItem<List<Voyage>, VoyageItemViewHolder>
            onItemClickListener = null;

    /**
     * 构造函数
     */
    public VoyageRecyclerViewAdapter() {

        this.dataList = new ArrayList<>();
        this.selectedItems = new SparseBooleanArray();


    }

    /**
     * 构造函数
     *
     * @param dataList 初始数据源
     */
    public VoyageRecyclerViewAdapter(List<Voyage> dataList) {
        this.dataList = dataList;
        this.selectedItems = new SparseBooleanArray();

    }

    /**
     * 添加一组数据
     *
     * @param position 添加位置
     * @param data     数组
     */
    public void addData(int position, List<Voyage> data) {
        this.dataList.addAll(position, data);
        notifyItemRangeInserted(position, data.size());
    }

    /**
     * 重置数据
     *
     * @param data 新数据
     */
    public void reset(List<Voyage> data) {
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

    /**
     * 切换选择状态
     *
     * @param voyageItemViewHolder 航次列表的ViewHolder
     */
    public void switchSelectedState(VoyageItemViewHolder voyageItemViewHolder) {

        int position = voyageItemViewHolder.getLayoutPosition();

        selectedItems.clear();
        selectedItems.put(position, true);
        notifyDataSetChanged();
    }

    /**
     * 获取选中item数目
     */
    public int getSelectedItemCount() {
        return selectedItems.size();
    }

    /**
     * 获取选中的Items
     *
     * @return Items
     */
    public Integer getSelectedItem() {

        if (selectedItems.size() == 0) {
            return null;
        }

        return selectedItems.keyAt(0);
    }

    /**
     * 获取选中的数据
     *
     * @return 获取选中的数据
     */
    public Voyage getSelectedData() {

        if (selectedItems.size() == 0) {
            return null;
        }

        Voyage selectedData = dataList.get(getSelectedItem());

        return selectedData;
    }


    /**
     * 设置Item点击事件监听器
     *
     * @param onItemClickListener 监听器
     */
    public void setOnItemClickListener(OnItemClickListenerForRecyclerViewItem<List<Voyage>,
            VoyageItemViewHolder> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public VoyageItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 创建Item根布局
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.voyage_list_item
                , parent, false);

        // 创建Item布局管理器
        return new VoyageItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final VoyageItemViewHolder holder, int position) {

        // 数据绑定
        Voyage voyage = this.dataList.get(position);
        holder.berthnoTextView.setText(voyage.getBerthno());
        holder.inOutTextView.setText(voyage.getCodeInOut().equals("1") == true ? "出" : "进");
        holder.voyageTextView.setText(voyage.getVoyage());
        holder.chi_VesselTextView.setText(voyage.getChi_vessel());

        String dstr=voyage.getDownloadTime();
        holder.downloadedTextView.setText(dstr.substring(5, 10));

        int color = 0;
        if (selectedItems.get(position, false)) {
            color = Color.parseColor("#E0FFFF");
        } else {
            color = Color.parseColor("#FFFFFF");
        }
        holder.itemView.setBackgroundColor(color);

        // 绑定监听事件
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
        return this.dataList.size();
    }
}

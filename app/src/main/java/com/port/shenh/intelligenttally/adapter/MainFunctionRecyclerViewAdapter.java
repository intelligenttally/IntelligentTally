package com.port.shenh.intelligenttally.adapter;
/**
 * Created by 超悟空 on 2015/9/19.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.port.shenh.intelligenttally.R;
import com.port.shenh.intelligenttally.function.ShipImageListFunction;
import com.port.shenh.intelligenttally.holder.MainFunctionItemViewHolder;

import org.mobile.library.model.operate.OnItemClickListenerForRecyclerViewItem;


/**
 * 主界面功能列表数据适配器
 *
 * @author 超悟空
 * @version 1.0 2015/10/20
 * @since 1.0
 */
public class MainFunctionRecyclerViewAdapter extends RecyclerView
        .Adapter<MainFunctionItemViewHolder> {

    /**
     * 功能名称数组
     */
    private String[] functionTitle = null;

    /**
     * 资源类型数组
     */
    private TypedArray images = null;

    /**
     * 船图数据功能类
     */
    public ShipImageListFunction shipImageListFunction = null;


    /**
     * item点击事件监听器
     */
    private OnItemClickListenerForRecyclerViewItem<String[], MainFunctionItemViewHolder>
            onItemClickListener = null;

    /**
     * 构造函数
     */
    public MainFunctionRecyclerViewAdapter(Context context) {
        // 加载标题
        functionTitle = context.getResources().getStringArray(R.array.grid_item_function_name_list);
        // 加载图标
        images = context.getResources().obtainTypedArray(R.array.grid_item_function_image_list);
        //加载船图数据
        shipImageListFunction = new ShipImageListFunction(context);
    }

    /**
     * 设置Item点击事件监听器
     *
     * @param onItemClickListener 监听器
     */
    public void setOnItemClickListener(OnItemClickListenerForRecyclerViewItem<String[],
            MainFunctionItemViewHolder> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MainFunctionItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 创建Item根布局
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .function_grid_item, parent, false);

        // 创建Item布局管理器
        return new MainFunctionItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MainFunctionItemViewHolder holder, int position) {

        // 数据绑定
        holder.titleTextView.setText(functionTitle[position]);
        holder.iconImageView.setImageResource(images.getResourceId(position, R.mipmap.ic_launcher));

        if (position == 4 && shipImageListFunction.isExistUploadedVoyage()){
            holder.iconCrossImageView.setImageResource(R.mipmap.cross);
        }

        // 绑定监听事件
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(functionTitle, holder);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.functionTitle.length;
    }
}

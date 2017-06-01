package com.port.shenh.intelligenttally.holder;
/**
 * Created by sh on 2016/11/8.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.port.shenh.intelligenttally.R;

/**
 * 主界面功能列表的ViewHolder
 *
 * @author sh
 * @version 1.0 2016/11/8
 * @since 1.0
 */
public class MainFunctionItemViewHolder extends RecyclerView.ViewHolder {

    /**
     * 叉型图标
     */
    public ImageView iconCrossImageView = null;

    /**
     * 功能图标
     */
    public ImageView iconImageView = null;

    /**
     * 功能标题
     */
    public TextView titleTextView = null;

    public MainFunctionItemViewHolder(View itemView) {
        super(itemView);

        iconCrossImageView = (ImageView) itemView.findViewById(R.id.function_grid_item_cross_image);

        iconImageView = (ImageView) itemView.findViewById(R.id.function_grid_item_image);

        titleTextView = (TextView) itemView.findViewById(R.id.function_grid_item_text);
    }
}

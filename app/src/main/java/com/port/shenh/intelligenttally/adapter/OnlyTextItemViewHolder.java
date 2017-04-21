package com.port.shenh.intelligenttally.adapter;
/**
 * Created by 超悟空 on 2016/3/16.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.port.shenh.intelligenttally.R;

/**
 * 仅文本框的Item的View管理器
 *
 * @author 超悟空
 * @version 1.0 2016/3/16
 * @since 1.0
 */
public class OnlyTextItemViewHolder extends RecyclerView.ViewHolder {

    /**
     * 文本框
     */
    public TextView textView = null;

    public OnlyTextItemViewHolder(View itemView) {
        super(itemView);

        textView = (TextView) itemView.findViewById(R.id.only_mini_text_item_textView);
    }
}

package com.port.shenh.intelligenttally.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.port.shenh.intelligenttally.R;

/**
 * 个统计列表的ViewHolder
 *
 * @author shenh
 * @version 1.0 2017/3/29
 * @since 1.0 2017/3/29
 */
public class SingleStatisticsViewHolder extends RecyclerView.ViewHolder {

    /**
     * 姓名文本框
     */
    public TextView nameTextView = null;

    /**
     * 20空文本框
     */
    public TextView E_20_TextView = null;

    /**
     * 20重文本框
     */
    public TextView F_20_TextView = null;

    /**
     * 40空文本框
     */
    public TextView E_40_TextView = null;

    /**
     * 40重文本框
     */
    public TextView F_40_TextView = null;

    /**
     * 其他空文本框
     */
    public TextView E_other_TextView = null;

    /**
     * 其他重文本框
     */
    public TextView F_other_TextView = null;


    public SingleStatisticsViewHolder(View itemView) {
        super(itemView);

        nameTextView = (TextView) itemView.findViewById(R.id
                .single_statistics_list_item_name_textView);

        E_20_TextView = (TextView) itemView.findViewById(R.id
                .single_statistics_list_item_E_20_textView);

        F_20_TextView = (TextView) itemView.findViewById(R.id
                .single_statistics_list_item_F_20_textView);

        E_40_TextView = (TextView) itemView.findViewById(R.id
                .single_statistics_list_item_E_40_textView);

        F_40_TextView = (TextView) itemView.findViewById(R.id
                .single_statistics_list_item_F_40_textView);

        E_other_TextView = (TextView) itemView.findViewById(R.id
                .single_statistics_list_item_E_other_textView);

        F_other_TextView = (TextView) itemView.findViewById(R.id
                .single_statistics_list_item_F_other_textView);
    }
}

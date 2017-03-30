package com.port.shenh.intelligenttally.holder;
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
 * @author sh
 * @version 1.0 2016/3/16
 * @since 1.0
 */
public class BayNumItemViewHolder extends RecyclerView.ViewHolder {

    /**
     * 文本框
     */
    public TextView textView = null;

    public BayNumItemViewHolder(View itemView) {
        super(itemView);

        textView = (TextView) itemView.findViewById(R.id.baynum_list_item_baynum);
    }
}

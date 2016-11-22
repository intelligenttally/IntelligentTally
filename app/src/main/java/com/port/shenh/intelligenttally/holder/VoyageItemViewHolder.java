package com.port.shenh.intelligenttally.holder;
/**
 * Created by sh on 2016/11/15.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.port.shenh.intelligenttally.R;

/**
 * 航次列表的ViewHolder
 *
 * @author sh
 * @version 1.0 2016/11/15
 * @since 1.0
 */
public class VoyageItemViewHolder extends RecyclerView.ViewHolder {

    /**
     * 进出口文本框
     */
    public TextView inOutTextView = null;

    /**
     * 航次文本框
     */
    public TextView voyageTextView = null;

    /**
     * 中文船名文本框
     */
    public TextView chi_VesselTextView = null;

    /**
     * 泊位号文本框
     */
    public TextView berthnoTextView = null;


    public VoyageItemViewHolder(View itemView) {
        super(itemView);

        berthnoTextView = (TextView) itemView.findViewById(R.id.voyage_list_item_berthno_textView);

        inOutTextView = (TextView) itemView.findViewById(R.id.voyage_list_item_inout_textView);

        voyageTextView = (TextView) itemView.findViewById(R.id.voyage_list_item_voyage_textView);

        chi_VesselTextView = (TextView) itemView.findViewById(R.id.voyage_list_item_chi_Vessel_textView);
    }
}

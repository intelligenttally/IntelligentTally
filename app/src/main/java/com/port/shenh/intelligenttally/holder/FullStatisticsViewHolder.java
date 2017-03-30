package com.port.shenh.intelligenttally.holder;

import android.view.View;
import android.widget.TextView;

import com.port.shenh.intelligenttally.R;

/**
 * 全统计ViewHolder
 *
 * @author shenh
 * @version 1.0 2017/3/24
 * @since 1.0 2017/3/24
 */
public class FullStatisticsViewHolder {

    /**
     * 预配合计文本框
     */
    public TextView forecast_totalTextView = null;

    /**
     * 预配空20文本框
     */
    public TextView forecast_E_20TextView = null;

    /**
     * 预配空40文本框
     */
    public TextView forecast_E_40TextView = null;

    /**
     * 预配空其他文本框
     */
    public TextView forecast_E_otherTextView = null;

    /**
     * 预配空合计文本框
     */
    public TextView forecast_E_totalTextView = null;

    /**
     * 预配重20文本框
     */
    public TextView forecast_F_20TextView = null;

    /**
     * 预配重40文本框
     */
    public TextView forecast_F_40TextView = null;

    /**
     * 预配重其他文本框
     */
    public TextView forecast_F_otherTextView = null;

    /**
     * 预配重合计文本框
     */
    public TextView forecast_F_totalTextView = null;



    /**
     * 理货合计文本框
     */
    public TextView tally_totalTextView = null;

    /**
     * 理货空20文本框
     */
    public TextView tally_E_20TextView = null;

    /**
     * 理货空40文本框
     */
    public TextView tally_E_40TextView = null;

    /**
     * 理货空其他文本框
     */
    public TextView tally_E_otherTextView = null;

    /**
     * 理货空合计文本框
     */
    public TextView tally_E_totalTextView = null;

    /**
     * 理货重20文本框
     */
    public TextView tally_F_20TextView = null;

    /**
     * 理货重40文本框
     */
    public TextView tally_F_40TextView = null;

    /**
     * 理货重其他文本框
     */
    public TextView tally_F_otherTextView = null;

    /**
     * 理货重合计文本框
     */
    public TextView tally_F_totalTextView = null;



    /**
     * 异常合计文本框
     */
    public TextView abnormal_totalTextView = null;

    /**
     * 异常空20文本框
     */
    public TextView abnormal_E_20TextView = null;

    /**
     * 异常空40文本框
     */
    public TextView abnormal_E_40TextView = null;

    /**
     * 异常空其他文本框
     */
    public TextView abnormal_E_otherTextView = null;

    /**
     * 异常空合计文本框
     */
    public TextView abnormal_E_totalTextView = null;

    /**
     * 异常重20文本框
     */
    public TextView abnormal_F_20TextView = null;

    /**
     * 异常重40文本框
     */
    public TextView abnormal_F_40TextView = null;

    /**
     * 异常重其他文本框
     */
    public TextView abnormal_F_otherTextView = null;

    /**
     * 异常重合计文本框
     */
    public TextView abnormal_F_totalTextView = null;

    public FullStatisticsViewHolder(View itemView){

        forecast_totalTextView = (TextView) itemView.findViewById(R.id
                .activity_statistics_full_forecast_total_textView);

        forecast_E_20TextView = (TextView) itemView.findViewById(R.id
                .activity_statistics_full_forecast_E_20_textView);

        forecast_E_40TextView = (TextView) itemView.findViewById(R.id
                .activity_statistics_full_forecast_E_40_textView);

        forecast_E_otherTextView = (TextView) itemView.findViewById(R.id
                .activity_statistics_full_forecast_E_other_textView);

        forecast_E_totalTextView = (TextView) itemView.findViewById(R.id
                .activity_statistics_full_forecast_E_total_textView);

        forecast_F_20TextView = (TextView) itemView.findViewById(R.id
                .activity_statistics_full_forecast_F_20_textView);

        forecast_F_40TextView = (TextView) itemView.findViewById(R.id
                .activity_statistics_full_forecast_F_40_textView);

        forecast_F_otherTextView = (TextView) itemView.findViewById(R.id
                .activity_statistics_full_forecast_F_other_textView);

        forecast_F_totalTextView = (TextView) itemView.findViewById(R.id
                .activity_statistics_full_forecast_F_total_textView);



        tally_totalTextView = (TextView) itemView.findViewById(R.id
                .activity_statistics_full_tally_total_textView);

        tally_E_20TextView = (TextView) itemView.findViewById(R.id
                .activity_statistics_full_tally_E_20_textView);

        tally_E_40TextView = (TextView) itemView.findViewById(R.id
                .activity_statistics_full_tally_E_40_textView);

        tally_E_otherTextView = (TextView) itemView.findViewById(R.id
                .activity_statistics_full_tally_E_other_textView);

        tally_E_totalTextView = (TextView) itemView.findViewById(R.id
                .activity_statistics_full_tally_E_total_textView);

        tally_F_20TextView = (TextView) itemView.findViewById(R.id
                .activity_statistics_full_tally_F_20_textView);

        tally_F_40TextView = (TextView) itemView.findViewById(R.id
                .activity_statistics_full_tally_F_40_textView);

        tally_F_otherTextView = (TextView) itemView.findViewById(R.id
                .activity_statistics_full_tally_F_other_textView);

        tally_F_totalTextView = (TextView) itemView.findViewById(R.id
                .activity_statistics_full_tally_F_total_textView);



        abnormal_totalTextView = (TextView) itemView.findViewById(R.id
                .activity_statistics_full_abnormal_total_textView);

        abnormal_E_20TextView = (TextView) itemView.findViewById(R.id
                .activity_statistics_full_abnormal_E_20_textView);

        abnormal_E_40TextView = (TextView) itemView.findViewById(R.id
                .activity_statistics_full_abnormal_E_40_textView);

        abnormal_E_otherTextView = (TextView) itemView.findViewById(R.id
                .activity_statistics_full_abnormal_E_other_textView);

        abnormal_E_totalTextView = (TextView) itemView.findViewById(R.id
                .activity_statistics_full_abnormal_E_total_textView);

        abnormal_F_20TextView = (TextView) itemView.findViewById(R.id
                .activity_statistics_full_abnormal_F_20_textView);

        abnormal_F_40TextView = (TextView) itemView.findViewById(R.id
                .activity_statistics_full_abnormal_F_40_textView);

        abnormal_F_otherTextView = (TextView) itemView.findViewById(R.id
                .activity_statistics_full_abnormal_F_other_textView);

        abnormal_F_totalTextView = (TextView) itemView.findViewById(R.id
                .activity_statistics_full_abnormal_F_total_textView);

    }
}

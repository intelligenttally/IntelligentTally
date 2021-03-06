package com.port.shenh.intelligenttally.adapter;
/**
 * Created by sh on 2016/11/8.
 */

import android.content.Context;
import android.content.Intent;

import com.port.shenh.intelligenttally.activity.FullStatisticsActivity;
import com.port.shenh.intelligenttally.activity.NotUploadedVoyageSelectActivity;
import com.port.shenh.intelligenttally.activity.SingleStatisticsActivity;
import com.port.shenh.intelligenttally.activity.VoyageDownloadActivity;
import com.port.shenh.intelligenttally.activity.VoyageSelectActivity;

/**
 * 主界面功能索引
 *
 * @author sh
 * @version 1.0 2016/11/8
 * @since 1.0
 */
public class FunctionIndex {

    /**
     * 跳转到选中的功能界面
     *
     * @param context  上下文
     * @param position 功能索引位置，从0开始
     */
    public static void toFunction(Context context, int position) {

        // 功能页跳转
        Intent intent = null;

        switch (position) {
            case 0:
                // 航次下载
                intent = new Intent(context, VoyageDownloadActivity.class);
                break;
            case 1:
                // 贝位图
                intent = new Intent(context, VoyageSelectActivity.class);
                break;
            case 2:
                // 个统计
                intent = new Intent(context, SingleStatisticsActivity.class);
                break;
            case 3:
                // 全统计
                intent = new Intent(context, FullStatisticsActivity.class);
                break;
            case 4:
                // 未上传
                intent = new Intent(context, NotUploadedVoyageSelectActivity.class);
                break;


        }

        if (intent != null) {
            // 执行跳转
            context.startActivity(intent);
        }
    }
}

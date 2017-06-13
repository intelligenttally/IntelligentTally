package com.port.shenh.intelligenttally.function;

import android.content.DialogInterface;

/**
 * 单选对话框监听
 *
 * @author shenh
 * @version 1.0 2017/6/6
 * @since 1.0 2017/6/6
 */
public class ChoiceOnClickListener implements DialogInterface.OnClickListener {

    static private int which = 0;
    @Override
    public void onClick(DialogInterface dialogInterface, int which) {
        this.which = which;
    }

    public int getWhich() {
        return which;
    }
}

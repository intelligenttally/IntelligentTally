package com.port.shenh.intelligenttally.util;
import android.app.Application;

import org.mobile.library.global.ApplicationAttribute;
import org.mobile.library.global.Global;

/**
 * Created by shenh on 2016/11/8.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化Global
        Global.init(this);

        //配置系统参数
        ApplicationAttribute.create().requestSign(true).appCode(StaticValue.APP_CODE).appToken(StaticValue.APP_TOKEN).loginUrl(StaticValue.Url.HTTP_LOGIN_URL);
    }
}

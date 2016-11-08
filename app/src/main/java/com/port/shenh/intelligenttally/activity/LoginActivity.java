package com.port.shenh.intelligenttally.activity;
/**
 * Created by sh on 2016/11/7.
 */


import android.content.Intent;
import com.port.shenh.intelligenttally.R;
import org.mobile.library.model.activity.BaseLoginActivity;

/**
 * 用户登录Activity
 *
 * @author sh
 * @version 2.0 2016/11/7
 * @since 1.0
 */

public class LoginActivity extends BaseLoginActivity {


    @Override
    protected int onActivityLoginLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected boolean onLoginSuccess(String message) {

        goMain();
        return false;
    }

    /**
     * 跳转到主界面
     */
    private void goMain() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }


}

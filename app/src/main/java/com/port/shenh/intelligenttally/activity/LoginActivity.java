package com.port.shenh.intelligenttally.activity;
/**
 * Created by sh on 2016/11/7.
 */


import android.content.Intent;
import android.text.TextUtils;

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
    protected boolean checkPassword(String password) {

        if (passwordTextInputLayout != null) {

            if (TextUtils.isEmpty(password)) {
                passwordTextInputLayout.setError(getString(org.mobile.library.R.string
                        .prompt_password_null_error));
                return false;
            }
        }

        return true;
    }

    @Override
    protected boolean checkUserName(String userName) {

        if (userNameTextInputLayout != null) {

            if (TextUtils.isEmpty(userName)) {
                userNameTextInputLayout.setError(getString(org.mobile.library.R.string
                        .prompt_user_name_null));
                return false;
            }

            if (userName.contains(" ")) {
                userNameTextInputLayout.setError(getString(org.mobile.library.R.string
                        .prompt_user_name_blank));
                return false;
            }

            if (userName.length() < 2) {
                userNameTextInputLayout.setError(getString(org.mobile.library.R.string
                        .prompt_user_name_short));
                return false;
            }
        }

        return true;
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

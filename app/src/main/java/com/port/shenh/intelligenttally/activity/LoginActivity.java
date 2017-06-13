package com.port.shenh.intelligenttally.activity;
/**
 * Created by sh on 2016/11/7.
 */


import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.port.shenh.intelligenttally.R;
import com.port.shenh.intelligenttally.function.ChoiceOnClickListener;
import com.port.shenh.intelligenttally.function.HttpIpSet;

import org.mobile.library.global.Global;
import org.mobile.library.model.activity.BaseLoginActivity;

/**
 * 用户登录Activity
 *
 * @author sh
 * @version 2.0 2016/11/7
 * @since 1.0
 */

public class LoginActivity extends BaseLoginActivity {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "LoginActivity.";

    /**
     * 版权文本
     */
    TextView copyrightTextView = null;

    /**
     * 网络设置按钮
     */
//    Button button = null;

    @Override
    protected int onActivityLoginLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initEdit() {
        // 文本框初始化
        userNameEditText = (EditText) findViewById(org.mobile.library.R.id.login_content_layout_user_name_editText);
        passwordEditText = (EditText) findViewById(org.mobile.library.R.id.login_content_layout_password_editText);
        userNameTextInputLayout = (TextInputLayout) findViewById(org.mobile.library.R.id
                .login_content_layout_user_name_textInputLayout);
        passwordTextInputLayout = (TextInputLayout) findViewById(org.mobile.library.R.id
                .login_content_layout_password_textInputLayout);
        copyrightTextView = (TextView) findViewById(R.id.login_content_layout_copyright_textview);

//        button = (Button) findViewById(R.id.login_content_layout_net_button);

        // 绑定错误提示
        onBindEditHint();

        // 尝试填充数据
        if (Global.getApplicationConfig().getUserName() != null) {
            // 填充用户
            userNameEditText.setText(Global.getApplicationConfig().getUserName());
        }

        PackageInfo pi= null;
        try {
            pi = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                doNetSet();
//            }
//        });

        copyrightTextView.setText("Copyright © 2017  版本：v " + pi.versionName);
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

//    @Override
//    protected void onLoginClick() {
//
//        //配置系统参数
//        ApplicationAttribute.create().loginUrl(HttpIpSet.getHttp_ip() + StaticValue.Url
//                .HTTP_LOGIN_URL);
//
//        // 获取用户名和密码
//        String userName = userNameEditText.getText().toString();
//        String password = passwordEditText.getText().toString();
//
//        InputMethodController.CloseInputMethod(this);
//
//        // 判断是否正确输入用户名和密码
//        if (!checkUserName(userName) || !checkPassword(password)) {
//            return;
//        }
//
//        // 打开旋转进度条
//        startProgressDialog();
//        // 执行登录任务
//        onExecuteLogin(userName, password);
//    }

    /**
     * 跳转到主界面
     */
    private void goMain() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }

    /**
     * 网络设置
     */
    private void doNetSet(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setTitle("网络设置");
        final ChoiceOnClickListener choiceListener =
                new ChoiceOnClickListener();
        builder.setSingleChoiceItems(R.array.net, choiceListener.getWhich(), choiceListener);

        DialogInterface.OnClickListener btnListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                int choiceWhich = choiceListener.getWhich();
                Log.i(LOG_TAG + "doNetSet", "choiceWhich is " + choiceWhich);

                HttpIpSet.setHttp_ip(choiceWhich);

                Log.i(LOG_TAG + "doNetSet", "Http_ip is " + HttpIpSet.getHttp_ip());
            }
        };
        builder.setPositiveButton("确定", btnListener).setNegativeButton("取消", null).show();
    }


}

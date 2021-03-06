package com.port.shenh.intelligenttally.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.port.shenh.intelligenttally.R;
import com.port.shenh.intelligenttally.adapter.MainFunctionRecyclerViewAdapter;
import com.port.shenh.intelligenttally.function.ShipImageListFunction;
import com.port.shenh.intelligenttally.function.VoyageListFunction;
import com.port.shenh.intelligenttally.holder.MainFunctionItemViewHolder;

import org.mobile.library.common.function.CheckUpdate;
import org.mobile.library.common.function.ToolbarInitialize;
import org.mobile.library.global.Global;
import org.mobile.library.model.operate.OnItemClickListenerForRecyclerViewItem;

import static com.port.shenh.intelligenttally.adapter.FunctionIndex.toFunction;

/**
 * 主界面
 *
 * @author sh
 * @version 1.0 2016/11/8
 * @since 1.0
 */
public class MainActivity extends AppCompatActivity {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "MainActivity.";

    /**
     * 进度条
     */
    private ProgressDialog progressDialog = null;

    /**
     * 船图
     */
    ShipImageListFunction shipImageListFunction = null;

    /**
     * 航次
     */
    VoyageListFunction voyageListFunction = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化控件引用
        initViewHolder();

        // 加载界面
        initView();
    }

    @Override
    protected void onResume() {

        // 初始化功能布局
        initGridView();

        super.onResume();
    }


    /**
     * 初始化控件
     */
    private void initView() {
        // 初始化Toolbar
        ToolbarInitialize.initToolbar(this, R.string.app_name, true, false);
        TextView titleTextView = (TextView) findViewById(R.id.toolbar_title);
        titleTextView.setText("理货员" + Global.getApplicationConfig().getUserName());

        // 初始化功能布局
        initGridView();

        //执行检查权限
        checkPermission();

        // 执行检查更新
        checkUpdate();

    }

    /**
     * 初始化控件引用
     */
    private void initViewHolder() {

        shipImageListFunction = new ShipImageListFunction(getApplication());
        voyageListFunction = new VoyageListFunction(getApplication());
    }


    /**
     * 检查新版本
     */
    private void checkUpdate() {

        // 新建版本检查工具
        CheckUpdate checkUpdate = new CheckUpdate(this);
        // 执行检查
        checkUpdate.checkInBackground();
    }

    /**
     * 检查权限
     */
    private void checkPermission() {

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission
                .WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(this, R.string.refuse_pmission, Toast.LENGTH_LONG).show();
                }
                break;
            default:
        }
    }

    /**
     * 初始化表格布局
     */
    private void initGridView() {

        // RecyclerView列表对象
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.activity_main_recyclerView);

        // 创建布局管理器
        GridLayoutManager gridlayoutManager = new GridLayoutManager(this, 2);

        // 设置布局管理器
        recyclerView.setLayoutManager(gridlayoutManager);

        // 新建数据适配器
        MainFunctionRecyclerViewAdapter adapter = new MainFunctionRecyclerViewAdapter(this);

        // 设置点击事件
        adapter.setOnItemClickListener(new OnItemClickListenerForRecyclerViewItem<String[],
                MainFunctionItemViewHolder>() {
            @Override
            public void onClick(String[] dataSource, MainFunctionItemViewHolder holder) {
                onGridItemClick(holder.getAdapterPosition());
            }
        });

        // 绑定数据适配器
        recyclerView.setAdapter(adapter);
    }


    /**
     * 表格项点击事件触发操作，
     * 默认触发功能跳转，
     * 并检测登录状态
     *
     * @param position 点击的位置索引
     */
    private void onGridItemClick(int position) {

        if (!Global.getLoginStatus().isLogin()) {
            // 未登录
            // 新建意图,跳转到登录页面
            Intent intent = new Intent(this, LoginActivity.class);
            // 执行跳转
            startActivity(intent);
            finish();
            return;
        }

        // 跳转到功能
        toFunction(this, position);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_logout:
                // 退出操作
                doLogout();
                break;
            case R.id.menu_clear_cache:
                //清楚缓存
                doClearCache();
                break;
        }
        return true;
    }

    /**
     * 退出操作
     */
    private void doLogout() {

        // 清空保存记录
        Global.getApplicationConfig().setPassword("");
        Global.getApplicationConfig().Save();

        shipImageListFunction.onClear();
        voyageListFunction.onClear();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 清除缓存
     */
    private void doClearCache() {

        //        startProgressDialog();
        //        shipImageListFunction.setOnClearEndListener(new ShipImageListFunction
        // .OnClearEndListener() {
        //            @Override
        //            public void OnClearEnd() {
        //                stopProgressDialog();
        //                Toast.makeText(getApplication(), R.string.clear_success, Toast
        // .LENGTH_SHORT).show();
        //            }
        //        });
        shipImageListFunction.onClear();
        voyageListFunction.onClear();

        Toast.makeText(getApplication(), R.string.clear_success, Toast.LENGTH_SHORT).show();

        // 初始化功能布局
        initGridView();
    }

    /**
     * 打开进度条
     */
    protected void startProgressDialog() {

        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            // 设置提醒
            progressDialog.setMessage("清除缓存中....");
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }

    /**
     * 停止进度条
     */
    protected void stopProgressDialog() {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
    }
}

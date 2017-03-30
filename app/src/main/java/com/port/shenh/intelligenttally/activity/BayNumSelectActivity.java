package com.port.shenh.intelligenttally.activity;
/**
 * Created by sh on 2016/11/15.
 */

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.port.shenh.intelligenttally.R;
import com.port.shenh.intelligenttally.adapter.BayNumRecyclerViewAdapter;
import com.port.shenh.intelligenttally.bean.ShipImage;
import com.port.shenh.intelligenttally.function.ShipImageListFunction;
import com.port.shenh.intelligenttally.holder.BayNumItemViewHolder;
import com.port.shenh.intelligenttally.util.StaticValue;
import com.port.shenh.intelligenttally.work.UploadShipImageList;

import org.mobile.library.model.operate.OnItemClickListenerForRecyclerViewItem;
import org.mobile.library.model.work.WorkBack;

import java.util.ArrayList;
import java.util.List;

/**
 * 贝位号选择Activity
 *
 * @author sh
 * @version 1.0 2016/12/17
 * @since 1.0
 */
public class BayNumSelectActivity extends AppCompatActivity {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "BayNumSelectActivity1.";

    /**
     * 控件集
     */
    private class LocalViewHolder {

        /**
         * 堆存列表数据适配器
         */
        public BayNumRecyclerViewAdapter recyclerViewAdapter = null;

        /**
         * 表示是否正在加载
         */
        public volatile boolean loading = false;

        /**
         * 船图数据功能类
         */
        public ShipImageListFunction shipImageListFunction = null;

        /**
         * 航次
         */
        String ship_id = null;
    }

    /**
     * 进度条
     */
    private ProgressDialog progressDialog = null;

    /**
     * 控件集对象
     */
    private LocalViewHolder viewHolder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baynum_select);

        // 初始化控件引用
        initViewHolder();
        // 加载界面
        initView();
    }

    /**
     * 初始化控件引用
     */
    private void initViewHolder() {
        viewHolder = new LocalViewHolder();

        viewHolder.shipImageListFunction = new ShipImageListFunction(this);

        viewHolder.ship_id = (String) getIntent().getSerializableExtra(StaticValue.IntentTag
                .VOYAGE_TAG);
        Log.i(LOG_TAG + " initViewAdapter", "ship_d is " + viewHolder.ship_id);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        //        // 初始化Toolbar
        //        ToolbarInitialize.initToolbar(this, R.string.baynum, true, true);


        Toolbar toolbar = (Toolbar) findViewById(org.mobile.library.R.id.toolbar);

        setSupportActionBar(toolbar);
        setTitle(R.string.baynum);

        TextView title = (TextView) findViewById(org.mobile.library.R.id.toolbar_title);

        if (title != null) {
            title.setVisibility(View.VISIBLE);
            title.setText(R.string.baynum);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (viewHolder.shipImageListFunction.isExistModifyMark(viewHolder.ship_id)) {
                    Toast.makeText(BayNumSelectActivity.this, R.string.not_upload_complete, Toast
                            .LENGTH_SHORT).show();

                    return;
                } else {

                    finish();

                }
            }
        });

        // 初始化列表
        initListView();
    }

    /**
     * 初始化列表
     */
    private void initListView() {

        // RecyclerView列表对象
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id
                .activity_baynum_select_recyclerView);

        // 设置item动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // 创建布局管理器
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        if (viewHolder.shipImageListFunction != null && viewHolder.ship_id != null) {

            List<String> dataList;

            if (viewHolder.shipImageListFunction.onLoadBayNumListFromDataBase(viewHolder.ship_id)
                    != null) {
                dataList = new ArrayList<>(viewHolder.shipImageListFunction
                        .onLoadBayNumListFromDataBase(viewHolder.ship_id));
            } else {
                dataList = new ArrayList<>();
            }

            viewHolder.shipImageListFunction.onLoadCodeUnloadPortSubListFromDataBase(viewHolder
                    .ship_id);

            Log.i(LOG_TAG + "loadData", "dataList count is " + dataList.size());

            viewHolder.recyclerViewAdapter = new BayNumRecyclerViewAdapter(dataList);

            // 设置列表适配器
            recyclerView.setAdapter(viewHolder.recyclerViewAdapter);

            // 绑定点击事件
            viewHolder.recyclerViewAdapter.setOnItemClickListener(new OnItemClickListenerForRecyclerViewItem<List<String>, BayNumItemViewHolder>() {
                @Override
                public void onClick(List<String> dataSource, BayNumItemViewHolder holder) {

                    int position = holder.getAdapterPosition();

                    viewHolder.recyclerViewAdapter.switchSelectedState(holder);

                    // 跳转意图
                    Intent intent = new Intent(BayNumSelectActivity.this, BayActivity.class);
                    intent.putExtra(StaticValue.IntentTag.BAYNUM_SELECT_TAG, position);
                    intent.putExtra(StaticValue.IntentTag.VOYAGE_TAG, viewHolder.ship_id);

                    // 跳转到详情页面
                    startActivity(intent);
                }
            });

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bay_upload, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_upload:
                // 上传
                doUpload();
                break;
        }
        return true;
    }

    /**
     * 上传操作
     */
    private void doUpload() {

        Log.i(LOG_TAG + "doUpload", "doUpload is invoked");

        if(!(viewHolder.ship_id.equals("196") || viewHolder.ship_id.equals("195"))){

            Toast.makeText(this, "暂不支持“新海悦”以外的船舶", Toast
                    .LENGTH_SHORT).show();

            return;

        }

        if (viewHolder.shipImageListFunction.onLoadShipImageListOfModifyFromDataBase(viewHolder
                .ship_id).size() == 0) {

            Toast.makeText(this, R.string.not_move, Toast.LENGTH_SHORT).show();

            return;
        }

        new AlertDialog.Builder(BayNumSelectActivity.this).setTitle("确认").setIcon(android.R
                .drawable.ic_dialog_info).setMessage("是否上传？").setPositiveButton("确定", new
                DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                startProgressDialog();

                UploadShipImageList uploadShipImageList = new UploadShipImageList();

                uploadShipImageList.setWorkEndListener(new WorkBack<Void>() {
                    @Override
                    public void doEndWork(boolean state, Void data) {

                        Log.i(LOG_TAG + "doUpload", "state is " + state);

                        if (state) {

                            viewHolder.shipImageListFunction.onUpdateModifyMark(viewHolder.ship_id);

                            Toast.makeText(getBaseContext(), R.string.upload_success, Toast
                                    .LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getBaseContext(), R.string.upload_failure, Toast
                                    .LENGTH_SHORT).show();
                        }

                        //停止进度条
                        stopProgressDialog();
                    }
                });

                String shipImageList = null;
                List<ShipImage> list = viewHolder.shipImageListFunction
                        .onLoadShipImageListOfModifyFromDataBase(viewHolder.ship_id);

                Log.i(LOG_TAG + " doUpload", "shipImageList is " + shipImageList);

                // 执行任务
                uploadShipImageList.beginExecute(list);


            }
        }).setNegativeButton("取消", null).show();
    }

    /**
     * 打开进度条
     */
    protected void startProgressDialog() {

        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            // 设置提醒
            progressDialog.setMessage("数据正在上传中....");
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {


            if (viewHolder.shipImageListFunction.isExistModifyMark(viewHolder.ship_id)) {

                Toast.makeText(this, R.string.not_upload_complete, Toast.LENGTH_SHORT).show();

                return false;

            }
        }

        return super.onKeyDown(keyCode, event);
    }


}

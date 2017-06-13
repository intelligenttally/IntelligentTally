package com.port.shenh.intelligenttally.activity;
/**
 * Created by sh on 2016/11/15.
 */

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.port.shenh.intelligenttally.R;
import com.port.shenh.intelligenttally.adapter.VoyageRecyclerViewAdapter;
import com.port.shenh.intelligenttally.bean.ShipImage;
import com.port.shenh.intelligenttally.bean.Voyage;
import com.port.shenh.intelligenttally.function.ShipImageListFunction;
import com.port.shenh.intelligenttally.function.VoyageListFunction;
import com.port.shenh.intelligenttally.holder.VoyageItemViewHolder;
import com.port.shenh.intelligenttally.work.UploadShipImageList;

import org.mobile.library.common.function.ToolbarInitialize;
import org.mobile.library.model.operate.OnItemClickListenerForRecyclerViewItem;
import org.mobile.library.model.work.WorkBack;

import java.util.ArrayList;
import java.util.List;

/**
 * 航次选择Activity(未上传航次列表)
 *
 * @author sh
 * @version 1.0 2017/04/27
 * @since 1.0
 */
public class NotUploadedVoyageSelectActivity extends AppCompatActivity {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "NotUploadedVoyageSelectActivity.";


    /**
     * 控件集
     */
    private class LocalViewHolder {

        /**
         * 堆存列表数据适配器
         */
        public VoyageRecyclerViewAdapter recyclerViewAdapter = null;


        /**
         * 航次列表数据功能类
         */
        public VoyageListFunction voyageListFunction = null;

        /**
         * 船图数据功能类
         */
        public ShipImageListFunction shipImageListFunction = null;
    }

    /**
     * 控件集对象
     */
    private LocalViewHolder viewHolder = null;

    /**
     * 进度条
     */
    private ProgressDialog progressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voyage_select);

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

        viewHolder.voyageListFunction = new VoyageListFunction(this);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        // 初始化Toolbar
        ToolbarInitialize.initToolbar(this, R.string.vayage_downloaded, true, true);
        // 初始化列表
        initListView();
    }

    /**
     * 初始化列表
     */
    private void initListView() {

        // RecyclerView列表对象
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id
                .activity_voyage_download_recyclerView);

        // 设置item动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // 创建布局管理器
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        if (viewHolder.shipImageListFunction != null) {

            List<Voyage> dataList;

            if (viewHolder.voyageListFunction.onLoadNotUploadedVoyageListFromDataBase() != null) {
                dataList = new ArrayList<>(viewHolder.voyageListFunction
                        .onLoadNotUploadedVoyageListFromDataBase());
            } else {
                dataList = new ArrayList<>();
            }

            Log.i(LOG_TAG + "loadData", "dataList count is " + dataList.size());

            viewHolder.recyclerViewAdapter = new VoyageRecyclerViewAdapter(dataList);


            // 设置列表适配器
            recyclerView.setAdapter(viewHolder.recyclerViewAdapter);

            // 设置点击事件
            viewHolder.recyclerViewAdapter.setOnItemClickListener(new OnItemClickListenerForRecyclerViewItem<List<Voyage>, VoyageItemViewHolder>() {
                @Override
                public void onClick(List<Voyage> voyages, VoyageItemViewHolder
                        voyageItemViewHolder) {

                    viewHolder.recyclerViewAdapter.switchSelectedState(voyageItemViewHolder);
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



        if (viewHolder.recyclerViewAdapter.getSelectedItemCount() == 0) {

            Toast.makeText(this, R.string.not_selected, Toast.LENGTH_SHORT).show();

            return;

        }

//        if (!(viewHolder.recyclerViewAdapter.getSelectedData().getShip_id().equals("196") || viewHolder.recyclerViewAdapter.getSelectedData().getShip_id().equals("195"))) {
//
//            Toast.makeText(this, "暂不支持“新海悦”以外的船舶", Toast.LENGTH_SHORT).show();
//
//            return;
//
//        }

        new AlertDialog.Builder(NotUploadedVoyageSelectActivity.this).setTitle("确认").setIcon(android.R
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

                                    viewHolder.shipImageListFunction.onUpdateModifyMark(viewHolder.recyclerViewAdapter.getSelectedData().getShip_id());
                                    initListView();

                                    Toast.makeText(getBaseContext(), R.string.upload_success, Toast
                                            .LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getBaseContext(), R.string.upload_error_field_required, Toast
                                            .LENGTH_SHORT).show();
                                }

                                //停止进度条
                                stopProgressDialog();
                            }
                        });

                        List<ShipImage> list = viewHolder.shipImageListFunction
                                .onLoadShipImageListOfModifyFromDataBase(viewHolder.recyclerViewAdapter.getSelectedData().getShip_id());

                        Log.i(LOG_TAG + " doUpload", "shipImageList is " + list.toString());

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

}

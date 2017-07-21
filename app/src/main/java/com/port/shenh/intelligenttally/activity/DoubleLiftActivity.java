package com.port.shenh.intelligenttally.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.port.shenh.intelligenttally.R;
import com.port.shenh.intelligenttally.bean.ShipImage;
import com.port.shenh.intelligenttally.function.ContainerInfoFunction;
import com.port.shenh.intelligenttally.function.ContainerInfoFunction2;
import com.port.shenh.intelligenttally.function.ContainerNoSelectList;
import com.port.shenh.intelligenttally.util.StaticValue;
import com.port.shenh.intelligenttally.work.DoubleLift;
import com.port.shenh.intelligenttally.work.PullContainerInfo;

import org.mobile.library.common.function.ToolbarInitialize;
import org.mobile.library.global.Global;
import org.mobile.library.model.function.ISelectList;
import org.mobile.library.model.work.IWorkEndListener;
import org.mobile.library.model.work.WorkBack;


/**
 * Created by shenh on 2017/05/22.
 */


/**
 * 双吊Activity
 *
 * @author sh
 * @version 1.0 2017/06/14
 * @since 1.0
 */
public class DoubleLiftActivity extends AppCompatActivity {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "DoubleLiftActivity.";

    /**
     * SearchView对象1
     */
    private SearchView searchView1 = null;

    /**
     * 搜索框内容1
     */
    TextView textView1 = null;

    /**
     * SearchView对象2
     */
    private SearchView searchView2 = null;

    /**
     * 搜索框内容2
     */
    TextView textView2 = null;

    /**
     * 箱号选择列表1
     */
    private ContainerNoSelectList containerNoSelectList1 = null;

    /**
     * 箱号选择列表2
     */
    private ContainerNoSelectList containerNoSelectList2 = null;

    /**
     * 集装箱信息1
     */
    private ContainerInfoFunction containerInfoFunction1 = null;

    /**
     * 集装箱信息2
     */
    private ContainerInfoFunction2 containerInfoFunction2 = null;

    /**
     * 集装箱船图1
     */
    ShipImage shipImage1 = null;

    /**
     * 集装箱船图2
     */
    ShipImage shipImage2 = null;

    /**
     * 用于显示选择列表的窗口
     */
    public PopupWindow popupWindow = null;

    /**
     * 弹出窗口的内容布局
     */
    public CardView cardView = null;

    /**
     * 根布局
     */
    private View rootView;

    /**
     * 航次ID
     */
    private String ship_id = null;

    /**
     * 船舶ID
     */
    private String v_id = null;

    /**
     * 目标贝号编辑框
     */
    TextView endBaynoTextView = null;

    /**
     * 提交按钮
     */
    Button button = null;

    /**
     * 警告
     */
    private static final String WARNING = "无法调贝：";


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_double_lift);

        // 初始化控件集
        initViewHolder();
        //初始化控件
        initView();

    }

    /**
     * 初始化控件集
     */
    private void initViewHolder() {

        rootView = getWindow().getDecorView();

        searchView1 = (SearchView) findViewById(R.id.activity_double_lift_search1);

        searchView2 = (SearchView) findViewById(R.id.activity_double_lift_search2);

        containerNoSelectList1 = new ContainerNoSelectList(this);
        containerNoSelectList2 = new ContainerNoSelectList(this);

        containerInfoFunction1 = new ContainerInfoFunction(rootView);
        containerInfoFunction2 = new ContainerInfoFunction2(rootView);

        endBaynoTextView = (TextView) findViewById(R.id.activity_double_lift_end_bayno_edit);
        button = (Button) findViewById(R.id.activity_double_lift_submit_button);

        // 弹出窗口布局
        cardView = (CardView) LayoutInflater.from(this).inflate(R.layout.layout_popup_window, null);
        popupWindow = new PopupWindow(this);

        button = (Button) findViewById(R.id.activity_double_lift_submit_button);

        ship_id = (String) getIntent().getSerializableExtra(StaticValue.IntentTag.VOYAGE_TAG);
        v_id = (String) getIntent().getSerializableExtra(StaticValue.IntentTag.V_ID_TAG);

        Log.i(LOG_TAG + "initViewHolder", "ship_id is " + ship_id);

    }

    /**
     * 初始化控件
     */
    private void initView() {
        // 初始化Toolbar
        ToolbarInitialize.initToolbar(this, R.string.double_lift, true, true);
        // 初始化弹出框
        initPopupWindow();
        //初始化搜索
        initSearch();
        //初始化箱号选择列表
        initContainerSelectList();
        //初始化提交按钮
        initButton();
    }

    /**
     * 初始化弹出框
     */
    private void initPopupWindow() {
        popupWindow.setContentView(cardView);
        popupWindow.setWidth(getResources().getDimensionPixelOffset(R.dimen.filter_popup_window_weight));
        popupWindow.setHeight(getResources().getDimensionPixelOffset(R.dimen.filter_popup_window_height2));
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    /**
     * 显示PopupWindow
     *
     * @param anchor 依附的布局
     * @param view   要显示的布局
     */
    private void showPopupWindow(View anchor, View view) {

        if (!popupWindow.isShowing()) {
            cardView.removeAllViews();
            cardView.addView(view);
            popupWindow.showAtLocation(view, Gravity.RIGHT, 0, 0);
            popupWindow.showAsDropDown(anchor);
        }
    }


    /**
     * 初始化搜索
     */
    private void initSearch() {

        if (searchView1 != null) {

            searchView1.setQueryHint(getString(R.string.search_hint1));
            int id1 = searchView1.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
            textView1 = (TextView) searchView1.findViewById(id1);
            textView1.setTextColor(Color.RED);//字体颜色
            textView1.setTextSize(26);//字体、提示字体大小
            searchView1.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    searchButtonClick1(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {

                    if (TextUtils.isEmpty(newText)) {
                        containerInfoFunction1.clearData();
                    }
                    return false;
                }
            });
        }


        if (searchView2 != null) {

            searchView2.setQueryHint(getString(R.string.search_hint2));
            int id1 = searchView2.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
            textView2 = (TextView) searchView2.findViewById(id1);
            textView2.setTextColor(Color.RED);//字体颜色
            textView2.setTextSize(26);//字体、提示字体大小
            searchView2.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    searchButtonClick2(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {

                    if (TextUtils.isEmpty(newText)) {
                        containerInfoFunction2.clearData();
                    }
                    return false;
                }
            });
        }
    }


    /**
     * 执行搜索按钮1
     *
     * @param query 查询字符串
     */
    private void searchButtonClick1(String query) {
        loadData1(query.toUpperCase());
    }


    /**
     * 加载数据1
     *
     * @param query 查询关键字
     */
    private void loadData1(String query) {

        Log.i(LOG_TAG + "loadData1", "query param is " + query);

        containerNoSelectList1.setData(ship_id, query);

        showPopupWindow(rootView, containerNoSelectList1.loadSelect());

    }

    /**
     * 执行搜索按钮2
     *
     * @param query 查询字符串
     */
    private void searchButtonClick2(String query) {
        loadData2(query.toUpperCase());
    }


    /**
     * 加载数据2
     *
     * @param query 查询关键字
     */
    private void loadData2(String query) {

        Log.i(LOG_TAG + "loadData2", "query param is " + query);

        containerNoSelectList2.setData(ship_id, query);

        showPopupWindow(rootView, containerNoSelectList2.loadSelect());

    }


    /**
     * 初始化箱号选择列表
     */
    private void initContainerSelectList() {

        containerNoSelectList1.setOnSelectedListener(new ISelectList.OnSelectedListener<View, String>() {
            @Override
            public void onFinish(String s) {

                textView1.setText(s);
                textView1.setCursorVisible(false);

                PullContainerInfo pullContainerInfo = new PullContainerInfo();
                pullContainerInfo.setWorkEndListener(new WorkBack<ShipImage>() {
                    @Override
                    public void doEndWork(boolean state, ShipImage data) {

                        if (state) {
                            popupWindow.dismiss();
                            containerInfoFunction1.bindData(data);
                            shipImage1 = data;


                        } else {
                            popupWindow.dismiss();
                            Toast.makeText(getApplication(), R.string.error_field_required, Toast.LENGTH_SHORT).show();


                        }

                    }
                });

                pullContainerInfo.beginExecute(ship_id, s);

            }

            @Override
            public void onCancel(View view) {

            }
        });


        containerNoSelectList2.setOnSelectedListener(new ISelectList.OnSelectedListener<View, String>() {
            @Override
            public void onFinish(String s) {

                textView2.setText(s);
                textView2.setCursorVisible(false);

                PullContainerInfo pullContainerInfo = new PullContainerInfo();
                pullContainerInfo.setWorkEndListener(new WorkBack<ShipImage>() {
                    @Override
                    public void doEndWork(boolean state, ShipImage data) {

                        if (state) {
                            popupWindow.dismiss();
                            containerInfoFunction2.bindData(data);
                            shipImage2 = data;


                        } else {
                            popupWindow.dismiss();
                            Toast.makeText(getApplication(), R.string.error_field_required, Toast.LENGTH_SHORT).show();


                        }

                    }
                });

                pullContainerInfo.beginExecute(ship_id, s);

            }

            @Override
            public void onCancel(View view) {

            }
        });

    }

    /**
     * 初始化提交按钮
     */
    private void initButton(){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onDoubleLift();

            }
        });
    }

    /**
     * 双吊移贝
     */
    private void onDoubleLift(){

        String container_no1 = textView1.getText().toString().trim();
        String container_no2 = textView2.getText().toString().trim();
        String endBayno = endBaynoTextView.getText().toString().trim();

        Log.i(LOG_TAG + "container_no1", "query param is " + container_no1);
        Log.i(LOG_TAG + "container_no2", "query param is " + container_no2);
        Log.i(LOG_TAG + "endBayno", "query param is " + endBayno);

        if (container_no1.length() == 0 || container_no2.length() == 0 ||endBayno.length() == 0) {
            Toast.makeText(this, WARNING + getResources().getString(R.string.info_incomplete), Toast.LENGTH_LONG).show();
            return;
        }

        //校验箱号是否存在
        if (shipImage1 == null || shipImage2 == null){
            Toast.makeText(this, WARNING + getResources().getString(R.string.error_container_no), Toast.LENGTH_LONG).show();
            return;
        }

        //校验箱号尺寸
        if ((!shipImage1.getSize_con().equals("20") )|| (!shipImage2.getSize_con().equals("20"))){
            Toast.makeText(this, WARNING + getResources().getString(R.string.mismatching_container_size_con), Toast.LENGTH_LONG).show();
            return;
        }

        //校验箱号是否重复
        if (container_no1.equals(container_no2)){
            Toast.makeText(this, WARNING + getResources().getString(R.string.repeat_container_no), Toast.LENGTH_LONG).show();
            return;
        }

        //校验目标贝位号是否输入正确
        if (endBayno.length()!=6){
            Toast.makeText(this, WARNING + getResources().getString(R.string.error_bayno), Toast.LENGTH_LONG).show();
            return;
        }

        String endBaynum = endBayno.substring(0, 2);
        Log.i(LOG_TAG + "onMoveBay", "endBaynum is " + endBaynum);

        if (Integer.parseInt(endBaynum) % 2 == 0) {
            Toast.makeText(this, WARNING + getResources().getString(R.string.error_bayno), Toast.LENGTH_LONG).show();
            return;
        }

        DoubleLift doubleLift = new DoubleLift();

        doubleLift.setWorkEndListener(new IWorkEndListener<Void>() {
            @Override
            public void doEndWork(boolean state, String message, Void data) {
                if (state){
                    if(message.equals("上传成功！")){
                        onClear();
                        Toast.makeText(DoubleLiftActivity.this, R.string
                                .upload_success, Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        Toast.makeText(DoubleLiftActivity.this, WARNING + message, Toast
                                .LENGTH_LONG).show();
                        return;
                    }

                }else {
                    Toast.makeText(DoubleLiftActivity.this, "上传失败：" + message,
                            Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });

        doubleLift.beginExecute(ship_id, v_id, container_no1, container_no2, endBayno, Global
                .getLoginStatus().getUserID());
    }

    /**
     * 清空数据
     */
    private void onClear(){

        textView1.setText("");
        textView2.setText("");
        containerInfoFunction1.clearData();
        containerInfoFunction2.clearData();
        endBaynoTextView.setText("");
    }
}
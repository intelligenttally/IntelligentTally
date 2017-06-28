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
import com.port.shenh.intelligenttally.function.ContainerNoSelectList;
import com.port.shenh.intelligenttally.util.StaticValue;
import com.port.shenh.intelligenttally.work.MoveBay;
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
 * 调贝Activity
 *
 * @author sh
 * @version 1.0 2017/06/17
 * @since 1.0
 */
public class MoveBayActivity extends AppCompatActivity {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "MoveBayActivity.";

    /**
     * SearchView对象1
     */
    private SearchView searchView = null;

    /**
     * 搜索框内容1
     */
    private TextView textView = null;

    /**
     * 箱号选择列表
     */
    private ContainerNoSelectList containerNoSelectList = null;

    /**
     * 集装箱信息
     */
    private ContainerInfoFunction containerInfoFunction = null;

    /**
     * 集装箱船图
     */
    ShipImage shipImage = null;

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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_move_bay);

        // 初始化控件集
        initViewHolder();

        //初始化控件
        initView();

    }

    /**
     * 初始化控件集
     */
    private void initViewHolder() {

//        rootView = LayoutInflater.from(this).inflate(R.layout.activity_move_bay, null);

        rootView = getWindow().getDecorView();

        searchView = (SearchView) findViewById(R.id.activity_move_bay_search);

        containerNoSelectList = new ContainerNoSelectList(this);

        containerInfoFunction = new ContainerInfoFunction(rootView);

        // 弹出窗口布局
        cardView = (CardView) LayoutInflater.from(this).inflate(R.layout.layout_popup_window, null);
        popupWindow = new PopupWindow(this);

        endBaynoTextView = (TextView) findViewById(R.id.activity_move_bay_end_bayno_edit);
        button = (Button) findViewById(R.id.activity_move_bay_submit_button);

        ship_id = (String) getIntent().getSerializableExtra(StaticValue.IntentTag
         .VOYAGE_TAG);
        v_id = (String) getIntent().getSerializableExtra(StaticValue.IntentTag
                .V_ID_TAG);

        Log.i(LOG_TAG + "initViewHolder", "ship_id is " + ship_id );

    }

    /**
     * 初始化控件
     */
    private void initView() {
        // 初始化Toolbar
        ToolbarInitialize.initToolbar(this, R.string.move_bay, true, true);
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
        popupWindow.setWidth(getResources().getDimensionPixelOffset(R.dimen
                .filter_popup_window_weight));
        popupWindow.setHeight(getResources().getDimensionPixelOffset(R.dimen
                .filter_popup_window_height2));
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

        if (searchView != null) {

            searchView.setQueryHint(getString(R.string.search_hint));
            int id1 = searchView.getContext().getResources().getIdentifier
                    ("android:id/search_src_text", null, null);
            textView = (TextView) searchView.findViewById(id1);
            textView.setTextColor(Color.RED);//字体颜色
            textView.setTextSize(26);//字体、提示字体大小
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    searchButtonClick(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {

                    if (TextUtils.isEmpty(newText)){
                        containerInfoFunction.clearData();
                    }
                    return false;
                }
            });
        }
    }


    /**
     * 执行搜索按钮
     *
     * @param query 查询字符串
     */
    private void searchButtonClick(String query) {
        loadData(query.toUpperCase());
    }

    /**
     * 初始化箱号选择列表
     */
    private void initContainerSelectList() {

        containerNoSelectList.setOnSelectedListener(new ISelectList.OnSelectedListener<View, String>() {
            @Override
            public void onFinish(String s) {

                textView.setText(s);
//                textView.setCursorVisible(false);

                PullContainerInfo pullContainerInfo = new PullContainerInfo();
                pullContainerInfo.setWorkEndListener(new WorkBack<ShipImage>() {
                    @Override
                    public void doEndWork(boolean state, ShipImage data) {

                        if(state){
                            popupWindow.dismiss();
                            containerInfoFunction.bindData(data);
                            shipImage = data;
                        }else {
                            popupWindow.dismiss();
                            Toast.makeText(getApplication(), R.string.error_field_required, Toast
                                    .LENGTH_SHORT).show();


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
     * 加载数据
     *
     * @param query 查询关键字
     */
    private void loadData(String query) {

        Log.i(LOG_TAG + "loadData", "query param is " + query);

        containerNoSelectList.setData(ship_id, query);

        showPopupWindow(rootView, containerNoSelectList.loadSelect());

    }

    /**
     * 初始化提交按钮
     */
    private void initButton(){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onMoveBay();

            }
        });

    }

    /**
     * 移贝
     */
    private void onMoveBay(){

        String container_no = textView.getText().toString().trim();
        String endBayno = endBaynoTextView.getText().toString().trim();

        //校验信息是否完整
        if (container_no.length() == 0 || endBayno.length() == 0) {
            Toast.makeText(this, R.string.info_incomplete, Toast.LENGTH_LONG).show();
            return;
        }

        //校验箱号是否存在
        if (shipImage == null){
            Toast.makeText(this, R.string.error_container_no, Toast.LENGTH_LONG).show();
            return;
        }

        //校验目标贝位号是否输入正确
        if (endBayno.length()!=6){
            Toast.makeText(this, R.string.error_bayno, Toast.LENGTH_LONG).show();
            return;
        }

        //校验输入集装箱对应的源贝位和目标贝位是否一致
        if (endBayno.equals(shipImage.getBayno())){
            Toast.makeText(this, R.string.repeat_bayno, Toast.LENGTH_LONG).show();
            return;
        }

        //校验输入实际贝位号和箱子尺寸是否匹配
        String endBaynum = endBayno.substring(0, 2);
        Log.i(LOG_TAG + "onMoveBay", "endBaynum is " + endBaynum);

        if (Integer.parseInt(endBaynum) % 2 == 0 && shipImage.getSize_con().equals("20")) {
            Toast.makeText(this, R.string.mismatching_container_bayno, Toast.LENGTH_LONG).show();
            return;

        } else if (Integer.parseInt(endBaynum) % 2 != 0 && !shipImage.getSize_con().equals("20")) {
            Toast.makeText(this, R.string.mismatching_container_bayno, Toast.LENGTH_LONG).show();
            return;
        }

        MoveBay moveBay = new MoveBay();

        moveBay.setWorkEndListener(new IWorkEndListener<Void>() {
            @Override
            public void doEndWork(boolean state, String message, Void data) {
                if (state){
                    if(message.equals("上传成功！")){
                        onClear();
                        Toast.makeText(MoveBayActivity.this, R.string
                                .upload_success, Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        Toast.makeText(MoveBayActivity.this, message, Toast
                                .LENGTH_LONG).show();
                        return;
                    }


                }else {
                    Toast.makeText(MoveBayActivity.this, "上传失败：" + message,
                            Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });

        moveBay.beginExecute(ship_id, v_id, container_no, endBayno, Global.getLoginStatus()
                .getUserID());
    }

    /**
     * 清空数据
     */
    private void onClear(){

        textView.setText("");
        containerInfoFunction.clearData();
        endBaynoTextView.setText("");
    }


}

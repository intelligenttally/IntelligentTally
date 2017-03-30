package com.port.shenh.intelligenttally.fragment;
/**
 * Created by 超悟空 on 2016/12/30.
 */

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.port.shenh.intelligenttally.R;
import com.port.shenh.intelligenttally.activity.BayActivity;
import com.port.shenh.intelligenttally.adapter.BayGridAdapter;
import com.port.shenh.intelligenttally.bean.ShipImage;
import com.port.shenh.intelligenttally.function.BottomBayCommonOperator;
import com.port.shenh.intelligenttally.function.BottomBayInfoFunction;
import com.port.shenh.intelligenttally.function.ShipImageListFunction;
import com.port.shenh.intelligenttally.function.VoyageListFunction;
import com.port.shenh.intelligenttally.work.UploadShipImageList;

import org.mobile.library.common.function.InputMethodController;
import org.mobile.library.model.operate.EmptyParameterListener;
import org.mobile.library.model.work.WorkBack;

import java.util.List;

/**
 * 移贝状态的贝图底部布局
 *
 * @author 超悟空
 * @version 1.0 2016/12/30
 * @since 1.0
 */
public class BayMoveBottomFragment extends Fragment implements BottomBayCommonOperator {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "BayMoveBottomFragment.";

    /**
     * 数据加载工具
     */
    private ShipImageListFunction shipFunction = null;

    /**
     * 航次加载工具
     */
    private VoyageListFunction voyageFunction = null;

    /**
     * 信息布局填充工具
     */
    private BottomBayInfoFunction function = null;

    /**
     * 移贝操作事件触发器
     */
    private EmptyParameterListener onMoveListener = null;

    /**
     * 贝号输入框
     */
    private TextInputEditText bayNumberEditText = null;

    /**
     * 父activity
     */
    private BayActivity activity = null;

    /**
     * 要移动的船图数据
     */
    private ShipImage dataShipImage = null;

    /**
     * 进出口编码
     */
    private String codeInOut = null;


    /**
     * 进度条
     */
    private ProgressDialog progressDialog = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bottom_sheet_move, container, false);

        initView(rootView);

        return rootView;
    }

    /**
     * 初始化布局
     *
     * @param rootView 根布局
     */
    private void initView(View rootView) {
        activity = (BayActivity) getActivity();
        shipFunction = new ShipImageListFunction(getActivity());
        voyageFunction = new VoyageListFunction(getActivity());
        codeInOut = voyageFunction.onLoadCodeInOutOfVoyageFromDataBase(dataShipImage.getShip_id());
        initEditText(rootView);
        initMove(rootView);
        initShip(rootView);
    }

    @Override
    public void onResume() {
        super.onResume();
        bayNumberEditText.setText(null);
        bayNumberEditText.setError(null);
    }

    /**
     * 初始化要移动的船图数据
     *
     * @param rootView 根布局
     */
    private void initShip(View rootView) {
        function = new BottomBayInfoFunction(rootView);
        function.bindData(dataShipImage);
    }

    /**
     * 初始化贝号输入框
     *
     * @param rootView 根布局
     */
    private void initEditText(View rootView) {
        bayNumberEditText = (TextInputEditText) rootView.findViewById(R.id
                .fragment_bottom_sheet_move_editText);

        bayNumberEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 6) {
                    bayNumberEditText.setError(null);
                }
            }
        });
    }

    /**
     * 初始化移贝操作
     *
     * @param rootView 根布局
     */
    private void initMove(View rootView) {
        View okView = rootView.findViewById(R.id.fragment_bottom_sheet_move_ok_textView);

        okView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMoveOk();
            }
        });

        View cancelView = rootView.findViewById(R.id.fragment_bottom_sheet_move_cancel_textView);

        cancelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMoveCancel();
            }
        });
    }

    /**
     * 点击取消事件
     */
    private void onMoveCancel() {
        InputMethodController.CloseInputMethod(getActivity());
        onMoveListener.onInvoke();
    }

    /**
     * 点击确认事件
     */
    private void onMoveOk() {
        if (bayNumberEditText.getText().toString().isEmpty()) {
            bayNumberEditText.setError(getString(R.string.hint_input_bay_number_text));
            return;
        }

        String result = shipFunction.onMoveBay(dataShipImage, bayNumberEditText.getText().toString(),
                codeInOut);

        if (result == null) {
            onMoveListener.onInvoke();
            activity.loadBay();
            onUpload();
        } else {
            bayNumberEditText.setError(result);
        }
    }

    /**
     * 设置移贝操作的事件回调
     *
     * @param onMoveListener 监听器，回调方法参数为要移动的贝图数据
     */
    public void setOnMoveListener(EmptyParameterListener onMoveListener) {
        this.onMoveListener = onMoveListener;
    }

    /**
     * 设置显示的贝数据
     *
     * @param data 贝数据
     */
    public void setBayData(ShipImage data) {
        this.dataShipImage = data;
    }

    @Override
    public void onBayClick(BayGridAdapter.ViewHolder holder, ShipImage data) {
        bayNumberEditText.setError(null);
        bayNumberEditText.setText(data.getSbayno());
    }

    @Override
    public void onBaySwitch() {

    }

    @Override
    public void onBack() {
        onMoveCancel();
    }

    /**
     *  数据上传
     */
    private void onUpload(){

        Log.i(LOG_TAG + " onUpload", "Ship_id is " + dataShipImage.getShip_id());

        if(!(dataShipImage.getShip_id().equals("196") || dataShipImage.getShip_id().equals("195"))){

            Toast.makeText(getContext(), "暂不支持“新海悦”以外的船舶", Toast
                    .LENGTH_SHORT).show();

            return;

        }



        startProgressDialog();

        UploadShipImageList uploadShipImageList = new UploadShipImageList();

        uploadShipImageList.setWorkEndListener(new WorkBack<Void>() {
            @Override
            public void doEndWork(boolean state, Void data) {

                Log.i(LOG_TAG + "doUpload", "state is " + state);

                if (state) {

                    shipFunction.onUpdateModifyMark(dataShipImage.getShip_id());

                    Toast.makeText(getContext(), R.string.upload_success, Toast
                            .LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), R.string.upload_failure, Toast
                            .LENGTH_SHORT).show();
                }

                //停止进度条
                stopProgressDialog();
            }
        });

        String shipImageList = null;
        List<ShipImage> list = shipFunction
                .onLoadShipImageListOfModifyFromDataBase(dataShipImage.getShip_id());

        // 执行任务
        uploadShipImageList.beginExecute(list);
    }

    /**
     * 打开进度条
     */
    protected void startProgressDialog() {

        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getContext());
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

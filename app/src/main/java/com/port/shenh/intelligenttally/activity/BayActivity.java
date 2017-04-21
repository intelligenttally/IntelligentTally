package com.port.shenh.intelligenttally.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.port.shenh.intelligenttally.R;
import com.port.shenh.intelligenttally.adapter.BayGridAdapter;
import com.port.shenh.intelligenttally.bean.Bay;
import com.port.shenh.intelligenttally.bean.ShipImage;
import com.port.shenh.intelligenttally.fragment.BayNormalBottomFragment;
import com.port.shenh.intelligenttally.function.BayNumSelectList;
import com.port.shenh.intelligenttally.function.BottomBayCommonOperator;
import com.port.shenh.intelligenttally.function.ShipImageListFunction;
import com.port.shenh.intelligenttally.util.StaticValue;
import com.port.shenh.intelligenttally.view.FreedomScrollView;

import org.mobile.library.common.function.ToolbarInitialize;
import org.mobile.library.model.function.ISelectList;
import org.mobile.library.model.operate.EmptyParameterListener;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Created by shenh on 2016/11/21.
 */


/**
 * 贝位图Activity
 *
 * @author sh
 * @version 1.0 2016/11/21
 * @since 1.0
 */
public class BayActivity extends AppCompatActivity {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "BayActivity.";

    /**
     * 贝布局数据适配器
     */
    private BayGridAdapter adapter = null;

    /**
     * 数据加载工具
     */
    private ShipImageListFunction function = null;

    /**
     * 航次ID
     */
    private String shipId = null;

    /**
     * 标题文本框
     */
    private TextView titleTextView = null;

    /**
     * 内容滚动布局
     */
    private FreedomScrollView scrollView = null;

    /**
     * 当前贝号索引
     */
    private int bayNumberPosition = 0;

    /**
     * 贝号列表
     */
    private List<String> bayNumberList = null;

    /**
     * 上一个选中的贝位
     */
    public BayGridAdapter.ViewHolder beforeHolder = null;

    /**
     * 底部布局
     */
    private View bottomLayout = null;

    /**
     * 底部背景布局（占位布局）
     */
    private View bottomBackgroundView = null;

    /**
     * 底部布局是否为显示状态
     */
    private boolean isBottomShow = false;

    /**
     * 当前底部布局
     */
    private BottomBayCommonOperator operator = null;

    /**
     * 贝号选择列表
     */
    private BayNumSelectList bayNumSelectList = null;

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
     * 内容布局
     */
    private TableLayout layout;

    /**
     * 缩放比例因子
     */
    final private float scaleArray[] = new float[]{0.5f , 0.6f , 0.7f , 0.8f , 0.9f , 1.0f , 1.1f
            , 1.2f , 1.3f , 1.4f , 1.5f};

    /**
     * 缩放比例因子索引
     */
    private int scaleIndex = 5;


    //    private final Matrix mScaleMatrix = new Matrix();
    //
    //    private final float[] matrixValues = new float[9];
    //
    //    /**
    //     * 初始化时的缩放比例，如果图片宽或高大于屏幕，此值将小于0
    //     */
    //    private float initScale = 0.0f;
    //
    //    public static final float SCALE_MAX = 4.0f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bay);

        // 初始化控件集
        initViewHolder();

        initView();
    }

    /**
     * 初始化控件集
     */
    private void initViewHolder() {

        rootView = LayoutInflater.from(this).inflate(R.layout.activity_bay, null);

        layout = (TableLayout) findViewById(R.id.activity_bay_content_layout);


        // 弹出窗口布局
        cardView = (CardView) LayoutInflater.from(this).inflate(R.layout.layout_popup_window, null);
        popupWindow = new PopupWindow(this);

        function = new ShipImageListFunction(this);
        Intent intent = getIntent();
        shipId = intent.getStringExtra(StaticValue.IntentTag.VOYAGE_TAG);
        bayNumberPosition = intent.getIntExtra(StaticValue.IntentTag.BAYNUM_SELECT_TAG, 0);

    }

    /**
     * 初始化控件
     */
    private void initView() {
        // 初始化Toolbar
        ToolbarInitialize.initToolbar(this, R.string.bay, true, true);
        titleTextView = (TextView) findViewById(R.id.toolbar_title);

        // 初始化弹出框
        initPopupWindow();
        // 初始化贝号选择列表
        initBayNumSelectList();

        initBay();
        initContentLayout();
        initBottomLayout();

        initData();
    }

    /**
     * 初始化弹出框
     */
    private void initPopupWindow() {
        popupWindow.setContentView(cardView);
        popupWindow.setWidth(getResources().getDimensionPixelOffset(R.dimen
                .filter_popup_window_weight));
        popupWindow.setHeight(getResources().getDimensionPixelOffset(R.dimen
                .filter_popup_window_height));
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
     * 初始化贝号选择列表
     */
    public void initBayNumSelectList() {

        bayNumSelectList = new BayNumSelectList(this, shipId);


        bayNumSelectList.setOnSelectedListener(new ISelectList.OnSelectedListener<View, String>() {
            @Override
            public void onFinish(String s) {


                bayNumberPosition = Integer.parseInt(s);

                loadBay();

                popupWindow.dismiss();

            }

            @Override
            public void onCancel(View view) {

            }
        });

    }

    /**
     * 初始化底部布局
     */
    private void initBottomLayout() {
        bottomLayout = findViewById(R.id.layout_bottom_parent_layout);
        bottomBackgroundView = findViewById(R.id.activity_bay_bottom_background_view);

        onChangeBottomFragment(new BayNormalBottomFragment());
    }

    /**
     * 替换底部布局
     *
     * @param fragment 要替换的布局
     */
    public void onChangeBottomFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (operator instanceof Fragment) {
            transaction.hide((Fragment) operator);
        }

        if (!fragment.isAdded()) {
            transaction.add(R.id.layout_bottom_parent_content_layout, fragment);
        } else {
            transaction.show(fragment);
        }
        transaction.commit();
        if (fragment instanceof BottomBayCommonOperator) {
            operator = (BottomBayCommonOperator) fragment;
        }
    }

    /**
     * 初始化贝布局
     */
    private void initBay() {
        adapter = new BayGridAdapter(this);

        adapter.setOnGridItemClickListener(new BayGridAdapter.OnGridItemClickListener() {
            @Override
            public void onClick(BayGridAdapter.ViewHolder holder, ShipImage data) {
                if (beforeHolder != null) {
                    beforeHolder.itemView.setSelected(false);
                }

                operator.onBayClick(holder, data);

                if (holder != beforeHolder) {
                    holder.itemView.setSelected(true);
                    beforeHolder = holder;
                } else {
                    beforeHolder = null;
                }
            }
        });
    }

    //    /**
    //     * 初始化内容布局
    //     */
    //    @SuppressWarnings("ConstantConditions")
    //    private void initContentLayout() {
    //
    //        final TableLayout layout = (TableLayout) findViewById(R.id
    // .activity_bay_content_layout);
    //
    //        scrollView = (FreedomScrollView) findViewById(R.id.activity_bay_scrollView);
    //
    //        scrollView.setScrollableOutsideChile(true);
    //
    //
    //        layout.setOnTouchListener(new View.OnTouchListener() {
    //
    //            float currentDistance;
    //            float lastDistance = -1;
    //
    //
    //            @Override
    //            public boolean onTouch(View v, MotionEvent event) {
    //
    //                switch (event.getAction()) {
    //
    //                    case MotionEvent.ACTION_MOVE:
    //
    //                        Log.i(LOG_TAG + "initContentLayout", "onTouch is invoked");
    //
    //                        if (event.getPointerCount() >= 2) {
    //
    //
    //                            float offsetX = event.getX(0) - event.getX(1);
    //                            float offsetY = event.getY(0) - event.getY(1);
    //
    //                            currentDistance = (float) Math.sqrt(offsetX * offsetX + offsetY *
    //                                    offsetY);
    //
    //                            if (lastDistance < 0) {
    //                                lastDistance = currentDistance;
    //
    //
    //                            } else {
    //                                if (currentDistance - lastDistance > 5) {
    //
    //                                    Log.i(LOG_TAG + "initContentLayout", "onTouch is 放大");
    //
    //                                    FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams)
    //                                            layout.getLayoutParams();
    //                                    lp.width = (int) (1.1f * layout.getWidth());
    //                                    lp.height = (int) (1.1f * layout.getHeight());
    //
    //                                    layout.setLayoutParams(lp);
    //
    //
    //
    //                                    lastDistance = currentDistance;
    //                                } else if (lastDistance - currentDistance > 5) {
    //                                    Log.i(LOG_TAG + "initContentLayout", "onTouch is 缩小");
    //                                    FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams)
    //                                            layout.getLayoutParams();
    //                                    lp.width = (int) (0.9f * layout.getWidth());
    //                                    lp.height = (int) (0.9f * layout.getHeight());
    //
    //                                    layout.setLayoutParams(lp);
    //
    //                                    lastDistance = currentDistance;
    //                                }
    //
    //                            }
    //
    //                        }
    //
    //
    //                        break;
    //
    //                }
    //
    //                return true;
    //            }
    //        });
    //    }


    /**
     * 初始化内容布局
     */
    @SuppressWarnings("ConstantConditions")
    private void initContentLayout() {

        scrollView = (FreedomScrollView) findViewById(R.id.activity_bay_scrollView);

        scrollView.setScrollableOutsideChile(true);
        //
        //        final ScaleGestureDetector scaleGestureDetector = new ScaleGestureDetector
        // (BayActivity
        //                .this, new ScaleGestureDetector.SimpleOnScaleGestureListener() {
        //            @Override
        //            public boolean onScale(ScaleGestureDetector detector) {
        //
        //                float scale = getScale();
        //                float scaleFactor = detector.getScaleFactor();
        //
        //                /**
        //                 * 缩放的范围控制
        //                 */
        //                if ((scale < SCALE_MAX && scaleFactor > 1.0f) || (scale > initScale &&
        //                        scaleFactor < 1.0f)) {
        //
        //                    /**
        //                     * 最大值最小值判断
        //                     */
        //                    if (scaleFactor * scale < initScale) {
        //                        scaleFactor = initScale / scale;
        //                    }
        //                    if (scaleFactor * scale > SCALE_MAX) {
        //                        scaleFactor = SCALE_MAX / scale;
        //                    }
        //
        //                    layout.setScaleX(scaleFactor);
        //                    layout.setScaleY(scaleFactor);
        //
        //                    layout.setTranslationX(layout.getWidth() * (scaleFactor - 1) / 2);
        //                    layout.setTranslationY(layout.getWidth() * (scaleFactor - 1) / 2);
        //
        //                }
        //
        //                return false;
        //            }
        //        });
        //
        //        scrollView.setOnTouchListener(new View.OnTouchListener() {
        //            @Override
        //            public boolean onTouch(View v, MotionEvent event) {
        //                return event.getPointerCount() > 1 && scaleGestureDetector.onTouchEvent
        // (event);
        //            }
        //        });
    }


    //
    //        final ScaleGestureDetector scaleGestureDetector = new ScaleGestureDetector(BayActivity
    //                .this, new ScaleGestureDetector.SimpleOnScaleGestureListener() {
    //
    //            private float scale;
    //            private float preScale = 1;// 默认前一次缩放比例为1
    //
    //            @Override
    //            public boolean onScale(ScaleGestureDetector detector) {
    //
    //                float previousSpan = detector.getPreviousSpan();
    //                float currentSpan = detector.getCurrentSpan();
    //                if (currentSpan < previousSpan) {
    //                    // 缩小
    //                    // scale = preScale-detector.getScaleFactor()/3;
    //                    scale = preScale - (previousSpan - currentSpan) / 1000;
    //                } else {
    //                    // 放大
    //                    // scale = preScale+detector.getScaleFactor()/3;
    //                    scale = preScale + (currentSpan - previousSpan) / 1000;
    //                }
    //
    //                // 缩放view
    //                ViewHelper.setScaleX(layout, scale );// x方向上缩小
    //                ViewHelper.setScaleY(layout, scale );// y方向上缩小
    //
    //                return false;
    //            }
    //
    //            @Override
    //            public boolean onScaleBegin(ScaleGestureDetector detector) {
    //                // 一定要返回true才会进入onScale()这个函数
    //                return true;
    //            }
    //
    //            @Override
    //            public void onScaleEnd(ScaleGestureDetector detector) {
    //                preScale = scale;//记录本次缩放比例
    //            }
    //        });
    //
    //        scrollView.setOnTouchListener(new View.OnTouchListener() {
    //            @Override
    //            public boolean onTouch(View v, MotionEvent event) {
    //                return event.getPointerCount() > 1 && scaleGestureDetector.onTouchEvent
    // (event);
    //            }
    //        });
    //
    //    }


    //
    //        final ScaleGestureDetector scaleGestureDetector = new ScaleGestureDetector
    // (BayActivity
    //                .this, new ScaleGestureDetector.SimpleOnScaleGestureListener() {
    //            @Override
    //            public boolean onScale(ScaleGestureDetector detector) {
    //
    //                float factor = detector.getScaleFactor();
    //
    //                if (factor > 1.5 || factor < 0.5) {
    //                    return false;
    //                }
    //
    //                layout.setScaleX(factor);
    //                layout.setScaleY(factor);
    //
    //                layout.setTranslationX(layout.getWidth() * (factor - 1) / 2);
    //                layout.setTranslationY(layout.getHeight() * (factor - 1) / 2);
    //
    //
    //                return false;
    //            }
    //        });


    //    /**
    //     * 获得当前的缩放比例
    //     *
    //     * @return
    //     */
    //    public final float getScale() {
    //        mScaleMatrix.getValues(matrixValues);
    //        return matrixValues[Matrix.MSCALE_X];
    //    }


    /**
     * 初始化数据
     */
    private void initData() {

        adapter.setUnloadSort(function.onLoadCodeUnloadPortSubListFromDataBase(shipId));
        bayNumberList = function.onLoadBayNumListFromDataBase(shipId);

        loadBay();
    }

    /**
     * 加载贝图
     */
    public void loadBay() {

        operator.onBaySwitch();

        if (beforeHolder != null) {
            beforeHolder.itemView.setSelected(false);
            beforeHolder = null;
        }

        if (bayNumberList == null || bayNumberList.isEmpty()) {
            return;
        }

        String bayNumber = bayNumberList.get(bayNumberPosition);

        Bay bay = function.onLoadBayFromDataBase(shipId, bayNumber);

        titleTextView.setText(bay.getBay_num());

        List<ShipImage> list = function.onLoadShipImageFromDataBase(shipId, bayNumber);

        adapter.showBay(bay, list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bay, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_refresh_bay:
                //刷新
                doRefresh_bay();
                break;
            case R.id.menu_last_bay:
                // 上一贝
                doLastBay();
                break;
            case R.id.menu_next_bay:
                //下一贝
                doNextBay();
                break;
            case R.id.menu_bay_num_select:
                //贝号选择
                doBayNumSelect();
                break;
            case R.id.menu_up_bay:
                //放大贝
                doUpBay();
                break;
            case R.id.menu_down_bay:
                //缩小贝
                doDownBay();
                break;
        }
        return true;
    }

    /**
     * 刷新贝
     */
    private void doRefresh_bay() {
        operator.onBack();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // 设置提醒
        progressDialog.setMessage("数据正在下载中....");
        progressDialog.setCancelable(false);
        progressDialog.show();

        final AtomicInteger count = new AtomicInteger(1);

        String bayNumber = bayNumberList.get(bayNumberPosition);

        function.setOnLoadEndListener(new ShipImageListFunction.OnLoadEndListener() {
            @Override
            public void OnLoadEnd() {

                Log.i(LOG_TAG + "OnLoadEnd", "count is" + count.get());

                if (count.decrementAndGet() == 0) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            loadBay();
                            progressDialog.dismiss();
                            Toast.makeText(getBaseContext(), R.string.download_success, Toast
                                    .LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


        count.incrementAndGet();
        function.onUpdate(shipId, bayNumber);

        String bayNumberNext = Integer.toString(Integer.parseInt(bayNumber) + 2);
        //        int bayNumberNextIndex = function.onLoadBayNumListFromDataBase(shipId).indexOf
        //                (bayNumberNext);
        //
        //        Log.i(LOG_TAG + "doRefresh_bay", "bayNumNext is " + bayNumberNext);
        //        Log.i(LOG_TAG + "doRefresh_bay", "bayNumberNextIndex is " + bayNumberNextIndex);

        if (function.isJoint(shipId, bayNumber) && (function.onLoadBayNumListFromDataBase(shipId)
                .indexOf(bayNumberNext) != -1)) {

            count.incrementAndGet();

            function.onUpdate(shipId, bayNumberNext);

        }

        count.decrementAndGet();

    }

    /**
     * 上一贝
     */
    private void doLastBay() {
        if (bayNumberPosition > 0) {
            bayNumberPosition--;
            loadBay();
        }
    }

    /**
     * 下一贝
     */
    private void doNextBay() {
        if (bayNumberList != null && bayNumberPosition < bayNumberList.size() - 1) {
            bayNumberPosition++;
            loadBay();
        }
    }

    /**
     * 贝号选择
     */
    private void doBayNumSelect() {

        showPopupWindow(rootView, bayNumSelectList.loadSelect());

    }

    /**
     * 放大贝
     */
    private void doUpBay() {

        scaleIndex++;
        if (scaleIndex > scaleArray.length - 1) {

            scaleIndex = scaleArray.length - 1;

        } else {

            layout.setScaleX(scaleArray[scaleIndex]);
            layout.setScaleY(scaleArray[scaleIndex]);

            layout.setTranslationX(layout.getWidth() * (scaleArray[scaleIndex] - 1) / 2);
            layout.setTranslationY(layout.getHeight() * (scaleArray[scaleIndex] - 1) / 2);
        }


    }

    /**
     * 缩小贝
     */
    private void doDownBay() {

        scaleIndex--;
        if (scaleIndex < 0) {

            scaleIndex = 0;

        } else {

            layout.setScaleX(scaleArray[scaleIndex]);
            layout.setScaleY(scaleArray[scaleIndex]);

            layout.setTranslationX(layout.getWidth() * (scaleArray[scaleIndex] - 1) / 2);
            layout.setTranslationY(layout.getHeight() * (scaleArray[scaleIndex] - 1) / 2);

        }


    }

    /**
     * 显示布局
     */
    public void showBottomLayout() {
        showBottomLayout(null);
    }

    /**
     * 隐藏布局
     */
    public void hideBottomLayout() {
        hideBottomLayout(null);
    }

    /**
     * 显示布局
     *
     * @param listener 动画完成后的回调
     */
    public void showBottomLayout(final EmptyParameterListener listener) {
        if (!isBottomShow) {
            isBottomShow = true;
            bottomLayout.animate().translationY(0).setDuration(200).setListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationEnd(Animator animation) {
                    ViewGroup.LayoutParams layoutParams = bottomBackgroundView.getLayoutParams();
                    layoutParams.height = bottomLayout.getHeight();
                    bottomBackgroundView.setLayoutParams(layoutParams);
                    bottomBackgroundView.setVisibility(View.VISIBLE);

                    if (listener != null) {
                        listener.onInvoke();
                    }
                }
            }).start();
        }
    }

    /**
     * 隐藏布局
     *
     * @param listener 动画完成后的回调
     */
    public void hideBottomLayout(final EmptyParameterListener listener) {
        if (isBottomShow) {
            isBottomShow = false;
            bottomLayout.animate().translationY(bottomLayout.getHeight()).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    if (listener != null) {
                        listener.onInvoke();
                    }
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    bottomBackgroundView.setVisibility(View.GONE);
                }
            }).start();
        }
    }

    @Override
    public void onBackPressed() {

        if (isBottomShow) {
            operator.onBack();
        } else {
            super.onBackPressed();
        }
    }
}

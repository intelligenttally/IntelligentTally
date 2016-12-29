package com.port.shenh.intelligenttally.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import com.port.shenh.intelligenttally.R;
import com.port.shenh.intelligenttally.adapter.BayGridAdapter;
import com.port.shenh.intelligenttally.bean.Bay;
import com.port.shenh.intelligenttally.bean.ShipImage;
import com.port.shenh.intelligenttally.fragment.BayNormalBottomFragment;
import com.port.shenh.intelligenttally.function.ShipImageListFunction;
import com.port.shenh.intelligenttally.util.StaticValue;
import com.port.shenh.intelligenttally.view.FreedomScrollView;

import org.mobile.library.common.function.ToolbarInitialize;

import java.util.List;

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
     * 内容布局原始高度
     */
    private int bottom = 0;

    /**
     * 上一个选中的贝位
     */
    private BayGridAdapter.ViewHolder beforeHolder = null;

    /**
     * 底部布局
     */
    private View bottomLayout = null;

    /**
     * 常规底部布局
     */
    private BayNormalBottomFragment bayNormalBottomFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bay);

        initView();
        initData();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        // 初始化Toolbar
        ToolbarInitialize.initToolbar(this, R.string.bay, true, true);
        titleTextView = (TextView) findViewById(R.id.toolbar_title);

        initContentLayout();
        initBottomLayout();
        initBay();
    }

    /**
     * 初始化底部布局
     */
    private void initBottomLayout() {
        bottomLayout = findViewById(R.id.layout_bottom_parent_layout);
        bayNormalBottomFragment = new BayNormalBottomFragment();

        onChangeBottomFragment(bayNormalBottomFragment);
    }

    /**
     * 替换底部布局
     *
     * @param fragment 要替换的布局
     */
    private void onChangeBottomFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id
                .layout_bottom_parent_content_layout, fragment).commit();
    }

    /**
     * 初始化贝布局
     */
    private void initBay() {
        adapter = new BayGridAdapter(this);

        adapter.setOnGridItemClickListener(new BayGridAdapter.OnGridItemClickListener() {
            @Override
            public void onClick(BayGridAdapter.ViewHolder holder, ShipImage data) {
                if (bottom == 0) {
                    bottom = scrollView.getBottom();
                }

                if (beforeHolder != null) {
                    beforeHolder.itemView.setSelected(false);
                }

                if (holder != beforeHolder) {
                    holder.itemView.setSelected(true);
                    beforeHolder = holder;

                    if (!TextUtils.isEmpty(data.getBayno()) && bayNormalBottomFragment.isVisible
                            ()) {
                        bayNormalBottomFragment.setBayData(data);
                        showBottomLayout();
                    } else {
                        hideBottomLayout();
                    }

                } else {
                    beforeHolder = null;
                    hideBottomLayout();
                }
            }
        });
    }

    /**
     * 初始化内容布局
     */
    @SuppressWarnings("ConstantConditions")
    private void initContentLayout() {

        final TableLayout layout = (TableLayout) findViewById(R.id.activity_bay_content_layout);

        scrollView = (FreedomScrollView) findViewById(R.id.activity_bay_scrollView);

        scrollView.setScrollableOutsideChile(true);

        final ScaleGestureDetector scaleGestureDetector = new ScaleGestureDetector(BayActivity
                .this, new ScaleGestureDetector.SimpleOnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {

                float factor = detector.getScaleFactor();

                if (factor > 1.5 || factor < 0.5) {
                    return false;
                }

                layout.setScaleX(factor);
                layout.setScaleY(factor);

                layout.setTranslationX(layout.getWidth() * (factor - 1) / 2);
                layout.setTranslationY(layout.getHeight() * (factor - 1) / 2);

                return false;
            }
        });

        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return event.getPointerCount() > 1 && scaleGestureDetector.onTouchEvent(event);
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        function = new ShipImageListFunction(this);

        Intent intent = getIntent();

        shipId = intent.getStringExtra(StaticValue.IntentTag.VOYAGE_TAG);
        bayNumberPosition = intent.getIntExtra(StaticValue.IntentTag.BAYNUM_SELECT_TAG, 0);

        adapter.setUnloadSort(function.onLoadCodeUnloadPortSubListFromDataBase(shipId));
        bayNumberList = function.onLoadBayNumListFromDataBase(shipId);

        loadBay();
    }

    /**
     * 加载贝图
     */
    private void loadBay() {

        if (bottom != 0) {
            hideBottomLayout();
            bottom = 0;
        }

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
            case R.id.menu_last_bay:
                // 退出操作
                doLastBay();
                break;
            case R.id.menu_next_bay:
                //清楚缓存
                doNextBay();
                break;
        }
        return true;
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
     * 显示布局
     */
    private void showBottomLayout() {
        bottomLayout.animate().translationY(0).setDuration(200).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (bottom != 0 && scrollView.getBottom() != bottom - bottomLayout.getHeight()) {
                    scrollView.setBottom(bottom - bottomLayout.getHeight());
                }
            }
        }).start();
    }

    /**
     * 隐藏布局
     */
    private void hideBottomLayout() {
        bottomLayout.animate().translationY(bottomLayout.getHeight()).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (bottom != 0 && scrollView.getBottom() != bottom) {
                    scrollView.setBottom(bottom);
                }
            }
        }).start();
    }
}

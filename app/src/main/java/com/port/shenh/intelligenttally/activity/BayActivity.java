package com.port.shenh.intelligenttally.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.port.shenh.intelligenttally.R;
import com.port.shenh.intelligenttally.adapter.BayGridAdapter;
import com.port.shenh.intelligenttally.bean.Bay;
import com.port.shenh.intelligenttally.bean.ShipImage;
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
     * 当前贝号
     */
    private int bayNumber = 1;

    /**
     * 最大贝号
     */
    private int maxBayNumber = 1;

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

        initLayout();

        initBay();
    }

    /**
     * 初始化贝布局
     */
    private void initBay() {
        adapter = new BayGridAdapter(this);

        adapter.setOnGridItemClickListener(new BayGridAdapter.OnGridItemClickListener() {
            @Override
            public void onClick(BayGridAdapter.ViewHolder holder, ShipImage data) {
                Toast.makeText(BayActivity.this, data.getBayno(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 初始化内容布局
     */
    @SuppressWarnings("ConstantConditions")
    private void initLayout() {

        final TableLayout layout = (TableLayout) findViewById(R.id.activity_bay_content_layout);

        final FreedomScrollView scrollView = (FreedomScrollView) findViewById(R.id
                .activity_bay_scrollView);

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
        String bayNumber = intent.getStringExtra(StaticValue.IntentTag.BAYNUM_SELECT_TAG);

        this.bayNumber = Integer.parseInt(bayNumber);

        maxBayNumber = intent.getIntExtra(StaticValue.IntentTag.MAX_BAY_NUMBER_TAG, this.bayNumber);

        loadBay();
    }

    /**
     * 加载贝图
     */
    private void loadBay() {

        String bayNumber = this.bayNumber >= 10 ? String.valueOf(this.bayNumber) : "0" + this
                .bayNumber;

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
        if (bayNumber > 1) {
            bayNumber -= 2;
            loadBay();
        }
    }

    /**
     * 下一贝
     */
    private void doNextBay() {
        if (bayNumber < maxBayNumber) {
            bayNumber += 2;
            loadBay();
        }
    }

}

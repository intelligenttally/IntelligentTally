package com.port.shenh.intelligenttally.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.port.shenh.intelligenttally.R;
import com.port.shenh.intelligenttally.view.FreedomScrollView;

import org.mobile.library.common.function.ToolbarInitialize;

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
     * 表格布局
     */
    private GridLayout gridLayout = null;

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

        initGridLayout();
    }

    /**
     * 初始化表格布局
     */
    @SuppressWarnings("ConstantConditions")
    private void initGridLayout() {

        gridLayout = (GridLayout) findViewById(R.id.activity_bay_gridLayout);

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

                gridLayout.setScaleX(factor);
                gridLayout.setScaleY(factor);

                gridLayout.setTranslationX(gridLayout.getWidth() * (factor - 1) / 2);
                gridLayout.setTranslationY(gridLayout.getHeight() * (factor - 1) / 2);

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
        // 模拟数据
        for (int i = 0; i < 200; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.layout_item_box, gridLayout,
                    false);

            final TextView textView = (TextView) view.findViewById(R.id.layout_item_box_textView);
            textView.setText("box" + i);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(BayActivity.this, textView.getText().toString(), Toast
                            .LENGTH_SHORT).show();
                }
            });

            gridLayout.addView(view);
        }
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

    }

    /**
     * 下一贝
     */
    private void doNextBay() {

    }
}

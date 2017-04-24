package com.port.shenh.intelligenttally.activity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.port.shenh.intelligenttally.R;

import org.mobile.library.common.function.ToolbarInitialize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shenh on 2017/04/24.
 */


/**
 * 全统计列表
 *
 * @author shenh
 * @version 1.0 2017/04/24
 * @since 1.0
 */
public class FullStatisticsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "FullStatisticsActivity.";

    /**
     * 功能标题的取值标签，用于数据适配器中
     */
    private static final String FUNCTION_TITLE = "function_title";

    /**
     * 功能图标取值图标
     */
    private static final String FUNCTION_IMAGE = "function_image";

    /**
     * 列表使用的数据适配器
     */
    private SimpleAdapter adapter = null;

    /**
     * 数据适配器的元数据
     */
    private List<Map<String, Object>> adapterDataList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_full_statistics);

        // 初始化控件
        initView();

        // 初始化功能列表
        initListView();

    }

    /**
     * 初始化控件
     */
    private void initView(){

        // 初始化Toolbar
        ToolbarInitialize.initToolbar(this, R.string.full_statistics, true, true);

        // 初始化功能列表
        initListView();
    }


    /**
     * 初始化功能表格布局
     *
     */
    private void initListView() {

        // 片段中的列表布局
        ListView listView = (ListView) findViewById(R.id.activity_full_statistics_list_view);

        // 列表使用的数据适配器
        adapter = new SimpleAdapter(this, getFunctionTitle(), R.layout
                .full_statistics_item, new String[]{FUNCTION_TITLE , FUNCTION_IMAGE}, new
                int[]{R.id.full_statistics_item_textView , R.id.full_statistics_item_imageView});

        // 设置适配器
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

    }

    /**
     * 生成功能项标签资源的数据源
     *
     * @return 返回SimpleAdapter适配器使用的数据源
     */
    private List<Map<String, Object>> getFunctionTitle() {
        // 加载功能项
        List<Map<String, Object>> dataList = new ArrayList<>();

        String[] functionTitle = getResources().getStringArray(R.array
                .full_statistics_title);
        // 资源类型数组
        TypedArray images = getResources().obtainTypedArray(R.array.full_statistics_image);

        for (int i = 0; i < functionTitle.length; i++) {
            // 新建一个功能项标签
            Map<String, Object> function = new HashMap<>();

            // 添加标签资源
            // 添加标题
            function.put(FUNCTION_TITLE, functionTitle[i]);
            // 添加功能标签图标资源
            function.put(FUNCTION_IMAGE, images.getResourceId(i, R.mipmap.ic_launcher));

            // 将标签加入数据源
            dataList.add(function);
        }
        return dataList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Log.i(LOG_TAG + "onItemClick", "onItemClick is invoked");
        switch (position) {
            case 0:
                // 普通
                doGeneralFullStatistics();
                break;
            case 1:
                // 捣箱
                doMovedFullStatistics();
                break;
            default:
                break;
        }

    }

    /**
     * 普通全统计
     */
    private void doGeneralFullStatistics() {
        Intent intent = new Intent(this, GeneralFullStatisticsActivity.class);
        startActivity(intent);
    }

    /**
     * 捣箱全统计
     */
    private void doMovedFullStatistics() {
        Intent intent = new Intent(this, GeneralFullStatisticsActivity.class);
        startActivity(intent);
    }
}

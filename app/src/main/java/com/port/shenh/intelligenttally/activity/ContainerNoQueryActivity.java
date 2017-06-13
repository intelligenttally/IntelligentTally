package com.port.shenh.intelligenttally.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.port.shenh.intelligenttally.R;
import com.port.shenh.intelligenttally.function.ShipImageListFunction;
import com.port.shenh.intelligenttally.util.StaticValue;

import org.mobile.library.common.function.ToolbarInitialize;

import java.util.List;

import static android.widget.Toast.makeText;


/**
 * Created by shenh on 2017/05/22.
 */


/**
 * 箱号查询Activity
 *
 * @author sh
 * @version 1.0 2017/05/22
 * @since 1.0
 */
public class ContainerNoQueryActivity extends AppCompatActivity {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "ContainerNoQueryActivity.";

    /**
     * SearchView对象
     */
    private SearchView searchView = null;

    /**
     * 箱号列表
     */
    private ListView listView = null;

    /**
     * 查询关键字
     */
    private String query = null;

    /**
     * 船图数据功能类
     */
    private ShipImageListFunction shipImageListFunction = null;

    /**
     * 航次编码
     */
    String ship_id = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_container_no_query);

        // 初始化控件集
        initViewHolder();

        //初始化控件
        initView();

        //初始化数据
//        initData();


    }

    /**
     * 初始化控件集
     */
    private void initViewHolder() {

        searchView = (SearchView) findViewById(R.id.activity_container_no_query_search);

        listView = (ListView) findViewById(R.id.activity_container_no_query_listView);

        shipImageListFunction = new ShipImageListFunction(this);

        ship_id = (String) getIntent().getSerializableExtra(StaticValue.IntentTag
         .VOYAGE_TAG);

        Log.i(LOG_TAG + "initViewHolder", "ship_id is " + ship_id );

    }

    /**
     * 初始化控件
     */
    private void initView() {
        // 初始化Toolbar
        ToolbarInitialize.initToolbar(this, R.string.container_no_search, true, true);
        //初始化搜索
        initSearch();
    }

    /**
     * 初始化搜索
     */
    private void initSearch() {

        if (searchView != null) {

            searchView.setQueryHint(getString(R.string.search_hint));
            int id = searchView.getContext().getResources().getIdentifier
                    ("android:id/search_src_text", null, null);
            TextView textView = (TextView) searchView.findViewById(id);
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
                        listView.setAdapter(null);
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
        this.query = query.toUpperCase();
        loadData();
    }

    /**
     * 初始化数据
     */
    private void initData(){

        loadData();

    }

    /**
     * 加载数据
     *
     */
    private void loadData() {

        Log.i(LOG_TAG + "loadData", "loadData is invoked");

        //从本地加载箱号列表数据
        final List<String> list = shipImageListFunction.onLoadContainerNoListFromDataBase
                (ship_id, this.query);
        Log.i(LOG_TAG + "loadData", "list count is " + list.size());

        if (list.size() == 0){

            makeText(this, R.string.not_search_container_no, Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                list));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent();
                intent.putExtra(StaticValue.IntentTag.CONTAINER_NO_TAG, list.get(position));
                setResult(RESULT_OK, intent);

                finish();
            }
        });
    }

}

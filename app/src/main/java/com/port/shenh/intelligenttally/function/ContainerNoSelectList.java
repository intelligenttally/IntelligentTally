package com.port.shenh.intelligenttally.function;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.port.shenh.intelligenttally.R;
import com.port.shenh.intelligenttally.adapter.OnlyTextItemViewHolder;
import com.port.shenh.intelligenttally.adapter.OnlyTextRecyclerViewAdapter;
import com.port.shenh.intelligenttally.work.PullContainerNoList;

import org.mobile.library.model.function.ISelectList;
import org.mobile.library.model.operate.OnItemClickListenerForRecyclerViewItem;
import org.mobile.library.model.work.WorkBack;

import java.util.List;

import static android.widget.Toast.makeText;

/**
 * 箱号选择列表
 *
 * @author shenh
 * @version 1.0 2017/4/19
 * @since 1.0 2017/4/19
 */
public class ContainerNoSelectList implements ISelectList<View, String> {

    /**
     * 日志前缀
     */
    private static final String LOG_TAG = "ContainerNoSelectList.";

    /**
     * 结果监听器
     */
    private OnSelectedListener<View, String> onSelectedListener = null;

    /**
     * 上下文
     */
    private Context context = null;

    /**
     * 当前的选择布局
     */
    private View view = null;

    /**
     * 箱号
     */
    String keyword = null;

    /**
     * 航次
     */
    String ship_id = null;

    /**
     * 集装箱号列表
     */
    List<String> list = null;


    public ContainerNoSelectList(Context context, String ship_id, String keyWord) {
        this.context = context;

        this.ship_id = ship_id;
        this.keyword = keyWord;
    }

    public ContainerNoSelectList(Context context) {
        this.context = context;
    }

    /**
     * 设置参数
     *
     * @param ship_id 航次
     * @param keyword 关键字
     */
    public void setData(String ship_id, String keyword) {
        this.ship_id = ship_id;
        this.keyword = keyword;
    }


    @Override
    public void setOnSelectedListener(OnSelectedListener<View, String> onSelectedListener) {

        this.onSelectedListener = onSelectedListener;
    }

    @Override
    public View loadSelect() {

        view = onCreateView();

        return view;
    }

    /**
     * 创建布局
     *
     * @return 布局实例
     */
    private View onCreateView() {
        Log.i(LOG_TAG + "onCreateView", "onCreateView is invoked");
        Log.i(LOG_TAG + "onCreateView", "ship_id param is " + ship_id);
        Log.i(LOG_TAG + "onCreateView", "keyword param is " + keyword);

        View view = LayoutInflater.from(context).inflate(R.layout.only_recycler_view_layout, null);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id
                .only_recycler_view_layout_recyclerView);

        // 创建布局管理器
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);

        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        final OnlyTextRecyclerViewAdapter adapter = new OnlyTextRecyclerViewAdapter();

        if (onSelectedListener != null) {
            // 绑定点击事件
            adapter.setOnItemClickListener(new OnItemClickListenerForRecyclerViewItem<List<String>, OnlyTextItemViewHolder>() {
                @Override
                public void onClick(List<String> dataSource, OnlyTextItemViewHolder holder) {

                    int position = holder.getAdapterPosition();

                    Log.i(LOG_TAG + "loadData", "position is " + position);

                    onSelectedListener.onFinish(list.get(position));
                }
            });
        }

        recyclerView.setAdapter(adapter);


        if (ship_id != null && keyword != null) {

            PullContainerNoList pullContainerNoList = new PullContainerNoList();

            pullContainerNoList.setWorkEndListener(new WorkBack<List<String>>() {
                @Override
                public void doEndWork(boolean state, List<String> data) {


                    if (state) {
                        if (data != null) {
                            if (data.size() == 0) {

                                Toast.makeText(context, R.string
                                        .not_search_container_no, Toast.LENGTH_SHORT).show();

                            } else {


                                Log.i(LOG_TAG + "onCreateView", "dataList count is " + data.size());

                                adapter.setDataList(data);
                                list = data;
                                adapter.notifyDataSetChanged();
                            }


                        }
                    } else {

                        makeText(context, R.string.error_field_required, Toast.LENGTH_SHORT).show();

                    }

                }
            });

            pullContainerNoList.beginExecute(ship_id, keyword);
        }


        return view;
    }

}

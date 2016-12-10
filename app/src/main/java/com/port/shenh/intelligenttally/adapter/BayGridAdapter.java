package com.port.shenh.intelligenttally.adapter;
/**
 * Created by 超悟空 on 2016/12/10.
 */

import android.support.v7.widget.GridLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.port.shenh.intelligenttally.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 贝位图表格适配器
 *
 * @author 超悟空
 * @version 1.0 2016/12/10
 * @since 1.0
 */
public class BayGridAdapter {

    /**
     * 默认的表格控件索引数组的大小
     */
    private static final int DEFAULT_GRID_INDEX_SIZE = 31;

    /**
     * 表格布局
     */
    private GridLayout gridLayout = null;

    /**
     * 布局加载器
     */
    private LayoutInflater inflater = null;

    /**
     * 缓存的控件集合
     */
    private List<ViewHolder> viewHolderList = null;

    /**
     * 与实际表格排版同步的控件索引集合
     */
    private ViewHolder[][] gridViewIndex = null;

    /**
     * 当前表格控件索引数组的大小
     */
    private int gridViewIndexSize = DEFAULT_GRID_INDEX_SIZE;

    /**
     * 当前有效的表格控件行数
     */
    private int currentGridIndexMaxRow = 0;

    /**
     * 当前有效的表格控件列数
     */
    private int currentGridIndexMaxColumn = 0;

    /**
     * 当前空白行索引
     */
    private int blankRowIndex = 0;

    /**
     * 构造函数
     *
     * @param gridLayout 表格布局
     */
    public BayGridAdapter(GridLayout gridLayout) {
        this.gridLayout = gridLayout;
        inflater = LayoutInflater.from(gridLayout.getContext());
        viewHolderList = new ArrayList<>();
        gridViewIndex = new ViewHolder[gridViewIndexSize][gridViewIndexSize];
    }

    /**
     * 初始化表格
     */
    private void onInitGridLayout() {
        gridLayout.removeAllViews();
        gridLayout.setColumnCount(currentGridIndexMaxColumn);
        gridLayout.setRowCount(currentGridIndexMaxRow);

        for (int i = 0; i < currentGridIndexMaxRow * currentGridIndexMaxColumn; i++) {
            if (i >= viewHolderList.size()) {
                viewHolderList.add(onCreateViewHolder());
            }

            ViewHolder holder = viewHolderList.get(i);
            holder.rowIndex = i / currentGridIndexMaxColumn;
            holder.columnIndex = i % currentGridIndexMaxColumn;

            gridViewIndex[holder.rowIndex][holder.columnIndex] = holder;

            onBindViewHolder(holder);

            gridLayout.addView(holder.itemView);
        }
    }

    /**
     * 创建一个item
     *
     * @return item管理器
     */
    private ViewHolder onCreateViewHolder() {
        ViewHolder holder = new ViewHolder();

        holder.itemView = inflater.inflate(R.layout.layout_item_box, gridLayout, false);
        holder.labelTextView = (TextView) holder.itemView.findViewById(R.id
                .layout_item_box_textView);

        return holder;
    }

    /**
     * 设置item控件内容
     *
     * @param holder item管理器
     */
    private void onBindViewHolder(ViewHolder holder) {

    }

    /**
     * 表格项片段管理器
     */
    public class ViewHolder {

        /**
         * 根布局
         */
        public View itemView = null;

        /**
         * 行索引
         */
        public int rowIndex = -1;

        /**
         * 列索引
         */
        public int columnIndex = -1;

        /**
         * item标签内容
         */
        public TextView labelTextView = null;
    }
}

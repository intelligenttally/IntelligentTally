package com.port.shenh.intelligenttally.adapter;
/**
 * Created by 超悟空 on 2016/12/10.
 */

import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.port.shenh.intelligenttally.R;
import com.port.shenh.intelligenttally.bean.Bay;
import com.port.shenh.intelligenttally.bean.ShipImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 贝位图表格适配器
 *
 * @author 超悟空
 * @version 1.0 2016/12/10
 * @since 1.0
 */
public class BayGridAdapter {

    /**
     * 甲板船图前缀
     */
    private static final String UP = "board";

    /**
     * 船舱船图前缀
     */
    private static final String DOWN = "cabin";

    /**
     * 背景颜色索引偏移量
     */
    private static final int BACKGROUND_COLOR_INDEX_OFFSET = 3;

    /**
     * item填充文本色
     */
    private int[] textColors = null;

    /**
     * 上一个选中的控件
     */
    private ViewHolder beforeHolder = null;

    /**
     * 区分卸货港颜色的索引
     */
    private Map<String, Integer> colorMap = null;

    /**
     * 甲板表格布局
     */
    private GridLayout upGridLayout = null;

    /**
     * 甲板左侧编号布局
     */
    private GridLayout upLeftGridLayout = null;

    /**
     * 船舱表格布局
     */
    private GridLayout downGridLayout = null;

    /**
     * 船舱左侧编号布局
     */
    private GridLayout downLeftGridLayout = null;

    /**
     * 布局加载器
     */
    private LayoutInflater inflater = null;

    /**
     * 缓存的控件集合
     */
    private List<ViewHolder> viewHolderList = null;

    /**
     * 船图数据表
     */
    private Map<String, ShipImage> dataMap = null;

    /**
     * item点击事件
     */
    private OnGridItemClickListener onGridItemClickListener = null;

    /**
     * 当前有效的表格控件行数
     */
    private int currentUpGridIndexMaxRow = 0;

    /**
     * 当前有效的表格控件列数
     */
    private int currentUpGridIndexMaxColumn = 0;

    /**
     * 当前有效的表格控件行数
     */
    private int currentDownGridIndexMaxRow = 0;

    /**
     * 当前有效的表格控件列数
     */
    private int currentDownGridIndexMaxColumn = 0;

    /**
     * 当前读取的item控件集缓存索引
     */
    private int viewHolderCursor = 0;

    /**
     * 当前有船图数据的item管理器在item控件集缓存中的索引偏移量
     */
    private int dataViewHolderIndexOffset = 0;

    /**
     * 当前使用到的颜色序号
     */
    private int currentColorIndex = 0;

    /**
     * 构造函数
     *
     * @param activity 引用的界面，用于获取表格控件
     */
    public BayGridAdapter(AppCompatActivity activity) {
        this.upGridLayout = (GridLayout) activity.findViewById(R.id.activity_bay_up_gridLayout);
        this.upLeftGridLayout = (GridLayout) activity.findViewById(R.id
                .activity_bay_up_left_gridLayout);
        this.downGridLayout = (GridLayout) activity.findViewById(R.id.activity_bay_down_gridLayout);
        this.downLeftGridLayout = (GridLayout) activity.findViewById(R.id
                .activity_bay_down_left_gridLayout);

        inflater = LayoutInflater.from(activity);
        viewHolderList = new ArrayList<>();
        dataMap = new HashMap<>();

        TypedArray typedArray = activity.getResources().obtainTypedArray(R.array.box_text_colors);

        textColors = new int[typedArray.length()];

        for (int i = 0; i < typedArray.length(); i++) {
            textColors[i] = typedArray.getColor(i, Color.BLACK);
        }

        typedArray.recycle();
    }

    /**
     * 设置船图item点击事件
     *
     * @param onGridItemClickListener 事件监听器
     */
    public void setOnGridItemClickListener(OnGridItemClickListener onGridItemClickListener) {
        this.onGridItemClickListener = onGridItemClickListener;
    }

    /**
     * 显示一个贝图
     *
     * @param bay           贝实例
     * @param shipImageList 船图实例列表
     */
    public void showBay(Bay bay, List<ShipImage> shipImageList) {
        if (bay == null || shipImageList == null) {
            return;
        }

        onRelease();

        onSortData(shipImageList);

        initResource(bay);

        onInitGridLayout();
    }

    /**
     * 整理数据
     *
     * @param dataList 数据源
     */
    private void onSortData(List<ShipImage> dataList) {

        for (ShipImage data : dataList) {
            if (data != null) {
                if ("board".equals(data.getLocation())) {
                    // 甲板上
                    dataMap.put(UP + data.getScreen_row() + data.getScreen_col(), data);
                } else {
                    // 船舱内
                    dataMap.put(DOWN + data.getScreen_row() + data.getScreen_col(), data);
                }
            }
        }
    }

    /**
     * 释放资源
     */
    private void onRelease() {
        upGridLayout.removeAllViews();
        upLeftGridLayout.removeAllViews();
        downGridLayout.removeAllViews();
        downLeftGridLayout.removeAllViews();

        beforeHolder = null;
        dataMap.clear();
        colorMap.clear();

        for (ViewHolder holder : viewHolderList) {
            holder.itemView.setOnClickListener(null);
        }
    }

    /**
     * 初始化资源
     *
     * @param bay 贝数据
     */
    private void initResource(Bay bay) {

        viewHolderCursor = 0;
        currentColorIndex = 0;

        currentUpGridIndexMaxRow = bay.getSumScreenRow_board();
        currentUpGridIndexMaxColumn = bay.getSumScreenCol_board();
        currentDownGridIndexMaxRow = bay.getSumScreenRow_cabin();
        currentDownGridIndexMaxColumn = bay.getSumScreenCol_cabin();

        dataViewHolderIndexOffset = currentUpGridIndexMaxRow + currentDownGridIndexMaxRow +
                currentUpGridIndexMaxColumn;

        // item控件集缓存扩容
        int size = (currentUpGridIndexMaxRow + 1) * (currentUpGridIndexMaxColumn + 1) +
                (currentDownGridIndexMaxRow + 1) * (currentDownGridIndexMaxColumn + 1) - 2;

        if (size > viewHolderList.size()) {
            for (int i = viewHolderList.size(); i < size; i++) {
                viewHolderList.add(onCreateViewHolder());
            }
        }

        // 设置表格大小
        upGridLayout.setRowCount(currentUpGridIndexMaxRow + 1);
        upGridLayout.setColumnCount(currentUpGridIndexMaxColumn);
        downGridLayout.setRowCount(currentDownGridIndexMaxRow + 1);
        downGridLayout.setColumnCount(currentDownGridIndexMaxColumn);
    }

    /**
     * 初始化表格
     */
    private void onInitGridLayout() {

        onInitLeftGridLayout();

        onInitUpGridLayout();

        onInitDownGridLayout();
    }

    /**
     * 初始化船舱部分的表格
     */
    private void onInitDownGridLayout() {
        for (int i = 1; i <= currentDownGridIndexMaxRow; i++) {
            for (int j = 1; j <= currentDownGridIndexMaxColumn; j++) {
                ViewHolder holder = viewHolderList.get(viewHolderCursor++);
                holder.rowIndex = i;
                holder.columnIndex = j;
                holder.itemGrid = 2;

                onBindViewHolder(holder);

                downGridLayout.addView(holder.itemView);
            }
        }

        for (int i = 0; i < currentDownGridIndexMaxColumn; i++) {
            ViewHolder holder = viewHolderList.get(viewHolderCursor++);
            holder.rowIndex = currentDownGridIndexMaxRow + 1;
            holder.columnIndex = i;
            holder.itemGrid = 6;

            // 设置编号和样式
            holder.itemView.getBackground().setLevel(0);
            holder.labelTextView.setText(onHorizontalNumberCompute(i,
                    currentDownGridIndexMaxColumn));

            downGridLayout.addView(holder.itemView);
        }
    }

    /**
     * 初始化甲板部分的表格
     */
    private void onInitUpGridLayout() {

        for (int i = 0; i < currentUpGridIndexMaxColumn; i++) {
            ViewHolder holder = viewHolderList.get(viewHolderCursor++);
            holder.rowIndex = 0;
            holder.columnIndex = i;
            holder.itemGrid = 4;

            // 设置编号和样式
            holder.itemView.getBackground().setLevel(0);
            holder.labelTextView.setText(onHorizontalNumberCompute(i, currentUpGridIndexMaxColumn));

            upGridLayout.addView(holder.itemView);
        }

        // 船图数据部分
        for (int i = 1; i <= currentUpGridIndexMaxRow; i++) {
            for (int j = 1; j <= currentUpGridIndexMaxColumn; j++) {
                ViewHolder holder = viewHolderList.get(viewHolderCursor++);
                holder.rowIndex = i;
                holder.columnIndex = j;
                holder.itemGrid = 1;

                onBindViewHolder(holder);

                upGridLayout.addView(holder.itemView);
            }
        }
    }

    /**
     * 计算横向的编号值
     *
     * @param index 从左至右从0开始的索引
     * @param count 总列数
     *
     * @return 字符串形式的编号值
     */
    private String onHorizontalNumberCompute(int index, int count) {
        int center = count / 2;

        int value;

        if (count % 2 == 0) {
            if (index < center) {
                value = count - index * 2;
            } else {
                value = index * 2 - count + 1;
            }
        } else {
            if (index < center) {
                value = count - index * 2 - 1;
            } else if (index == center) {
                value = 0;
            } else {
                value = index * 2 - count;
            }
        }

        return value < 10 ? "0" + value : String.valueOf(value);
    }

    /**
     * 初始化左侧编号
     */
    private void onInitLeftGridLayout() {
        // 甲板编号
        for (int i = currentUpGridIndexMaxRow; i > 0; i--) {
            ViewHolder holder = viewHolderList.get(viewHolderCursor++);
            holder.rowIndex = i;
            holder.columnIndex = 0;
            holder.itemGrid = 3;

            // 设置编号和样式
            holder.itemView.getBackground().setLevel(0);
            holder.labelTextView.setText(String.valueOf(i * 2 + 80));

            upLeftGridLayout.addView(holder.itemView);
        }

        // 船舱编号
        for (int i = currentDownGridIndexMaxRow; i > 0; i--) {
            ViewHolder holder = viewHolderList.get(viewHolderCursor++);
            holder.rowIndex = i;
            holder.columnIndex = 0;
            holder.itemGrid = 5;

            // 设置编号和样式
            holder.itemView.getBackground().setLevel(0);
            String value = i < 5 ? "0" + String.valueOf(i * 2) : String.valueOf(i * 2);
            holder.labelTextView.setText(value);

            downLeftGridLayout.addView(holder.itemView);
        }
    }

    /**
     * 创建一个item
     *
     * @return item管理器
     */
    private ViewHolder onCreateViewHolder() {
        ViewHolder holder = new ViewHolder();

        holder.itemView = inflater.inflate(R.layout.layout_item_box, null, false);
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

        ShipImage data;

        if (holder.itemGrid == 1) {
            data = dataMap.get(UP + holder.rowIndex + holder.columnIndex);
        } else {
            data = dataMap.get(DOWN + holder.rowIndex + holder.columnIndex);
        }

        if (data == null) {
            holder.itemView.getBackground().setLevel(0);
            holder.labelTextView.setText(null);
            return;
        }

        if ("0".equals(data.getUser_char())) {
            holder.itemView.getBackground().setLevel(1);
            holder.labelTextView.setText(null);
        } else if ("1".equals(data.getJoint())) {
            holder.itemView.getBackground().setLevel(2);
            holder.labelTextView.setText(null);
        } else {
            int colorIndex;

            if (colorMap.containsKey(data.getCode_unload_port())) {
                colorIndex = colorMap.get(data.getCode_unload_port());
            } else {
                colorIndex = currentColorIndex++;
                colorMap.put(data.getCode_unload_port(), colorIndex);
            }

            if ("1".equals(data.getUnload_mark())) {
                holder.itemView.getBackground().setLevel(colorIndex +
                        BACKGROUND_COLOR_INDEX_OFFSET);
                holder.labelTextView.setTextColor(Color.WHITE);
            } else {
                holder.itemView.getBackground().setLevel(1);
                holder.labelTextView.setTextColor(textColors[colorIndex]);
            }

            String container = "";

            if ("0".equals(data.getMoved())) {
                if ("E".equals(data.getCode_empty().trim().toUpperCase())) {
                    container = "e";
                } else if (!TextUtils.isEmpty(data.getDegree_unit()) && !TextUtils.isEmpty(data
                        .getDanger_grade())) {
                    container = "k";
                } else if (!TextUtils.isEmpty(data.getDanger_grade())) {
                    container = "d";
                } else if (!TextUtils.isEmpty(data.getDegree_unit())) {
                    container = "r";
                }
            } else {
                container = "*";
            }

            holder.labelTextView.setText(String.format("%s%s", data.getCode_unload_port().charAt
                    (0), container));
        }

        int index = getDataViewHolderIndex(holder.rowIndex, holder.columnIndex, holder.itemGrid
                == 1);
        holder.itemView.setTag(index);
        holder.itemView.setOnClickListener(onClickListener);
    }

    /**
     * 绑定在表格item上的点击事件
     */
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (onGridItemClickListener != null) {
                int index = (int) v.getTag();
                ViewHolder holder = viewHolderList.get(index);
                if (beforeHolder != null) {
                    beforeHolder.itemView.setSelected(false);
                }
                holder.itemView.setSelected(true);
                beforeHolder = holder;
                String tag = (holder.itemGrid == 1 ? UP : DOWN) + holder.rowIndex + holder
                        .columnIndex;
                onGridItemClickListener.onClick(holder, dataMap.get(tag));
            }
        }
    };

    /**
     * 得到有船图数据的ViewHolder在控件缓存中的索引
     *
     * @param row      船图屏幕行索引
     * @param column   船图屏幕列索引
     * @param upOrDown true表示甲板，false表示船舱
     *
     * @return 在控件缓存中的真实索引
     */
    private int getDataViewHolderIndex(int row, int column, boolean upOrDown) {
        if (upOrDown) {
            return (row - 1) * currentUpGridIndexMaxColumn + column - 1 + dataViewHolderIndexOffset;
        } else {
            return (row - 1) * currentDownGridIndexMaxColumn + column - 1 +
                    currentUpGridIndexMaxRow * currentUpGridIndexMaxColumn +
                    dataViewHolderIndexOffset;
        }
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
         * 行索引(船图屏幕索引)
         */
        public int rowIndex = -1;

        /**
         * 列索引(船图屏幕索引)
         */
        public int columnIndex = -1;

        /**
         * item标签内容
         */
        public TextView labelTextView = null;

        /**
         * 当前item所在布局，1为甲板船图表格，2为船舱船图表格，3为甲板左编号，4为甲板顶编号，5为船舱左编号，6为船舱底编号
         */
        public int itemGrid = 1;
    }

    /**
     * 表格item点击事件监听器
     */
    public interface OnGridItemClickListener {

        /**
         * 点击事件
         *
         * @param holder 控件管理器
         * @param data   item绑定的船图数据
         */
        void onClick(ViewHolder holder, ShipImage data);
    }
}

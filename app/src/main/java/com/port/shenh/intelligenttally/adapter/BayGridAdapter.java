package com.port.shenh.intelligenttally.adapter;
/**
 * Created by 超悟空 on 2016/12/10.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
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
     * 日志标签前缀
     */
    private static final String LOG_TAG = "BayGridAdapter.";

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
     * 默认文本控件字体颜色
     */
    private int defaultTextColor = 0;

    /**
     * item填充文本色
     */
    private int[] textColors = null;

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
     * 卸货港简拼
     */
    private Map<String, String> unloadSort = null;

    /**
     * item点击事件
     */
    private OnGridItemClickListener onGridItemClickListener = null;

    /**
     * 当前有效的甲板最大行
     */
    private int currentUpGridIndexMaxRow = 0;

    /**
     * 当前有效的甲板最小行
     */
    private int currentUpGridIndexMinRow = 0;

    /**
     * 当前有效的甲板最大列
     */
    private int currentUpGridIndexMaxColumn = 0;

    /**
     * 当前有效的船舱最大行
     */
    private int currentDownGridIndexMaxRow = 0;

    /**
     * 当前有效的船舱最小行
     */
    private int currentDownGridIndexMinRow = 0;

    /**
     * 当前有效的船舱最大列
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
     * 屏幕宽度
     */
    private int screenWidth = 0;
    /**
     * 屏幕高度
     */
    private int screenheight = 0;

    /**
     * 当前有效最大列
     */
    private int currentMaxColumn = 0;

    /**
     * 左侧编号文本大小
     */
    private int leftGridTextSize = 16;

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
        colorMap = new HashMap<>();

        TypedArray typedArray = activity.getResources().obtainTypedArray(R.array.box_text_colors);
        textColors = new int[typedArray.length()];

        for (int i = 0; i < typedArray.length(); i++) {
            textColors[i] = typedArray.getColor(i, Color.BLACK);
        }

        typedArray.recycle();

        typedArray = activity.obtainStyledAttributes(new int[]{android.R.attr.textColorSecondary});

        defaultTextColor = typedArray.getColor(0, Color.DKGRAY);
        typedArray.recycle();


        WindowManager wm = (WindowManager) activity.getApplication().getSystemService(Context
                .WINDOW_SERVICE);

        DisplayMetrics metric = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metric);
        screenWidth = metric.widthPixels;     // 屏幕宽度（像素）
        screenheight = metric.heightPixels;   // 屏幕高度（像素）

        Log.i(LOG_TAG + "BayGridAdapter", "width is" + screenWidth + "  " + "height is" +
                screenheight);
    }

    /**
     * 设置卸货港简拼
     *
     * @param unloadSort 简拼集合
     */
    public void setUnloadSort(Map<String, String> unloadSort) {
        this.unloadSort = unloadSort == null ? new HashMap<String, String>() : unloadSort;
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

        dataMap.clear();
    }

    /**
     * 初始化资源
     *
     * @param bay 贝数据
     */
    private void initResource(Bay bay) {

        viewHolderCursor = 0;

        currentUpGridIndexMaxRow = bay.getSumScreenRow_board();
        currentUpGridIndexMinRow = bay.getMinScreenRow_board();
        currentUpGridIndexMaxColumn = bay.getSumScreenCol_board();
        currentDownGridIndexMaxRow = bay.getSumScreenRow_cabin();
        currentDownGridIndexMinRow = bay.getMinScreenRow_cabin();
        currentDownGridIndexMaxColumn = bay.getSumScreenCol_cabin();
        Log.i(LOG_TAG + "initResource", "currentUpGridIndexMaxRow is " + currentUpGridIndexMaxRow);
        Log.i(LOG_TAG + "initResource", "currentUpGridIndexMinRow is " + currentUpGridIndexMinRow);
        Log.i(LOG_TAG + "initResource", "currentUpGridIndexMaxColumn is " +
                currentUpGridIndexMaxColumn);
        Log.i(LOG_TAG + "initResource", "currentDownGridIndexMaxRow is " +
                currentDownGridIndexMaxRow);
        Log.i(LOG_TAG + "initResource", "currentDownGridIndexMinRow is " +
                currentDownGridIndexMinRow);
        Log.i(LOG_TAG + "initResource", "currentDownGridIndexMaxColumn is " +
                currentDownGridIndexMaxColumn);

        currentMaxColumn = currentUpGridIndexMaxColumn > currentDownGridIndexMaxColumn ?
                currentUpGridIndexMaxColumn : currentDownGridIndexMaxColumn;

        dataViewHolderIndexOffset = currentUpGridIndexMaxRow - currentUpGridIndexMinRow +
                currentDownGridIndexMaxRow - currentDownGridIndexMinRow +
                currentUpGridIndexMaxColumn + 2;

        //对偏移量进行修正
        if (currentUpGridIndexMaxRow == 0 || currentDownGridIndexMaxRow == 0) {
            dataViewHolderIndexOffset--;
        }

        // item控件集缓存扩容
        int size = (currentUpGridIndexMaxRow - currentUpGridIndexMinRow + 2) *
                (currentUpGridIndexMaxColumn + 1) + (currentDownGridIndexMaxRow -
                currentDownGridIndexMinRow + 2) * (currentDownGridIndexMaxColumn + 1) - 2;

        if (size > viewHolderList.size()) {
            for (int i = viewHolderList.size(); i < size; i++) {
                viewHolderList.add(onCreateViewHolder());
            }
        }

        // 设置表格大小
        //        downGridLayout.setRowCount(currentDownridIndexMaxRow -
        // currentDownGridIndexMinRow + 2);
        Log.i(LOG_TAG + "initResource", "currentDownGridIndexMaxRow is " +
                currentDownGridIndexMaxRow);
        downGridLayout.setRowCount(currentDownGridIndexMaxRow - currentDownGridIndexMinRow + 1);
        downGridLayout.setColumnCount(currentDownGridIndexMaxColumn);
        upGridLayout.setRowCount(currentUpGridIndexMaxRow - currentUpGridIndexMinRow + 1);
        upGridLayout.setColumnCount(currentUpGridIndexMaxColumn);
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
        for (int i = currentDownGridIndexMaxRow; i >= currentDownGridIndexMinRow; i--) {
            for (int j = 1; j <= currentDownGridIndexMaxColumn; j++) {
                ViewHolder holder = viewHolderList.get(viewHolderCursor++);
                holder.rowIndex = i;
                holder.columnIndex = j;
                holder.itemGrid = 2;

                onResetViewLayoutParams(holder.itemView);
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
            onSetNumberView(holder, onHorizontalNumberCompute(i, currentDownGridIndexMaxColumn));

            onResetViewLayoutParams(holder.itemView);
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
            onSetNumberView(holder, onHorizontalNumberCompute(i, currentUpGridIndexMaxColumn));

            onResetViewLayoutParams(holder.itemView);
            upGridLayout.addView(holder.itemView);
        }

        // 船图数据部分
        for (int i = currentUpGridIndexMaxRow; i >= currentUpGridIndexMinRow; i--) {
            for (int j = 1; j <= currentUpGridIndexMaxColumn; j++) {
                ViewHolder holder = viewHolderList.get(viewHolderCursor++);
                holder.rowIndex = i;
                holder.columnIndex = j;
                holder.itemGrid = 1;

                onResetViewLayoutParams(holder.itemView);
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

        getLeftGridTextSize(currentMaxColumn);

        if (currentUpGridIndexMaxRow > 0) {
            // 甲板编号
            for (int i = currentUpGridIndexMaxRow; i >= currentUpGridIndexMinRow; i--) {
                ViewHolder holder = viewHolderList.get(viewHolderCursor++);
                holder.rowIndex = i;
                holder.columnIndex = 0;
                holder.itemGrid = 3;

                // 设置编号和样式
                // onSetNumberView(holder, String.valueOf((i - currentUpGridIndexMinRow + 1) * 2 +
                //                        80));
                onSetNumberView(holder, String.valueOf((i - 1) * 2 + 80));
                //
                onResetViewLayoutParams(holder.itemView);
                upLeftGridLayout.addView(holder.itemView);
            }
        }

        if (currentDownGridIndexMaxRow > 0) {
            // 船舱编号
            for (int i = currentDownGridIndexMaxRow; i >= currentDownGridIndexMinRow; i--) {
                ViewHolder holder = viewHolderList.get(viewHolderCursor++);
                holder.rowIndex = i;
                holder.columnIndex = 0;
                holder.itemGrid = 5;

                // 设置编号和样式
                String value = i < 5 ? "0" + String.valueOf(i * 2) : String.valueOf(i * 2);
                onSetNumberView(holder, value);

                onResetViewLayoutParams(holder.itemView);
                downLeftGridLayout.addView(holder.itemView);
            }
        }
    }

    /**
     * 获取左侧编号文本大小
     *
     * @param maxColumn 最大列数
     */
    private void getLeftGridTextSize(int maxColumn) {

        if (maxColumn <= 10) {
            leftGridTextSize = 22;
        } else if (maxColumn < 16 & maxColumn > 10) {
            leftGridTextSize = 18;
        } else if (maxColumn >= 16) {
            leftGridTextSize = 14;
        }

    }

    /**
     * 设置一个编号item
     *
     * @param holder 控件管理器
     * @param number 编号
     */
    private void onSetNumberView(ViewHolder holder, String number) {
        holder.itemView.getBackground().setLevel(0);
        holder.labelTextView.setText(number);
        holder.labelTextView.setTextSize(leftGridTextSize);
        holder.labelTextView.setTextColor(defaultTextColor);
    }

    /**
     * 重置item索引的默认序号
     */
    private static final GridLayout.Spec UNDEFINED_SPEC = GridLayout.spec(GridLayout.UNDEFINED);

    /**
     * 重置item控件参数
     *
     * @param view item控件
     */
    private void onResetViewLayoutParams(View view) {
        view.setOnClickListener(null);
        if (view.getLayoutParams() instanceof GridLayout.LayoutParams) {
            GridLayout.LayoutParams layoutParams = (GridLayout.LayoutParams) view.getLayoutParams();
            layoutParams.rowSpec = UNDEFINED_SPEC;
            layoutParams.columnSpec = UNDEFINED_SPEC;
            layoutParams.width = (screenWidth - 76 - (currentMaxColumn + 1) * 2) /
                    (currentMaxColumn + 1);
            layoutParams.height = layoutParams.width;
            layoutParams.setMargins(2, 2, 2, 2);

            Log.i(LOG_TAG + "onResetViewLayoutParams", "layoutParams.width is" + layoutParams
                    .width + "  " + "layoutParams.height is" + layoutParams.height);


        }
    }

    /**
     * 创建一个item
     *
     * @return item管理器
     */
    private ViewHolder onCreateViewHolder() {
        ViewHolder holder = new ViewHolder();

        holder.itemView = inflater.inflate(R.layout.layout_item_box, upGridLayout, false);
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

        if (data == null || "0".equals(data.getUser_char())) {
            holder.itemView.getBackground().setLevel(0);
            holder.labelTextView.setText(null);
            return;
        }

        if (TextUtils.isEmpty(data.getBayno())) {
            holder.itemView.getBackground().setLevel(1);
            holder.labelTextView.setText(null);
        } else if (data.getBaynum().compareTo(data.getBay_num()) < 0) {
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

            // 卸货港简拼
            String unloadPort = unloadSort.get(data.getCode_unload_port());

            if (unloadPort == null) {
                unloadPort = data.getCode_unload_port();
            }

            holder.labelTextView.setText(String.format("%s%s", unloadPort, container));
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
            return (currentUpGridIndexMaxRow - row) * currentUpGridIndexMaxColumn + column - 1 +
                    dataViewHolderIndexOffset;
        } else {
            return (currentDownGridIndexMaxRow - row) * currentDownGridIndexMaxColumn + column - 1 +
                    (currentUpGridIndexMaxRow - currentUpGridIndexMinRow + 1) *
                            currentUpGridIndexMaxColumn + dataViewHolderIndexOffset;
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

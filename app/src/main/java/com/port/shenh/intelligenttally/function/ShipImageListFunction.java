package com.port.shenh.intelligenttally.function;
/**
 * Created by sh on 2016/11/25.
 */

import android.content.Context;
import android.util.Log;

import com.port.shenh.intelligenttally.bean.ShipImage;
import com.port.shenh.intelligenttally.database.ShipImageOperator;
import com.port.shenh.intelligenttally.work.PullShipImageList;

import org.mobile.library.model.database.BaseOperator;

import java.util.List;

/**
 * 船图数据功能类
 *
 * @author sh
 * @version 1.1 2016/11/25
 * @since 1.0
 */
public class ShipImageListFunction{

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "ShipImageListFunction.";

    /**
     * 数据集合
     */
    private List<ShipImage> dataList = null;

    /**
     * 上下文
     */
    private Context context = null;

    /**
     * 数据库操作工具
     */
    private BaseOperator<ShipImage> operator = null;

    /**
     * 标识是否正在加载数据
     */
    protected volatile boolean loading = false;

    /**
     * 标识工具加载是否被取消
     */
    protected volatile boolean canceled = false;

    /**
     * 数据提取条件参数
     */
    private String parameter;

    /**
     * 构造函数
     *
     * @param context 上下文
     */
    public ShipImageListFunction(Context context) {
        this.context = context;
        Log.i(LOG_TAG + "BaseDataListFunction", "BaseDataListFunction is invoked");
    }

    /**
     * 构造函数
     *
     * @param context   上下文
     * @param parameter 取值条件参数
     */
    public ShipImageListFunction(Context context, String parameter) {
        this.context = context;
        this.parameter = parameter;
        Log.i(LOG_TAG + "BaseDataListFunction", "BaseDataListFunction is invoked");
    }

    /**
     * 创建数据库操作对象
     *
     * @param context 上下文
     * @return 数据库操作对象
     */
    private BaseOperator<ShipImage> onCreateOperator(Context context) {
        return new ShipImageOperator(context);
    }

    /**
     * 判断是否正在加载数据
     *
     * @return true表示正在加载数据
     */
    public boolean isLoading() {
        return loading;
    }

    /**
     * 取消数据加载，将不发出完成通知
     */
    public void cancel() {
        canceled = true;
    }

    /**
     * 数据加载
     */
    public void onLoad() {
        Log.i(LOG_TAG + "onCreate", "getDataList is invoked");
        // 加载开始
        loading = true;

        if (!canceled && (dataList == null || dataList.size() == 0)) {
            // 从网络拉取
            Log.i(LOG_TAG + "onCreate", "from network");
            onLoadFromNetWork(parameter);
        } else {
            if (!canceled) {
                onNotify(context);
            }
            // 加载结束
            loading = false;
        }
    }

    /**
     * 升级数据
     */
    public void onUpdate() {
        Log.i(LOG_TAG + "onUpdate", "getDataList is invoked");
        // 加载开始
        loading = true;

        if (!canceled) {
            // 从网络拉取
            Log.i(LOG_TAG + "onCreate", "from network");
            onLoadFromNetWork(parameter);
        }
    }

    /**
     * 通知该工具已存在，不必重新加载
     */
    public final void notifyExist() {
        onNotify(context);
    }

    /**
     * 获取数据集合
     *
     * @return 数据集合
     */
    public List<ShipImage> getDataList() {
        Log.i(LOG_TAG + "getDataList", "getDataList is invoked");

        return dataList;
    }

//    /**
//     * 从数据库加载数据
//     *
//     * @param operator 数据库操作对象
//     *
//     * @return 数据集合
//     */
//    private List<ShipImage> onLoadFromDataBase(BaseOperator<ShipImage> operator, String
//            parameter) {
//        if (operator == null || operator.isEmpty()) {
//            Log.i(LOG_TAG + "onLoadFromDataBase", "database null");
//            return null;
//        }
//
//        return operator.queryAll();
//    }

    /**
     * 从网络加载数据，
     * 完成请求后要调用{@link #netWorkEndSetData(boolean , List)}继续执行后续任务
     *
     * @param parameter 取值条件参数
     */
     private void onLoadFromNetWork(String parameter)
     {
         Log.i(LOG_TAG + "onLoadFromNetWork", "parameter is " + parameter);

         PullShipImageList workModel = new PullShipImageList();

         Log.i(LOG_TAG + "netWorkEndSetData", "beginExecute is invoked");
         workModel.beginExecute(parameter);

         Log.i(LOG_TAG + "netWorkEndSetData", "getResult is invoked");
         netWorkEndSetData(true, workModel.getResult());
     }

    /**
     * 网络请求结束后调用
     *
     * @param state 网络任务执行结果
     * @param data  结果数据
     */
    private void netWorkEndSetData(boolean state, List<ShipImage> data) {
        Log.i(LOG_TAG + "netWorkEndSetData", "result is " + state);

        if (!canceled) {
            // 提取结果
            Log.i(LOG_TAG + "netWorkEndSetData", "onNetworkEnd is invoked");
            dataList = onNetworkEnd(state, data);
        }

        if (!canceled) {
            // 保存数据
            Log.i(LOG_TAG + "netWorkEndSetData", "onSaveData is invoked");
            onSaveData(operator, dataList);
        }

        if (!canceled) {
            onNotify(context);
        }
        // 加载结束
        loading = false;
    }

    /**
     * 整理从服务器取回的数据
     *
     * @param state 执行结果
     * @param data  响应数据
     *
     * @return 整理好的数据集
     */
    private List<ShipImage> onNetworkEnd(boolean state, List<ShipImage> data) {
        return state ? data : null;
    }

    /**
     * 将服务器取回的数据持久化到本地
     *
     * @param operator 数据库操作类
     * @param dataList 数据集
     */
    private void onSaveData(BaseOperator<ShipImage> operator, List<ShipImage> dataList) {
        if (!canceled && operator != null && dataList != null) {
            operator.clear();
            operator.insert(dataList);
        }
    }

    /**
     * 数据加载结束发送广播通知
     */
    public void onNotify(Context context) {
    }

    /**
     * 清空表
     */
    public void onClear()
    {
        Log.i(LOG_TAG + "onClear", "clear is invoked");

        Log.i(LOG_TAG + "onCreate", "onCreateOperator is invoked");
        this.operator = onCreateOperator(context);
        operator.clear();
    }


}

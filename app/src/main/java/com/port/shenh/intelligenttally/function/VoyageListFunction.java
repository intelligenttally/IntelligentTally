package com.port.shenh.intelligenttally.function;
/**
 * Created by sh on 2016/11/25.
 */

import android.content.Context;
import android.util.Log;

import com.port.shenh.intelligenttally.bean.Voyage;
import com.port.shenh.intelligenttally.database.VoyageOperator;
import com.port.shenh.intelligenttally.work.PullVoyageListOfDownloaded;

import org.mobile.library.model.database.BaseOperator;
import org.mobile.library.model.work.WorkBack;

import java.util.List;

/**
 * 航次数据功能类
 *
 * @author sh
 * @version 1.1 2016/11/25
 * @since 1.0
 */
public class VoyageListFunction {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "VoyageListFunction.";

    /**
     * 数据集合
     */
    private List<Voyage> dataList = null;

    /**
     * 上下文
     */
    private Context context = null;

    /**
     * 数据库操作工具
     */
    private VoyageOperator operator = null;

    /**
     * 标识是否正在加载数据
     */
    protected volatile boolean loading = false;

    /**
     * 标识工具加载是否被取消
     */
    protected volatile boolean canceled = false;

    /**
     * 构造函数
     *
     * @param context 上下文
     */
    public VoyageListFunction(Context context) {
        this.context = context;
        Log.i(LOG_TAG + "onCreate", "onCreateOperator is invoked");
        this.operator = (VoyageOperator) onCreateOperator(context);
    }

    public interface OnLoadEndListener {

        void OnLoadEnd();
    }

    OnLoadEndListener onLoadEndListener;

    public interface OnClearEndListener {

        void OnClearEnd();
    }

    OnClearEndListener onClearEndListener;

    /**
     * 加载结束监听事件
     *
     * @param onLoadEndListener
     */
    public void setOnLoadEndListener(OnLoadEndListener onLoadEndListener) {
        this.onLoadEndListener = onLoadEndListener;
    }

    /**
     * 清楚缓存结束监听事件
     *
     * @param onClearEndListener
     */
    public void setOnClearEndListener(OnClearEndListener onClearEndListener) {
        this.onClearEndListener = onClearEndListener;
    }

    /**
     * 创建数据库操作对象
     *
     * @param context 上下文
     * @return 数据库操作对象
     */
    private BaseOperator<Voyage> onCreateOperator(Context context) {
        return new VoyageOperator(context);
    }

    /**
     * 数据加载
     *
     * @param parameter 取值条件参数
     */
    public void onLoad(String parameter) {
        Log.i(LOG_TAG + "onCreate", "getDataList is invoked");
        // 加载开始
        loading = true;
        dataList = null;

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
     *
     * @param parameter 取值条件参数
     */
    public void onUpdate(String parameter) {
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
     * 从网络加载数据，
     * 完成请求后要调用{@link #netWorkEndSetData(boolean, List)}继续执行后续任务
     *
     * @param parameter 取值条件参数
     */
    private void onLoadFromNetWork(String parameter) {
        Log.i(LOG_TAG + "onLoadFromNetWork", "parameter is " + parameter);

        final PullVoyageListOfDownloaded workModel = new PullVoyageListOfDownloaded();

        workModel.setWorkEndListener(new WorkBack<List<Voyage>>() {
            @Override
            public void doEndWork(boolean state, List<Voyage> data) {
                if (state && data != null) {

                    Log.i(LOG_TAG + "netWorkEndSetData", "getResult is invoked");
                    netWorkEndSetData(state, data);
                }

            }
        }, false);

        Log.i(LOG_TAG + "netWorkEndSetData", "beginExecute is invoked");
        workModel.beginExecute(parameter);
    }

    /**
     * 网络请求结束后调用
     *
     * @param state 网络任务执行结果
     * @param data  结果数据
     */
    private void netWorkEndSetData(boolean state, List<Voyage> data) {
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

        onLoadEndListener.OnLoadEnd();
    }

    /**
     * 整理从服务器取回的数据
     *
     * @param state 执行结果
     * @param data  响应数据
     * @return 整理好的数据集
     */
    private List<Voyage> onNetworkEnd(boolean state, List<Voyage> data) {
        return state ? data : null;
    }

    /**
     * 将服务器取回的数据持久化到本地
     *
     * @param operator 数据库操作类
     * @param dataList 数据集
     */
    private void onSaveData(BaseOperator<Voyage> operator, List<Voyage> dataList) {
        if (!canceled && operator != null && dataList != null) {
            operator.delete(dataList.get(0));
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
    public void onClear() {
        Log.i(LOG_TAG + "onClear", "clear is invoked");
        operator.clear();
//        onClearEndListener.OnClearEnd();
    }

    /**
     * 从数据库获取单个航次列表
     *
     * @return 数据集合
     */
    public List<Voyage> onLoadVoyageListFromDataBase() {
        if (operator == null || operator.isEmpty()) {
            Log.i(LOG_TAG + "onLoadFromDataBase", "database null");
            return null;
        }

        return operator.queryAllVoyageList();
    }

    /**
     * 从数据库获取航次进出口编码
     *
     * @param shipId 航次编码
     * @return 进出口
     */
    public String onLoadCodeInOutOfVoyageFromDataBase(String shipId) {
        if (operator == null || operator.isEmpty()) {
            Log.i(LOG_TAG + "onLoadFromDataBase", "database null");
            return null;
        }

        Log.i(LOG_TAG + "onLoadInOutOfVayageFromDataBase", "query shipId is " + shipId);

        return operator.queryCodeInOutOfVoyage(shipId);
    }


}

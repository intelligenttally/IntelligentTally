package com.port.shenh.intelligenttally.function;
/**
 * Created by sh on 2016/11/25.
 */

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.port.shenh.intelligenttally.R;
import com.port.shenh.intelligenttally.bean.Bay;
import com.port.shenh.intelligenttally.bean.ShipImage;
import com.port.shenh.intelligenttally.database.ShipImageOperator;
import com.port.shenh.intelligenttally.database.TableConst;
import com.port.shenh.intelligenttally.work.PullShipImageList;

import org.mobile.library.model.database.BaseOperator;
import org.mobile.library.model.work.WorkBack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 船图数据功能类
 *
 * @author sh
 * @version 1.1 2016/11/25
 * @since 1.0
 */
public class ShipImageListFunction {

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
    private ShipImageOperator operator = null;

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
    public ShipImageListFunction(Context context) {
        this.context = context;
        Log.i(LOG_TAG + "onCreate", "onCreateOperator is invoked");
        this.operator = (ShipImageOperator) onCreateOperator(context);
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
    private BaseOperator<ShipImage> onCreateOperator(Context context) {
        return new ShipImageOperator(context);
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

        final PullShipImageList workModel = new PullShipImageList();

        workModel.setWorkEndListener(new WorkBack<List<ShipImage>>() {
            @Override
            public void doEndWork(boolean state, List<ShipImage> data) {
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

        onLoadEndListener.OnLoadEnd();
    }

    /**
     * 整理从服务器取回的数据
     *
     * @param state 执行结果
     * @param data  响应数据
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
     * 是否已下载
     *
     * @param shipId 航次编码
     * @return true/false
     */
    public boolean isDownloaded(String shipId) {
        Log.i(LOG_TAG + "isDownloaded", "isDownloaded is invoked");

        return operator.isExistByShipId(shipId);
    }


    /**
     * 从数据库获取贝号列表
     *
     * @param shipId 航次编码
     * @return 数据集合
     */
    public List<String> onLoadBayNumListFromDataBase(String shipId) {
        if (operator == null || operator.isEmpty()) {
            Log.i(LOG_TAG + "onLoadFromDataBase", "database null");
            return null;
        }

        Log.i(LOG_TAG + "onLoadBayNumListFromDataBase", "query shipId is " + shipId);

        return operator.queryBayNumList(shipId);
    }


    /**
     * 从数据库获取卸货港简写列表
     *
     * @param shipId 航次编码
     * @return 数据集合
     */
    public Map<String, String> onLoadCodeUnloadPortSubListFromDataBase(String shipId) {
        if (operator == null || operator.isEmpty()) {
            Log.i(LOG_TAG + "onLoadFromDataBase", "database null");
            return null;
        }

        Log.i(LOG_TAG + "onLoadCodeUnloadPortSubListFromDataBase", "query shipId is " + shipId);

        return operator.queryCodeUnloadPortSubList(shipId);
    }


    /**
     * 从数据库获取单个贝船图数据
     *
     * @param shipId 航次编码
     * @param bayNum 贝号
     * @return 数据集合
     */
    public List<ShipImage> onLoadShipImageFromDataBase(String shipId, String bayNum) {
        if (operator == null || operator.isEmpty()) {
            Log.i(LOG_TAG + "onLoadFromDataBase", "database null");
            return null;
        }

        Log.i(LOG_TAG + "onLoadShipImageFromDataBase", "query param is " + shipId + " " + bayNum);

        return operator.queryShipImage(shipId, bayNum);
    }

    /**
     * 从数据库获取贝数据
     *
     * @param shipId 航次编码
     * @param bayNum 贝号
     * @return 数据集合
     */
    public Bay onLoadBayFromDataBase(String shipId, String bayNum) {
        if (operator == null || operator.isEmpty()) {
            Log.i(LOG_TAG + "onLoadFromDataBase", "database null");
            return null;
        }

        Log.i(LOG_TAG + "onLoadShipImageFromDataBase", "query param is " + shipId + " " + bayNum);

        return operator.queryBay(shipId, bayNum);
    }

    /**
     * 移贝
     *
     * @param b1 贝位1
     * @param sbayno2 贝位号2
     * @param codeInOut 进出口编码
     * @return 错误消息，正确返回null
     */
    public String moveBay(ShipImage b1, String sbayno2, String codeInOut) {
        if (operator == null || operator.isEmpty()) {
            Log.i(LOG_TAG + "onLoadFromDataBase", "database null");
            return "false";
        }

        Log.i(LOG_TAG + "MoveBay", "query param is " + b1.getSbayno() + " " + sbayno2);

        List<ShipImage> shipImages = getShipImageList(b1.getShip_id(), sbayno2);

        Log.i(LOG_TAG + "MoveBay", "贝位箱子数量：shipImages is " + shipImages.size());

        if (shipImages.size() == 0){

            return String.format(sbayno2 + "贝位不存在，请重新操作");

        }

        //判断b2是否是一贝多箱
        if (shipImages.size()>1){

            return String.format(sbayno2 + "贝位存在多箱，请重新操作");

        }

        ShipImage b2 = shipImages.get(0);

        //贝位1箱子尺寸
        if (b1.getSize_con().equals("20")) {

            //贝位2是否有箱子
            if (!b2.getBayno().isEmpty()) {

                if (b2.getSize_con().equals("20")) {

                    Log.i(LOG_TAG + "MoveBay", "贝位1箱子20，贝位2有箱子，箱子20");

                    //对调处理
                    swapBay(b1, b2, codeInOut);

                } else {

                    if (operator.isJoint(b1.getShip_id(), b1.getBay_num())) {

                        ShipImage b1Next = isContainerOfNextBayNo(b1);

                        if (b1Next != null) {

                            Log.i(LOG_TAG + "MoveBay", "贝位1箱子20，贝位2有箱子，箱子40，贝位1通贝，贝位1Next有箱子");
                            return String.format(b1Next.getSbayno() + "贝位存在箱子，请重新操作");

                        } else {

                            Log.i(LOG_TAG + "MoveBay", "贝位1箱子20，贝位2有箱子，箱子40，贝位1通贝，贝位1Next无箱子");

                            //对调处理
                            swapBay(b1, b2, codeInOut);

                        }

                    }else{

                        Log.i(LOG_TAG + "MoveBay", "贝位1箱子20，贝位2有箱子，箱子40，贝位1不通贝");
                        return String.format(b1.getSbayno() + "贝位放不下大箱子，请重新操作");

                    }

                }

            } else {

                Log.i(LOG_TAG + "MoveBay", "贝位1箱子20，贝位2无箱子");

                //对调处理
                swapBay(b1, b2, codeInOut);

            }

        } else {

            if(operator.isJoint(b2.getShip_id(), b2.getBay_num())){

                ShipImage b2Next = isContainerOfNextBayNo(b2);

                if (b2Next != null) {

                    Log.i(LOG_TAG + "MoveBay", "贝位1箱子40，贝位2通贝，贝位2Next有箱子");

                    return String.format(b2Next.getSbayno() + "贝位存在箱子，请重新操作");

                } else {

                    Log.i(LOG_TAG + "MoveBay", "贝位1箱子40，贝位2通贝，贝位2Next无箱子");

                    //对调处理
                    swapBay(b1, b2, codeInOut);

                }

            }else{

                Log.i(LOG_TAG + "MoveBay", "贝位1箱子40，贝位2不通贝");

                return String.format(b2.getSbayno() + "贝位放不下大箱子，请重新操作");
            }

        }

        return null;
    }

    /**
     *  对调贝位1，贝位2
     * @param b1 贝位1
     * @param b2 贝位2
     * @param codeInOut 进出口编码
     */
    private void swapBay(ShipImage b1, ShipImage b2, String codeInOut){

        Log.i(LOG_TAG + "swapBay", "swapBay is invoked");

        operator.swapBay(b1, b2, codeInOut);

    }

    /**
     * 判断下一贝贝位是否有箱子
     *
     * @param b 当前贝位
     * @return 有，返回下一贝位；无返回null
     */
    private ShipImage isContainerOfNextBayNo(ShipImage b) {

        List<String> bayList = onLoadBayNumListFromDataBase(b.getShip_id());

        String bayNumNext = null;
        //获取下一贝号
        for (int i = 0; i < bayList.size() - 1; i++) {
            if (bayList.get(i).equals(b.getBay_num())) {
                bayNumNext = bayList.get(i + 1);
                break;
            }
        }

        if (bayNumNext == null) {
            return null;
        }

        String sbaynoNext = bayNumNext + b.getBay_col() + b.getBay_row();

        ShipImage shipImage = null;
        List<ShipImage> shipImageList = onLoadShipImageFromDataBase(b.getShip_id(), bayNumNext);

        for (int i = 0; i < shipImageList.size(); i++) {
            if (shipImageList.get(i).getSbayno().equals(sbaynoNext)) {
                if (!shipImageList.get(i).getBayno().isEmpty()) {
                    shipImage = shipImageList.get(i);
                    break;
                }
            }
        }

        return shipImage;
    }


    /**
     * 根据航次编码和标准贝位号查询船图数据
     *
     * @param shipId 航次编码
     * @param sbayno 标准贝位号
     * @return 数据对象
     */
    private List<ShipImage> getShipImageList(String shipId, String sbayno) {
        Log.i(LOG_TAG + "getShipImageList", "getShipImageList is invoked");

        return operator.getShipImageList(shipId, sbayno);

    }
}

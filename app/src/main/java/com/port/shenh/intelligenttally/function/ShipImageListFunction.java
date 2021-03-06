package com.port.shenh.intelligenttally.function;
/**
 * Created by sh on 2016/11/25.
 */

import android.content.Context;
import android.util.Log;

import com.port.shenh.intelligenttally.bean.Bay;
import com.port.shenh.intelligenttally.bean.ShipImage;
import com.port.shenh.intelligenttally.database.ShipImageOperator;
import com.port.shenh.intelligenttally.work.PullShipImageList;
import com.port.shenh.intelligenttally.work.PullShipImageListOfBay;

import org.mobile.library.model.database.BaseOperator;
import org.mobile.library.model.work.WorkBack;

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

        void OnLoadEnd(boolean state);
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
     *
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
     * 升级数据（贝船图数据）
     *
     * @param shipId 航次编码
     * @param bayNum 贝号
     */
    public void onUpdate(String shipId, String bayNum) {
        Log.i(LOG_TAG + "onUpdate", "getDataList is invoked");
        // 加载开始
        loading = true;

        if (!canceled) {
            // 从网络拉取
            Log.i(LOG_TAG + "onCreate", "from network");
            onLoadFromNetWork(shipId, bayNum);
        }
    }

    /**
     * 从网络加载数据(加载贝船图数据)，
     * 完成请求后要调用{@link #netWorkEndSetData(boolean , List)}继续执行后续任务
     *
     * @param shipId 航次编码
     * @param bayNum 贝号
     */
    private void onLoadFromNetWork(String shipId, String bayNum) {
        Log.i(LOG_TAG + "onLoadFromNetWork", "shipId parameter is " + shipId);
        Log.i(LOG_TAG + "onLoadFromNetWork", "bayNum parameter is " + bayNum);

        final PullShipImageListOfBay workModel = new PullShipImageListOfBay();

        workModel.setWorkEndListener(new WorkBack<List<ShipImage>>() {
            @Override
            public void doEndWork(boolean state, List<ShipImage> data) {
                if (state && data != null) {

                    Log.i(LOG_TAG + "netWorkEndSetData", "getResult is invoked");
                    netWorkEndSetDataOfBay(state, data);
                } else {
                    onLoadEndListener.OnLoadEnd(state);
                }

            }
        }, false);

        Log.i(LOG_TAG + "netWorkEndSetData", "beginExecute is invoked");
        workModel.beginExecute(shipId, bayNum);
    }

    /**
     * 从网络加载数据，
     * 完成请求后要调用{@link #netWorkEndSetData(boolean , List)}继续执行后续任务
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
                } else {
                    onLoadEndListener.OnLoadEnd(state);
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

        onLoadEndListener.OnLoadEnd(state);
    }

    /**
     * 网络请求结束后调用
     *
     * @param state 网络任务执行结果
     * @param data  结果数据
     */
    private void netWorkEndSetDataOfBay(boolean state, List<ShipImage> data) {
        Log.i(LOG_TAG + "netWorkEndSetData", "result is " + state);

        if (!canceled) {
            // 提取结果
            Log.i(LOG_TAG + "netWorkEndSetData", "onNetworkEnd is invoked");
            dataList = onNetworkEnd(state, data);
        }

        if (!canceled) {
            // 保存数据
            Log.i(LOG_TAG + "netWorkEndSetData", "onSaveData is invoked");
            onSaveDataOfBay(operator, dataList);
        }

        if (!canceled) {
            onNotify(context);
        }
        // 加载结束
        loading = false;

        onLoadEndListener.OnLoadEnd(state);
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
            this.operator.deleteShipImage(dataList.get(0).getShip_id());
            operator.insert(dataList);
        }
    }

    /**
     * 将服务器取回的数据持久化到本地
     *
     * @param operator 数据库操作类
     * @param dataList 数据集
     */
    private void onSaveDataOfBay(BaseOperator<ShipImage> operator, List<ShipImage> dataList) {
        if (!canceled && operator != null && dataList != null) {
            this.operator.deleteShipImage(dataList.get(0).getShip_id(), dataList.get(0)
                    .getBay_num());
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
     *
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
     *
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
     *
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
     *
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
     *
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
     * 从数据库获取已修改的船图数据
     *
     * @param shipId 航次编码
     *
     * @return 数据对象
     */
    public List<ShipImage> onLoadShipImageListOfModifyFromDataBase(String shipId) {
        if (operator == null || operator.isEmpty()) {
            Log.i(LOG_TAG + "onLoadFromDataBase", "database null");
            return null;
        }
        Log.i(LOG_TAG + "getShipImageListOfModify", "getShipImageList is invoked");

        return operator.queryShipImageOfModify(shipId);

    }


    /**
     * 移贝
     *
     * @param b1        贝位1船图
     * @param bayno2    实际贝位号2
     * @param codeInOut 进出口编码
     *
     * @return 错误消息，正确返回null
     */
    public String onMoveBay(ShipImage b1, String bayno2, String codeInOut) {
        if (operator == null || operator.isEmpty()) {
            Log.i(LOG_TAG + "onLoadFromDataBase", "database null");
            return "false";
        }

        Log.i(LOG_TAG + "MoveBay", "b1.getSbayno() param is " + b1.getSbayno());
        Log.i(LOG_TAG + "MoveBay", "bayno2 param is " + bayno2);

        //校验输入实际贝位号是否是6位
        if (bayno2.length() != 6) {
            return String.format(bayno2 + "贝位号不存在");
        }

        //校验输入实际贝位和原实际贝位是否一致
        if (bayno2.equals(b1.getBayno())){
            return String.format(bayno2 + "贝位号与源贝位号重复");
        }

        //校验输入实际贝位号和箱子尺寸是否匹配
        String baynum2 = bayno2.substring(0, 2);
        String baycol2 = bayno2.substring(2, 4);
        String bayrow2 = bayno2.substring(4, 6);
        Log.i(LOG_TAG + "MoveBay", "baynum2 is " + baynum2);
        Log.i(LOG_TAG + "MoveBay", "baycol2 is " + baycol2);
        Log.i(LOG_TAG + "MoveBay", "bayrow2 is " + bayrow2);

        if (Integer.parseInt(baynum2) % 2 == 0 && b1.getSize_con().equals("20")) {

            return String.format(bayno2 + "贝位号与箱子尺寸不匹配");

        } else if (Integer.parseInt(baynum2) % 2 != 0 && !b1.getSize_con().equals("20")) {

            return String.format(bayno2 + "贝位号与箱子尺寸不匹配");
        }

        //如果是40的箱子，获取实际贝位前通的表准贝号
        String sbayno2 = null;
        if (b1.getSize_con().equals("40")) {
            if ((Integer.parseInt(baynum2) - 1) < 10) {

                sbayno2 = "0" + Integer.toString(Integer.parseInt(baynum2) - 1) + baycol2 + bayrow2;

            } else {

                sbayno2 = Integer.toString(Integer.parseInt(baynum2) - 1) + baycol2 + bayrow2;
            }

            Log.i(LOG_TAG + "MoveBay", "sbayno2 is " + sbayno2);
        } else {
            sbayno2 = bayno2;
        }

        //校验输入的实际贝位号是否存在
        if (!isExistBayno(b1.getShip_id(), bayno2)) {

            return String.format(bayno2 + "贝位号不存在");

        }


        List<ShipImage> shipImages = onLoadgetShipImageListFromDataBase(b1.getShip_id(), sbayno2);

        Log.i(LOG_TAG + "MoveBay", "贝位箱子数量：shipImages is " + shipImages.size());

        if (shipImages.size() == 0) {

            return String.format(sbayno2 + "贝位不存在，请重新操作");

        }

        //判断b2是否是一贝多箱
        if (shipImages.size() > 1) {

            String baynos = "";
            for (int i = 0; i < shipImages.size(); i++) {
                baynos += shipImages.get(i).getBayno();
                baynos += ",";
            }
            baynos.subSequence(0, baynos.length() - 1);

            return String.format(baynos + "贝位同时存在箱子，请重新操作");

        }

        ShipImage b2 = shipImages.get(0);

        //贝位1箱子尺寸
        if (b1.getSize_con().equals("20")) {

            //贝位2是否有箱子
            if (!b2.getBayno().isEmpty()) {

                if (b2.getSize_con().equals("20")) {

                    Log.i(LOG_TAG + "MoveBay", "贝位1箱子20，贝位2有箱子，箱子20");

                    //对调处理
                    onSwapBay(b1, b2, codeInOut);

                } else {

                    /**
                     * 校验b2是前通还是后通
                     *
                     * 如果b2是40的箱子，则判断是前通还是后通；
                     * 如果是后通，则可以进行对调；
                     * 如果是前通，则不可以进行对调；
                     */

                    //b2的实际贝号小于标准贝号，是前通；否则后通
                    if (b2.getBaynum().compareTo(b2.getBay_num()) < 0) {
                        Log.i(LOG_TAG + "MoveBay", "b2.getBaynum is " + b2.getBaynum());
                        Log.i(LOG_TAG + "MoveBay", "b2.getBay_num is " + b2.getBay_num());

                        //前通
                        //获取前通实际贝位号
                        String bayno2last = null;
                        if ((Integer.parseInt(baynum2) - 1) < 10) {

                            bayno2last = "0" + Integer.toString(Integer.parseInt(baynum2) - 1) +
                                    baycol2 + bayrow2;

                        } else {

                            bayno2last = Integer.toString(Integer.parseInt(baynum2) - 1) + baycol2 +
                                    bayrow2;
                        }

                        Log.i(LOG_TAG + "MoveBay", "bayno2last is " + bayno2last);

                        Log.i(LOG_TAG + "MoveBay", "贝位1箱子20，贝位2有箱子，箱子40，箱子前通");
                        return String.format(bayno2last + "贝位有箱子，" + bayno2 + "不能放大箱子，请重新操作");


                    } else {
                        //后通
                        /**
                         * 判断b1是否通贝，
                         * 通贝，判断b1通贝位是否有箱子
                         * 有箱子，不能调贝；没有可以调贝；
                         * 不通贝，不可以调贝；
                         */

                        //获取后通实际贝位号
                        String bayno2next = null;
                        if ((Integer.parseInt(baynum2) + 1) < 10) {

                            bayno2next = "0" + Integer.toString(Integer.parseInt(baynum2) + 1) +
                                    baycol2 + bayrow2;

                        } else {

                            bayno2next = Integer.toString(Integer.parseInt(baynum2) + 1) + baycol2 +
                                    bayrow2;
                        }

                        if (operator.isJoint(b1.getShip_id(), b1.getBay_num())) {

                            ShipImage b1Next = isContainerOfNextBayNo(b1);

                            if (b1Next != null) {

                                Log.i(LOG_TAG + "MoveBay", "贝位1箱子20，贝位2有箱子，箱子40，贝位1通贝，贝位1Next有箱子");
                                return String.format(bayno2next + "贝位存在大箱子，" + b1Next.getBayno() +
                                        "贝位存在箱子，请重新操作");

                            } else {

                                Log.i(LOG_TAG + "MoveBay", "贝位1箱子20，贝位2有箱子，箱子40，贝位1通贝，贝位1Next无箱子");

                                //对调处理
                                onSwapBay(b1, b2, codeInOut);

                            }

                        } else {

                            Log.i(LOG_TAG + "MoveBay", "贝位1箱子20，贝位2有箱子，箱子40，贝位1不通贝");
                            return String.format(bayno2next + "贝位存在大箱子，" + b1.getSbayno() +
                                    "贝位不通贝，放不下大箱子，请重新操作");

                        }
                    }

                    //                    return String.format(bayno2 + "贝位有大箱子，请重新操作");
                }

            } else {

                Log.i(LOG_TAG + "MoveBay", "贝位1箱子20，贝位2无箱子");

                //对调处理
                onSwapBay(b1, b2, codeInOut);

            }

        } else {

            //贝位2是否通贝
            if (operator.isJoint(b2.getShip_id(), b2.getBay_num())) {

                //贝位2是否有箱子
                if (!b2.getBayno().isEmpty()) {

                    if (b2.getSize_con().equals("20")) {
                        ShipImage b2Next = isContainerOfNextBayNo(b2);

                        if (b2Next != null) {

                            Log.i(LOG_TAG + "MoveBay", "贝位1箱子40，贝位2通贝，贝位2箱子20，贝位2Next有箱子");

                            return String.format(b2Next.getBayno() + "贝位存在箱子，请重新操作");

                        } else {

                            Log.i(LOG_TAG + "MoveBay", "贝位1箱子40，贝位2通贝，贝位2箱子20，贝位2Next无箱子");

                            //对调处理
                            onSwapBay(b1, b2, codeInOut);

                        }

                    } else {
                        /**
                         * 校验b2是前通还是后通
                         *
                         * 如果b2是40的箱子，则判断是前通还是后通；
                         * 如果是后通，则可以进行对调；
                         * 如果是前通，则不可以进行对调；
                         */

                        //b2的实际贝号小于标准贝号，是前通；否则后通
                        if (b2.getBaynum().compareTo(b2.getBay_num()) < 0) {
                            Log.i(LOG_TAG + "MoveBay", "b2.getBaynum is " + b2.getBaynum());
                            Log.i(LOG_TAG + "MoveBay", "b2.getBay_num is " + b2.getBay_num());

                            //前通
                            //获取前通实际贝位号
                            String bayno2last = null;
                            if ((Integer.parseInt(baynum2) - 2) < 10) {

                                bayno2last = "0" + Integer.toString(Integer.parseInt(baynum2) - 2) +
                                        baycol2 + bayrow2;

                            } else {

                                bayno2last = Integer.toString(Integer.parseInt(baynum2) - 2) +
                                        baycol2 +
                                        bayrow2;
                            }

                            Log.i(LOG_TAG + "MoveBay", "bayno2last is " + bayno2last);

                            Log.i(LOG_TAG + "MoveBay", "贝位1箱子40，贝位2有箱子，箱子40，箱子前通");
                            return String.format(bayno2last + "贝位有箱子，" + bayno2 + "不能放大箱子，请重新操作");


                        } else {
                            //后通
                            Log.i(LOG_TAG + "MoveBay", "贝位1箱子40，贝位2有箱子，箱子40，箱子后通");
                            //对调处理
                            onSwapBay(b1, b2, codeInOut);
                        }
                    }

                } else {

                    ShipImage b2Next = isContainerOfNextBayNo(b2);

                    if (b2Next != null) {

                        Log.i(LOG_TAG + "MoveBay", "贝位1箱子40，贝位2通贝，贝位2没有箱子，贝位2Next有箱子");

                        return String.format(b2Next.getBayno() + "贝位存在箱子，请重新操作");

                    } else {

                        Log.i(LOG_TAG + "MoveBay", "贝位1箱子40，贝位2通贝，贝位2没有箱子，贝位2Next无箱子");

                        //对调处理
                        onSwapBay(b1, b2, codeInOut);

                    }

                }

            } else {

                Log.i(LOG_TAG + "MoveBay", "贝位1箱子40，贝位2不通贝");
                return String.format(bayno2 + "贝位不通贝，放不下大箱子，请重新操作");
            }

        }

        return null;
    }
    //
    //    /**
    //     * 移贝
    //     *
    //     * @param b1        贝位1
    //     * @param sbayno2   贝位号2
    //     * @param codeInOut 进出口编码
    //     *
    //     * @return 错误消息，正确返回null
    //     */
    //    public String onMoveBay(ShipImage b1, String sbayno2, String codeInOut) {
    //        if (operator == null || operator.isEmpty()) {
    //            Log.i(LOG_TAG + "onLoadFromDataBase", "database null");
    //            return "false";
    //        }
    //
    //        Log.i(LOG_TAG + "MoveBay", "query param is " + b1.getSbayno() + " " + sbayno2);
    //
    ////        //添加贝位号校验
    ////        List<ShipImage> shipImages = onLoadBaynumBybaynoFromDataBase(b1.getShip_id(),
    // sbayno2);
    ////
    ////        //添加大箱贝位号检验
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //        List<ShipImage> shipImages = onLoadgetShipImageListFromDataBase(b1.getShip_id(),
    // sbayno2);
    //
    //        Log.i(LOG_TAG + "MoveBay", "贝位箱子数量：shipImages is " + shipImages.size());
    //
    //        if (shipImages.size() == 0) {
    //
    //            return String.format(sbayno2 + "贝位不存在，请重新操作");
    //
    //        }
    //
    //        //判断b2是否是一贝多箱
    //        if (shipImages.size() > 1) {
    //
    //            return String.format(sbayno2 + "贝位存在多箱，请重新操作");
    //
    //        }
    //
    //        ShipImage b2 = shipImages.get(0);
    //
    //        //贝位1箱子尺寸
    //        if (b1.getSize_con().equals("20")) {
    //
    //            //贝位2是否有箱子
    //            if (!b2.getBayno().isEmpty()) {
    //
    //                if (b2.getSize_con().equals("20")) {
    //
    //                    Log.i(LOG_TAG + "MoveBay", "贝位1箱子20，贝位2有箱子，箱子20");
    //
    //                    //对调处理
    //                    onSwapBay(b1, b2, codeInOut);
    //
    //                } else {
    //
    //                    if (operator.isJoint(b1.getShip_id(), b1.getBay_num())) {
    //
    //                        ShipImage b1Next = isContainerOfNextBayNo(b1);
    //
    //                        if (b1Next != null) {
    //
    //                            Log.i(LOG_TAG + "MoveBay",
    // "贝位1箱子20，贝位2有箱子，箱子40，贝位1通贝，贝位1Next有箱子");
    //                            return String.format(b1Next.getSbayno() + "贝位存在箱子，请重新操作");
    //
    //                        } else {
    //
    //                            Log.i(LOG_TAG + "MoveBay",
    // "贝位1箱子20，贝位2有箱子，箱子40，贝位1通贝，贝位1Next无箱子");
    //
    //                            //对调处理
    //                            onSwapBay(b1, b2, codeInOut);
    //
    //                        }
    //
    //                    } else {
    //
    //                        Log.i(LOG_TAG + "MoveBay", "贝位1箱子20，贝位2有箱子，箱子40，贝位1不通贝");
    //                        return String.format(b1.getSbayno() + "贝位放不下大箱子，请重新操作");
    //
    //                    }
    //
    //                }
    //
    //            } else {
    //
    //                Log.i(LOG_TAG + "MoveBay", "贝位1箱子20，贝位2无箱子");
    //
    //                //对调处理
    //                onSwapBay(b1, b2, codeInOut);
    //
    //            }
    //
    //        } else {
    //
    //            //贝位2是否通贝
    //            if (operator.isJoint(b2.getShip_id(), b2.getBay_num())) {
    //
    //                //贝位2是否有箱子
    //                if (!b2.getBayno().isEmpty()) {
    //
    //                    if (b2.getSize_con().equals("20")) {
    //                        ShipImage b2Next = isContainerOfNextBayNo(b2);
    //
    //                        if (b2Next != null) {
    //
    //                            Log.i(LOG_TAG + "MoveBay", "贝位1箱子40，贝位2通贝，贝位2箱子20，贝位2Next有箱子");
    //
    //                            return String.format(b2Next.getSbayno() + "贝位存在箱子，请重新操作");
    //
    //                        } else {
    //
    //                            Log.i(LOG_TAG + "MoveBay", "贝位1箱子40，贝位2通贝，贝位2箱子20，贝位2Next无箱子");
    //
    //                            //对调处理
    //                            onSwapBay(b1, b2, codeInOut);
    //
    //                        }
    //
    //                    } else {
    //
    //                        Log.i(LOG_TAG + "MoveBay", "贝位1箱子40，贝位2通贝，贝位2箱子40");
    //
    //                        //对调处理
    //                        onSwapBay(b1, b2, codeInOut);
    //                    }
    //
    //                } else {
    //
    //                    ShipImage b2Next = isContainerOfNextBayNo(b2);
    //
    //                    if (b2Next != null) {
    //
    //                        Log.i(LOG_TAG + "MoveBay", "贝位1箱子40，贝位2通贝，贝位2没有箱子，贝位2Next有箱子");
    //
    //                        return String.format(b2Next.getSbayno() + "贝位存在箱子，请重新操作");
    //
    //                    } else {
    //
    //                        Log.i(LOG_TAG + "MoveBay", "贝位1箱子40，贝位2通贝，贝位2没有箱子，贝位2Next无箱子");
    //
    //                        //对调处理
    //                        onSwapBay(b1, b2, codeInOut);
    //
    //                    }
    //
    //                }
    //
    //            } else {
    //
    //                Log.i(LOG_TAG + "MoveBay", "贝位1箱子40，贝位2不通贝");
    //
    //                return String.format(b2.getSbayno() + "贝位放不下大箱子，请重新操作");
    //            }
    //
    //        }
    //
    //        return null;
    //    }


    /**
     * 根据航次编码更新船图数据修改标志
     *
     * @param shipId 航次编码
     */
    public void onUpdateModifyMark(String shipId) {
        if (operator == null || operator.isEmpty()) {
            Log.i(LOG_TAG + "onLoadFromDataBase", "database null");
            return;
        }
        Log.i(LOG_TAG + "onUpdateModifyMark", "onUpdateModifyMark is invoked");

        operator.updateModifyMark(shipId);

    }

    /**
     * 根据航次编码查询是否有修改标志为1
     *
     * @param shipId 航次编码
     */
    public boolean isExistModifyMark(String shipId) {
        if (operator == null || operator.isEmpty()) {
            Log.i(LOG_TAG + "onLoadFromDataBase", "database null");
            return false;
        }
        Log.i(LOG_TAG + "isExistModifyMark", "isExistModifyMark is invoked");

        return operator.isExistModifyMark(shipId);
    }

    /**
     * 判断是否有未上传航次
     *
     * @return true/false
     */
    public boolean isExistUploadedVoyage() {
        if (operator == null || operator.isEmpty()) {
            Log.i(LOG_TAG + "onLoadFromDataBase", "database null");
            return false;
        }
        Log.i(LOG_TAG + "isExistUploadedVoyage", "isExistUploadedVoyage is invoked");

        return operator.isExistUploadedVoyage();
    }

    /**
     * 根据航次编码、贝号判断是否通贝
     *
     * @param shipId 航次编码
     * @param bayNum 贝号
     *
     * @return false/true
     */
    public boolean isJoint(String shipId, String bayNum) {
        if (operator == null || operator.isEmpty()) {
            Log.i(LOG_TAG + "onLoadFromDataBase", "database null");
            return false;
        }
        Log.i(LOG_TAG + "isJoint", "isJoint is invoked");

        return operator.isJoint(shipId, bayNum);
    }


    /**
     * 根据航次编码查询箱号数据
     *
     * @param shipId 航次编码
     * @param query  箱号查询条件
     *
     * @return 数据对象
     */
    public List<String> onLoadContainerNoListFromDataBase(String shipId, String query) {
        if (operator == null || operator.isEmpty()) {
            Log.i(LOG_TAG + "onLoadFromDataBase", "database null");
            return null;
        }
        Log.i(LOG_TAG + "onLoadContainerNoListFromDataBase", "onLoadContainerNoListFromDataBase "
                + "is invoked");

        return operator.getContainerNoList(shipId, query);

    }

    /**
     * 根据箱号查询船图数据
     *
     * @param container_no 箱号
     *
     * @return 数据对象
     */
    public List<ShipImage> onLoadgetShipImageListFromDataBase(String container_no) {
        if (operator == null || operator.isEmpty()) {
            Log.i(LOG_TAG + "onLoadFromDataBase", "database null");
            return null;
        }
        Log.i(LOG_TAG + "onLoadgetShipImageListFromDataBase",
                "onLoadgetShipImageListFromDataBase" + " is invoked");

        return operator.getShipImageListByContainerNo(container_no);

    }


    /**
     * 对调贝位1，贝位2
     *
     * @param b1        贝位1
     * @param b2        贝位2
     * @param codeInOut 进出口编码
     */
    private void onSwapBay(ShipImage b1, ShipImage b2, String codeInOut) {
        if (operator == null || operator.isEmpty()) {
            Log.i(LOG_TAG + "onLoadFromDataBase", "database null");
            return;
        }
        Log.i(LOG_TAG + "swapBay", "swapBay is invoked");

        operator.swapBay(b1, b2, codeInOut);

    }

    /**
     * 判断下一贝贝位是否有箱子
     *
     * @param b 当前贝位
     *
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

        Log.i(LOG_TAG + "isContainerOfNextBayNo", "bayNumNext is " + bayNumNext);

        String sbaynoNext = bayNumNext + b.getBay_col() + b.getBay_row();
        Log.i(LOG_TAG + "isContainerOfNextBayNo", "sbaynoNext is " + sbaynoNext);

        ShipImage shipImage = null;
        List<ShipImage> shipImageList = onLoadShipImageFromDataBase(b.getShip_id(), bayNumNext);

        for (int i = 0; i < shipImageList.size(); i++) {
            if (shipImageList.get(i).getSbayno().equals(sbaynoNext)) {
                if (!shipImageList.get(i).getBayno().equals("") && shipImageList.get(i).getBayno
                        () != null && !shipImageList.get(i).getBayno().equals("null")) {
                    Log.i(LOG_TAG + "isContainerOfNextBayNo", "shipImageList.get(i).getBayno() is" +
                            " " + shipImageList.get(i).getBayno());
                    shipImage = shipImageList.get(i);
                    break;
                }
            }
        }

        if (shipImage != null) {

            Log.i(LOG_TAG + "swapBay", "getContainer_no is " + shipImage.getContainer_no());

            Log.i(LOG_TAG + "swapBay", "getBay_num is " + shipImage.getBay_num());
            Log.i(LOG_TAG + "swapBay", "getBay_col is " + shipImage.getBay_col());
            Log.i(LOG_TAG + "swapBay", "getBay_row is " + shipImage.getBay_row());
            Log.i(LOG_TAG + "swapBay", "-----------------------------");
            Log.i(LOG_TAG + "swapBay", "getBayno is " + shipImage.getBayno());
            Log.i(LOG_TAG + "swapBay", "getSbayno is " + shipImage.getSbayno());
            Log.i(LOG_TAG + "swapBay", "shipImage is " + shipImage.getBaynum());
            Log.i(LOG_TAG + "swapBay", "getBaycol is " + shipImage.getBaycol());
            Log.i(LOG_TAG + "swapBay", "getBayrow is " + shipImage.getBayrow());

        }

        return shipImage;
    }


    /**
     * 根据航次编码和标准贝位号查询船图数据
     *
     * @param shipId 航次编码
     * @param sbayno 标准贝位号
     *
     * @return 数据对象
     */
    private List<ShipImage> onLoadgetShipImageListFromDataBase(String shipId, String sbayno) {
        if (operator == null || operator.isEmpty()) {
            Log.i(LOG_TAG + "onLoadFromDataBase", "database null");
            return null;
        }
        Log.i(LOG_TAG + "onLoadgetShipImageListFromDataBase",
                "onLoadgetShipImageListFromDataBase" + " is invoked");

        return operator.getShipImageList(shipId, sbayno);

    }


    /**
     * 根据航次编码和输入实际贝位号查询船图数据
     *
     * @param shipId 航次编码
     * @param bayno  实际贝位号
     *
     * @return true/false
     */
    private boolean isExistBayno(String shipId, String bayno) {
        if (operator == null || operator.isEmpty()) {
            Log.i(LOG_TAG + "onLoadFromDataBase", "database null");
            return false;
        }
        Log.i(LOG_TAG + "isExistBayno", "isExistBayno is invoked");

        return operator.isExistBayno(shipId, bayno);
    }


}

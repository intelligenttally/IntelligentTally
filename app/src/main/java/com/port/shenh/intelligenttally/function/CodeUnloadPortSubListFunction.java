package com.port.shenh.intelligenttally.function;

/**
 * Created by shenh on 2016/12/27.
 */

import android.media.MediaActionSound;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.Map;

/**
 * 卸货港数据功能类
 *
 * @author sh
 * @version 1.1 2016/11/25
 * @since 1.0
 */
public class CodeUnloadPortSubListFunction {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "CodeUnloadPortSubListFunction.";

    /**
     * 卸货港数据集合
     */
    private static class UnloadPort {

        /**
         * 卸货港代码
         */
        public String codeUnloadPort = null;

        /**
         * 卸货港（去后三字母）
         */
        public String unload = null;

        /**
         * 标志
         */
        public String substitute = null;
    }

    /**
     * 获取卸货港标志列表
     *
     * @param codeUnloadPortList 卸货港列表
     * @return 数据集合
     */
    public static Map<String, String> GetCodeUnloadPortSubList(List<String> codeUnloadPortList) {

        List<UnloadPort> unloadPortList = new ArrayList<>();
        for (int i = 0; i < codeUnloadPortList.size(); i++) {
            Log.i(LOG_TAG + "GetCodeUnloadPortSubList", "codeUnloadPortList is " + codeUnloadPortList.get(i));

            UnloadPort unloadPort = new UnloadPort();
            if (codeUnloadPortList.get(i).length() > 3) {
                unloadPort.codeUnloadPort = codeUnloadPortList.get(i);
                unloadPort.unload = unloadPort.codeUnloadPort.substring(unloadPort.codeUnloadPort.length() - 3, unloadPort.codeUnloadPort.length());
                unloadPort.substitute = unloadPort.codeUnloadPort.substring(unloadPort.codeUnloadPort.length() - 3, unloadPort.codeUnloadPort.length() - 2);
            } else {
                unloadPort.codeUnloadPort = codeUnloadPortList.get(i);
                unloadPort.unload = unloadPort.codeUnloadPort;
                unloadPort.substitute = unloadPort.unload.substring(0, 1);
            }

            Log.i(LOG_TAG + "GetCodeUnloadPortSubList", "unloadPort is " + unloadPort.codeUnloadPort + "," + unloadPort.unload + "," + unloadPort.substitute);

            unloadPortList.add(unloadPort);
        }

        SortByASCII(unloadPortList);

        for (int i = 0; i < unloadPortList.size(); i++) {
            Log.i(LOG_TAG + "queryCodeUnloadPortSubList", "unloadPort sort is " + unloadPortList.get(i).unload);
        }

        List<UnloadPort> newUnloadPortList = new ArrayList<>();
        for (int i = 0; i < unloadPortList.size(); i++) {

            String substitute = unloadPortList.get(i).substitute;
            boolean isExit = false;
            for (int j = 0; j < i; j++) {
                if (substitute.equals(unloadPortList.get(j))) {
                    isExit = true;
                    break;
                }
            }

            //存在
            if (isExit) {

                if (substitute.equals("Z")) {
                    substitute = "@";
                }

                do {
                    substitute = String.valueOf(substitute.charAt(0) + 1);
                    isExit = IsExit(substitute, unloadPortList, newUnloadPortList);
                } while (isExit);
            }

            UnloadPort newUnloadPort = new UnloadPort();
            newUnloadPort.codeUnloadPort = unloadPortList.get(i).codeUnloadPort;
            newUnloadPort.unload = unloadPortList.get(i).unload;
            newUnloadPort.substitute = substitute;
            newUnloadPortList.add(newUnloadPort);
        }

        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < newUnloadPortList.size(); i++) {
            Log.i(LOG_TAG + "GetCodeUnloadPortSubList", "newUnloadport is " + newUnloadPortList.get(i).codeUnloadPort + "," + newUnloadPortList.get(i).unload + "," + newUnloadPortList.get(i).substitute);
            map.put(newUnloadPortList.get(i).codeUnloadPort, newUnloadPortList.get(i).substitute);
        }

        for (int i = 0; i < newUnloadPortList.size(); i++) {
            Log.i(LOG_TAG + "queryCodeUnloadPortSubList", "newUnloadPort sort is " + newUnloadPortList.get(i).unload);
        }

        return map;
    }

    /**
     * 判断卸货港简写是否存在
     *
     * @param substitute        卸货港简写
     * @param oldUnloadPortList old
     * @param newUnloadPortList new
     * @return true/flase
     */
    private static boolean IsExit(String substitute, List<UnloadPort> oldUnloadPortList, List<UnloadPort> newUnloadPortList) {

        //已经转换的字符中是否存在
        for (int i = 0; i < oldUnloadPortList.size(); i++) {
            if (oldUnloadPortList.get(i).substitute.equals(substitute)) {
                return true;
            }
        }

        //已经转换的字符中是否存在
        for (int i = 0; i < newUnloadPortList.size(); i++) {
            if (newUnloadPortList.get(i).substitute.equals(substitute)) {
                return true;
            }
        }
        return false;
    }


    /**
     * unload按字符串大小比较进行排序
     *
     * @param unloadPortList
     * @return
     */
    private static void SortByASCII(List<UnloadPort> unloadPortList) {

        for (int i = 0; i < unloadPortList.size(); i++) {
            for (int j = i + 1; j < unloadPortList.size(); j++) {

                if (unloadPortList.get(i).unload.compareTo(unloadPortList.get(j).unload) > 0) {
                    Log.i(LOG_TAG + "GetCodeUnloadPortSubList", "swap before is " + unloadPortList.get(i).unload + " " + unloadPortList.get(j).unload);
                    swap(unloadPortList, i, j);
                    Log.i(LOG_TAG + "GetCodeUnloadPortSubList", "swap back is " + unloadPortList.get(i).unload + " " + unloadPortList.get(j).unload);
                }
            }
        }


    }

    /**
     * 交换两个元素的位置的方法
     *
     * @param unloadPortList 需要交换元素的数组
     * @param i              索引i
     * @param j              索引j
     */
    private static void swap(List<UnloadPort> unloadPortList, int i, int j) {
        UnloadPort uP = new UnloadPort();
        uP.codeUnloadPort = unloadPortList.get(i).codeUnloadPort;
        uP.unload = unloadPortList.get(i).unload;
        uP.substitute = unloadPortList.get(i).substitute;

        unloadPortList.get(i).codeUnloadPort = unloadPortList.get(j).codeUnloadPort;
        unloadPortList.get(i).unload = unloadPortList.get(j).unload;
        unloadPortList.get(i).substitute = unloadPortList.get(j).substitute;

        unloadPortList.get(j).codeUnloadPort = uP.codeUnloadPort;
        unloadPortList.get(j).unload = uP.unload;
        unloadPortList.get(j).substitute = uP.substitute;

    }
}

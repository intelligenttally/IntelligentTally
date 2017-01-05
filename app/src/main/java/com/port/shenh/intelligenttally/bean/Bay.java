package com.port.shenh.intelligenttally.bean;

/**
 * Created by shenh on 2016/12/12.
 */

/**
 * 贝数据模型
 *
 * @author sh
 * @version 1.0 2016/12/1
 * @since 1.0
 */
public class Bay {

    /**
     * 默认的最大表格颜色种类
     */
    private static final int DEFAULT_MAX_GRID_COLOR_TYPE = 10;

    /**
     * 贝号
     */
    private String bay_num = null;

    /**
     * 屏幕甲板最大行数
     */
    private int sumScreenRow_board = 0;

    /**
     * 屏幕甲板最小行数
     */
    private int minScreenRow_board = 0;

    /**
     * 屏幕甲板最大列数
     */
    private int sumScreenCol_board = 0;

    /**
     * 屏幕船舱最大行数
     */
    private int sumScreenRow_cabin = 0;

    /**
     * 屏幕船舱最小行数
     */
    private int minScreenRow_cabin = 0;

    /**
     * 屏幕船舱最大列数
     */
    private int sumScreenCol_cabin = 0;

    /**
     * 表格颜色索引数组大小
     */
    private int gridColorType = DEFAULT_MAX_GRID_COLOR_TYPE;


    /**
     * 获取贝号
     *
     * @return 贝号
     */
    public String getBay_num() {
        return bay_num;
    }

    /**
     * 设置贝号
     *
     * @param bay_num 贝号
     */
    public void setBay_num(String bay_num) {
        this.bay_num = bay_num;
    }

    /**
     * 获取屏幕甲板最大行数
     *
     * @return 屏幕甲板最大行数
     */
    public int getSumScreenRow_board() {
        return sumScreenRow_board;
    }

    /**
     * 设置屏幕甲板最大行数
     *
     * @param sumScreenRow_board 屏幕甲板最大行数
     */
    public void setSumScreenRow_board(int sumScreenRow_board) {
        this.sumScreenRow_board = sumScreenRow_board;
    }

    /**
     * 获取屏幕甲板最小行数
     *
     * @return 屏幕甲板最小行数
     */
    public int getMinScreenRow_board() {
        return minScreenRow_board;
    }

    /**
     * 设置屏幕甲板最小行数
     *
     * @param minScreenRow_board 屏幕甲板最小行数
     */
    public void setMinScreenRow_board(int minScreenRow_board) {
        this.minScreenRow_board = minScreenRow_board;
    }


    /**
     * 获取屏幕甲板最大列数
     *
     * @return 屏幕甲板最大列数
     */
    public int getSumScreenCol_board() {
        return sumScreenCol_board;
    }

    /**
     * 设置屏幕甲板最大列数
     *
     * @param sumScreenCol_board 屏幕甲板最大列数
     */
    public void setSumScreenCol_board(int sumScreenCol_board) {
        this.sumScreenCol_board = sumScreenCol_board;
    }

    /**
     * 获取屏幕船舱最大行数
     *
     * @return 屏幕船舱最大行数
     */
    public int getSumScreenRow_cabin() {
        return sumScreenRow_cabin;
    }

    /**
     * 设置屏幕船舱最大行数
     *
     * @param sumScreenRow_cabin 屏幕船舱最大行数
     */
    public void setSumScreenRow_cabin(int sumScreenRow_cabin) {
        this.sumScreenRow_cabin = sumScreenRow_cabin;
    }

    /**
     * 获取屏幕船舱最小行数
     *
     * @return 屏幕船舱最大行数
     */
    public int getMinScreenRow_cabin() {
        return minScreenRow_cabin;
    }

    /**
     * 设置屏幕船舱最小行数
     *
     * @param minScreenRow_cabin 屏幕船舱最大行数
     */
    public void setMinScreenRow_cabin(int minScreenRow_cabin) {
        this.minScreenRow_cabin = minScreenRow_cabin;
    }

    /**
     * 获取屏幕船舱最大列数
     *
     * @return 屏幕船舱最大列数
     */
    public int getSumScreenCol_cabin() {
        return sumScreenCol_cabin;
    }

    /**
     * 设置屏幕船舱最大列数
     *
     * @param sumScreenCol_cabin 屏幕船舱最大列数
     */
    public void setSumScreenCol_cabin(int sumScreenCol_cabin) {
        this.sumScreenCol_cabin = sumScreenCol_cabin;
    }

    /**
     * 获取表格颜色索引数组大小
     *
     * @return 表格颜色索引数组大小
     */
    public int getGridColorType() {
        return gridColorType;
    }

    /**
     * 设置表格颜色索引数组大小
     *
     * @param gridColorType 表格颜色索引数组大小
     */
    public void setGridColorType(int gridColorType) {
        this.gridColorType = gridColorType;
    }


}

package com.port.shenh.intelligenttally.bean;

/**
 * 个统计数据模型
 *
 * @author shenh
 * @version 1.0 2017/3/29
 * @since 1.0 2017/3/29
 */
public class SingleStatistics {


    /**
     * 姓名
     */
    private int name = 0;

    /**
     * 20空
     */
    private int E_20 = 0;

    /**
     * 20重文本框
     */
    private int F_20 = 0;

    /**
     * 40空
     */
    private int E_40 = 0;

    /**
     * 40重
     */
    private int F_40 = 0;

    /**
     * 其他空
     */
    private int E_other = 0;

    /**
     * 其他重
     */
    private int F_other = 0;

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }


    public int getE_20() {
        return E_20;
    }

    public void setE_20(int e_20) {
        E_20 = e_20;
    }

    public int getF_20() {
        return F_20;
    }

    public void setF_20(int f_20) {
        F_20 = f_20;
    }

    public int getE_40() {
        return E_40;
    }

    public void setE_40(int e_40) {
        E_40 = e_40;
    }

    public int getF_40() {
        return F_40;
    }

    public void setF_40(int f_40) {
        F_40 = f_40;
    }


    public int getE_other() {
        return E_other;
    }

    public void setE_other(int e_other) {
        E_other = e_other;
    }

    public int getF_other() {
        return F_other;
    }

    public void setF_other(int f_other) {
        F_other = f_other;
    }

}

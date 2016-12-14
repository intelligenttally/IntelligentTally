package com.port.shenh.intelligenttally.bean;
/**
 * Created by sh on 2016/1/15.
 */

/**
 * 航次数据模型
 *
 * @author sh
 * @version 1.0 2015/9/19
 * @since 1.0
 */
public class Voyage {

    /**
     * 航次编码
     */
    private String ship_id = null;

    /**
     * 船舶编码
     */
    private String v_id = null;

    /**
     * 泊位号
     */
    private String berthno = null;

    /**
     * 航次
     */
    private String voyage = null;

    /**
     * 中文船名
     */
    private String chi_vessel = null;

    /**
     * 进出口编码
     */
    private String codeInOut = null;

    /**
     * 内外贸
     */
    private String trade = null;

    /**
     * 国外轮
     */
    private String wheel = null;

    /**
     * 已下载
     */
    private boolean downloaded = false;


    /**
     * 获取航次编码
     *
     * @return 航次编码
     */
    public String getShip_id() {
        return ship_id;
    }

    /**
     * 设置航次编码
     *
     * @param ship_id 航次编码
     */
    public void setShip_id(String ship_id) {
        this.ship_id = ship_id;
    }

    /**
     * 获取船舶编码
     *
     * @return 船舶编码
     */
    public String getV_id() {
        return v_id;
    }

    /**
     * 设置船舶编码
     *
     * @param v_id 船舶编码
     */
    public void setV_id(String v_id) {
        this.v_id = v_id;
    }

    /**
     * 获取泊位号
     *
     * @return 泊位号
     */
    public String getBerthno() {
        return berthno;
    }

    /**
     * 设置泊位号
     *
     * @param berthno 泊位号
     */
    public void setBerthno(String berthno) {
        this.berthno = berthno;
    }

    /**
     * 获取航次
     *
     * @return 航次
     */
    public String getVoyage() {
        return voyage;
    }

    /**
     * 设置航次
     *
     * @param voyage 航次
     */
    public void setVoyage(String voyage) {
        this.voyage = voyage;
    }

    /**
     * 获取中文船名
     *
     * @return 中文船名
     */
    public String getChi_vessel() {
        return chi_vessel;
    }

    /**
     * 设置中文船名
     *
     * @param chi_vessel 中文船名
     */
    public void setChi_vessel(String chi_vessel) {
        this.chi_vessel = chi_vessel;
    }

    /**
     * 获取进出口编码
     *
     * @return 出口编码
     */
    public String getCodeInOut() {
        return codeInOut;
    }

    /**
     * 设置进出口编码
     *
     * @param codeInOut 进出口编码
     */
    public void setCodeInOut(String codeInOut) {
        this.codeInOut = codeInOut;
    }

    /**
     * 获取内外贸
     *
     * @return 内外贸
     */
    public String getTrade() {
        return trade;
    }

    /**
     * 设置内外贸
     *
     * @param trade 内外贸
     */
    public void setTrade(String trade) {
        this.trade = trade;
    }

    /**
     * 获取国外轮
     *
     * @return 国外轮
     */
    public String getWheel() {
        return wheel;
    }

    /**
     * 设置国外轮
     *
     * @param wheel 国外轮
     */
    public void setWheel(String wheel) {
        this.wheel = wheel;
    }

    /**
     * 获取已下载标志
     *
     * @return true/false
     */
    public boolean isDownloaded() {
        return downloaded;
    }

    /**
     * 设置已下载
     *
     * @param downloaded true/false
     */
    public void setDownloaded(boolean downloaded) {
        this.downloaded = downloaded;
    }
}

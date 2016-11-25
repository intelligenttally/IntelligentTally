package com.port.shenh.intelligenttally.bean;

/**
 * Created by sh on 2016/11/24.
 */

/**
 * 船图数据模型
 *
 * @author sh
 * @version 1.0 2016/11/24
 * @since 1.0
 */
public class ShipImage {

    /**
     * 编码ID
     */
    private String id = null;

    /**
     * 船图ID
     */
    String IMAGE_ID = "image_id";

    /**
     * 贝
     */
    private String bay_no = null;

    /**
     * 列
     */
    private String bay_col = null;

    /**
     * 层
     */
    private String bay_row = null;

    /**
     * 箱号
     */
    private String container_no = null;

    /**
     * 尺寸
     */
    private String size_con = null;

    /**
     * 箱型
     */
    private String container_type = null;

    /**
     * 空/重
     */
    private String code_empty = null;

    /**
     * 重量
     */
    private String  weight = null;

    /**
     * 工作时间
     */
    private String work_date = null;

    /**
     * 铅封号
     */
    private String sealno = null;

    /**
     * 捣箱
     */
    private String moved_name = null;

    /**
     * 内外贸
     */
    private String inoutmark = null;

    /**
     * 中转箱
     */
    private String transmark = null;

    /**
     * 节假日
     */
    private String holidays = null;

    /**
     * 夜班
     */
    private String night = null;

    /**
     * 桥吊号
     */
    private String code_crane = null;

    /**
     * 理货员
     */
    private String name = null;

    /**
     * 获取编码ID
     * @return 编码ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置编码ID
     * @param id 编码
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取船图ID
     * @return 船图ID
     */
    public String getIMAGE_ID() {
        return IMAGE_ID;
    }

    /**
     * 设置船图ID
     * @param IMAGE_ID 船图ID
     */
    public void setIMAGE_ID(String IMAGE_ID) {
        this.IMAGE_ID = IMAGE_ID;
    }

    /**
     * 获取贝
     * @return 贝
     */
    public String getBay_no() {
        return bay_no;
    }

    /**
     * 设置贝
     * @param bay_no 贝
     */
    public void setBay_no(String bay_no) {
        this.bay_no = bay_no;
    }

    /**
     * 获取列
     * @return 列
     */
    public String getBay_col() {
        return bay_col;
    }

    /**
     * 设置列
     * @param bay_col 列
     */
    public void setBay_col(String bay_col) {
        this.bay_col = bay_col;
    }

    /**
     * 获取层
     * @return 层
     */
    public String getBay_row() {
        return bay_row;
    }

    /**
     * 设置层
     * @param bay_row 层
     */
    public void setBay_row(String bay_row) {
        this.bay_row = bay_row;
    }

    /**
     * 获取箱号
     * @return 箱号
     */
    public String getContainer_no() {
        return container_no;
    }

    /**
     * 设置箱号
     * @param container_no 箱号
     */
    public void setContainer_no(String container_no) {
        this.container_no = container_no;
    }

    /**
     * 获取尺寸
     * @return 尺寸
     */
    public String getSize_con() {
        return size_con;
    }

    /**
     * 设置尺寸
     * @param size_con 尺寸
     */
    public void setSize_con(String size_con) {
        this.size_con = size_con;
    }

    /**
     * 获取箱型
     * @return 箱型
     */
    public String getContainer_type() {
        return container_type;
    }

    /**
     * 设置箱型
     * @param container_type 箱型
     */
    public void setContainer_type(String container_type) {
        this.container_type = container_type;
    }

    /**
     * 获取空/重
     * @return 空/重
     */
    public String getCode_empty() {
        return code_empty;
    }

    /**
     * 设置空/重
     * @param code_empty 空/重
     */
    public void setCode_empty(String code_empty) {
        this.code_empty = code_empty;
    }

    /**
     * 获取重量
     * @return 重量
     */
    public String getWeight() {
        return weight;
    }

    /**
     * 设置重量
     * @param weight 重量
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     * 获取工作时间
     * @return 工作时间
     */
    public String getWork_date() {
        return work_date;
    }

    /**
     * 设置工作时间
     * @param work_date 工作时间
     */
    public void setWork_date(String work_date) {
        this.work_date = work_date;
    }

    /**
     * 获取铅封号
     * @return 铅封号
     */
    public String getSealno() {
        return sealno;
    }

    /**
     * 设置铅封号
     * @param sealno 铅封号
     */
    public void setSealno(String sealno) {
        this.sealno = sealno;
    }

    /**
     * 获取捣箱
     * @return 捣箱
     */
    public String getMoved_name() {
        return moved_name;
    }

    /**
     * 设置捣箱
     * @param moved_name 捣箱
     */
    public void setMoved_name(String moved_name) {
        this.moved_name = moved_name;
    }

    /**
     * 获取内外贸
     * @return 内外贸
     */
    public String getInoutmark() {
        return inoutmark;
    }

    /**
     * 设置内外贸
     * @param inoutmark 内外贸
     */
    public void setInoutmark(String inoutmark) {
        this.inoutmark = inoutmark;
    }

    /**
     * 获取中转箱
     * @return 中转箱
     */
    public String getTransmark() {
        return transmark;
    }

    /**
     * 设置中转箱
     * @param transmark 中转箱
     */
    public void setTransmark(String transmark) {
        this.transmark = transmark;
    }

    /**
     * 获取节假日
     * @return 节假日
     */
    public String getHolidays() {
        return holidays;
    }

    /**
     * 设置节假日
     * @param holidays 节假日
     */
    public void setHolidays(String holidays) {
        this.holidays = holidays;
    }

    /**
     * 获取夜班
     * @return 夜班
     */
    public String getNight() {
        return night;
    }

    /**
     * 设置夜班
     * @param night 夜班
     */
    public void setNight(String night) {
        this.night = night;
    }

    /**
     * 获取桥吊号
     * @return 桥吊号
     */
    public String getCode_crane() {
        return code_crane;
    }

    /**
     * 设置桥吊号
     * @param code_crane 桥吊号
     */
    public void setCode_crane(String code_crane) {
        this.code_crane = code_crane;
    }

    /**
     * 获取理货员
     * @return 理货员
     */
    public String getName() {
        return name;
    }

    /**
     * 设置理货员
     * @param name 理货员
     */
    public void setName(String name) {
        this.name = name;
    }
}

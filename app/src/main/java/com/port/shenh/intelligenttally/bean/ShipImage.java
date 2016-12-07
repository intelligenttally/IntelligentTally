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
     * 航次编码
     */
    private String ship_id = null;

    /**
     * 船舶编码
     */
    private String v_id = null;

    /**
     * 英文船名
     */
    private String eng_vessel = null;

    /**
     * 中文船名
     */
    private String chi_vessel = null;

    /**
     * 甲板/舱内
     */
    private String location = null;


    /**
     * 贝号
     */
    private String bay_num = null;

    /**
     * 贝列
     */
    private String bay_col = null;

    /**
     * 贝层
     */
    private String bay_row = null;

    /**
     * 标准贝位
     */
    private String sbayno = null;

    /**
     * 理论通贝
     */
    private String tbayno = null;

    /**
     * 被通时贝位
     */
    private String jbayno = null;

    /**
     * 用户标志
     */
    private String user_char = null;

    /**
     * 屏幕行
     */
    private String screen_row = null;

    /**
     * 屏幕列
     */
    private String screen_col = null;

    /**
     * 通贝标志
     */
    private String joint = null;

    /**
     * 装货港
     */
    private String code_load_port = null;

    /**
     * 卸货港
     */
    private String code_unload_port = null;

    /**
     * 交界地
     */
    private String delivery = null;

    /**
     * 捣箱标志
     */
    private String moved = null;

    /**
     * 卸箱标志
     */
    private String unload_mark = null;

    /**
     * 理货员工号
     */
    private String work_no = null;

    /**
     * 危险品等级
     */
    private String danger_grade = null;

    /**
     * 设置温度
     */
    private String degree_setting = null;

    /**
     * 温度单位
     */
    private String degree_unit = null;

    /**
     * 最小温度
     */
    private String min_degree = null;

    /**
     * 最大温度
     */
    private String max_degree = null;

    /**
     * 贝位号
     */
    private String bayno = null;

    /**
     * 原贝
     */
    private String oldbayno = null;

    /**
     * 桥吊号
     */
    private String code_crane = null;


    /**
     * 船图ID
     */
    String image_id = "image_id";

    /**
     * 贝
     */
    private String baynum = null;

    /**
     * 列
     */
    private String baycol = null;

    /**
     * 层
     */
    private String bayrow = null;

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
    private String weight = null;

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
     * 理货员
     */
    private String name = null;

    /**
     * 获取编码ID
     *
     * @return 编码ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置编码ID
     *
     * @param id 编码
     */
    public void setId(String id) {
        this.id = id;
    }

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
     * 获取英文船名
     *
     * @return 英文船名
     */
    public String getEng_vessel() {
        return eng_vessel;
    }

    /**
     * 设置英文船名
     *
     * @param eng_vessel 英文船名
     */
    public void setEng_vessel(String eng_vessel) {
        this.eng_vessel = eng_vessel;
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
     * 获取甲板/舱内
     *
     * @return 甲板/舱内
     */
    public String getLocation() {
        return location;
    }

    /**
     * 设置甲板/舱内
     *
     * @param location 甲板/舱内
     */
    public void setLocation(String location) {
        this.location = location;
    }

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
     * 获取贝列
     *
     * @return 贝列
     */
    public String getBay_col() {
        return bay_col;
    }

    /**
     * 设置贝列
     *
     * @param bay_col 贝列
     */
    public void setBay_col(String bay_col) {
        this.bay_col = bay_col;
    }

    /**
     * 获取贝层
     *
     * @return 贝层
     */
    public String getBay_row() {
        return bay_row;
    }

    /**
     * 设置贝层
     *
     * @param bay_row 贝层
     */
    public void setBay_row(String bay_row) {
        this.bay_row = bay_row;
    }

    /**
     * 获取标准贝位
     *
     * @return 标准贝位
     */
    public String getSbayno() {
        return sbayno;
    }

    /**
     * 设置标准贝位
     *
     * @param sbayno 标准贝位
     */
    public void setSbayno(String sbayno) {
        this.sbayno = sbayno;
    }

    /**
     * 获取理论通贝
     *
     * @return 理论通贝
     */
    public String getTbayno() {
        return tbayno;
    }

    /**
     * 设置理论通贝
     *
     * @param tbayno 理论通贝
     */
    public void setTbayno(String tbayno) {
        this.tbayno = tbayno;
    }

    /**
     * 获取被通时贝位
     *
     * @return 被通时贝位
     */
    public String getJbayno() {
        return jbayno;
    }

    /**
     * 设置被通时贝位
     *
     * @param jbayno 被通时贝位
     */
    public void setJbayno(String jbayno) {
        this.jbayno = jbayno;
    }

    /**
     * 获取用户标志
     *
     * @return 用户标志
     */
    public String getUser_char() {
        return user_char;
    }

    /**
     * 设置用户标志
     *
     * @param user_char 用户标志
     */
    public void setUser_char(String user_char) {
        this.user_char = user_char;
    }

    /**
     * 获取屏幕行
     *
     * @return 屏幕行
     */
    public String getScreen_row() {
        return screen_row;
    }

    /**
     * 设置屏幕行
     *
     * @param screen_row 屏幕行
     */
    public void setScreen_row(String screen_row) {
        this.screen_row = screen_row;
    }

    /**
     * 获取屏幕列
     *
     * @return 屏幕列
     */
    public String getScreen_col() {
        return screen_col;
    }

    /**
     * 设置屏幕列
     *
     * @param screen_col 屏幕列
     */
    public void setScreen_col(String screen_col) {
        this.screen_col = screen_col;
    }

    /**
     * 获取通贝标志
     *
     * @return 通贝标志
     */
    public String getJoint() {
        return joint;
    }

    /**
     * 设置通贝标志
     *
     * @param joint 通贝标志
     */
    public void setJoint(String joint) {
        this.joint = joint;
    }

    /**
     * 获取装货港
     *
     * @return 装货港
     */
    public String getCode_load_port() {
        return code_load_port;
    }

    /**
     * 设置装货港
     *
     * @param code_load_port 装货港
     */
    public void setCode_load_port(String code_load_port) {
        this.code_load_port = code_load_port;
    }

    /**
     * 获取卸货港
     *
     * @return 卸货港
     */
    public String getCode_unload_port() {
        return code_unload_port;
    }

    /**
     * 设置卸货港
     *
     * @param code_unload_port 卸货港
     */
    public void setCode_unload_port(String code_unload_port) {
        this.code_unload_port = code_unload_port;
    }

    /**
     * 获取交界地
     *
     * @return 交界地
     */
    public String getDelivery() {
        return delivery;
    }

    /**
     * 设置交界地
     *
     * @param delivery 交界地
     */
    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    /**
     * 获取捣箱标志
     *
     * @return 捣箱标志
     */
    public String getMoved() {
        return moved;
    }

    /**
     * 设置捣箱标志
     *
     * @param moved 捣箱标志
     */
    public void setMoved(String moved) {
        this.moved = moved;
    }

    /**
     * 获取卸箱标志
     *
     * @return 卸箱标志
     */
    public String getUnload_mark() {
        return unload_mark;
    }

    /**
     * 设置卸箱标志
     *
     * @param unload_mark 卸箱标志
     */
    public void setUnload_mark(String unload_mark) {
        this.unload_mark = unload_mark;
    }

    /**
     * 获取理货员工号
     *
     * @return 理货员工号
     */
    public String getWork_no() {
        return work_no;
    }

    /**
     * 设置理货员工号
     *
     * @param work_no 理货员工号
     */
    public void setWork_no(String work_no) {
        this.work_no = work_no;
    }

    /**
     * 获取危险品等级
     *
     * @return 危险品等级
     */
    public String getDanger_grade() {
        return danger_grade;
    }

    /**
     * 设置危险品等级
     *
     * @param danger_grade 危险品等级
     */
    public void setDanger_grade(String danger_grade) {
        this.danger_grade = danger_grade;
    }

    /**
     * 获取设置温度
     *
     * @return 设置温度
     */
    public String getDegree_setting() {
        return degree_setting;
    }

    /**
     * 设置设置温度
     *
     * @param degree_setting 设置温度
     */
    public void setDegree_setting(String degree_setting) {
        this.degree_setting = degree_setting;
    }

    /**
     * 获取温度单位
     *
     * @return 温度单位
     */
    public String getDegree_unit() {
        return degree_unit;
    }

    /**
     * 设置温度单位
     *
     * @param degree_unit 温度单位
     */
    public void setDegree_unit(String degree_unit) {
        this.degree_unit = degree_unit;
    }

    /**
     * 获取最小温度
     *
     * @return 最小温度
     */
    public String getMin_degree() {
        return min_degree;
    }

    /**
     * 设置最小温度
     *
     * @param min_degree 最小温度
     */
    public void setMin_degree(String min_degree) {
        this.min_degree = min_degree;
    }

    /**
     * 获取最大温度
     *
     * @return 最大温度
     */
    public String getMax_degree() {
        return max_degree;
    }

    /**
     * 设置最大温度
     *
     * @param max_degree 最大温度
     */
    public void setMax_degree(String max_degree) {
        this.max_degree = max_degree;
    }

    /**
     * 获取贝位号
     *
     * @return 贝位号
     */
    public String getBayno() {
        return bayno;
    }

    /**
     * 设置贝位号
     *
     * @param bayno 贝位号
     */
    public void setBayno(String bayno) {
        this.bayno = bayno;
    }

    /**
     * 获取原贝
     *
     * @return 原贝
     */
    public String getOldbayno() {
        return oldbayno;
    }

    /**
     * 设置原贝
     *
     * @param oldbayno 原贝
     */
    public void setOldbayno(String oldbayno) {
        this.oldbayno = oldbayno;
    }

    /**
     * 获取桥吊号
     *
     * @return 桥吊号
     */
    public String getCode_crane() {
        return code_crane;
    }

    /**
     * 设置桥吊号
     *
     * @param code_crane 桥吊号
     */
    public void setCode_crane(String code_crane) {
        this.code_crane = code_crane;
    }

    /**
     * 获取船图ID
     *
     * @return 船图ID
     */
    public String getImage_id() {
        return image_id;
    }

    /**
     * 设置船图ID
     *
     * @param image_id 船图ID
     */
    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }


    /**
     * 获取贝
     *
     * @return 贝
     */
    public String getBaynum() {
        return baynum;
    }

    /**
     * 设置贝
     *
     * @param baynum 贝
     */
    public void setBaynum(String baynum) {
        this.baynum = baynum;
    }

    /**
     * 获取列
     *
     * @return 列
     */
    public String getBaycol() {
        return baycol;
    }

    /**
     * 设置列
     *
     * @param baycol 列
     */
    public void setBaycol(String baycol) {
        this.baycol = baycol;
    }

    /**
     * 获取层
     *
     * @return 层
     */
    public String getBayrow() {
        return bayrow;
    }

    /**
     * 设置层
     *
     * @param bayrow 层
     */
    public void setBayrow(String bayrow) {
        this.bayrow = bayrow;
    }

    /**
     * 获取箱号
     *
     * @return 箱号
     */
    public String getContainer_no() {
        return container_no;
    }

    /**
     * 设置箱号
     *
     * @param container_no 箱号
     */
    public void setContainer_no(String container_no) {
        this.container_no = container_no;
    }

    /**
     * 获取尺寸
     *
     * @return 尺寸
     */
    public String getSize_con() {
        return size_con;
    }

    /**
     * 设置尺寸
     *
     * @param size_con 尺寸
     */
    public void setSize_con(String size_con) {
        this.size_con = size_con;
    }

    /**
     * 获取箱型
     *
     * @return 箱型
     */
    public String getContainer_type() {
        return container_type;
    }

    /**
     * 设置箱型
     *
     * @param container_type 箱型
     */
    public void setContainer_type(String container_type) {
        this.container_type = container_type;
    }

    /**
     * 获取空/重
     *
     * @return 空/重
     */
    public String getCode_empty() {
        return code_empty;
    }

    /**
     * 设置空/重
     *
     * @param code_empty 空/重
     */
    public void setCode_empty(String code_empty) {
        this.code_empty = code_empty;
    }

    /**
     * 获取重量
     *
     * @return 重量
     */
    public String getWeight() {
        return weight;
    }

    /**
     * 设置重量
     *
     * @param weight 重量
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     * 获取工作时间
     *
     * @return 工作时间
     */
    public String getWork_date() {
        return work_date;
    }

    /**
     * 设置工作时间
     *
     * @param work_date 工作时间
     */
    public void setWork_date(String work_date) {
        this.work_date = work_date;
    }

    /**
     * 获取铅封号
     *
     * @return 铅封号
     */
    public String getSealno() {
        return sealno;
    }

    /**
     * 设置铅封号
     *
     * @param sealno 铅封号
     */
    public void setSealno(String sealno) {
        this.sealno = sealno;
    }

    /**
     * 获取捣箱
     *
     * @return 捣箱
     */
    public String getMoved_name() {
        return moved_name;
    }

    /**
     * 设置捣箱
     *
     * @param moved_name 捣箱
     */
    public void setMoved_name(String moved_name) {
        this.moved_name = moved_name;
    }

    /**
     * 获取内外贸
     *
     * @return 内外贸
     */
    public String getInoutmark() {
        return inoutmark;
    }

    /**
     * 设置内外贸
     *
     * @param inoutmark 内外贸
     */
    public void setInoutmark(String inoutmark) {
        this.inoutmark = inoutmark;
    }

    /**
     * 获取中转箱
     *
     * @return 中转箱
     */
    public String getTransmark() {
        return transmark;
    }

    /**
     * 设置中转箱
     *
     * @param transmark 中转箱
     */
    public void setTransmark(String transmark) {
        this.transmark = transmark;
    }

    /**
     * 获取节假日
     *
     * @return 节假日
     */
    public String getHolidays() {
        return holidays;
    }

    /**
     * 设置节假日
     *
     * @param holidays 节假日
     */
    public void setHolidays(String holidays) {
        this.holidays = holidays;
    }

    /**
     * 获取夜班
     *
     * @return 夜班
     */
    public String getNight() {
        return night;
    }

    /**
     * 设置夜班
     *
     * @param night 夜班
     */
    public void setNight(String night) {
        this.night = night;
    }

    /**
     * 获取理货员
     *
     * @return 理货员
     */
    public String getName() {
        return name;
    }

    /**
     * 设置理货员
     *
     * @param name 理货员
     */
    public void setName(String name) {
        this.name = name;
    }
}

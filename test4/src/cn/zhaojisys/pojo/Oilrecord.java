package cn.zhaojisys.pojo;

import cn.zhaojisys.tools.Constants;

/**
 * 集卡购油管理系统
 * 燃油收支记录
 * 
 */
public class Oilrecord {
	private Integer id;//ID
	private Integer gsId;//站点ID 站点关联字段
	private String operationTime;//操作时间
	private Integer vipUserId;//会员id 会员关联字段(会员可带出车牌 车主)
	private Integer income;//收入（L）
	private Integer expenditure;//支出（L）
	private Integer remaining;//剩余油量（L）
	private Integer sumIncome;//当月总燃油收入量
	private Integer sumExpenditure;//当月总燃油提取量
	private String opDate;//操作时间的截取字段
	private String monthDate;//截取日期中的月份
	private Integer infotype ; // 记录类型 1会员 2站点
	private Integer balance;//0为未结算 1为已结算
	private Double fee;//服务费总价

	private Gasstation gasstation;// 站点类
	
	private String friendId;//加油方式
	
	private Vipuserinfo vipuserinfo;
	
	private Integer paymentType; //收支类型0 收入 1支出 
	
	private Integer operationType;// 操作类型0 平台 1好友 2站点 
	private Integer mycarId; //关联车主id（物流公司Vip有车主）
	private Integer logicId; //车主关联物流公司
	public Gasstation getGasstation() {
		return gasstation;
	}
	public void setGasstation(Gasstation gasstation) {
		this.gasstation = gasstation;
	}
	
	public String getFriendId() {
		return friendId;
	}
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	public Vipuserinfo getVipuserinfo() {
		return vipuserinfo;
	}
	public void setVipuserinfo(Vipuserinfo vipuserinfo) {
		this.vipuserinfo = vipuserinfo;
	}
	public Integer getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}
	public Integer getOperationType() {
		return operationType;
	}
	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}
	public Integer getSumIncome() {
		return sumIncome;
	}
	public void setSumIncome(Integer sumIncome) {
		this.sumIncome = sumIncome;
	}
	public Integer getSumExpenditure() {
		return sumExpenditure;
	}
	public void setSumExpenditure(Integer sumExpenditure) {
		this.sumExpenditure = sumExpenditure;
	}
	public String getOpDate() {
		return opDate;
	}
	public void setOpDate(String opDate) {
		this.opDate = Constants.changeDate(getOperationTime());
	}
	public String getMonthDate() {
		return monthDate;
	}
	public void setMonthDate(String monthDate) {
		this.monthDate = Constants.changeDateMonth(getOperationTime());
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGsId() {
		return gsId;
	}
	public void setGsId(Integer gsId) {
		this.gsId = gsId;
	}
	public String getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
		setOpDate(operationTime);
		setMonthDate(operationTime);
	}
	public Integer getVipUserId() {
		return vipUserId;
	}
	public void setVipUserId(Integer vipUserId) {
		this.vipUserId = vipUserId;
	}
	public Integer getIncome() {
		return income;
	}
	public void setIncome(Integer income) {
		this.income = income;
	}
	public Integer getExpenditure() {
		return expenditure;
	}
	public void setExpenditure(Integer expenditure) {
		this.expenditure = expenditure;
	}
	public Integer getRemaining() {
		return remaining;
	}
	public void setRemaining(Integer remaining) {
		this.remaining = remaining;
	}
	public Integer getMycarId() {
		return mycarId;
	}
	public void setMycarId(Integer mycarId) {
		this.mycarId = mycarId;
	}
	public Integer getLogicId() {
		return logicId;
	}
	public void setLogicId(Integer logicId) {
		this.logicId = logicId;
	}
	
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	public Integer getInfotype() {
		return infotype;
	}
	public void setInfotype(Integer infotype) {
		this.infotype = infotype;
	}
}

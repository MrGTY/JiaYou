package cn.zhaojisys.pojo;

import cn.zhaojisys.tools.Constants;

/**
 * 集卡购油管理系统
 * 轮胎收支记录
 *
 */
public class Tyredatails {
	private Integer id;//ID
	private String operationData;//操作时间
	private Integer vipUserId;//会员id 会员关联字段(会员可带出车牌 车主)
	private String typrSpec;//轮胎品牌
	private String tireModel;//轮胎型号
	private String typrMaterial;//轮胎规格
	private Integer quantity;//数量（支）
	private Integer income;//收入（元）
	private Integer expenditure;//支出（元）
	private Integer balance;//剩余金额（元）
	private Integer sumIncome;// 当月轮胎总金额；
	private Integer sumExpenditure;//当月轮胎总提取额
	private String opDate;
	private String monthDate;//截取日期中的月份
	private Vipuserinfo vipuserinfo;
	private Integer infotype ; // 记录类型 1会员 2站点
	
	public Integer getInfotype() {
		return infotype;
	}
	public void setInfotype(Integer infotype) {
		this.infotype = infotype;
	}
	private  Integer zdid ;  //关联站点id

	private Integer operationType;// 操作类型0 平台 1好友 2站点 
	
	private String uptyreType;// 买胎方式
	
	private Gasstation gasstation;// 站点类
	
	public Gasstation getGasstation() {
		return gasstation;
	}
	public void setGasstation(Gasstation gasstation) {
		this.gasstation = gasstation;
	}
	public Integer getZdid() {
		return zdid;
	}
	public void setZdid(Integer zdid) {
		this.zdid = zdid;
	}
	public Vipuserinfo getVipuserinfo() {
		return vipuserinfo;
	}
	public void setVipuserinfo(Vipuserinfo vipuserinfo) {
		this.vipuserinfo = vipuserinfo;
	}
	public Integer getOperationType() {
		return operationType;
	}
	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}
	public String getUptyreType() {
		return uptyreType;
	}
	public void setUptyreType(String uptyreType) {
		this.uptyreType = uptyreType;
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
		this.opDate = Constants.changeDate(getOperationData());
	}
	public String getMonthDate() {
		return monthDate;
	}
	public void setMonthDate(String monthDate) {
		this.monthDate = Constants.changeDateMonth(getOperationData());
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOperationData() {
		return operationData;
	}
	public void setOperationData(String operationData) {
		this.operationData = operationData;
		setOpDate(operationData);
		setMonthDate(operationData);
	}
	public Integer getVipUserId() {
		return vipUserId;
	}
	public void setVipUserId(Integer vipUserId) {
		this.vipUserId = vipUserId;
	}
	public String getTyprSpec() {
		return typrSpec;
	}
	public void setTyprSpec(String typrSpec) {
		this.typrSpec = typrSpec;
	}
	public String getTireModel() {
		return tireModel;
	}
	public void setTireModel(String tireModel) {
		this.tireModel = tireModel;
	}
	public String getTyprMaterial() {
		return typrMaterial;
	}
	public void setTyprMaterial(String typrMaterial) {
		this.typrMaterial = typrMaterial;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	
	
}

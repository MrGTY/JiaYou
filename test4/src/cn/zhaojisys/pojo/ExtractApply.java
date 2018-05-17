package cn.zhaojisys.pojo;

import java.util.Date;

import cn.zhaojisys.tools.Constants;

/*
 * 申请表
 */
public class ExtractApply {
	private Integer id;//编号
	private Integer businessType;//业务类型 0 加油 1 轮胎
	private Integer amountDrawn;//申请提取额
	private Integer surplusBalance;//剩余额
	private String telePhone;//联系电话
	private Date applicationTime;//申请时间
	private String emplId;//审批人id 关联员工表 
	private String serialNum;//凭证编号
	private Date approveData;//审批时间
	private Integer applyUserId;//申请人id 关联员工表 
	private Integer gsId;//关联站点id
	private Integer status;//0审核中.1.通过2拒绝
	private Integer note;//备注1加油站点2轮胎站点3召集平台
	private Gasstation gasstation;
	private EmployeInfo employeInfo;
	private String userName;
	
	private String opDate;//操作时间的截取字段
	private String monthDate;//截取日期中的月份
	
	public String getOpDate() {
		return opDate;
	}
	public void setOpDate(String opDate) {
		this.opDate = Constants.changeDate(Constants.getDateToString(getApproveData()));
	}
	public String getMonthDate() {
		return monthDate;
	}
	public void setMonthDate(String monthDate) {
		this.monthDate = Constants.changeDateMonth(Constants.getDateToString(getApproveData()));
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public EmployeInfo getEmployeInfo() {
		return employeInfo;
	}
	public void setEmployeInfo(EmployeInfo employeInfo) {
		this.employeInfo = employeInfo;
	}
	public Gasstation getGasstation() {
		return gasstation;
	}
	public void setGasstation(Gasstation gasstation) {
		this.gasstation = gasstation;
	}
	public Integer getNote() {
		return note;
	}
	public void setNote(Integer note) {
		this.note = note;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBusinessType() {
		return businessType;
	}
	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}
	public Integer getAmountDrawn() {
		return amountDrawn;
	}
	public void setAmountDrawn(Integer amountDrawn) {
		this.amountDrawn = amountDrawn;
	}
	public Integer getSurplusBalance() {
		return surplusBalance;
	}
	public void setSurplusBalance(Integer surplusBalance) {
		this.surplusBalance = surplusBalance;
	}
	public String getTelePhone() {
		return telePhone;
	}
	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}
	public Date getApplicationTime() {
		return applicationTime;
	}
	public void setApplicationTime(Date applicationTime) {
		this.applicationTime = applicationTime;
	}
	public String getEmplId() {
		return emplId;
	}
	public void setEmplId(String emplId) {
		this.emplId = emplId;
	}
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	public Date getApproveData() {
		return approveData;
	}
	public void setApproveData(Date approveData) {
		this.approveData = approveData;
		setOpDate(Constants.getDateToString(approveData));
		setMonthDate(Constants.getDateToString(approveData));
	}
	public Integer getApplyUserId() {
		return applyUserId;
	}
	public void setApplyUserId(Integer applyUserId) {
		this.applyUserId = applyUserId;
	}
	public Integer getGsId() {
		return gsId;
	}
	public void setGsId(Integer gsId) {
		this.gsId = gsId;
	}
	
	
}

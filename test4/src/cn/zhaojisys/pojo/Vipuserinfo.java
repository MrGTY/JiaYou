package cn.zhaojisys.pojo;

import cn.zhaojisys.tools.Constants;

public class Vipuserinfo {
	private  Integer id;//会员编号
	private	 String vipName;//会员账户(手机)
	private  String userName;//会员姓名
	private String phoneNum;//会员手机号
	private String password;//会员密码
	private String busNum;//会员车牌号
	private String createTime;//创建时间
	private  Integer userType;//会员类型 0车主/个人 1：物流公司
	private  Integer oilMass;//燃油余量
	private  Integer tyreBalance;//轮胎余额
	private  Integer qkBalance;//欠款金额
	private String xszimg;//行驶证 图片
	private Integer stateTag;//状态:0启用 1禁用
	private Gasstation gasstation;//站点信息 
	private Integer deleteTag;//是否被删除标记 0 删除 1 未删除
	private  String opDate;//操作时间的截取字段
	private String monthDate;//截取日期中的月份
	private String mycarId;//物流公司
	private Integer changetype;//物流对应的id
	private String uploadImg;//行驶证
	
	public String getUploadImg() {
		return uploadImg;
	}
	public void setUploadImg(String uploadImg) {
		this.uploadImg = uploadImg;
	}
	public String getMycarId() {
		return mycarId;
	}
	public void setMycarId(String mycarId) {
		this.mycarId = mycarId;
	}
	public Integer getChangetype() {
		return changetype;
	}
	public void setChangetype(Integer changetype) {
		this.changetype = changetype;
	}
	public String getOpDate() {
		return opDate;
	}
	public void setOpDate(String opDate) {
		this.opDate = Constants.changeDate(getCreateTime());
	}
	public String getMonthDate() {
		return monthDate;
	}
	public void setMonthDate(String monthDate) {
		this.monthDate = Constants.changeDateMonth(getCreateTime());
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVipName() {
		return vipName;
	}
	public void setVipName(String vipName) {
		this.vipName = vipName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBusNum() {
		return busNum;
	}
	public void setBusNum(String busNum) {
		this.busNum = busNum;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
		setOpDate(createTime);
		setMonthDate(createTime);
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public Integer getOilMass() {
		return oilMass;
	}
	public void setOilMass(Integer oilMass) {
		this.oilMass = oilMass;
	}
	public Integer getTyreBalance() {
		return tyreBalance;
	}
	public void setTyreBalance(Integer tyreBalance) {
		this.tyreBalance = tyreBalance;
	}
	public Integer getQkBalance() {
		return qkBalance;
	}
	public void setQkBalance(Integer qkBalance) {
		this.qkBalance = qkBalance;
	}
	public String getXszimg() {
		return xszimg;
	}
	public void setXszimg(String xszimg) {
		this.xszimg = xszimg;
	}
	public Integer getStateTag() {
		return stateTag;
	}
	public void setStateTag(Integer stateTag) {
		this.stateTag = stateTag;
	}
	public Gasstation getGasstation() {
		return gasstation;
	}
	public void setGasstation(Gasstation gasstation) {
		this.gasstation = gasstation;
	}
	public Integer getDeleteTag() {
		return deleteTag;
	}
	public void setDeleteTag(Integer deleteTag) {
		this.deleteTag = deleteTag;
	}
	
}
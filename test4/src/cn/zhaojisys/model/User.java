package cn.zhaojisys.model;

public class User {

	private Integer[] id;
	private String vipName;
	private String userName;
	private String busNum;
	private Integer userType;
	private Integer stateTag;
	private Integer oilMass;
	private Integer tyreBalance;
	private String password;
	private String mycarId;	
	public String getMycarId() {
		return mycarId;
	}
	public void setMycarId(String mycarId) {
		this.mycarId = mycarId;
	}
	public Integer[] getId() {
		return id;
	}
	public void setId(Integer[] id) {
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
	public String getBusNum() {
		return busNum;
	}
	public void setBusNum(String busNum) {
		this.busNum = busNum;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public Integer getStateTag() {
		return stateTag;
	}
	public void setStateTag(Integer stateTag) {
		this.stateTag = stateTag;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}

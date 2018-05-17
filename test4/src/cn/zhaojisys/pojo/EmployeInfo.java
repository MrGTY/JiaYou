package cn.zhaojisys.pojo;


/*
 * 员工基本信息类
 */
public class EmployeInfo {
	private Integer id;//员工id
	private String loginName;//登录名
	private String userName;//员工名字
	private String position;//职位
	private String department;//部门
	private String mobilePhone;//员工手机
	private String email;//员工邮箱
	private String createData;//创建时间
	private String password;//登陆密码
	private Integer QQ;//员工QQ
	private String power;//员工权限
	private Integer trg;// 状态 0不显示 1显示
	
	public Integer getTrg() {
		return trg;
	}
	public void setTrg(Integer trg) {
		this.trg = trg;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	public String getCreateData() {
		return createData;
	}
	public void setCreateData(String createData) {
		this.createData = createData;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getQQ() {
		return QQ;
	}
	public void setQQ(Integer qQ) {
		QQ = qQ;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	
}

package cn.zhaojisys.pojo;

import java.util.Date;

public class Gathering {
	private Integer id;// 编号
	private Integer type;//收款类型 0 燃油收款 1为轮胎收款
	private String remark;//收款备注
	private Integer vipId;// 关联会员id
	private  String createTime;//创建时间
	private Integer Payamount;//收款金额
	private  Integer empId;// 关联员工id
	private Integer oil;//充值油量
	private Integer ljqk;//累计欠款
	private Vipuserinfo vipuserinfo;//会员类
	private EmployeInfo employeInfo;//员工类
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getVipId() {
		return vipId;
	}
	public void setVipId(Integer vipId) {
		this.vipId = vipId;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getPayamount() {
		return Payamount;
	}
	public void setPayamount(Integer payamount) {
		Payamount = payamount;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public Integer getOil() {
		return oil;
	}
	public void setOil(Integer oil) {
		this.oil = oil;
	}
	public Integer getLjqk() {
		return ljqk;
	}
	public void setLjqk(Integer ljqk) {
		this.ljqk = ljqk;
	}
	public Vipuserinfo getVipuserinfo() {
		return vipuserinfo;
	}
	public void setVipuserinfo(Vipuserinfo vipuserinfo) {
		this.vipuserinfo = vipuserinfo;
	}
	public EmployeInfo getEmployeInfo() {
		return employeInfo;
	}
	public void setEmployeInfo(EmployeInfo employeInfo) {
		this.employeInfo = employeInfo;
	}
	
}

package cn.zhaojisys.pojo;
/**
 * 日志实体类
 * @author user
 *
 */
public class Systemlog {
	private Integer id; //系统用户id
	
	private Integer empId;//关联员工id
	
	private String createTime;//创建时间 
	
	private String type;//类型
	
	private String operationLog;//操作日志
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOperationLog() {
		return operationLog;
	}
	public void setOperationLog(String operationLog) {
		this.operationLog = operationLog;
	}
	
}

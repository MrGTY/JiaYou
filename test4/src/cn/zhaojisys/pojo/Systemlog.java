package cn.zhaojisys.pojo;
/**
 * ��־ʵ����
 * @author user
 *
 */
public class Systemlog {
	private Integer id; //ϵͳ�û�id
	
	private Integer empId;//����Ա��id
	
	private String createTime;//����ʱ�� 
	
	private String type;//����
	
	private String operationLog;//������־
	
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

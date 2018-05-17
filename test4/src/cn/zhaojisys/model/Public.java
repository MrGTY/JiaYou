package cn.zhaojisys.model;

public class Public {
	
	private String operationTime;//操作时间
	
	private Integer paymentType; //收支类型0 收入 1支出 
	
	private Integer operationType;// 操作类型0 平台 1好友 2站点 

	private String  startTime;//开始时间
	
	private String  endTime;//结束时间
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
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
	
	
	
}

package cn.zhaojisys.model;

public class Public {
	
	private String operationTime;//����ʱ��
	
	private Integer paymentType; //��֧����0 ���� 1֧�� 
	
	private Integer operationType;// ��������0 ƽ̨ 1���� 2վ�� 

	private String  startTime;//��ʼʱ��
	
	private String  endTime;//����ʱ��
	
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

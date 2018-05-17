package cn.zhaojisys.pojo;

public class Messages {
	private Integer id;
	private Integer msgType;// 1 交易提醒 2 分配提醒 3消费提醒 4 平台消息
	private String content;// 消息内容
	private String operationTime;// 操作时间
	private Integer vipId;
	private  Integer gsId;// 关联站点id 
	
	public Integer getGsId() {
		return gsId;
	}
	public void setGsId(Integer gsId) {
		this.gsId = gsId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMsgType() {
		return msgType;
	}
	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}
	public Integer getVipId() {
		return vipId;
	}
	public void setVipId(Integer vipId) {
		this.vipId = vipId;
	}
	
}

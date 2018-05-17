package cn.zhaojisys.pojo;

import cn.zhaojisys.tools.Constants;

public class VipuserinfoModel {
	private  Integer id;//会员编号
	private String createTime;//创建时间
	private  String opDate;//操作时间的截取字段
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
		setOpDate(createTime);
	}
	
	public String getOpDate() {
		return opDate;
	}
	public void setOpDate(String opDate) {
		this.opDate = Constants.changeDate(getCreateTime());
	}
	
}
package cn.zhaojisys.pojo;

public class RelationShip {
	
	private Integer id;//好友关系表自增长id
	
	private Integer vId;//被查看好友关系的会员id
	
	private Integer vipId;//被查看好友关系会员的好友id
	public Integer getVipId() {
		return vipId;
	}

	public void setVipId(Integer vipId) {
		this.vipId = vipId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getvId() {
		return vId;
	}

	public void setvId(Integer vId) {
		this.vId = vId;
	}

	

}

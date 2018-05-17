package cn.zhaojisys.pojo;

import java.util.Date;

public class Feetable {
	private Integer id;         //服务费付清消息id
	private Date createtime ;   //付清时间
	private Integer oilmass;    //燃油量L
	private Double chargefee;   //服务费额度
	private Integer state;      //1为已经付清状态
	private String idc;			//记录所有付清id(   1,3,4,5,10   )
	private Integer gassid;		//关联站点Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Integer getOilmass() {
		return oilmass;
	}
	public void setOilmass(Integer oilmass) {
		this.oilmass = oilmass;
	}
	public Double getChargefee() {
		return chargefee;
	}
	public void setChargefee(Double chargefee) {
		this.chargefee = chargefee;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getIdc() {
		return idc;
	}
	public void setIdc(String idc) {
		this.idc = idc;
	}
	public Integer getGassid() {
		return gassid;
	}
	public void setGassid(Integer gassid) {
		this.gassid = gassid;
	}
}

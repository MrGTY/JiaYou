package cn.zhaojisys.pojo;

import java.util.List;

public class EchartsGasstationYear {
	private List<Oilrecord> oilder1;// 查询全年燃油交易额
	private List<Oilrecord> oilder2;// 燃油站点首页全年订单成交个数
	private	List<Oilrecord> oilder3;// 燃油站点首页全年退单量和退单金额
	public List<Oilrecord> getOilder1() {
		return oilder1;
	}
	public void setOilder1(List<Oilrecord> oilder1) {
		this.oilder1 = oilder1;
	}
	public List<Oilrecord> getOilder2() {
		return oilder2;
	}
	public void setOilder2(List<Oilrecord> oilder2) {
		this.oilder2 = oilder2;
	}
	public List<Oilrecord> getOilder3() {
		return oilder3;
	}
	public void setOilder3(List<Oilrecord> oilder3) {
		this.oilder3 = oilder3;
	}
}

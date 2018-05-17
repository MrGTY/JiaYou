package cn.zhaojisys.vo;

import java.util.List;

import cn.zhaojisys.pojo.ExtractApply;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.Paramatersetting;

public class ZhandianSumVo {

	//当月 每天的
	private List<Oilrecord> oilrecords1;//燃油站点查询每天燃油交易额，交易量,可提取額 
	private List<ExtractApply> applies1;//燃油站点查询每天燃油可提取額
	
	
	//当年每月的
	private List<Oilrecord> oilrecords2;//燃油站点查询年燃油交易额，交易量,可提取額
	private List<ExtractApply> applies2;//燃油站点查询年燃油可提取額 
	
	private Paramatersetting paramatersetting;//参数
	
	
	public Paramatersetting getParamatersetting() {
		return paramatersetting;
	}
	public void setParamatersetting(Paramatersetting paramatersetting) {
		this.paramatersetting = paramatersetting;
	}
	public List<Oilrecord> getOilrecords1() {
		return oilrecords1;
	}
	public void setOilrecords1(List<Oilrecord> oilrecords1) {
		this.oilrecords1 = oilrecords1;
	}
	public List<ExtractApply> getApplies1() {
		return applies1;
	}
	public void setApplies1(List<ExtractApply> applies1) {
		this.applies1 = applies1;
	}
	public List<Oilrecord> getOilrecords2() {
		return oilrecords2;
	}
	public void setOilrecords2(List<Oilrecord> oilrecords2) {
		this.oilrecords2 = oilrecords2;
	}
	public List<ExtractApply> getApplies2() {
		return applies2;
	}
	public void setApplies2(List<ExtractApply> applies2) {
		this.applies2 = applies2;
	}
	
}

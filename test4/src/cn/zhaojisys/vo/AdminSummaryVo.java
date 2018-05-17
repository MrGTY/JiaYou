package cn.zhaojisys.vo;

import java.util.List;

import cn.zhaojisys.pojo.ExtractApply;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.Tyredatails;
import cn.zhaojisys.pojo.Vipuserinfo;

public class AdminSummaryVo {
	
	/**
	 * 年交易统计
	 * @return
	 */
	//燃油总收油量 和次数
	private List<Oilrecord> yearOilClosedTotal;

	///轮胎总收油量 和次数
	private List<Tyredatails> yearOilextractTotal;
	
	//燃油提取
	private List<ExtractApply> yearOilTradingCount;
	
	//轮胎提取
	private List<ExtractApply> yeartireCount;
	//会员个数
	private List<Vipuserinfo> yeartireextractCount;
	
	
	/**
	 * 月交易统计
	 */
	//燃油总收油量 和次数
	private List<Oilrecord> oilList1;
	
	//轮胎总收油量 和次数
	private List<Tyredatails> trilList1;
	
	//燃油提取
	private List<ExtractApply> oilextractapply1;
	//轮胎提取
	private List<ExtractApply> tractapply1;
	//会员个数
	private List<Vipuserinfo> vipConut;
	public List<Oilrecord> getYearOilClosedTotal() {
		return yearOilClosedTotal;
	}
	public void setYearOilClosedTotal(List<Oilrecord> yearOilClosedTotal) {
		this.yearOilClosedTotal = yearOilClosedTotal;
	}
	public List<Tyredatails> getYearOilextractTotal() {
		return yearOilextractTotal;
	}
	public void setYearOilextractTotal(List<Tyredatails> yearOilextractTotal) {
		this.yearOilextractTotal = yearOilextractTotal;
	}
	public List<ExtractApply> getYearOilTradingCount() {
		return yearOilTradingCount;
	}
	public void setYearOilTradingCount(List<ExtractApply> yearOilTradingCount) {
		this.yearOilTradingCount = yearOilTradingCount;
	}
	public List<ExtractApply> getYeartireCount() {
		return yeartireCount;
	}
	public void setYeartireCount(List<ExtractApply> yeartireCount) {
		this.yeartireCount = yeartireCount;
	}
	public List<Vipuserinfo> getYeartireextractCount() {
		return yeartireextractCount;
	}
	public void setYeartireextractCount(List<Vipuserinfo> yeartireextractCount) {
		this.yeartireextractCount = yeartireextractCount;
	}
	public List<Oilrecord> getOilList1() {
		return oilList1;
	}
	public void setOilList1(List<Oilrecord> oilList1) {
		this.oilList1 = oilList1;
	}
	public List<Tyredatails> getTrilList1() {
		return trilList1;
	}
	public void setTrilList1(List<Tyredatails> trilList1) {
		this.trilList1 = trilList1;
	}
	public List<ExtractApply> getOilextractapply1() {
		return oilextractapply1;
	}
	public void setOilextractapply1(List<ExtractApply> oilextractapply1) {
		this.oilextractapply1 = oilextractapply1;
	}
	public List<ExtractApply> getTractapply1() {
		return tractapply1;
	}
	public void setTractapply1(List<ExtractApply> tractapply1) {
		this.tractapply1 = tractapply1;
	}
	public List<Vipuserinfo> getVipConut() {
		return vipConut;
	}
	public void setVipConut(List<Vipuserinfo> vipConut) {
		this.vipConut = vipConut;
	}
	
	
	

}

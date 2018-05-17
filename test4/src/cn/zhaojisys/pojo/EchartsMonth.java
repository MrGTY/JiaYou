package cn.zhaojisys.pojo;

import java.util.List;

/**
 * 封装首页报表数据
 * @author 李杰
 *
 */
public class EchartsMonth {
	
	private List<Tyredatails> list2;// 首页查询本月轮胎总交易额 1
	private List<Oilrecord> list3;// 首页查询本月燃油总收油量1
	private	List<Oilrecord> list5;// 首页查询本月燃油总提取量1
	private List<Tyredatails> list7;// 首页查询本月轮胎总提取量1
	private List<VipuserinfoModel> list9;// 首页查询本月新增会员个数 1 
	private List<Oilrecord> list11;// 首页本月燃油交易量1
	private List<Tyredatails> list13;// 首页本月轮胎交易量
	public List<Tyredatails> getList2() {
		return list2;
	}
	public void setList2(List<Tyredatails> list2) {
		this.list2 = list2;
	}
	public List<Oilrecord> getList3() {
		return list3;
	}
	public void setList3(List<Oilrecord> list3) {
		this.list3 = list3;
	}
	public List<Oilrecord> getList5() {
		return list5;
	}
	public void setList5(List<Oilrecord> list5) {
		this.list5 = list5;
	}
	public List<Tyredatails> getList7() {
		return list7;
	}
	public void setList7(List<Tyredatails> list7) {
		this.list7 = list7;
	}
	public List<VipuserinfoModel> getList9() {
		return list9;
	}
	public void setList9(List<VipuserinfoModel> list9) {
		this.list9 = list9;
	}
	public List<Oilrecord> getList11() {
		return list11;
	}
	public void setList11(List<Oilrecord> list11) {
		this.list11 = list11;
	}
	public List<Tyredatails> getList13() {
		return list13;
	}
	public void setList13(List<Tyredatails> list13) {
		this.list13 = list13;
	}
	
	
}

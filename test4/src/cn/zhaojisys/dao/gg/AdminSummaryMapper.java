package cn.zhaojisys.dao.gg;

import java.util.List;

import cn.zhaojisys.pojo.ExtractApply;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.Tyredatails;
import cn.zhaojisys.pojo.Vipuserinfo;

/**
 * 
 * @author 李杰
 *
 */
public interface AdminSummaryMapper {
	
	/**
	 * 年交易统计
	 * @return
	 */
	//燃油总收油量 和次数
	public List<Oilrecord> yearOilClosedTotal()throws Exception;

	//轮胎总收油量 和次数
	public List<Tyredatails> yearOilextractTotal()throws Exception;
	
	//燃油提取
	public List<ExtractApply> yearOilTradingCount()throws Exception;
	
	//轮胎提取
	public List<ExtractApply> yeartireCount()throws Exception;
	//会员个数
	public List<Vipuserinfo> yeartireextractCount()throws Exception;
	
	
	/**
	 * 月交易统计
	 */
	//燃油总收油量 和次数
	public List<Oilrecord> oilList1()throws Exception;
	
	//轮胎总收油量 和次数
	public List<Tyredatails> trilList1()throws Exception;
	
	//燃油提取
	public List<ExtractApply> oilextractapply1()throws Exception;
	//轮胎提取
	public List<ExtractApply> tractapply1()throws Exception;
	//会员个数
	public List<Vipuserinfo> vipConut()throws Exception;
	
	
	
}

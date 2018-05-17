package cn.zhaojisys.dao.app;

import java.sql.SQLException;
import java.util.Date;

import org.apache.ibatis.annotations.Param;

import cn.zhaojisys.pojo.Gasstation;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.Vipuserinfo;

public interface AddOilMapper {

	/**
	 * 车主加油
	 * 
	 * @param id
	 * @param oilMass
	 * @return
	 */
	public int vipAddoil(@Param("id") Integer id, @Param("oilMass") Integer oilMass) throws Exception;

	/**
	 * 加油记录
	 * 
	 * @param Oilrecord
	 * @return
	 */
	public int addOilHistory(Oilrecord Oilrecord) throws Exception;

	// 查询会员信息
	public Vipuserinfo getVipinfo(@Param("vid") Integer vid) throws SQLException;

	// 查询站点信息
	public Gasstation getGassinfo(@Param("gid") Integer gid) throws SQLException;
	
	/**
	 * 最后一次购买时间
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Oilrecord lastMaxBuyshopBy_Id(@Param("vipUserId")Integer vipUserId)throws Exception; 

}

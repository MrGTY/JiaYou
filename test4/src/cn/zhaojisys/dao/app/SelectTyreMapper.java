package cn.zhaojisys.dao.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.zhaojisys.pojo.Feetable;
import cn.zhaojisys.pojo.Gasstation;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.Shop;
import cn.zhaojisys.pojo.Tyredatails;
import cn.zhaojisys.pojo.Vipuserinfo;

public interface SelectTyreMapper {
	
	//查询用户轮胎收支记录列表
	public List<Tyredatails> getTyrelist(@Param("id")Integer id,
										 @Param("pageNo")Integer pageNo,
										 @Param("pageSize")Integer pageSize)throws SQLException;
	
	//查询用户燃油记录列表
	public List<Oilrecord> getOilrelist(@Param("id")Integer id,
			 							@Param("pageNo")Integer pageNo,
			 							@Param("pageSize")Integer pageSize)throws SQLException;
	
	//查询加油站点列表
	public List<Gasstation> getGassOlist(@Param("type")Integer type,
										 @Param("pageNo")Integer pageNo,
										 @Param("pageSize")Integer pageSize)throws SQLException;
	
	//查询轮胎站点列表
	public List<Gasstation> getGassTlist(@Param("type")Integer type,
										 @Param("pageNo")Integer pageNo,
										 @Param("pageSize")Integer pageSize)throws SQLException;
	
	//查询商户轮胎收支记录
	public List<Tyredatails> getStyrelist(@Param("id")Integer id)throws SQLException;
	
	//查询轮胎商品信息
	public Shop getTyreinfo(@Param("id")Integer id)throws SQLException;
	
	//查询会员信息
	public Vipuserinfo getVipinfo(@Param("vid")Integer vid)throws SQLException;
	
	//查询站点信息
	public Gasstation getGassinfo(@Param("gid")Integer gid)throws SQLException;
	
	//修改密码
	public int updatePwd(@Param("password")String password ,@Param("mobile")String mobile)throws SQLException;
	
	//通过手机号查询是否有该用户
	public Vipuserinfo selectVip(@Param("mobile")String mobile)throws SQLException;
	
	
	//消息插入
	public int insertinfo(@Param("msgType")Integer msgType,
			 			  @Param("content")String content,
			 			  @Param("operationTime")String operationTime,
			 			  @Param("vipId")Integer vipId,
			 			  @Param("gsId")Integer gsId
			)throws SQLException;
	//通过站点Id查询 站点二维码
	public Gasstation getQrCodeImg(@Param("gid")Integer gid)throws SQLException;
	//商户修改密码
	public int shupdatePwd(@Param("password")String password ,@Param("mobile")String mobile)throws SQLException;
	//通过手机号查询是否有该商户
	public Gasstation selectGass(@Param("mobile")String mobile) throws SQLException;
	//查询加油量与服务费
	public List<Oilrecord> getfee(@Param("gid")Integer gid) throws SQLException;
	//插入服务费的记录
	public int feeAdd(Feetable feetable) throws SQLException;
	//更新数据库对应燃油记录的状态
	public int updateBalance(Map<String,Object> conmap) throws SQLException;
}

package cn.zhaojisys.service.app;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;

import cn.zhaojisys.dao.gasstation.GasstationMapper;
import cn.zhaojisys.pojo.Gasstation;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.Shop;
import cn.zhaojisys.pojo.Tyredatails;
import cn.zhaojisys.pojo.Vipuserinfo;

public interface SelectTyre {
	//查询轮胎收支记录
	public List<Tyredatails> getTyrelist(@Param("id")Integer id,
			 							 @Param("pageNo")Integer pageNo,
			 							 @Param("pageSize")Integer pageSize)throws Exception;
	
	//查询燃油收支记录
	public List<Oilrecord> getOilrelist(@Param("id")Integer id,
			 							@Param("pageNo")Integer pageNo,
			 							@Param("pageSize")Integer pageSize)throws Exception;
	
	//查询加油站点列表
	public List<Gasstation> getGassOlist(@Param("type")Integer type,
										 @Param("pageNo")Integer pageNo,
										 @Param("pageSize")Integer pageSize
								)throws Exception;
	
	//查询轮胎站点列表
	public List<Gasstation> getGassTlist(@Param("type")Integer type,
										 @Param("pageNo")Integer pageNo,
										 @Param("pageSize")Integer pageSize
								)throws Exception;
	
	//查询商户轮胎收支记录
	public List<Tyredatails> getStyrelist(@Param("id")Integer id)throws Exception;
	
	//查询轮胎商品信息
	public Shop getTyreinfo(@Param("id")Integer id)throws Exception;
	
	//查询会员信息
	public Vipuserinfo getVipinfo(@Param("vid")Integer vid)throws Exception;
	
	//查询站点信息
	public Gasstation getGassinfo(@Param("gid")Integer gid)throws Exception;
	
	//修改密码
	public int updatePwd(@Param("password")String password ,@Param("mobile")String mobile)throws Exception;
	
	//通过手机号查询是否有该用户
	public Vipuserinfo selectVip(@Param("mobile")String mobile)throws Exception;
	
	//消息插入
	public int insertinfo(@Param("msgType")Integer msgType,
				 			  @Param("content")String content,
				 			  @Param("operationTime")String operationTime,
				 			  @Param("vipId")Integer vipId,
				 			  @Param("gsId")Integer gsId
				)throws Exception;
	//通过站点Id查询 站点二维码
	public Gasstation getQrCodeImg(@Param("gid")Integer gid)throws Exception;
	//商户修改密码
	public int shupdatePwd(@Param("password")String password ,@Param("mobile")String mobile)throws Exception;
	//通过手机号查询是否有该商户
	public Gasstation selectGass(@Param("mobile")String mobile)throws Exception;
	//查询加油量与服务费
	public List<Oilrecord> getfee(@Param("gid")Integer gid)throws Exception;
	//插入服务费总量
	public Map<String,Object> feeAdd(String idc,Integer id,Double fee) throws Exception;
}

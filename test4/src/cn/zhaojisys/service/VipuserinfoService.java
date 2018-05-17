package cn.zhaojisys.service;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Param;

import cn.zhaojisys.pojo.Gasstation;
import cn.zhaojisys.pojo.Gathering;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.Tyredatails;
import cn.zhaojisys.pojo.Vipuserinfo;

/**
 * 会员用户业务层接口
 * @author user
 */
public interface VipuserinfoService {	
		//按条件查询用户信息
		public List<Vipuserinfo> selectVipbyCondition(@Param("vipName")String vipName,
												   	  @Param("userName")String userName,
												   	  @Param("busNum")String busNum,
												      @Param("userType")Integer userType,
												      @Param("mycarId")String mycarId,
												      @Param("pageNo")Integer pageNo,
													  @Param("pageSize")Integer pageSize)throws Exception;
		//根据id查询用户所有信息
		public Vipuserinfo selectAllbyId(@Param("id")Integer id)throws Exception;
			
		//为分页查询总计路数
		public int selectCount(@Param("vipName")String vipName,
				   			   @Param("userName")String userName,
				   			   @Param("busNum")String busNum,
				   			   @Param("userType")Integer userType,
				   			   @Param("mycarId")String mycarId)throws Exception;
		
		//查询燃油收支明细
		public List<Oilrecord> getOilrecord(@Param("paymentType")Integer paymentType,
				  							  @Param("operationType")Integer operationType,
				  							  @Param("startTime")String startTime,
				  							  @Param("endTime")String endTime,
				  							  @Param("pageNo")Integer pageNo,
											  @Param("pageSize")Integer pageSize,
				  							  @Param("id")Integer id)throws Exception;
		
		//查询站点名称
		public Oilrecord getName(@Param("id")Integer id)throws Exception;
		
		
		//查询燃油收支明细总计路数
		public	int getoilCount(@Param("paymentType")Integer paymentType,
				  				@Param("operationType")Integer operationType,
				  				@Param("startTime")String startTime,
				  				@Param("endTime")String endTime,
				  				@Param("id")Integer id
				  				)throws Exception;
		
		//查询轮胎收支明细
		public List<Tyredatails> getTyrerelist(@Param("paymentType")Integer paymentType,
				  							 @Param("startTime")String startTime,
				  							 @Param("endTime")String endTime,
				  							 @Param("id")Integer id,
				  							 @Param("pageNo")Integer pageNo,
				  							 @Param("pageSize")Integer pageSize
				  							 )throws Exception;
		
		//查询轮胎收支明细总计路数
		public	int getTyreCount(@Param("paymentType")Integer paymentType,
								 @Param("startTime")String startTime,
								 @Param("endTime")String endTime,
								 @Param("id")Integer id
								 )throws Exception;
		
		//查询收款记录明细
		 public List<Gathering> getCollist(@Param("id")Integer id,
										   @Param("vipName")String vipName,
										   @Param("startTime")String startTime,
										   @Param("endTime")String endTime,
										   @Param("pageNo")Integer pageNo,
				  						   @Param("pageSize")Integer pageSize)throws Exception;
		
		//查询收款记录明细总记录数
		public int getcollCount(@Param("id")Integer id,
								@Param("vipName")String vipName,
				   				@Param("startTime")String startTime,
				   				@Param("endTime")String endTime)throws Exception;
			
		//根据id修改会员信息
		public int updataUser(@Param("id")Integer id,
				  @Param("stateTag")Integer stateTag,	
				  @Param("oilMass")Integer oilMass,
				  @Param("tyreBalance")Integer tyreBalance,
				  @Param("password")String password)throws Exception;
		
		//根据id删除用户
		public int deleteUser(@Param("id")Integer id)throws Exception;
		
		//根据id更新密码
		public int updatepwd(@Param("id")Integer id,@Param("password")String password)throws Exception;
		
		//修改分类
		public int changetype(@Param("id")Integer id,@Param("userType")Integer userType,
							  @Param("remark")String remark,Integer logicId,String mycarId)throws Exception;
		
		//插入收款记录
		public int skRecord1(@Param("vipId")Integer vipId,
							@Param("empId")Integer empId,
							@Param("type")Integer type,
							@Param("Payamount")Integer Payamount,
							@Param("remark")String remark,
							@Param("createTime")String createTime,
							@Param("ljqk")Integer ljqk
							)throws Exception;	
		
		//保存燃油记录
		public int oilinsert(@Param("id")Integer id,
							@Param("operationTime")String operationTime,
							@Param("moneyname")Integer moneyname,
							@Param("oilname")Integer oilname,
							@Param("options")Integer options,
							@Param("type")String type	
							)throws Exception;
		
		//保存轮胎记录
		public int tyerinsert(@Param("id")Integer id,
				@Param("operationData")String operationData,
				@Param("moneyname")Integer moneyname,
				@Param("balance")Integer balance,
				@Param("options")Integer options,
				@Param("type")String type	
				)throws Exception;
		
		//更新欠款金额
		public int qkupdate(@Param("qkBalance")Integer qkBalance,
							@Param("id")Integer id
							)throws Exception;
		
		//收款交易
		public Map<String, String> getGathering(Integer id,Integer oiltype,Integer Payamount,String remark,HttpSession session)throws Exception;
		
		//充值交易
		public Map<String, String> getRecharge(Integer id,Integer moneyname,Integer oilname,Integer options,Integer optionsRad,String remark,HttpSession session)throws Exception;
		public List<Vipuserinfo> getVipuserinfoByName(String name) throws Exception;
		public boolean getFenPei(HttpServletRequest request,String vipname1,String vipname2,Integer sheng) throws Exception;
		public List<Gasstation> getGasstationByName(String name) throws Exception;
		public boolean getJiaYou(HttpServletRequest request,String vipname1,String vipname2,Integer sheng) throws Exception;
		//根据会员状态找物流公司会员
		public List<Vipuserinfo> getNewVipByUserType(String name) throws Exception;
		//根据会员的所属物流id查找该物流公司
		public Vipuserinfo getVipuserinfoBylogicId(Integer logicId) throws Exception;
		//添加会员
		public int addVip(Vipuserinfo vipuserinfo) throws Exception;
		//修改会员
		public int updateVip(Vipuserinfo vipuserinfo) throws Exception;
}
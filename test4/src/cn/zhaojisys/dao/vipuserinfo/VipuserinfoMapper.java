package cn.zhaojisys.dao.vipuserinfo;
/**
 * 会员用户数据层接口
 */
import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.zhaojisys.pojo.Gasstation;
import cn.zhaojisys.pojo.Gathering;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.Tyredatails;
import cn.zhaojisys.pojo.Vipuserinfo;

public interface VipuserinfoMapper {
		//按条件查询用户信息
	public List<Vipuserinfo> getAllbyCondition(@Param("vipName")String vipName,
											   @Param("userName")String userName,
											   @Param("busNum")String busNum,
											   @Param("userType")Integer userType,
											   @Param("mycarId")String mycarId,
											   @Param("pageNo")Integer pageNo,
											   @Param("pageSize")Integer pageSize)throws SQLException;
	//根据id查询用户所有信息
	public Vipuserinfo getAllbyId(@Param("id")Integer id)throws SQLException;
	
	//查询vip用户总计路数
	public int getAllcount(@Param("vipName")String vipName,
			   			   @Param("userName")String userName,
			   			   @Param("busNum")String busNum,
			   			   @Param("userType")Integer userType,
			   			   @Param("mycarId")String mycarId
			   			   )throws SQLException;
	
	//查询燃油收支明细
	public List<Oilrecord> getOilrelist(@Param("paymentType")Integer paymentType,
										  @Param("operationType")Integer operationType,
										  @Param("startTime")String startTime,
										  @Param("endTime")String endTime,
										  @Param("id")Integer id,
										  @Param("pageNo")Integer pageNo,
										  @Param("pageSize")Integer pageSize)throws SQLException;
	//查询站点名称
	public Oilrecord getName(@Param("id")Integer id)throws SQLException;
	
	//查询燃油收支明细总计路数
	public	int getoilCount(@Param("paymentType")Integer paymentType,
			  				@Param("operationType")Integer operationType,
			  				@Param("startTime")String startTime,
			  				@Param("endTime")String endTime,
			  				@Param("id")Integer id
			  				)throws SQLException;
	
	//查询轮胎收支明细
	public List<Tyredatails> getTyrerelist(@Param("paymentType")Integer paymentType,
			  							 @Param("startTime")String startTime,
			  							 @Param("endTime")String endTime,
			  							 @Param("id")Integer id,
			  							 @Param("pageNo")Integer pageNo,
			  							 @Param("pageSize")Integer pageSize
			  							 )throws SQLException;
	
	//查询轮胎收支明细总计路数
	public	int getTyreCount(@Param("paymentType")Integer paymentType,
							 @Param("startTime")String startTime,
							 @Param("endTime")String endTime,
							 @Param("id")Integer id
							 )throws SQLException;
	
	//查询收款记录明细
	 public List<Gathering> getCollist(@Param("id")Integer id,
									   @Param("vipName")String vipName,
									   @Param("startTime")String startTime,
									   @Param("endTime")String endTime,
									   @Param("pageNo")Integer pageNo,
			  						   @Param("pageSize")Integer pageSize)throws SQLException;
		
	//查询收款记录明细总记录数
	public int getcollCount(@Param("id")Integer id,
							@Param("vipName")String vipName,
			   				@Param("startTime")String startTime,
			   				@Param("endTime")String endTime)throws SQLException;
	
	//根据会员id修改数据
	public int updataUser(@Param("id")Integer id,
						  @Param("stateTag")Integer stateTag,	
						  @Param("oilMass")Integer oilMass,
						  @Param("tyreBalance")Integer tyreBalance,
						  @Param("password")String password
			)throws SQLException;
	
	//根据id删除用户
	public int deleteUser(@Param("id")Integer id)throws SQLException;
	
	//根据id更新密码
	public int updatepwd(@Param("id")Integer id,@Param("password")String password)throws SQLException;
	
	//修改分类
	public int changetype(@Param("id")Integer id,
						  @Param("userType")Integer userType,
						  @Param("remark")String remark,
						  @Param("logicId")Integer logicId,
						  @Param("mycarId")String mycarId)throws SQLException;
	
	//插入收款记录
	public int skRecord(@Param("vipId")Integer vipId,
						@Param("empId")Integer empId,
						@Param("type")Integer type,
						@Param("moneyname")Integer moneyname,
						@Param("remark")String remark,
						@Param("createTime")String createTime,
						@Param("ljqk")Integer ljqk,
						@Param("oil")Integer oil
						)throws SQLException;
	//更新欠款金额
	public int qkupdate(@Param("qkBalance")Integer qkBalance,
						@Param("id")Integer id       
						)throws SQLException;
	
	//会员充值
	public int czupdate(@Param("id")Integer id,
						@Param("options")Integer options,
						@Param("optionsRad")Integer optionsRad,
						@Param("moneyname")Integer moneyname,
						@Param("oilname")Integer oilname,
						@Param("remark")String remark,
						@Param("tyreBalance")Integer tyreBalance
			)throws SQLException;
	
	//保存燃油记录
	public int oilinsert(@Param("id")Integer id,
						@Param("operationTime")String operationTime,
						@Param("moneyname")Integer moneyname,
						@Param("oilname")Integer oilname,
						@Param("options")Integer options
						)throws SQLException;
	
	//保存轮胎记录
	public int tyerinsert(@Param("id")Integer id,
			@Param("operationData")String operationData,
			@Param("moneyname")Integer moneyname,
			@Param("balance")Integer balance,
			@Param("options")Integer options,
			@Param("type")String type	
			)throws SQLException;

		//通过帐号模糊查询找Vipuserinfo
		public List<Vipuserinfo> getVipuserinfoByName(@Param(value="name")String name) throws SQLException;
		//通过帐号名查询是否有记录
		public int getVipuserinfoByNameCount(@Param(value="name")String name) throws SQLException;
		//通过帐号名找出Vipuserinfo
		public Vipuserinfo getNewVipuserinfoByName(@Param(value="name")String name) throws SQLException;
		//收油会员添加油量
		public int updateVipuserinfoOilMassJia(@Param(value="id")Integer id,@Param(value="leave")Integer leave) throws SQLException;
		//分配会员支出油量
		public int updateVipuserinfoOilMassJian(@Param(value="id")Integer id,@Param(value="leave")Integer leave) throws SQLException;
		//收油站点添加油量
		public int updateGasstationQuota(@Param(value="id")Integer id,@Param(value="leave")Integer leave) throws SQLException;
		//通过站点名模糊查询找Gasstation
		public List<Gasstation> getGasstationByName(@Param(value="name")String name) throws SQLException;
		//通过帐号找唯一站点是否有
		public Gasstation getNewGasstationByName(@Param(value="name")String name) throws SQLException;
		//根据会员状态找物流公司会员
		public List<Vipuserinfo> getNewVipByUserType(@Param(value="name")String name) throws Exception;
		//根据会员的所属物流id查找该物流公司
		public Vipuserinfo getVipuserinfoBylogicId(Integer logicId) throws Exception;
		//添加会员
		public int addVip(Vipuserinfo vipuserinfo) throws Exception;
		//修改会员
		public int updateVip(Vipuserinfo vipuserinfo) throws SQLException;
}

package cn.zhaojisys.dao.gasstation;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zhaojisys.pojo.ExtractApply;
import cn.zhaojisys.pojo.Gasstation;
import cn.zhaojisys.pojo.Messages;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.Paramatersetting;

public interface GasstationMapper {
	//根据条件查询加油站or轮胎
	public List<Gasstation> getGasstationListByCondition(
			@Param(value="siteName")String querySiteName,
			@Param(value="contact")String queryContact,
			@Param(value="gsType")Integer queryGsType,
			@Param(value="status")Integer queryStatus,
			@Param(value="from")Integer currentPageNo,
			@Param(value="pageSize")Integer pageSize)throws Exception;
	//根据条件查询加油站or轮胎所有记录数
	public int getGasstationCount(@Param(value="siteName")String querySiteName,
			@Param(value="contact")String queryContact,
			@Param(value="gsType")Integer queryGsType,
			@Param(value="status")Integer queryStatus)throws Exception;
	//根据当前Id逻辑删站点
	public int deleteById(@Param(value="id")Integer id) throws Exception;
	//根据当前Id改变账户状态
	public int updateStatusById(@Param(value="id")Integer id,
			@Param(value="status")Integer queryStatus)throws Exception;
	//根据Id找单个站点信息(为了修改做准备)
	public Gasstation getGasstationById(@Param(value="id")Integer id) throws Exception;
	//修改
	public int modifyGasstation(Gasstation gasstation) throws Exception;
	//添加
	public int addGasstation(Gasstation gasstation) throws Exception;
	//根据多条件查询而确定单个站点
	public Gasstation getGasstationByMoney(@Param(value="creatData")Date creatData,
			@Param(value="siteName")String siteName) throws Exception;
	//添加成功后更具多条件插入二维码
	public int updateGasstationQRcode(@Param(value="id")Integer id,
			@Param(value="qrcode")String qrcode) throws Exception;
	//按帐号查数据有则重复
	public int getGasstationCode(@Param(value="code")String code) throws Exception;
	//从参数查所有信息
	public Paramatersetting getParamatersettingList() throws Exception;
	//查看是否禁用中
	public int getStatusById(@Param(value="id")Integer id) throws Exception;
	//查看是否审批中
	public int getExtractApplyStatusById(@Param(value="id")Integer id) throws Exception;
	//没有审批中的则添加
	public int addExtractApply(ExtractApply extractApply) throws Exception;
	//添加燃油收支记录
	public int addOilrecord(Oilrecord oilrecord) throws Exception;
	//添加消息推送
	public int addMessages(Messages messages) throws Exception;
	//审批成功后改相关id站点的可提取
	public int updateGasstationQuota(@Param(value="id")Integer id,
			@Param(value="quota")Integer quota) throws Exception;
}

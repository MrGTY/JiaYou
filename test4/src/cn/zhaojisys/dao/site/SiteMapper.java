package cn.zhaojisys.dao.site;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.zhaojisys.pojo.ExtractApply;
import cn.zhaojisys.pojo.Feetable;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.Tyredatails;

public interface SiteMapper {
	//根据条件查询审批
	public List<ExtractApply>  getExtractApplyByCondition(
			@Param(value="status")Integer queryStatus,
			@Param(value="begintime")Date begintime,
			@Param(value="endtime")Date endtime,
			@Param(value="index")Integer index,
			@Param(value="from")Integer currentPageNo,
			@Param(value="pageSize")Integer pageSize) throws Exception;
	//根据条件查询审批记录数
	public int getExtractApplyCount(
			@Param(value="begintime")Date begintime,
			@Param(value="endtime")Date endtime,
			@Param(value="status")Integer queryStatus,
			@Param(value="index")Integer index)throws Exception;
	//根据条件查询站点的收支记录
	public List<Oilrecord> getOilrecordByCondition(
			@Param(value="gsId")Integer gsId,
			@Param(value="status")Integer queryStatus,
			@Param(value="begintime")Date begintime,
			@Param(value="endtime")Date endtime,
			@Param(value="from")Integer currentPageNo,
			@Param(value="pageSize")Integer pageSize) throws Exception;
	//根据条件查询站点的收支记录总数
	public int getOilrecordByCount(
			@Param(value="gsId")Integer gsId,
			@Param(value="status")Integer queryStatus,
			@Param(value="begintime")Date begintime,
			@Param(value="endtime")Date endtime) throws Exception;
	//根据条件查询站点的收支记录(tai)
	public List<Tyredatails> getTyredatailsByCondition(
			@Param(value="gsId")Integer gsId,
			@Param(value="status")Integer queryStatus,
			@Param(value="begintime")Date begintime,
			@Param(value="endtime")Date endtime,
			@Param(value="from")Integer currentPageNo,
			@Param(value="pageSize")Integer pageSize) throws Exception;
	//根据条件查询站点的收支记录总数(tai)
	public int getTyredatailsByCount(
			@Param(value="gsId")Integer gsId,
			@Param(value="status")Integer queryStatus,
			@Param(value="begintime")Date begintime,
			@Param(value="endtime")Date endtime) throws Exception;
	//按时间查询站点的服务费交易
	public List<Feetable> getFeetable(
			@Param(value="gassid")Integer gassid,
			@Param(value="begintime")Date begintime,
			@Param(value="endtime")Date endtime,
			@Param(value="from")Integer currentPageNo,
			@Param(value="pageSize")Integer pageSize) throws Exception;
	//按时间查询服务费的总数
	public int getFeetableCount(
			@Param(value="gassid")Integer gassid,
			@Param(value="begintime")Date begintime,
			@Param(value="endtime")Date endtime) throws Exception;
	//算还未结算清楚的服务费
	public Double getFeeAll(@Param(value="gassid")Integer gassid) throws Exception;
	//按时间查服务费明细
	public List<Oilrecord> getOilrecordByFee(Map<String,Object> conmap) throws Exception;
	//根据条件查询站点的收支记录总数
	public int getOilrecordCountByFee(Map<String,Object> conmap) throws Exception;
	//查询交易累计的总提取量
	public int getOilAll(@Param(value="gassid")Integer gassid) throws Exception;
	
}

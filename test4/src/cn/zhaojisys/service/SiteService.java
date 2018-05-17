package cn.zhaojisys.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.zhaojisys.pojo.ExtractApply;
import cn.zhaojisys.pojo.Feetable;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.Tyredatails;

public interface SiteService {
	//根据条件查询审批
	public List<ExtractApply>  getExtractApplyByCondition(Integer queryStatus,
			Date begintime,Date endtime,Integer index,Integer currentPageNo,Integer pageSize) throws Exception;
	//根据条件查询审批记录数
	public int getExtractApplyCount(Integer queryStatus,
			Date begintime,Date endtime,Integer index) throws Exception;
	//根据条件查询站点的收支记录
	public List<Oilrecord> getOilrecordByCondition(Integer gsId,Integer queryStatus,Date begintime,
			Date endtime,Integer currentPageNo,Integer pageSize) throws Exception;
	//根据条件查询站点的收支记录总数
	public int getOilrecordByCount(Integer gsId,Integer queryStatus,Date begintime,Date endtime) throws Exception;
	//根据条件查询站点的收支记录
	public List<Tyredatails> getTyredatailsByCondition(Integer gsId,Integer queryStatus,Date begintime,
			Date endtime,Integer currentPageNo,Integer pageSize) throws Exception;
	//根据条件查询站点的收支记录总数
	public int getTyredatailsByCount(Integer gsId,Integer queryStatus,Date begintime,Date endtime) throws Exception;
	//按时间查询站点的服务费交易
	public List<Feetable> getFeetable(Integer gassid,Date begintime,
			Date endtime,Integer currentPageNo,Integer pageSize) throws Exception;
	//按时间查询服务费的总数
	public int getFeetableCount(Integer gassid,Date begintime,
			Date endtime) throws Exception;
	//算还未结算清楚的服务费
	public Double getFeeAll(Integer gassid) throws Exception;
	//按时间查服务费明细
	public List<Oilrecord> getOilrecordByFee(Map<String,Object> conmap) throws Exception;
	//根据条件查询站点的收支记录总数
	public int getOilrecordCountByFee(Map<String,Object> conmap) throws Exception;
	//查询交易累计的总提取量
	public int getOilAll(Integer gassid) throws Exception;
}

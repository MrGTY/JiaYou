package cn.zhaojisys.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zhaojisys.dao.site.SiteMapper;
import cn.zhaojisys.pojo.ExtractApply;
import cn.zhaojisys.pojo.Feetable;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.Tyredatails;
import cn.zhaojisys.service.SiteService;

@Service
public class SiteServiceImpl implements SiteService{
	@Resource
	private SiteMapper sitemapper;
	public List<ExtractApply> getExtractApplyByCondition(Integer queryStatus,
			Date begintime, Date endtime,Integer index,Integer currentPageNo,
			Integer pageSize) throws Exception {
		return sitemapper.getExtractApplyByCondition(queryStatus, begintime, endtime,index,(currentPageNo-1)*pageSize,pageSize);
	}
	public int getExtractApplyCount(Integer queryStatus, Date begintime,
			Date endtime,Integer index) throws Exception {
		return sitemapper.getExtractApplyCount(begintime, endtime,queryStatus,index);
	}
	@Override
	public List<Oilrecord> getOilrecordByCondition(Integer gsId,
			Integer queryStatus, Date begintime, Date endtime,
			Integer currentPageNo, Integer pageSize) throws Exception {
		return sitemapper.getOilrecordByCondition(gsId, queryStatus, begintime, endtime,(currentPageNo-1)*pageSize,pageSize);
	}
	@Override
	public int getOilrecordByCount(Integer gsId, Integer queryStatus,
			Date begintime, Date endtime) throws Exception {
		return sitemapper.getOilrecordByCount(gsId, queryStatus, begintime, endtime);
	}
	@Override
	public List<Feetable> getFeetable(Integer gassid, Date begintime,
			Date endtime, Integer currentPageNo, Integer pageSize)
			throws Exception {
		return sitemapper.getFeetable(gassid, begintime, endtime,(currentPageNo-1)*pageSize, pageSize);
	}
	@Override
	public int getFeetableCount(Integer gassid, Date begintime, Date endtime)
			throws Exception {
		return sitemapper.getFeetableCount(gassid, begintime, endtime);
	}
	@Override
	public Double getFeeAll(Integer gassid) throws Exception {
		return sitemapper.getFeeAll(gassid);
	}
	@Override
	public List<Oilrecord> getOilrecordByFee(Map<String,Object> conmap) throws Exception {
		return sitemapper.getOilrecordByFee(conmap);
	}
	@Override
	public int getOilrecordCountByFee(Map<String,Object> conmap) throws Exception{
		return sitemapper.getOilrecordCountByFee(conmap);
	}
	@Override
	public int getOilAll(Integer gassid) throws Exception {
		return sitemapper.getOilAll(gassid);
	}
	@Override
	public List<Tyredatails> getTyredatailsByCondition(Integer gsId,
			Integer queryStatus, Date begintime, Date endtime,
			Integer currentPageNo, Integer pageSize) throws Exception {
		return sitemapper.getTyredatailsByCondition(gsId, queryStatus, begintime,endtime,(currentPageNo-1)*pageSize,pageSize);
	}
	@Override
	public int getTyredatailsByCount(Integer gsId, Integer queryStatus,
			Date begintime, Date endtime) throws Exception {
		return sitemapper.getTyredatailsByCount(gsId, queryStatus, begintime, endtime);
	}
}

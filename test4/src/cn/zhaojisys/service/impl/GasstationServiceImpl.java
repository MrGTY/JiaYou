package cn.zhaojisys.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zhaojisys.dao.gasstation.GasstationMapper;
import cn.zhaojisys.pojo.ExtractApply;
import cn.zhaojisys.pojo.Gasstation;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.Paramatersetting;
import cn.zhaojisys.service.GasstationService;

@Service
public class GasstationServiceImpl implements GasstationService{
	@Resource
	private GasstationMapper gasstationMapper;
	public List<Gasstation> getGasstationListByCondition(
			String querySiteName,
			String queryContact,
			Integer queryGsType,
			Integer queryStatus,
			Integer currentPageNo,
			Integer pageSize)throws Exception{
		return gasstationMapper.getGasstationListByCondition(querySiteName, queryContact,
				queryGsType, queryStatus,(currentPageNo-1)*pageSize, pageSize);
	}
	public int getGasstationCount(String querySiteName, String queryContact,
			Integer queryGsType, Integer queryStatus) throws Exception {
		return gasstationMapper.getGasstationCount(querySiteName, queryContact, queryGsType, queryStatus);
	}
	@Override
	public boolean updateByLogicId(Integer id) throws Exception {
		int i=gasstationMapper.deleteById(id);
		if(i>0){
			return true;
		}
		return false;
	}
	@Override
	public boolean updateStatusById(Integer id, Integer queryStatus)
			throws Exception {
		int i=gasstationMapper.updateStatusById(id, queryStatus);
		if(i>0){
			return true;
		}
		return false;
	}
	@Override
	public Gasstation getGasstationById(Integer id) throws Exception {
		return gasstationMapper.getGasstationById(id);
	}
	@Override
	public boolean modifyGasstation(Gasstation gasstation) throws Exception {
		if(gasstationMapper.modifyGasstation(gasstation)>0){
			return true;
		}
		return false;
	}
	@Override
	public boolean addGasstation(Gasstation gasstation) throws Exception {
		if(gasstationMapper.addGasstation(gasstation)>0){
			return true;
		}
		return false;
	}
	@Override
	public Paramatersetting getParamatersettingList() throws Exception {
		return gasstationMapper.getParamatersettingList();
	}
	@Override
	public boolean addExtractApply(ExtractApply extractApply) throws Exception {
		if(gasstationMapper.addExtractApply(extractApply)>0){
			return true;
		}
		return false;
	}
	@Override
	public boolean getStatusById(Integer id) throws Exception {
		int i=gasstationMapper.getStatusById(id);
		if(i>0){
			return true;
		}
		return false;
	}
	@Override
	public boolean getExtractApplyStatusById(Integer id) throws Exception {
		int i=gasstationMapper.getExtractApplyStatusById(id);
		if(i>0){
			return true;
		}
		return false;
	}
	@Override
	public boolean getGasstationCode(String code)
			throws Exception {
		if(gasstationMapper.getGasstationCode(code)>0){
			return true;
		}
		return false;
	}
	@Override
	public boolean updateGasstationQRcode(Integer id, String qrcode)
			throws Exception {
		if(gasstationMapper.updateGasstationQRcode(id, qrcode)>0){
			return true;
		}
		return false;
	}
	@Override
	public Gasstation getGasstationByMoney(Date creatData, String siteName)
			throws Exception {
		return gasstationMapper.getGasstationByMoney(creatData, siteName);
	}
	@Override
	public int addOilrecord(Oilrecord oilrecord) throws Exception {
		return gasstationMapper.addOilrecord(oilrecord);
	}
	@Override
	public int updateGasstationQuota(Integer id, Integer quota)
			throws Exception {
		return gasstationMapper.updateGasstationQuota(id, quota);
	}
}

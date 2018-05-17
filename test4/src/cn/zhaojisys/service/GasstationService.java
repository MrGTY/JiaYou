package cn.zhaojisys.service;

import java.util.Date;
import java.util.List;

import cn.zhaojisys.pojo.ExtractApply;
import cn.zhaojisys.pojo.Gasstation;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.Paramatersetting;

public interface GasstationService {
	public List<Gasstation> getGasstationListByCondition(
			String querySiteName,
			String queryContact,
			Integer queryGsType,
			Integer queryStatus,
			Integer currentPageNo,
			Integer pageSize) throws Exception;
	public int getGasstationCount(String querySiteName,
			String queryContact,
			Integer queryGsType,
			Integer queryStatus) throws Exception;
	public boolean updateByLogicId(Integer id) throws Exception;
	public boolean updateStatusById(Integer id,Integer queryStatus) throws Exception;
	public Gasstation getGasstationById(Integer id) throws Exception;
	public boolean modifyGasstation(Gasstation gasstation) throws Exception;
	public boolean addGasstation(Gasstation gasstation) throws Exception;
	public Paramatersetting getParamatersettingList() throws Exception;
	public boolean addExtractApply(ExtractApply extractApply) throws Exception;
	public boolean getStatusById(Integer id) throws Exception;
	public boolean getExtractApplyStatusById(Integer id) throws Exception;
	public boolean getGasstationCode(String code) throws Exception;
	public boolean updateGasstationQRcode(Integer id,String qrcode) throws Exception;
	public Gasstation getGasstationByMoney(Date creatData,String siteName) throws Exception;
	public int addOilrecord(Oilrecord oilrecord) throws Exception;
	public int updateGasstationQuota(Integer id,Integer quota) throws Exception;
}

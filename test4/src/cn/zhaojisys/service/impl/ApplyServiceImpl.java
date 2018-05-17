package cn.zhaojisys.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;

import cn.zhaojisys.dao.applymapper.ApplyMapper;
import cn.zhaojisys.dao.gasstation.GasstationMapper;
import cn.zhaojisys.pojo.EmployeInfo;
import cn.zhaojisys.pojo.ExtractApply;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.service.ApplyService;
import cn.zhaojisys.tools.Constants;

@Service
public class ApplyServiceImpl implements ApplyService{

	@Autowired
	ApplyMapper aMapper;
	
	@Resource
	private GasstationMapper gasstationMapper;
	
	//ͨ通过Id查询来选择通过还是拒绝
	@Override
	public List<ExtractApply> selectAllApplyById(String id) throws Exception {
		return aMapper.selectAllApplyById(id);
	}
	
	//查询所有申请信息并分页
	@Override
	public List<ExtractApply> selectAllApply(Integer pageNo, Integer pageSize,
			String siteName, Integer businessType, Integer status)
			throws Exception {
		return aMapper.selectAllApply(pageNo, pageSize, siteName, businessType, status);
	}
	
	//总数
	@Override
	public int count(String siteName,Integer businessType,Integer status) throws Exception {
		return aMapper.count(siteName, businessType, status);
	}
	
	//审批
	@Override
	public int updateApply(String emplId, String serialNum, Date approveData,
			String status,Integer id) throws Exception {
		return aMapper.updateApply(emplId, serialNum, approveData,status,id);
	}

	@Override
	public ExtractApply selectExtractApplyById(Integer id) throws Exception {
		return aMapper.selectExtractApplyById(id);
	}
	// 通过或拒绝申请

	@Override
	public Object getResult(String serialNum, String type, Integer id,
			HttpSession session) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		ExtractApply o=null;
		int sesscs = 0;
		Date date=new Date();
		Date approveData=date;
		String emplId=((EmployeInfo)session.getAttribute(Constants.USER_SESSION)).getLoginName();
		//Integer emplId=1;
				if(type.equals("通过")){
					sesscs=aMapper.updateApply(emplId,serialNum,approveData,"1",id);
					if(sesscs > 0){
						o=aMapper.selectExtractApplyById(id);
						//System.out.println("-----------------------"+o.getGsId()+"--"+o.getSurplusBalance());
						gasstationMapper.updateGasstationQuota(o.getGsId(), o.getSurplusBalance());
						map.put("mas","success");
					}else{
						map.put("mas","failure");
					}
				}else if(type.equals("拒绝")){
					sesscs=aMapper.updateApply(emplId,serialNum,approveData,"2",id);
					if(sesscs > 0){
						map.put("mas","success");
					}else{
						map.put("mas","failure");
					}
				}
		return JSONArray.toJSONString(map);
	}
}

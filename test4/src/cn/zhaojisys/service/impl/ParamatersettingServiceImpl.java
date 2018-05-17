package cn.zhaojisys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zhaojisys.dao.paramatersetting.ParamatersettingMapper;
import cn.zhaojisys.pojo.Customer;
import cn.zhaojisys.pojo.HeadLine;
import cn.zhaojisys.pojo.Paramatersetting;
import cn.zhaojisys.service.ParamatersettingService;

@Service
public class ParamatersettingServiceImpl implements ParamatersettingService {
	@Resource
	private ParamatersettingMapper paramatersettingMapper;

	// 查询参数信息
	@Override
	public List<Paramatersetting> getParamatersettingList() throws Exception {
		// TODO Auto-generated method stub
		return paramatersettingMapper.getParamatersettingList();
	}

	// 更新参数信息
	@Override
	public int updateListById(Paramatersetting paramatersetting)
			throws Exception {
		// TODO Auto-generated method stub
		return paramatersettingMapper.updateListById(paramatersetting);
	}

	@Override
	public boolean addLine(HeadLine headLine) throws Exception {
		if(paramatersettingMapper.addLine(headLine)>0)
			return true;
		return false;
	}

	@Override
	public HeadLine chaLine() throws Exception {
		return paramatersettingMapper.chaLine();
	}

	@Override
	public List<Customer> getCustomer(String name, Integer currentPageNo,
			Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		return paramatersettingMapper.getCustomer(name, (currentPageNo-1)*pageSize, pageSize);
	}

	@Override
	public int getCustomerCount(String name) throws Exception {
		// TODO Auto-generated method stub
		return paramatersettingMapper.getCustomerCount(name);
	}

}

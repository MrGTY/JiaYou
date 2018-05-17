package cn.zhaojisys.service;

import java.util.List;

import cn.zhaojisys.pojo.Customer;
import cn.zhaojisys.pojo.HeadLine;
import cn.zhaojisys.pojo.Paramatersetting;

public interface ParamatersettingService {
	// 查询参数信息
	public List<Paramatersetting> getParamatersettingList() throws Exception;

	// 更新参数信息
	public int updateListById(Paramatersetting paramatersetting)
			throws Exception;
	public boolean addLine(HeadLine headLine) throws Exception;
	public HeadLine chaLine() throws Exception;
	public List<Customer> getCustomer(String name,Integer currentPageNo,Integer pageSize) throws Exception;
	public int getCustomerCount(String name) throws Exception;
}

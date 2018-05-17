package cn.zhaojisys.dao.paramatersetting;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zhaojisys.pojo.Customer;
import cn.zhaojisys.pojo.HeadLine;
import cn.zhaojisys.pojo.Paramatersetting;

public interface ParamatersettingMapper {
	// 查询参数信息
	public List<Paramatersetting> getParamatersettingList() throws Exception;

	// 更新参数信息
	public int updateListById(Paramatersetting paramatersetting)
			throws Exception;
	
	public int addLine(HeadLine headLine) throws Exception;
	public HeadLine chaLine() throws Exception;
	public List<Customer> getCustomer(@Param(value="name")String name,
			@Param(value="from")Integer currentPageNo,
			@Param(value="pageSize")Integer pageSize) throws Exception;
	public int getCustomerCount(@Param(value="name")String name) throws Exception;
}

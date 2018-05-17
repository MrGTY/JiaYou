package cn.zhaojisys.dao.app;

import cn.zhaojisys.pojo.Customer;

public interface CustomerMapper {

	/**
	 * 添加客户
	 * @param customer
	 * @return
	 * @throws Exception
	 */
	public int addCtm(Customer customer)throws Exception;
}

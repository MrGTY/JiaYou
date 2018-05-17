package cn.zhaojisys.service.app;

import cn.zhaojisys.pojo.Customer;

public interface CustomerService {

	/**
	 * 添加客户
	 * @param customer
	 * @return
	 * @throws Exception
	 */
	public int addCtm(Customer customer)throws Exception;
}

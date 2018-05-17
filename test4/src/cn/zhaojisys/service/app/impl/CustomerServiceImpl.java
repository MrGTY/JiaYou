package cn.zhaojisys.service.app.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhaojisys.dao.app.CustomerMapper;
import cn.zhaojisys.pojo.Customer;
import cn.zhaojisys.service.app.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerMapper customerMapper;
	@Override
	public int addCtm(Customer customer) throws Exception {
		Date date=new Date();
		System.out.println(date);
		customer.setCreateTime(date);
		return customerMapper.addCtm(customer);
	}

}

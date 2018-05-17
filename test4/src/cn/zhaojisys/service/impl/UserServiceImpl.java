package cn.zhaojisys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import cn.zhaojisys.dao.user.UserMapper;
import cn.zhaojisys.pojo.EmployeInfo;
import cn.zhaojisys.pojo.Gasstation;
import cn.zhaojisys.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserMapper uMapper;

	@Override
	public Gasstation zdLogin(Gasstation gasstation) {
		// TODO Auto-generated method stub
		return uMapper.zdLogin(gasstation);
	}
	

	@Override
	public EmployeInfo userLogin(EmployeInfo employeInfo) {
		// TODO Auto-generated method stub
		return uMapper.userLogin(employeInfo);
	}



	
}

package cn.zhaojisys.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhaojisys.dao.employe.EmployeMapper;
import cn.zhaojisys.pojo.EmployeInfo;
import cn.zhaojisys.service.EmployeService;

@Service
public class EmployeServiceImpl implements EmployeService {

	@Autowired
	EmployeMapper eMapper;

	// 查询所有员工信息并分页
	@Override
	public List<EmployeInfo> findAllEmploye(int pageNo, int pageSize) {
		return eMapper.findAllEmploye(pageNo, pageSize);
	}

	// 新增员工信息
	@Override
	public boolean addEmploye(EmployeInfo employeInfo) throws Exception {
		if(eMapper.addEmploye(employeInfo)>0){
			return true;
		}
		return false;
	}
	
	//查询员工手机号码是否存在
	@Override
	public EmployeInfo selectPhone(String mobilePhone) throws Exception {
		return eMapper.selectPhone(mobilePhone);
	}
	
	//查询员工登录名是否存在
	@Override
	public EmployeInfo selectName(String loginName) throws Exception {
		return eMapper.selectName(loginName);
	}
	// ͨ通过Id查询员工信息并点击编辑
	@Override
	public List<EmployeInfo> selectEmployeById(String id) throws Exception {
		return eMapper.selectEmployeById(id);
	}

	// 编辑员工信息
	@Override
	public void updateEmploye(EmployeInfo employeInfo) throws Exception {
		eMapper.updateEmploye(employeInfo);
	}

	// 删除员工信息
	@Override
	public void deleteEmploye(@Param("id") String id) throws Exception {
		eMapper.deleteEmploye(id);
		
	}
	// 查询员工信息总条数
	@Override
	public int count() {
		return eMapper.count();
	}

	
}

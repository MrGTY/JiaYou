package cn.zhaojisys.dao.employe;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zhaojisys.pojo.EmployeInfo;

public interface EmployeMapper {
	// 查询所有员工信息并分页
	public List<EmployeInfo> findAllEmploye(@Param("pageNo") int pageNo, @Param("pageSize") int pageSize);

	// 新增员工信息
	public int addEmploye(EmployeInfo employeInfo) throws Exception;

	// 查询员工手机号码是否存在
	public EmployeInfo selectPhone(@Param("mobilePhone") String mobilePhone) throws Exception;

	// 查询员工登录名是否存在
	public EmployeInfo selectName(@Param("loginName") String loginName) throws Exception;

	// 通过Id查询员工信息并点击编辑
	public List<EmployeInfo> selectEmployeById(@Param("id") String id) throws Exception;

	// 编辑员工信息
	public void updateEmploye(EmployeInfo employeInfo) throws Exception;

	// 删除员工信息
	public void deleteEmploye(@Param("id") String id) throws Exception;

	// 查询员工信息总条数
	int count();
}

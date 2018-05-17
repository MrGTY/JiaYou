package cn.zhaojisys.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zhaojisys.pojo.EmployeInfo;
import cn.zhaojisys.pojo.Gasstation;


public interface UserMapper {
	//站点登陆
	Gasstation zdLogin(Gasstation gasstation);
	//管理登陆
	EmployeInfo userLogin(EmployeInfo employeInfo);


}

package cn.zhaojisys.service;




import cn.zhaojisys.pojo.EmployeInfo;
import cn.zhaojisys.pojo.Gasstation;

public interface UserService {
	//站点登陆
	Gasstation zdLogin(Gasstation gasstation);
	//管理登陆
	EmployeInfo userLogin(EmployeInfo employeInfo);

}

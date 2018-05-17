package cn.zhaojisys.service.app.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhaojisys.dao.app.CarList;
import cn.zhaojisys.pojo.Vipuserinfo;
import cn.zhaojisys.service.app.CarListService;
@Service
public class CarListServiceImpl implements CarListService{
	@Autowired CarList cal;
	




	@Override
	public List<Vipuserinfo> carList(Integer logicId, Integer id, Integer pageNo) {
		// TODO Auto-generated method stub
		return cal.carList(logicId, id, pageNo);
	}

}

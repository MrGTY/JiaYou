package cn.zhaojisys.service.app.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhaojisys.dao.app.CarList;
import cn.zhaojisys.dao.app.TyreList;
import cn.zhaojisys.pojo.Tyredatails;
import cn.zhaojisys.pojo.Vipuserinfo;
import cn.zhaojisys.service.app.CarListService;
import cn.zhaojisys.service.app.TyreListService;
@Service
public class TyreListServiceImpl implements TyreListService{
	@Autowired TyreList tyr;

	@Override
	public List<Tyredatails> tyreList(Integer mycarId) {
		// TODO Auto-generated method stub
		return tyr.tyreList(mycarId);
	}
	

	

}

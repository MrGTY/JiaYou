package cn.zhaojisys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.zhaojisys.dao.tyredatails.TyredatailsMapper;
import cn.zhaojisys.pojo.Tyredatails;
import cn.zhaojisys.service.TyredatailsServiceMapper;

@Service
public class TyredatailsServiceMapperImpl implements TyredatailsServiceMapper {

	
	@Autowired
	private  TyredatailsMapper tyredatailsMapper;
	
	@Override
	public List<Tyredatails> selectAll() throws Exception {
		// TODO Auto-generated method stub
		return tyredatailsMapper.selectAll();
	}

}

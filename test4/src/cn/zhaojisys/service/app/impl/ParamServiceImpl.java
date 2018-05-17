package cn.zhaojisys.service.app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhaojisys.dao.app.ParamMapper;
import cn.zhaojisys.pojo.Paramatersetting;
import cn.zhaojisys.service.app.ParamService;

@Service
public class ParamServiceImpl implements ParamService{

	@Autowired
	private ParamMapper paramMapper;
	@Override
	public Paramatersetting param() throws Exception{
		// TODO Auto-generated method stub
		return paramMapper.param();
	}

}

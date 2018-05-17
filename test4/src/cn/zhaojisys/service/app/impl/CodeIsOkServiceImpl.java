package cn.zhaojisys.service.app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhaojisys.dao.app.CodeIsOkMapper;
import cn.zhaojisys.service.app.CodeIsOkService;

@Service
public class CodeIsOkServiceImpl implements CodeIsOkService{

	@Autowired
	private CodeIsOkMapper codeIsOkMapper;
	
	@Override
	public int CodeIsOk(String phoneNum) throws Exception {
		
		return codeIsOkMapper.CodeIsOk(phoneNum);
	}

}

package cn.zhaojisys.service.app.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhaojisys.dao.app.GasstationAppMapper;
import cn.zhaojisys.pojo.Shop;
import cn.zhaojisys.service.app.GasstationAppService;

@Service
public class GasstationAppServiceImpl implements GasstationAppService{

	@Autowired
	private GasstationAppMapper gasstationAppMapper;
	@Override
	public List<Shop> shopList(Integer gId,
			Integer currentPageNo,
			Integer pageSize) throws Exception{
		return gasstationAppMapper.shopList(gId, currentPageNo, pageSize);
	}

}

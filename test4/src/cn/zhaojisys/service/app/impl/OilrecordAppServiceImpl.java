package cn.zhaojisys.service.app.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhaojisys.dao.app.OilrecordAppMapper;
import cn.zhaojisys.pojo.OilModel;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.service.app.OilrecordAppService;
@Service
public class OilrecordAppServiceImpl implements OilrecordAppService{

	@Autowired
	private OilrecordAppMapper oilrecordAppMapper;
	@Override
	public List<OilModel> listOilrecordBy_gsId(Integer id,Integer currentPageNo,
			Integer pageSize) throws Exception{
		return oilrecordAppMapper.listOilrecordBy_gsId(id,currentPageNo,pageSize);
	}
	@Override
	public int countOilrecordBy_gsId(Integer id) throws Exception{
		return oilrecordAppMapper.countOilrecordBy_gsId(id);
	}

}

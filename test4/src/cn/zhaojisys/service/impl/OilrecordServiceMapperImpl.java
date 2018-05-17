package cn.zhaojisys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cn.zhaojisys.dao.oilrecord.OilrecordMapper;
import cn.zhaojisys.pojo.Gasstation;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.service.OilrecordServiceMapper;
@Service
public class OilrecordServiceMapperImpl implements OilrecordServiceMapper {

	@Autowired
	private  OilrecordMapper oilrecordMapper;
	
	@Override
	public List<Oilrecord> selectAll() throws Exception {
		return oilrecordMapper.selectAll();
	}

	@Override
	public Gasstation getGasstationBy_idAndType(String id, String gsType) throws Exception {
		return oilrecordMapper.getGasstationBy_idAndType(id, gsType);
	}

}

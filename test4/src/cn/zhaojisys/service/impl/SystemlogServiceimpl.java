package cn.zhaojisys.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zhaojisys.dao.systemlog.SystemlogMapper;
import cn.zhaojisys.pojo.Systemlog;
import cn.zhaojisys.service.SystemlogService;

/**
 * 系统日志实现类
 * @author user
 *
 */
@Service("SystemlogServiceimpl")
public class SystemlogServiceimpl implements SystemlogService{
	
	@Resource
	SystemlogMapper mapper=null;
	
	@Override
	public List<Systemlog> selectSystemlog(Integer id) {
		List<Systemlog> sysList=new ArrayList<Systemlog>();
		sysList=mapper.getAllsystemlog(id);
		return sysList;
	}

}

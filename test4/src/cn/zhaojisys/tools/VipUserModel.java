package cn.zhaojisys.tools;

import java.util.Comparator;

import cn.zhaojisys.pojo.Tyredatails;
import cn.zhaojisys.pojo.Vipuserinfo;
import cn.zhaojisys.pojo.VipuserinfoModel;

public class VipUserModel implements Comparator<VipuserinfoModel>{

	@Override
	public int compare(VipuserinfoModel o1, VipuserinfoModel o2) {
		return o1.getCreateTime().compareTo(o2.getCreateTime());
	}
	


}
package cn.zhaojisys.tools;

import java.util.Comparator;

import cn.zhaojisys.pojo.Tyredatails;
import cn.zhaojisys.pojo.Vipuserinfo;

public class VipUser implements Comparator<Vipuserinfo>{

	@Override
	public int compare(Vipuserinfo o1, Vipuserinfo o2) {
		return o1.getCreateTime().compareTo(o2.getCreateTime());
	}
	


}
package cn.zhaojisys.tools;

import java.util.Comparator;


import cn.zhaojisys.pojo.Tyredatails;

public class TyreDate implements Comparator<Tyredatails>{

	@Override
	public int compare(Tyredatails o1, Tyredatails o2) {
		return o1.getOperationData().compareTo(o2.getOperationData());
	}
	


}
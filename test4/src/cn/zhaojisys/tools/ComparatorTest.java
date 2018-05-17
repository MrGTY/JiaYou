package cn.zhaojisys.tools;

import java.util.Comparator;

import cn.zhaojisys.pojo.Oilrecord;

public class ComparatorTest implements Comparator<Oilrecord>{

	@Override
	public int compare(Oilrecord o1, Oilrecord o2) {
		return o1.getOperationTime().compareTo(o2.getOperationTime());
	}
	


}

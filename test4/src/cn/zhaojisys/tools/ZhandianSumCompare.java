package cn.zhaojisys.tools;

import java.util.Comparator;

import cn.zhaojisys.pojo.ExtractApply;
import cn.zhaojisys.pojo.Vipuserinfo;

public class ZhandianSumCompare implements Comparator<ExtractApply>{

	@Override
	public int compare(ExtractApply o1, ExtractApply o2) {
		return o1.getApproveData().compareTo(o2.getApproveData());
	}

}

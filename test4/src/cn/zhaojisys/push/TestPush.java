package cn.zhaojisys.push;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;

public class TestPush {
	public static void main(String[] args) throws PushClientException, PushServerException, IOException {

        /**
         * 单条推送*/
         Map<String, Object> resultMap = PushMessageNew.pushMsgToSingleDevice("3814863451180523498", 3600, 1, "代码测试", "测试ing", 2, "www.baidu.com", 4, "","");
         for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
        	   System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        	  }

        /**
         * 批量推送
        String[] channelIds = {"4182697971366550372","3814863451180523498"} ;
        Map<String, Object> resultMap = PushMessageNew.androidPushBatchUniMsg("测试*****", "测试1205", channelIds , 3);
         */

        /**
         * 广播推送
        Map<String, Object> resultMap = PushMessageNew.pushMsgToAll("涌泉金服", "注册有礼", System.currentTimeMillis() / 1000 + 120, 3600, 1, 1, "https://www.baidu.com/", 3, "");
        System.out.println(resultMap);
         */

        /**
         * 消息状态的查看
         */
        String[] strs={"8361003517954776467"};
        List<?> list = PushMessageNew.QueryMsgStatus(strs, 3);
        System.out.println("list===="+list);
    }
}
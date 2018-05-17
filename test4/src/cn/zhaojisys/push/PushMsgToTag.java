package cn.zhaojisys.push;

import java.util.Date;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToTagRequest;
import com.baidu.yun.push.model.PushMsgToTagResponse;

public class PushMsgToTag {
	 /**
	 * @param only 对应唯一设备
	 * @param msgType 0：透传消息 1：通知 默认值为0
	 * @param deployStatus 仅IOS应用推送时使用，默认值为null，取值如下：1：开发状态 2：生产状态
	 * @param message 消息  json格式
	 * @param deviceType 3：Android，4：IOS
	 */
	public static PushMsgToTagResponse pushMsgToTag(String tagName,Integer msgType,Integer deployStatus, String message,Integer deviceType)throws PushClientException,PushServerException{

        String apiKey = "y14OUOumIyVdbcsZEXbT3Qrn";
        String secretKey = "EI3jcjLGiwj2PlHtP9YwBN2PUlfK7Syx";
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);
        pushClient.setChannelLogHandler(new YunLogHandler() {
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });
        Date date = new Date(System.currentTimeMillis());
        System.out.println("==="+date.getTime());
        try {
            PushMsgToTagRequest request = new PushMsgToTagRequest()
                    .addTagName(tagName)
                    .addMsgExpires(new Integer(3600))
                    .addMessageType(msgType)
                    //.addDeployStatus(deployStatus)//仅IOS应用推送时使用，默认值为null，取值如下：1：开发状态 2：生产状态
                    .addSendTime(System.currentTimeMillis() / 1000 + 70)
                    .addMessage(message)
                    .addDeviceType(deviceType);//3：Android，4：IOS
            // 5. http request
            PushMsgToTagResponse response = pushClient.pushMsgToTag(request);
            // Http请求返回值解析
            System.out.println("msgId: " + response.getMsgId() + ",sendTime: "
                    + response.getSendTime() + ",timerId: "
                    + response.getTimerId());
            return response;
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMsg: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
		return null;
	}
	
	
	
	
	public static void main(String[] args) 
            throws PushClientException,PushServerException {
		 // 1. get apiKey and secretKey from developer console
		String apiKey = "y14OUOumIyVdbcsZEXbT3Qrn";
        String secretKey = "EI3jcjLGiwj2PlHtP9YwBN2PUlfK7Syx";
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

        // 2. build a BaidupushClient object to access released interfaces
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        // 3. register a YunLogHandler to get detail interacting information
        // in this request.
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {
            // 4. specify request arguments
            // pushTagTpye = 1 for common tag pushing
            PushMsgToTagRequest request = new PushMsgToTagRequest()
                    .addTagName("15250423297")//15250423297
                    .addMsgExpires(new Integer(3600))
                    .addMessageType(1)
                    //.addSendTime(System.currentTimeMillis() / 1000 + 70)
                    .addMessage("{\"title\":\"测试本地\",\"description\":\"测试内容\"}")
                    .addDeviceType(3);
            // 5. http request
            PushMsgToTagResponse response = pushClient.pushMsgToTag(request);
            // Http请求返回值解析
            System.out.println("msgId: " + response.getMsgId() + ",sendTime: "
                    + response.getSendTime() + ",timerId: "
                    + response.getTimerId());
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMsg: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
    }   
}
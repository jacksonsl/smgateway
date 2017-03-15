package com.ysepay.smgateway.handler;

import org.apache.log4j.Logger;
import org.restexpress.Request;
import org.restexpress.Response;

import com.ysepay.smgateway.domain.ResponseObject;
import com.ysepay.smgateway.domain.YSMsgPO;
import com.ysepay.smgateway.exception.SMGatewayException;
import com.ysepay.smgateway.msgchannel.MsgChannel;
import com.ysepay.smgateway.router.CommonMsgRouter;
import com.ysepay.smgateway.utils.Constants;

/**
 * 发送短信
 * @author sunlei
 * @date 2017年2月27日
 */
public class ShortMsgSendHandler extends HandlerHelper {
	
	private static final Logger LOGGER = Logger.getLogger(ShortMsgSendHandler.class);

	/**
	 * 发送短信
	 * @param request
	 * @param response
	 * @return
	 */
	public Object sendShortMsg(Request request, Response response) {
		LOGGER.info("[ShortMsgSendHandler.sendShortMsg][begin]");
		ResponseObject responseObject = new ResponseObject();
		try {
			// 业务预处理
			YSMsgPO requestObject = preDobusiness(request, Constants.ENCODEING_UTF8);
			
			// 获取短信发送通道
			MsgChannel msgChannel = new CommonMsgRouter().getMsgChannel(requestObject);
			if (null == msgChannel) {
				SMGatewayException.raise("3002");
			}

			// 发送短信
			responseObject = msgChannel.sendMsg();
			
		} catch (SMGatewayException e) {
			responseObject.setCode(e.getCode());
			responseObject.setMsg(e.getMessage());
			LOGGER.error(e, e);
		} catch (Exception e) {
			responseObject.setCode("1001");
			responseObject.setMsg("系统异常，请稍后再试。");
			LOGGER.error(e, e);
		}
		LOGGER.info("[ShortMsgSendHandler.sendShortMsg][end]");
		return responseObject;
	}
	
}

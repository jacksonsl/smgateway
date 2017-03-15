package com.ysepay.smgateway.handler;

import java.security.MessageDigest;

import io.netty.handler.codec.http.HttpMethod;

import org.apache.log4j.Logger;
import org.restexpress.Request;
import org.restexpress.Response;

import sun.misc.BASE64Encoder;

import com.ysepay.smgateway.dao.po.Merchant_appPO;
import com.ysepay.smgateway.domain.OldMsgResponse;
import com.ysepay.smgateway.domain.OldYSMsgPO;
import com.ysepay.smgateway.domain.ResponseObject;
import com.ysepay.smgateway.domain.YSMsgPO;
import com.ysepay.smgateway.exception.SMGatewayException;
import com.ysepay.smgateway.msgchannel.MsgChannel;
import com.ysepay.smgateway.router.CommonMsgRouter;
import com.ysepay.smgateway.service.MerchantAppService;
import com.ysepay.smgateway.utils.Constants;
import com.ysepay.smgateway.utils.MD5Util;
import com.ysepay.smgateway.utils.SpringContextUtil;

/**
 * 兼容公司旧的发送短信网关
 * @author sunlei
 * @date 2017年3月6日
 */
@SuppressWarnings("restriction")
public class OldShortMsgSendHandler extends HandlerHelper {
	
	private static final Logger LOGGER = Logger.getLogger(OldShortMsgSendHandler.class);

	/**
	 * 发送短信
	 * @param request
	 * @param response
	 * @return
	 */
	public Object sendShortMsg(Request request, Response response) {
		LOGGER.info("[OldShortMsgSendHandler.sendShortMsg][begin]");
		ResponseObject responseObject = new ResponseObject();
		try {
			// 业务预处理
			YSMsgPO requestObject = getRequestEntity(request);
			
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
		OldMsgResponse oldMsgResponse = new OldMsgResponse();
		oldMsgResponse.setCode(responseObject.getCode());
		oldMsgResponse.setNote(responseObject.getMsg());
		LOGGER.info("[OldShortMsgSendHandler.sendShortMsg][end]");
		return oldMsgResponse;
	}
	
	private YSMsgPO getRequestEntity(Request request) throws Exception {
		YSMsgPO msgPO = new YSMsgPO();
		OldYSMsgPO oldMsgPO = null;
		if (HttpMethod.POST.equals(request.getHttpMethod())) {
			oldMsgPO = paraseOldPost(request);
		} else {
			oldMsgPO = paraseOldGet(request);
		}
		LOGGER.info("[OldShortMsgSendHandler.getRequestEntity] oldRequestObj=" + gson.toJson(oldMsgPO));
		checkSign(oldMsgPO);
		
		msgPO.setAppId(oldMsgPO.getSrc());
		msgPO.setMobile(oldMsgPO.getMobile());
		msgPO.setMsg(oldMsgPO.getMessage());
		msgPO.setTimestamp(oldMsgPO.getTime());
		msgPO.setMsgSign(oldMsgPO.getMessagesign());
		msgPO.setMsgId(oldMsgPO.getMsgid());

		String sign = MD5Util.MD5Encode(oldMsgPO.getKey() + 
				oldMsgPO.getSrc() + oldMsgPO.getTime());
		msgPO.setSign(sign);
		return msgPO;
	}
	
	private OldYSMsgPO paraseOldPost(Request request) throws Exception {
		paraseRequestBody(request, Constants.ENCODEING_GBK);
		OldYSMsgPO oldMsgPO = new OldYSMsgPO();
		oldMsgPO.setChannel(getParameterByName("channel"));
		oldMsgPO.setCheckValue(getParameterByName("checkValue"));
		oldMsgPO.setMessage(getParameterByName("message"));
		oldMsgPO.setMessagesign(getParameterByName("messagesign"));
		oldMsgPO.setMobile(getParameterByName("mobile"));
		oldMsgPO.setMsgid(getParameterByName("msgid"));
		oldMsgPO.setRepeatCount(getParameterByName("repeatCount"));
		oldMsgPO.setSrc(getParameterByName("src"));
		oldMsgPO.setTime(getParameterByName("time"));
		return oldMsgPO;
	}
	
	private OldYSMsgPO paraseOldGet(Request request) throws Exception {
		OldYSMsgPO oldMsgPO = new OldYSMsgPO();
		oldMsgPO.setChannel(request.getHeader("channel"));
		oldMsgPO.setCheckValue(request.getHeader("checkValue"));
		oldMsgPO.setMessage(request.getHeader("message"));
		oldMsgPO.setMessagesign(request.getHeader("messagesign"));
		oldMsgPO.setMobile(request.getHeader("mobile"));
		oldMsgPO.setMsgid(request.getHeader("msgid"));
		oldMsgPO.setRepeatCount(request.getHeader("repeatCount"));
		oldMsgPO.setSrc(request.getHeader("src"));
		oldMsgPO.setTime(request.getHeader("time"));
		return oldMsgPO;
	}
	
	/**
	 * 校验值
	 * MD5(src + messagesign + msgid +channel + mobile+ message  + time+ repeatCount +key) ；
	 * MD5后需进行base64
	 * @throws Exception
	 */
	private void checkSign(OldYSMsgPO oldMsgPO) throws Exception {
		merchantAppService = (MerchantAppService)SpringContextUtil.getBean("merchantAppService");
		Merchant_appPO merchantApp = merchantAppService.queryMerchantApp(oldMsgPO.getSrc());
		if (null == merchantApp) {
        	SMGatewayException.raise("4004");
		}
		String key  = merchantApp.getApp_key();
		oldMsgPO.setKey(key);
		
		String str3  = oldMsgPO.getSrc() + oldMsgPO.getMessagesign() + oldMsgPO.getMsgid() + 
				oldMsgPO.getChannel() + oldMsgPO.getMobile() + oldMsgPO.getMessage() + 
				oldMsgPO.getTime() + oldMsgPO.getRepeatCount() + key;
		//确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
		BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String basestr1 = base64en.encode(md5.digest(str3.getBytes("GBK")));

		String oldCheckVal = oldMsgPO.getCheckValue().trim().replace(' ', '+');
		LOGGER.info("[OldShortMsgSendHandler.checkSign] checkVal=" + basestr1);
        if (!basestr1.equals(oldCheckVal)) {
        	SMGatewayException.raise("2007");
        }
	}
	
}

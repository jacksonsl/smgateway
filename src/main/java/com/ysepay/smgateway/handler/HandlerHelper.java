package com.ysepay.smgateway.handler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.restexpress.Request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ysepay.smgateway.dao.po.Merchant_appPO;
import com.ysepay.smgateway.domain.YSMsgPO;
import com.ysepay.smgateway.exception.SMGatewayException;
import com.ysepay.smgateway.service.MerchantAppService;
import com.ysepay.smgateway.utils.Constants;
import com.ysepay.smgateway.utils.MD5Util;
import com.ysepay.smgateway.utils.PropertiesUtil;
import com.ysepay.smgateway.utils.SpringContextUtil;

/**
 * 发送短信辅助类
 * @author sunlei
 * @date 2017年2月27日
 */
public class HandlerHelper {
	private static final Logger LOGGER = Logger.getLogger(HandlerHelper.class);
	
	protected static PropertiesUtil properties4Sysconfig = null;
	protected static PropertiesUtil properties4Message = null;
	protected String requestBody;// 请求数据
	protected static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	protected MerchantAppService merchantAppService = null;

	static {
		try {
			if (null == properties4Sysconfig) {
				properties4Sysconfig = new PropertiesUtil("sysconfig.properties");
			}
			if (null == properties4Message) {
				properties4Message = new PropertiesUtil("message.properties");
			}
		} catch (IOException e) {
			LOGGER.error("load properties is error in Component.", e);
		}
	}
	
	/**
	 * 解析请求参数
	 * @param request 请求对象
	 * @param encodeType 编码格式
	 * @throws UnsupportedEncodingException
	 */
	protected void paraseRequestBody(Request request, String encodeType) throws UnsupportedEncodingException{
		byte[] req = new byte[request.getBody().readableBytes()];
		request.getBody().readBytes(req);
		String body = null;
		if (null == encodeType || "".equals(encodeType)) {// 默认UTF-8编码格式
			body = new String(req, Constants.ENCODEING_UTF8);
			body = URLDecoder.decode(body, Constants.ENCODEING_UTF8);
		} else {
			body = new String(req, encodeType);
			body = URLDecoder.decode(body, encodeType);
		}
		this.requestBody = body;
	}
	
	/**
	 * 获取请求参数
	 * @param paramName
	 * @return
	 */
	protected String getParameterByName(String paramName) {
		if (null == this.requestBody) {
			return null;
		}
		String[] params = this.requestBody.split("&");
		for (String param : params) {
			if (paramName.equalsIgnoreCase(param.split("=", 2)[0])) {
				return param.split("=", 2)[1];
			}
		}
		return null;
	}
	
	/**
	 * 封装请求对象
	 * @return
	 */
	protected YSMsgPO getRequestObject() {
		YSMsgPO msgPO = new YSMsgPO();
		String appId = getParameterByName("appId");
		String sign = getParameterByName("sign");
		String mobile = getParameterByName("mobile");
		String msg = getParameterByName("msg");
		String msgId = getParameterByName("msgId");
		String timestamp = getParameterByName("timestamp");
		
		if (null == appId || "".equals(appId)) {
			SMGatewayException.raise("2001");
		}
		if (null == sign || "".equals(sign)) {
			SMGatewayException.raise("2002");
		}
		if (null == mobile || "".equals(mobile)) {
			SMGatewayException.raise("2003");
		}
		if (null == msg || "".equals(msg)) {
			SMGatewayException.raise("2004");
		}
		if (null == timestamp || "".equals(timestamp)) {
			SMGatewayException.raise("2005");
		}
		msgPO.setAppId(appId);
		msgPO.setMobile(mobile);
		msgPO.setMsg(msg);
		msgPO.setMsgId(msgId);
		msgPO.setSign(sign);
		msgPO.setTimestamp(timestamp);
		msgPO.setMsgSign(getParameterByName("messagesign"));
		LOGGER.info("[HandlerHelper.getRequestObject][request]"+gson.toJson(msgPO));
		return msgPO;
	}
	
	/**
	 * 签名校验
	 * @param requestObject
	 * @throws Exception
	 */
	protected void validSign(YSMsgPO requestObject) throws Exception {
		Calendar c = Calendar.getInstance();
		c.getTimeInMillis();
		merchantAppService = (MerchantAppService)SpringContextUtil.getBean("merchantAppService");
		Merchant_appPO merchantApp = merchantAppService.queryMerchantApp(requestObject.getAppId());
		if (null == merchantApp) {
			SMGatewayException.raise("4001");
		}
		String sign = MD5Util.MD5Encode(merchantApp.getApp_key() + 
				requestObject.getAppId() + requestObject.getTimestamp());
		if (!sign.equalsIgnoreCase(requestObject.getSign())) {
			SMGatewayException.raise("2006");
		}
	}
	
	/**
	 * 业务预处理
	 * @param request 请求对象
	 * @param encodeType 编码格式
	 * @return
	 * @throws Exception
	 */
	protected YSMsgPO preDobusiness(Request request, String encodeType) throws Exception {
		LOGGER.info("[HandlerHelper.preDobusiness][begin]");
		// 解析请求数据
		paraseRequestBody(request, encodeType);
		
		// 获取请求参数
		YSMsgPO requestObject = getRequestObject();
		
		// 验证签名
		validSign(requestObject);
		LOGGER.info("[HandlerHelper.preDobusiness][end]");
		return requestObject;
	}
}

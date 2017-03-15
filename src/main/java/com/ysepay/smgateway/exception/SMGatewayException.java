package com.ysepay.smgateway.exception;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.ysepay.smgateway.utils.PropertiesUtil;

/**
 * 异常信息处理类
 * @author sunlei
 * @date 2017年2月21日
 */
public class SMGatewayException extends RuntimeException {
	
	private static final long serialVersionUID = -9007177568370544168L;

	private static final Logger LOGGER = Logger.getLogger(SMGatewayException.class);

	// 错误编码
	private String code;

	// 错误信息
	private String msg;

	// 错误信息配置文件
	protected static PropertiesUtil properties4Message = null;
	
	public static PropertiesUtil getMessageProperties(){
		try {
			if (null == properties4Message) {
				properties4Message = new PropertiesUtil("message.properties");
			}
		} catch (IOException e) {
			LOGGER.error("load properties is error in Component.", e);
		}
		return properties4Message;
	}

	public SMGatewayException() {
	}

	public SMGatewayException(String code, String message) {
		this.code = code;
		this.msg = message;
	}

	public static void raise(String code) {
		String message = getMessageProperties().getProperty(code);
		if (null == message || "".equals(message)) {
			message = "[短信网关] 未知异常编码：" + code;
		}
		SMGatewayException exception = new SMGatewayException(code, message);
		throw exception;
	}

	public static void raise(String code, String message) {
		SMGatewayException exception = new SMGatewayException(code, message);
		throw exception;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return msg;
	}

	public void setMessage(String message) {
		this.msg = message;
	}
}

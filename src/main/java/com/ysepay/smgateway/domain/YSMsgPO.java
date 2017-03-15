package com.ysepay.smgateway.domain;

public class YSMsgPO {
	
	private String appId;
	private String msgId;
	private String msgSign;
	private String sign;
	private String mobile;
	private String msg;
	private String timestamp;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getMsgSign() {
		return msgSign;
	}
	public void setMsgSign(String msgSign) {
		this.msgSign = msgSign;
	}
	
}

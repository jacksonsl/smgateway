package com.ysepay.smgateway.domain;

/**
 * 梦网短信发送对象
 * @author sunlei
 * @date 2017年2月28日
 */
public class MengWangMsgPO {

	private String userId;
	
	private String password;
	
	private String pszMobis;
	
	private String pszMsg;
	
	private String iMobiCount;

	private String pszSubPort;
	
	private String MsgId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPszMobis() {
		return pszMobis;
	}

	public void setPszMobis(String pszMobis) {
		this.pszMobis = pszMobis;
	}

	public String getPszMsg() {
		return pszMsg;
	}

	public void setPszMsg(String pszMsg) {
		this.pszMsg = pszMsg;
	}

	public String getiMobiCount() {
		return iMobiCount;
	}

	public void setiMobiCount(String iMobiCount) {
		this.iMobiCount = iMobiCount;
	}

	public String getPszSubPort() {
		return pszSubPort;
	}

	public void setPszSubPort(String pszSubPort) {
		this.pszSubPort = pszSubPort;
	}

	public String getMsgId() {
		return MsgId;
	}

	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	
}

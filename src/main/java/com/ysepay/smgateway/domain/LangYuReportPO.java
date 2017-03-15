package com.ysepay.smgateway.domain;

/**
 * 朗宇状态报告对象
 * @author sunlei
 * @date 2017年3月2日
 */
public class LangYuReportPO {
	
	/**
	 * 接收状态报告验证的用户名（不是账户名），是按照用户要求配置的名称，可以为空
	 */
	private String receiver;
	
	/**
	 * 接收状态报告验证的密码，可以为空
	 */
	private String pswd;
	
	/**
	 * 提交短信时平台返回的msgid
	 */
	private String msgid;
	
	/**
	 * 格式YYMMDDhhmm，其中YY=年份的最后两位（00-99），MM=月份（01-12），DD=日（01-31），hh=小时（00-23），mm=分钟（00-59）
	 */
	private String reportTime;
	
	/**
	 * 单一的手机号码
	 */
	private String mobile;
	
	/**
	 * 状态报告数值
	 */
	private String status;

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

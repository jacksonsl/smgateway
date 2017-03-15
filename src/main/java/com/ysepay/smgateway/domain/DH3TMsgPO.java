package com.ysepay.smgateway.domain;

/**
 * 大汉三通短信发送对象
 * @author sunlei
 * @date 2017年2月28日
 */
public class DH3TMsgPO {
	/**
	 * 用户账号
	 */
	private String account;
	
	/**
	 * 账号密码，需采用MD5加密(32位小写)
	 */
	private String password;
	
	/**
	 * 该批短信编号(32位UUID)，需保证唯一，选填
	 */
	private String msgid;
	
	/**
	 * 接收手机号码，多个手机号码用英文逗号分隔，最多500个，必填
	 */
	private String phones;
	
	/**
	 * 短信内容，最多350个汉字，必填,内容中不要出现【】[]这两种方括号，该字符为签名专用
	 */
	private String content;

	/**
	 * 短信签名，该签名需要提前报备，生效后方可使用，不可修改，必填
	 * 示例如：【大汉三通】
	 */
	private String sign;
	
	/**
	 * 短信签名对应子码(大汉三通提供)+自定义扩展子码(选填)，必须是数字，选填，未填使用签名对应子码
	 */
	private String subcode;
	
	/**
	 * 定时发送时间，格式yyyyMMddHHmm，为空或早于当前时间则立即发送
	 */
	private String sendtime;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public String getPhones() {
		return phones;
	}

	public void setPhones(String phones) {
		this.phones = phones;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSubcode() {
		return subcode;
	}

	public void setSubcode(String subcode) {
		this.subcode = subcode;
	}

	public String getSendtime() {
		return sendtime;
	}

	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}
	
}

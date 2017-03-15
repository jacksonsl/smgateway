package com.ysepay.smgateway.domain;

/**
 * 旧的短信网关请求对象
 * @author sunlei
 * @date 2017年3月6日
 */
public class OldYSMsgPO {
	/**
	 * 发起方
	 * eptok：E票通系统
	 * ysepay：支付平台
	 * p2p：P2P平台
	 * posp：POSP系统
	 * callcenter:呼叫中心
	 */
	private String src;
	
	/**
	 * 密钥
	 */
	private String key;
	
	/**
	 * 短信签名
	 * ystx,yszf,hm,ysjt,ywt
	 */
	private String messagesign;
	
	/**
	 * 短信ID
	 * yyyymmddhhmiss+4位整数（循环从0000到9999）
	 */
	private String msgid;
	
	/**
	 * 短信通道
	 * 01：移动梦网
	 * 02：朗宇通道.
	 * 如果使用者不指定，默认选择系统配置的。
	 */
	private String channel;
	
	/**
	 * 手机号码
	 * 手机号码一次最大可以发送100个号码，英文逗号分割
	 */
	private String mobile;
	
	/**
	 * 短信内容
	 * 350是字的长度。一个英文字母，一个汉字都视为一个字。
	 * 梦网：70字为一条进行计费，70字包含签名在内。
	 * 朗宇：普通短信70字符/条，长短信（超过70字符）按67字符/条计费（包括签名），短信内容长度不能超过700个字符
	 */
	private String message;
	
	/**
	 * 时间戳,yyyyMMdddhhmmss
	 */
	private String time;
	
	/**
	 * 失败时重发次数
	 */
	private String repeatCount;
	
	/**
	 * 校验值
	 * MD5(src + messagesign + msgid +channel + mobile+ message  + time+ repeatCount +key) ；
	 * MD5后需进行base64
	 */
	private String checkValue;

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getMessagesign() {
		return messagesign;
	}

	public void setMessagesign(String messagesign) {
		this.messagesign = messagesign;
	}

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(String repeatCount) {
		this.repeatCount = repeatCount;
	}

	public String getCheckValue() {
		return checkValue;
	}

	public void setCheckValue(String checkValue) {
		this.checkValue = checkValue;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}

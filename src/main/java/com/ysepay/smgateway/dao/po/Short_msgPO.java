package com.ysepay.smgateway.dao.po;

import java.util.Date;

/**
 * <b>短信流水表[short_msg_flow]数据持久化对象</b>
 * 
 * @author sunlei
 * @date 2017-03-07 10:15:45
 */
public class Short_msgPO {

	/**
	 * 商户号
	 */
	private String merchant_no;
	
	/**
	 * 应用id
	 */
	private String app_id;
	
	/**
	 * 来源
	 */
	private String src;
	
	/**
	 * 网关消息id
	 */
	private String gateway_msgid;
	
	/**
	 * 商户消息id
	 */
	private String msgid;
	
	/**
	 * 电话号码
	 */
	private String mobile;
	
	/**
	 * 通道消息id
	 */
	private String mw_msgid;
	
	/**
	 * 应用签名
	 */
	private String app_sign;
	
	/**
	 * 消息签名
	 */
	private String messagesign;
	
	/**
	 * 渠道
	 */
	private String channel;
	
	/**
	 * 消息
	 */
	private String message;
	
	/**
	 * 短信字数
	 */
	private int content_count;
	
	/**
	 * 短信条数
	 */
	private int msg_count;
	
	/**
	 * 创建时间
	 */
	private Date createtime;
	
	/**
	 * 状态(0:初始化 1:申请成功 2:申请失败 3:发送成功 4:发送失败)
	 */
	private String status;
	
	/**
	 * 渠道返回的状态描述
	 */
	private String descs;
	
	/**
	 * 申请状态
	 */
	private String fetch_state;
	
	/**
	 * 渠道账号
	 */
	private String channel_account;
	
	/**
	 * 短信网关向通道上送短信的时间
	 */
	private Date send_time;
	
	/**
	 * 用户接收到短信的时间
	 */
	private Date receive_time;
	

	/**
	 * 商户号
	 * 
	 * @return merchant_no
	 */
	public String getMerchant_no() {
		return merchant_no;
	}
	
	/**
	 * 应用id
	 * 
	 * @return app_id
	 */
	public String getApp_id() {
		return app_id;
	}
	
	/**
	 * 来源
	 * 
	 * @return src
	 */
	public String getSrc() {
		return src;
	}
	
	/**
	 * 网关消息id
	 * 
	 * @return gateway_msgid
	 */
	public String getGateway_msgid() {
		return gateway_msgid;
	}
	
	/**
	 * 商户消息id
	 * 
	 * @return msgid
	 */
	public String getMsgid() {
		return msgid;
	}
	
	/**
	 * 电话号码
	 * 
	 * @return mobile
	 */
	public String getMobile() {
		return mobile;
	}
	
	/**
	 * 通道消息id
	 * 
	 * @return mw_msgid
	 */
	public String getMw_msgid() {
		return mw_msgid;
	}
	
	/**
	 * 应用签名
	 * 
	 * @return app_sign
	 */
	public String getApp_sign() {
		return app_sign;
	}
	
	/**
	 * 消息签名
	 * 
	 * @return messagesign
	 */
	public String getMessagesign() {
		return messagesign;
	}
	
	/**
	 * 渠道
	 * 
	 * @return channel
	 */
	public String getChannel() {
		return channel;
	}
	
	/**
	 * 消息
	 * 
	 * @return message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * 短信字数
	 * 
	 * @return content_count
	 */
	public int getContent_count() {
		return content_count;
	}
	
	/**
	 * 短信条数
	 * 
	 * @return msg_count
	 */
	public int getMsg_count() {
		return msg_count;
	}
	
	/**
	 * 创建时间
	 * 
	 * @return createtime
	 */
	public Date getCreatetime() {
		return createtime;
	}
	
	/**
	 * 状态(0:初始化 1:申请成功 2:申请失败 3:发送成功 4:发送失败)
	 * 
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * 渠道返回的状态描述
	 * 
	 * @return descs
	 */
	public String getDescs() {
		return descs;
	}
	
	/**
	 * 申请状态
	 * 
	 * @return fetch_state
	 */
	public String getFetch_state() {
		return fetch_state;
	}
	
	/**
	 * 渠道账号
	 * 
	 * @return channel_account
	 */
	public String getChannel_account() {
		return channel_account;
	}
	
	/**
	 * 短信网关向通道上送短信的时间
	 * 
	 * @return send_time
	 */
	public Date getSend_time() {
		return send_time;
	}
	
	/**
	 * 用户接收到短信的时间
	 * 
	 * @return receive_time
	 */
	public Date getReceive_time() {
		return receive_time;
	}
	

	/**
	 * 商户号
	 * 
	 * @param merchant_no
	 */
	public void setMerchant_no(String merchant_no) {
		this.merchant_no = merchant_no;
	}
	
	/**
	 * 应用id
	 * 
	 * @param app_id
	 */
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	
	/**
	 * 来源
	 * 
	 * @param src
	 */
	public void setSrc(String src) {
		this.src = src;
	}
	
	/**
	 * 网关消息id
	 * 
	 * @param gateway_msgid
	 */
	public void setGateway_msgid(String gateway_msgid) {
		this.gateway_msgid = gateway_msgid;
	}
	
	/**
	 * 商户消息id
	 * 
	 * @param msgid
	 */
	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	
	/**
	 * 电话号码
	 * 
	 * @param mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	/**
	 * 通道消息id
	 * 
	 * @param mw_msgid
	 */
	public void setMw_msgid(String mw_msgid) {
		this.mw_msgid = mw_msgid;
	}
	
	/**
	 * 应用签名
	 * 
	 * @param app_sign
	 */
	public void setApp_sign(String app_sign) {
		this.app_sign = app_sign;
	}
	
	/**
	 * 消息签名
	 * 
	 * @param messagesign
	 */
	public void setMessagesign(String messagesign) {
		this.messagesign = messagesign;
	}
	
	/**
	 * 渠道
	 * 
	 * @param channel
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	/**
	 * 消息
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * 短信字数
	 * 
	 * @param content_count
	 */
	public void setContent_count(int content_count) {
		this.content_count = content_count;
	}
	
	/**
	 * 短信条数
	 * 
	 * @param msg_count
	 */
	public void setMsg_count(int msg_count) {
		this.msg_count = msg_count;
	}
	
	/**
	 * 创建时间
	 * 
	 * @param createtime
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	/**
	 * 状态(0:初始化 1:申请成功 2:申请失败 3:发送成功 4:发送失败)
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * 渠道返回的状态描述
	 * 
	 * @param descs
	 */
	public void setDescs(String descs) {
		this.descs = descs;
	}
	
	/**
	 * 申请状态
	 * 
	 * @param fetch_state
	 */
	public void setFetch_state(String fetch_state) {
		this.fetch_state = fetch_state;
	}
	
	/**
	 * 渠道账号
	 * 
	 * @param channel_account
	 */
	public void setChannel_account(String channel_account) {
		this.channel_account = channel_account;
	}
	
	/**
	 * 短信网关向通道上送短信的时间
	 * 
	 * @param send_time
	 */
	public void setSend_time(Date send_time) {
		this.send_time = send_time;
	}
	
	/**
	 * 用户接收到短信的时间
	 * 
	 * @param receive_time
	 */
	public void setReceive_time(Date receive_time) {
		this.receive_time = receive_time;
	}
	

}
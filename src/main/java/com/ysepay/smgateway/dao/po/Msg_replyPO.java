package com.ysepay.smgateway.dao.po;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <b>短信回复[msg_reply]数据持久化对象</b>
 * 
 * @author sunlei
 * @date 2017-03-03 11:16:39
 */
public class Msg_replyPO {

	/**
	 * 商户号
	 */
	private String merchant_no;
	
	/**
	 * 应用id
	 */
	private String app_id;
	
	/**
	 * 消息id
	 */
	private BigDecimal msgid;
	
	/**
	 * 渠道编码
	 */
	private String channel_code;
	
	/**
	 * 渠道消息id
	 */
	private String channel_msg_id;
	
	/**
	 * 电话号码
	 */
	private String mobile_no;
	
	/**
	 * 回复内容
	 */
	private String reply_content;
	
	/**
	 * 接收时间
	 */
	private Date receive_time;
	
	/**
	 * 推送信息
	 */
	private String deliver_msg;
	

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
	 * 消息id
	 * 
	 * @return msgid
	 */
	public BigDecimal getMsgid() {
		return msgid;
	}
	
	/**
	 * 渠道编码
	 * 
	 * @return channel_code
	 */
	public String getChannel_code() {
		return channel_code;
	}
	
	/**
	 * 渠道消息id
	 * 
	 * @return channel_msg_id
	 */
	public String getChannel_msg_id() {
		return channel_msg_id;
	}
	
	/**
	 * 电话号码
	 * 
	 * @return mobile_no
	 */
	public String getMobile_no() {
		return mobile_no;
	}
	
	/**
	 * 回复内容
	 * 
	 * @return reply_content
	 */
	public String getReply_content() {
		return reply_content;
	}
	
	/**
	 * 接收时间
	 * 
	 * @return receive_time
	 */
	public Date getReceive_time() {
		return receive_time;
	}
	
	/**
	 * 推送信息
	 * 
	 * @return deliver_msg
	 */
	public String getDeliver_msg() {
		return deliver_msg;
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
	 * 消息id
	 * 
	 * @param msgid
	 */
	public void setMsgid(BigDecimal msgid) {
		this.msgid = msgid;
	}
	
	/**
	 * 渠道编码
	 * 
	 * @param channel_code
	 */
	public void setChannel_code(String channel_code) {
		this.channel_code = channel_code;
	}
	
	/**
	 * 渠道消息id
	 * 
	 * @param channel_msg_id
	 */
	public void setChannel_msg_id(String channel_msg_id) {
		this.channel_msg_id = channel_msg_id;
	}
	
	/**
	 * 电话号码
	 * 
	 * @param mobile_no
	 */
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	
	/**
	 * 回复内容
	 * 
	 * @param reply_content
	 */
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	
	/**
	 * 接收时间
	 * 
	 * @param receive_time
	 */
	public void setReceive_time(Date receive_time) {
		this.receive_time = receive_time;
	}
	
	/**
	 * 推送信息
	 * 
	 * @param deliver_msg
	 */
	public void setDeliver_msg(String deliver_msg) {
		this.deliver_msg = deliver_msg;
	}
	

}
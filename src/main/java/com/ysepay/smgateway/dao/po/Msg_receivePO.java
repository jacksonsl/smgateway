package com.ysepay.smgateway.dao.po;

import java.util.Date;

/**
 * <b>短信接收[msg_receive]数据持久化对象</b>
 * 
 * @author sunlei
 * @date 2017-02-21 14:17:12
 */
public class Msg_receivePO {

	/**
	 * 渠道id
	 */
	private String channel_id;
	
	/**
	 * 渠道消息id
	 */
	private String channel_msg_id;
	
	/**
	 * 电话号码
	 */
	private String mobile_no;
	
	/**
	 * 短信内容
	 */
	private String msg_content;
	
	/**
	 * 接收时间
	 */
	private Date receive_time;
	

	/**
	 * 渠道id
	 * 
	 * @return channel_id
	 */
	public String getChannel_id() {
		return channel_id;
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
	 * 短信内容
	 * 
	 * @return msg_content
	 */
	public String getMsg_content() {
		return msg_content;
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
	 * 渠道id
	 * 
	 * @param channel_id
	 */
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
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
	 * 短信内容
	 * 
	 * @param msg_content
	 */
	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}
	
	/**
	 * 接收时间
	 * 
	 * @param receive_time
	 */
	public void setReceive_time(Date receive_time) {
		this.receive_time = receive_time;
	}
	

}
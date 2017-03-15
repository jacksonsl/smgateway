package com.ysepay.smgateway.dao.po;

import java.util.Date;

/**
 * <b>短信渠道[msg_channel]数据持久化对象</b>
 * 
 * @author sunlei
 * @date 2017-03-02 11:50:12
 */
public class Msg_channelPO {

	/**
	 * 渠道编码
	 */
	private String channel_code;
	
	/**
	 * 渠道商名称
	 */
	private String channel_name;
	
	/**
	 * 渠道状态
	 */
	private String channel_status;
	
	/**
	 * 渠道创建时间
	 */
	private Date create_time;
	

	/**
	 * 渠道编码
	 * 
	 * @return channel_code
	 */
	public String getChannel_code() {
		return channel_code;
	}
	
	/**
	 * 渠道商名称
	 * 
	 * @return channel_name
	 */
	public String getChannel_name() {
		return channel_name;
	}
	
	/**
	 * 渠道状态
	 * 
	 * @return channel_status
	 */
	public String getChannel_status() {
		return channel_status;
	}
	
	/**
	 * 渠道创建时间
	 * 
	 * @return create_time
	 */
	public Date getCreate_time() {
		return create_time;
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
	 * 渠道商名称
	 * 
	 * @param channel_name
	 */
	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}
	
	/**
	 * 渠道状态
	 * 
	 * @param channel_status
	 */
	public void setChannel_status(String channel_status) {
		this.channel_status = channel_status;
	}
	
	/**
	 * 渠道创建时间
	 * 
	 * @param create_time
	 */
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	

}
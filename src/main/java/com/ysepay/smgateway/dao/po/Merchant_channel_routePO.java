package com.ysepay.smgateway.dao.po;

import java.math.BigDecimal;

/**
 * <b>商户渠道路由[merchant_channel_route]数据持久化对象</b>
 * 
 * @author sunlei
 * @date 2017-03-02 11:47:39
 */
public class Merchant_channel_routePO {

	/**
	 * 商户号
	 */
	private String merchant_no;
	
	/**
	 * 应用id
	 */
	private String app_id;
	
	/**
	 * 渠道编码
	 */
	private String channel_code;
	
	/**
	 * 渠道账号
	 */
	private String channel_account;
	
	/**
	 * 账号类型
	 */
	private String type;

	/**
	 * 应用签名
	 */
	private String app_sign;
	
	/**
	 * 渠道优先级，数字越大优先级越高
	 */
	private BigDecimal priority;
	
	/**
	 * 渠道权重
	 */
	private BigDecimal weight;
	
	/**
	 * 路由状态(0:停用 1:正常)
	 */
	private String status;
	

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
	 * 渠道编码
	 * 
	 * @return channel_code
	 */
	public String getChannel_code() {
		return channel_code;
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
	 * 账号类型
	 * 
	 * @return type
	 */
	public String getType() {
		return type;
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
	 * 渠道优先级，数字越大优先级越高
	 * 
	 * @return priority
	 */
	public BigDecimal getPriority() {
		return priority;
	}
	
	/**
	 * 渠道权重
	 * 
	 * @return weight
	 */
	public BigDecimal getWeight() {
		return weight;
	}
	
	/**
	 * 路由状态(0:停用 1:正常)
	 * 
	 * @return status
	 */
	public String getStatus() {
		return status;
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
	 * 渠道编码
	 * 
	 * @param channel_code
	 */
	public void setChannel_code(String channel_code) {
		this.channel_code = channel_code;
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
	 * 账号类型
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
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
	 * 渠道优先级，数字越大优先级越高
	 * 
	 * @param priority
	 */
	public void setPriority(BigDecimal priority) {
		this.priority = priority;
	}
	
	/**
	 * 渠道权重
	 * 
	 * @param weight
	 */
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	
	/**
	 * 路由状态(0:停用 1:正常)
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	

}
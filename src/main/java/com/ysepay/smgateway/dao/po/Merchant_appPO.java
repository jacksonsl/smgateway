package com.ysepay.smgateway.dao.po;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <b>商户应用[merchant_app]数据持久化对象</b>
 * 
 * @author sunlei
 * @date 2017-03-02 11:45:22
 */
public class Merchant_appPO {

	/**
	 * 商户号
	 */
	private String merchant_no;
	
	/**
	 * 应用编码
	 */
	private String app_code;
	
	/**
	 * 应用状态(1:正常 2:停用)
	 */
	private String app_status;
	
	/**
	 * 应用名称
	 */
	private String app_name;
	
	/**
	 * 负责人,使用人
	 */
	private String contact_person;
	
	/**
	 * 应用id
	 */
	private String app_id;
	
	/**
	 * 应用key
	 */
	private String app_key;
	
	/**
	 * 应用创建时间
	 */
	private Date create_time;
	
	/**
	 * 状态回送地址
	 */
	private String status_return_url;
	
	/**
	 * 回复回送地址
	 */
	private String reply_return_url;
	
	/**
	 * 短信余额
	 */
	private BigDecimal msg_balance;
	

	/**
	 * 商户号
	 * 
	 * @return merchant_no
	 */
	public String getMerchant_no() {
		return merchant_no;
	}
	
	/**
	 * 应用编码
	 * 
	 * @return app_code
	 */
	public String getApp_code() {
		return app_code;
	}
	
	/**
	 * 应用状态(1:正常 2:停用)
	 * 
	 * @return app_status
	 */
	public String getApp_status() {
		return app_status;
	}
	
	/**
	 * 应用名称
	 * 
	 * @return app_name
	 */
	public String getApp_name() {
		return app_name;
	}
	
	/**
	 * 负责人,使用人
	 * 
	 * @return contact_person
	 */
	public String getContact_person() {
		return contact_person;
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
	 * 应用key
	 * 
	 * @return app_key
	 */
	public String getApp_key() {
		return app_key;
	}
	
	/**
	 * 应用创建时间
	 * 
	 * @return create_time
	 */
	public Date getCreate_time() {
		return create_time;
	}
	
	/**
	 * 状态回送地址
	 * 
	 * @return status_return_url
	 */
	public String getStatus_return_url() {
		return status_return_url;
	}
	
	/**
	 * 回复回送地址
	 * 
	 * @return reply_return_url
	 */
	public String getReply_return_url() {
		return reply_return_url;
	}
	
	/**
	 * 短信余额
	 * 
	 * @return msg_balance
	 */
	public BigDecimal getMsg_balance() {
		return msg_balance;
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
	 * 应用编码
	 * 
	 * @param app_code
	 */
	public void setApp_code(String app_code) {
		this.app_code = app_code;
	}
	
	/**
	 * 应用状态(1:正常 2:停用)
	 * 
	 * @param app_status
	 */
	public void setApp_status(String app_status) {
		this.app_status = app_status;
	}
	
	/**
	 * 应用名称
	 * 
	 * @param app_name
	 */
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	
	/**
	 * 负责人,使用人
	 * 
	 * @param contact_person
	 */
	public void setContact_person(String contact_person) {
		this.contact_person = contact_person;
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
	 * 应用key
	 * 
	 * @param app_key
	 */
	public void setApp_key(String app_key) {
		this.app_key = app_key;
	}
	
	/**
	 * 应用创建时间
	 * 
	 * @param create_time
	 */
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	/**
	 * 状态回送地址
	 * 
	 * @param status_return_url
	 */
	public void setStatus_return_url(String status_return_url) {
		this.status_return_url = status_return_url;
	}
	
	/**
	 * 回复回送地址
	 * 
	 * @param reply_return_url
	 */
	public void setReply_return_url(String reply_return_url) {
		this.reply_return_url = reply_return_url;
	}
	
	/**
	 * 短信余额
	 * 
	 * @param msg_balance
	 */
	public void setMsg_balance(BigDecimal msg_balance) {
		this.msg_balance = msg_balance;
	}
	

}
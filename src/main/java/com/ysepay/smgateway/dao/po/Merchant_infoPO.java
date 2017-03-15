package com.ysepay.smgateway.dao.po;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <b>商户信息[merchant_info]数据持久化对象</b>
 * 
 * @author sunlei
 * @date 2017-02-21 14:17:12
 */
public class Merchant_infoPO {

	/**
	 * 商户名称
	 */
	private String merchant_name;
	
	/**
	 * 商户号
	 */
	private String merchant_no;
	
	/**
	 * 商户密码
	 */
	private String merchant_pwd;
	
	/**
	 * 商户地址
	 */
	private String mercant_address;
	
	/**
	 * 商户联系人
	 */
	private String merchant_contact;
	
	/**
	 * 商户联系人电话
	 */
	private String merchant_mobile;
	
	/**
	 * 商户状态(0:正常 1:停用 2:短信余额已经用完)
	 */
	private String merchant_status;
	
	/**
	 * 商户创建时间
	 */
	private Date create_time;
	
	/**
	 * 商户短信余额
	 */
	private BigDecimal msg_balance;
	

	/**
	 * 商户名称
	 * 
	 * @return merchant_name
	 */
	public String getMerchant_name() {
		return merchant_name;
	}
	
	/**
	 * 商户号
	 * 
	 * @return merchant_no
	 */
	public String getMerchant_no() {
		return merchant_no;
	}
	
	/**
	 * 商户密码
	 * 
	 * @return merchant_pwd
	 */
	public String getMerchant_pwd() {
		return merchant_pwd;
	}
	
	/**
	 * 商户地址
	 * 
	 * @return mercant_address
	 */
	public String getMercant_address() {
		return mercant_address;
	}
	
	/**
	 * 商户联系人
	 * 
	 * @return merchant_contact
	 */
	public String getMerchant_contact() {
		return merchant_contact;
	}
	
	/**
	 * 商户联系人电话
	 * 
	 * @return merchant_mobile
	 */
	public String getMerchant_mobile() {
		return merchant_mobile;
	}
	
	/**
	 * 商户状态(0:正常 1:停用 2:短信余额已经用完)
	 * 
	 * @return merchant_status
	 */
	public String getMerchant_status() {
		return merchant_status;
	}
	
	/**
	 * 商户创建时间
	 * 
	 * @return create_time
	 */
	public Date getCreate_time() {
		return create_time;
	}
	
	/**
	 * 商户短信余额
	 * 
	 * @return msg_balance
	 */
	public BigDecimal getMsg_balance() {
		return msg_balance;
	}
	

	/**
	 * 商户名称
	 * 
	 * @param merchant_name
	 */
	public void setMerchant_name(String merchant_name) {
		this.merchant_name = merchant_name;
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
	 * 商户密码
	 * 
	 * @param merchant_pwd
	 */
	public void setMerchant_pwd(String merchant_pwd) {
		this.merchant_pwd = merchant_pwd;
	}
	
	/**
	 * 商户地址
	 * 
	 * @param mercant_address
	 */
	public void setMercant_address(String mercant_address) {
		this.mercant_address = mercant_address;
	}
	
	/**
	 * 商户联系人
	 * 
	 * @param merchant_contact
	 */
	public void setMerchant_contact(String merchant_contact) {
		this.merchant_contact = merchant_contact;
	}
	
	/**
	 * 商户联系人电话
	 * 
	 * @param merchant_mobile
	 */
	public void setMerchant_mobile(String merchant_mobile) {
		this.merchant_mobile = merchant_mobile;
	}
	
	/**
	 * 商户状态(0:正常 1:停用 2:短信余额已经用完)
	 * 
	 * @param merchant_status
	 */
	public void setMerchant_status(String merchant_status) {
		this.merchant_status = merchant_status;
	}
	
	/**
	 * 商户创建时间
	 * 
	 * @param create_time
	 */
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	/**
	 * 商户短信余额
	 * 
	 * @param msg_balance
	 */
	public void setMsg_balance(BigDecimal msg_balance) {
		this.msg_balance = msg_balance;
	}
	

}
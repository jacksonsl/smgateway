package com.ysepay.smgateway.dao.po;

import java.math.BigDecimal;

/**
 * <b>短信渠道账号[msg_channel_account]数据持久化对象</b>
 * 
 * @author sunlei
 * @date 2017-03-02 11:52:25
 */
public class Msg_channel_accountPO {
	
	/**
	 * 渠道网关地址
	 */
	private String url;
	
	/**
	 * 渠道账号
	 */
	private String account;
	
	/**
	 * 渠道密码
	 */
	private String pwd;
	
	/**
	 * 渠道短信余额
	 */
	private BigDecimal balance;
	
	/**
	 * 渠道状态
	 */
	private String account_status;
	
	/**
	 * 渠道编码
	 */
	private String channel_code;
	
	/**
	 * 渠道网关地址
	 * 
	 * @return url
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * 渠道账号
	 * 
	 * @return account
	 */
	public String getAccount() {
		return account;
	}
	
	/**
	 * 渠道密码
	 * 
	 * @return pwd
	 */
	public String getPwd() {
		return pwd;
	}
	
	/**
	 * 渠道短信余额
	 * 
	 * @return balance
	 */
	public BigDecimal getBalance() {
		return balance;
	}
	
	/**
	 * 渠道状态
	 * 
	 * @return account_status
	 */
	public String getAccount_status() {
		return account_status;
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
	 * 渠道网关地址
	 * 
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * 渠道账号
	 * 
	 * @param account
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	
	/**
	 * 渠道密码
	 * 
	 * @param pwd
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	/**
	 * 渠道短信余额
	 * 
	 * @param balance
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	/**
	 * 渠道状态
	 * 
	 * @param account_status
	 */
	public void setAccount_status(String account_status) {
		this.account_status = account_status;
	}
	
	/**
	 * 渠道编码
	 * 
	 * @param channel_code
	 */
	public void setChannel_code(String channel_code) {
		this.channel_code = channel_code;
	}
	

}
package com.ysepay.smgateway.domain;

/**
 * 朗宇发送短信对象
 * @author sunlei
 * @date 2017年2月21日
 */
public class LangYuMsgPO {
	
	/**
	 * 必填参数。用户账号
	 */
	private String account;
	
	/**
	 * 必填参数。用户密码
	 */
	private String pswd;
	
	/**
	 * 必填参数。合法的手机号码，号码间用英文逗号分隔
	 */
	private String mobile;
	
	/**
	 * 必填参数。短信内容，短信内容长度不能超过700个字符。
	 * 使用URL方式编码为UTF-8格式。
	 * 短信内容超过70个字符时，会被拆分成多条，然后以长短信的格式发送。
	 * 普通短信70字符/条，长短信（超过70字符）按67字符/条计费（包括签名）
	 */
	private String msg;
	
	/**
	 * 必填参数。
	 * 是否需要状态报告，取值true或false，true，表明需要状态报告；false不需要状态报告
	 */
	private String needstatus;
	
	/**
	 * 可选参数。用户订购的产品id，不填写系统采用用户的默认产品，用户订购多个产品时必填，否则会发生计费错误。
	 */
	private String product;
	
	/**
	 * 可选参数，用户自定义扩展码
	 */
	private String extno;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getNeedstatus() {
		return needstatus;
	}

	public void setNeedstatus(String needstatus) {
		this.needstatus = needstatus;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getExtno() {
		return extno;
	}

	public void setExtno(String extno) {
		this.extno = extno;
	}

}

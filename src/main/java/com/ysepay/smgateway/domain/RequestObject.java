package com.ysepay.smgateway.domain;

public class RequestObject {

	private String data;
	private String timestamp;
	private String sign;
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
}

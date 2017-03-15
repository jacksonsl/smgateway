package com.ysepay.smgateway.enums;

/**
 * 发送短信状态
 * @author sunlei
 * @date 2017年3月2日
 */
public enum SendMsgStatus {
	INIT("0", "初始化"), 
	APPLY_SUCCESS("1", "申请成功"), 
	APPLY_FAIL("2", "申请失败"), 
	SEND_SUCCESS("3", "发送成功"), 
	SEND_FAIL("4", "发送失败");
	
	private String status;
	
	SendMsgStatus(String status, String desc) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	
}

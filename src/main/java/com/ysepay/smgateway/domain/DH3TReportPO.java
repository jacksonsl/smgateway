package com.ysepay.smgateway.domain;

import java.util.List;

/**
 * 大汉三通状态报告
 * @author sunlei
 * @date 2017年3月3日
 */
public class DH3TReportPO {

	private String result;
	private String desc;
	private List<Reports> reports;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public List<Reports> getReports() {
		return reports;
	}
	public void setReports(List<Reports> reports) {
		this.reports = reports;
	}

	public class Reports {
		private String msgid;
		private String phone;
		private String status;
		private String desc;
		private String wgcode;
		private String time;
		private String smsCount;
		private String smsIndex;
		public String getMsgid() {
			return msgid;
		}
		public void setMsgid(String msgid) {
			this.msgid = msgid;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public String getWgcode() {
			return wgcode;
		}
		public void setWgcode(String wgcode) {
			this.wgcode = wgcode;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public String getSmsCount() {
			return smsCount;
		}
		public void setSmsCount(String smsCount) {
			this.smsCount = smsCount;
		}
		public String getSmsIndex() {
			return smsIndex;
		}
		public void setSmsIndex(String smsIndex) {
			this.smsIndex = smsIndex;
		}
		
	}
}

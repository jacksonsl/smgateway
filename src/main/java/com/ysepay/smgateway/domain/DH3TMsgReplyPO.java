package com.ysepay.smgateway.domain;

import java.util.List;

/**
 * 大汉三通短信回复
 * @author sunlei
 * @date 2017年3月3日
 */
public class DH3TMsgReplyPO {

	private String result;
	private String desc;
	private List<Deliver> delivers;
	
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
	public List<Deliver> getDelivers() {
		return delivers;
	}
	public void setDelivers(List<Deliver> delivers) {
		this.delivers = delivers;
	}

	public class Deliver {
		private String phone;// 回复手机号码
		private String content;// 手机回复短信内容
		private String subcode;// 子号码
		private String delivertime;// 回复时间，格式yyyy-MM-ddHH:mm:ss
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getSubcode() {
			return subcode;
		}
		public void setSubcode(String subcode) {
			this.subcode = subcode;
		}
		public String getDelivertime() {
			return delivertime;
		}
		public void setDelivertime(String delivertime) {
			this.delivertime = delivertime;
		}
	}
}

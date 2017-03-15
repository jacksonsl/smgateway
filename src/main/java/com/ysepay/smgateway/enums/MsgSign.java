package com.ysepay.smgateway.enums;

/**
 * 银盛短信签名
 * @author sunlei
 * @date 2017年2月22日
 */
public enum MsgSign {
		
	SIGN_YSTX("ystx", "【银盛通信】"),
	SIGN_YSZF("yszf", "【银盛支付】"),
	SIGN_HM("hm", "【海盟】"),
	SIGN_YSJT("ysjt", "【银盛集团】"),
	SIGN_YWT("ywt", "【壹网通】"),
	SIGN_YMXX("ymxx", "【银盟信息】"),
	SIGN_YSKD("yskd", "【银盛快递】"),
	SIGN_YSD("ysd", "【银盛兑】");
	
	public String code;
	public String msg;
	
	private MsgSign(String code, String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public static MsgSign getMsgSign(String code) {
		if (null == code) {
			return null;
		}
		for (MsgSign lyc : MsgSign.values()) {
			if (lyc.code.equals(code)) {
				return lyc;
			}
		}
		return null;
	}
}

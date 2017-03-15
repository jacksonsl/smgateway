package com.ysepay.smgateway.enums;

/**
 * 短信渠道编码
 * @author sunlei
 * @date 2017年2月21日
 */
public enum MsgChannelCode {
	/**
	 * 朗宇
	 */
	MSG_LANG_YU("MSG_LANG_YU"),
	/**
	 * 大汉三通
	 */
	MSG_DAHAN3TONG("MSG_DAHAN3TONG"),
	/**
	 * 梦网
	 */
	MSG_MENGWANG("MSG_MENGWANG");
	
	public String code;
	
	private String getCode() {
		return this.code;
	}
	
	private MsgChannelCode(String code) {
		this.code = code;
	}

	public static MsgChannelCode getMsgChannelCode(String code) {
        for (MsgChannelCode m : MsgChannelCode.values()) {
            if (m.getCode().equals(code)) {
                return m;
            }
        }
        return null;
    }
}

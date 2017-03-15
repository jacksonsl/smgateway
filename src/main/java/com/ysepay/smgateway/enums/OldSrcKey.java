package com.ysepay.smgateway.enums;

/**
 * 旧短信通道秘钥
 * @author sunlei
 * @date 2017年3月6日
 */
public enum OldSrcKey {
	CALLCENTER("callcenter", "call1357"),
	EPTOK("eptok", "eptok248"),
	YSEPAY("ysepay", "ysepay12"),
	P2P("p2p", "p2p12345"),
	POSP("posp", "posp1234"),
	YSKD("yskd", "dw704919"),
	RISK("risk", "risk1234");
	
	public String src;
	public String key;
	
	OldSrcKey(String src, String key) {
		this.src = src;
		this.key = key;
	}

	public static OldSrcKey getOldSrcKey(String src) {
		if (null == src) {
			return null;
		}
		for (OldSrcKey osk : OldSrcKey.values()) {
			if (osk.src.equals(src)) {
				return osk;
			}
		}
		return null;
	}
}

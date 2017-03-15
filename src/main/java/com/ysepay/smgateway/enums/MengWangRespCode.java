package com.ysepay.smgateway.enums;

/**
 * 梦网错误响应编码
 * @author sunlei
 * @date 2017年2月22日
 */
public enum MengWangRespCode {
	MENG_WANG_ERROR_CODE_0("0", "申请成功"),
	MENG_WANG_ERROR_CODE_1("-1", "参数为空"),
	MENG_WANG_ERROR_CODE_12("-12", "有异常电话号码"),
	MENG_WANG_ERROR_CODE_13("-13", "电话号码个数与实际个数不相等"),
	MENG_WANG_ERROR_CODE_14("-14", "实际号码个数超过100"),
	MENG_WANG_ERROR_CODE_999("-999", "web服务器内部错误");
	
	public String code;
	public String msg;
	
	private MengWangRespCode(String code, String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public static MengWangRespCode getMengWangRespCode(String code) {
		if (null == code) {
			return null;
		}
		for (MengWangRespCode lyc : MengWangRespCode.values()) {
			if (lyc.code.equals(code)) {
				return lyc;
			}
		}
		return null;
	}
}

package com.ysepay.smgateway.enums;

/**
 * 大汉三通响应编码
 * @author sunlei
 * @date 2017年2月22日
 */
public enum DH3TRespCode {
	DH3T_ERROR_CODE_0("0", "提交成功"),
	DH3T_ERROR_CODE_1("1", "账号无效"),
	DH3T_ERROR_CODE_2("2", "密码错误"),
	DH3T_ERROR_CODE_3("3", "msgid太长，不得超过32位"),
	DH3T_ERROR_CODE_5("5", "手机号码个数超过最大限制(500个)"),
	DH3T_ERROR_CODE_6("6", "短信内容超过最大限制(350字)"),
	DH3T_ERROR_CODE_7("7", "扩展子号码无效"),
	DH3T_ERROR_CODE_8("8", "定时时间格式错误"),
	DH3T_ERROR_CODE_14("14", "手机号码为空"),
	DH3T_ERROR_CODE_19("19", "用户被禁发或禁用"),
	DH3T_ERROR_CODE_20("20", "ip鉴权失败"),
	DH3T_ERROR_CODE_21("21", "短信内容为空"),
	DH3T_ERROR_CODE_22("22", "数据包大小不匹配"),
	DH3T_ERROR_CODE_98("98", "系统正忙"),
	DH3T_ERROR_CODE_99("99", "消息格式错误");
	
	public String code;
	public String msg;
	
	private DH3TRespCode(String code, String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public static DH3TRespCode getLangYuRespCode(String code) {
		if (null == code) {
			return null;
		}
		for (DH3TRespCode lyc : DH3TRespCode.values()) {
			if (lyc.code.equals(code)) {
				return lyc;
			}
		}
		return null;
	}
}

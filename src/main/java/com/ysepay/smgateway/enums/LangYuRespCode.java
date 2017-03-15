package com.ysepay.smgateway.enums;

/**
 * 朗宇错误响应编码
 * @author sunlei
 * @date 2017年2月22日
 */
public enum LangYuRespCode {
	LANG_YU_ERROR_CODE_0("0", "提交成功"),
	LANG_YU_ERROR_CODE_101("101", "无此用户"),
	LANG_YU_ERROR_CODE_102("102", "密码错"),
	LANG_YU_ERROR_CODE_103("103", "提交过快（提交速度超过流速限制）"),
	LANG_YU_ERROR_CODE_104("104", "系统忙（因平台侧原因，暂时无法处理提交的短信）"),
	LANG_YU_ERROR_CODE_105("105", "敏感短信（短信内容包含敏感词）"),
	LANG_YU_ERROR_CODE_106("106", "消息长度错（>536或<=0）"),
	LANG_YU_ERROR_CODE_107("107", "包含错误的手机号码"),
	LANG_YU_ERROR_CODE_108("108", "手机号码个数错（群发>50000或<=0;单发>200或<=0）"),
	LANG_YU_ERROR_CODE_109("109", "无发送额度（该用户可用短信数已使用完）"),
	LANG_YU_ERROR_CODE_110("110", "不在发送时间内"),
	LANG_YU_ERROR_CODE_111("111", "超出该账户当月发送额度限制"),
	LANG_YU_ERROR_CODE_112("112", "无此产品，用户没有订购该产品"),
	LANG_YU_ERROR_CODE_113("113", "extno格式错（非数字或者长度不对）"),
	LANG_YU_ERROR_CODE_115("115", "自动审核驳回"),
	LANG_YU_ERROR_CODE_116("116", "签名不合法，未带签名（用户必须带签名的前提下）"),
	LANG_YU_ERROR_CODE_117("117", "IP地址认证错,请求调用的IP地址不是系统登记的IP地址"),
	LANG_YU_ERROR_CODE_118("118", "用户没有相应的发送权限"),
	LANG_YU_ERROR_CODE_119("119", "用户已过期"),
	LANG_YU_ERROR_CODE_120("120", "内容不在白名单中");
	
	public String code;
	public String msg;
	
	private LangYuRespCode(String code, String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public static LangYuRespCode getLangYuRespCode(String code) {
		if (null == code) {
			return null;
		}
		for (LangYuRespCode lyc : LangYuRespCode.values()) {
			if (lyc.code.equals(code)) {
				return lyc;
			}
		}
		return null;
	}
}

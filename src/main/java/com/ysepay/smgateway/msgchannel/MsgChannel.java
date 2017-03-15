package com.ysepay.smgateway.msgchannel;

import com.ysepay.smgateway.domain.ResponseObject;

/**
 * 短信通道接口
 * @author sunlei
 * @date 2017年2月27日
 */
public interface MsgChannel {

	/**
	 * 发送短信
	 * @return
	 * @throws Exception
	 */
	ResponseObject sendMsg() throws Exception;
}

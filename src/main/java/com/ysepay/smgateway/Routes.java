package com.ysepay.smgateway;

import io.netty.handler.codec.http.HttpMethod;

import org.restexpress.RestExpress;

import com.ysepay.smgateway.handler.DH3TMsgReplyHandler;
import com.ysepay.smgateway.handler.DH3TReportHandler;
import com.ysepay.smgateway.handler.LangYuMsgReplyHandler;
import com.ysepay.smgateway.handler.LangYuReportHandler;
import com.ysepay.smgateway.handler.OldShortMsgSendHandler;
import com.ysepay.smgateway.handler.ShortMsgSendHandler;

/**
 * 请求路由
 * @author sunlei
 * @date 2017年2月21日
 */
public abstract class Routes {
	
	public static void define(RestExpress server) {
		
		// 兼容旧的网关，发送短信路由
		server.uri("/smsgate/sendsms.do", new OldShortMsgSendHandler()).action(
				"sendShortMsg", HttpMethod.POST).action("sendShortMsg", HttpMethod.GET);
		
		// 发送短信路由
		server.uri("/smgateway/sendsm", new ShortMsgSendHandler()).action(
				"sendShortMsg", HttpMethod.POST);
		
		// 朗宇状态报告推送
		server.uri("/smgateway/langyu/report", new LangYuReportHandler()).action(
				"report", HttpMethod.GET).noSerialization();

		// 朗宇短信回复
		server.uri("/smgateway/langyu/deliver", new LangYuMsgReplyHandler()).action(
				"deliver", HttpMethod.GET).noSerialization();

		// 大汉三通状态报告推送
		server.uri("/smgateway/dh3t/report", new DH3TReportHandler()).action(
				"report", HttpMethod.POST);
		
		// 大汉三通短信回复
		server.uri("/smgateway/dh3t/deliver", new DH3TMsgReplyHandler()).action(
				"deliver", HttpMethod.POST);
		
	}
}

package com.ysepay.smgateway.router;

import com.ysepay.smgateway.dao.po.Merchant_channel_routePO;
import com.ysepay.smgateway.domain.YSMsgPO;
import com.ysepay.smgateway.enums.MsgChannelCode;
import com.ysepay.smgateway.enums.MsgSign;
import com.ysepay.smgateway.msgchannel.DH3TMsgChannel;
import com.ysepay.smgateway.msgchannel.LangYuMsgChannel;
import com.ysepay.smgateway.msgchannel.MengWangMsgChannel;
import com.ysepay.smgateway.msgchannel.MsgChannel;
import com.ysepay.smgateway.service.MerchantChannelRouteService;
import com.ysepay.smgateway.utils.SpringContextUtil;

/**
 * 短信通道路由器
 * @author sunlei
 * @date 2017年2月27日
 */
public class CommonMsgRouter {
	
	private MerchantChannelRouteService merchantChannelRouteService = null;
	
	public CommonMsgRouter() {
		try {
			merchantChannelRouteService = (MerchantChannelRouteService)SpringContextUtil.getBean("merchantChannelRouteService");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取短信通道
	 * @param msgPO
	 * @return
	 */
	public MsgChannel getMsgChannel (YSMsgPO msgPO) {
		// 根据appId获取渠道编码
		Merchant_channel_routePO channelRoute = merchantChannelRouteService.getDefaultChannel(msgPO.getAppId(), msgPO.getMsgSign());
		
		MsgChannelCode msgChannelCode = MsgChannelCode.getMsgChannelCode(channelRoute.getChannel_code());
		
		// 兼容旧的短信网关签名
		if (null == channelRoute.getApp_sign() || "".equals(channelRoute.getApp_sign())) {
			if (null != MsgSign.getMsgSign(msgPO.getMsgSign())) {
				channelRoute.setApp_sign(MsgSign.getMsgSign(msgPO.getMsgSign()).msg);
			}
		}
		
		// 根据渠道编码获取渠道
		switch (msgChannelCode) {
		case MSG_LANG_YU:
			return new LangYuMsgChannel(msgPO, channelRoute);
			
		case MSG_DAHAN3TONG:
			return new DH3TMsgChannel(msgPO, channelRoute);
			
		case MSG_MENGWANG:
			return new MengWangMsgChannel(msgPO, channelRoute);

		default:
			break;
		}
		
		return null;
	}
	
}

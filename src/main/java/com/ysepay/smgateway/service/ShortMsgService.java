package com.ysepay.smgateway.service;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysepay.smgateway.dao.mapper.Merchant_appMapper;
import com.ysepay.smgateway.dao.mapper.Msg_channelMapper;
import com.ysepay.smgateway.dao.mapper.Short_msgMapper;
import com.ysepay.smgateway.dao.po.Merchant_appPO;
import com.ysepay.smgateway.dao.po.Merchant_channel_routePO;
import com.ysepay.smgateway.dao.po.Msg_channelPO;
import com.ysepay.smgateway.dao.po.Short_msgPO;
import com.ysepay.smgateway.domain.YSMsgPO;
import com.ysepay.smgateway.enums.SendMsgStatus;
import com.ysepay.smgateway.exception.SMGatewayException;
import com.ysepay.smgateway.utils.Constants;
import com.ysepay.smgateway.utils.SpringContextUtil;

/**
 * 短信流水服务层
 * @author sunlei
 * @date 2017年3月1日
 */
@Service("shortMsgService")
public class ShortMsgService {

	private static final Logger LOGGER = Logger.getLogger(ShortMsgService.class);
	
	@Autowired
	private Short_msgMapper shortMsgFlowDao;

	@Autowired
	private Merchant_appMapper merchantAppDao;

	@Autowired
	private Msg_channelMapper msgChannelDao;
	
	/**
	 * 根据应用id查询商户应用信息
	 * @param msgPO
	 * @return
	 */
	@Transactional
	public String saveShortMsg(YSMsgPO msgPO) throws SMGatewayException {
		LOGGER.info("[ShortMsgService.saveShortMsg] [begin]" );
		// 获取应用信息
		Merchant_appPO merchantApp = merchantAppDao.selectByAppId(msgPO.getAppId());
		if (null == merchantApp) {
			SMGatewayException.raise("4002");
		}
		
		// 获取短信通道信息
		MerchantChannelRouteService merchantChannelRouteService = null;
		try {
			merchantChannelRouteService = (MerchantChannelRouteService)SpringContextUtil.getBean("merchantChannelRouteService");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info(e, e);
			SMGatewayException.raise("1001");
		}
		Merchant_channel_routePO channelRoute = merchantChannelRouteService.getDefaultChannel(msgPO.getAppId(), msgPO.getMsgSign());
		Msg_channelPO msgChannel = msgChannelDao.selectByCode(channelRoute.getChannel_code());
		if (null == msgChannel) {
			SMGatewayException.raise("5001");
		}
		if (!Constants.NORMAL.equals(msgChannel.getChannel_status())) {
			SMGatewayException.raise("5003");
		}
		
		String gateway_msgid = shortMsgFlowDao.getGatewayMsgId();
		Short_msgPO shortMsg = new Short_msgPO();
		shortMsg.setMerchant_no(merchantApp.getMerchant_no());
		shortMsg.setApp_id(msgPO.getAppId());
		shortMsg.setSrc(msgPO.getAppId());
		shortMsg.setMessagesign(channelRoute.getType());
		shortMsg.setApp_sign(channelRoute.getApp_sign());
		shortMsg.setChannel_account(channelRoute.getChannel_account());
		shortMsg.setGateway_msgid(gateway_msgid);
		shortMsg.setMsgid(msgPO.getMsgId());
		shortMsg.setChannel(msgChannel.getChannel_code());
		shortMsg.setChannel_account(channelRoute.getChannel_account());
		shortMsg.setCreatetime(new Date());
		shortMsg.setMessage(msgPO.getMsg());
		shortMsg.setMobile(msgPO.getMobile());
		shortMsg.setMsg_count(getMsgCount(msgPO));
		shortMsg.setContent_count(msgPO.getMsg().length());
		shortMsg.setStatus(SendMsgStatus.INIT.getStatus());
		try {
			shortMsgFlowDao.insert(shortMsg);
		} catch(DuplicateKeyException e) {
			LOGGER.error(e, e);
			SMGatewayException.raise("1002");
		}
		LOGGER.info("[ShortMsgService.saveShortMsg] [end] gateway_msgid = " + gateway_msgid );
		return gateway_msgid;
	}
	
	/**
	 * 计算短信条数
	 * @param msgPO
	 * @return
	 */
	private int getMsgCount(YSMsgPO msgPO) {
		int num = msgPO.getMobile().split(",").length;
		int count = (msgPO.getMsg().length() / 70 ) + 1;
		return num * count;
	}
	
	@Transactional
	public int updateShortMsg(Short_msgPO shortMsg) {
		return shortMsgFlowDao.updateByGatewayMsgid(shortMsg);
	}
	
	@Transactional
	public int updateShortMsgByMWMsgId(Short_msgPO shortMsg) {
		return shortMsgFlowDao.updateByMWMsgid(shortMsg);
	}
	
}

package com.ysepay.smgateway.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysepay.smgateway.dao.mapper.Msg_channel_accountMapper;
import com.ysepay.smgateway.dao.po.Msg_channel_accountPO;
import com.ysepay.smgateway.exception.SMGatewayException;

/**
 * 渠道账号服务层
 * @author sunlei
 * @date 2017年3月2日
 */
@Service("msgChannelAccountService")
public class MsgChannelAccountService {

	private static final Logger LOGGER = Logger.getLogger(MsgChannelAccountService.class);

	@Autowired
	private Msg_channel_accountMapper msgChannelAccountDao;
	
	/**
	 * 获取渠道账号信息
	 * @param channelCode 渠道编码
	 * @param channelAccount 渠道账号
	 * @return
	 * @throws SMGatewayException
	 */
	@Transactional(readOnly = true)
	public Msg_channel_accountPO getMsgChannelAccount(String channelCode, String channelAccount) throws SMGatewayException {
		LOGGER.info("[MsgChannelAccountService.getMsgChannelAccount] channelCode = "
				+ channelCode + ", channelAccount = " + channelAccount);
		Msg_channel_accountPO mca = msgChannelAccountDao.selectByCodeAndAccount(channelCode, channelAccount);
		return mca;
	}
	
}

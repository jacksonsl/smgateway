package com.ysepay.smgateway.msgchannel;

import java.util.Date;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.ysepay.smgateway.dao.po.Merchant_channel_routePO;
import com.ysepay.smgateway.dao.po.Msg_channel_accountPO;
import com.ysepay.smgateway.dao.po.Short_msgPO;
import com.ysepay.smgateway.domain.ResponseObject;
import com.ysepay.smgateway.domain.YSMsgPO;
import com.ysepay.smgateway.exception.SMGatewayException;
import com.ysepay.smgateway.service.MerchantAppService;
import com.ysepay.smgateway.service.MsgChannelAccountService;
import com.ysepay.smgateway.service.ShortMsgService;
import com.ysepay.smgateway.utils.SpringContextUtil;

/**
 * 短信通道抽象模板
 * @author sunlei
 * @date 2017年2月21日
 * @param <T>
 */
public abstract class AbstractMsgChannel<T> implements MsgChannel {
	
	private static final Logger LOGGER = Logger.getLogger(AbstractMsgChannel.class);
	
	protected static final Gson gson = new Gson();
	
	protected YSMsgPO msgPO = null;
	
	protected ShortMsgService shortMsgService = null;
	
	protected MsgChannelAccountService msgChannelAccountService = null;
	
	protected MerchantAppService merchantAppService = null;
	
	protected Merchant_channel_routePO channelRoute = null;
	
	protected Msg_channel_accountPO msgChannelAccount = null;
	
	protected AbstractMsgChannel(YSMsgPO msgPO, Merchant_channel_routePO channelRoute) {
		this.msgPO = msgPO;
		this.channelRoute = channelRoute;
		try {
			shortMsgService = (ShortMsgService)SpringContextUtil.getBean("shortMsgService");
			msgChannelAccountService = (MsgChannelAccountService)SpringContextUtil.getBean("msgChannelAccountService");
			merchantAppService = (MerchantAppService)SpringContextUtil.getBean("merchantAppService");
		} catch (Exception e) {
			LOGGER.error(e, e);
			SMGatewayException.raise("5002");
		}
	}

	/**
	 * 初始化
	 * @throws SMGatewayException
	 */
	public void init() throws SMGatewayException {
		// 获取渠道账号信息
		msgChannelAccount = msgChannelAccountService.getMsgChannelAccount(channelRoute.getChannel_code(), 
				channelRoute.getChannel_account());
		if (null == msgChannelAccount) {
			SMGatewayException.raise("5004");
		}
	}
	
	/**
	 * 获取实体对象
	 * @return
	 * @throws SMGatewayException
	 */
	public abstract T getEntity() throws SMGatewayException;
	
	/**
	 * 校验参数
	 * @throws SMGatewayException
	 */
	public abstract void check(T entity) throws SMGatewayException;
	
	/**
	 * 业务处理
	 * @param entity
	 * @param msgId
	 * @return
	 * @throws Exception
	 */
	public abstract ResponseObject doBusiness(T entity, String msgId) throws Exception;
	
	/**
	 * 发送短信
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResponseObject sendMsg() throws Exception {
		
		// 初始化参数
		init();
		
		// 封装通道实体对象
		T entity = getEntity();
		
		// 校验通道参数
		check(entity);
		
		// 记录短信流水
		String msgId = saveShortMsgFlow(msgPO);
		
		// 处理业务
		return doBusiness(entity, msgId);
		
	}
	
	/**
	 * 更新短信发送到渠道的时间
	 * @param gateway_msgid
	 */
	protected void updateShortMsgSendTime(String gateway_msgid) {
		Short_msgPO shortMsg = new Short_msgPO();
		shortMsg.setGateway_msgid(gateway_msgid);
		shortMsg.setSend_time(new Date());
		shortMsgService.updateShortMsg(shortMsg);
	}
	
	/**
	 * 保存短信发送流水
	 * @param msgPO
	 * @return
	 * @throws Exception
	 */
	private String saveShortMsgFlow(YSMsgPO msgPO) throws Exception {
		String gateway_msgid = shortMsgService.saveShortMsg(msgPO);
		return gateway_msgid;
	}

}

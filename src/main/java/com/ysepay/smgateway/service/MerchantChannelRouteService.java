package com.ysepay.smgateway.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysepay.smgateway.dao.mapper.Merchant_channel_routeMapper;
import com.ysepay.smgateway.dao.po.Merchant_channel_routePO;
import com.ysepay.smgateway.exception.SMGatewayException;

/**
 * 商户渠道路由服务层
 * @author sunlei
 * @date 2017年3月1日
 */
@Service("merchantChannelRouteService")
public class MerchantChannelRouteService {

	private static final Logger LOGGER = Logger.getLogger(MerchantChannelRouteService.class);
	
	@Autowired
	private Merchant_channel_routeMapper merchantChannelRouteDao;
	
	/**
	 * 根据应用id获取渠道
	 * @param appId
	 * @param type
	 * @return
	 */
	@Transactional(readOnly = true)
	public Merchant_channel_routePO getDefaultChannel(String appId, String type) {
		LOGGER.info("[MerchantChannelRouteService.getDefaultChannel] appId="+appId + ", type = " + type);
		List<Merchant_channel_routePO> merchantChannelRoute = null;
		if (null != type && !"".equals(type)) {// 获取通配所有签名的路由
			merchantChannelRoute = merchantChannelRouteDao.selectByAppId(appId, type);
		} else {
			merchantChannelRoute = merchantChannelRouteDao.selectByAppId(appId, null);
		}
		if (null == merchantChannelRoute || 0 == merchantChannelRoute.size()) {
			merchantChannelRoute = merchantChannelRouteDao.selectByAppId(appId, null);
		}
		if (null == merchantChannelRoute || 0 == merchantChannelRoute.size()) {
			SMGatewayException.raise("3001");
		}
		Merchant_channel_routePO channelRoute = merchantChannelRoute.get(0);
		LOGGER.info("[MerchantChannelRouteService.getDefaultChannel] channelCode="+channelRoute.getChannel_code());
		return channelRoute;
		/*// 只有一个通道时，直接返回
		if (1 == merchantChannelRoute.size()) {
			return merchantChannelRoute.get(0).getChannel_code();
		} else {
			// 如果两个优先级不一样则返回最大的
			if (0 != merchantChannelRoute.get(0).getPriority().compareTo(merchantChannelRoute.get(0).getPriority())) {
				return merchantChannelRoute.get(0).getChannel_code();
			}
		}
		return null;*/
	}
	
}

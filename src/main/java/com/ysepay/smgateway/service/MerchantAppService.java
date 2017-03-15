package com.ysepay.smgateway.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysepay.smgateway.dao.mapper.Merchant_appMapper;
import com.ysepay.smgateway.dao.po.Merchant_appPO;

/**
 * 商户应用服务层
 * @author sunlei
 * @date 2017年3月1日
 */
@Service("merchantAppService")
public class MerchantAppService {

	private static final Logger LOGGER = Logger.getLogger(MerchantAppService.class);
	
	@Autowired
	private Merchant_appMapper merchantAppDao;
	
	/**
	 * 根据应用id查询商户应用信息
	 * @param appId
	 * @return
	 */
	@Transactional(readOnly = true)
	public Merchant_appPO queryMerchantApp(String appId) {
		LOGGER.info("[MerchantAppService.queryMerchantApp] appId="+appId);
		Merchant_appPO merchantApp = merchantAppDao.selectByAppId(appId);
		return merchantApp;
	}
	
}

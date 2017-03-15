package com.ysepay.smgateway.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.ysepay.smgateway.dao.po.Merchant_appPO;

/**
 * <b>商户应用[merchant_app]数据访问接口</b>
 * 
 * 
 * @author sunlei
 * @date 2017-02-27 18:01:10
 */
public interface Merchant_appMapper {

	/**
	 * 根据appId查询并返回数据持久化对象
	 * 
	 * @return Merchant_appPO
	 */
	Merchant_appPO selectByAppId(@Param("appId")String appId);
}

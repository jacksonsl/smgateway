package com.ysepay.smgateway.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ysepay.smgateway.dao.po.Merchant_channel_routePO;

/**
 * <b>商户渠道路由[merchant_channel_route]数据访问接口</b>
 * 
 * @author sunlei
 * @date 2017-02-27 15:21:54
 */
public interface Merchant_channel_routeMapper {

	/**
	 * 根据appId查询并返回数据持久化对象
	 * @param appId
	 * @param type
	 * @return
	 */
	List<Merchant_channel_routePO> selectByAppId(@Param("appId")String appId, @Param("type")String type);
	
}

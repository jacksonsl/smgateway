package com.ysepay.smgateway.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.ysepay.smgateway.dao.po.Msg_channelPO;

/**
 * <b>短信渠道[msg_channel]数据访问接口</b>
 * 
 * @author sunlei
 * @date 2017-02-21 14:17:12
 */
public interface Msg_channelMapper {

	/**
	 * 根据编码查询并返回数据持久化对象
	 * 
	 * @return Msg_channelPO
	 */
	Msg_channelPO selectByCode(@Param("channelCode")String channelCode);

}

package com.ysepay.smgateway.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.ysepay.smgateway.dao.po.Msg_channel_accountPO;

/**
 * <b>短信渠道账号[msg_channel_account]数据访问接口</b>
 * 
 * @author sunlei
 * @date 2017-03-02 11:52:26
 */
public interface Msg_channel_accountMapper {

	/**
	 * 查询并返回数据持久化对象
	 * 
	 * @return Msg_channel_accountPO
	 */
	Msg_channel_accountPO selectByCodeAndAccount(@Param("channelCode")String channelCode, 
			@Param("channelAccount")String channelAccount);
	
}

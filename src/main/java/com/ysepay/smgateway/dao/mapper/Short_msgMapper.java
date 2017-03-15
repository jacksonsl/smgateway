package com.ysepay.smgateway.dao.mapper;

import com.ysepay.smgateway.dao.po.Short_msgPO;

/**
 * <b>短信流水表[short_msg_flow]数据访问接口</b>
 * 
 * @author sunlei
 * @date 2017-03-01 15:08:36
 */
public interface Short_msgMapper {

	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param short_msg_flowPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insert(Short_msgPO short_msg_flowPO);
	
	/**
	 * 获取gateway_msgid
	 * @return
	 */
	String getGatewayMsgId();
	
	/**
	 * 根据msgid修改数据持久化对象
	 * @param short_msg_flowPO
	 * @return
	 */
	int updateByGatewayMsgid(Short_msgPO short_msg_flowPO);

	/**
	 * 根据mw_msgid修改数据持久化对象
	 * @param short_msg_flowPO
	 * @return
	 */
	int updateByMWMsgid(Short_msgPO short_msg_flowPO);
}

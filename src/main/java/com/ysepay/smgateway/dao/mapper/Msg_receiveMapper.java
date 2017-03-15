package com.ysepay.smgateway.dao.mapper;

import com.ysepay.smgateway.dao.po.Msg_receivePO;

/**
 * <b>短信接收[msg_receive]数据访问接口</b>
 * 
 * @author sunlei
 * @date 2017-02-21 14:17:12
 */
public interface Msg_receiveMapper {

	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param msg_receivePO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insert(Msg_receivePO msg_receivePO);
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param msg_receivePO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insertAll(Msg_receivePO msg_receivePO);

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param msg_receivePO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	int updateByKey(Msg_receivePO msg_receivePO);

	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return Msg_receivePO
	 */
	Msg_receivePO selectByKey();
	
}

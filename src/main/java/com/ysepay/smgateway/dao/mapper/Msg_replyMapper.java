package com.ysepay.smgateway.dao.mapper;

import com.ysepay.smgateway.dao.po.Msg_replyPO;

/**
 * <b>短信回复[msg_reply]数据访问接口</b>
 * 
 * @author sunlei
 * @date 2017-03-03 11:16:39
 */
public interface Msg_replyMapper {

	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param msg_replyPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insert(Msg_replyPO msg_replyPO);
	
}

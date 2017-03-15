package com.ysepay.smgateway.dao.mapper;

import com.ysepay.smgateway.dao.po.Merchant_infoPO;

/**
 * <b>商户信息[merchant_info]数据访问接口</b>
 * 
 * @author sunlei
 * @date 2017-02-21 14:17:12
 */
public interface Merchant_infoMapper {

	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param merchant_infoPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insert(Merchant_infoPO merchant_infoPO);
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param merchant_infoPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insertAll(Merchant_infoPO merchant_infoPO);

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param merchant_infoPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	int updateByKey(Merchant_infoPO merchant_infoPO);

	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return Merchant_infoPO
	 */
	Merchant_infoPO selectByKey();
	
}

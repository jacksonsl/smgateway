package com.ysepay.smgateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysepay.smgateway.dao.mapper.Msg_replyMapper;
import com.ysepay.smgateway.dao.po.Msg_replyPO;
import com.ysepay.smgateway.exception.SMGatewayException;

/**
 * 短信回复服务层
 * @author sunlei
 * @date 2017年3月3日
 */
@Service("msgReplyService")
public class MsgReplyService {

	@Autowired
	private Msg_replyMapper msgReplyDao;
	
	/**
	 * 保存短信回复
	 * @param msgReply
	 * @return
	 * @throws SMGatewayException
	 */
	@Transactional
	public int saveMsgReply(Msg_replyPO msgReply) throws SMGatewayException {
		return msgReplyDao.insert(msgReply);
	}
	
}

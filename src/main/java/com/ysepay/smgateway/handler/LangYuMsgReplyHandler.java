package com.ysepay.smgateway.handler;

import java.util.Date;

import org.apache.log4j.Logger;
import org.restexpress.Request;
import org.restexpress.Response;

import com.google.gson.Gson;
import com.ysepay.smgateway.dao.po.Msg_replyPO;
import com.ysepay.smgateway.domain.LangYuMsgReplyPO;
import com.ysepay.smgateway.enums.MsgChannelCode;
import com.ysepay.smgateway.exception.SMGatewayException;
import com.ysepay.smgateway.service.MsgReplyService;
import com.ysepay.smgateway.utils.Constants;
import com.ysepay.smgateway.utils.SpringContextUtil;

/**
 * 朗宇短信回复处理
 * @author sunlei
 * @date 2017年3月3日
 */
public class LangYuMsgReplyHandler {

	private static final Logger LOGGER = Logger.getLogger(LangYuMsgReplyHandler.class);
	private Gson gson = new Gson();

	private MsgReplyService msgReplyService = null;

	/**
	 * 发送短信
	 * @param request
	 * @param response
	 * @return
	 */
	public Object deliver(Request request, Response response) {
		try {
			// 解析请求对象
			LangYuMsgReplyPO lyReply = getEntity(request);
			
			msgReplyService = (MsgReplyService)SpringContextUtil.getBean("msgReplyService");

			Msg_replyPO msgReply = new Msg_replyPO();
			msgReply.setMobile_no(lyReply.getMobile());
			msgReply.setReceive_time(new Date());
			msgReply.setReply_content(lyReply.getMsg());
			msgReply.setChannel_code(MsgChannelCode.MSG_LANG_YU.code);
			msgReply.setDeliver_msg(gson.toJson(lyReply));
//					msgReply.setApp_id(app_id);
//					msgReply.setChannel_msg_id(channel_msg_id);
//					msgReply.setMerchant_no(merchant_no);
//					msgReply.setMsgid(msgid);
			msgReplyService.saveMsgReply(msgReply);
		} catch (SMGatewayException e) {
			LOGGER.error(e, e);
			return Constants.FAIL;
		} catch (Exception e) {
			LOGGER.error(e, e);
			return Constants.FAIL;
		}
		return Constants.SUCCESS;
	}
	
	private LangYuMsgReplyPO getEntity(Request request) {
		LangYuMsgReplyPO lyReply = new LangYuMsgReplyPO();
		lyReply.setDestcode(request.getHeader("destcode"));
		lyReply.setEmshead(request.getHeader("emshead"));
		lyReply.setIsems(request.getHeader("isems"));
		lyReply.setMobile(request.getHeader("mobile"));
		lyReply.setMoTime(request.getHeader("moTime"));
		lyReply.setMsg(request.getHeader("msg"));
		lyReply.setPswd(request.getHeader("pswd"));
		lyReply.setReceiver(request.getHeader("receiver"));
		LOGGER.info("[DH3TMsgReplyHandler.getEntity] deliver = " + gson.toJson(lyReply));
		return lyReply;
	}
	
}

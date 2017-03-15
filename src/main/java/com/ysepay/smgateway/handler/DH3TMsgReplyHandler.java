package com.ysepay.smgateway.handler;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.restexpress.Request;
import org.restexpress.Response;

import com.google.gson.Gson;
import com.ysepay.smgateway.dao.po.Msg_replyPO;
import com.ysepay.smgateway.domain.DH3TMsgReplyPO;
import com.ysepay.smgateway.domain.ResponseObject;
import com.ysepay.smgateway.enums.MsgChannelCode;
import com.ysepay.smgateway.exception.SMGatewayException;
import com.ysepay.smgateway.service.MsgReplyService;
import com.ysepay.smgateway.utils.Constants;
import com.ysepay.smgateway.utils.SpringContextUtil;

/**
 * 大汉三通短信回复处理
 * @author sunlei
 * @date 2017年3月2日
 */
public class DH3TMsgReplyHandler {

	private static final Logger LOGGER = Logger.getLogger(DH3TMsgReplyHandler.class);
	private Gson gson = new Gson();

	private MsgReplyService msgReplyService = null;

	protected String requestBody;// 请求数据
	
	/**
	 * 发送短信
	 * @param request
	 * @param response
	 * @return
	 */
	public Object deliver(Request request, Response response) {
		ResponseObject responseObject = new ResponseObject();
		try {
			paraseRequestBody(request);
			
			// 解析请求对象
			DH3TMsgReplyPO dh3tReply = getEntity(request);
			
			msgReplyService = (MsgReplyService)SpringContextUtil.getBean("msgReplyService");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (null != dh3tReply && null != dh3tReply.getDelivers()) {
				for (int i=0;i<dh3tReply.getDelivers().size();i++) {
					Msg_replyPO msgReply = new Msg_replyPO();
					msgReply.setMobile_no(dh3tReply.getDelivers().get(i).getPhone());
					msgReply.setReply_content(dh3tReply.getDelivers().get(i).getContent());
					msgReply.setChannel_code(MsgChannelCode.MSG_DAHAN3TONG.code);
					msgReply.setDeliver_msg(gson.toJson(dh3tReply.getDelivers().get(i)));
//					msgReply.setApp_id(app_id);
//					msgReply.setChannel_msg_id(channel_msg_id);
//					msgReply.setMerchant_no(merchant_no);
//					msgReply.setMsgid(msgid);
					try {
						msgReply.setReceive_time(sdf.parse(dh3tReply.getDelivers().get(i).getDelivertime()));
					} catch(ParseException e) {
						msgReply.setReceive_time(new Date());
					}
					msgReplyService.saveMsgReply(msgReply);
				}
			}
			responseObject.setStatus(Constants.SUCCESS);
		} catch (SMGatewayException e) {
			LOGGER.error(e, e);
			responseObject.setStatus(Constants.FAIL);
		} catch (Exception e) {
			LOGGER.error(e, e);
			responseObject.setStatus(Constants.FAIL);
		}
		return responseObject;
	}
	
	private DH3TMsgReplyPO getEntity(Request request) {
		String deliver = getParameterByName("deliver");
		LOGGER.info("[DH3TMsgReplyHandler.getEntity] deliver = " + deliver);
		DH3TMsgReplyPO dh3tReply = gson.fromJson(deliver, DH3TMsgReplyPO.class);
		return dh3tReply;
	}
	
	/**
	 * 解析请求参数
	 * @param request
	 * @throws UnsupportedEncodingException
	 */
	private void paraseRequestBody(Request request) throws UnsupportedEncodingException{
		byte[] req = new byte[request.getBody().readableBytes()];
		request.getBody().readBytes(req);
		String body = new String(req,"UTF-8");
		body = URLDecoder.decode(body, "UTF-8");
		this.requestBody = body;
	}
	
	/**
	 * 获取请求参数
	 * @param paramName
	 * @return
	 */
	private String getParameterByName(String paramName) {
		if (null == this.requestBody) {
			return null;
		}
		String[] params = this.requestBody.split("&");
		for (String param : params) {
			if (paramName.equalsIgnoreCase(param.split("=", 2)[0])) {
				return param.split("=", 2)[1];
			}
		}
		return null;
	}
	
}

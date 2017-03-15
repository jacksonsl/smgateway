package com.ysepay.smgateway.handler;

import java.util.Date;

import org.apache.log4j.Logger;
import org.restexpress.Request;
import org.restexpress.Response;

import com.google.gson.Gson;
import com.ysepay.smgateway.dao.po.Short_msgPO;
import com.ysepay.smgateway.domain.LangYuReportPO;
import com.ysepay.smgateway.enums.MsgChannelCode;
import com.ysepay.smgateway.enums.SendMsgStatus;
import com.ysepay.smgateway.exception.SMGatewayException;
import com.ysepay.smgateway.service.ShortMsgService;
import com.ysepay.smgateway.utils.Constants;
import com.ysepay.smgateway.utils.SpringContextUtil;

/**
 * 朗宇状态报告处理
 * @author sunlei
 * @date 2017年3月2日
 */
public class LangYuReportHandler {

	private static final Logger LOGGER = Logger.getLogger(LangYuReportHandler.class);
	private Gson gson = new Gson();

	private ShortMsgService shortMsgService = null;
	/**
	 * 发送短信
	 * @param request
	 * @param response
	 * @return
	 */
	public Object report(Request request, Response response) {
		try {
			// 解析请求对象
			LangYuReportPO lyReport = getEntity(request);
			LOGGER.info("[LangYuReportHandler.report] report = " + gson.toJson(lyReport));
			
			shortMsgService = (ShortMsgService)SpringContextUtil.getBean("shortMsgService");
			Short_msgPO shortMsg = new Short_msgPO();
			shortMsg.setMw_msgid(lyReport.getMsgid());
			shortMsg.setReceive_time(new Date());
			shortMsg.setDescs(gson.toJson(lyReport));
			shortMsg.setChannel(MsgChannelCode.MSG_LANG_YU.code);
			if (ReportStatus.DELIVRD.code.equals(lyReport.getStatus())) {
				shortMsg.setStatus(SendMsgStatus.SEND_SUCCESS.getStatus());
				shortMsg.setFetch_state("00");
			} else {
				shortMsg.setStatus(SendMsgStatus.SEND_FAIL.getStatus());
				shortMsg.setFetch_state("99");
			}
			shortMsgService.updateShortMsgByMWMsgId(shortMsg);

		} catch (SMGatewayException e) {
			LOGGER.error(e, e);
			return Constants.FAIL;
		} catch (Exception e) {
			LOGGER.error(e, e);
			return Constants.FAIL;
		}
		return Constants.SUCCESS;
	}
	
	private LangYuReportPO getEntity(Request request) {
		LangYuReportPO lyReport = new LangYuReportPO();
		lyReport.setMobile(request.getHeader("pswd"));
		lyReport.setMsgid(request.getHeader("msgid"));
		lyReport.setPswd(request.getHeader("pswd"));
		lyReport.setReceiver(request.getHeader("receiver"));
		lyReport.setReportTime(request.getHeader("reportTime"));
		lyReport.setStatus(request.getHeader("status"));
		return lyReport;
	}
	
	enum ReportStatus {
		DELIVRD("DELIVRD","发送成功"),
		MBBLACK("MBBLACK","黑名单号码"),
		NOROUTE("NOROUTE","无通道"),
		ROUTEERR("ROUTEERR","通道异常"),
		REJECT("REJECT","审核驳回"),
		DISTURB("DISTURB","手机号码发送次数过多"),
		EMSERR("EMSERR","长短信不完整"),
		SIGNERR("SIGNERR","签名错"),
		KEYWORD("KEYWORD","敏感词"),
		WHITESMS("WHITESMS","短信内容不在白名单中");

		public String code;
		public String desc;
		
		ReportStatus(String code, String desc){
			this.code = code;
			this.desc = desc;
		}
		
		public static ReportStatus getReportStatus(String code) {
			if (null == code) {
				return null;
			}
			for (ReportStatus lyc : ReportStatus.values()) {
				if (lyc.code.equals(code)) {
					return lyc;
				}
			}
			return null;
		}
	}
	
}

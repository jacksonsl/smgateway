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
import com.ysepay.smgateway.dao.po.Short_msgPO;
import com.ysepay.smgateway.domain.DH3TReportPO;
import com.ysepay.smgateway.domain.ResponseObject;
import com.ysepay.smgateway.enums.MsgChannelCode;
import com.ysepay.smgateway.enums.SendMsgStatus;
import com.ysepay.smgateway.exception.SMGatewayException;
import com.ysepay.smgateway.service.ShortMsgService;
import com.ysepay.smgateway.utils.Constants;
import com.ysepay.smgateway.utils.SpringContextUtil;

/**
 * 大汉三通状态报告处理
 * @author sunlei
 * @date 2017年3月2日
 */
public class DH3TReportHandler {

	private static final Logger LOGGER = Logger.getLogger(DH3TReportHandler.class);
	private Gson gson = new Gson();

	private ShortMsgService shortMsgService = null;

	protected String requestBody;// 请求数据
	
	/**
	 * 发送短信
	 * @param request
	 * @param response
	 * @return
	 */
	public Object report(Request request, Response response) {
		ResponseObject responseObject = new ResponseObject();
		try {
			paraseRequestBody(request);
			
			// 解析请求对象
			DH3TReportPO dh3tReport = getEntity(request);
			
			shortMsgService = (ShortMsgService)SpringContextUtil.getBean("shortMsgService");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (null != dh3tReport && null != dh3tReport.getReports()) {
				for (int i=0;i<dh3tReport.getReports().size();i++) {
					Short_msgPO shortMsg = new Short_msgPO();
					shortMsg.setMw_msgid(dh3tReport.getReports().get(i).getMsgid());
					try {
						shortMsg.setReceive_time(sdf.parse(dh3tReport.getReports().get(i).getTime()));
					} catch(ParseException e) {
						shortMsg.setReceive_time(new Date());
					}
					shortMsg.setDescs(gson.toJson(dh3tReport.getReports().get(i)));
					shortMsg.setChannel(MsgChannelCode.MSG_DAHAN3TONG.code);
					if ("0".equals(dh3tReport.getReports().get(i).getStatus())) {
						shortMsg.setStatus(SendMsgStatus.SEND_SUCCESS.getStatus());
						shortMsg.setFetch_state("00");
					} else {
						shortMsg.setStatus(SendMsgStatus.SEND_FAIL.getStatus());
						shortMsg.setFetch_state("99");
					}
					shortMsgService.updateShortMsgByMWMsgId(shortMsg);
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
	
	private DH3TReportPO getEntity(Request request) {
		String report = getParameterByName("report");
		LOGGER.info("[DH3TReportHandler.getEntity] report = " + report);
		DH3TReportPO dh3tReport = gson.fromJson(report, DH3TReportPO.class);
		return dh3tReport;
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

package com.ysepay.smgateway.msgchannel;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.ysepay.smgateway.dao.po.Merchant_channel_routePO;
import com.ysepay.smgateway.dao.po.Short_msgPO;
import com.ysepay.smgateway.domain.DH3TMsgPO;
import com.ysepay.smgateway.domain.ResponseObject;
import com.ysepay.smgateway.domain.YSMsgPO;
import com.ysepay.smgateway.enums.DH3TRespCode;
import com.ysepay.smgateway.enums.SendMsgStatus;
import com.ysepay.smgateway.exception.SMGatewayException;
import com.ysepay.smgateway.utils.MD5Util;

/**
 * 大汉三通短信通道
 * @author sunlei
 * @date 2017年3月1日
 */
public class DH3TMsgChannel extends AbstractMsgChannel<DH3TMsgPO> {

	private static final Logger LOGGER = Logger.getLogger(DH3TMsgChannel.class);
	
	public DH3TMsgChannel(YSMsgPO msgPO, Merchant_channel_routePO channelRoute) {
		super(msgPO, channelRoute);
	}
	
	@Override
	public void init() throws SMGatewayException {
		super.init();
	}

	@Override
	public DH3TMsgPO getEntity() throws SMGatewayException {
		if (null == msgPO) {
			SMGatewayException.raise("0");
		}
		DH3TMsgPO dh3tMsgPO = new DH3TMsgPO();
		dh3tMsgPO.setAccount(msgChannelAccount.getAccount());
		dh3tMsgPO.setPassword(MD5Util.MD5Encode(msgChannelAccount.getPwd()));
//		dh3tMsgPO.setMsgid(msgid);
		dh3tMsgPO.setPhones(msgPO.getMobile());
		dh3tMsgPO.setSendtime("");
		dh3tMsgPO.setContent(msgPO.getMsg());
		dh3tMsgPO.setSubcode("");
		return dh3tMsgPO;
	}

	@Override
	public void check(DH3TMsgPO entity) throws SMGatewayException {
		LOGGER.info("[DH3TMsgChannel.check] sign = " + channelRoute.getApp_sign());
		entity.setSign(channelRoute.getApp_sign());
		/*if (!msgPO.getMsg().startsWith(channelRoute.getApp_sign())) {
			SMGatewayException.raise("4003");
		}*/
		// 走大汉三通渠道，要去掉短信签名
		entity.setContent(msgPO.getMsg().replace(channelRoute.getApp_sign(), ""));
	}

	@Override
	public ResponseObject doBusiness(DH3TMsgPO entity, String gateway_msgid)
			throws Exception {
		String data = gson.toJson(entity);
		LOGGER.info("[DH3TMsgChannel.doBusiness] request data = " + data);
		HttpClient httpclient = new DefaultHttpClient();
		// 设置通信协议版本
		httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		httpclient.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "UTF-8");
		// 设置请求URL
		LOGGER.info("[DH3TMsgChannel.doBusiness] URL = " + msgChannelAccount.getUrl());
		URI uri = new URI(msgChannelAccount.getUrl());
		// 使用POST提交
		HttpPost httpPost = new HttpPost(uri);
		// 设置请求参数
	    StringEntity strEntity = new StringEntity(data, "UTF-8");
		httpPost.setEntity(strEntity);
		if (null != strEntity) {
			strEntity = null;
		}

		// 记录发送到通道的时间
		updateShortMsgSendTime(gateway_msgid);
		
		LOGGER.info("[DH3TMsgChannel.doBusiness] 提交请求 = " + httpPost.getRequestLine());
		// 发送请求
		HttpResponse response = httpclient.execute(httpPost);
		// 获取响应结果
		HttpEntity resEntity = response.getEntity();
		LOGGER.info("[DH3TMsgChannel.doBusiness] 请求结果 = " + response.getStatusLine());
		// 解析响应结果
		String json = "";
		DH3TResponse result = null;
		if (resEntity != null) {
			json = EntityUtils.toString(resEntity, "utf-8");
			LOGGER.info("[DH3TMsgChannel.doBusiness] 请求结果json = " + json);
			result = gson.fromJson(json, DH3TResponse.class);
		}
		httpclient.getConnectionManager().shutdown();
		
		// 更新短信流水状态
		updateShortMsgStatus(result, gateway_msgid);
		
		ResponseObject ro = getResult(result);
		ro.setMsgId(gateway_msgid);
		LOGGER.info("[DH3TMsgChannel.doBusiness] [end]");
		return ro;
	}
	
	/**
	 * 修改短信发送状态
	 * @param result
	 * @param gateway_msgid
	 */
	private void updateShortMsgStatus(DH3TResponse result, String gateway_msgid) {
		Short_msgPO shortMsg = new Short_msgPO();
		shortMsg.setGateway_msgid(gateway_msgid);
		if (DH3TRespCode.DH3T_ERROR_CODE_0.code.equals(result.getResult())) {// 申请成功
			shortMsg.setStatus(SendMsgStatus.APPLY_SUCCESS.getStatus());
		} else {// 申请失败
			shortMsg.setStatus(SendMsgStatus.APPLY_FAIL.getStatus());
		}
		shortMsg.setDescs(result.getDesc());
		shortMsg.setMw_msgid(result.getMsgid());
		shortMsgService.updateShortMsg(shortMsg);
	}
	
	private ResponseObject getResult(DH3TResponse result) {
		ResponseObject respResult = new ResponseObject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String respTime = sdf.format(new Date());
		if (null != result) {
			respResult.setCode("00");
			respResult.setMsg(result.getDesc());
			respResult.setResptime(respTime);
			respResult.setStatus("1");
		} else {
			respResult.setCode("99");
			respResult.setMsg("发送失败");
			respResult.setResptime(respTime);
			respResult.setStatus("0");
		}
		return respResult;
	}
	
	/**
	 * 大汉三通响应对象
	 * @author sunlei
	 * @date 2017年3月1日
	 */
	class DH3TResponse {
		private String msgid;// 该批短信编号
		
		private String result;// 该批短信提交结果；说明请参照：5.1.提交响应错误码
		
		private String desc;// 状态描述
		
		private String failPhones;// 如果提交的号码中含有错误（格式）号码将在此显示

		public String getMsgid() {
			return msgid;
		}

		public void setMsgid(String msgid) {
			this.msgid = msgid;
		}

		public String getResult() {
			return result;
		}

		public void setResult(String result) {
			this.result = result;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public String getFailPhones() {
			return failPhones;
		}

		public void setFailPhones(String failPhones) {
			this.failPhones = failPhones;
		}
	}

}

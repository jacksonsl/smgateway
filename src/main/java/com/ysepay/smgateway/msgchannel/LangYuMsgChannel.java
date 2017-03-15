package com.ysepay.smgateway.msgchannel;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.ysepay.smgateway.dao.po.Merchant_channel_routePO;
import com.ysepay.smgateway.dao.po.Short_msgPO;
import com.ysepay.smgateway.domain.LangYuMsgPO;
import com.ysepay.smgateway.domain.ResponseObject;
import com.ysepay.smgateway.domain.YSMsgPO;
import com.ysepay.smgateway.enums.LangYuRespCode;
import com.ysepay.smgateway.enums.SendMsgStatus;
import com.ysepay.smgateway.exception.SMGatewayException;

/**
 * 朗宇发送短信通道
 * @author sunlei
 * @date 2017年2月21日
 */
public class LangYuMsgChannel extends AbstractMsgChannel<LangYuMsgPO> {
	
	private static final Logger LOGGER = Logger.getLogger(LangYuMsgChannel.class);

	public LangYuMsgChannel(YSMsgPO msgPO, Merchant_channel_routePO channelRoute) {
		super(msgPO, channelRoute);
	}

	@Override
	public void init() throws SMGatewayException {
		super.init();
	}

	@Override
	public LangYuMsgPO getEntity() throws SMGatewayException {
		if (null == msgPO) {
			SMGatewayException.raise("");
		}
		LangYuMsgPO langYuMsgPO = new LangYuMsgPO();
		langYuMsgPO.setAccount(msgChannelAccount.getAccount());
		langYuMsgPO.setPswd(msgChannelAccount.getPwd());
		langYuMsgPO.setMobile(msgPO.getMobile());
		langYuMsgPO.setMsg(msgPO.getMsg());
		langYuMsgPO.setNeedstatus("true");
//		langYuMsgPO.setProduct(product);
//		langYuMsgPO.setExtno(extno);
		return langYuMsgPO;
	}

	@Override
	public void check(LangYuMsgPO entity) throws SMGatewayException {
		LOGGER.info("[LangYuMsgChannel.check] sign = " + channelRoute.getApp_sign());
		if (null != channelRoute.getApp_sign()) {
			if (!msgPO.getMsg().startsWith(channelRoute.getApp_sign()) || 
					!msgPO.getMsg().endsWith(channelRoute.getApp_sign())) {
				entity.setMsg(channelRoute.getApp_sign() + msgPO.getMsg());
			}
		}
	}

	/**
	 * 封装请求对象
	 * @param entity
	 * @return
	 * @throws SMGatewayException
	 */
	private List<NameValuePair> getHttpParams(LangYuMsgPO entity)
			throws SMGatewayException {
		LOGGER.info("[LangYuMsgChannel.getHttpParams] params = " + gson.toJson(entity));
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        formParams.add(new BasicNameValuePair("account", entity.getAccount()));
        formParams.add(new BasicNameValuePair("pswd", entity.getPswd()));
        formParams.add(new BasicNameValuePair("mobile", entity.getMobile()));
        formParams.add(new BasicNameValuePair("msg", entity.getMsg()));
        formParams.add(new BasicNameValuePair("needstatus", entity.getNeedstatus()));
        formParams.add(new BasicNameValuePair("product", entity.getProduct()));
        formParams.add(new BasicNameValuePair("extno", entity.getExtno()));
		return formParams;
	}

	@Override
	public ResponseObject doBusiness(LangYuMsgPO entity, String gateway_msgid) throws Exception {
		List<NameValuePair> formParams = getHttpParams(entity);
		LOGGER.info("[LangYuMsgChannel.doBusiness] formParams = " + gson.toJson(formParams));
		HttpClient httpclient = new DefaultHttpClient();
		// 设置通信协议版本
		httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		httpclient.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "UTF-8");
		// 设置请求URL
		LOGGER.info("[LangYuMsgChannel.doBusiness] URL = " + msgChannelAccount.getUrl());
		URI uri = new URI(msgChannelAccount.getUrl());
		// 使用POST提交
		HttpPost httpPost = new HttpPost(uri);
		// 设置请求参数
        UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
		httpPost.setEntity(uefEntity);
		if (null != uefEntity) {
			uefEntity = null;
		}

		// 记录发送到通道的时间
		updateShortMsgSendTime(gateway_msgid);

		LOGGER.info("[LangYuMsgChannel.doBusiness] 提交请求 = " + httpPost.getRequestLine());
		// 发送请求
		HttpResponse response = httpclient.execute(httpPost);
		// 获取响应结果
		HttpEntity resEntity = response.getEntity();
		LOGGER.info("[LangYuMsgChannel.doBusiness] 请求结果 = " + response.getStatusLine());
		// 解析响应结果
		String json = "";
		if (resEntity != null) {
			json = EntityUtils.toString(resEntity, "utf-8");
			LOGGER.info("[LangYuMsgChannel.doBusiness] 请求结果json = " + json);
		}
		httpclient.getConnectionManager().shutdown();

		ResponseObject ro = getResult(json);
		
		// 更新短信流水状态
		updateShortMsgStatus(ro, gateway_msgid);
		
		ro.setMsgId(gateway_msgid);
		LOGGER.info("[LangYuMsgChannel.doBusiness] [end]");
		return ro;
	}
	
	/**
	 * 修改短信发送状态
	 * @param result
	 * @param gateway_msgid
	 */
	private void updateShortMsgStatus(ResponseObject ro, String gateway_msgid) {
		Short_msgPO shortMsg = new Short_msgPO();
		shortMsg.setGateway_msgid(gateway_msgid);
		LOGGER.info("[LangYuMsgChannel.updateShortMsgStatus] respstatus = " + ro.getStatus());
		if (LangYuRespCode.LANG_YU_ERROR_CODE_0.code.equals(ro.getStatus())) {// 申请成功
			shortMsg.setStatus(SendMsgStatus.APPLY_SUCCESS.getStatus());
		} else {// 申请失败
			shortMsg.setStatus(SendMsgStatus.APPLY_FAIL.getStatus());
		}
		shortMsg.setDescs(ro.getMsg());
		shortMsg.setMw_msgid(ro.getMsgId());
		shortMsgService.updateShortMsg(shortMsg);
	}
	
	/**
	 * 解析同步结果
	 * @param result
	 * @return
	 */
	private ResponseObject getResult(String result) {
		ResponseObject ro = new ResponseObject();
		String[] rs = result.split("\n");
		String resptime = "";// 响应时间
		String respstatus = "";// 响应状态
		String msgid = "";// 消息id
		if (null != rs) {
			String[] resps =  rs[0].split(",");
			if (null != resps) {
				resptime = resps[0];
			}
			if (resps.length > 1) {
				respstatus = resps[1];
			}
			if (rs.length > 1) {
				msgid = rs[1];
			}
		}
		if ("0".equals(respstatus)) {
			ro.setCode("00");
			ro.setMsg("提交成功");
			ro.setMsgId(msgid);
		} else {
			ro.setCode("99");
			if (null != LangYuRespCode.getLangYuRespCode(respstatus)) {
				ro.setMsg(LangYuRespCode.getLangYuRespCode(respstatus).msg);
			}
		}
		ro.setStatus(respstatus);
		ro.setResptime(resptime);
		return ro;
	}

}

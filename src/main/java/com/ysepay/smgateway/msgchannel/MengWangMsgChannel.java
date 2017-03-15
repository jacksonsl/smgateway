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
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ysepay.smgateway.dao.po.Merchant_channel_routePO;
import com.ysepay.smgateway.dao.po.Short_msgPO;
import com.ysepay.smgateway.domain.MengWangMsgPO;
import com.ysepay.smgateway.domain.ResponseObject;
import com.ysepay.smgateway.domain.YSMsgPO;
import com.ysepay.smgateway.enums.MengWangRespCode;
import com.ysepay.smgateway.enums.SendMsgStatus;
import com.ysepay.smgateway.exception.SMGatewayException;

/**
 * 梦网发送短信通道
 * @author sunlei
 * @date 2017年2月21日
 */
public class MengWangMsgChannel extends AbstractMsgChannel<MengWangMsgPO> {
	
	private static final Logger LOGGER = Logger.getLogger(MengWangMsgChannel.class);

	public MengWangMsgChannel(YSMsgPO msgPO, Merchant_channel_routePO channelRoute) {
		super(msgPO, channelRoute);
	}

	@Override
	public void init() throws SMGatewayException {
		super.init();
	}

	@Override
	public MengWangMsgPO getEntity() throws SMGatewayException {
		if (null == msgPO) {
			SMGatewayException.raise("");
		}
		MengWangMsgPO mengWangMsgPO = new MengWangMsgPO();
		mengWangMsgPO.setUserId(msgChannelAccount.getAccount());
		mengWangMsgPO.setPassword(msgChannelAccount.getPwd());
		mengWangMsgPO.setiMobiCount(msgPO.getMobile().split(",").length+"");
		mengWangMsgPO.setPszMobis(msgPO.getMobile());
		mengWangMsgPO.setPszMsg(msgPO.getMsg());
		mengWangMsgPO.setPszSubPort("*");
		return mengWangMsgPO;
	}

	@Override
	public void check(MengWangMsgPO entity) throws SMGatewayException {
		/*LOGGER.info("[MengWangMsgChannel.check] sign = " + channelRoute.getApp_sign());
		if (!msgPO.getMsg().startsWith(channelRoute.getApp_sign())) {
			SMGatewayException.raise("4003");
		}
		// 走梦网通渠道，要去掉短信签名
		entity.setPszMsg(msgPO.getMsg().replace(channelRoute.getApp_sign(), ""));*/
	}

	/**
	 * 封装请求对象
	 * @param entity
	 * @return
	 * @throws SMGatewayException
	 */
	private List<NameValuePair> getHttpParams(MengWangMsgPO entity)
			throws SMGatewayException {
		LOGGER.info("[MengWangMsgChannel.getHttpParams] params = " + gson.toJson(entity));
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        formParams.add(new BasicNameValuePair("userId", entity.getUserId()));
        formParams.add(new BasicNameValuePair("password", entity.getPassword()));
        formParams.add(new BasicNameValuePair("pszMobis", entity.getPszMobis()));
        formParams.add(new BasicNameValuePair("pszMsg", entity.getPszMsg()));
        formParams.add(new BasicNameValuePair("iMobiCount", entity.getiMobiCount()));
        formParams.add(new BasicNameValuePair("pszSubPort", entity.getPszSubPort()));
        formParams.add(new BasicNameValuePair("MsgId", entity.getMsgId()));
		return formParams;
	}

	@Override
	public ResponseObject doBusiness(MengWangMsgPO entity, String gateway_msgid) throws Exception {
		entity.setMsgId(gateway_msgid);
		List<NameValuePair> formParams = getHttpParams(entity);
		LOGGER.info("[MengWangMsgChannel.doBusiness] formParams = " + gson.toJson(formParams));
		HttpClient httpclient = new DefaultHttpClient();
		// 设置通信协议版本
		httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		httpclient.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "UTF-8");
		// 设置请求URL
		LOGGER.info("[MengWangMsgChannel.doBusiness] URL = " + msgChannelAccount.getUrl());
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

		LOGGER.info("[MengWangMsgChannel.doBusiness] 提交请求 = " + httpPost.getRequestLine());
		// 发送请求
		HttpResponse response = httpclient.execute(httpPost);
		// 获取响应结果
		HttpEntity resEntity = response.getEntity();
		LOGGER.info("[MengWangMsgChannel.doBusiness] 请求结果 = " + response.getStatusLine());
		// 解析响应结果
		String result = "";
		if (resEntity != null) {
			result = EntityUtils.toString(resEntity, "utf-8");
			LOGGER.info("[MengWangMsgChannel.doBusiness] 请求结果json = " + result);
		}
		httpclient.getConnectionManager().shutdown();

		ResponseObject ro = getResult(result);
		
		// 更新短信流水状态
		updateShortMsgStatus(ro, gateway_msgid);
		
		ro.setMsgId(gateway_msgid);
		LOGGER.info("[MengWangMsgChannel.doBusiness] [end]");
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
		LOGGER.info("[MengWangMsgChannel.updateShortMsgStatus] respstatus = " + ro.getStatus());
		if (MengWangRespCode.MENG_WANG_ERROR_CODE_0.code.equals(ro.getStatus())) {// 申请成功
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
	 * -1:参数为空 ,-12:有异常电话号码,
	 * -13:电话号码个数与实际个数不相等, -14:实际号码个数超过100,
	 * -999：web服务器内部错误
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	private ResponseObject getResult(String result) throws Exception {
		LOGGER.info("[MengWangMsgChannel.getResult] result = " + result);
		ResponseObject ro = new ResponseObject();
		Document doc = (Document)DocumentHelper.parseText(result);
		Element root = doc.getRootElement();  
		List<Element> childlist = root.elements();
		if(0 == childlist.size()) {
			result = root.getText();
		}
		if (null == MengWangRespCode.getMengWangRespCode(result)) {
			ro.setCode("00");
			ro.setStatus(MengWangRespCode.MENG_WANG_ERROR_CODE_0.code);
			ro.setMsg(MengWangRespCode.MENG_WANG_ERROR_CODE_0.msg);
			ro.setMsgId(result);
		} else {
			ro.setCode("99");
			ro.setStatus(MengWangRespCode.getMengWangRespCode(result).code);
			ro.setMsg(MengWangRespCode.getMengWangRespCode(result).msg);
		}
		return ro;
	}

}

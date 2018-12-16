/**
 * Project Name:sell.<br/> 
 * File Name:WechatController.java.<br/> 
 * Package Name:com.imooc.sell.controller.<br/> 
 * Date:2017年12月13日下午4:44:50.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */
package com.imooc.sell.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.sell.config.prop.ProjectUrlProperties;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.lly835.bestpay.utils.JsonUtil;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;

/**
 * <pre>
 * ClassName: WechatController
 * Function: ADD FUNCTION
 * Reason: ADD REASON(可选)
 * Date: 2017年12月13日 下午4:44:50
 * Desc: ADD DESC(可选)
 * @author 王艳军
 * @version 1.0
 * @since JDK 1.6
 * </pre>
 */
@Slf4j
@Controller
@RequestMapping(value = "/wechat")
public class WechatController {

  @Autowired
  private WxMpService wxMpService;

  @Autowired
  private WxMpService wxOpenService;

  @Autowired
  private ProjectUrlProperties projectUrlConfig;
  
  @Autowired
  private WxMpMessageRouter router;

  /**
   * @Title: authGet
   * @Description 接收到来自微信服务器的认证消息
   * @param signature
   * @param timestamp
   * @param nonce
   * @param echostr
   * @return string 返回值类型 
   * @throws java.lang.IllegalArgumentException 抛出异常类型 
   */
  @ResponseBody
  @GetMapping(value = "/portal", produces = "text/plain;charset=utf-8")
  public String authGet(@RequestParam(name = "signature", required = false) String signature,
                        @RequestParam(name = "timestamp", required = false) String timestamp,
                        @RequestParam(name = "nonce", required = false) String nonce,
                        @RequestParam(name = "echostr", required = false) String echostr) {
    if (log.isDebugEnabled()) {
      log.debug("\n【微信公众平台】 接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature, timestamp, nonce, echostr);
    }
    if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
      throw new IllegalArgumentException("【微信公众平台】 请求参数非法，请核实!");
    }
    if (this.wxMpService.checkSignature(timestamp, nonce, signature)) {
      return echostr;
    }
    return "非法请求";
  }
  
  /**
   * 
   * @Title: authPost
   * @Description: 接收微信推送过来的消息
   * @param requestBody
   * @param signature
   * @param timestamp
   * @param nonce
   * @param encType
   * @param msgSignature
   * @return string 返回值类型 
   * @throws java.lang.IllegalArgumentException 抛出异常类型 
   */
  @ResponseBody
  @PostMapping(value = "/portal", produces = "application/xml; charset=UTF-8")
  public String authPost (@RequestBody String requestBody,
                          @RequestParam(name = "signature", required = true) String signature,
                          @RequestParam(name = "timestamp", required = true) String timestamp,
                          @RequestParam(name = "nonce", required = true) String nonce,
                          @RequestParam(name = "encrypt_type", required = false) String encType,
                          @RequestParam(name = "msg_signature", required = false) String msgSignature) {
	    log.info("【微信公众平台】接收微信请求：[signature=[{}], encType=[{}], msgSignature=[{}]," + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ", signature, encType, msgSignature, timestamp, nonce, requestBody);

	    if (!this.wxMpService.checkSignature(timestamp, nonce, signature)) {
	      throw new IllegalArgumentException("【微信公众平台】非法请求，可能属于伪造的请求！");
	    }
	    String out = null;
	    if (encType == null) {
	      // 明文传输的消息
	      WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
	      WxMpXmlOutMessage outMessage = this.route(inMessage);
	      if (outMessage == null) {
	        return "";
	      }
	      out = outMessage.toXml();
	    } else if ("aes".equals(encType)) {
	      // aes加密的消息
	      WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(requestBody, this.wxMpService.getWxMpConfigStorage(), timestamp, nonce, msgSignature);
	      log.debug("【微信公众平台】消息解密后内容为：{} ", inMessage.toString());
	      WxMpXmlOutMessage outMessage = this.route(inMessage);
	      if (outMessage == null) {
	        return "";
	      }
	      out = outMessage.toEncryptedXml(this.wxMpService.getWxMpConfigStorage());
	    }
	    log.debug("【微信公众平台】组装回复信息：{}", out);
	    return out;
    }
  
  /**
   * 
   * @Title: route 
   * @Description: 根据不同的消息类型路由处理消息
   * @param message
   * @param message
   * @return WxMpXmlOutMessage 返回值类型 
   * @throws  Exception
   */
  private WxMpXmlOutMessage route(WxMpXmlMessage message) {
    try {
      return this.router.route(message);
    } catch (Exception e) {
      log.error("【微信公众平台】处理消息出现错误", e);
    }
    return null;
  }
  
  /**
   * 微信公众平台网页授权登录，获取code
   * 
   * @param returnUrl
   * @return
   */
  @GetMapping(value = "/authorize")
  public String authorize(@RequestParam(name = "returnUrl", required = true) String returnUrl) {
    String url = projectUrlConfig.getDomainUrl() + projectUrlConfig.getProjectName() + projectUrlConfig.getWechatMpAuthorizeUrlUserInfo();
    if (log.isInfoEnabled()) {
      log.info("【微信公众平台网页授权】获取code ==> url={}", url);
    }
    String redirectUrl = "";
    try {
      redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl, "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      log.error("【微信公众平台网页授权】获取code出现UnsupportedEncodingException错误，e={}", e);
      throw new SellException(ResultEnum.ENCODING_UNSUPPORT_ERROR.getCode(), e.getMessage());
    } catch (Exception e) {
      log.error("【微信公众平台网页授权】获取code出现Exception错误，e={}", e);
      throw new SellException(ResultEnum.MP_OTHER_ERROR.getCode(), e.getMessage());
    }
    log.info("【微信公众平台网页授权】获取code ==> redirectUrl={}", redirectUrl);
    return "redirect:" + redirectUrl;
  }

  /**
   * 微信公众平台网页授权，获取用户信息
   * @param code
   * @param returnUrl
   * @return
   */
  @GetMapping(value = "/userInfo")
  public String userInfo(@RequestParam(name = "code", required = true) String code,
                         @RequestParam(name = "state", required = true) String returnUrl) {
    WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
    try {
      wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
    } catch (WxErrorException e) {
      log.info("【微信公众平台网页授权】获取用户信息出现错误， e={}", e);
      throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
    }
    if (log.isInfoEnabled()) {
      log.info("【微信公众平台网页授权】获取用户信息  ==>  wxMpOAuth2AccessToken_Json={}", JsonUtil.toJson(wxMpOAuth2AccessToken));
    }
    String openId = wxMpOAuth2AccessToken.getOpenId();
    return "redirect:" + returnUrl + "?openid=" + openId;
  }

  /**
   * 
   * 微信开放平台扫码登录Authorize
   * 
   * @param returnUrl
   * @return
   */
  @GetMapping(value = "/qrAuthorize")
  public String qrAuthorize(@RequestParam(name = "returnUrl", required = true) String returnUrl) {
    String url = projectUrlConfig.getWechatMpAuthorizeUrl() + projectUrlConfig.getProjectName() + projectUrlConfig.getWechatOpenAuthorizeUrlUserInfo();
    log.info("【微信开放平台网页授权】扫描登录获取code, url={}", url);
    String redirectUrl = null;
    try {
      redirectUrl = wxOpenService.buildQrConnectUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl, "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      log.error("【微信开放平台网页授权】出现UnsupportedEncodingException错误，e={}", e);
      throw new SellException(ResultEnum.ENCODING_UNSUPPORT_ERROR.getCode(), e.getMessage());
    } catch (Exception e) {
      log.error("【微信公众平台网页授权】获取code出现Exception错误，e={}", e);
      throw new SellException(ResultEnum.MP_OTHER_ERROR.getCode(), e.getMessage());
    }
    if (log.isInfoEnabled()) {
      log.info("【微信开放平台网页授权】扫描登录获取code ==> redirectUrl={}", redirectUrl);
    }
    return "redirect:" + redirectUrl;
  }

  /**
   * 微信开放平台扫码登录Authorize，获取用户信息
   * 
   * @param code
   * @param returnUrl
   * @return
   */
  @GetMapping(value = "/qrUserInfo")
  public String qrUserInfo(@RequestParam(name = "code", required = true) String code,
                           @RequestParam(name = "state", required = true) String returnUrl) {
    WxMpOAuth2AccessToken wxOpenOAuth2AccessToken = new WxMpOAuth2AccessToken();
    try {
      wxOpenOAuth2AccessToken = wxOpenService.oauth2getAccessToken(code);
    } catch (WxErrorException e) {
      log.info("【微信开放平台网页授权登录获取code】	出现错误， e={}", e);
      throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
    }
    log.info("wxMpOAuth2AccessToken={}", wxOpenOAuth2AccessToken);
    String openid = wxOpenOAuth2AccessToken.getOpenId();
    return "redirect:" + returnUrl + "?openid=" + openid;
  }
}

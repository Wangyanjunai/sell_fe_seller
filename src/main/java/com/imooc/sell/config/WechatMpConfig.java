
/**
 * Project Name:sell.<br/> 
 * File Name:WechatMpConfig.java.<br/> 
 * Package Name:com.imooc.sell.config.<br/> 
 * Date:2017年12月13日下午4:52:37.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */ 
    
package com.imooc.sell.config;

import com.imooc.sell.handler.*;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.constant.WxMpEventConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.imooc.sell.config.prop.WechatMpProperties;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

/** 
 * <pre>
 * @ClassName:WechatMpConfig
 * @Function:微信公众账号相关Bean配置
 * @Reason:ADD REASON(可选) 
 * @Date:2017年12月13日 下午4:52:37
 * @Desc:ADD DESC(可选) 
 * @author 王艳军
 * @version 1.0
 * @since JDK 1.6
 * </pre>
 */
@Configuration
@ConditionalOnClass(WxMpService.class)
@EnableConfigurationProperties(WechatMpProperties.class)
public class WechatMpConfig {

  @Autowired
  protected LogHandler logHandler;

  @Autowired
  protected NullHandler nullHandler;

  @Autowired
  protected KfSessionHandler kfSessionHandler;

  @Autowired
  protected StoreCheckNotifyHandler storeCheckNotifyHandler;

  @Autowired
  private LocationHandler locationHandler;

  @Autowired
  private MenuHandler menuHandler;

  @Autowired
  private MsgHandler msgHandler;

  @Autowired
  private UnsubscribeHandler unsubscribeHandler;

  @Autowired
  private SubscribeHandler subscribeHandler;
  
  @Autowired
  private WechatMpProperties wechatMpConfig;

  @Bean
  @ConditionalOnMissingBean
  public WxMpConfigStorage wxMpConfigStorage() {
    WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
    wxMpConfigStorage.setAppId(wechatMpConfig.getMpAppId());
    wxMpConfigStorage.setSecret(wechatMpConfig.getMpAppSecret());
    wxMpConfigStorage.setToken(wechatMpConfig.getToken());
    wxMpConfigStorage.setAesKey(wechatMpConfig.getAesKey());
    return wxMpConfigStorage;
  }

  @Bean
  @ConditionalOnMissingBean
  public WxMpService wxMpService() {
    WxMpService wxMpService = new WxMpServiceImpl();
    wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
    return wxMpService;
  }

  @Bean
  @ConditionalOnMissingBean
  public WxMpMessageRouter router(WxMpService wxMpService) {

    final WxMpMessageRouter newRouter = new WxMpMessageRouter(wxMpService);

    // 记录所有事件的日志 （异步执行）
    newRouter.rule().handler(this.logHandler).next();

    // 接收客服接入会话事件
    newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxMpEventConstants.CustomerService.KF_CREATE_SESSION)
            .handler(this.kfSessionHandler)
            .end();

    // 接收客服关闭会话事件
    newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxMpEventConstants.CustomerService.KF_CLOSE_SESSION)
            .handler(this.kfSessionHandler)
            .end();

    // 接收客服接入会话事件
    newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxMpEventConstants.CustomerService.KF_SWITCH_SESSION)
            .handler(this.kfSessionHandler)
            .end();

    // 门店审核事件
    newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxMpEventConstants.POI_CHECK_NOTIFY)
            .handler(this.storeCheckNotifyHandler)
            .end();

    // 自定义菜单事件
    newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxConsts.MenuButtonType.CLICK).handler(this.getMenuHandler())
            .end();

    // 点击菜单连接事件
    newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxConsts.MenuButtonType.VIEW).handler(this.nullHandler)
            .end();

    // 关注事件
    newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxConsts.EventType.SUBSCRIBE).handler(this.getSubscribeHandler())
            .end();

    // 取消关注事件
    newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxConsts.EventType.UNSUBSCRIBE).handler(this.getUnsubscribeHandler())
            .end();

    // 上报地理位置事件
    newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxConsts.EventType.LOCATION).handler(this.getLocationHandler())
            .end();

    // 接收地理位置消息
    newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.LOCATION)
            .handler(this.getLocationHandler())
            .end();

    // 扫码事件
    newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxConsts.EventType.SCAN).handler(this.getScanHandler())
            .end();

    // 默认
    newRouter.rule().async(false).handler(this.getMsgHandler())
            .end();

    return newRouter;

  }

  protected MenuHandler getMenuHandler() {
    return this.menuHandler;
  }

  protected SubscribeHandler getSubscribeHandler() {
    return this.subscribeHandler;
  }

  protected UnsubscribeHandler getUnsubscribeHandler() {
    return this.unsubscribeHandler;
  }

  protected AbstractHandler getLocationHandler() {
    return this.locationHandler;
  }

  protected MsgHandler getMsgHandler() {
    return this.msgHandler;
  }

  protected AbstractHandler getScanHandler() {
    return null;
  }
}

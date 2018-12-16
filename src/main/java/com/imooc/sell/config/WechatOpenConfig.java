package com.imooc.sell.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.imooc.sell.config.prop.WechatMpProperties;

/**
 * @Author 王艳军
 * @Date 2017-12-17 14:38
 */
public class WechatOpenConfig {

    @Autowired
    private WechatMpProperties wechatMpConfig;

    @Bean
    public WxMpService wxOpenService(){
        WxMpService wxOpenService = new WxMpServiceImpl();
        wxOpenService.setWxMpConfigStorage(wxOpenConfigStorage());
        return wxOpenService;
    }

    @Bean
    private WxMpConfigStorage wxOpenConfigStorage(){
        WxMpInMemoryConfigStorage wxOpenInMemoryConfigStorage = new WxMpInMemoryConfigStorage();
        wxOpenInMemoryConfigStorage.setAppId(wechatMpConfig.getOpenAppid());
        wxOpenInMemoryConfigStorage.setSecret(wechatMpConfig.getOpenAppSecret());
        return wxOpenInMemoryConfigStorage;
    }
}

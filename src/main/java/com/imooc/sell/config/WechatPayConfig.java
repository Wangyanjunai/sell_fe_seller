package com.imooc.sell.config;

import com.imooc.sell.config.prop.ProjectUrlProperties;
import com.imooc.sell.config.prop.WechatMpProperties;
import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * describe:
 *
 * @author 王艳军
 * @date 2017/12/13 21:48:55
 */
@Component
public class WechatPayConfig {

    @Autowired
    private WechatMpProperties wechatMpConfig;

    @Autowired
    private ProjectUrlProperties projectUrlProperties;

    @Bean
    public BestPayServiceImpl bestPayService(){
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config());
        return bestPayService;
    }

    @Bean
    private WxPayH5Config wxPayH5Config(){
        WxPayH5Config wxPayH5Config = new  WxPayH5Config();
        wxPayH5Config.setAppId(wechatMpConfig.getMpAppId());
        wxPayH5Config.setAppSecret(wechatMpConfig.getMpAppSecret());
        wxPayH5Config.setMchId(wechatMpConfig.getMchId());
        wxPayH5Config.setMchKey(wechatMpConfig.getMchKey());
        wxPayH5Config.setKeyPath(wechatMpConfig.getKeyPath());
        wxPayH5Config.setNotifyUrl(projectUrlProperties.getDomainUrl() + projectUrlProperties.getProjectName() + wechatMpConfig.getNotifyUrl());
        return wxPayH5Config;
    }
}

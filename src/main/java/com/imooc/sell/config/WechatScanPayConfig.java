package com.imooc.sell.config;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.imooc.sell.config.prop.WechatMpProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * @PackageName com.imooc.sell.config
 * @ClassName WechatScanPayConfig
 * @Desc 微信刷卡支付Bean配置
 * @WebSite https://www.potato369.com
 * @Author 王艳军
 * @Date 2018/10/10 15:52
 * @CreateBy IntellJ IDEA 2018.2.4
 * @Copyright Copyright (c) 2016 ~ 2020 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Component
public class WechatScanPayConfig {

    @Autowired
    private WechatMpProperties wechatMpConfig;

    @Bean
    public WxPayService wxPayService() {
        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(wxPayConfig());
        return wxPayService;
    }

    @Bean
    public WxPayConfig wxPayConfig() {
        WxPayConfig wxPayConfig = new WxPayConfig();
        wxPayConfig.setAppId(this.wechatMpConfig.getMpAppId());
        wxPayConfig.setMchId(this.wechatMpConfig.getMchId());
        wxPayConfig.setMchKey(this.wechatMpConfig.getMchKey());
        wxPayConfig.setTradeType(WxPayConstants.TradeType.MICROPAY);
        return wxPayConfig;
    }
}

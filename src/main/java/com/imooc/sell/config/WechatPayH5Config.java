package com.imooc.sell.config;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.imooc.sell.config.prop.ProjectUrlProperties;
import com.imooc.sell.config.prop.WechatMpProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * Describe:H5支付是指商户在微信客户端外的移动端网页展示商品或服务，用户在前述页面确认使用微信支付时，商户发起本服务呼起微信客户端进行支付<br />
 *          主要用于触屏版的手机浏览器请求微信支付的场景。可以方便的从外部浏览器唤起微信支付 <br />
 *          文档地址：https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=15_1<br />
 *          发起微信客户端外的移动端网页H5调起支付配置<br />
 * <pre>
 * @author 王艳军
 * @date 2017/12/13 21:48:55
 */
@Component
public class WechatPayH5Config {

    @Autowired
    private WechatMpProperties wechatMpConfig;

    @Autowired
    private ProjectUrlProperties projectUrlProperties;

    @Bean
    public WxPayServiceImpl wxPayH5Service() {
        WxPayServiceImpl wxPayH5Service = new WxPayServiceImpl();
        wxPayH5Service.setConfig(wxPayH5Config());
        return wxPayH5Service;
    }

    @Bean
    private WxPayConfig wxPayH5Config() {
        WxPayConfig wxPayH5Config = new  WxPayConfig();
        wxPayH5Config.setAppId(wechatMpConfig.getMpAppId());
        wxPayH5Config.setMchId(wechatMpConfig.getMchId());
        wxPayH5Config.setMchKey(wechatMpConfig.getMchKey());
        wxPayH5Config.setTradeType(WxPayConstants.TradeType.MWEB);//H5支付
        wxPayH5Config.setKeyPath(wechatMpConfig.getKeyPath());
        wxPayH5Config.setUseSandboxEnv(false);
        wxPayH5Config.setNotifyUrl(projectUrlProperties.getDomainUrl() + projectUrlProperties.getProjectName() + wechatMpConfig.getNotifyUrl());
        return wxPayH5Config;
    }
}

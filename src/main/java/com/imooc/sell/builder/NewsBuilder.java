package com.imooc.sell.builder;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;

/**
 * <pre>
 * @PackageName com.imooc.sell.builder
 * @ClassName NewsBuilder
 * @Desc 图文消息Builder
 * @WebSite https://www.potato369.com
 * @Author 王艳军
 * @Date 2018/11/29 17:49
 * @CreateBy IntellJ IDEA 2018.2.6
 * @Copyright Copyright (c) 2016 ~ 2020 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
public class NewsBuilder extends AbstractBuilder {

    @Override
    public WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMessage, WxMpService service) {

        WxMpXmlOutNewsMessage m = WxMpXmlOutMessage.NEWS().build();

        return m;
    }
}

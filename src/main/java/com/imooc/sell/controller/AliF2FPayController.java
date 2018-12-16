package com.imooc.sell.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * <pre>
 * @PackageName com.imooc.sell.controller
 * @ClassName AliF2FPayController
 * @Desc 支付宝当面付Controller
 * @WebSite https://www.potato369.com
 * @Author 王艳军
 * @Date 2018/10/10 16:23
 * @CreateBy IntellJ IDEA 2018.2.4
 * @Copyright Copyright (c) 2016 ~ 2020 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Controller
@RequestMapping(value = "/pay/alif2f")
public class AliF2FPayController {

    @RequestMapping(value = "/index")
    public ModelAndView index() {
        return new ModelAndView("pay/alipay/f2fpay/index");
    }

    @RequestMapping("/f2fpay")
    public ModelAndView f2fpay() {
        return new ModelAndView("pay/alipay/f2fpay/pay");
    }
}

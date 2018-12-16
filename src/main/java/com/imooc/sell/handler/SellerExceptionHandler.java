package com.imooc.sell.handler;

import com.imooc.sell.VO.ResultVO;
import com.imooc.sell.config.prop.ProjectUrlProperties;
import com.imooc.sell.exception.ResponseBankException;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.exception.SellerAuthorizeException;
import com.imooc.sell.utils.ResultVOUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * describe:
 *
 * @Author 王艳军
 * @Date 2017/12/18 17:41:05
 */
@ControllerAdvice
public class SellerExceptionHandler {

    @Autowired
    private ProjectUrlProperties projectUrlConfig;
    /**
     * 拦截登录异常：未在客户端查询到token,通过token查询不到redis缓存里面的对应openid,则使用微信扫码登录用户直接跳转url：
     * http://potato369.natapp1.cc/sell/wechat/qrAuthorize?returnUrl=http://potato369.natapp1.cc/sell/seller/login
     */
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerSellerAuthorizeException(){
        return new ModelAndView("redirect:"
                        .concat(projectUrlConfig.getDomainUrl()
                        .concat(projectUrlConfig.getProjectName())
                        .concat(projectUrlConfig.getWechatOpenAuthorizeUrl())
                        .concat("?returnUrl=")
                        .concat(projectUrlConfig.getDomainUrl())
                        .concat(projectUrlConfig.getProjectName())
                        .concat(projectUrlConfig.getLoginUrl())));
    }

    @ResponseBody
    @ExceptionHandler(value = SellException.class)
    public ResultVO<?> handlerSellerException(SellException e) {
        return  ResultVOUtil.error(e.getCode(), e.getMessage());
    }
    
    @ExceptionHandler(value = ResponseBankException.class)
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public void handlerResponseBankException(){
      
    }
}

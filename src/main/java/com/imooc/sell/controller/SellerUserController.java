package com.imooc.sell.controller;

import com.imooc.sell.config.prop.ProjectUrlProperties;
import com.imooc.sell.constant.CookieConstant;
import com.imooc.sell.constant.RedisConstant;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.service.SellerService;
import com.imooc.sell.dataobject.SellerInfo;
import com.imooc.sell.utils.CookieUtil;
import com.imooc.sell.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Description:卖家用户相关操作
 * @Author 王艳军
 * @Date 2017-12-17 15:17
 */
@Controller
@RequestMapping(value = "/seller")
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlProperties projectUrlConfig;

    /**
     * 点餐系统卖家端后台系统网站应用微信扫码登录
     * @param openid
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/login")
    public ModelAndView login(@RequestParam(name = "openid", required = true) String openid, HttpServletResponse response, Map<String, Object> map){

        //第一步：用openid和数据库里的数据匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if (sellerInfo == null){
            map.put("msg", ResultEnum.LOGIN_FAIL.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error");
        }

        //第二步：设置Token保存到Redis缓存
        String token = UUIDUtil.gen32UUID();
        //设置Redis缓存过期时间为2小时
        Integer expire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX , token), openid, expire, TimeUnit.SECONDS);

        //第三步：设置Token保存到客户端浏览器Cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);
        return new ModelAndView("redirect:" + projectUrlConfig.getDomainUrl() + projectUrlConfig.getProjectName() + "/seller/order/list", map);
    }

    /**
     * 点餐系统卖家端后台系统网站应用登出
     * @param request
     * @param response
     */
    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map){
        //1、从cookie里面查询
       Cookie cookie =  CookieUtil.get(request, CookieConstant.TOKEN);
       if (cookie != null){
           //2、清除Redis
           redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
           //3、清除cookie
           CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
       }
       map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMessage());
       map.put("url","/sell/seller/order/list");
       return new ModelAndView("common/success",map);
    }
}

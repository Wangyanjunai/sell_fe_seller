/**
 * Project Name:sell.<br/> 
 * File Name:PayController.java.<br/> 
 * Package Name:com.imooc.sell.controller.<br/> 
 * Date:2017年12月13日下午9:21:13.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */
package com.imooc.sell.controller;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * <pre>
 * ClassName: PayController
 * Function: ADD FUNCTION
 * Reason: ADD REASON(可选)
 * Date: 2017年12月13日 下午9:21:13
 * Desc: ADD DESC(可选)
 * @author 王艳军
 * @version 1.0
 * @since JDK 1.6
 * </pre>
 */
@Controller
@RequestMapping(value = "/pay")
@Slf4j
public class PayController {

  @Autowired
  private OrderService orderService;

  @Autowired
  private PayService payService;

  /**
   * 发起微信内H5调起支付
   * @param orderId
   * @param returnUrl
   * @param map
   * @return
   */
  @GetMapping(value = "/create")
  public ModelAndView create(@RequestParam(name = "orderId", required = true) String orderId,
                             @RequestParam(name = "returnUrl", required = true) String returnUrl,
                             Map<String, Object> map) {
    // 1、查询订单信息是否存在
    OrderDTO orderDTO = orderService.findOne(orderId);
    if (orderDTO == null) {
      log.error("【支付订单】 订单信息不存在，订单id={}，返回前端URL={}", orderId, returnUrl);
      throw new SellException(ResultEnum.ORDER_NOT_EXIST);
    }
    // 2、查询订单状态
    if (orderDTO.getOrderStatus() != OrderStatusEnum.NEW.getCode()) {
    	log.error("【支付订单】 订单状态不正确，订单id={}，订单状态={}，返回前端URL={}， ", orderId, orderDTO.getOrderStatusEnum().getMessage(), returnUrl);
        throw new SellException(ResultEnum.ORDER_NOT_EXIST);
	}
    // 3、查询订单支付状态
    if (orderDTO.getPayStatus() != PayStatusEnum.WAITING.getCode()) {
    	log.error("【支付订单】 订单支付状态不正确，订单id={}，订单支付状态={}，返回前端URL={}， ", orderId, orderDTO.getPayStatusEnum().getMessage(), returnUrl);
        throw new SellException(ResultEnum.ORDER_NOT_EXIST);
	}
    // 4、发起微信支付
    PayResponse payResponse = payService.create(orderDTO);
    map.put("payResponse", payResponse);
    map.put("returnUrl", returnUrl);
    return new ModelAndView("pay/create", map);
  }

  /**
   * 微信异步通知
   * @param notifyData
   * @return
   */
  @PostMapping(value = "/notify")
  public ModelAndView notify(@RequestBody String notifyData) {
    
    payService.notify(notifyData);
    
    //3、返回给微信处理结果
    return new ModelAndView("pay/success");
  }
}

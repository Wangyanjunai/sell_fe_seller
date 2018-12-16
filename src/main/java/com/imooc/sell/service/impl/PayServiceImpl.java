
/**
 * Project Name:sell.<br/> 
 * File Name:PayServiceImpl.java.<br/> 
 * Package Name:com.imooc.sell.service.impl.<br/> 
 * Date:2017年12月13日下午9:31:01.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */

package com.imooc.sell.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.PayService;
import com.imooc.sell.utils.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.lly835.bestpay.utils.JsonUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * ClassName: PayServiceImpl. <br/>
 * Function: ADD FUNCTION.<br/>
 * Reason: ADD REASON(可选).<br/>
 * Date: 2017年12月13日 下午9:31:01. <br/>
 * Desc: ADD DESC(可选). <br/>
 * 
 * @author 王艳军
 * @version
 * @since JDK 1.6
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

  private static final String ORDER_NAME = "微信点餐订单";

  @Autowired
  private BestPayServiceImpl bestPayService;

  @Autowired
  private OrderService orderService;

  @Override
  public PayResponse create(OrderDTO orderDTO) {
    
    PayRequest payRequest = new PayRequest();
    payRequest.setOpenid(orderDTO.getBuyerOpenid());
    payRequest.setOrderAmount((orderDTO.getOrderAmount()).doubleValue());
    payRequest.setOrderId(orderDTO.getOrderId());
    payRequest.setOrderName(ORDER_NAME);
    payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

    if (log.isDebugEnabled()) {
      log.debug("【微信支付】 发起支付，payRequest={}", JsonUtil.toJson(payRequest));
    }
    PayResponse payResponse = bestPayService.pay(payRequest);
    if (log.isDebugEnabled()) {
      log.debug("【微信支付】发起支付，payResponse={}", JsonUtil.toJson(payResponse));
    }
    return payResponse;
  }

  @Override
  public PayResponse notify(String notifyData) {
    
    // 需要注意的点：
    // 1、验证签名
    // 2、支付的状态
    // 3、支付的金额
    // 4、下单人（下单人=支付人）
    
    PayResponse payResponse = bestPayService.asyncNotify(notifyData);
    if (log.isDebugEnabled()) {
      log.debug("【微信公众号支付】异步通知，payResponse={}", JsonUtil.toJson(payResponse));
    }
    
    // 查询订单
    OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());
    if(orderDTO == null){
      log.error("【微信公众号支付】异步通知，订单不存在 orderId={}", payResponse.getOrderId());
      throw new SellException(ResultEnum.ORDER_NOT_EXIST);
    }
    // 判断订单金额与支付金额是否一致（0.10 == 0.1）
    if (!MathUtil.equals(orderDTO.getOrderAmount().doubleValue(), payResponse.getOrderAmount()))  {
      log.error("【微信公众号支付】异步通知，订单金额不一致 orderId={}，微信通知金额={}，系统金额={}", payResponse.getOrderId(), payResponse.getOrderAmount(), orderDTO.getOrderAmount());
      throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
    }
    // 修改订单的支付状态
    orderService.paid(orderDTO);
    return payResponse;
  }

  /**
   * 微信退款
   * @param orderDTO
   * @return
   */
  @Override
  public RefundResponse refund(OrderDTO orderDTO){
    
    RefundRequest refundRequest = new RefundRequest();
    refundRequest.setOrderId(orderDTO.getOrderId());
    refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
    refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
    if (log.isDebugEnabled()) {
      log.debug("【微信公众号退款】订单id={}, 退款请求Request={}", orderDTO.getOrderId(), JsonUtil.toJson(refundRequest));
    }

    RefundResponse refundResponse = bestPayService.refund(refundRequest);
    
    if (log.isDebugEnabled()) {
      log.info("【微信公众号退款】订单id={}, 退款响应Response={}", orderDTO.getOrderId(), JsonUtil.toJson(refundResponse));
    }

    return refundResponse;
  }

}

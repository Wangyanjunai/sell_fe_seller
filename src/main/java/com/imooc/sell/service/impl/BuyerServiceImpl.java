
/**
 * Project Name:sell.<br/> 
 * File Name:BuyerServiceImpl.java.<br/> 
 * Package Name:com.imooc.sell.service.impl.<br/> 
 * Date:2017年12月12日下午10:39:25.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */ 
    
package com.imooc.sell.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.BuyerService;
import com.imooc.sell.service.OrderService;

import lombok.extern.slf4j.Slf4j;

/** 
 * ClassName: BuyerServiceImpl
 * Function:
 * Reason:
 * Date: 2017年12月12日 下午10:39:25
 * Desc:
 * @author 王艳军
 * @version
 * @since JDK 1.6
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

  @Autowired
  private OrderService orderService;

  /* (non-Javadoc)
   * @see com.imooc.sell.service.BuyerService#findOrderOne(java.lang.String, java.lang.String)
   */
  @Override
  public OrderDTO findOrderOne(String openid, String orderId) {
    return checkOrderOwner(openid,orderId);
  }

  /* (non-Javadoc)
   * @see com.imooc.sell.service.BuyerService#cancelOrder(java.lang.String, java.lang.String)
   */
  @Override
  public OrderDTO cancelOrder(String openid, String orderId) {
    OrderDTO orderDTO = checkOrderOwner(openid, orderId);
    if (orderDTO == null) {
      log.error("【取消订单】查询不到该订单。orderId={}", orderId);
      throw new SellException(ResultEnum.ORDER_NOT_EXIST);
    }
    return orderService.cancel(orderDTO);
  }

  /**
   * 检查该订单的拥有者是否属于该用户
   * @param openid
   * @param orderId
   * @return
   */
  private OrderDTO checkOrderOwner(String openid, String orderId){
    OrderDTO orderDTO = orderService.findOne(orderId);
    if (orderDTO == null) {
      return null;
    }
    if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
      log.error("【查询订单】订单的openid不一致。openid={}，orderId={}", openid, orderId);
      throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
    }
    return orderDTO;
  }
}


/**
 * Project Name:sell.<br/> 
 * File Name:PayService.java.<br/> 
 * Package Name:com.imooc.sell.service.<br/> 
 * Date:2017年12月13日下午9:29:35.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */ 
    
package com.imooc.sell.service;

import com.imooc.sell.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

/** 
* ClassName: PayService
* Function:   ADD FUNCTION
* Reason:  支付订单<br/> 
* Date: 2017年12月13日 下午9:29:35
* Desc:  ADD DESC(可选)
* @author 王艳军 
* @version  
* @since JDK 1.6 
*/
public interface PayService {

  /**
   * 微信公众号支付订单
   * @param orderDTO
   * @return
   */
  PayResponse create(OrderDTO orderDTO);

  /**
   * 微信公众号支付异步通知结果
   * @param notifyData
   */
  PayResponse notify(String notifyData);
  
  /**
   * 微信公众号支付退款
   * @param orderDTO
   */
  RefundResponse refund(OrderDTO orderDTO);
  
}

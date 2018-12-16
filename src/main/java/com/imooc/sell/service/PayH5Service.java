
/**
 * Project Name:sell.<br/> 
 * File Name:PayService.java.<br/> 
 * Package Name:com.imooc.sell.service.<br/> 
 * Date:2017年12月13日下午9:29:35.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */ 
    
package com.imooc.sell.service;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.imooc.sell.dto.OrderDTO;

/** 
* ClassName: PayService.		  <br/> 
* Function:   ADD FUNCTION.<br/> 
* Reason:  支付订单<br/> 
* Date: 2017年12月13日 下午9:29:35.		 <br/> 
* Desc:  ADD DESC(可选).	 <br/> 
* @author 王艳军 
* @version  
* @since JDK 1.6 
*/

public interface PayH5Service {

  /**
   * 微信H5支付
   * @param orderDTO
   * @return
   * @throws WxPayException
   */
  WxPayUnifiedOrderResult create(OrderDTO orderDTO) throws WxPayException;

  /**
   * 微信H5支付异步通知结果
   * @param notifyData
   * @throws WxPayException
   */
  WxPayOrderNotifyResult notify(String notifyData) throws WxPayException;
  
  /**
   * 微信H5支付退款
   * @param orderDTO
   * @throws WxPayException
   */
  WxPayRefundResult refund(OrderDTO orderDTO) throws WxPayException;
  
}

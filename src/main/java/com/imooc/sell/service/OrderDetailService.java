
/**
 * Project Name:sell.<br/> 
 * File Name:OrderDetailService.java.<br/> 
 * Package Name:com.imooc.sell.service.impl.<br/> 
 * Date:2017年12月18日下午9:01:22.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */ 
    
package com.imooc.sell.service;

import java.util.List;

import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dataobject.ProductInfo;

/** 
  * 订单详情service
  * ClassName: OrderDetailService.<br/> 
  * Function: ADD FUNCTION.<br/> 
  * Reason:  ADD REASON(可选).<br/> 
  * Date: 2017年12月18日 下午9:01:22.<br/> 
  * Desc:  ADD DESC(可选).	 <br/> 
  * @author 王艳军 
  * @version  
  * @since JDK 1.6 
  */

public interface OrderDetailService {
  
  /**
   * 根据订单id查询订单详情列表信息
   * @param orderId
   * @return
   */
  List<OrderDetail> findOrderDetailByOrderId(String orderId);
  
  /**
   * 根据订单id查询订购的商品信息
   * @param orderId
   * @return
   */
  List<ProductInfo> findProductInfoByOrderId(String orderId);
  
}

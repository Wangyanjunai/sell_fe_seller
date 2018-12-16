
/**
 * Project Name:sell.<br/> 
 * File Name:OrderDetailServiceImpl.java.<br/> 
 * Package Name:com.imooc.sell.service.impl.<br/> 
 * Date:2017年12月18日下午9:06:30.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */ 
    
package com.imooc.sell.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.repository.OrderDetailRepository;
import com.imooc.sell.service.OrderDetailService;

/** 
* ClassName: OrderDetailServiceImpl.		  <br/> 
* Function:   ADD FUNCTION.<br/> 
* Reason:  ADD REASON(可选).<br/> 
* Date: 2017年12月18日 下午9:06:30.		 <br/> 
* Desc:  ADD DESC(可选).	 <br/> 
* @author 王艳军 
* @version  
* @since JDK 1.6 
*/
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

  @Autowired
  private OrderDetailRepository orderDetailRepository;
  
  
  /* (non-Javadoc)
   * @see com.imooc.sell.service.impl.OrderDetailService#findOrderDetailByOrderId(java.lang.String)
   */
  @Override
  public List<OrderDetail> findOrderDetailByOrderId(String orderId) {
    return orderDetailRepository.findByOrderId(orderId);
  }

  /* (non-Javadoc)
   * @see com.imooc.sell.service.impl.OrderDetailService#findProductInfoByOrderId(java.lang.String)
   */
  @Override
  public List<ProductInfo> findProductInfoByOrderId(String orderId) {
    List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
    List<ProductInfo> productInfoList = new ArrayList<ProductInfo>();
    if (orderDetailList != null) {
      for (OrderDetail orderDetail : orderDetailList) {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(orderDetail.getProductId());
        productInfo.setProductName(orderDetail.getProductName());
        productInfoList.add(productInfo);
      }
    }
    return productInfoList;
  }

}

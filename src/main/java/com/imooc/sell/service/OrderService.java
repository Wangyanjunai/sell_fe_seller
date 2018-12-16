
/**
 * <pre>
 * Project Name:sell
 * File Name:OrderService.java
 * Package Name:com.imooc.sell.service
 * Date:2017年12月11日下午4:57:22
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/>
 * </pre>
 */ 
    
package com.imooc.sell.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.imooc.sell.dto.OrderDTO;

/**
  * <pre>
  * ClassName: OrderService
  * Function:   ADD FUNCTION
  * Reason:  ADD REASON(可选)
  * Date: 2017年12月11日 下午4:57:22
  * Desc:  ADD DESC(可选)
  * @author 王艳军
  * @version
  * @since JDK 1.6
  * </pre>
  */
public interface OrderService {

  /*1.创建订单*/
  /**
   * 创建订单
   * @param orderDTO
   * @return OrderDTO
   * @throws Exception
   */
  OrderDTO create(OrderDTO orderDTO);
  
  /*2.查询单个订单*/
  /**
   * 查询单个订单
   * @param orderId
   * @return OrderDTO
   * @throws Exception
   */
  OrderDTO findOne(String orderId);
  
  /*3.查询订单列表*/
  /**
   * 查询订单列表
   * @param buyerOpenid
   * @param pageable
   * @return Page<OrderDTO>
   * @throws Exception
   */
  Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);
  
  /*4.取消订单*/
  /**
   * 取消订单
   * @param orderDTO
   * @return OrderDTO
   * @throws Exception
   */
  OrderDTO cancel(OrderDTO orderDTO);
  
  /*5.完结订单*/
  /**
   * 完结订单
   * @param orderDTO
   * @return orderDTO
   * @throws Exception
   */
  OrderDTO finsh(OrderDTO orderDTO);
  
  /*6.支付订单*/
  /**
   * 支付订单
   * @param orderDTO
   * @return OrderDTO
   * @throws Exception
   */
  OrderDTO paid(OrderDTO orderDTO);
  
  /*7.查询订单列表*/
  /**
   * 
   * findList:(查询订单列表)
   *
   * @author admin
   * @param pageable
   * @return
   * @since JDK 1.6
   */
  Page<OrderDTO> findList(Pageable pageable);
}

/**
 * <pre>
 * Project Name:sell
 * File Name:OrderDetailRepository.java
 * Package Name:com.imooc.sell.repository
 * Date:2017年12月11日下午3:53:36
 * Copyright (c) 2018 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved
 * </pre>
 */ 
package com.imooc.sell.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.imooc.sell.dataobject.OrderDetail;
/** 
  * <pre>
  * @className:OrderDetailRepository
  * @function:
  * @reason:
  * @date:2017年12月11日 下午3:53:36
  * @Desc:
  * @author: 王艳军
  * @version:  
  * @since: JDK 1.6
  * </pre>
  */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
  List<OrderDetail> findByOrderId(String orderId);
}

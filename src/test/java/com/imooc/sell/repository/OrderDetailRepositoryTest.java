
/**
 * Project Name:sell.<br/> 
 * File Name:OrderDetailRepositoryTest.java.<br/> 
 * Package Name:com.imooc.sell.repository.<br/> 
 * Date:2017年12月11日下午4:22:55.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */ 
    
package com.imooc.sell.repository;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import com.imooc.sell.SellApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.utils.UUIDUtil;

/** 
* ClassName: OrderDetailRepositoryTest.		  <br/> 
* Function:   ADD FUNCTION.<br/> 
* Reason:  ADD REASON(可选).<br/> 
* Date: 2017年12月11日 下午4:22:55.		 <br/> 
* Desc:  ADD DESC(可选).	 <br/> 
* @author 王艳军 
* @version  
* @since JDK 1.6 
*/
@Rollback
@Transactional
@SpringBootTest(classes = SellApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
public class OrderDetailRepositoryTest {

  @Autowired
  private OrderDetailRepository orderDetailRepository;
  
  @Test
  public void saveOneTest(){
    OrderDetail orderDetail = new OrderDetail();
    orderDetail.setDetailId(UUIDUtil.gen32UUID());
    orderDetail.setOrderId("41bd5932c2e748f388b672a8f5e2984c");
    orderDetail.setProductId("bd34d70c13c244b08907a753ef11a2ad");
    orderDetail.setProductIcon("http://www.potato369.com/upload/images/fznr.png");
    orderDetail.setProductName("腐竹牛腩饭");
    orderDetail.setProductQuantity(2);
    orderDetail.setProductPrice(new BigDecimal(51.00));
    OrderDetail result = orderDetailRepository.save(orderDetail);
    assertNotNull(result);
  }
  
  @Test
  public void  findByOrderIdTest(){
    List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId("41bd5932c2e748f388b672a8f5e2984c");
    assertNotEquals(0, orderDetailList.size());
  }
}

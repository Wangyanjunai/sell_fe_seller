
/**
 * Project Name:sell.<br/> 
 * File Name:OrderDetailServiceImplTest.java.<br/> 
 * Package Name:com.imooc.sell.service.impl.<br/> 
 * Date:2017年12月19日上午10:18:48.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */ 
    
package com.imooc.sell.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import com.imooc.sell.SellApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.service.OrderDetailService;
import org.springframework.transaction.annotation.Transactional;

/** 
* ClassName: OrderDetailServiceImplTest.		  <br/> 
* Function:   ADD FUNCTION.<br/> 
* Reason:  ADD REASON(可选).<br/> 
* Date: 2017年12月19日 上午10:18:48.		 <br/> 
* Desc:  ADD DESC(可选).	 <br/> 
* @author 王艳军 
* @version  
* @since JDK 1.6 
*/
@Rollback
@Transactional
@SpringBootTest(classes = SellApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
public class OrderDetailServiceImplTest {

  @Autowired
  private OrderDetailService orderDetailService;
  
  @Test
  public void findOrderDetailByOrderIdTest(){
    List<OrderDetail> orderDetailList =  orderDetailService.findOrderDetailByOrderId("201712190947304742542a3593097460");
    assertNotEquals(0, orderDetailList.size());
  }
  
  @Test
  public void findProductInfoByOrderIdTest(){
    List<ProductInfo> productInfoList = orderDetailService.findProductInfoByOrderId("20171219094059415fb762f2d1d5e4d4");
    assertEquals(1, productInfoList.size());
  }
}

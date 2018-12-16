
/**
 * Project Name:sell.<br/> 
 * File Name:OrderMasterRepositoryTest.java.<br/> 
 * Package Name:com.imooc.sell.repository.<br/> 
 * Date:2017年12月11日下午3:55:30.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */ 
    
package com.imooc.sell.repository;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import com.imooc.sell.SellApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.sell.dataobject.OrderMaster;
import com.imooc.sell.utils.UUIDUtil;

/** 
* ClassName: OrderMasterRepositoryTest.		  <br/> 
* Function:   ADD FUNCTION.<br/> 
* Reason:  ADD REASON(可选).<br/> 
* Date: 2017年12月11日 下午3:55:30.		 <br/> 
* Desc:  ADD DESC(可选).	 <br/> 
* @author 王艳军 
* @version  
* @since JDK 1.6 
*/
@Rollback
@Transactional
@SpringBootTest(classes = SellApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
public class OrderMasterRepositoryTest {

  @Autowired
  private OrderMasterRepository orderMasterRepository;
  
  private static final String OPENID = "1101202assd";
  
  @Test
  public void saveTest(){
    OrderMaster orderMaster = new OrderMaster();
    orderMaster.setOrderId(UUIDUtil.genTimstampUUID());
    orderMaster.setBuyerName("廖师兄");
    orderMaster.setBuyerPhone("18575586895");
    orderMaster.setBuyerAddress("慕课网");
    orderMaster.setBuyerOpenid(OPENID);
    orderMaster.setOrderAmount(new BigDecimal(39.00));
    OrderMaster result = orderMasterRepository.save(orderMaster);
    assertNotNull(result);
  }
  
  @Test
  public void findByBuyerOpenidTest(){
    PageRequest pageRequest = new PageRequest(1, 6);
   Page<OrderMaster> result = orderMasterRepository.findByBuyerOpenid(OPENID, pageRequest);
   System.out.println(result.getTotalElements());
   assertNotEquals(0, result.getTotalElements());
  }
}

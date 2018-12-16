/**
 * Project Name:sell
 * File Name:OrderServiceImplTest.java
 * Package Name:com.imooc.sell.service.impl
 * Date:2017年12月12日上午6:45:53
 * Copyright (c) 2017,版权所有 (C) 2000-2016 卓望数码技术(深圳)有限公司 www.aspirecn.com All Rights Reserved.
 *
 */
package com.imooc.sell.service.impl;

import com.imooc.sell.SellApplication;
import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
/**
 * ClassName:OrderServiceImplTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年12月12日 上午6:45:53 <br/>
 * 
 * @author admin <br/>
 * @version 1.0 <br/>
 * @since JDK 1.6 <br/>
 * @see 1.0 <br/>
 */
@Slf4j
@Rollback(value = true)
@Transactional
@SpringBootTest(classes = SellApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
public class OrderServiceImplTest {

  @Autowired
  private OrderServiceImpl orderService;

  private final String BUYER_OPENID = "oSkiNv4fBXYxidv0wU_U0UDHNP4M";

  @Test
  public void create() throws Exception {

    OrderDTO orderDTO = new OrderDTO();
    orderDTO.setBuyerName("王冬丽");
    orderDTO.setBuyerAddress("和福楼");
    orderDTO.setBuyerPhone("18845673299");
    orderDTO.setBuyerOpenid(BUYER_OPENID);

    // 购物车
    List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();

    OrderDetail o1 = new OrderDetail();
    o1.setProductId("32ea66d543494ba8b0536a8f42249baa");
    o1.setProductQuantity(1);
    orderDetailList.add(o1);

    OrderDetail o2 = new OrderDetail();
    o2.setProductId("3a80c3cd341c44f9834e41a0c499baf6");
    o2.setProductQuantity(1);
    orderDetailList.add(o2);

    OrderDetail o3 = new OrderDetail();
    o3.setProductId("3ba9477c13ba4b2294d5adf794d4eb37");
    o3.setProductQuantity(1);
    orderDetailList.add(o3);

    OrderDetail o4 = new OrderDetail();
    o4.setProductId("61ae83410ed64850bb3d334845d83bde");
    o4.setProductQuantity(1);
    orderDetailList.add(o4);

    OrderDetail o5 = new OrderDetail();
    o5.setProductId("baeb1abb756a4fa88a62d24c015d62ee");
    o5.setProductQuantity(1);
    orderDetailList.add(o5);

    OrderDetail o6 = new OrderDetail();
    o6.setProductId("bc71782347834f79a46cba0c96a03cde");
    o6.setProductQuantity(1);
    orderDetailList.add(o6);

    OrderDetail o7 = new OrderDetail();
    o7.setProductId("bd34d70c13c244b08907a753ef11a2ad");
    o7.setProductQuantity(1);
    orderDetailList.add(o7);

    OrderDetail o8 = new OrderDetail();
    o8.setProductId("c750fbe8758f489abe21c2866e3daa3a");
    o8.setProductQuantity(1);
    orderDetailList.add(o8);

    orderDTO.setOrderDetailList(orderDetailList);
    OrderDTO result = orderService.create(orderDTO);
    log.info("【创建订单】 result={}", result);
  }

  @Test
  public void findOneTest() throws Exception{
    OrderDTO result = orderService.findOne("20171212135141628beeef186c01344f");
    assertNotNull(result);
    assertEquals("20171212135141628beeef186c01344f", result.getOrderId());
    log.info("【查询单个订单】 result={}", result);
  }
  
  @Test
  public void findListTest() throws Exception{
    PageRequest pageRequest = new PageRequest(0,2);
    Page<OrderDTO> result = orderService.findList(BUYER_OPENID,pageRequest);
    assertNotNull(result.getContent());
    assertNotEquals(0,result.getTotalElements());
    assertNotEquals(0,result.getTotalPages());
    log.info("【查询订单列表】 result={}", result.getContent());
    log.info("【查询订单总数】 totalElements={}",result.getTotalElements());
    log.info("【查询订单总页数】 totalPages={}",result.getTotalPages());
  }

  @Test
  public void cancelTest() throws Exception{
//    OrderDTO orderDTO = orderService.findOne("20171212135141628beeef186c01344f");
    OrderDTO orderDTO = orderService.findOne("8c447c865c274c8280a6ce0e6703a6bb");
    OrderDTO result = orderService.cancel(orderDTO);
    assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
  }

  @Test
  public void finshTest() throws Exception{
    OrderDTO orderDTO = orderService.findOne("8c447c865c274c8280a6ce0e6703a6bb");
    OrderDTO result = orderService.finsh(orderDTO);
    assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());
  }

  @Test
  public void paidTest() throws Exception{
    OrderDTO orderDTO = orderService.findOne("8c447c865c274c8280a6ce0e6703a6bb");
    OrderDTO result = orderService.paid(orderDTO);
    assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());
  }

  @Test
  public void listTest() throws Exception{
    PageRequest pageRequest = new PageRequest(0,10);
    Page<OrderDTO> result = orderService.findList(pageRequest);
    assertNotEquals(0, result.getSize());
    assertTrue("【查询所有的订单列表】",result.getTotalElements() > 0);
  }
}

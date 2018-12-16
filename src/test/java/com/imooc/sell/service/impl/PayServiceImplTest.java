package com.imooc.sell.service.impl;

import com.imooc.sell.SellApplication;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.PayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@Rollback
@Transactional
@SpringBootTest(classes = SellApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
public class PayServiceImplTest {

    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;

    @Test
    public void createTest() {
        OrderDTO orderDTO = orderService.findOne("20180927155540703020eb3d4f0e74db");
        payService.create(orderDTO);
    }
    @Test
    public void refundTest(){
        OrderDTO orderDTO = orderService.findOne("20180927155540703020eb3d4f0e74db");
        payService.refund(orderDTO);
    }
}
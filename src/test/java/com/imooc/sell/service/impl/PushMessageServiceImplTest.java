package com.imooc.sell.service.impl;

import com.imooc.sell.SellApplication;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.PushMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@Rollback
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SellApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PushMessageServiceImplTest {

    @Autowired
    private PushMessageService pushMessageService;

    @Autowired
    private OrderService orderService;

    @Test
    public void pushOrderStatusTest() {
        OrderDTO orderDTO = orderService.findOne("2017121815032306058ef3f1a6d5b4d5");
        pushMessageService.pushOrderStatus(orderDTO, "", "");
    }
    @Test
    public void pushOrderSuccessTest() {
        OrderDTO orderDTO = orderService.findOne("2017121815032306058ef3f1a6d5b4d5");
        pushMessageService.pushOrderSuccess(orderDTO);
    }
    @Test
    public void pushPaySuccessTest() {
        OrderDTO orderDTO = orderService.findOne("2017121815032306058ef3f1a6d5b4d5");
        pushMessageService.pushPaySuccess(orderDTO);
    }
}
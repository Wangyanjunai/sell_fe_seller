package com.imooc.sell.service.impl;

import com.imooc.sell.SellApplication;
import com.imooc.sell.dataobject.SellerInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
@Rollback
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SellApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SellerServiceImplTest {

    @Autowired
    private SellerServiceImpl sellerService;

    public static final String SELLER_OPENID = "oSkiNv4fBXYxidv0wU_U0UDHNP4M";

    @Test
    public void findSellerInfoByOpenidTest() {
        SellerInfo result = sellerService.findSellerInfoByOpenid(SELLER_OPENID);
        assertEquals(SELLER_OPENID, result.getOpenid());
    }
}
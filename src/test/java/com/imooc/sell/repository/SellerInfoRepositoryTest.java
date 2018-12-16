package com.imooc.sell.repository;

import com.imooc.sell.SellApplication;
import com.imooc.sell.dataobject.SellerInfo;
import com.imooc.sell.utils.UUIDUtil;
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
@SpringBootTest(classes = SellApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
public class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    public static final String SELLER_OPENID = "oSkiNv4fBXYxidv0wU_U0UDHNP4M";

    @Test
    public void saveTest(){
        SellerInfo sellerInfo = new SellerInfo("admin","123456", SELLER_OPENID);
        sellerInfo.setSellerId(UUIDUtil.gen32UUID());
        SellerInfo result = sellerInfoRepository.save(sellerInfo);
        assertNotNull(result);
    }

    @Test
    public void deleteTest(){
        String sellerId = "57a676a648484920ad6b6c715ef45fa1";
        sellerInfoRepository.delete(sellerId);
        SellerInfo result = sellerInfoRepository.findOne(sellerId);
        assertNull(result);
    }

    @Test
    public void findByOpenidTest() {
       SellerInfo sellerInfo = sellerInfoRepository.findByOpenid(SELLER_OPENID);
       assertEquals(SELLER_OPENID,sellerInfo.getOpenid());
    }
}
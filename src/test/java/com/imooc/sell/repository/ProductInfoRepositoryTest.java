/**
 * Project Name:sell
 * File Name:ProductInfoRepositoryTest.java
 * Package Name:com.imooc.sell.repository
 * Date:2017年12月8日下午11:56:50
 * Copyright (c) 2017,版权所有 (C) 2000-2016 卓望数码技术(深圳)有限公司 www.aspirecn.com All Rights Reserved.
 *
*/

package com.imooc.sell.repository;

import static org.junit.Assert.*;
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

import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.enums.ProductStatusEnum;
import com.imooc.sell.utils.UUIDUtil;

/**
 * ClassName:ProductInfoRepositoryTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年12月8日 下午11:56:50 <br/>
 * 
 * @author admin
 * @version
 * @since JDK 1.6
 * @see
 */
@Rollback
@Transactional
@SpringBootTest(classes = SellApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
public class ProductInfoRepositoryTest {

  @Autowired
  private ProductInfoRepository productInfoRepository;

  @Test
  public void saveTest() {
    ProductInfo productInfo = new ProductInfo("农家一碗香", new BigDecimal(18.30), 100, 1, 0);
    productInfo.setProductId(UUIDUtil.gen32UUID());
    productInfo.setProductDescription("农家一碗香，里面加了鸡蛋和瘦肉，一起炒的，味道非常棒的哈！！！！");
    ProductInfo result = productInfoRepository.save(productInfo);
    assertNotNull(result);
  }

  @Test
  public void deleteByIdTest() {
    productInfoRepository.delete("28da29e01a2d401abbc3bbd8cce47019");
    ProductInfo result =  productInfoRepository.findOne("28da29e01a2d401abbc3bbd8cce47019");
    assertNull(result);
  }
  
  @Test
  public void updateByIdTest() {
    ProductInfo productInfo = new ProductInfo("农家一碗香", new BigDecimal(20.00), 200, 2, ProductStatusEnum.DOWN.getCode());
    productInfo.setProductId("61ae83410ed64850bb3d334845d83bde");
    productInfo.setProductIcon("http://www.potato369.com/upload/images/ywx.png");
    productInfo.setProductDescription("农家一碗香，里面加了鸡蛋和瘦肉，一起炒的，味道非常棒的哈！！！！");
    ProductInfo result = productInfoRepository.save(productInfo);
    assertEquals("http://www.potato369.com/upload/images/ywx.png", result.getProductIcon());
  }
  
  @Test
  public void findOneTest() {
    ProductInfo result =  productInfoRepository.findOne("61ae83410ed64850bb3d334845d83bde");
    assertEquals("61ae83410ed64850bb3d334845d83bde", result.getProductId());
  }
  
  @Test
  public void findByProductStatusTest() {
    List<ProductInfo> productInfoList = productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    assertNotEquals(0, productInfoList.size());
  }
  
}

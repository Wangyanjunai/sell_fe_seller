
/**
 * Project Name:sell.<br/> 
 * File Name:ProductServiceImplTest.java.<br/> 
 * Package Name:com.imooc.sell.service.impl.<br/> 
 * Date:2017年12月11日上午10:45:29.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */

package com.imooc.sell.service.impl;

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

import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.enums.ProductStatusEnum;
import com.imooc.sell.service.ProductService;
import com.imooc.sell.utils.UUIDUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClassName: ProductServiceImplTest.<br/>
 * Function: ADD FUNCTION.<br/>
 * Reason: ADD REASON(可选).<br/>
 * Date: 2017年12月11日 上午10:45:29.<br/>
 * Desc: ADD DESC(可选). <br/>
 * 
 * @author 王艳军
 * @version 1.0
 * @since JDK 1.6
 */
@Slf4j
@Rollback
@Transactional
@SpringBootTest(classes = SellApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)

public class ProductServiceImplTest {

  @Autowired
  private ProductService productService;

  @Test
  public void findOneTest() {
    ProductInfo productInfo = productService.findOne("32ea66d543494ba8b0536a8f42249baa");
    assertEquals("32ea66d543494ba8b0536a8f42249baa", productInfo.getProductId());
  }

  @Test
  public void findAllTest() {
    PageRequest request = new PageRequest(0, 10);
    Page<ProductInfo> productInfoPage = productService.findAll(request);
    
    log.info("【分页查询商品列表数据】 totalElements={}, page={}, size={}", productInfoPage.getTotalElements(), productInfoPage.getTotalPages(), productInfoPage.getSize());
    assertNotEquals(0, productInfoPage.getTotalElements());
  }

  @Test
  public void saveTest() {
    ProductInfo productInfo = new ProductInfo();
    productInfo.setProductId(UUIDUtil.genTimstampUUID());
    productInfo.setProductName("皮蛋廋肉粥");
    productInfo.setProductPrice(new BigDecimal(3.2));
    productInfo.setProductStock(100);
    productInfo.setProductDescription("很好喝的粥");
    productInfo.setProductIcon("http://xxxxx.jpg");
    productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
    productInfo.setCategoryType(2);

    ProductInfo result = productService.save(productInfo);
    assertNotNull(result);
  }
  
  @Test
  public void saveOneTest() {
    ProductInfo productInfo = new ProductInfo();
    productInfo.setProductId(UUIDUtil.genTimstampUUID());
    productInfo.setProductName("芒果冰");
    productInfo.setProductPrice(new BigDecimal(20.00));
    productInfo.setProductStock(200);
    productInfo.setProductDescription("冰冰的，很爽，很好喝额！！！！");
    productInfo.setProductIcon("http://www.potato369.com/upload/images/mgb.png");
    productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
    productInfo.setCategoryType(22233322);
    
    ProductInfo result = productService.save(productInfo);
    assertEquals(Integer.valueOf(22233322), result.getCategoryType());
  }

  @Test
  public void onSaleTest() {
    ProductInfo result = productService.onSale("bc71782347834f79a46cba0c96a03cde");
    assertEquals(ProductStatusEnum.UP, result.getProductStatusEnum());
  }

  @Test
  public void offSaleTest() {
    ProductInfo result = productService.offSale("bc71782347834f79a46cba0c96a03cde");
    assertEquals(ProductStatusEnum.DOWN, result.getProductStatusEnum());
  }
  
  @Test
  public void updateOneTest() {
    ProductInfo productInfo = new ProductInfo();
    productInfo.setProductId("bd34d70c13c244b08907a753ef11a2ad");
    productInfo.setProductName("腐竹牛腩饭");
    productInfo.setProductDescription("腐竹牛腩饭饭是一份精美的快餐饭，里面加了腐竹和新鲜的牛腩一起焖煮的一道美味佳肴饭。");
    productInfo.setProductIcon("http://www.potato369.com/upload/images/fznr.png");
    productInfo.setProductPrice(new BigDecimal(0.71));
    productInfo.setCategoryType(5);
    productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
    productInfo.setProductStock(100);
    
    ProductInfo result = productService.save(productInfo);
    assertEquals("bd34d70c13c244b08907a753ef11a2ad", result.getProductId());
  }
  
}

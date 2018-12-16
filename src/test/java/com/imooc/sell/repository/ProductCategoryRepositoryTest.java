/**
 * Project Name:sell
 * File Name:ProductCategoryRepositoryTest.java
 * Package Name:com.imooc.sell.repository
 * Date:2017年12月8日上午12:25:25
 * Copyright (c) 2017,版权所有 (C) 2000-2016 卓望数码技术(深圳)有限公司 www.aspirecn.com All Rights Reserved.
 *
*/

package com.imooc.sell.repository;

import com.imooc.sell.SellApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import com.imooc.sell.dataobject.ProductCategory;

import lombok.extern.slf4j.Slf4j;

/**
 * ClassName:ProductCategoryRepositoryTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年12月8日 上午12:25:25 <br/>
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
@Slf4j
public class ProductCategoryRepositoryTest {

  @Autowired
  private ProductCategoryRepository productCategoryRepository;

  /**
   * saveOneTest:(测试保存对象信息dao方法)
   * 
   * @author admin
   */
  @Test
  public void saveOneTest() {
    ProductCategory productCategory1 = new ProductCategory("热销榜", 1);
    ProductCategory result1 = productCategoryRepository.save(productCategory1);
    assertNotNull(result1);

//    ProductCategory productCategory2 = new ProductCategory("女生最爱", 2);
//    ProductCategory result2 = productCategoryRepository.save(productCategory2);
//    assertNotNull(result2);
  }

  /**
   * 
   * updateTest:(测试修改对象信息Dao方法). <br/>
   * 
   * @author admin
   */
  @Test
  public void updateTest1() {
    ProductCategory category = new ProductCategory("女生专项", 7);
    category.setCategoryType(3);
    productCategoryRepository.save(category);
  }
  /**
   * 
   * updateOneTest:(测试修改对象信息Dao方法). <br/>
   * 
   * @author admin
   */
  @Test
  public void updateOneTest() {
    ProductCategory category = productCategoryRepository.findOne(7);
    category.setCategoryType(4);
    productCategoryRepository.saveAndFlush(category);
  }
  /**
   * 
   * findOneTest:(测试查找对象信息Dao方法). <br/>
   * 
   * @author admin
   */
  @Test
  public void findOneTest() {
    ProductCategory productCategory = productCategoryRepository.findOne(1);
    assertNotNull(productCategory);
    log.info("根据CategoryId=1，查询到的对象信息={}" + productCategory);
  }

  @Test
  public void saveOneTest1() {
    ProductCategory productCategory = new ProductCategory("女生最爱", 3);
    ProductCategory result = productCategoryRepository.save(productCategory);
    assertNotNull(result);
  }

  @Test
  public void findByCategoryTypeInTest() {
    List<Integer> list = Arrays.asList(2, 3, 4);
    List<ProductCategory> results = productCategoryRepository.findByCategoryTypeIn(list);
    assertNotEquals(0, results.size());
  }

  @Test
  public void deleteOneTest() {
    productCategoryRepository.delete(2);
    ProductCategory result = productCategoryRepository.findOne(2);
    assertNull(result);
  }
}

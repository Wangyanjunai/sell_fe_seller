package com.imooc.sell.dataobject.mapper;

import com.imooc.sell.SellApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.sell.dataobject.ProductCategory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;

@Rollback
@Transactional
@SpringBootTest(classes = SellApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
public class ProductCategoryMapperTest {

  @Autowired
  private ProductCategoryMapper productCategoryMapper;

  @Test
  public void insertByMapTest() {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("category_name", "精美快餐");
    map.put("category_type", 100);
    int result = productCategoryMapper.insertByMap(map);
    assertEquals(1, result);
  }

  @Test
  public void insertByMapTest1() {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("category_name", "美味快餐");
    map.put("category_type", 300);
    int result = productCategoryMapper.insertByMap(map);
    assertEquals(1, result);
  }

  @Test
  public void insertByObjectTest() {
    ProductCategory productCategory = new ProductCategory("经典快餐", 200);
    int result = productCategoryMapper.insertByObject(productCategory);
    assertEquals(1, result);
  }

  @Test
  public void findByCategoryTypeTest() {
    ProductCategory result = productCategoryMapper.findByCategoryType(100);
    assertNotNull(result);
  }

  @Test
  public void findByCategoryNameTest() {
    List<ProductCategory> result = productCategoryMapper.findByCategoryName("师兄最不爱");
    assertNotEquals(0, result.size());
  }

  @Test
  public void updateByCategoryTypeTest() {
    int result = productCategoryMapper.updateByCategoryType("师兄最不爱的分类", 101);
    assertEquals(1, result);
  }

  @Test
  public void updateByObjectTest() {
    ProductCategory productCategory = new ProductCategory("师兄最不爱", 101);
    int result = productCategoryMapper.updateByObject(productCategory);
    assertEquals(1, result);
  }

  @Test
  public void deleteByCategoryTypeTest() {
    int result = productCategoryMapper.deleteByCategoryType(10);
    assertEquals(1, result);
  }

  @Test
  public void selectByCategoryTypeTest() {
    ProductCategory result = productCategoryMapper.selectByCategoryType(100);
    assertNotNull(result);
  }
}
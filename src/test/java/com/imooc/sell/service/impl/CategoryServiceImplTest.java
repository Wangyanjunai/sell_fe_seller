/**
 * Project Name:sell
 * File Name:CategoryServiceImplTest.java
 * Package Name:com.imooc.sell.service.impl
 * Date:2017年12月8日下午11:08:13
 * Copyright (c) 2017,版权所有 (C) 2000-2016 卓望数码技术(深圳)有限公司 www.aspirecn.com All Rights Reserved.
 *
*/

package com.imooc.sell.service.impl;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import com.imooc.sell.SellApplication;
import com.imooc.sell.enums.CategoryDeletedEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.imooc.sell.dataobject.ProductCategory;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClassName:CategoryServiceImplTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年12月8日 下午11:08:13 <br/>
 * @author   admin
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Rollback
@Transactional
@SpringBootTest(classes = SellApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
public class CategoryServiceImplTest {
	
	@Autowired
	private CategoryServiceImpl categoryService;
	
	@Test
	public void findOneTest() {
		ProductCategory category = categoryService.findOne(1);
//		assertNotNull(category);
//		assertEquals(new Integer(1), category.getCategoryId());
		assertEquals(new Integer(1), category.getCategoryId());
//		assertNotNull(category);
	}
	@Test
	public void findAllTest() {
		List<ProductCategory> pCategories = categoryService.findAll();
		assertNotEquals(0, pCategories.size());
	}
	@Test
	public void findByCategoryTypeInTest() {
		List<ProductCategory> productCategories = 
				categoryService.findByCategoryTypeIn(Arrays.asList(1,2,22,222,3,22223333,22233322,4));
		assertNotEquals(0, productCategories.size());
	}
	@Test
	public void saveTest() {
		ProductCategory productCategory = new ProductCategory("少妇不最爱",111);
		ProductCategory result = categoryService.save(productCategory);
		assertNotNull(result);
	}
	@Test
	public void deleteOneTest() throws Exception{
		ProductCategory productCategory = categoryService.deleteOne(54);
		assertEquals(productCategory.getIsDeleted(), CategoryDeletedEnum.DELETE);
	}
}
 

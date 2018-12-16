/**
 * Project Name:sell
 * File Name:ProductCategoryRepository.java
 * Package Name:com.imooc.sell.repository
 * Date:2017年12月8日上午12:23:22
 * Copyright (c) 2017,版权所有 (C) 2000-2016 卓望数码技术(深圳)有限公司 www.aspirecn.com All Rights Reserved.
 *
 */
package com.imooc.sell.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.imooc.sell.dataobject.ProductCategory;

/**
 * ClassName:ProductCategoryRepository <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年12月8日 上午12:23:22 <br/>
 * 
 * @author admin
 * @version
 * @since JDK 1.6
 * @see
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
  /**
   * 通过类目type列表查询商品类目列表
   * 
   * @param categoryTypeList
   * @return List<ProductCategory>
   */
  List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}

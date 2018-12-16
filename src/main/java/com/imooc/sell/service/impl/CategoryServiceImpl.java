/**
 * Project Name:sell
 * File Name:CategoryServiceImpl.java
 * Package Name:com.imooc.sell.service.impl
 * Date:2017年12月8日下午11:01:35
 * Copyright (c) 2017,版权所有 (C) 2000-2016 卓望数码技术(深圳)有限公司 www.aspirecn.com All Rights Reserved.
 *
*/

package com.imooc.sell.service.impl;

import java.util.List;

import com.imooc.sell.enums.CategoryDeletedEnum;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.imooc.sell.dataobject.ProductCategory;
import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.repository.ProductCategoryRepository;
import com.imooc.sell.repository.ProductInfoRepository;
import com.imooc.sell.service.CategoryService;

/**
 * ClassName:CategoryServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年12月8日 下午11:01:35 <br/>
 * 
 * @author admin
 * @version
 * @since JDK 1.6
 * @see
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private ProductCategoryRepository productCategoryRepository;
  
  @Autowired
  private ProductInfoRepository productInfoRepository;

  @Override
  public ProductCategory findOne(Integer categoryId) {

    return productCategoryRepository.findOne(categoryId);
  }

  @Override
  public List<ProductCategory> findAll() {

    return productCategoryRepository.findAll();
  }

  @Override
  public Page<ProductCategory> findAll(Pageable pageable) {
    return productCategoryRepository.findAll(pageable);
  }

  @Override
  public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {

    return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
  }

  @Override
  public ProductCategory save(ProductCategory productCategory) {

    return productCategoryRepository.save(productCategory);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.imooc.sell.service.CategoryService#deleteOne(java.lang.Integer)
   */
  @Override
  public ProductCategory deleteOne(Integer categoryId) {
    ProductCategory productCategory = productCategoryRepository.findOne(categoryId);
    if (productCategory == null) {
      log.error("【根据商品类目categoryId，查询商品类目信息】 不存在，categoryId={}", categoryId);
      throw new SellException(ResultEnum.CATEGORY_NOT_EXIST);
    }
    if (productCategory.getIsDeleted() == CategoryDeletedEnum.DELETE.getCode()){
      log.error("【根据商品类目categoryId，查询商品类目信息】 此类目是否标记已删除状态为是删除状态，categoryId={}", categoryId);
      throw new SellException(ResultEnum.CATEGORY_WHETHER_OR_NOT_TO_DELETED_STATUS_ERROR);
    }
    Integer categoryType = productCategory.getCategoryType();
    List<ProductInfo> productInfoList = productInfoRepository.findByCategoryType(categoryType);
    if (productInfoList != null && productInfoList.size() >0) {
      log.warn("【根据商品类目categoryId，查询商品类目信息】 此商品类目下存在一个或者多个商品信息，categoryId={}，categoryType={}", categoryId, categoryType);
      throw new SellException(ResultEnum.CATEGORY_NOT_DELETED);
    }
    productCategory.setIsDeleted(CategoryDeletedEnum.DELETE.getCode());
    return productCategoryRepository.save(productCategory);
  }
}


/**
 * Project Name:sell
 * File Name:BuyerProductController.java
 * Package Name:com.imooc.sell.controller
 * Date:2017年12月11日上午11:21:01
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved
 */ 

package com.imooc.sell.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.imooc.sell.VO.ProductInfoVO;
import com.imooc.sell.VO.ProductVO;
import com.imooc.sell.VO.ResultVO;
import com.imooc.sell.dataobject.ProductCategory;
import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.service.CategoryService;
import com.imooc.sell.service.ProductService;
import com.imooc.sell.utils.ResultVOUtil;

/** 
 * <pre>
 * @ClassName: BuyerProductController
 * @Function:
 * @Reason: 买家商品Controller
 * @Date: 2017年12月11日 上午11:21:01
 * @Desc:
 * @author 王艳军
 * @version 1.0
 * @since JDK 1.6
 * <pre>
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

  @Autowired
  private ProductService productService;
  
  @Autowired
  private CategoryService categoryService;
  
  @SuppressWarnings("unchecked")
  @GetMapping(value = "/list")
  @Cacheable(cacheNames = "product", key = "#sellerId", condition = "#sellerId.length() > 3", unless = "#result.getCode() != 0")
  public ResultVO<List<ProductVO>> list(@RequestParam(name = "sellerId", required = true, defaultValue = "oSkiNv4fBXYxidv0wU_U0UDHNP4M") String sellerId){
    //1.查询所有上架的商品信息
    List<ProductInfo> productInfoList = productService.findUpAll();
    
    //2.查询所有的商品类目（一次性查询）
    //List<Integer> categoryTypeList = new ArrayList<Integer>();
    //传统方法
    //for(ProductInfo productInfo:productInfos){
    //categoryTypeList.add(productInfo.getCategoryType());
    //}
    //精简方法（java8 lambda表达式）
    List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
    List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
    
    //3.数据拼装
    List<ProductVO> productVOList = new ArrayList<ProductVO>();
    for(ProductCategory productCategory : productCategoryList){
      ProductVO productVO = new ProductVO();
      productVO.setCategoryType(productCategory.getCategoryType());
      productVO.setCategoryName(productCategory.getCategoryName());
      List<ProductInfoVO> productInfoVOList = new ArrayList<ProductInfoVO>();
      for (ProductInfo productInfo : productInfoList) { 
        if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
          ProductInfoVO productInfoVO = new ProductInfoVO();
          BeanUtils.copyProperties(productInfo, productInfoVO);
          productInfoVOList.add(productInfoVO);
        }
      }
      productVO.setProductInfoVOs(productInfoVOList);
      productVOList.add(productVO);
    }
    return ResultVOUtil.success(productVOList);
  }
}

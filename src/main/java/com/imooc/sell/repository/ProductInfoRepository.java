/**
 * Project Name:sell
 * File Name:ProductInfoRepository.java
 * Package Name:com.imooc.sell.repository
 * Date:2017年12月8日下午11:52:26
 * Copyright (c) 2017,版权所有 (C) 2000-2016 卓望数码技术(深圳)有限公司 www.aspirecn.com All Rights Reserved.
 *
 */
package com.imooc.sell.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.imooc.sell.dataobject.ProductInfo;
/**
 * ClassName:ProductInfoRepository <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年12月8日 下午11:52:26 <br/>
 * 
 * @author admin
 * @version
 * @since JDK 1.6
 * @see
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

  /**
   * 
   * findByProductStatus:(根据productStatus商品状态查询所有的商品信息). <br/>
   * 
   * @author admin
   * @param productStatus
   * @return
   * @since JDK 1.6
   */
  List<ProductInfo> findByProductStatus(Integer productStatus);

  /**
   * findByCategoryType：(根据categoryType类目Type查询商品信息)
   * 
   * @param categoryType
   * @return
   */
  List<ProductInfo> findByCategoryType(Integer categoryType);

}

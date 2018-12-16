/**
 * Project Name:sell.<br/> 
 * File Name:ProductService.java.<br/> 
 * Package Name:com.imooc.sell.service.<br/> 
 * Date:2017年12月11日上午10:24:02.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */
package com.imooc.sell.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.dto.CartDTO;

/** 
* ClassName: ProductService.<br/>
* Function:   ADD FUNCTION.<br/> 
* Reason:  商品service.<br/>
* Date: 2017年12月11日 上午10:24:02.<br/>
* Desc:  商品service.	 <br/>
* @author 王艳军 
* @version  1.0
* @since JDK 1.6 
*/
public interface ProductService {
  
  ProductInfo findOne(String productId);

  /**
   * 查询所有在架商品列表
   * @return
   */
  List<ProductInfo> findUpAll();

  /**
   * 查询所有商品列表（分页）
   * @param pageable
   * @return
   */
  Page<ProductInfo> findAll(Pageable pageable);

  /**
   * 添加一个商品信息到数据库
   * @param productInfo
   * @return
   */
  ProductInfo save(ProductInfo productInfo);

  /**
   * 加库存
   * @param cartDTOList
   */
  void increaseStock(List<CartDTO> cartDTOList);

  /**
   * 减库存
   * @param cartDTOList
   */
  void decreaseStock(List<CartDTO> cartDTOList);


  /**
   * 上架
   * @param productId
   * @return
   */
  ProductInfo onSale(String productId);

  /**
   * 下架
   * @param productId
   * @return
   */
  ProductInfo offSale(String productId);
}

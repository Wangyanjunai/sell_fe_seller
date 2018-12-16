
/**
 * Project Name:sell.<br/> 
 * File Name:ProductServiceImpl.java.<br/> 
 * Package Name:com.imooc.sell.service.impl.<br/> 
 * Date:2017年12月11日上午10:26:40.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */ 
    
package com.imooc.sell.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.dto.CartDTO;
import com.imooc.sell.enums.ProductStatusEnum;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.repository.ProductInfoRepository;
import com.imooc.sell.service.ProductService;

/** 
* ClassName: ProductServiceImpl.		  <br/> 
* Function:   ADD FUNCTION.<br/> 
* Reason:  ADD REASON(可选).<br/> 
* Date: 2017年12月11日 上午10:26:40.		 <br/> 
* Desc:  ADD DESC(可选).	 <br/> 
* @author 王艳军 
* @version  
* @since JDK 1.6 
*/
@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductInfoRepository productInfoRepository;
  
  /* (non-Javadoc)
   * @see com.imooc.sell.service.ProductService#findOne(java.lang.String)
   */
  @Override
  public ProductInfo findOne(String productId) {
    return productInfoRepository.findOne(productId);
  }

  /* (non-Javadoc)
   * @see com.imooc.sell.service.ProductService#findUpAll()
   */
  @Override
  public List<ProductInfo> findUpAll() {
    return productInfoRepository.findAll();
  }

  /* (non-Javadoc)
   * @see com.imooc.sell.service.ProductService#findAll(org.springframework.data.domain.Pageable)
   */
  @Override
  public Page<ProductInfo> findAll(Pageable pageable) {
    return productInfoRepository.findAll(pageable);
  }

  /* (non-Javadoc)
   * @see com.imooc.sell.service.ProductService#save(com.imooc.sell.dataobject.ProductInfo)
   */
  @Override
  public ProductInfo save(ProductInfo productInfo) {
    return productInfoRepository.save(productInfo);
  }

  /* (non-Javadoc)
   * @see com.imooc.sell.service.ProductService#increaseStock(java.util.List)
   */
  @Override
  @Transactional
  public void increaseStock(List<CartDTO> cartDTOList) {
    for (CartDTO cartDTO: cartDTOList) {
      ProductInfo productInfo = productInfoRepository.findOne(cartDTO.getProductId());
      if (productInfo == null) {
          throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
      }
      Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
      productInfo.setProductStock(result);
      productInfoRepository.save(productInfo);
  }
  }

  /* (non-Javadoc)
   * @see com.imooc.sell.service.ProductService#decreaseStock(java.util.List)
   */
  @Override
  @Transactional
  public void decreaseStock(List<CartDTO> cartDTOList) {
    for (CartDTO cartDTO: cartDTOList) {
      ProductInfo productInfo = productInfoRepository.findOne(cartDTO.getProductId());
      if (productInfo == null) {
          throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
      }
      Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
      if(result < 0){
    	  throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
      }
      productInfo.setProductStock(result);
      productInfoRepository.save(productInfo);
    }
  }

  /* (non-Javadoc)
   * @see com.imooc.sell.service.ProductService#onSale(java.lang.String)
   */
  @Override
  public ProductInfo onSale(String productId) {
    ProductInfo productInfo = productInfoRepository.findOne(productId);
    if (productInfo == null) {
        throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
    }
    if (productInfo.getProductStatusEnum() == ProductStatusEnum.UP) {
        throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
    }

    //更新
    productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
    return productInfoRepository.save(productInfo);

  }

  /* (non-Javadoc)
   * @see com.imooc.sell.service.ProductService#offSale(java.lang.String)
   */
  @Override
  public ProductInfo offSale(String productId) {

    ProductInfo productInfo = productInfoRepository.findOne(productId);
    if (productInfo == null) {
        throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
    }
    if (productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN) {
        throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
    }

    //更新
    productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
    return productInfoRepository.save(productInfo);
  }

}

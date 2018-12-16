
/**
 * Project Name:sell.<br/> 
 * File Name:ProductInfoVO.java.<br/> 
 * Package Name:com.imooc.sell.VO.<br/> 
 * Date:2017年12月11日上午11:35:38.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */ 
    
package com.imooc.sell.VO;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * <pre>
 * @ClassName:ProductInfoVO
 * @Function:ADD FUNCTION
 * @Reason:商品类目详情
 * @Date:2017年12月11日 上午11:35:38 
 * @Desc:ADD DESC(可选)
 * @Author 王艳军
 * @Version 1.0
 * @Since JDK 1.6
 * </pre>
 */
@Data
public class ProductInfoVO implements Serializable {

  private static final long serialVersionUID = 1192721805912686397L;

  @JsonProperty(value = "id")
  private String productId;
  
  @JsonProperty(value = "name")
  private String productName;
  
  @JsonProperty(value = "price")
  private BigDecimal productPrice;
  
  @JsonProperty(value = "description")
  private String productDescription;
  
  @JsonProperty(value = "icon")
  private String productIcon;
}

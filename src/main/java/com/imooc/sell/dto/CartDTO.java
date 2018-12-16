
/**
 * Project Name:sell.<br/> 
 * File Name:CartDTO.java.<br/> 
 * Package Name:com.imooc.sell.dto.<br/> 
 * Date:2017年12月11日上午10:25:35.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */ 
    
package com.imooc.sell.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <pre>
 * ClassName:CartDTO
 * Function:ADD FUNCTION
 * Reason:购物车
 * Date:2017年12月11日 上午10:25:35
 * Desc:ADD DESC(可选)
 * @author 王艳军
 * @version  1.0
 * @since JDK 1.6
 * </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO implements Serializable{

  /**serialVersionUID：序列号*/
  private static final long serialVersionUID = 8300837976789349474L;

  /** productId：商品Id. */
  private String productId;

  /** productQuantity：商品数量. */
  private Integer productQuantity;
}

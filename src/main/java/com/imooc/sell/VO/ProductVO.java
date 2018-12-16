
/**
 * Project Name:sell.<br/> 
 * File Name:ProductVO.java.<br/> 
 * Package Name:com.imooc.sell.VO.<br/> 
 * Date:2017年12月11日上午11:32:49.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */ 
    
package com.imooc.sell.VO;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * <pre>
 * @ClassName:ProductVO
 * @Function:ADD FUNCTION
 * @Reason:商品类目
 * @Date:2017年12月11日 上午11:32:49
 * @Desc:ADD DESC(可选)
 * @Author 王艳军 
 * @Version 1.0 
 * @Since JDK 1.6
 * </pre> 
 */
@Data
public class ProductVO implements Serializable {

  private static final long serialVersionUID = -1619866944061488544L;

  @JsonProperty(value = "name")
  private String categoryName;
  
  @JsonProperty(value = "type")
  private Integer categoryType; 
  
  @JsonProperty(value = "foods")
  private List<ProductInfoVO> productInfoVOs; 
}

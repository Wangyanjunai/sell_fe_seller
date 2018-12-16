
/**
 * Project Name:sell.<br/> 
 * File Name:ProductStatusEnum.java.<br/> 
 * Package Name:com.imooc.sell.enums.<br/> 
 * Date:2017年12月11日上午10:18:19.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */

package com.imooc.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * ClassName: ProductStatusEnum. <br/>
 * Function: ADD FUNCTION.<br/>
 * Reason: ADD REASON(可选).<br/>
 * Date: 2017年12月11日 上午10:18:19. <br/>
 * Desc: ADD DESC(可选). <br/>
 * 
 * @author 王艳军
 * @version 1.0
 * @since JDK 1.6
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ProductStatusEnum implements CodeEnum<Object> {

  UP(0, "在架"),

  DOWN(1, "下架"),

  ;

  private Integer code;

  private String message;

}

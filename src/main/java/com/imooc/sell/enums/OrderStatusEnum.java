
/**
 * Project Name:sell.<br/> 
 * File Name:OrderStatusEnum.java.<br/> 
 * Package Name:com.imooc.sell.enums.<br/> 
 * Date:2017年12月11日下午3:21:06.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */ 
    
package com.imooc.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** 
* ClassName: OrderStatusEnum.		  <br/> 
* Function:   ADD FUNCTION.<br/> 
* Reason:  ADD REASON(可选).<br/> 
* Date: 2017年12月11日 下午3:21:06.		 <br/> 
* Desc:  ADD DESC(可选).	 <br/> 
* @author 王艳军 
* @version  
* @since JDK 1.6 
*/
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum OrderStatusEnum implements CodeEnum<Object>{

  NEW(0,"新下单"),
  FINISHED(1,"已完结"),
  CANCEL(2,"已取消");
  
  private Integer code;

  private String message;

}

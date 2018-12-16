
/**
 * Project Name:sell.<br/> 
 * File Name:PayStatusEnum.java.<br/> 
 * Package Name:com.imooc.sell.enums.<br/> 
 * Date:2017年12月11日下午3:29:29.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */ 
    
package com.imooc.sell.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
* ClassName: PayStatusEnum.		  <br/> 
* Function:   ADD FUNCTION.<br/> 
* Reason:  ADD REASON(可选).<br/> 
* Date: 2017年12月11日 下午3:29:29.		 <br/> 
* Desc:  ADD DESC(可选).	 <br/> 
* @author 王艳军 
* @version  
* @since JDK 1.6 
*/
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum PayStatusEnum implements CodeEnum<Object>{
  
  WAITING(0, "等待支付"),
  
  SUCCESS(1, "支付成功"),
  
  ;
  
  private Integer code;

  private String message;  

}

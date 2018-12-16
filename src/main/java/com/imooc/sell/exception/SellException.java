
/**
 * Project Name:sell.<br/> 
 * File Name:SellException.java.<br/> 
 * Package Name:com.imooc.sell.exception.<br/> 
 * Date:2017年12月11日上午10:33:22.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */ 
    
package com.imooc.sell.exception;

import com.imooc.sell.enums.ResultEnum;

import lombok.Getter;

/** 
* ClassName: SellException.		  <br/> 
* Function:   ADD FUNCTION.<br/> 
* Reason:  ADD REASON(可选).<br/> 
* Date: 2017年12月11日 上午10:33:22.		 <br/> 
* Desc:  ADD DESC(可选).	 <br/> 
* @author 王艳军 
* @version  
* @since JDK 1.6 
*/
@Getter
public class SellException extends RuntimeException{
  
  private static final long serialVersionUID = -3170669599060979531L;
  
  private Integer code;

  public SellException(ResultEnum resultEnum) {
      super(resultEnum.getMessage());
      this.code = resultEnum.getCode();
  }

  public SellException(Integer code, String message) {
      super(message);
      this.code = code;
  }
  
}

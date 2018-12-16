
/**
 * Project Name:sell.<br/> 
 * File Name:EnumUtil.java.<br/> 
 * Package Name:com.imooc.sell.utils.<br/> 
 * Date:2017年12月11日上午10:41:50.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */ 
    
package com.imooc.sell.utils;

import com.imooc.sell.enums.CodeEnum;

/** 
* ClassName: EnumUtil.		  <br/> 
* Function:   ADD FUNCTION.<br/> 
* Reason:  ADD REASON(可选).<br/> 
* Date: 2017年12月11日 上午10:41:50.		 <br/> 
* Desc:  ADD DESC(可选).	 <br/> 
* @author 王艳军 
* @version  
* @since JDK 1.6 
*/
public class EnumUtil {
  
  public static <T extends CodeEnum<?>> T getByCode(Integer code, Class<T> enumClass) {
    for (T each: enumClass.getEnumConstants()) {
        if (code.equals(each.getCode())) {
            return each;
        }
    }
    return null;
}
}

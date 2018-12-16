
/**
 * Project Name:sell.<br/> 
 * File Name:UUIDUtil.java.<br/> 
 * Package Name:com.imooc.sell.utils.<br/> 
 * Date:2017年12月11日上午10:54:51.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */ 
    
package com.imooc.sell.utils;

import java.util.UUID;

/** 
* ClassName: UUIDUtil.		  <br/> 
* Function:   ADD FUNCTION.<br/> 
* Reason:  ADD REASON(可选).<br/> 
* Date: 2017年12月11日 上午10:54:51.		 <br/> 
* Desc:  ADD DESC(可选).	 <br/> 
* @author 王艳军 
* @version  
* @since JDK 1.6 
*/

public class UUIDUtil {

  /**
   * 创建订单号，订单详情
   * @return
   */
  public static synchronized String genTimstampUUID(){
    return DateUtil.getTimestamp().concat(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 15).toLowerCase());
  }

  /**
   * 创建产品id，商品id
   * @return
   */
  public static synchronized String gen32UUID(){
    return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
  }
  
  public static void main(String[] args) {
    System.out.println(UUIDUtil.genTimstampUUID());
    System.out.println(UUIDUtil.gen32UUID());
  }
}


/**
 * Project Name:sell.<br/> 
 * File Name:BuyerService.java.<br/> 
 * Package Name:com.imooc.sell.service.<br/> 
 * Date:2017年12月12日下午10:36:19.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */ 
    
package com.imooc.sell.service;

import com.imooc.sell.dto.OrderDTO;

/** 
* ClassName: BuyerService.		  <br/> 
* Function:   ADD FUNCTION.<br/> 
* Reason:  ADD REASON(可选).<br/> 
* Date: 2017年12月12日 下午10:36:19.		 <br/> 
* Desc:  ADD DESC(可选).	 <br/> 
* @author 王艳军 
* @version  
* @since JDK 1.6 
*/
public interface BuyerService {
  
  /**
   * 根据买家微信openid查询一个订单
   * @param openid
   * @param orderid
   * @return
   */
  OrderDTO findOrderOne(String openid,String orderid);
  
  /**
   * 根据买家微信openid取消订单
   * @param openid
   * @param orderid
   * @return
   */
  OrderDTO cancelOrder(String openid,String orderid);
  
}

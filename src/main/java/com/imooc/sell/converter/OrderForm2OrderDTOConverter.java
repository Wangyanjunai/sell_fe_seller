
/**
 * Project Name:sell.<br/> 
 * File Name:OrderForm2OrderDTOConverter.java.<br/> 
 * Package Name:com.imooc.sell.converter.<br/> 
 * Date:2017年12月12日下午4:51:32.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */

package com.imooc.sell.converter;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.form.OrderForm;

import lombok.extern.slf4j.Slf4j;

/**
 * ClassName: OrderForm2OrderDTOConverter. <br/>
 * Function: ADD FUNCTION.<br/>
 * Reason: ADD REASON(可选).<br/>
 * Date: 2017年12月12日 下午4:51:32. <br/>
 * Desc: ADD DESC(可选). <br/>
 * 
 * @author 王艳军
 * @version
 * @since JDK 1.6
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

  public static OrderDTO convert(OrderForm orderForm) {
    Gson gson = new Gson();
    OrderDTO orderDTO = new OrderDTO();
    orderDTO.setBuyerName(orderForm.getName());
    orderDTO.setBuyerAddress(orderForm.getAddress());
    orderDTO.setBuyerOpenid(orderForm.getOpenid());
    orderDTO.setBuyerPhone(orderForm.getPhone());
    List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
    try {
        orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {}.getType());
    } catch (Exception e) {
      log.error("转换出错，string={}", orderForm.getItems());
      throw new SellException(ResultEnum.PARAM_ERROR);
    }
    orderDTO.setOrderDetailList(orderDetailList);
    return orderDTO;
  }
}

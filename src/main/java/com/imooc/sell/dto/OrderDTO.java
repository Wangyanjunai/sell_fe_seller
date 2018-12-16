
/**
 * Project Name:sell.<br/> 
 * File Name:OrderDTO.java.<br/> 
 * Package Name:com.imooc.sell.dto.<br/> 
 * Date:2017年12月11日下午5:03:28.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */ 
    
package com.imooc.sell.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import com.imooc.sell.utils.EnumUtil;
import com.imooc.sell.utils.serializer.Date2LongSerializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * @ClassName:OrderDTO
 * @Function:
 * @Reason:
 * @Date: 2017年12月11日 下午5:03:28
 * @Desc:
 * @author 王艳军
 * @version
 * @since JDK 1.6
 * </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

  /**
   * orderId：订单编号
   */
  @Id
  private String orderId;
  
  /**
   * buyerName：买家名字
   */
  private String buyerName;
  
  /**
   * buyerPhone：买家电话
   */
  private String buyerPhone;
  
  /**
   * buyerAddress：买家地址
   */
  private String buyerAddress;
  
  /**
   * buyerOpenid：买家微信openid
   */
  private String buyerOpenid;
  
  /**
   * orderAmount：订单总金额
   */
  private BigDecimal orderAmount;
  
  /**
   * orderStatus：订单状态，默认是新下单
   */
  private Integer orderStatus;
  
  /**
   * payStatus：支付状态，默认0为未支付
   */
  private Integer payStatus;
  
  /**
   * createTime：创建时间
   */
  @JsonSerialize(using = Date2LongSerializer.class)
  private Date createTime;
  
  /**
   * updateTime：更新时间
   */
  @JsonSerialize(using = Date2LongSerializer.class)
  private Date updateTime;
  
  @Transient
  private List<OrderDetail> orderDetailList;

  @JsonIgnore
  public OrderStatusEnum getOrderStatusEnum(){
    return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
  }

  @JsonIgnore
  public PayStatusEnum getPayStatusEnum(){
    return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
  }

  /**
   * cip：买家微信客户端ip
   */
  private String cip;

  /**
   * fingerPrint：浏览器指纹
   */
  private String fingerPrint;

  /**
   * jsonSceneInfo：json格式场景信息字符串
   */
  private String jsonSceneInfo;
}

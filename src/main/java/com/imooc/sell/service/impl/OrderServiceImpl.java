
/**
 * Project Name:sell.<br/> 
 * File Name:OrderServiceImpl.java.<br/> 
 * Package Name:com.imooc.sell.service.impl.<br/> 
 * Date:2017年12月11日下午5:12:29.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */

package com.imooc.sell.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.imooc.sell.converter.OrderMaster2OrderDTOConverter;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import com.imooc.sell.service.*;
import com.imooc.sell.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dataobject.OrderMaster;
import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.dto.CartDTO;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.repository.OrderDetailRepository;
import com.imooc.sell.repository.OrderMasterRepository;
import org.springframework.util.CollectionUtils;

/**
 * ClassName: OrderServiceImpl.<br/>
 * Function: ADD FUNCTION.<br/>
 * Reason: ADD REASON(可选).<br/>
 * Date: 2017年12月11日 下午5:12:29.<br/>
 * Desc: 商品类目service实现. <br/>
 * 
 * @author 王艳军
 * @version 1.0
 * @since JDK 1.6
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

  @Autowired
  private ProductService productService;

  @Autowired
  private OrderDetailRepository orderDetailRepository;

  @Autowired
  private OrderMasterRepository orderMasterRepository;

  @Autowired
  private PushMessageService pushMessageService;

  @Autowired
  private WebSocket webSocket;

  @Autowired
  private PayService payService;

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.imooc.sell.service.OrderService#create(com.imooc.sell.dto.OrderDTO)
   */
  @Override
  @Transactional
  public OrderDTO create(OrderDTO orderDTO) {
    BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
    String orderId = UUIDUtil.genTimstampUUID();
    // 1.查询商品（数量，价格）
    for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
      ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
      if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
//          throw new ResponseBankException();
      }
      // 2.计算订单总价
      orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
      // 订单详情入库
      BeanUtils.copyProperties(productInfo, orderDetail);
      orderDetail.setProductCategory(productInfo.getCategoryType());
      orderDetail.setDetailId(UUIDUtil.genTimstampUUID());
      orderDetail.setOrderId(orderId);
      orderDetail.setBuyerOpenid(orderDTO.getBuyerOpenid());
      orderDetailRepository.save(orderDetail);
    }
    // 3.写入订单数据库（orderMaster和orderDetail）
    OrderMaster orderMaster = new OrderMaster();
    orderDTO.setOrderId(orderId);
    BeanUtils.copyProperties(orderDTO, orderMaster);
    orderMaster.setOrderAmount(orderAmount);
    orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
    orderMaster.setPayStatus(PayStatusEnum.WAITING.getCode());
    orderDTO.setOrderAmount(orderMaster.getOrderAmount());
    orderMasterRepository.save(orderMaster);

    // 4.扣库存
    List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
    productService.decreaseStock(cartDTOList);

    //5、发生Websocket消息
    webSocket.sendMessage("有新的美团外卖订单！！订单号：" + orderDTO.getOrderId());

    //6、推送订单创建成功模板消息
    pushMessageService.pushOrderSuccess(orderDTO);

    return orderDTO;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.imooc.sell.service.OrderService#findOne(java.lang.String)
   */
  @Override
  public OrderDTO findOne(String orderId) {
    OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
    if (orderMaster == null){
      throw new SellException(ResultEnum.ORDER_NOT_EXIST);
    }
    List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
    if (CollectionUtils.isEmpty(orderDetailList)){
      throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
    }
    OrderDTO orderDTO = new OrderDTO();
    BeanUtils.copyProperties(orderMaster,orderDTO);
    orderDTO.setOrderDetailList(orderDetailList);
    return orderDTO;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.imooc.sell.service.OrderService#findList(java.lang.String,
   * org.springframework.data.domain.Pageable)
   */
  @Override
  public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
    Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid,pageable);
    List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());
    return new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.imooc.sell.service.OrderService#cancel(com.imooc.sell.dto.OrderDTO)
   */
  @Override
  @Transactional
  public OrderDTO cancel(OrderDTO orderDTO) {
    OrderMaster orderMaster = new OrderMaster();
    //1.判断订单的状态
    if (orderDTO.getOrderStatus() != OrderStatusEnum.NEW.getCode()){
      log.error("【取消订单】 订单状态不正确， orderId={}，orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
      throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
    }
    //2.修改订单的状态
    orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
    BeanUtils.copyProperties(orderDTO,orderMaster);
    OrderMaster updateResult = orderMasterRepository.save(orderMaster);
    if (updateResult == null){
      log.error("【取消订单】 更新失败，orderMaster={}", orderMaster);
      throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
    }
    //3.返回库存
    if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
      log.error("【取消订单】 订单中无商品详情，orderDTO={}", orderDTO);
      throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
    }
    List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->new CartDTO(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());
    productService.increaseStock(cartDTOList);
    
    //4.如果订单已经支付，需要退款
    if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
      payService.refund(orderDTO);
    }
    //5、推送订单取消通知模板消息
    pushMessageService.pushOrderStatus(orderDTO,"您好，您的订单已取消，订单状态已通过系统确认！！！", "我们会尽快原路退回您支付的金额到您的支付账户，具体到账时间以银行时间为准。感谢您对土豆互联的支持，有任何疑问可拨打客服电话：0755-86969315");
    
    return orderDTO;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.imooc.sell.service.OrderService#finsh(com.imooc.sell.dto.OrderDTO)
   */
  @Override
  @Transactional
  public OrderDTO finsh(OrderDTO orderDTO) {
    //1.判断订单状态
    if (orderDTO.getOrderStatus() != OrderStatusEnum.NEW.getCode()){
      log.error("【完结订单】 订单状态不正确， orderId={}，orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
      throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
    }
    //2.修改订单状态为完结状态
    orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
    OrderMaster orderMaster = new OrderMaster();
    BeanUtils.copyProperties(orderDTO,orderMaster);
    OrderMaster updateResult = orderMasterRepository.save(orderMaster);
    if (updateResult == null){
      log.error("【完结订单】 更新失败，orderMaster={}", orderMaster);
      throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
    }
    //3、推送订单完结状态模板消息
    pushMessageService.pushOrderStatus(orderDTO, "您好，您的订单已经完成，订单状态已通过系统确认！！！", "请您核对收到的商品是否与您购买的一致！感谢您对土豆互联的支持，有任何疑问可拨打客服电话：0755-86969315");

    return orderDTO;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.imooc.sell.service.OrderService#paid(com.imooc.sell.dto.OrderDTO)
   */
  @Override
  @Transactional
  public OrderDTO paid(OrderDTO orderDTO) {
    //1.1 判断订单状态
    if (OrderStatusEnum.NEW.getCode() != orderDTO.getOrderStatus()){
      log.error("【支付订单完成】 订单状态不正确， orderId={}，orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
      throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
    }
    //1.2 判断支付状态
    if (PayStatusEnum.WAITING.getCode() != orderDTO.getPayStatus()){
      log.error("【支付订单完成】 订单支付状态不正确， orderId={}，orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
      throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
    }
    //2.修改支付状态
    orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
    OrderMaster orderMaster = new OrderMaster();
    BeanUtils.copyProperties(orderDTO, orderMaster);
    OrderMaster updateResult = orderMasterRepository.save(orderMaster);
    if (updateResult == null){
      log.error("【支付订单完成】 更新失败，orderMaster={}", orderMaster);
      throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
    }
    //3、推送订单支付成功模板消息
    pushMessageService.pushPaySuccess(orderDTO);

    return orderDTO;
  }

  @Override
  public Page<OrderDTO> findList(Pageable pageable) {
	Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);

    List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

    return new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());
  }

}

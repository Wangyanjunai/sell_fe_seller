 
/**
 * Project Name:sell
 * File Name:BuyerOrderController.java
 * Package Name:com.imooc.sell.controller
 * Date:2017年12月12日下午4:29:16
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved
 */

package com.imooc.sell.controller;

import com.imooc.sell.VO.ResultVO;
import com.imooc.sell.constant.RedisConstant;
import com.imooc.sell.converter.OrderForm2OrderDTOConverter;
import com.imooc.sell.converter.ProductForm2CartDTOConverter;
import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.dto.CartDTO;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ProductStatusEnum;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.form.OrderForm;
import com.imooc.sell.form.ProductForm;
import com.imooc.sell.service.BuyerService;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.ProductService;
import com.imooc.sell.utils.CookieUtil;
import com.imooc.sell.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * ClassName: BuyerOrderController
 * Function:
 * Reason:
 * Date: 2017年12月12日 下午4:29:16
 * Desc:
 * @author 王艳军
 * @version
 * @since JDK 1.6
 * </pre>
 */
@Slf4j
@RestController
@RequestMapping(value = "/buyer/order")
public class BuyerOrderController {

  @Autowired
  private OrderService orderService;

  @Autowired
  private BuyerService buyerService;

  @Autowired
  private ProductService productService;

  // 1、 创建订单
  @SuppressWarnings({ "unchecked", "static-access" })
  @PostMapping(value = "/create")
  public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      log.error("【创建订单】 参数不正确，orderForm={}", orderForm);
      throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
    }
    OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
    if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
      log.error("【创建订单】 购物车不能为空");
      throw new SellException(ResultEnum.CART_EMPTY);
    }
    OrderDTO createResult = orderService.create(orderDTO);
    Map<String, String> resultMap = new HashMap<String, String>();
    resultMap.put("orderId", createResult.getOrderId());
    return new ResultVOUtil().success(resultMap);
  }

  // 2、查询订单列表
  @SuppressWarnings("unchecked")
  @GetMapping(value = "/list")
  public ResultVO<List<OrderDTO>> list(@RequestParam(name = "openid", required = true) String openid,
                                       @RequestParam(name = "page", required = true, defaultValue = "0") Integer page,
                                       @RequestParam(name = "size", required = true, defaultValue = "10") Integer size) {
    if (StringUtils.isEmpty(openid)) {
      log.error("【查询订单列表】openid为空");
      throw new SellException(ResultEnum.PARAM_ERROR);
    }
    Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
    PageRequest pageRequest = new PageRequest(page, size, sort);
    Page<OrderDTO> orderDTOPage = null;
    try {
      orderDTOPage = orderService.findList(openid, pageRequest);
    } catch (Exception e) {
      log.error("【查询订单列表】 失败，出现错误，或者是数据库链接错误");
      throw new SellException(ResultEnum.OTHER_ERROR);
    }
    return ResultVOUtil.success(orderDTOPage.getContent());
  }

  // 3、查询订单详情
  @SuppressWarnings("unchecked")
  @GetMapping(value = "/detail")
  public ResultVO<List<OrderDTO>> detail(@RequestParam(name = "openid", required = false) String openid,
      @RequestParam(name = "orderId", required = false) String orderId) {
    // TODO 不安全，需要改进
    // OrderDTO orderDTO = null;
    // try {
    // orderDTO = orderService.findOne(orderId);
    // } catch (Exception e) {
    // log.error("【查询订单详情出现错误】 error={}", e);
    // throw new SellException(ResultEnum.OTHER_ERROR);
    // }
    OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
    return ResultVOUtil.success(orderDTO);
  }

  // 4、取消订单
  @PostMapping(value = "/cancel")
  public ResultVO<?> cancel(@RequestParam(name = "openid", required = false) String openid,
      @RequestParam(name = "orderId", required = false) String orderId) {
    // TODO 不安全，需要改进
    // OrderDTO orderDTO = null;
    // try {
    // orderDTO = orderService.findOne(orderId);
    // } catch (Exception e) {
    // log.error("【取消订单出现错误】 error={}", e);
    // throw new SellException(ResultEnum.OTHER_ERROR);
    // }
    // OrderDTO cancelResult = null;
    // try {
    // cancelResult = orderService.cancel(orderDTO);
    // } catch (Exception e) {
    // log.error("【取消订单出现错误】 error={}", e);
    // throw new SellException(ResultEnum.OTHER_ERROR);
    // }
    buyerService.cancelOrder(openid, orderId);
    return ResultVOUtil.success();
  }

  // 5、添加到购物车
  @PostMapping(value = "/addToCart")
  public ModelAndView addToCart(@Valid ProductForm productForm, BindingResult bindingResult,
      HttpServletResponse response, Map<String, Object> map) {
    if (bindingResult.hasErrors()) {
      log.error("【将商品添加到购物车】 参数不正确，productForm={}", productForm);
      throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
    }
    // 第一步、根据客户端传送过来的购物车数据，查找商品信息数据，并校验数据是否符合要求
    ProductInfo productInfo = productService.findOne(productForm.getProductId());
    if (productInfo == null) {
      log.error("【将商品添加到购物车】 商品信息不存在，productId={}", productForm.getProductId());
      throw new SellException(ResultEnum.PRODUCT_NOT_EXIST.getCode(), ResultEnum.PRODUCT_NOT_EXIST.getMessage());
    }
    if (productInfo.getProductStatus() == ProductStatusEnum.DOWN.getCode()) {
      log.error("【将商品添加到购物车】 商品已经下架，productId={}", productForm.getProductId());
      throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR.getCode(), ResultEnum.PRODUCT_STATUS_ERROR.getMessage());
    }
    if (productInfo.getProductStock() < productForm.getProductQuantity()) {
      log.error("【将商品添加到购物车】 商品库存不足，productId={}", productForm.getProductId());
      throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR.getCode(), ResultEnum.PRODUCT_STOCK_ERROR.getMessage());
    }
    CartDTO cartDTO = ProductForm2CartDTOConverter.convert(productForm);
    // 第二步：设置cartDTO保存到客户端浏览器cookie缓存
    // 设置cookie缓存过期时间为2小时
    CookieUtil.set(response, cartDTO, RedisConstant.EXPIRE);
    map.put("cartDTO", cartDTO);
    map.put("productInfo", productInfo);
    return new ModelAndView("cart/detail", map);
  }
}

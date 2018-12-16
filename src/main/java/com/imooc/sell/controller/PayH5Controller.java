package com.imooc.sell.controller;

import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.imooc.sell.config.prop.ProjectUrlProperties;
import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dataobject.ProductCategory;
import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import com.imooc.sell.service.CategoryService;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.PayH5Service;
import com.imooc.sell.service.ProductService;
import com.imooc.sell.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
/**
 * Describe:
 * 微信H5支付文档地址：https://pay.weixin.qq.com/wiki/doc/api/H5_sl.php?chapter=15_1
 * H5支付是指商户在微信客户端外的移动端网页展示商品或服务，用户在前述页面确认使用微信支付时，商户发起本服务呼起微信客户端进行支付。
 * 主要用于触屏版的手机浏览器请求微信支付的场景。可以方便的从外部浏览器唤起微信支付。
 * 
 * @Author 王艳军
 * @Date 2017/12/26 15:58:36
 */
@Controller
@Slf4j
@RequestMapping(value = "/payH5")
public class PayH5Controller {

  @Autowired
  private OrderService orderService;

  @Autowired
  private PayH5Service payH5Service;

  @Autowired
  private ProductService productService;

  @Autowired
  private CategoryService categoryService;
  
  @Autowired
  private ProjectUrlProperties ProjectUrlConfig;

  @GetMapping(value = "/index")
  public ModelAndView index(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                            @RequestParam(name = "size", required = true, defaultValue = "10") Integer size,
                            Map<String, Object> map) {
    Sort sort = new Sort(Sort.Direction.DESC, "updateTime","productStock");
    PageRequest pageRequest = new PageRequest(page - 1, size, sort);
    Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
    List<ProductCategory> productCategoryList = categoryService.findAll();
    map.put("productInfoPage", productInfoPage);
    map.put("productCategoryList", productCategoryList);
    map.put("currentPage", page);
    map.put("size", size);
    return new ModelAndView("product/listProd", map);
  }

    /**
     *
     * @param productId
     * @param cip
     * @param cname
     * @param returnUrl
     * @param fingerPrint
     * @return
     */
  @GetMapping(value = "/create")
  public void create(@RequestParam(name = "productId", required = true) String productId,
                                    @RequestParam(name = "buyerName", required = true) String buyerName,
                                    @RequestParam(name = "buyerPhone", required = true) String buyerPhone,
                                    @RequestParam(name = "buyerAddress", required = true) String buyerAddress,
                                    @RequestParam(name = "productQuantity", required = true) Integer productQuantity,
                                    @RequestParam(name = "cip", required = true) String cip,
                                    @RequestParam(name = "cname", required = false) String cname,
                                    @RequestParam(name = "returnUrl", required = false) String returnUrl,
                                    @RequestParam(name = "fingerPrint", required = false) String fingerPrint,
                                    Map<String, Object> map) {
    log.info("客户端浏览器Post Ajax请求提交过来的数据为：productId={}, cip={}, cname={}, returnUrl={}, fingerPrint={}", productId, cip, cname, returnUrl, fingerPrint);
    // 1、创建订单
    OrderDTO orderDTO = new OrderDTO();
    String orderId = UUIDUtil.genTimstampUUID();
    orderDTO.setOrderId(orderId);
    orderDTO.setBuyerAddress(buyerAddress);
    orderDTO.setBuyerName(buyerName);
    List<OrderDetail> orderDetailList = new ArrayList<>();
    ProductInfo productInfo = productService.findOne(productId);
    OrderDetail detail = OrderDetail.builder().build();
    detail.setProductId(productInfo.getProductId());
    detail.setProductQuantity(productQuantity);
    detail.setProductIcon(productInfo.getProductIcon());
    detail.setDetailId(UUIDUtil.gen32UUID());
    detail.setOrderId(orderId);
    detail.setProductName(productInfo.getProductName());
    detail.setProductPrice(productInfo.getProductPrice());
    orderDetailList.add(detail);
    orderDTO.setOrderDetailList(orderDetailList);
    orderDTO.setBuyerPhone(buyerPhone);
    orderDTO.setOrderAmount(productInfo.getProductPrice());
    orderDTO.setOrderStatus(OrderStatusEnum.NEW.getCode());
    orderDTO.setPayStatus(PayStatusEnum.WAITING.getCode());
    orderDTO.setCip(cip);
    orderDTO.setFingerPrint(fingerPrint);
    // 2、发起H5微信支付
    WxPayUnifiedOrderResult payResponse = null;
    String redirectedUrl = ProjectUrlConfig.getDomainUrl() + returnUrl;
    try {
      orderService.create(orderDTO);
      payResponse = payH5Service.create(orderDTO);
      redirectedUrl = URLEncoder.encode(payResponse.getMwebUrl() + "&redirect_url" + redirectedUrl, "UTF-8");
      ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
      HttpServletResponse response = requestAttributes.getResponse();
      response.sendRedirect(redirectedUrl);
    } catch (WxPayException e) {
      String errmsg =" 创建订单信息出现WxPayException";
      log.error("【微信H5支付订单】  "+ errmsg +", e={}", e);

    } catch (Exception e) {
      String errmsg =" 创建订单信息出现Exception";
      log.error("【微信H5支付订单】 "+ errmsg +", e={}", e);
    }
  }

  /**
   * 微信H5支付异步通知
   * 
   * @param notifyData
   * @return
   */
  @PostMapping(value = "/notify")
  public ModelAndView notify(@RequestBody String notifyData) {
    try {
      payH5Service.notify(notifyData);
    } catch (WxPayException e) {
      log.error("【微信H5支付异步通知】 异步通知订单支付信息出现WxPayException, e={}", e);

    } catch (Exception e) {
      log.error("【微信H5支付异步通知】 异步通知订单支付信息出现Exception, e={}", e);

    }

    // 3、返回给微信处理结果
    return new ModelAndView("pay/success");
  }
}

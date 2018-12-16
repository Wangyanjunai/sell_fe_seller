package com.imooc.sell.service.impl;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.service.PayH5Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Describe:
 *
 * @Author 王艳军
 * @Date 2017/12/26 16:33:37
 */
@Service
public class PayH5ServiceImpl implements PayH5Service {
  
    @Autowired
    private WxPayServiceImpl wxPayService;

    /**
     * 微信H5支付订单
     *
     * @param orderDTO
     * @return WxPayUnifiedOrderResult
     * @throws WxPayException
     */
    @Override
    public WxPayUnifiedOrderResult create(OrderDTO orderDTO) throws WxPayException {
        WxPayUnifiedOrderRequest request =  WxPayUnifiedOrderRequest.newBuilder()
                .fingerprint(orderDTO.getFingerPrint())
                .body("土豆互联科技（深圳）有限公司--微信点餐")
                .spbillCreateIp(orderDTO.getCip())
                .openid(orderDTO.getBuyerOpenid())
                .build();
        request.setOutTradeNo(orderDTO.getOrderId());
        request.setTotalFee(orderDTO.getOrderAmount().intValue() * 100);
        WxPayUnifiedOrderResult wxPayUnifiedOrderResult = wxPayService.unifiedOrder(request);
        return wxPayUnifiedOrderResult;
    }

    /**
     * 微信H5支付异步通知结果
     *
     * @param notifyData
     * @return WxPayOrderNotifyResult
     * @throws WxPayException
     */
    @Override
    public WxPayOrderNotifyResult notify(String notifyData) throws WxPayException {
        return wxPayService.parseOrderNotifyResult(notifyData);
    }

    /**
     * 微信H5支付退款
     *
     * @param orderDTO
     * @return WxPayRefundResult
     * @throws WxPayException
     */
    @Override
    public WxPayRefundResult refund(OrderDTO orderDTO) throws WxPayException {
        WxPayRefundRequest request = new WxPayRefundRequest();
        return wxPayService.refund(request);
    }
}

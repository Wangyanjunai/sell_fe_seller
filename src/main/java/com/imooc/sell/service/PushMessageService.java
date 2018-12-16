package com.imooc.sell.service;

import com.imooc.sell.dto.OrderDTO;

/**
 * Describe:模板消息推送
 *
 * @Author 王艳军
 * @Date 2017/12/18 19:16:38
 */
public interface PushMessageService {

    /**
     * 推送订单状态变更模板消息
     * @param orderDTO
     */
    void pushOrderStatus(OrderDTO orderDTO, String first, String remark);

    /**
     * 推送下单成功模板消息
     * @param orderDTO
     */
    void pushOrderSuccess(OrderDTO orderDTO);

    /**
     * 推送订单支付成功模板消息
     * @param orderDTO
     */
    void pushPaySuccess(OrderDTO orderDTO);
}

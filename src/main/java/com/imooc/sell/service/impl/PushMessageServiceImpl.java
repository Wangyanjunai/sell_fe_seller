package com.imooc.sell.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.sell.config.prop.WechatMpProperties;
import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.service.PushMessageService;
import com.imooc.sell.utils.DateUtil;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
/**
 * Describe:
 *
 * @Author 王艳军
 * @Date 2017/12/18 19:22:10
 */
@Service
@Slf4j
public class PushMessageServiceImpl implements PushMessageService {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatMpProperties wechatAccountConfig;

    @Override
    public void pushOrderStatus(OrderDTO orderDTO, String first, String remark) {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setTemplateId(wechatAccountConfig.getTemplateId().get("orderStatus"));
        templateMessage.setToUser(orderDTO.getBuyerOpenid());
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first", first,"red"),
                new WxMpTemplateData("keyword1", DateUtil.getTime()),
                new WxMpTemplateData("keyword2", orderDTO.getOrderStatusEnum().getMessage()),
                new WxMpTemplateData("remark", remark, "green")
        );
        templateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        }catch (WxErrorException e){
            log.error("【推送订单状态变更模板消息】 失败，出现错误 e={}" , e.getLocalizedMessage());
        }

    }

    /**
     * 推送下单成功模板消息
     *
     * @param orderDTO
     */
    @Override
    public void pushOrderSuccess(OrderDTO orderDTO) {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setTemplateId(wechatAccountConfig.getTemplateId().get("orderSuccess"));
        templateMessage.setToUser(orderDTO.getBuyerOpenid());
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        StringBuffer orderProductNames = new StringBuffer();
        for(OrderDetail orderDetail:orderDetailList){
          orderProductNames.append(orderDetail.getProductName()).append(" ");
        }
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first","恭喜您，订单下单成功通知！！！","red"),
                new WxMpTemplateData("keyword1", orderProductNames.toString()),
                new WxMpTemplateData("keyword2", "￥"+ orderDTO.getOrderAmount()),
                new WxMpTemplateData("keyword3", DateUtil.getTime()),
                new WxMpTemplateData("remark","我们将立即安排发放【" + orderProductNames.toString() + "】到您的游戏账户","green")
        );
        templateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        }catch (WxErrorException e){
            log.error("【推送订单状态变更模板消息】 失败，出现错误 e={}" ,e.getLocalizedMessage());
        }
    }

    /**
     * 推送订单支付成功模板消息
     *
     * @param orderDTO
     */
    @Override
    public void pushPaySuccess(OrderDTO orderDTO) {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setTemplateId(wechatAccountConfig.getTemplateId().get("paySuccess"));
        templateMessage.setToUser(orderDTO.getBuyerOpenid());
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first","您好，您的订单已支付成功！！！","red"),
                new WxMpTemplateData("keyword1", orderDTO.getOrderId()),
                new WxMpTemplateData("keyword2", DateUtil.getTime()),
                new WxMpTemplateData("keyword3", "￥"+orderDTO.getOrderAmount()),
                new WxMpTemplateData("keyword4", "微信支付"),
                new WxMpTemplateData("remark","感谢您对土豆互联的支持，有任何疑问可拨打客服电话：0755-86969315","green")
        );
        templateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        }catch (WxErrorException e){
            log.error("【推送订单状态变更模板消息】 失败，出现错误 e={}" ,e.getLocalizedMessage());
        }

    }
}

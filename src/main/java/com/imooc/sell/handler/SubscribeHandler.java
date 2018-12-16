package com.imooc.sell.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.imooc.sell.builder.NewsBuilder;
import com.imooc.sell.dataobject.UserInfo;
import com.imooc.sell.service.UserInfoService;
import com.imooc.sell.utils.JsonUtils;
import com.imooc.sell.utils.UUIDUtil;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Component
public class SubscribeHandler extends AbstractHandler {

  @Autowired
  private UserInfoService userInfoService;

  /**
   * 一般处理
   * @param wxMessage
   * @param context
   * @param weixinService
   * @param sessionManager
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService weixinService, WxSessionManager sessionManager) throws WxErrorException {


    if (this.logger.isDebugEnabled()) {
      this.logger.info("【普通（搜索或者消息推送关注的）】新关注用户 OPENID: " + wxMessage.getFromUser());
    }

    // 获取微信用户基本信息
    WxMpUser userWxInfo = weixinService.getUserService().userInfo(wxMessage.getFromUser(), null);
    if (this.logger.isDebugEnabled()) {
      this.logger.info("【新关注用户】  WxMpUser={}" + JsonUtils.toJson(userWxInfo));
    }
    if (userWxInfo != null) {
      // 添加关注用户到本地数据库
      userInfoService.add(WxMpUser2UserInfo(userWxInfo));
    }

    WxMpXmlOutMessage responseResult = null;
    try {
      responseResult = handleSpecial(wxMessage);
    } catch (Exception e) {
      this.logger.error(e.getMessage(), e);
    }

    if (responseResult != null) {
      return responseResult;
    }

    try {
      NewsBuilder newsBuilder = new NewsBuilder();
      WxMpXmlOutNewsMessage.Item item1 = new WxMpXmlOutNewsMessage.Item();
      item1.setTitle("亲，终于等到您了！推荐几本热门小说，给您看看：");
      item1.setDescription("");
      item1.setUrl("https://www.potato369.com");
      return newsBuilder.build("", wxMessage, weixinService);
    } catch (Exception e) {
      this.logger.error(e.getMessage(), e);
    }

    return null;
  }

  /**
   * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
   * @param wxMessage
   * @return
   * @throws Exception
   */
  private WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage)  throws Exception {
    return null;
  }

  /**
   *
   * @param wxMpUser
   * @return
   */
  private UserInfo WxMpUser2UserInfo(WxMpUser wxMpUser) {
    UserInfo userInfo = UserInfo.builder().build();
    userInfo.setId(UUIDUtil.gen32UUID());
    userInfo.setOpenId(wxMpUser.getOpenId());
    userInfo.setNickName(wxMpUser.getNickname());
    return userInfo;
  }
}

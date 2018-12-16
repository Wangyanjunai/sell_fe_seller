package com.imooc.sell.config.prop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author 王艳军
 * @Date 2017-12-17 15:00
 */
@Data
@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "projectUrl")
public class ProjectUrlProperties {

  /**
   * <pre>
   * projectName：项目名字
   * </pre>
   */
  private String projectName;

  /**
   * <pre>
   * domainUrl：项目部署解析的域名地址URL
   * </pre>
   */
  private String domainUrl;

  /**
   * <pre>
   * wechatMpAuthorizeUrl：微信公众平台授权地址URL
   * </pre>
   */
  private String wechatMpAuthorizeUrl;

  /**
   * <pre>
   * wechatMpAuthorizeUrlUserInfo：微信公众平台授权获取用户信息地址URL
   * </pre>
   */
  private String wechatMpAuthorizeUrlUserInfo;

  /**
   * <pre>
   * wechatOpenAuthorizeUrl：微信开放平台授权登录地址URL
   * </pre>
   */
  private String wechatOpenAuthorizeUrl;

  /**
   * <pre>
   * wechatOpenAuthorizeUrlUserInfo：微信开放平台授权登录获取用户信息地址URL
   * </pre>
   */
  private String wechatOpenAuthorizeUrlUserInfo;

  /**
   * <pre>
   * loginUrl：跳转登录地址URL
   * </pre>
   */
  private String loginUrl;

  /**
   * <pre>
   * logoutUrl：跳转登出地址URL
   * </pre>
   */
  private String logoutUrl;

  /**
   * <pre>
   * errorUrl：错误页面地址URL
   * </pre>
   */
  private String errorUrl;

  /**
   * <pre>
   * successUrl：成功页面地址URL
   * </pre>
   */
  private String successUrl;
}

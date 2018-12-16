package com.imooc.sell.config.prop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.Map;

/**
 * describe:微信相关配置属性
 *
 * @author 王艳军
 * @date 2017/12/13 17:06:17
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "wechat.mp")
public class WechatMpProperties {

  /**
   * <pre>
   * @serialField 1、mpAppId：微信公众平台appid
   * </pre>
   */
  private String mpAppId;

  /**
   * <pre>
   * @serialField 2、mpAppSecret：微信公众平台密钥
   * </pre>
   */
  private String mpAppSecret;

  /**
   * <pre>
   * @serialField 3、openAppid：微信开放平台appid
   * </pre>
   */
  private String openAppid;

  /**
   * <pre>
   * @serialField 4、openAppSecret：微信开放平台密钥
   * </pre>
   */
  private String openAppSecret;

  /**
   * <pre>
   * @serialField 5、mchId：微信商户号
   * </pre>
   */
  private String mchId;

  /**
   * <pre>
   * @serialField 6、mchKey：微信商户密钥
   * </pre>
   */
  private String mchKey;

  /**
   * <pre>
   * @serialField 7、subAppId：服务商模式下的子商户公众账号ID，普通模式请不要配置，请在配置文件中将对应项删除
   * </pre>
   */
  private String subAppId;

  /**
   * <pre>
   * @serialField 8、subMchId：服务商模式下的子商户号，普通模式请不要配置，最好是请在配置文件中将对应项删除
   * </pre>
   */
  private String subMchId;

  /**
   * <pre>
   * @serialField 9、keyPath：微信商户证书路径
   * </pre>
   */
  private String keyPath;

  /**
   * <pre>
   * @serialField 10、notifyUrl：微信支付异步通知地址URL
   * </pre>
   */
  private String notifyUrl;

  /**
   * <pre>
   * @serialField 11、微信模板id
   * </pre>
   */
  private Map<String, String> templateId;
  
  /**
   * <pre>
   * @serialField 12、 公众平台服务器验证token
   * </pre>
   */
  private String token;
  
  /**
   * <pre>
   * @serialField 13、 公众平台消息加密密钥
   * </pre>
   */
  private String aesKey;
}

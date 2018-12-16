
/**
 * <pre>
 * Project Name:sell
 * File Name:OrderForm.java
 * Package Name:com.imooc.sell.form
 * Date:2017年12月12日下午4:32:46
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved
 * </pre>
 */

package com.imooc.sell.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * <pre>
 * ClassName:OrderForm
 * Function:
 * Reason:
 * Date: 2017年12月12日 下午4:32:46
 * Desc:
 * @author 王艳军
 * @version
 * @since JDK 1.6
 * </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderForm {

  /**
   * <pre>
   *  name：买家姓名
   * </pre>
   */
  @NotEmpty(message = "姓名必填")
  private String name;

  /**
   * <pre>
   *  phone：买家手机号
   * </pre>
   */
  @NotEmpty(message = "手机号必填")
  private String phone;

  /**
   * <pre>
   *  address：买家收获地址
   * </pre>
   */
  @NotEmpty(message = "地址必填")
  private String address;

  /**
   * <pre>
   *  openid：买家微信openid
   * </pre>
   */
  @NotEmpty(message = "微信openid必填")
  private String openid;

  /**
   * <pre>
   * items：买家购物车
   * </pre>
   */
  @NotEmpty(message = "购物车不能为空")
  private String items;
}

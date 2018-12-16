/**
 * Project Name:sell
 * File Name:ProductForm.java
 * Package Name:com.imooc.sell.form
 * Date:2017年12月16日下午9:03:19
 * Copyright (c) 2017,版权所有 (C) 2000-2016 卓望数码技术(深圳)有限公司 www.aspirecn.com All Rights Reserved.
 *
*/

package com.imooc.sell.form;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * @ClassName:
 * @Function:
 * @Reason:
 * @Date: 2017年12月16日 下午9:03:19
 * @author admin
 * @version
 * @since JDK 1.6
 * @see
 * </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductForm {

  /**
   * <pre>
   * productId：商品编号：主键
   * </pre>
   */
  private String productId;

  /**
   * <pre>
   * productName：商品名称
   * </pre>
   */
  @NotBlank(message = "商品名称不能为空")
  private String productName;

  /**
   * <pre>
   * productPrice：商品单价
   * </pre>
   */
  @DecimalMin(value = "0.00", message = "商品单价必须大于0.00")
  private BigDecimal productPrice;

  /**
   * <pre>
   * productStock：商品库存
   * </pre>
   */
  @Min(value = 0, message = "商品库存不能为负数")
  private Integer productStock;

  /**
   * <pre>
   * procductDescription：商品描述
   * </pre>
   */
  @Size(min = 0, max = 64)
  private String productDescription;

  /**
   * <pre>
   * procductIcon：小图片
   * </pre>
   */
  private String productIcon;

  /**
   * <pre>
   * categoryType：类目编号
   * </pre>
   */
  @NotNull(message = "类目编号不能为空")
  private Integer categoryType;

  /**
   * <pre>
   * productQuantity：商品数量
   * </pre>
   */
  private Integer productQuantity;
}

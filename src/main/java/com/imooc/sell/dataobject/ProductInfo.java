/**
 * Project Name:sell
 * File Name:ProductInfo.java
 * Package Name:com.imooc.sell.dataobject
 * Date:2017年12月8日下午11:32:28
 * Copyright (c) 2017,版权所有 (C) 2000-2016 卓望数码技术(深圳)有限公司 www.aspirecn.com All Rights Reserved.
 *
 */
package com.imooc.sell.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.hibernate.annotations.DynamicUpdate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imooc.sell.enums.ProductStatusEnum;
import lombok.Data;
import com.imooc.sell.utils.EnumUtil;

/**
 * <pre>
 * @className:ProductInfo
 * @function:
 * @reason:
 * @date:2017年12月8日 下午11:32:28
 * @author: admin
 * @version: 1.0
 * @since: JDK 1.6
 * @see: 1.0
 * </pre>
 */
@Data
@Builder
@DynamicUpdate
@AllArgsConstructor
@Entity(name = "ProductInfo")
@Table(name  = "product_info")
public class ProductInfo implements Serializable {

  /**
   *@Fields serialVersionUID：序列号
   */
  private static final long serialVersionUID = 4564468027461751520L;

  /**
   *@Fields productId：产品id，主键
   */
  @Id
  @Column(name = "product_id", nullable = false, length = 32)
  private String productId;

  /**
   *@Fields productName：产品名称
   */
  @Column(name = "product_name", nullable = false, length = 64)
  private String productName;

  /**
   *@Fields productPrice：产品单价
   */
  @Column(name = "product_price", nullable = false, length = 8)
  private BigDecimal productPrice;

  /**
   *@Fields productStock：库存
   */
  @Column(name = "product_stock", nullable = false, length = 11)
  private Integer productStock;

  /**
   *@Fields productDescription：产品描述
   */
  @Column(name = "product_description", nullable = true, length = 1024)
  private String productDescription;

  /**
   *@Fields productIcon：产品小图
   */
  @Column(name = "product_icon", nullable = true, length = 512)
  private String productIcon;

  /**
   *@Fields productStatus：产品状态，0-在架；1-下架，“默认：0-在架“
   */
  @Builder.Default
  @Column(name = "product_status", nullable = true, length = 3)
  private Integer productStatus = ProductStatusEnum.UP.getCode();

  /**
   *@Fields categoryType：类目编号
   */
  @Column(name = "category_type", nullable = true, length = 11)
  private Integer categoryType;

  /**
   *@Fields createTime：创建时间
   */
  @Column(name = "create_time", nullable = false)
  private Date createTime;

  /**
   *@Fields updateTime：修改时间
   */
  @Column(name = "update_time", nullable = false)
  private Date updateTime;

  public ProductInfo() {
    super();
  }

  public ProductInfo(String productName, BigDecimal productPrice, Integer productStock, Integer categoryType, Integer productStatus) {
    super();
    this.productName = productName;
    this.productPrice = productPrice;
    this.productStock = productStock;
    this.categoryType = categoryType;
    this.productStatus = productStatus;
  }

  @JsonIgnore
  public ProductStatusEnum getProductStatusEnum() {
    return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
  }
}


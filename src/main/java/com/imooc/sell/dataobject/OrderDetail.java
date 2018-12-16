
/**
 * Project Name:sell.<br/> 
 * File Name:OrderDetail.java.<br/> 
 * Package Name:com.imooc.sell.dataobject.<br/> 
 * Date:2017年12月11日下午3:35:56.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */

package com.imooc.sell.dataobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <pre>
 * @className:OrderDetail
 * @function:
 * @reason:
 * @date:2017年12月11日 下午3:35:56
 * @desc:
 * @author 王艳军
 * @version 1.0
 * @since JDK 1.6
 * </pre>
 */
@Data
@Builder
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "OrderDetail")
@Table(name  = "order_detail")
public class OrderDetail implements Serializable {

  /**
   *@Fields serialVersionUID：序列号
   */
  private static final long serialVersionUID = -2922952467636846483L;

  /**
   *@Fields detailId：详情id，主键
   */
  @Id
  @Column(name = "detail_id", nullable = false, length = 32)
  private String detailId;

  /**
   *@Fields orderId：订单id，外键
   */
  @Column(name = "order_id", nullable = false, length = 32)
  private String orderId;

  /**
   *@Fields productId：产品id，外键
   */
  @Column(name = "product_id", nullable = false, length = 32)
  private String productId;

  /**
   * productCategory：类目编号，外键
   */
  @Column(name = "product_category", nullable = false, length = 11)
  private Integer productCategory;

  /**
   *@Fields buyerOpenid：买家微信openid，外键
   */
  @Column(name = "buyer_openid", nullable = false, length = 64)
  private String buyerOpenid;

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
   *@Fields productQuantity：购买数量
   */
  @Column(name = "product_quantity", nullable = false, length = 11)
  private Integer productQuantity;

  /**
   *@Fields productIcon：产品小图
   */
  @Column(name = "product_icon", nullable = true, length = 512)
  private String productIcon;

  /**
   *@Fields createTime：创建时间
   */
  @Column(name = "create_time", nullable = false)
  private Date createTime;

  /**
   *@Fields updateTime：更新时间
   */
  @Column(name = "update_time", nullable = false)
  private Date updateTime;
}

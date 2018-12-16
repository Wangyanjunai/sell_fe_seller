
/**
 * <pre>
 * @ProjectName:sell 
 * @FileName:OrderMaster.java 
 * @PackageName:com.imooc.sell.dataobject
 * @Date:2017年12月11日下午3:12:04 
 * @Copyright (c) 2018版权所有 (C) 2016-2036 土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved
 * </pre>
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
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import lombok.Data;

/**
 * <pre>
 * @ClassName: OrderMaster
 * @Function:
 * @Reason:
 * @Date: 2017年12月11日 下午3:12:04
 * @Desc:
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
@Entity(name = "OrderMaster")
@Table(name  = "order_master")
public class OrderMaster implements Serializable {

  /**
   *@Fields serialVersionUID：序列号
   */
  private static final long serialVersionUID = 3765541505739277088L;

  /**
   *@Fields orderId：订单id，主键
   */
  @Id
  @Column(name = "order_id", nullable = false, length = 32)
  private String orderId;

  /**
   *@Fields buyerName：买家名字
   */
  @Column(name = "buyer_name", nullable = false, length = 32)
  private String buyerName;

  /**
   *@Fields buyerPhone：买家电话
   */
  @Column(name = "buyer_phone", nullable = false, length = 15)
  private String buyerPhone;

  /**
   *@Fields buyerAddress：买家地址
   */
  @Column(name = "buyer_address", nullable = false, length = 128)
  private String buyerAddress;

  /**
   *@Fields buyerOpenid：买家微信openId
   */
  @Column(name = "buyer_openid", nullable = false, length = 64)
  private String buyerOpenid;

  /**
   *@Fields orderAmount：订单总金额
   */
  @Column(name = "order_amount", nullable = false, length = 8)
  private BigDecimal orderAmount;

  /**
   *@Fields orderStatus：订单状态，0-新订单；1-已完结；2-已取消，“默认：0-新订单”
   */
  @Builder.Default
  @Column(name = "order_status", nullable = false, length = 3)
  private Integer orderStatus = OrderStatusEnum.NEW.getCode();

  /**
   *@Fields payStatus：订单支付状态，0-等待支付；1-支付成功，“默认：0-等待支付”
   */
  @Builder.Default
  @Column(name = "pay_status", nullable = false, length = 3)
  private Integer payStatus = PayStatusEnum.WAITING.getCode();

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

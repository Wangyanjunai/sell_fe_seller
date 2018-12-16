package com.imooc.sell.dataobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author 王艳军
 * @Date 2017-12-17 13:24
 */
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class SellerInfo implements Serializable {

  /**
   *@Fields serialVersionUID：序列号
   */
  private static final long serialVersionUID = 2291413426974623929L;

  /**
   *@Fields sellerId：卖家用户id
   */
  @Id
  @Column(name = "seller_id", nullable=false, length=32)
  private String sellerId;

  /**
   *@Fields username：卖家用户名字
   */
  @Column(name = "username", nullable=false, length=32)
  private String username;

  /**
   *@Fields password：登录密码
   */
  @Column(name = "password", nullable=false, length=32)
  private String password;

  /**
   *@Fields openid：微信openid
   */
  @Column(name = "openid", nullable=false, length=64)
  private String openid;

  /**
   *@Fields createTime：创建时间
   */
  @Column(name = "create_time", nullable=false)
  private Date createTime;

  /**
   *@Fields updateTime：修改时间
   */
  @Column(name = "update_time", nullable=false)
  private Date updateTime;

  public SellerInfo(String username, String password, String openid) {
    super();
    this.username = username;
    this.password = password;
    this.openid = openid;
  }
}

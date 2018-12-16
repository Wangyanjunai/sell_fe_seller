/**
 * Project Name:sell
 * File Name:ProductCategory.java
 * Package Name:com.imooc.sell.dataobject
 * Date:2017年12月8日上午12:14:26
 * Copyright (c) 2017,版权所有 (C) 2000-2016 卓望数码技术(深圳)有限公司 www.aspirecn.com All Rights Reserved.
 *
*/

package com.imooc.sell.dataobject;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.imooc.sell.enums.CategoryDeletedEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

/**
 * <pre>
 * @ClassName:ProductCategory
 * @Function:
 * @Reason:
 * @Date:2017年12月8日 上午12:14:26
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
@Entity(name = "ProductCategory")
@Table(name = "product_category")
public class ProductCategory implements Serializable {

  /**
   *@Fields serialVersionUID:序列号
   */
  private static final long serialVersionUID = 3486046846836931032L;

  /**
   *@Fields categoryId：类目id，主键
   */
  @Id
  @GeneratedValue
  @Column(name = "category_id", nullable = false, length = 11)
  private Integer categoryId;

  /**
   * categoryName：类目名称
   */
  @Column(name = "category_name", nullable = false, length = 64)
  private String categoryName;

  /**
   *@Fields categoryType：类目编号
   */
  @Column(name = "category_type", nullable = false, length = 11)
  private Integer categoryType;

  /**
   *@Fields isDeleted：类目是否删除，0-否；1-是，“默认：0-否”
   */
  @Builder.Default
  @Column(name = "is_deleted", nullable = false, length = 3)
  private Integer isDeleted = CategoryDeletedEnum.NOT_DELETE.getCode();

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

  public ProductCategory() {
    super();
  }

  public ProductCategory(String categoryName, Integer categoryType) {
    super();
    this.categoryName = categoryName;
    this.categoryType = categoryType;
  }
}

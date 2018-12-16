package com.imooc.sell.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
/**
 * <pre>
 * @Description 卖家商品类目表单From
 * @Author 王艳军
 * @Date 2017-12-17 10:41
 * </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryForm {

  /**
   * <pre>
   * categoryId：类目id
   * </pre>
   */
  private Integer categoryId;

  /**
   * <pre>
   * categoryName：类目名称
   * </pre>
   */
  private String categoryName;

  /**
   * <pre>
   * categoryType：类目编号
   * </pre>
   */
  private Integer categoryType;

  /**
   * <pre>
   * isDeleted：此类目是否标记已删除，默认否
   * </pre>
   */
  private Integer isDeleted;

  /**
   * <pre>
   * createTime：创建时间
   * </pre>
   */
  private Date createTime;

  /**
   * <pre>
   * updateTime：修改时间
   * </pre>
   */
  private Date updateTime;
}

package com.imooc.sell.dataobject.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.imooc.sell.dataobject.ProductCategory;

import java.util.List;
import java.util.Map;

/**
 * Describe:
 *
 * @Author 王艳军
 * @Date 2017/12/19 15:44:40
 */
public interface ProductCategoryMapper {
  /**
   * 通过map
   * 
   * @param map
   * @return
   */
  @Insert("insert into product_category (category_name, category_type) values (#{category_name, jdbcType=VARCHAR}, #{category_type, jdbcType=INTEGER})")
  int insertByMap(Map<String, Object> map);

  /**
   * 通过object
   * 
   * @param productCategory
   * @return
   */
  @Insert("insert into product_category (category_name, category_type) values (#{categoryName, jdbcType=VARCHAR}, #{categoryType, jdbcType=INTEGER})")
  int insertByObject(ProductCategory productCategory);

  /**
   * 通过category_id查询
   * @param categoryType
   * @return
   */
  @Select("select category_id, category_name, category_type, create_time, update_time from product_category where category_type= #{categoryType, jdbcType=INTEGER}")
  @Results({ @Result(column = "category_id", property = "categoryId"),
      @Result(column = "category_name", property = "categoryName"),
      @Result(column = "category_type", property = "categoryType"),
      @Result(column = "create_time", property = "createTime"),
      @Result(column = "update_time", property = "updateTime") })
  ProductCategory findByCategoryType(Integer categoryType);
  
  /**
   * 通过category_name查询
   * @param categoryName
   * @return
   */
  @Select("select category_id, category_name, category_type, create_time, update_time from product_category where category_name= #{categoryName, jdbcType=VARCHAR}")
  @Results({ @Result(column = "category_id", property = "categoryId"),
      @Result(column = "category_name", property = "categoryName"),
      @Result(column = "category_type", property = "categoryType"),
      @Result(column = "create_time", property = "createTime"),
      @Result(column = "update_time", property = "updateTime") })
  List<ProductCategory> findByCategoryName(String categoryName);
  
  /**
   * 通过category_name字段更新
   * @param categoryName
   * @param categoryType
   * @return
   */
  @Update("update product_category set category_name = #{categoryName, jdbcType=VARCHAR} where category_type = #{categoryType, jdbcType=INTEGER}")
  int updateByCategoryType(@Param("categoryName")String categoryName, @Param("categoryType")Integer categoryType);
  
  /**
   * 通过Object对象更新
   * @param productCategory
   * @return
   */
  @Update("update product_category set category_name = #{categoryName, jdbcType=VARCHAR} where category_type = #{categoryType, jdbcType=INTEGER}")
  int updateByObject(ProductCategory productCategory);
  
  /**
   * 通过category_type字段删除
   * @param categoryType
   * @return
   */
  @Delete("delete from product_category where category_type = #{categoryType, jdbcType=INTEGER}")
  int deleteByCategoryType(Integer categoryType);

  /**
   * 通过xml文件配置sql查找ProductCategory
   * @param categoryType
   * @return
   */
  ProductCategory selectByCategoryType(Integer categoryType);
}

/**
 * Project Name:sell
 * File Name:CategoryService.java
 * Package Name:com.imooc.sell.service
 * Date:2017年12月8日下午10:54:28
 * Copyright (c) 2017,版权所有 (C) 2000-2016 卓望数码技术(深圳)有限公司 www.aspirecn.com All Rights Reserved.
 *
*/

package com.imooc.sell.service;
/**
 * ClassName:CategoryService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年12月8日 下午10:54:28 <br/>
 * @author   admin
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */

import java.util.List;

import com.imooc.sell.dataobject.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
  
	/**
	 * 
	 * findOne:(根据categoryId查询一个商品信息). <br/>
	 *
	 * @author admin
	 * @param categoryId
	 * @return
	 * @since JDK 1.6
	 */
	ProductCategory findOne(Integer categoryId);
	
	/**
	 * 
	 * findAll:(查询所有的ProductCategory商品信息). <br/>
	 *
	 * @author admin
	 * @return
	 * @since JDK 1.6
	 */
	List<ProductCategory> findAll();

	/**
	 *
	 * findAll:(分页查询所有的ProductCategory商品信息). <br/>
	 *
	 * @author admin
	 * @return
	 * @since JDK 1.6
	 */
	Page<ProductCategory> findAll(Pageable pageable);
	/**
	 * 
	 * findByCategoryTypeIn:(通过categoryType查询商品信息). <br/>
	 * @author admin
	 * @param categoryTypeList
	 * @return
	 * @since JDK 1.6
	 */
	List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
	/**
	 * 
	 * save:(新增一个ProductCategory商品信息). <br/>
	 *
	 * @author admin
	 * @param productCategory
	 * @return
	 * @since JDK 1.6
	 */
	ProductCategory save(ProductCategory productCategory);
	
	/**
     * 
     * deleteOne:(根据categoryI删除一个类目，不是真正物理上的删除，只是逻辑删除，修改为是否删除状态，). <br/>
     *
     * @author admin
     * @param categoryId
     * @return
     * @since JDK 1.6
     */
	ProductCategory deleteOne(Integer categoryId);
}


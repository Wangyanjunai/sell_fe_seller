/**
 * Project Name:sell 
 * File Name:OrderMasterRepository.java
 * Package Name:com.imooc.sell.repository
 * Date:2017年12月11日下午3:48:15
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved
 */ 
package com.imooc.sell.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.imooc.sell.dataobject.OrderMaster;
/** 
 * <pre>
 * @className:OrderMasterRepository
 * @function:
 * @reason:
 * @date:2017年12月11日 下午3:48:15 
 * @Desc:
 * @author: 王艳军
 * @version:
 * @since: JDK 1.6
 * </pre>
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
  Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}

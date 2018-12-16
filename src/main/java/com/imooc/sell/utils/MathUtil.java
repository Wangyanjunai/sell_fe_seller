
/**
 * Project Name:sell.<br/> 
 * File Name:MathUtil.java.<br/> 
 * Package Name:com.imooc.sell.utils.<br/> 
 * Date:2017年12月14日下午6:10:32.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */ 
    
package com.imooc.sell.utils;


/**
 * <pre>
 * ClassName:MathUtil
 * Function:数字比较工具类
 * Reason:
 * Date:2017年12月14日 下午6:10:32
 * Desc:
 * @author 王艳军
 * @version  
 * @since JDK 1.6
 * </pre>
 */

public class MathUtil {

  private static final Double MONEY_RANG = 0.01;
  /**
   * 比较两个金额是否相等
   * @param double1 金额1
   * @param double2 金额2
   * @return Boolean
   */
  public static Boolean equals(Double double1, Double double2){
    Double result =  Math.abs(double1 - double2);
    if (result < MONEY_RANG) {
      return true;
    } else {
      return false;
    }
  }
}

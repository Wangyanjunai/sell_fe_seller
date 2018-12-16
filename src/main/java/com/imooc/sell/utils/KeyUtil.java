/**
 * Project Name:sell
 * File Name:KeyUtil.java
 * Package Name:com.imooc.sell.utils
 * Date:2017年12月12日上午6:15:50
 * Copyright (c) 2017,版权所有 (C) 2000-2016 卓望数码技术(深圳)有限公司 www.aspirecn.com All Rights Reserved.
 *
*/

package com.imooc.sell.utils;

import java.util.Random;

/**
 * ClassName:KeyUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年12月12日 上午6:15:50 <br/>
 * @author   admin
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class KeyUtil {
	
	public static synchronized String genUniquKey(){
		Random random = new Random();
		Integer number = random.nextInt(900000) + 100000;
		return System.currentTimeMillis() + String.valueOf(number);
	}
}


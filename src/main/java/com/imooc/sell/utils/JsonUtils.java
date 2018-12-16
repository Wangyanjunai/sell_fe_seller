/**
 * <pre>
 * Project Name: sell
 * File Name: JsonUtils.java 
 * Package Name: com.imooc.sell.utils
 * Create Date: 2018年11月29日 下午12:12:50
 * Copyright (c) 2018, 版权所有 (C) 2016-2036 土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved
 * </pre>
 */
package com.imooc.sell.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

/**
 * <pre>
 * @PackageName com.imooc.sell.utils
 * @ClassName JsonUtils
 * @Desc Json字符串或者对象处理工具类
 * @WebSite https://www.potato369.com
 * @Author 王艳军
 * @Date 2018年11月29日 下午12:12:50
 * @CreateBy Eclipse IDEA Neon.3 Release(4.6.3)
 * @Copyright Copyright (c) 2016 ~ 2020 版权所有 (C) 土豆互联科技（深圳）有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
public class JsonUtils {
	public static String toJson(Object obj) {
		return WxMpGsonBuilder.create().toJson(obj);
	}

	public static String toJsonStr(Object object) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		return gson.toJson(object);
	}
}


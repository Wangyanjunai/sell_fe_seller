/**
 * <pre>
 * Project Name: sell
 * File Name: JedisDemo.java
 * Package Name: com.imooc.sell.jedis
 * Create Date: 2018年10月27日 下午7:44:57
 * Copyright (c) 2018, 版权所有 (C) 2016-2036 土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved。
 * </pre>
 */
package com.imooc.sell.jedis;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * <pre>
 * @PackageName com.imooc.sell.jedis
 * @ClassName JedisDemo
 * @Desc 描述此类实现的功能
 * @WebSite https://www.potato369.com
 * @Author Administrator
 * @Date 2018年10月27日 下午7:44:57
 * @CreateBy Eclipse IDEA Neon.3 Release(4.6.3)
 * @Copyright Copyright (c) 2016 ~ 2020 版权所有 (C) 土豆互联科技（深圳）有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Slf4j
public class JedisDemo {

	/**
	 * 
	 * test1:(单例的方式获取Jedis资源)
	 *
	 * @author Administrator
	 * @since JDK 1.6
	 */
	@Test
	public void test1() {
		//设置主机地址和端口号
		Jedis jedis = new Jedis("192.168.1.8", 6379);
		//保存数据
		jedis.set("website", "https://www.imooc.com");
		//获取数据
		String value = jedis.get("name");
		log.info("【获取到的数据】value={}", value);
		if (jedis != null) {
			jedis.close();
		}
	}
	
	/**
	 * 
	 * test2:(连接池的方式获取Jedis资源)
	 *
	 * @author Administrator
	 * @since JDK 1.6
	 */
	@Test
	public void test2() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(30);
		config.setMaxIdle(10);
		
		Jedis jedis = null;
		JedisPool jedisPool = null;
		
		try {
			jedisPool = new JedisPool("192.168.1.8", 6379);
			jedis = jedisPool.getResource();
			jedis.set("site", "https://www.potato369.com");
			String value = jedis.get("name");
			log.info("【获取到的数据】value={}", value);
		} catch (Exception e) {
			log.error("【获取Jedis连接】出现错误", e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
			if (jedisPool != null) {
				jedisPool.close();
			}
		}
		
	}
}


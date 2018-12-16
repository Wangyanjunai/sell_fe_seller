/**
 * Project Name:sell.<br/> 
 * File Name:HttpAspect.java.<br/> 
 * Package Name:com.imooc.sell.aspect.<br/> 
 * Date:2018年1月3日下午5:33:28.<br/> 
 * Copyright (c) 2018, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */ 
package com.imooc.sell.aspect;

import javax.servlet.http.HttpServletRequest;

import com.imooc.sell.utils.WwwUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import lombok.extern.slf4j.Slf4j;
/**
  * <pre>
  * @className:HttpAspect
  * @function:ADD FUNCTION
  * @reason:Http请求拦截切面对象
  * @date:2018年1月3日 下午5:33:28
  * @desc:ADD DESC(可选)
  * @author 王艳军
  * @version 1.0
  * @since JDK 1.6
  * </pre>
  */
@Aspect
@Component
@Slf4j
public class HttpAspect {

	@Pointcut(value = "execution(public * com.imooc.sell.controller.*.*(..))")
	private void log() {
	}

	@Before(value = "log()")
	public void doBefore(JoinPoint joinpoint) {
		log.info("【拦截http请求】doBefore:拦截http请求日志");
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		// url：请求URL
		log.info("【拦截http请求】url={}", request.getRequestURL());
		// method：请求方式
		log.info("【拦截http请求】method={}", request.getMethod());
		// ip：请求IP地址
		log.info("【拦截http请求】代理ip={}", WwwUtil.getIpAddr1(request));
		// 客户端真实ip
		log.info("【拦截http请求】真实ip={}", WwwUtil.getIpAddr4(request));
		// class method：请求类的方法
		log.info("【拦截http请求】class method name={}", joinpoint.getSignature().getDeclaringTypeName() + "." + joinpoint.getSignature().getName() + "()");
		// args：请求参数
		for (int i = 0; i < joinpoint.getArgs().length; i++) {
			log.info("【拦截http请求】 args[" + i + "]={}", joinpoint.getArgs()[i]);
		}
	}

	@After(value = "log()")
	public void doAfter() {
		log.info("【拦截http请求】doAfter: 拦截http请求日志");
	}

	@AfterReturning(returning = "object", pointcut = "log()")
	public void doAfterReturning(Object object) {
		log.info("【拦截http请求】doAfterReturning: 拦截http请求返回response={}", object);
	}
}

package com.imooc.sell.constant;

/**
 * redis常量
 */
public interface RedisConstant {

    Integer EXPIRE = 7200;//2小时

    String TOKEN_PREFIX = "TOKEN_%s";//token前缀

    String CART_COOKIE_PREFIX = "CART_COOKIE%s";//cart前缀
}

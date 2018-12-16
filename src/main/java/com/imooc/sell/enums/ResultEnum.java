
/**
 * Project Name:sell.<br/> 
 * File Name:ResultEnum.java.<br/> 
 * Package Name:com.imooc.sell.enums.<br/> 
 * Date:2017年12月11日上午10:31:55.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */

package com.imooc.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * ClassName: ResultEnum. <br/>
 * Function: ADD FUNCTION.<br/>
 * Reason: 返回给前端的消息.<br/>
 * Date: 2017年12月11日 上午10:31:55. <br/>
 * Desc: ADD DESC(可选). <br/>
 * 
 * @author 王艳军
 * @version
 * @since JDK 1.6
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ResultEnum implements CodeEnum<Object>{

	SUCCESS(0, "成功"),

	PARAM_ERROR(1, "参数不正确"),

	PRODUCT_NOT_EXIST(10, "商品不存在"),

	PRODUCT_STOCK_ERROR(11, "商品库存不正确"),

	ORDER_NOT_EXIST(12, "订单不存在"),

	ORDERDETAIL_NOT_EXIST(13, "订单详情不存在"),

	ORDER_STATUS_ERROR(14, "订单状态不正确"),

	ORDER_UPDATE_FAIL(15, "订单更新失败"),

	ORDER_DETAIL_EMPTY(16, "订单详情为空"),

	ORDER_PAY_STATUS_ERROR(17, "订单支付状态不正确"),

	CART_EMPTY(18, "购物车为空"),

	ORDER_OWNER_ERROR(19, "该订单不属于当前用户"),

	WECHAT_MP_ERROR(20, "微信公众账号方面错误"),

	WXPAY_NOTIFY_MONEY_VERIFY_ERROR(21, "微信支付异步通知金额校验不通过"),

	ORDER_CANCEL_SUCCESS(22, "订单取消成功"),

	ORDER_FINISH_SUCCESS(23, "订单完结成功"),

	PRODUCT_STATUS_ERROR(24, "商品状态不正确"),

	LOGIN_FAIL(25, "登录失败, 登录信息不正确"),

	LOGOUT_SUCCESS(26, "登出成功"),

	OTHER_ERROR(27, "其他系统错误"),

	PRODUCT_ON_SALE_SUCCESS(28, "商品上架成功"),

	PRODUCT_ON_SALE_FAIL(29, "商品上架失败"),

	PRODUCT_OFF_SALE_SUCCESS(30, "商品下架成功"),

	PRODUCT_OFF_SALE_FAIL(31, "商品下架失败"),

	PRODUCT_SAVE_SUCCESS(32, "新增商品成功"),
	
	PRODUCT_SAVE_FAIL(33, "新增商品失败"),

	PRODUCT_UPDATE_SUCCESS(34, "修改商品成功"),
	
	PRODUCT_UPDATE_FAIL(35, "修改商品失败"),
	
	PRODUCT_SAVE_OR_UPDATE_SUCCESS(36, "新增或者修改商品成功"),
	
	PRODUCT_SAVE_OR_UPDATE_FAIL(37, "新增或者修改商品失败"),

	CATEGORY_SAVE_OR_UPDATE_SUCCESS(38, "新增或者修改商品类目成功"),
	
	CATEGORY_SAVE_OR_UPDATE_FAIL(39, "新增或者修改商品类目失败"),

	CATEGORY_SAVE_SUCCESS(40, "新增商品类目成功"),

	CATEGORY_UPDATE_SUCCESS(41, "修改商品类目成功"),

	CATEGORY_NOT_EXIST(42, "商品类目不存在"),

	CATEGORY_NOT_DELETED(43, "此商品类目下存在一个或者多个商品信息，不允许删除此类目"),

	CATEGORY_WHETHER_OR_NOT_TO_DELETED_STATUS_ERROR(44, "此商品类目是否标记已删除状态已经是已标记为是删除状态"),
	
	ENCODING_UNSUPPORT_ERROR(45, "编码格式不支持错误"),
	
	MP_OTHER_ERROR(46, "微信公众平台其他方面错误"),
	
	;

	private Integer code;

	private String message;

}

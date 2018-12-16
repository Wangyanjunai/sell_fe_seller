
/**
 * Project Name:sell.<br/> 
 * File Name:ResultVO.java.<br/> 
 * Package Name:com.imooc.sell.VO.<br/> 
 * Date:2017年12月11日上午11:25:48.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */ 
    
package com.imooc.sell.VO;

//import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

import java.io.Serializable;

/**
 * <pre>
 * @ClassName:ResultVO
 * @Function:ADD FUNCTION
 * @Reason:Http请求返回的最外层对象
 * @Date:2017年12月11日 上午11:25:48
 * @Desc:ADD DESC(可选)
 * @Author 王艳军
 * @Version
 * @Since JDK 1.6
 * <pre>
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> implements Serializable{

  private static final long serialVersionUID = 8848731630266574133L;

  /**
   * code：返回的错误码
   */
  private Integer code;
  
  /**
   * msg：返回的提示信息
   */
  private String msg;
  
  /**
   * data：返回的具体内容
   */
  private T data;
}

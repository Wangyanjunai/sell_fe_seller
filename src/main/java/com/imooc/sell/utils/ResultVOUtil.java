
/**
 * Project Name:sell.<br/> 
 * File Name:ResultVOUtil.java.<br/> 
 * Package Name:com.imooc.sell.utils.<br/> 
 * Date:2017年12月11日下午2:25:03.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */ 
    
package com.imooc.sell.utils;

import com.imooc.sell.VO.ResultVO;

/** 
* ClassName: ResultVOUtil.		  <br/> 
* Function:   ADD FUNCTION.<br/> 
* Reason:  ADD REASON(可选).<br/> 
* Date: 2017年12月11日 下午2:25:03.		 <br/> 
* Desc:  ADD DESC(可选).	 <br/> 
* @author 王艳军 
* @version  
* @since JDK 1.6 
*/

public class ResultVOUtil{

  public static ResultVO success(Object object){
    ResultVO resultVO = new ResultVO();
    resultVO.setCode(0);
    resultVO.setMsg("成功");
    resultVO.setData(object);
    return resultVO;
  }
  
  public static ResultVO success(){
    return success(null);
  }
  
  public static ResultVO error(Integer code, String msg){
    ResultVO resultVO = new ResultVO();
    resultVO.setCode(code);
    resultVO.setMsg(msg);
    return resultVO;
  }

}

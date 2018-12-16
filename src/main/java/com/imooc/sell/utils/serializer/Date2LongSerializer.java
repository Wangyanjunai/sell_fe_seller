
/**
 * Project Name:sell.<br/> 
 * File Name:Date2LongSerializer.java.<br/> 
 * Package Name:com.imooc.sell.utils.serializer.<br/> 
 * Date:2017年12月12日下午9:31:30.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */ 
    
package com.imooc.sell.utils.serializer;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/** 
* ClassName: Date2LongSerializer.		  <br/> 
* Function:   ADD FUNCTION.<br/> 
* Reason:  ADD REASON(可选).<br/> 
* Date: 2017年12月12日 下午9:31:30.		 <br/> 
* Desc:  ADD DESC(可选).	 <br/> 
* @author 王艳军 
* @version  
* @since JDK 1.6 
*/

public class Date2LongSerializer extends JsonSerializer<Date>{

  /* (non-Javadoc)
   * @see com.fasterxml.jackson.databind.JsonSerializer#serialize(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider)
   */
  @Override
  public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializers)
      throws IOException, JsonProcessingException {
    jsonGenerator.writeNumber(date.getTime() / 1000);
  }

}

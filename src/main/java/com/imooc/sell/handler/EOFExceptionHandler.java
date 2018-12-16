package com.imooc.sell.handler;

import com.imooc.sell.exception.ResponseBankException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import java.util.Map;

/**
 * Describe:
 *
 * @Author 王艳军
 * @Date 2017/12/22 15:08:42
 */
@ControllerAdvice
public class EOFExceptionHandler {

    @ExceptionHandler(value = ResponseBankException.class)
    public ModelAndView handlerEOFExceptionHandler() {
        Map<String ,Object> map = new HashMap<String ,Object>();
        map.put("msg", "页面加载出现错误");
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/error:", map);
    }
}

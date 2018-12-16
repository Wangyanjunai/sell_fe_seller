package com.imooc.sell.utils;

import com.imooc.sell.VO.BookStoreResultVO;
import com.imooc.sell.VO.ResultVO;

/**
 * <pre>
 * @PackageName com.imooc.sell.utils
 * @ClassName BookStoreResultVOUtils
 * @Desc 返回结果数据工具类
 * @WebSite https://www.potato369.com
 * @Author 王艳军
 * @Date 2018/11/14 17:08
 * @CreateBy IntellJ IDEA 2018.2.4
 * @Copyright Copyright (c) 2016 ~ 2020 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
public class BookStoreResultVOUtils {
    public static BookStoreResultVO success(Object object){
        BookStoreResultVO resultVO = new BookStoreResultVO();
        resultVO.setErrorCode(0);
        resultVO.setMessage("获取成功");
        resultVO.setData(object);
        return resultVO;
    }

    public static BookStoreResultVO success(){
        return success(null);
    }

    public static BookStoreResultVO error(Integer code, String msg){
        BookStoreResultVO resultVO = new BookStoreResultVO();
        resultVO.setErrorCode(code);
        resultVO.setMessage(msg);
        return resultVO;
    }
}

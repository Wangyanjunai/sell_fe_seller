package com.imooc.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <pre>
 * @PackageName com.imooc.sell.VO
 * @ClassName BookStoreResultVO
 * @Desc 返回书城书籍数据结果
 * @WebSite https://www.potato369.com
 * @Author 王艳军
 * @Date 2018/11/14 17:00
 * @CreateBy IntellJ IDEA 2018.2.4
 * @Copyright Copyright (c) 2016 ~ 2020 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Data
public class BookStoreResultVO<T> implements Serializable {

    /**
     * errorCode：返回的错误码信息
     */
    @JsonProperty(value = "error_code")
    private Integer errorCode;

    /**
     * message：返回的提示信息
     */
    @JsonProperty(value = "msg")
    private String message;

    /**
     * data：返回的具体数据信息
     */
    @JsonProperty(value = "data")
    private T data;

    /**
     * total：返回的数据总条数
     */
    @JsonProperty(value = "total")
    private Long total;
}

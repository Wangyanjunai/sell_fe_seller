package com.imooc.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * <pre>
 * @PackageName com.imooc.sell.VO
 * @ClassName BookVO
 * @Desc 书籍信息列表
 * @WebSite https://www.potato369.com
 * @Author 王艳军
 * @Date 2018/11/14 17:33
 * @CreateBy IntellJ IDEA 2018.2.4
 * @Copyright Copyright (c) 2016 ~ 2020 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Data
public class BookVO {
    @JsonProperty(value = "name")
    private String categoryName;

    @JsonProperty(value = "type")
    private Integer categoryType;

    @JsonProperty(value = "foods")
    private List<BookInfoVO> bookInfoVOInfoVOs;
}

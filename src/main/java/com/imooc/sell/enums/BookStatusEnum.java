package com.imooc.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * @PackageName com.imooc.sell.enums
 * @EnumName BookStatusEnum
 * @Desc 书籍状态枚举
 * @WebSite https://www.potato369.com
 * @Author 王艳军
 * @Date 2018/11/14 18:29
 * @CreateBy IntellJ IDEA 2018.2.4
 * @Copyright Copyright (c) 2016 ~ 2020 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum BookStatusEnum implements CodeEnum<Object> {



    NOT_SELECTED(0, false, "否"),

    SELECTED(1, true, "是"),

    NOT_PRIVATED(0, false, "否"),

    PRIVATED(1, true, "是"),

    NOT_CACHE(0, false, "否"),

    CACHE(1, true, "是"),

    NOT_DELETE(0, false, "否"),

    DELETE(1, true, "是"),

    LANG_CN(86, true, "cn"),

    LANG_EN(44, true, "en"),

    NO_READ(0, false, "否"),

    HAVE_READ(1, true, "是"),

    ;

    private Integer code;

    private Boolean status;

    private String message;
}

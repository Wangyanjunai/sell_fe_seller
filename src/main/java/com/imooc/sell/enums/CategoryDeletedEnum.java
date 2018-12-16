package com.imooc.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum CategoryDeletedEnum implements CodeEnum<Object>{

    NOT_DELETE(0, "否"),

    DELETE(1, "是"),

    ;

    private Integer code;

    private String message;
}

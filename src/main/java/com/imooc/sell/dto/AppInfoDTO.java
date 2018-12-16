package com.imooc.sell.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * @description：app信息
 * @author 王艳军
 * @version  1.0
 * @since JDK 1.6
 * </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppInfoDTO {

    @JsonProperty(value = "type")
    private String type;

    @JsonProperty(value = "wap_url")
    private String wapUrl;

    @JsonProperty(value = "wap_name")
    private String wapName;
}

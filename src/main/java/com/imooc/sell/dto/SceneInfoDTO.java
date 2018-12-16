package com.imooc.sell.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * @description：场景信息DTO
 * @author 王艳军
 * @version 1.0
 * @since JDK 1.6
 * </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SceneInfoDTO {

    private AppInfoDTO appInfoDTO;
}

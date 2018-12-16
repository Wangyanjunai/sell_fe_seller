package com.imooc.sell.dto;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import com.lly835.bestpay.utils.JsonUtil;

@Slf4j
public class SceneInfoDTOTest {

    @Test
    public void testJson() {
        SceneInfoDTO sceneInfoDTO = SceneInfoDTO.builder().build();
        AppInfoDTO appInfoDTO = AppInfoDTO.builder().build();
        appInfoDTO.setType("wap");
        appInfoDTO.setWapName("腾讯充值");
        appInfoDTO.setWapUrl("https://pay.qq.com");
        sceneInfoDTO.setAppInfoDTO(appInfoDTO);
        String jsonSceneInfo = JsonUtil.toJson(sceneInfoDTO);
        log.info("jsonSceneInfo={}", jsonSceneInfo);
    }

    @Test
    public void test() {

    }
}
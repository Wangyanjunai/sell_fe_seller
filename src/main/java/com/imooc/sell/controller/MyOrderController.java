package com.imooc.sell.controller;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * <pre>
 * @PackageName com.imooc.sell.controller
 * @ClassName MyOrderController
 * @Desc 我的订单信息列表
 * @WebSite https://www.potato369.com
 * @Author 王艳军
 * @Date 2018/10/11 11:54
 * @CreateBy IntellJ IDEA 2018.2.4
 * @Copyright Copyright (c) 2016 ~ 2020 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Slf4j
@Controller
@RequestMapping(value = "/my/order")
public class MyOrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/maList")
    public ModelAndView findMaList(@RequestParam(name = "openid", required = true) String openid,
                                   @RequestParam(name = "page", required = true, defaultValue = "0") Integer page,
                                   @RequestParam(name = "size", required = true, defaultValue = "10") Integer size,
                                   Map<String, Object> map) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest pageRequest = new PageRequest(page, size, sort);
        Page<OrderDTO> orderDTOPage = null;
        try {
            orderDTOPage = orderService.findList(openid, pageRequest);
        } catch (Exception e) {
            log.error("【查询订单列表】 失败，出现错误，或者是数据库链接错误");
            throw new SellException(ResultEnum.OTHER_ERROR);
        }
        map.put("orderDTOPage", orderDTOPage);
        return new ModelAndView("order/malist", map);
    }
}

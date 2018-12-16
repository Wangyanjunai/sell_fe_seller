package com.imooc.sell.converter;

import com.imooc.sell.dataobject.OrderMaster;
import com.imooc.sell.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * describe:
 *
 * @author 王艳军
 * @date 2017/12/12 14:36:55
 */
public class OrderMaster2OrderDTOConverter {
    /**
     * 将oderMaster对象转换为OrderDTO对象
     * @param orderMaster
     * @return OrderDTO对象
     */
    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    /**
     * 将List<OrderMaster>对象转换为List<OrderDTO>对象
     * @param orderMasterList
     * @return List<OrderDTO>
     */
    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map(master -> convert(master)).collect(Collectors.toList());
    }
}

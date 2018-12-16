package com.imooc.sell.service;

import com.imooc.sell.dataobject.SellerInfo;

/**
 * 卖家端Service
 * Create By 王艳军
 */
public interface SellerService {

    /**
     * 根据卖家微信openid查找卖家信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}

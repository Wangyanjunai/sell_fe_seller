package com.imooc.sell.service;

import com.imooc.sell.dataobject.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * <pre>
 * @PackageName com.imooc.sell.service
 * @InterfaceName UserInfoService
 * @Desc 精品小说用户信息Service
 * @WebSite https://www.potato369.com
 * @Author 王艳军
 * @Date 2018/11/27 16:30
 * @CreateBy IntellJ IDEA 2018.2.6
 * @Copyright Copyright (c) 2016 ~ 2020 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
public interface UserInfoService {

    /**
     * 添加用户信息
     *
     * @param userInfo
     * @return
     */
    UserInfo add(UserInfo userInfo);

    /**
     * 删除用户信息
     *
     * @param uid
     */
    void delete(Integer uid);

    /**
     * 修改用户信息
     *
     * @param userInfo
     * @return
     */
    UserInfo update(UserInfo userInfo);

    /**
     * 根据用户uid查找用户信息
     *
     * @param uid
     * @return
     */
    UserInfo findUserInfoByUid(Integer uid);

    /**
     * 根据用户id查找用户信息
     * @param id
     * @return
     */
    UserInfo findUserInfoById(String id);

    /**
     * 根据用户微信openid查找用户信息
     *
     * @param openid
     * @return
     */
    UserInfo findUserInfoByOpenId(String openid);

    /**
     * 查询所有用户信息
     *
     * @return
     */
    List<UserInfo> findAll();

    /**
     * 分页查询所有的用户信息
     *
     * @return
     */
    Page<UserInfo> findAll(Pageable pageable);
}

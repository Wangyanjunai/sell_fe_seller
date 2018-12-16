package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.UserInfo;
import com.imooc.sell.repository.UserInfoRepository;
import com.imooc.sell.service.UserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <pre>
 * @PackageName com.imooc.sell.service.impl
 * @ClassName UserInfoServiceImpl
 * @Desc 精品小说用户信息Service实现
 * @WebSite https://www.potato369.com
 * @Author 王艳军
 * @Date 2018/11/27 16:32
 * @CreateBy IntellJ IDEA 2018.2.6
 * @Copyright Copyright (c) 2016 ~ 2020 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    /**
     * 添加用户信息
     *
     * @param userInfo
     * @return
     */
    @Override
    public UserInfo add(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }

    /**
     * 删除用户信息
     *
     * @param uid
     */
    @Override
    public void delete(Integer uid) {
        userInfoRepository.delete(uid);
    }

    /**
     * 修改用户信息
     *
     * @param userInfo
     * @return
     */
    @Override
    public UserInfo update(UserInfo userInfo) {
        if (userInfo != null) {
            Integer uid = userInfo.getUid();
            UserInfo userInfoTmp = userInfoRepository.findOne(uid);
            BeanUtils.copyProperties(userInfo, userInfoTmp);
            return userInfoRepository.saveAndFlush(userInfoTmp);
        } else {
            return null;
        }
    }

    /**
     * 根据用户uid查找用户信息
     *
     * @param uid
     * @return
     */
    @Override
    public UserInfo findUserInfoByUid(Integer uid) {
        return userInfoRepository.findOne(uid);
    }

    /**
     * 根据用户id查找用户信息
     *
     * @param id
     * @return
     */
    @Override
    public UserInfo findUserInfoById(String id) {
        return userInfoRepository.findUserInfoById(id);
    }

    /**
     * 根据用户微信openid查找用户信息
     *
     * @param openid
     * @return
     */
    @Override
    public UserInfo findUserInfoByOpenId(String openid) {
        return userInfoRepository.findUserInfoByOpenId(openid);
    }

    /**
     * 查询所有用户信息
     *
     * @return
     */
    @Override
    public List<UserInfo> findAll() {
        return userInfoRepository.findAll();
    }

    /**
     * 分页查询所有的用户信息
     *
     * @param pageable
     * @return
     */
    @Override
    public Page<UserInfo> findAll(Pageable pageable) {
        return userInfoRepository.findAll(pageable);
    }
}

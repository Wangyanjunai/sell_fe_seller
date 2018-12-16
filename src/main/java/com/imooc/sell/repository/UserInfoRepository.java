package com.imooc.sell.repository;

import com.imooc.sell.dataobject.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * <pre>
 * @PackageName com.imooc.sell.repository
 * @ClassName UserInfoRepository
 * @Desc 精品小说用户信息Dao
 * @WebSite https://www.potato369.com
 * @Author 王艳军
 * @Date 2018/11/27 16:05
 * @CreateBy IntellJ IDEA 2018.2.6
 * @Copyright Copyright (c) 2016 ~ 2020 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    /**
     * 根据用户id查找用户信息
     *
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
}

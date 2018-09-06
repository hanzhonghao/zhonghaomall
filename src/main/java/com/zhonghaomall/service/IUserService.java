package com.zhonghaomall.service;

import com.zhonghaomall.common.ServerResponse;
import com.zhonghaomall.pojo.User;

/**
 * Created by 小豪 on 2018/9/7.
 */
public interface IUserService {
    ServerResponse<User> login(String username, String password);
}

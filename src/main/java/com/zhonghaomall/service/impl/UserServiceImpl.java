package com.zhonghaomall.service.impl;

import com.zhonghaomall.common.ServerResponse;
import com.zhonghaomall.dao.UserMapper;
import com.zhonghaomall.pojo.User;
import com.zhonghaomall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 小豪 on 2018/9/7.
 */
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse<User> login(String username, String password) {
        int resultCount = userMapper.checkUsername(username);
        if(resultCount==0){
            return ServerResponse.createByErrorMessage("用户名不存在");
        }

        //todo 密码登录MD5
        return null;
    }
}

package com.yx.service;

import com.yx.entities.User;

public interface UserService {

    //根据用户名和密码查询用户信息
    public User selectUserByUserNameAndPassword(String userName, String password);

}

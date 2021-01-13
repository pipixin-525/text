package com.yx.service.impl;

import com.yx.dao.UserDao;
import com.yx.entities.User;
import com.yx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User selectUserByUserNameAndPassword(String userName, String password) {
        int count = this.userDao.selectUserCountByUserNameAndPassword(userName, password);
        if (count == 1) {
            return this.userDao.selectUserByUserNameAndPassword(userName, password);
        }
        return null;
    }
}

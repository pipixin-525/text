package com.yx.dao;

import com.yx.entities.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    //根据用户名和密码查询用户信息
    public User selectUserByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);

    //查询账户余额
    public Double selectUserBalanceByUserId(@Param("userId") Integer userId);

    //根据用户名和密码查询用户数据笔数
    public Integer selectUserCountByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);

    //更改账户余额
    public int updateUserBalance(@Param("userId") Integer userId, @Param("addMoney") Double addMoney);

}

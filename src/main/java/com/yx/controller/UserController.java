package com.yx.controller;

import com.yx.entities.User;
import com.yx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //登录
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public boolean userLogin(String userName, String password, HttpSession session) {
        User user = this.userService.selectUserByUserNameAndPassword(userName, password);
        if (null != user) {
            //将用户信息加入到session域中
            session.setAttribute("user", user);
            return true;
        }
        return false;
    }
}

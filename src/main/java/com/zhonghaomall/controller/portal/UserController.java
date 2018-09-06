package com.zhonghaomall.controller.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by 小豪 on 2018/8/25.
 */
@Controller
@RequestMapping("/user/")
public class UserController {

    /**
     * user login
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    @ResponseBody
    public Object login(String username, String password, HttpSession session){
        //service-mybatis-dao
        return null;
    }
}

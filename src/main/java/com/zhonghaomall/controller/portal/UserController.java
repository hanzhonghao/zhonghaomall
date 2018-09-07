package com.zhonghaomall.controller.portal;

import com.zhonghaomall.common.Const;
import com.zhonghaomall.common.ResponseCode;
import com.zhonghaomall.common.ServerResponse;
import com.zhonghaomall.pojo.User;
import com.zhonghaomall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

import static com.zhonghaomall.common.Const.CURRENT_USER;

/**
 * Created by 小豪 on 2018/8/25.
 */
@Controller @RequestMapping("/user/") public class UserController {
  @Autowired private IUserService iUserService;

  /**
   * user login
   *
   * @param username
   * @param password
   * @param session
   * @return
   */
  @RequestMapping(value = "login.do", method = RequestMethod.POST) @ResponseBody public ServerResponse<User> login(String username,
      String password, HttpSession session) {
    //service-mybatis-dao
    ServerResponse<User> response = iUserService.login(username, password);
    if (response.isSuccess()) {
      session.setAttribute(CURRENT_USER, response.getData());
    }
    return response;
  }

  @RequestMapping(value = "logout.do", method = RequestMethod.GET) @ResponseBody public ServerResponse<User> logout(
      HttpSession session) {
    //service-mybatis-dao
    session.removeAttribute(CURRENT_USER);
    return ServerResponse.createBySuccess();
  }

  @RequestMapping(value = "register.do", method = RequestMethod.POST) @ResponseBody public ServerResponse<String> register(User user){
    //service-mybatis-dao

    return iUserService.register(user);
  }

  @RequestMapping(value = "check_valid.do",method = RequestMethod.POST)
  @ResponseBody
  public ServerResponse<String> checkValid(String str,String type){
    return iUserService.checkValid(str,type);
  }

  @RequestMapping(value = "get_user_info.do",method = RequestMethod.POST)
  @ResponseBody
  public ServerResponse<User> getUserInfo(HttpSession session){
    User user = (User) session.getAttribute(Const.CURRENT_USER);
    if(user != null){
      return ServerResponse.createBySuccess(user);
    }
    return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
  }

  @RequestMapping(value = "forget_get_question.do",method = RequestMethod.POST)
  @ResponseBody
  public ServerResponse<String> forgetGetQuestion(String username){
    return iUserService.selectQuestion(username);
  }

  @RequestMapping(value = "forget_check_answer.do",method = RequestMethod.POST)
  @ResponseBody
  public ServerResponse<String> forgetCheckAnswer(String username,String question,String answer){
    return iUserService.checkAnswer(username,question,answer);
  }


  @RequestMapping(value = "forget_reset_password.do",method = RequestMethod.POST)
  @ResponseBody
  public ServerResponse<String> forgetRestPassword(String username,String passwordNew,String forgetToken){
    return iUserService.forgetResetPassword(username,passwordNew,forgetToken);
  }



  @RequestMapping(value = "reset_password.do",method = RequestMethod.POST)
  @ResponseBody
  public ServerResponse<String> resetPassword(HttpSession session,String passwordOld,String passwordNew){
    User user = (User)session.getAttribute(Const.CURRENT_USER);
    if(user == null){
      return ServerResponse.createByErrorMessage("用户未登录");
    }
    return iUserService.resetPassword(passwordOld,passwordNew,user);
  }


  @RequestMapping(value = "update_information.do",method = RequestMethod.POST)
  @ResponseBody
  public ServerResponse<User> update_information(HttpSession session,User user){
    User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
    if(currentUser == null){
      return ServerResponse.createByErrorMessage("用户未登录");
    }
    user.setId(currentUser.getId());
    user.setUsername(currentUser.getUsername());
    ServerResponse<User> response = iUserService.updateInformation(user);
    if(response.isSuccess()){
      response.getData().setUsername(currentUser.getUsername());
      session.setAttribute(Const.CURRENT_USER,response.getData());
    }
    return response;
  }

  @RequestMapping(value = "get_information.do",method = RequestMethod.POST)
  @ResponseBody
  public ServerResponse<User> get_information(HttpSession session){
    User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
    if(currentUser == null){
      return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录,需要强制登录status=10");
    }
    return iUserService.getInformation(currentUser.getId());
  }

}

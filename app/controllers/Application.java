package controllers;

import play.*;
import play.libs.Codec;
import play.mvc.*;
import utils.SessionManager;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
      SessionManager.setFooter(session);
      User user = SessionManager.getLoginedUser(session);

        render(user);
    }
    
    
    public static void manager() {
        SessionManager.setFooter(session);

        Users.index();
    }
    
    

    /*
     * 跳转到登录页面
     */
    public static void login() {
        String randomId = Codec.UUID();// 登录时候附带的验证信息
        render(randomId);
    }
    
    /*
     * 注销
     */
    public static void logout() {
        session.clear();
        session.put("logined", false);
        Application.index();
    }
    
    /*
     * 跳转到注册页面
     */
    public static void register() {
        render();
    }
   


}
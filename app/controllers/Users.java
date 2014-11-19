package controllers;

import play.*;

import play.mvc.*;
import utils.FileUtils;
import utils.SessionManager;
import utils.StringUtils;
import utils.Uploader;
import utils.enumvalue.Config;
import utils.enumvalue.FileUploadState;

import java.io.File;
import java.util.*;

import models.*;
/*
 * 用户处理
 * @author zhuangXiangPeng
 */
public class Users extends Controller {
  
  /**
   * 跳转基本页面，用于测试样例
   */
    public static void index(){
      render();
    }

  
	/*
	 * 跳转到登录页面
	 */
	public static void login() {
		render();
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
	 * 验证登录信息
	 * 
	 * @param account
	 * @param password
	 * @param type
	 */
	public static void verificate(String account, String password) {
		if (User.isExist(account, password)) {
			// 用户
			User user = User.find("account=? AND password=? ",
					account, password).first();
			session.put("userId", user.id);
			Application.index();
		}
	}
	
	/*
	 * 跳转到注册页面
	 */
	public static void register() {
		render();
	}

	
	/*
     * 保存注册的用户
     * 
     * @param user
     */
    public static void saveUser(User user,File photo) {
      
      System.out.println(photo.getAbsolutePath());
      if (photo != null) {
        
        if (!StringUtils.isEmpty(user.photo)) {
            FileUtils.deleteFile(user.getPhoto());
        }
        
        String baseUrl = Config.DEFAULT_BASE_URL;
        String savePath = Config.USER_PHOTO_PATH;
        Uploader uploader = new Uploader(baseUrl, savePath);
        //System.out.println("savePath:"+savePath);
        uploader.upload(photo);

        if (uploader.getState() == FileUploadState.SUCCESS) {
            // 文件上传成功
          user.photo = uploader.getUrl();

        } else {
            flash.error("上传失败");
            index();
        }
      } else{
        if(user.photo==null||"".equals(user.photo))
          user.photo = "/public/images/no_pic.jpg";
        
      } 
      
      
        user.save();
        session.put("userId", user.id);
        register();
    }
	
	/*
	 * 修改用户信息
	 * 
	 * @param user
	 * @param userInfo
	 */
	public static void editUserInfo(User user) {
		user.save();
		showUsers();
	}
	
	/*
	 * 显示所有用户（管理员用）
	 */
	public static void showUsers() {
		if(SessionManager.getLoginedUser(session) != null) {
			User user = SessionManager.getLoginedUser(session);
			if(user != null && user.isAdmin()) {
				// 是管理员
				List<User> userList = User.findAll();
				render(userList,user);
			}
		}
	}
	
	/*
	 * 删除用户 
	 * @param userId
	 */
	public static void deleteUser(String userId) {
		User.delete("id", userId);
		showUsers();
	}
	
	/*
	 * 显示用户信息
	 * @param userId
	 */
	public static void userInfo(String userId) {
		User user = User.findById(userId);
		render(user);
	}


}

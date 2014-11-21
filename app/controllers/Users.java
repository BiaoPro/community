package controllers;

import play.*;

import play.libs.Codec;
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
@With(UserSecures.class)
public class Users extends Controller {
  
  /**
   * 跳转基本页面，用于测试样例
   */
    public static void index(){
      showUserInfo();
    }

	
	/*
	 * 修改用户信息
	 * 
	 * @param user
	 * @param userInfo
	 */
	public static void editUser(User user,File photo) {
	  
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
        Users.showUserInfo();
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
	
	
	public static void showUserInfo() {
      User user = SessionManager.getLoginedUser(session);
      render(user);
  }


}

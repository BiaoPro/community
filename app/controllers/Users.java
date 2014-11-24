package controllers;

import play.*;

import play.libs.Codec;
import play.mvc.*;
import utils.FileUtils;
import utils.PageBean;
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
      showUserInfo("");
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
        Users.showUserInfo("");
	}
	
	/**
     * 根据关键字和页数显示用户列表 关键字或搜索范围为null时显示所有用户 默认每页显示5个用户
     * 
     * @param searchScope
     * @param searchKey
     * @param curPage
     */
    public static void showUsers(String searchKey,Integer curPage, Integer perPage) {
      
      PageBean pageBean = null;
      List<User> list = null;   
      long total = 0;
      if(curPage == null ) curPage = 1;
      if(perPage == null)  perPage = 5;

      if (StringUtils.isEmpty(searchKey)) {
          // 非搜索模式
          total = User.count();
          list = User.all()
                     .fetch(curPage, perPage);
          
      } else {
          // 搜索模式
              total = User
                      .count("select count(*) from User where account like ? or rname like ?","%" + searchKey + "%","%" + searchKey + "%");
              list = User
                      .find("from User where account like ? or rname like ?","%" + searchKey + "%","%" + searchKey + "%")
                      .fetch(curPage,perPage);
      }
      
      pageBean = PageBean.getInstance(curPage, total, perPage);
      
      render(list, curPage, perPage, pageBean);


    }
	
    
    /**
     * 重置用户密码为1234 
     * @param userId
     */
    public static void resetPassword(String userId) {
       User vo = User.findById(userId);
       vo.password = "1234";
       vo.save();
       showUsers("",1,5);
    }
    
    /**
     * 修改用户类型 
     * @param userId
     */
    public static void changeUserType(String id, Integer type) {
      if(User.count("select count(*) from User where type=3")>1){
        User vo = User.findById(id);
        vo.type = type;
        vo.save();
      }
      else if(type!=3){
        flash.error("请保留至少一个管理员");
      }else{
        User vo = User.findById(id);
        vo.type = type;
        vo.save();
      }
      showUsers("",1,5);
    }
    
    
    
	/*
	 * 删除用户 
	 * @param userId
	 */
	public static void deleteUser(String userId) {
	    if(SessionManager.getLoginedId(session).equals(userId)){
	      flash.error("不能把自己删除了哦~");
	    }else
		User.delete("id", userId);
		 showUsers("",1,5);
	}
	
	/*
	 * 显示用户信息
	 * @param userId
	 */
	public static void userInfo(String userId) {
		User user = User.findById(userId);
		render(user);
	}
	
	
	public static void showUserInfo(String id) {
	  User user;
	  if(id==null||id.equals(""))
        user = SessionManager.getLoginedUser(session);
      else  user = User.findById(id);
      render(user);
  }


}

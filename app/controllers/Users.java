package controllers;

import play.*;

import play.mvc.*;
import utils.SessionManager;

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
	public static void saveUser(User user) {
		user.save();
		session.put("userId", user.id);
		Application.index();
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

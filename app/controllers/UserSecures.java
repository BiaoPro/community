package controllers;

import models.User;
import models.UserInfo;
import play.cache.Cache;
import play.libs.Images;
import play.mvc.Before;
import play.mvc.Controller;
import utils.MD5Utils;
import utils.SessionManager;
import utils.StringUtils;

/**
 * 用户验证
 * 
 * @author leaf
 * 
 */
public class UserSecures extends Controller {

	/**
	 * 检测是否以登录，防止直接通过url在没有登录的情况下进入后台
	 */
	@Before(unless = { "login", "verify", "logout", "getCaptcha" })
	public static void checkAccess() {
		if (!SessionManager.isLogin(session)) {
//			// 在没有登录的情况下跳转到首页
//			redirect("/");
		   Application.login();
		}
	}

	/**
	 * 登录验证
	 * 
	 * @param username
	 * @param pass
	 * @param randomId
	 *            验证码id，防止多次提交成错误
	 */
	public static void verify(String username, String pass,
			String captchaCode, String randomId) {
		String code = String.valueOf(Cache.get(randomId)).toLowerCase();
		if (StringUtils.isEmpty(code) || StringUtils.isEmpty(captchaCode)
				|| !captchaCode.toLowerCase().equals(code)) {
			// 验证码信息错误
			flash.put("captchaCodeErrorMessage", "验证码不正确~");
			flash.put("captchaCodeError", true);
			flash.put("account", username);
			flash.put("password", pass);
			 Application.login();
			return;
		}

		User user = User.findByAccount(username);
		if (user != null) {
			//String checkPwd = MD5Utils.getMD5Str(user.password);
			if(user.password.equals(pass)) {
				// 密码正确
				UserInfo userInfo = UserInfo.getUserInfoByUserId(user.id);
				userInfo.updateUserInfo(UserInfo.LOGIN);

				session.put("userId", user.id);
				session.put("account", user.account);
				session.put("userInfoId", userInfo.id);
				session.put("userCategory", user.type);
				session.put("userName", userInfo.name);
				
				Application.manager();
			} else {
				// 密码错误
				session.clear();
				flash.put("passwordError", "密码不正确~");
				flash.put("account", username);
			}
			
		} else {
			// 帐号错误
			session.clear();
			flash.put("accountError", "账号不存在~");
			
		}
		 Application.login();
		

	}

	/**
	 * 注销
	 */
	public static void logout() {
		if (SessionManager.isLogin(session)) {
			User user = SessionManager.getLoginedUser(session);
			UserInfo userInfo = user.getUserInfo();
			userInfo.updateUserInfo(UserInfo.LOGOUT);
			session.clear();
		}
		Application.index();
	}

	/**
	 * 获取验证码
	 */
	public static void getCaptcha(String randomId) {
		Images.Captcha captcha = Images.captcha();
		String code = captcha.getText();
		Cache.set(randomId, code, "10mn");
		renderBinary(captcha);
	}
}

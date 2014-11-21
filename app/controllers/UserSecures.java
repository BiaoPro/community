package controllers;

import java.io.File;

import models.User;
import models.UserInfo;
import play.cache.Cache;
import play.libs.Images;
import play.mvc.Before;
import play.mvc.Controller;
import utils.FileUtils;
import utils.MD5Utils;
import utils.SessionManager;
import utils.StringUtils;
import utils.Uploader;
import utils.enumvalue.Config;
import utils.enumvalue.FileUploadState;

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
	@Before(unless = { "login", "saveUser", "verify", "logout", "getCaptcha" })
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
				session.clear();
				session.put("userId", user.id);
				session.put("account", user.account);
				session.put("userName", user.rname);
				session.put("userCategory", user.type);
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
    * 保存注册的用户
    * 
    * @param user
    */
   public static void saveUser(User user,File photo) {
     try{
       if(User.findByAccount(user.account)!=null){
         flash.put("backMessage", "账号:"+user.account+"已被注册~");
         flash.put("account", user.account);
         flash.put("rname", user.rname); 
         flash.put("prc", user.prc); 
         flash.put("email", user.email); 
         flash.put("birthday", user.birthday);
         if (!StringUtils.isEmpty(user.photo)) {
           FileUtils.deleteFile(user.getPhoto());
         }
       }else{
         

       if (photo != null) {
         
         String baseUrl = Config.DEFAULT_BASE_URL;
         String savePath = Config.USER_PHOTO_PATH;
         Uploader uploader = new Uploader(baseUrl, savePath);
         //System.out.println("savePath:"+savePath);
         uploader.upload(photo);
  
         if (uploader.getState() == FileUploadState.SUCCESS) {
             // 文件上传成功
           user.photo = uploader.getUrl();
         } else {
             flash.put("backMessage", "图片上传失败~");
         }
       } else{
         if("".equals(user.photo))
           user.photo = "/public/images/no_pic.jpg";
         
       } 
       
       
         user.save();
         new UserInfo(user.id,user.account).save();
         
         flash.put("backMessage", "注册成功~");
       }
         
         
     }catch(Exception e){
       flash.put("user", user);
       flash.put("backMessage", "注册失败~");
       if (!StringUtils.isEmpty(user.photo)) {
         FileUtils.deleteFile(user.getPhoto());
       }
     }
         
         Application.register();
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

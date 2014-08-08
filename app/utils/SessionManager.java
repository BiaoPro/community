package utils;

import models.SystemParameter;
import models.User;
import play.mvc.Scope.Session;

public class SessionManager {

	/**
	 * 获取当前的登录的用户
	 */
	public static User getLoginedUser(Session session) {
		if(!session.contains("userId")) {
			return null;
		}
		return User.find("id", session.get("userId")).first();
	}
	
	/**
	 * 获取登录后的用户id
	 * @return
	 */
	public static String getLoginedId(Session session) {
		return session.get("userId");
	
	}
	
	
	/**
	 * 是否已经登录
	 * @return
	 */
	public static boolean isLogin(Session session) {
		return session.contains("userId");
	}
	
	public static void setHeader(Session session) {
		SystemParameter sp=SystemParameter.getSystemParameter();
		session.put("indexName",sp.indexName);
	}
	
	/**
	 * 设置页面底部内容
	 * @return
	 */
	public static void setFooter(Session session) {
		SystemParameter sp=SystemParameter.getSystemParameter();
		session.put("indexName",sp.indexName);
		session.put("copyright",sp.copyright);
		session.put("address",sp.address);
		session.put("telephone",sp.telephone);
		session.put("mailbox",sp.mailbox);
	}
	
	
}

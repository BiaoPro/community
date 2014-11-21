package controllers;

import models.User;
import models.UserInfo;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import utils.enumvalue.UserRoleEnum;
import models.SystemSetting;

/**
 * 数据库初始化
 * 
 * @author biao
 * 
 */
@OnApplicationStart
public class Bootstrap extends Job {
	@Override
	public void doJob() throws Exception {
		
		User adminUser = User.findByAccount("admin");
		if (adminUser == null) {
			adminUser = new User("admin", "sys", 3);
			UserInfo adminUserInfo = new UserInfo(adminUser.id, "admin");
			// 设置admin帐号为管理员帐号
			adminUserInfo.userCategory = UserRoleEnum.ADMIN.getValue();
			adminUser.save();
			adminUserInfo.save();
			
		}
		
	        User backUser = User.findByAccount("back");
	        if (backUser == null) {
	            adminUser = new User("back", "123", 2);
	            UserInfo adminUserInfo = new UserInfo(adminUser.id, "back");
	            adminUserInfo.userCategory = UserRoleEnum.PUBLISHER.getValue();
	            adminUser.save();
	            adminUserInfo.save();
	            
	        }
	        
	           User nomalUser = User.findByAccount("test");
	            if (nomalUser == null) {
	                adminUser = new User("test", "123", 1);
	                UserInfo adminUserInfo = new UserInfo(adminUser.id, "back");
	                adminUserInfo.userCategory = UserRoleEnum.NOMALUSER.getValue();
	                adminUser.save();
	                adminUserInfo.save();
	                
	            }
		
		if(SystemSetting.count() == 0) {
			new SystemSetting().save();
		}
	}
}
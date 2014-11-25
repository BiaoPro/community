package controllers;

import models.CourseCategory;
import models.NewsClass;
import models.SystemSetting;
import models.User;
import models.UserInfo;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import utils.enumvalue.UserRoleEnum;

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
		
	  //用户初始化
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
	            
	    //版权信息初始化
		if(SystemSetting.count() == 0) {
			new SystemSetting().save();
		}
		
		//在线课程类目初始化
        //最新网络课程、同步课程、微课程、公开课
		if(CourseCategory.count("type=1") <= 1) {
          new CourseCategory("最新网络课程",1).save();
          new CourseCategory("同步课程",1).save();
          new CourseCategory("微课程",1).save();
          new CourseCategory("公开课",1).save();
      }
		
		
		//社区课程类目初始化
        //最新课程、实用技能、讲座类、活动类
		if(CourseCategory.count("type=2") <= 1) {
          new CourseCategory("最新课程",2).save();
          new CourseCategory("实用技能",2).save();
          new CourseCategory("讲座类",2).save();
          new CourseCategory("活动类",2).save();
      }
		
	      //社区新闻类目初始化
        //新广州人栏目、国家新政策、医疗、住房、就业、保险
		if(NewsClass.count() <= 1) {
          new NewsClass("新广州人栏目").save();
          new NewsClass("国家政策").save();
          new NewsClass("医疗相关").save();
          new NewsClass("就业相关").save();
          new NewsClass("住房相关").save();
      }
		
	}
}

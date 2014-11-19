package controllers;

import models.SystemSetting;
import play.mvc.Controller;
import play.mvc.With;
import utils.SessionManager;

@With(UserSecures.class)
public class SystemSettings extends  Controller{

	public static void index(){
	  showSystemSettings();
	}
	
	public static void showSystemSettings(){
        SystemSetting sp=SystemSetting.getSetting();
        render(sp);
	}
	

	/**
	 * 修改页面信息
	 */
	public static void changeIndex(String indexName,String copyright,
		                            String address, String telephone, String mailbox) {
		SystemSetting sp=SystemSetting.getSetting();
		sp.indexName=indexName;
		sp.copyright=copyright;
		sp.address=address;
		sp.telephone=telephone;
		sp.mailbox=mailbox;
		sp.save();
		SessionManager.setHeader(session);
		SessionManager.setFooter(session);
		index();
	}
}

package controllers;

import java.util.List;

import models.BackMessage;
import models.Course;
import models.LinkCategory;
import models.News;
import models.User;
import play.libs.Codec;
import play.mvc.Controller;
import utils.NewsBean;
import utils.PageBeanFactory;
import utils.SessionManager;

public class Application extends Controller {

    public static void index() {
      SessionManager.setFooter(session);
      SessionManager.setLinkCategory(session);
      User user = SessionManager.getLoginedUser(session);
      
      //获取课程信息
      Course courseGet;
      if(Course.count()>0)
       courseGet = (Course) Course.findAll().get(0);
      else courseGet=null;

      //新闻文章
      List newsList = News.getIndexNews(new PageBeanFactory(1,7),0);
      News impNews = null;
      try{
        NewsBean firNews = (NewsBean)newsList.get(0);
        String id = firNews.getNewsId();
        impNews = News.findById(id);
      }catch(Exception e){
        
      }
      
      //新闻文章
      List linkCategoryList = LinkCategory.find("status=1 ORDER BY sequence").fetch();
     
     
     

      
        render(user,newsList,linkCategoryList,impNews,courseGet);

    }
    
    
    public static void manager() {
        SessionManager.setFooter(session);

        Users.index();
    }
    
    

    /**
     * 跳转到登录页面
     */
    public static void login() {
        String randomId = Codec.UUID();// 登录时候附带的验证信息
        render(randomId);
    }
    
    /**
     * 注销
     */
    public static void logout() {
        session.clear();
        session.put("logined", false);
        Application.index();
    }
    
    /**
     * 跳转到注册页面
     */
    public static void register() {
        render();
    }
    
    /**
     * 跳转到信息反馈页面
     */
    public static void back() {
        render();
    }
    
    /**
     * 保存信息反馈内容
     */
    public static void saveBackMessage(String message) {
      BackMessage bm = new BackMessage();
      bm.message = message;
      bm.save();
      flash.put("backMessage","信息已收到，感谢您的建议~");
      flash.put("isAlert",true);
      back();
  }
   


}
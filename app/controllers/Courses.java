/**
 * 文件创建时间：2014年8月15日
 */
package controllers;

import java.util.List;

import models.BackMessage;
import models.Course;
import models.CourseCategory;
import models.CourseOnline;
import models.Link;
import models.LinkCategory;
import play.mvc.Controller;
import play.mvc.With;
import utils.PageBean;
import utils.StringUtils;
import utils.enumvalue.ConfigValue;

/**
 * @author biao
 *
 */
@With(UserSecures.class)
public class Courses extends Controller {

  /**
   * 显示所有课堂类别
   */
  public static void showCourseCategories(int type) {
       if(type!=1&&type!=2) type=2;
       List<CourseCategory> list = CourseCategory.find("type=? ORDER BY sequence",type).fetch();
       render(list,type);
  }

      
    /**
     * 显示课程类别下的所有课程
     */
    public static void showCourseCategoryInfo(String categoryId, 
                                                 String searchKey, Integer curPage, Integer perPage) {
      
      if(curPage == null ) curPage = 1;
      if(perPage == null)  perPage = 5;
      
      CourseCategory category = CourseCategory.findById(categoryId);
      if(category.type == ConfigValue.COURSE_ONLINE){
        
        showOnlineCourses(categoryId, searchKey, curPage, perPage);
        
      }else if(category.type == ConfigValue.COURSE_COMMUNITY){ 
        
        showCommunityCourses(categoryId, searchKey, curPage, perPage);
        
      }else{
        
        render();
        
      }
      
    }


     
    public static void showOnlineCourses(String categoryId,
        String searchKey, Integer curPage, Integer perPage) {
      
          PageBean pageBean = null;
          List<CourseOnline> list = null;   
          long total = 0;
          
          if (StringUtils.isEmpty(searchKey)) {
            // 非搜索模式
            total = CourseOnline.count("category_id = ?",categoryId);
            list = CourseOnline.find("category_id = ? ",categoryId).fetch(curPage, perPage);
        } else {
            // 搜索模式
                total = Course
                        .count("select count(*) from Course where category_id = ? and title like ?",categoryId,"%" + searchKey + "%");
                list = Course
                        .find("from Course where category_id = ? and title like ?",categoryId,"%" + searchKey + "%")
                        .fetch(curPage,perPage);
        
        }
        
        pageBean = PageBean.getInstance(curPage, total, perPage);
        
        CourseCategory category = CourseCategory.findById(categoryId);
        
        render(category, list, curPage, perPage, pageBean);
      
      
    }
    
    
    public static void showCommunityCourses(String categoryId,
                                              String searchKey, Integer curPage, Integer perPage) {
      
      PageBean pageBean = null;
      List<CourseOnline> list = null;   
      long total = 0;
      
      if (StringUtils.isEmpty(searchKey)) {
        // 非搜索模式
        total = CourseOnline.count("category_id = ?",categoryId);
        list = CourseOnline.find("category_id = ? ORDER BY endTime DESC",categoryId).fetch(curPage, perPage);
    } else {
        // 搜索模式
            total = Course
                    .count("select count(*) from Course where category_id = ? and title like ?",categoryId,"%" + searchKey + "%");
            list = Course
                    .find("from Course where category_id = ? and title like ?",categoryId,"%" + searchKey + "%")
                    .fetch(curPage,perPage);
    
    }
    
    pageBean = PageBean.getInstance(curPage, total, perPage);
    
    CourseCategory category = CourseCategory.findById(categoryId);
    
    render(category, list, curPage, perPage, pageBean);
      
      
    }
    
    /**
     * 默认每页显示5个活动
     * @param searchKey 关键字
     * @param curPage 页码
     * @param perPage 页记录数
     */
   public static void showMessages(String searchKey, Integer curPage, Integer perPage) {
      
        PageBean pageBean = null;
        List<BackMessage> list = null;   
        long total = 0;
        if(curPage == null ) curPage = 1;
        if(perPage == null)  perPage = 5;

        if (StringUtils.isEmpty(searchKey)) {
            // 非搜索模式
            total = BackMessage.count();
            list = BackMessage.find("ORDER BY pubTime DESC").fetch(curPage, perPage);
        } else {
            // 搜索模式
                total = BackMessage
                        .count("select count(*) from BackMessage where message like ?","%" + searchKey + "%");
                list = BackMessage
                        .find("from BackMessage where message like ?","%" + searchKey + "%")
                        .fetch(curPage,perPage);
            }
        
        
        pageBean = PageBean.getInstance(curPage, total, perPage);
        
        render(list, curPage, perPage, pageBean);

    }
  
  
  

}

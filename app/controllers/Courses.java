/**
 * 文件创建时间：2014年8月15日
 */
package controllers;

import java.io.File;
import java.util.List;

import models.BackMessage;
import models.Course;
import models.CourseCategory;
import models.CourseOnline;
import models.Link;
import models.LinkCategory;
import play.mvc.Controller;
import play.mvc.With;
import utils.FileUtils;
import utils.PageBean;
import utils.StringUtils;
import utils.Uploader;
import utils.enumvalue.Config;
import utils.enumvalue.ConfigValue;
import utils.enumvalue.FileUploadState;

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


    public static void addOnlineCourse(String categoryId){ 
      render(categoryId);
    }
    
    public static void addCommunityCourse(String categoryId){ 
      render(categoryId);
    }
    
     
    public static void showOnlineCourses(String categoryId,
        String searchKey, Integer curPage, Integer perPage) {
      
          PageBean pageBean = null;
          List<CourseOnline> list = null;   
          long total = 0;
          if(curPage == null) curPage = 1;
          if(perPage == null) perPage = 5;
          
          if (StringUtils.isEmpty(searchKey)) {
            // 非搜索模式
            total = CourseOnline.count("category_id = ? ORDER BY PUB_TIME DESC",categoryId);
            list = CourseOnline.find("category_id = ? ORDER BY PUB_TIME DESC",categoryId).fetch(curPage, perPage);
        } else {
            // 搜索模式
                total = Course
                        .count("select count(*) from CourseOnline where category_id = ? and title like ? ORDER BY PUB_TIME DESC",categoryId,"%" + searchKey + "%");
                list = Course
                        .find("from CourseOnline where category_id = ? and title like ? ORDER BY PUB_TIME DESC",categoryId,"%" + searchKey + "%")
                        .fetch(curPage,perPage);
        
        }
        
        pageBean = PageBean.getInstance(curPage, total, perPage);
        
        CourseCategory category = CourseCategory.findById(categoryId);
        
        render(category, list, searchKey, curPage, perPage, pageBean);
      
      
    }
    
    
    public static void showCommunityCourses(String categoryId,
                                              String searchKey, Integer curPage, Integer perPage) {
      
      PageBean pageBean = null;
      List<CourseOnline> list = null;   
      long total = 0;
      
      if (StringUtils.isEmpty(searchKey)) {
        // 非搜索模式
        total = Course.count("category_id = ? ORDER BY END_TIME DESC",categoryId);
        list = Course.find("category_id = ? ORDER BY END_TIME DESC",categoryId).fetch(curPage, perPage);
    } else {
        // 搜索模式
            total = Course
                    .count("select count(*) from Course where category_id = ? and title like ? ORDER BY END_TIME DESC",categoryId,"%" + searchKey + "%");
            list = Course
                    .find("from Course where category_id = ? and title like ? ORDER BY END_TIME DESC",categoryId,"%" + searchKey + "%")
                    .fetch(curPage,perPage);
    
    }
    
    pageBean = PageBean.getInstance(curPage, total, perPage);
    
    CourseCategory category = CourseCategory.findById(categoryId);
    
    render(category, list, searchKey, curPage, perPage, pageBean);
      
      
    }
    
  
  
   /**
    * 保存、编辑在线课程
    * 
    * @param course
    */
   public static void saveOnlineCourse(CourseOnline course,File photo) {
     if (photo != null) {
       
       if (!StringUtils.isEmpty(course.photo)) {
           FileUtils.deleteFile(course.getPhoto());
       }
       
       String baseUrl = Config.DEFAULT_BASE_URL;
       String savePath = Config.COURSE_ONLINE_PHOTO_PATH;
       Uploader uploader = new Uploader(baseUrl, savePath);
       //System.out.println("savePath:"+savePath);
       uploader.upload(photo);

       if (uploader.getState() == FileUploadState.SUCCESS) {
           // 文件上传成功
         course.photo = uploader.getUrl();
       } else {
           flash.error("上传失败");
       }
     } else{
       if(course.photo==null||"".equals(course.photo))
         course.photo = "/public/images/no_pic.jpg";
       
     }
        course.save();
        showOnlineCourses(course.categoryId,"",1,5);
   }
   
   /**
    * 保存，编辑社区课程
    * 
    * @param link
    */
   public static void saveCommunityCourse(Course course, File photo) {
     
     if (photo != null) {
       if (!StringUtils.isEmpty(course.photo)) {
           FileUtils.deleteFile(course.getPhoto());
       }
       
       String baseUrl = Config.DEFAULT_BASE_URL;
       String savePath = Config.COURSE_ONLINE_PHOTO_PATH;
       Uploader uploader = new Uploader(baseUrl, savePath);
       //System.out.println("savePath:"+savePath);
       uploader.upload(photo);

       if (uploader.getState() == FileUploadState.SUCCESS) {
           // 文件上传成功
         course.photo = uploader.getUrl();
       } else {
           flash.error("上传失败");
       }
     } else{
       if(course.photo==null||"".equals(course.photo))
         course.photo = "/public/images/no_pic.jpg";
     }
        course.save();
        showCommunityCourses(course.categoryId,"",1,5);
   }
   
   
   public static void delOnlineCourse(String id,String categoryId){
     CourseOnline.deleteById(id);
     showOnlineCourses(categoryId,"",1,5);
   }
   public static void delCommunityCourse(String id,String categoryId){
     Course.deleteById(id);
     showCommunityCourses(categoryId,"",1,5);
   }

}

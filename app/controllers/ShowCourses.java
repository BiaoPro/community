package controllers;

import play.*;
import play.mvc.*;
import utils.PageBean;
import utils.StringUtils;

import java.util.*;

import models.*;

public class ShowCourses extends Controller{
  public static void Courses(){
    render();
  }
  public static void ShowCourses(){
    render();
  }
  public static void showCourseInfo(String id){
    Course course = Course.findById(id);
    Course courseGet = course;
    render(course,courseGet);
  }
  
  
  public static void showCommunityCourses(String categoryId,
        String searchKey, Integer curPage, Integer perPage) {
  
        PageBean pageBean = null;
        List<CourseOnline> list = null;   
        long total = 0;
        if(curPage == null) curPage = 1;
        if(perPage == null) perPage = 6;
        
        List<CourseCategory> listCategory = CourseCategory.find("type=? ORDER BY sequence",2).fetch();
        if(listCategory.size()==0) categoryId="";
        if(categoryId == null){ categoryId = listCategory.get(0).id; }
       
        if (StringUtils.isEmpty(searchKey)) {
        // 非搜索模式
        total = Course.count("status=1 and audit>=1 and category_id = ? ORDER BY END_TIME DESC",categoryId);
        list = Course.find("status=1 and audit>=1 and category_id = ? ORDER BY END_TIME DESC",categoryId).fetch(curPage, perPage);
        } else {
        // 搜索模式
          
        if(categoryId.equals("all")){  
          total = Course
              .count("select count(*) from Course where status=1 and audit>=1 and title like ? ORDER BY END_TIME DESC","%" + searchKey + "%");
              list = Course
              .find("from Course where status=1 and audit>=1  and title like ? ORDER BY END_TIME DESC","%" + searchKey + "%")
              .fetch(curPage,perPage);
          
        }else{
          
          total = Course
              .count("select count(*) from Course where status=1 and audit>=1 and category_id = ? and title like ? ORDER BY END_TIME DESC",categoryId,"%" + searchKey + "%");
              list = Course
              .find("from Course where status=1 and audit>=1 and category_id = ? and title like ? ORDER BY END_TIME DESC",categoryId,"%" + searchKey + "%")
              .fetch(curPage,perPage);
          }
        
        }
        
        pageBean = PageBean.getInstance(curPage, total, perPage);
        
        CourseCategory category = CourseCategory.findById(categoryId);
        
        Course courseGet;
        if(Course.count()>0)
         courseGet = (Course) Course.findAll().get(0);
        else courseGet=null;
        
        render(listCategory,category,courseGet, list, searchKey, curPage, perPage, pageBean);


    }
  
  public static void showOnlineCourses(String categoryId,
      String searchKey, Integer curPage, Integer perPage) {
    
        PageBean pageBean = null;
        List<CourseOnline> list = null;   
        long total = 0;
        if(curPage == null) curPage = 1;
        if(perPage == null) perPage = 6;
        
        List<CourseCategory> listCategory = CourseCategory.find("type=? ORDER BY sequence",1).fetch();
        
        if(listCategory.size()==0) categoryId="";
        if(categoryId == null){ categoryId = listCategory.get(0).id; }
        
        if (StringUtils.isEmpty(searchKey)) {
          // 非搜索模式
          total = CourseOnline.count("status=1 and audit>=1 and category_id = ? ORDER BY PUB_TIME DESC",categoryId);
          list = CourseOnline.find("status=1 and audit>=1 and category_id = ? ORDER BY PUB_TIME DESC",categoryId).fetch(curPage, perPage);
      } else {
          // 搜索模式
              total = Course
                      .count("select count(*) from CourseOnline where status=1 and audit>=1 and category_id = ? and title like ? ORDER BY PUB_TIME DESC",categoryId,"%" + searchKey + "%");
              list = Course
                      .find("from CourseOnline where status=1 and audit>=1 and category_id = ? and title like ? ORDER BY PUB_TIME DESC",categoryId,"%" + searchKey + "%")
                      .fetch(curPage,perPage);
      
      }
      
      pageBean = PageBean.getInstance(curPage, total, perPage);
      
      CourseCategory category = CourseCategory.findById(categoryId);
      
      CourseOnline courseGet;
      if(CourseOnline.count()>0)
       courseGet = (CourseOnline) CourseOnline.findAll().get(0);
      else courseGet=null;
      
      render(listCategory, category, courseGet, list, searchKey, curPage, perPage, pageBean);
    
    
  }
  
  
  
}

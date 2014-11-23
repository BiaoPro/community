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
  public static void ShowCoursesInfo(String id){
    Course course = Course.findById(id);
    render(course);
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
  
  
  
}

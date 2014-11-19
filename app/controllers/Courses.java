/**
 * 文件创建时间：2014年8月15日
 */
package controllers;

import java.util.List;

import models.Course;
import models.CourseCategory;
import models.Link;
import models.LinkCategory;
import play.mvc.Controller;

/**
 * @author biao
 *
 */

public class Courses extends Controller {

  /**
   * 显示所有课堂类别
   */
  public static void showCourseCategories() {
       List<CourseCategory> list = CourseCategory.find("ORDER BY sequence").fetch();
       render(list);
  }

      
    /**
     * 显示课程类别下的所有课程
     */
    public static void showCourseCategoryInfo(String categoryId) {
      CourseCategory category = CourseCategory.findById(categoryId);
      List<Link> list = Course.find("category_id = ? ORDER BY sequence",categoryId).fetch();
      render(category,list);
    }
  
  
  

}

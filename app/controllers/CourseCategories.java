package controllers;
import models.CourseCategory;
import models.LinkCategory;
import play.*;
import play.mvc.*;

import java.util.List;



/**链接类别管理
 * 
 * @author 吴泽标
 *
 */

public class CourseCategories extends Controller {
  
	/**
	 * 显示所有课堂类别
	 */
	public static void showCourseCategories() {

	  Courses.showCourseCategories();
	}
	
	
	/**
     * 保存新增课程类别
     * @param vo
     */
    public static void saveCourseCategory(CourseCategory vo) {
      System.out.println(vo.id+":"+vo.name+":"+vo.status);
      vo.save();
        showCourseCategories();
    }
    
    /**
     * 编辑课程类别
     * @param vo
     */
    public static void editCourseCategory(CourseCategory vo) {
        vo.save();
        showCourseCategoryInfo(vo.id);
    }
    
    /**
     * 删除类别
     * 
     * @param id
     */
    public static void delCourseCategory(String id) {
        CourseCategory.deleteById(id);
        showCourseCategories();
    }
	
	/**
	 * 跳转到显示信息页面
	 * @param categoryId
	 */
	public static void showCourseCategoryInfo(String categoryId) {
		Courses.showCourseCategoryInfo(categoryId);
	}
	
	
	
	/**
	 * 改变课程显示的状态
	 * 
	 * @param categoryId
	 * @param status
	 */
	public static void changeStatus(String categoryId, int status) {
		CourseCategory category = CourseCategory.findById(categoryId);
		category.status = status;
		category.save();
		showCourseCategories();
	}
	
	/**
	 * 向上移动
	 * 
	 * @param categoryId
	 */
	public static void sequenceUp(String categoryId) {
		CourseCategory category = CourseCategory.findById(categoryId);
		CourseCategory frontCategory = category.getFrontCategory();

		if (frontCategory != null) {
			long temp = category.sequence;
			category.sequence = frontCategory.sequence;
			category.save();
			frontCategory.sequence = temp;
			frontCategory.save();
		}
		showCourseCategories();
	}
	
	/**
	 * 向下移动
	 * 
	 * @param categoryId
	 */
	public static void sequenceDown(String categoryId) {
		CourseCategory category = CourseCategory.findById(categoryId);
		CourseCategory backCategory = category.getBackCategory();

		if (backCategory != null) {
			long temp = category.sequence;
			category.sequence = backCategory.sequence;
			category.save();
			backCategory.sequence = temp;
			backCategory.save();
		}
		showCourseCategories();
	}
}

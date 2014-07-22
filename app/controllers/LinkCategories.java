package controllers;
import models.LinkCategory;
import play.*;
import play.mvc.*;

import java.util.List;



/**
 * 仅供参看用，需做修改
 * 
 * @author 吴泽标
 *
 */

public class LinkCategories extends Controller {
	/**
	 * 显示所有链接类别
	 */
	public static void showLinkCategories() {
		List<LinkCategory> allCategoryList = LinkCategory.find("ORDER BY sequence").fetch();
		render(allCategoryList);
	}
	
	/**
	 * 跳转到显示信息页面
	 * @param categoryId
	 */
	public static void showLinkCategoryInfo(String categoryId) {
		LinkCategory linkCategory = LinkCategory.findById(categoryId);
		render(linkCategory);
	}
	
	/**
	 * 保存新增链接类别
	 * 
	 * @param linkcategory
	 */
	public static void saveLinkCategory(LinkCategory linkCategory) {
		linkCategory.save();

		showLinkCategories();
	}
	
	/**
	 * 删除链接类别
	 * 
	 * @param id
	 */
	public static void delLinkCategory(String id) {
		LinkCategory.deleteById(id);
		showLinkCategories();
	}
	
	/**
	 * 修改链接类别
	 * 
	 * @param linkcategory
	 */
	public static void editLinkCategory(LinkCategory linkCategory) {
		linkCategory.save();

		showLinkCategories();
	}
	
	/**
	 * 改变导航栏的状态
	 * 
	 * @param categoryId
	 * @param status
	 */
	public static void changeStatus(String categoryId, int status) {
		LinkCategory linkCategory = LinkCategory.findById(categoryId);
		linkCategory.status = status;
		linkCategory.save();
		showLinkCategories();
	}
	
	/**
	 * 向上移动
	 * 
	 * @param categoryId
	 */
	public static void sequenceUp(String categoryId) {
		LinkCategory category = LinkCategory.findById(categoryId);
		LinkCategory frontCategory = category.getFrontCategory();

		if (frontCategory != null) {
			long temp = category.sequence;
			category.sequence = frontCategory.sequence;
			category.save();
			frontCategory.sequence = temp;
			frontCategory.save();
		}
		showLinkCategories();
	}
	
	/**
	 * 向下移动
	 * 
	 * @param categoryId
	 */
	public static void sequenceDown(String categoryId) {
		LinkCategory category = LinkCategory.findById(categoryId);
		LinkCategory backCategory = category.getBackCategory();

		if (backCategory != null) {
			long temp = category.sequence;
			category.sequence = backCategory.sequence;
			category.save();
			backCategory.sequence = temp;
			backCategory.save();
		}
		showLinkCategories();
	}
}

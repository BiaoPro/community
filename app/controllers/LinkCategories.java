package controllers;
import models.LinkCategory;
import play.*;
import play.mvc.*;
import utils.SessionManager;

import java.util.List;



/**链接类别管理
 * 
 * @author 吴泽标
 *
 */
@With(UserSecures.class)
public class LinkCategories extends Controller {
  
	/**
	 * 显示所有链接类别
	 */
	public static void showLinkCategories() {
	  SessionManager.setLinkCategory(session);
		Links.showLinkCategories();
	}
	
	
	/**
     * 保存新增链接类别
     * @param linkcategory
     */
    public static void saveLinkCategory(LinkCategory linkCategory) {
        linkCategory.save();
        showLinkCategories();
    }
    
    /**
     * 编辑链接类别
     * @param linkcategory
     */
    public static void editLinkCategory(LinkCategory linkCategory) {
        linkCategory.save();
        showLinkCategoryInfo(linkCategory.id);
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
	 * 跳转到显示信息页面
	 * @param categoryId
	 */
	public static void showLinkCategoryInfo(String categoryId) {
		Links.showLinkCategoryInfo(categoryId);
	}
	
	
	/**
	 * 改变链接显示的状态
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

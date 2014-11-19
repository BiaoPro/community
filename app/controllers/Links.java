package controllers;

import models.*;
import play.*;
import play.mvc.*;
import utils.PageBean;
import utils.StringUtils;

import java.util.List;


/**
 * 仅供参看用，需做修改
 * 
 * @author 吴泽标
 *
 */
@With(UserSecures.class)
public class Links extends Controller {
	/**
	 * 跳转基本页面
	 */
	public static void index() {
		render();
	}	
	
	/**
     * 保存、编辑链接
     * 
     * @param link
     */
    public static void saveLink(Link link) {
        link.save();
        showLinkCategoryInfo(link.categoryId);
    }
    
    /**
     * 删除链接
     * 
     * @param link
     */
    public static void delLink(String id,String categoryId) {
        Link.deleteById(id);
        showLinkCategoryInfo(categoryId);
    }
    
    
    /**
     * 显示所有链接类别
     */
    public static void showLinkCategories() {
      List<LinkCategory> linkCategoryList = LinkCategory.find("ORDER BY sequence").fetch();
        render(linkCategoryList);
    }
    
    /**
     * 显示链接类别下的所有链接
     */
    public static void showLinkCategoryInfo(String categoryId) {
      LinkCategory linkCategory = LinkCategory.findById(categoryId);
      List<Link> linkList = Link.find("category_id = ? ORDER BY sequence",categoryId).fetch();
      render(linkCategory,linkList);
    }
    
    
    
    
    
    /**
     * 修改链接状态
     * 
     * @param linkId
     * @param status
     */
    public static void changeLinkStatus(String linkId, int status) {
        Link link = Link.findById(linkId);
        link.status = status;
        link.save();
        index();
    }
    
    
    /**
     * 显示类别下所有链接
     * @param category_id
     */
    public static void showLinks(String category_id) {
      List<Link> linkList = Link.find("category_id",category_id).fetch();
      render(linkList);
  }
    
    
    public static void base() {
      render();
  }

    public static void addLink() {
      render();
  }
	
		
	/**
	 * 跳转的到修改链接界面
	 * 
	 * @param linkId
	 */
	public static void showLinkInfo(String linkId) {
		Link link = Link.findById(linkId);
		List<LinkCategory> allCategoryList = LinkCategory.findAll();
		render(link, allCategoryList);
	}


}

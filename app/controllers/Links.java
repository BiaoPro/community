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
public class Links extends Controller {
	/**
	 * 跳转基本页面
	 */
	public static void index() {
		showLinks(null, null, 1);
	}

	/**
	 * 显示所有链接
	 * 
	 * @param searchKey
	 * @param curPage
	 */
	public static void showLinks(String searchScope, String searchKey,
			int curPage) {
		curPage = curPage <= 0 ? 1 : curPage;
		if (!StringUtils.isEmpty(searchKey)) {
			// 搜索模式
			if (StringUtils.isEmpty(searchScope) || searchScope.equals("name")) {
				// 链接名搜索
				long total = Link.count("name LIKE ?", "%" + searchKey + "%");
				List<Link> linkList = Link.find("name LIKE ? ORDER BY name",
						"%" + searchKey + "%")
						.fetch(curPage, PageBean.PER_PAGE);
				PageBean pageBean = PageBean.getInstance(curPage, total);
				render(linkList, searchScope, searchKey, curPage, pageBean);
			} else {
				// 类别搜索
				long total = Link
						.count("SELECT COUNT(link) FROM Link link,LinkCategory category WHERE link.categoryId=category.id AND category.name LIKE ?",
								"%" + searchKey + "%");
				List<Link> linkList = Link
						.find("SELECT link FROM Link link,LinkCategory category WHERE link.categoryId=category.id AND category.name LIKE ? ORDER BY link.name",
								"%" + searchKey + "%").fetch(curPage,
								PageBean.PER_PAGE);
				PageBean pageBean = PageBean.getInstance(curPage, total);
				render(linkList, searchScope, searchKey, curPage, pageBean);
			}
		} else {
			// 非搜索模式
			long total = Link.count();
			List<Link> linkList = Link.find("ORDER BY name").fetch(curPage,
					PageBean.PER_PAGE);
			PageBean pageBean = PageBean.getInstance(curPage, total);
			render(linkList, searchScope, searchKey, curPage, pageBean);
		}
	}

	/**
	 * 保存链接
	 * 
	 * @param link
	 */
	public static void saveLink(Link link) {
		link.save();
		index();
	}

	/**
	 * 删除链接
	 * 
	 * @param id
	 */
	public static void delLink(String id) {
		Link.delete("id", id);
		index();
	}

	/**
	 * 跳转到添加链接的页面
	 */
	public static void addLink() {
		List<LinkCategory> allCategoryList = LinkCategory.findAll();
		render(allCategoryList);
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
}

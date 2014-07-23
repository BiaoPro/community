package controllers;

import java.io.File;
import java.util.Date;
import java.util.List;

//import models.Article;
//import models.ArticleCategory;
//import models.SpecialTopic;
import play.mvc.Controller;
import play.mvc.With;
import utils.DateUtils;
import utils.FileUtils;
import utils.PageBean;
import utils.SessionManager;
import utils.StringUtils;
import utils.Uploader;
import utils.enumvalue.Config;



/**
 * 仅供参看用，需做修改
 * 
 * @author 吴泽标
 *
 */


//@With(UserSecures.class)
public class Articles extends Controller {

	/**
	 * 跳转基本页面
	 */
	public static void index() {
		render();
	}
	
///*	
//
//	/**
//	 * 跳转添加文章页面
//	 */
//	public static void addArticle() {
//		renderArgs.put("articleCategoryList",
//				ArticleCategory.getArticleCategorys());
//		renderArgs.put("specialTopicList", SpecialTopic.findAll());
//		renderArgs.put("articleParentCategoryList",
//				ArticleCategory.getCategories(1));
//		renderArgs.put("articleChildCategoryList",
//				ArticleCategory.getCategories(2));
//		renderArgs.put("curTime", Articles.getCurTime());
//		renderArgs.put("canAddPictureArticle", Articles.canAddPictureArticle());
//		render();
//	}
//
//	/**
//	 * 保存文章数据
//	 * 
//	 * @param article
//	 * @param created
//	 *            发表时间
//	 * @param hasImage
//	 * @param image
//	 *            首页显示的图片
//	 */
//	public static void saveArticle(Article article, String created,
//			int hasImage, File image) {
//		// 判断文章分类是否为空
//		if (StringUtils.isEmpty(article.categoryId)) {
//			flash("tips", "文章分类不能为空");
//			addArticle();
//		}
//
//		if (hasImage == 1) {
//			// 设置成首页显示
//			if (image != null && FileUtils.isImageType(image)) {
//				// 有上传图片
//				if (!StringUtils.isEmpty(article.scrollPicture)) {
//					// 删除原本的图片
//					String imageUrl = article.scrollPicture.substring(1);// 储存的url格式为/XX/XX.jpg，要去掉前面的'/'
//					FileUtils.deleteFile(imageUrl);
//				}
//
//				String baseUrl = Config.DEFAULT_BASE_URL;
//				String savePath = Config.ARTICLE_SCROLL_IMAGES;
//				Uploader uploader = new Uploader(baseUrl, savePath);
//				uploader.upload(image);
//
//				article.scrollPicture = uploader.getUrl();
//			}
//		} else {
//			// 取消在首页上显示
//			if (!StringUtils.isEmpty(article.scrollPicture)) {
//				// 删除原本的图片
//				String imageUrl = article.scrollPicture.substring(1);// 储存的url格式为/XX/XX.jpg，要去掉前面的'/'
//				FileUtils.deleteFile(imageUrl);
//				article.scrollPicture = null;
//			}
//		}
//
//		if (DateUtils.isDateStr(created)) {
//			article.created = DateUtils.getTimeByDateStr(created);
//		}
//		article.userId = SessionManager.getLoginedId(session);
//		System.out.println("article"+": "+article.outLink);
//		if(article.outLink !=1)
//			article.outLink = 0;
//
//		article.save();
//		index();
//	}
//
//	/**
//	 * 跳转到显示文章信息
//	 */
//	public static void showArticleInfo(String articleId) {
//		Article article = Article.findById(articleId);
//
//		renderArgs.put("articleParentCategoryList",
//				ArticleCategory.getCategories(1));
//		renderArgs.put("articleChildCategoryList",
//				ArticleCategory.getCategories(2));
//		renderArgs.put("articleCategory",
//				ArticleCategory.findById(article.categoryId));
//		renderArgs.put("specialTopicList", SpecialTopic.findAll());
//		render(article);
//	}
//
//	/**
//	 * 修改发布文章状态
//	 * 
//	 */
//	public static void editArticleStatus(String articleId, int status) {
//		Article article = Article.findById(articleId);
//		article.status = status;
//		article.save();
//		index();
//	}
//
//	/**
//	 * 删除文章
//	 * 
//	 * @param articleId
//	 */
//	public static void delArticle(String articleId) {
//
//		Article article = Article.findById(articleId);
//		if (!StringUtils.isEmpty(article.scrollPicture)) {
//			// 删除原本的图片
//			String imageUrl = article.scrollPicture.substring(1);// 储存的url格式为/XX/XX.jpg，要去掉前面的'/'
//			FileUtils.deleteFile(imageUrl);
//		}
//		article.delete();
//		index();
//	}
//
//	/**
//	 * 根据关键字和页数显示用户列表 关键字为null时显示所有用户 默认每页显示5个用户
//	 * 
//	 * @param searchKey
//	 * @param curPage
//	 */
//	public static void showArticles(String searchKey, int curPage,
//			String selectedContent) {
//		List<Article> articleList = null;
//		long total = 0;
//		if (!StringUtils.isEmpty(searchKey)) {
//			// 搜索模式
//			if (StringUtils.isEmpty(selectedContent)
//					|| selectedContent.equals("0")) {
//				// 标题
//				total = Article.count("title LIKE ?", "%" + searchKey + "%");
//				articleList = Article.find(
//						"title LIKE ? ORDER BY top DESC,created DESC",
//						"%" + searchKey + "%")
//						.fetch(curPage, PageBean.PER_PAGE);
//				PageBean pageBean = PageBean.getInstance(curPage, total);
//				render(articleList, searchKey, curPage, pageBean, searchKey);
//			} else if (selectedContent.equals("1")) {
//				total = Article
//						.count("SELECT COUNT(article) FROM Article article,ArticleCategory category WHERE article.categoryId=category.id AND category.name LIKE ?",
//								"%" + searchKey + "%");
//				articleList = Article
//						.find("SELECT article FROM Article article,ArticleCategory category WHERE article.categoryId=category.id AND category.name LIKE ? ORDER BY top DESC,created DESC",
//								"%" + searchKey + "%").fetch(curPage,
//								PageBean.PER_PAGE);
//				PageBean pageBean = PageBean.getInstance(curPage, total);
//				render(articleList, searchKey, curPage, pageBean, searchKey);
//			} else {
//				total = Article
//						.count("SELECT COUNT(article) FROM Article article,SpecialTopic topic WHERE article.topicId=topic.id AND topic.name LIKE ?",
//								"%" + searchKey + "%");
//				articleList = Article
//						.find("SELECT article FROM Article article,SpecialTopic topic WHERE article.topicId=topic.id AND topic.name LIKE ? ORDER BY top DESC,created DESC",
//								"%" + searchKey + "%").fetch(curPage,
//								PageBean.PER_PAGE);
//				PageBean pageBean = PageBean.getInstance(curPage, total);
//				render(articleList, searchKey, curPage, pageBean, searchKey);
//			}
//		} else {
//			// 非搜索模式
//			total = Article.count();
//			articleList = Article.find("ORDER BY top DESC,created DESC").fetch(
//					curPage, PageBean.PER_PAGE);
//
//			PageBean pageBean = PageBean.getInstance(curPage, total);
//			render(articleList, searchKey, curPage, pageBean, searchKey);
//		}
//	}
//
//	/**
//	 * 返回当前时间
//	 * 
//	 */
//	public static long getCurTime() {
//		return new Date().getTime();
//	}
//
//	/**
//	 * 修改置顶状态
//	 * 
//	 */
//	public static void editArticleTop(String articleId, boolean judge) {
//		Article article = Article.findById(articleId);
//		if (judge)
//			article.top = 0;
//		else
//			article.top = Articles.getCurTime();
//		article.save();
//		index();
//	}
//
//	/**
//	 * 计算已有图片的文章个数,如果个数大于等于5,返回false 如果返回false,则不能添加带图片文章
//	 * 
//	 */
//	public static boolean canAddPictureArticle() {
//		int count = 0;
//		List<Article> articleList = Article.findAll();
//		for (Article article : articleList)
//			if (article.scrollPicture != null)
//				count++;
//		if (count >= 5)
//			return false;
//		else
//			return true;
//	}
//	

}
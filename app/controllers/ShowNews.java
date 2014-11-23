package controllers;

import play.*;
import play.mvc.*;
import utils.PageBeanFactory;

import java.util.*;

import models.*;

public class ShowNews extends Controller{
	// 展示新闻主页
	public static void News(int curpage){
		//获取文章存在的栏目
		List newsClassIdsList = News.getNewsClassIdsExist();
		List newsClassList = NewsClass.getNewsClassByIds(newsClassIdsList);
		
		//获取文章存在的日期
		List newsDateList = News.getNewsDateExist();
		
		//获取首页文章
		PageBeanFactory pageBean= new PageBeanFactory(curpage,7);
		List currentList = News.getIndexNews(pageBean,0);
		int maxPage=pageBean.getMaxPage();
		int[] maxPageArgs = new int[maxPage];
		for(int i=0;i<maxPageArgs.length;i++){
			maxPageArgs[i]=i+1;
		}
		int fontPage=curpage-1;
		if(fontPage==0){
			fontPage=1;
		}
		int nextPage=curpage+1;
		if(nextPage>maxPage){
			nextPage=maxPage;
		}
		
		render(newsClassList,newsDateList,currentList,maxPageArgs,fontPage,nextPage);
	}
	
	/**
	 * @description 筛选日期和栏目
	 */
	public static void newsFilter(int curpage,String newsCreateDate,String newClassType){
		//获取文章存在的栏目
		List newsClassIdsList = News.getNewsClassIdsExist();
		List newsClassList = NewsClass.getNewsClassByIds(newsClassIdsList);
		//获取文章存在的日期
		List newsDateList = News.getNewsDateExist();
		
			PageBeanFactory pageBean = new PageBeanFactory(curpage,7);
			List currentList = News.getNewsByDateAndClass(pageBean, newsCreateDate, newClassType,0);
			int maxPage=pageBean.getMaxPage();
			int[] maxPageArgs = new int[maxPage];
			for(int i=0;i<maxPageArgs.length;i++){
				maxPageArgs[i]=i+1;
			}
			int fontPage=curpage-1;
			if(fontPage==0){
				fontPage=1;
			}
			int nextPage=curpage+1;
			if(nextPage>maxPage){
				nextPage=maxPage;
			}
			render(newsClassList,newsDateList,currentList,fontPage,nextPage,maxPageArgs,newsCreateDate,newClassType);
		
		
	}
	// 展示单个新闻
	public static void showNewsInfo(String id){
		News news = News.findById(id); 
		User audit = User.findById(news.newsAuditId);
		String auditName = audit.rname;
		User user= User.findById(news.newsAuthorId);
		String authorName = user.rname;
		render(news,auditName,authorName);
	}
	
}

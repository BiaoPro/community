package controllers;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import models.News;
import models.NewsClass;
import play.mvc.Controller;
import utils.DateUtils;
import utils.Debug;
import utils.KindEditorUpload;
import utils.NewsBean;
import utils.PageBean;
import utils.PageBeanFactory;
/*
 * @author YourKingda
 * @description:新闻控制器
 */
public class BackNews extends Controller{
	
	/*
	 * @description 新闻发布
	 */
	public static void newsSend(String status){
		//获取所有栏目
		List newsClasslist = NewsClass.findAll();
		if(newsClasslist.size()==0){
			BackNewsClass.newsClassManager("warnAdd");
		}
		render(newsClasslist,status);
	}
	
	
	/*
	 * @description 增加一篇新闻
	 */
	public static void addNews(){
		//预留栏目
		News news = new News(); 
		news.newsContent=params.get("content");
		news.newsTitle=params.get("title");
		news.newsClassId=params.get("classId");
		Long time = DateUtils.getTimeByDate(new Date());
		String timeStr = DateUtils.getDateTimeStr(time);
		news.newsCreateDate=timeStr;
		news.newsModifyDate=timeStr;
		try{
			news._save();
			newsSend("success");
		}catch(Exception e){
		  e.printStackTrace();    
			newsSend("failed");
		}
		
	}
	
	
	/*
	 * 上传文件
	 * @param file控件
	 */
	public static void uploadFile(File  imgFile){
		String dirName = params.get("dir");
		String fileName = imgFile.getName();
		KindEditorUpload keu = new KindEditorUpload(dirName,fileName,imgFile);
		Controller.renderJSON(keu.upload());
	}
	

	/**
	 * @description 删除新闻
	 */
	public static void deleteNews(String id){
		News.deleteNewsById(id);
			newsManager(1);
	}
	
	/**
	 * @description 编辑新闻
	 * 
	 */
	public static void newsEdit(String id,String status){
			List newsClasslist = NewsClass.findAll();
			News news = News.getNewsById(id);
			render(news,newsClasslist,status);
	}
	
	/**
	 * @description 编辑新闻action
	 */
	public static void updateNews(String id){
		News news = News.findById(id);
		news.newsContent=params.get("content");
		news.newsTitle=params.get("title");
		news.newsClassId=params.get("classId");
		Long time = DateUtils.getTimeByDate(new Date());
		String timeStr = DateUtils.getDateTimeStr(time);
		news.newsModifyDate=timeStr;
		news._save();
		newsEdit(id,"success");
	}
	
	
	/*
	 * @description 新闻管理首页
	 */
	public static void newsManager(int curpage){
		if(curpage==0){
			curpage=1;
		}
		//获取文章存在的栏目
		List newsClassIdsList = News.getNewsClassIdsExist();
		List newsClasslist = NewsClass.getNewsClassByIds(newsClassIdsList);
		
		//获取文章存在的日期
		List newsDateList = News.getNewsDateExist();
		
		//获取首页文章
		PageBeanFactory pageBean= new PageBeanFactory(curpage,7);
		List currentList = News.getIndexNews(pageBean,1);
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
		//渲染界面
		render(newsClasslist,newsDateList,currentList,maxPageArgs,fontPage,nextPage);
	}
	
	/**
	 * @description 筛选请求
	 */
	public static void filter(int curpage,String newsTitle,String newsCreateDate,String newClassType){
		//获取文章存在的栏目
		List newsClassIdsList = News.getNewsClassIdsExist();
		List newsClasslist = NewsClass.getNewsClassByIds(newsClassIdsList);
		//获取文章存在的日期
		List newsDateList = News.getNewsDateExist();
		if(newsTitle.length()==0){
			PageBeanFactory pageBean = new PageBeanFactory(curpage,7);
			List currentList = News.getNewsByDateAndClass(pageBean, newsCreateDate, newClassType,1);
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
			render(newsClasslist,newsDateList,currentList,fontPage,nextPage,maxPageArgs,newsTitle,newsCreateDate,newClassType);
		}
		else{
			PageBeanFactory pageBean = new PageBeanFactory(curpage,7);
			List currentList = News.getNewsByTitle(pageBean, newsTitle,1);
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
			newsCreateDate="";
			newClassType="";
			render(newsClasslist,newsDateList,currentList,fontPage,nextPage,maxPageArgs,newsTitle,newsCreateDate,newClassType);
			
		}
	}
	
	/**
	 * @description 批量处理逻辑
	 */
	public static void patchDo(){
		String choice = params.get("patchType");
		String[] ids = params.getAll("checkBoxId");
		if(choice!=null&&ids!=null){
			int type = choice.length();
			if(type==1){
				for(String id:ids){
					News.findById(id)._delete();
				}
				newsManager(1);
			}else if(type==2){
				for(String id:ids){
					News news = News.findById(id);
					if(news.newsAudit==1){
						news.newsAudit=0;
					}
					else{
						news.newsAudit=1;
					}
					news._save();
				}
				newsManager(1);
			}else {
				
			}
			
		}
	}
	/**
	 * @description 审核处理逻辑
	 */
	public static void audit(String id){
		News news = News.findById(id);
		if(news.newsAudit==1){
			news.newsAudit=0;
		}
		else{
			news.newsAudit=1;
		}
		news._save();
		newsManager(1);
	}
	
	
	
	
}

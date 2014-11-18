package controllers;

import java.io.File;
import java.util.Date;
import java.util.List;

import models.News;
import models.NewsClass;
import play.mvc.Controller;
import utils.DateUtils;
import utils.Debug;
import utils.KindEditorUpload;
import utils.PageBean;
import beans.NewsBean;
import beans.PageBeanFactory;
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
			newsSend("failed");
		}
		
	}
	
	/*
	 * @description 新闻管理
	 */
	public static void newsManager(int curpage){
		//获取文章存在的栏目
		List newsClassIdsList = News.getNewsClassIdsExist();
		List newsClasslist = NewsClass.getNewsClassByIds(newsClassIdsList);
		
		//获取文章存在的日期
		List newsDateList = News.getNewsDateExist();
		
		
		String newsTitle = params.get("newsTitle");
		String newsCreateDate = params.get("date");
		String newClassType = params.get("type");
		PageBean pageBean;
		List currentList;
		//获取首页文章
		if(newsTitle==null&&newsCreateDate==null&&newClassType==null){
			pageBean = PageBean.getInstance(curpage, News.count());
			currentList= NewsBean.getIndexCurrentList(curpage,7);
		}
		else{
			currentList= NewsBean.getIndexCurrentList(curpage,7);
			pageBean = PageBean.getInstance(curpage, News.count());
		}
			
		//获取最大页数
		int maxpage =pageBean.getMaxPage();
		int[] maxPageArgs=new int[maxpage];
		for(int i=0;i<maxpage;i++){
			maxPageArgs[i]=i+1;
		}
		//判断前一页和后一页
		int fontPage=curpage-1;
		int nextPage=curpage+1;
		if(fontPage==0){
			fontPage=1;
		}
		if(nextPage>maxpage){
			nextPage=maxpage;
		}
		//渲染界面
		render(newsClasslist,newsDateList,currentList,maxPageArgs,fontPage,nextPage);
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
}

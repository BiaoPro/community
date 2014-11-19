package controllers;

import play.*;
import play.mvc.*;
import java.util.*;

import models.*;

public class ShowNews extends Controller{
	// 展示新闻主页
	public static void News(){
		//获取文章存在的栏目
		List newsClassIdsList = News.getNewsClassIdsExist();
		List newsClasslist = NewsClass.getNewsClassByIds(newsClassIdsList);
		
		//获取文章存在的日期
		List newsDateList = News.getNewsDateExist();
		
		
		render();
	}
	// 展示单个新闻
	public static void showNewsInfo(){
		render();
	}
	//得到新闻json数据
	public static void NewsJson(){
		
	}
}

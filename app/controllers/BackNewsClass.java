package controllers;

import java.util.List;

import models.News;
import models.NewsClass;
import play.mvc.Controller;
import play.mvc.With;
/*
 * @author kingda
 * @description 后台栏目管理控制类
 */
@With(UserSecures.class)
public class BackNewsClass extends Controller{
	
	/*
	 * @description 栏目管理
	 * @param status 操作状态
	 */
	public static void newsClassManager(String status){
		//获取所有栏目
		List newsClassList = NewsClass.findAll();
		render(newsClassList,status);
		
	}
	/*
	 * @description 增加栏目
	 * @param newClassType req过来的栏目标题
	 */
	public static void addNewsClass(String newClassType){
		List list = NewsClass.find("byNewClassType",newClassType).fetch();
		if(list.size()>0){
			newsClassManager("addRepeat");
			return;
		}
		NewsClass newsClass = new NewsClass();
		newsClass.newClassType=newClassType;
		newsClass.save();
		newsClassManager("addSuccess");
		
	}
	/*
	 * @description 修改栏目
	 * @param req过来的id和标题
	 */
	public static void modify(){
		String[] ids =params.getAll("id");
		String[] titles = params.getAll("title");
		for(int i=0;i<ids.length;i++){
			NewsClass newsClass = NewsClass.findById(ids[i]);
			if(titles[i]!=null)
			newsClass.newClassType=titles[i];
			newsClass.save();
		}
		newsClassManager("modify");
		
	}
	/*
	 * @description 删除栏目
	 * @param req传过来的deleteId 
	 */
	public static void delete(){
		String id = params.get("deleteId");
		NewsClass newsClass = NewsClass.findById(id);
		if(News.checkIsExistNewsClass(id)){
			newsClassManager("FailedDelete");
		}else{
			newsClass._delete();
			newsClassManager("delete");
		}
	}
}

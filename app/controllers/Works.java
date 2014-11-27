package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;
/*
 * @author zhuangxiangpeng
 */
public class Works extends Controller {
  
  
  public static void index() {
    render();
  }
  public static void addWork(){
	  render();
  }
  public static void showWorkInfo(String id){
	  Work work=Work.findById(id);
	    List<Work> WorksList=Work.all().fetch(4);
	  render(work,WorksList);
  }
  public static void showWorks(){
	  String srarchKey="";
	  if(params.get("srarchKey")!=null)
		  srarchKey=params.get("srarchKey");
	  List<Work> WorksList=Work.findWorks("searchKey", 1);
	  render(WorksList);
  }
  
  
  /*
   * 保存新增work信息
   * @param work
   */
  public static void saveWork(Work work){
	  work.save(); 
	  flash.put("backMessage", "发布成功~");
	  addWork();
  }
}

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
  public static void showWorkInfo(Work work){
	  work=Work.findById("11b50ec9-6cd8-4128-9e15-95fc8511b38d");
	  render(work);
  }
  public static void showWorks(){
	  render();
  }
  /*
   * 保存新增work信息
   * @param work
   */
  public static void saveWork(Work work){
	  work.save();
	  render(work);
  }
}

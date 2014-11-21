package controllers;

import java.util.List;

import play.mvc.Controller;
import utils.PageBean;
import utils.StringUtils;

import models.BackMessage;
import models.LinkCategory;

public class BackMessages extends Controller {
  
 /**
  * 显示所有链接类别
  */
 public static void showBackMessages() {
     List<BackMessage> list = BackMessage.find("ORDER BY pubTime DESC").fetch();
     render(list);
 } 
 
 
 /**
  * 默认每页显示5个活动
  * 
  * @param curPage
  */
 public static void showMessages(Integer curPage) {
   
     long total = BackMessage.count();
     
     if(curPage == null ) curPage = 1;
     
     List<BackMessage> list = BackMessage.find("ORDER BY pubTime DESC").fetch(curPage, 2);
     
     PageBean pageBean = PageBean.getInstance(curPage, total, 2);
     
     render(list, curPage, pageBean);

 }

}

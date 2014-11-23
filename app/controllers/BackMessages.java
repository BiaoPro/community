package controllers;

import java.util.List;

import play.mvc.Controller;
import utils.PageBean;
import utils.StringUtils;

import models.BackMessage;
import models.LinkCategory;

public class BackMessages extends Controller {
  
 
 /**
  * 默认每页显示5个活动
  * @param searchKey 关键字
  * @param curPage 页码
  * @param perPage 页记录数
  */
public static void showMessages(String searchKey, Integer curPage, Integer perPage) {
   
     PageBean pageBean = null;
     List<BackMessage> list = null;   
     long total = 0;
     if(curPage == null ) curPage = 1;
     if(perPage == null)  perPage = 5;

     if (StringUtils.isEmpty(searchKey)) {
         // 非搜索模式
         total = BackMessage.count();
         list = BackMessage.find("ORDER BY pubTime DESC").fetch(curPage, perPage);
     } else {
         // 搜索模式
             total = BackMessage
                     .count("select count(*) from BackMessage where message like ?","%" + searchKey + "%");
             list = BackMessage
                     .find("from BackMessage where message like ?","%" + searchKey + "%")
                     .fetch(curPage,perPage);
         }
     
     
     pageBean = PageBean.getInstance(curPage, total, perPage);
     
     render(list, curPage, perPage, pageBean);

 }

}

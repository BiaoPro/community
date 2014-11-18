package beans;

import java.util.ArrayList;
import java.util.List;

import models.News;
import utils.PageBean;

public class NewsBean {

	private String newsTitle;
	private String newsCreateDate;
	private String newClassType;
	private PageBean pageBean;
	public NewsBean(String newsTitle,String newsCreateDate,String newClassType){
		this.newsTitle=newsTitle;
		this.newsCreateDate=newsCreateDate;
		this.newClassType=newClassType;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getNewsCreateDate() {
		return newsCreateDate;
	}
	public void setNewsCreateDate(String newsCreateDate) {
		this.newsCreateDate = newsCreateDate;
	}
	public String getNewClassType() {
		return newClassType;
	}
	public void setNewClassType(String newClassType) {
		this.newClassType = newClassType;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	
	/*
	 * @description 获取指定行数的数据
	 */
	public static List getCurrentList(NewsBean newsBean,int curPage,int perPage){
		int total = News.getTotal(newsBean.getNewsCreateDate(), newsBean.getNewsTitle(), newsBean.getNewClassType());
		PageBean pageBean = PageBean.getInstance(curPage, (long)total, perPage);
		newsBean.setPageBean(pageBean);
		List list = News.getAllNews(newsBean.getNewsCreateDate(), newsBean.getNewsTitle(), newsBean.getNewClassType(), pageBean);
		List resList=new ArrayList();
		for(int i=0;i<list.size();i++)
        {
            Object[] o = (Object[])list.get(i);  //转型为数组
            NewsBean tempNewsBean = new NewsBean((String)o[0],(String)o[1],(String)o[2]);
            resList.add(tempNewsBean);
        }
		return resList;
	}
	
	public static List getIndexCurrentList(int curPage,int perPage){
		int total = (int)News.count();
		PageBean pageBean = PageBean.getInstance(curPage, (long)total, perPage);
		List list = News.getIndexNews(pageBean);
		List resList=new ArrayList();
		for(int i=0;i<list.size();i++)
        {
            Object[] o = (Object[])list.get(i);  //转型为数组
            NewsBean tempNewsBean = new NewsBean((String)o[0],(String)o[1],(String)o[2]);
            resList.add(tempNewsBean);
        }
		return resList;
	}
	
	
	
}

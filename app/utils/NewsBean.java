package utils;

import java.util.ArrayList;
import java.util.List;

import models.News;

public class NewsBean {

	private String newsTitle;
	private String newsCreateDate;
	private String newClassType;
	private String newsId;
	private String newsModifyDate;
	private PageBean pageBean;
	public NewsBean(String newsId,String newsTitle,String newsCreateDate,String newClassType,String newsModifyDate){
		this.newsId=newsId;
		this.newsTitle=newsTitle;
		this.newsCreateDate=newsCreateDate;
		this.newClassType=newClassType;
		this.newsModifyDate=newsModifyDate;
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
	public String getNewsId() {
		return newsId;
	}
	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	public String getNewsModifyDate() {
		return newsModifyDate;
	}
	public void setNewsModifyDate(String newsModifyDate) {
		this.newsModifyDate = newsModifyDate;
	}
	
	
	
}

package utils;

import models.User;

public class NewsBean {

	private String newsTitle;
	private String newsCreateDate;
	private String newClassType;
	private String newsId;
	private String newsModifyDate;
	private PageBean pageBean;
	private String newsAuthorId;
	private String newsAuthorName;
	private int newsAudit;
	public NewsBean(String newsId,String newsTitle,String newsCreateDate,String newClassType,String newsModifyDate,int newsAudit,String newsAuthorId){
		this.newsId=newsId;
		this.newsTitle=newsTitle;
		this.newsCreateDate=newsCreateDate;
		this.newClassType=newClassType;
		this.newsModifyDate=newsModifyDate;
		this.newsAudit=newsAudit;
		this.newsAuthorId=newsAuthorId;
	}
	
	public String getNewsAuthorName() {
		User user = User.findById(this.newsAuthorId);
		this.newsAuthorName=user.rname;
		return newsAuthorName;
	}

	public String getNewsAuthorId() {
		return newsAuthorId;
	}
	
	public void setNewsAuthorId(String newsAuthorId) {
		this.newsAuthorId = newsAuthorId;
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

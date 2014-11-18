package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;

import play.db.jpa.GenericModel;
import play.db.jpa.JPA;
import play.libs.Codec;
import utils.PageBean;
import beans.NewsBean;
import beans.PageBeanFactory;

/*
 * @author YourKingda
 * @description news model class
 */
@Entity
@Table(name = "news")
public class News extends GenericModel{
	@Id
	@Column(name="news_id")
    public String newsId;
	
	@Column(name="news_class_id")
	public String newsClassId;
	
	@Column(name="news_title")
	public String newsTitle;
	
	@Column(name="news_content",length=100000)
	public String newsContent;
	
	@Column(name="news_create_date")
	public String newsCreateDate;
	
	@Column(name="news_modify_date")
	public String newsModifyDate;
	
	@Column(name="news_author")
	public int newsAuthor;
	
	@Column(name="news_audit")
	public int newsAudit;
	
	
	public News(){
		this.newsId=Codec.UUID();
		
		
	}
	/*
	 * @description 获取所有文章中存在的栏目
	 * @return list
	 */
	public static List getNewsClassIdsExist(){
		List list = new ArrayList();
		Query query = JPA.em().createQuery("select distinct newsClassId from News");
		List<News> ids = query.getResultList();
		return ids;
	}
	
	/*
	 * @description 获取所有文章中存在的日期
	 * @return list
	 */
	public static List getNewsDateExist(){
		List list = new ArrayList();
		Query query = JPA.em().createQuery("select distinct SubString(newsCreateDate,1,10) from News");
		List<News> dates = query.getResultList();
		return dates;
	}
	
	/**
	 * @description 获取所有文章列表
	 * @param date 日期
	 * @param newTitle 标题
	 * @param newClassType 新闻栏目
	 * @return list
	 */
	public static List getAllNews(String date,String newsTitle,String newClassType,PageBean pageBean){
		String hql="select a.newsTitle,a.newsCreateDate,b.newClassType "+
		"from News a,NewsClass b where a.newsClassId = b.newClassId and a.newsTitle like '%"
		+newsTitle+"%' and a.newsCreateDate like '"+date+"%' and b.newClassType like '"+newClassType+"' order by a.newsCreateDate desc";
		
		int curpage=pageBean.getCurPage();
		Query query = JPA.em().createQuery(hql);
		int startPos=curpage!=0?pageBean.getPerPage()*(curpage-1):0;
		query.setFirstResult(startPos);
		query.setMaxResults(pageBean.getPerPage());
		List list =query.getResultList();
		return list;
	}
	
	/*
	 * @description 获取指定文章个数
	 */
	public static int getTotal(String date,String newsTitle,String newClassType){
		String hql="select a.newsTitle,a.newsCreateDate,b.newClassType "+
		"from News a,NewsClass b where a.newsClassId = b.newClassId and a.newsTitle like '%"
		+newsTitle+"%' and a.newsCreateDate like '"+date+"%' and b.newClassType like '%"+newClassType+"%' order by a.newsCreateDate desc";
		Query query = JPA.em().createQuery(hql);
		List list = query.getResultList();
		return list.size();
	}
	
	/*
	 * 
	 */
	public static List getIndexNews(PageBean pageBean){
		String fontPageHql = "select a.newsTitle,a.newsCreateDate,b.newClassType "+
		"from News a,NewsClass b where a.newsClassId = b.newClassId order by a.newsCreateDate desc";
		int curpage=pageBean.getCurPage();
		Query query = JPA.em().createQuery(fontPageHql);
		int startPos=curpage!=0?pageBean.getPerPage()*(curpage-1):0;
		query.setFirstResult(startPos);
		query.setMaxResults(pageBean.getPerPage());
		List list =query.getResultList();
		return list;
		
	}
		
}



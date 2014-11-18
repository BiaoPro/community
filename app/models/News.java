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
	 * @param pageBean 一个pageBean对象
	 * @return list
	 */
	public static List getAllNews(PageBeanFactory pageBeanFactory,String date,String newsTitle,String newClassType){
		String hql="select a.newsTitle,a.newsCreateDate,b.newClassType "+
		"from News a,NewsClass b where a.newsClassId = b.newClassId and a.newsTitle like '%"
		+newsTitle+"%' and a.newsCreateDate like '"+date+"%' and b.newClassType like '%"+newClassType+"%' order by a.newsCreateDate desc";
		Query query = JPA.em().createQuery(hql);
		query.setFirstResult(pageBeanFactory.getStartPos());
		query.setMaxResults(pageBeanFactory.getPerPage());
		List list =query.getResultList();
		pageBeanFactory.setTotal(list.size());
		System.out.println();
		System.out.println("--totalList:"+list.size());
		System.out.println("--maxpage:"+pageBeanFactory.getMaxPage());
		List resList=new ArrayList();
		for(int i=0;i<list.size();i++)
        {
			NewsBean newsBean = new NewsBean();
            Object[] o = (Object[])list.get(i);  //转型为数组
            newsBean.setNewsTitle((String)o[0]);  //和select中顺序的类型相对应,可以是类
            newsBean.setNewsCreateDate((String)o[1]);
            newsBean.setNewClassType((String)o[2]);
            resList.add(newsBean);
        }
		return resList;
	}
	
	
}



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
import utils.NewsBean;
import utils.PageBean;
import utils.PageBeanFactory;


/*
 * @author YourKingda
 * @description news model class
 */
@Entity
@Table(name = "news")
public class News extends GenericModel{
	@Id
	@Column(name="id")
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
	/**
	 * @description 获取所有文章中存在的栏目
	 * @return list
	 */
	public static List getNewsClassIdsExist(){
		List list = new ArrayList();
		Query query = JPA.em().createQuery("select distinct newsClassId from News");
		List<News> ids = query.getResultList();
		return ids;
	}
	
	/**
	 * @description 获取所有文章中存在的日期
	 * @return list
	 */
	public static List getNewsDateExist(){
		List list = new ArrayList();
		Query query = JPA.em().createQuery("select distinct SubString(newsCreateDate,1,10) from News order by newsCreateDate desc");
		List<News> dates = query.getResultList();
		return dates;
	}
	
	/**
	 * @description 获取所有文章
	 * 
	 */
	public static List getIndexNews(PageBeanFactory pageBean,int isAll){
		String hql="";
		if(isAll==1){
			
			hql = "select a.newsId,a.newsTitle,a.newsCreateDate,b.newClassType,a.newsModifyDate,a.newsAudit"+
			" from News a,NewsClass b where a.newsClassId = b.newClassId order by a.newsModifyDate desc";
		}
		else{
			 hql = "select a.newsId,a.newsTitle,a.newsCreateDate,b.newClassType,a.newsModifyDate,a.newsAudit"+
			" from News a,NewsClass b where a.newsClassId = b.newClassId and a.newsAudit =1 order by a.newsModifyDate desc";
		}
		return executeJPA(pageBean,hql);
	}
	
	/**
	 * @description 根据标题获取文章
	 */
	public static List getNewsByTitle(PageBeanFactory pageBean,String title,int isAll){
		String hql="";
		if(isAll==1){
			hql = "select a.newsId,a.newsTitle,a.newsCreateDate,b.newClassType,a.newsModifyDate,a.newsAudit "+
			" from News a,NewsClass b where a.newsClassId = b.newClassId and a.newsTitle like '%"+title+"%' order by a.newsModifyDate desc";
		}
		else{
			hql = "select a.newsId,a.newsTitle,a.newsCreateDate,b.newClassType,a.newsModifyDate,a.newsAudit "+
			" from News a,NewsClass b where a.newsClassId = b.newClassId and a.newsTitle like '%"+title+"%' and a.newsAudit =1 order by a.newsModifyDate desc";
			
		}
		return executeJPA(pageBean,hql);
	}
	
	/**
	 * @description 根据栏目或者时间获取文章
	 */
	public static List getNewsByDateAndClass(PageBeanFactory pageBean,String date,String className,int isAll){
		String hql;
		if(isAll==1){
			hql="select a.newsId,a.newsTitle,a.newsCreateDate,b.newClassType,a.newsModifyDate,a.newsAudit "+
			" from News a,NewsClass b where a.newsClassId = b.newClassId"
			+" and a.newsCreateDate like '"+date+"%' and b.newClassType like '%"+className+"%' order by a.newsModifyDate desc";
		}
		else{
			hql="select a.newsId,a.newsTitle,a.newsCreateDate,b.newClassType,a.newsModifyDate,a.newsAudit "+
			" from News a,NewsClass b where a.newsClassId = b.newClassId"
			+" and a.newsCreateDate like '"+date+"%' and b.newClassType like '%"+className+"%' and a.newsAudit =1 order by a.newsModifyDate desc";
		}
		return executeJPA(pageBean,hql);
		
	}
	
	
	/**
	 * @description 工具方法 
	 */
	public static List changeList(List list){
		List resList=new ArrayList();
		for(int i=0;i<list.size();i++)
        {
            Object[] o = (Object[])list.get(i);  //转型为数组
            NewsBean tempNewsBean = new NewsBean((String)o[0],(String)o[1],(String)o[2],(String)o[3],(String)o[4],(Integer)o[5]);
            resList.add(tempNewsBean);
        }
		return resList;
	}
	public static List executeJPA(PageBeanFactory pageBean,String hql){
		pageBean.setTotal(getTotal(hql));
		Query query = JPA.em().createQuery(hql);
		int startPos=pageBean.getStartPos();
		query.setFirstResult(startPos);
		query.setMaxResults(pageBean.getPerPage());
		List list =query.getResultList();
		return changeList(list);
	}
	
	public static int getTotal(String hql){
		
		Query query = JPA.em().createQuery(hql);
		List list = query.getResultList();
		return list.size();
	}
	

	/**
	 * @description 删除新闻
	 * @param id 新闻id
	 */
	public static void deleteNewsById(String id){
		News news=News.findById(id);
		news.delete();
	}
	/**
	 * @description 得到一篇新闻
	 * @param id 新闻id
	 */
	public static News getNewsById(String id){
		News news = News.findById(id);
		return news;
	}
	
	/**
	 * @description 查看该栏目是否存在于新闻当中
	 * @param newsClassId 栏目id
	 */
	public static boolean checkIsExistNewsClass(String id){
		String hql = "from News as a where a.newsClassId ='"+id+"'";
		Query query = JPA.em().createQuery(hql);
		List list = query.getResultList();
		if(list.size()>0){
			return true;
		}
		else{
			return false;
		}
	}
	

}



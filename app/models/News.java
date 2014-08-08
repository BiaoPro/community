package models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.jpa.GenericModel;

/*
 * @author:john
 */
@Entity
@Table(name = "news")
public class News extends GenericModel{
	@Id
	@Column(name="news_id")
    public String newsId;
	
	@Column(name="news_class_id")
	public int newsClassId;
	
	@Column(name="news_titile")
	public String newsTitle;
	
	@Column(name="news_body")
	public String newsBody;
	
	@Column(name="news_date")
	public Date newsDate;
	
	@Column(name="news_author")
	public int newsAuthor;
	
	@Column(name="news_audit")
	public int newsAudit;
	
}

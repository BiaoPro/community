package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import play.db.jpa.GenericModel;
import play.libs.Codec;
import utils.PageBean;
import utils.StringUtils;

/**
 * 链接表
 * @author 吴泽标
 *
 */
@Entity
@Table(name="link")
public class Link  extends GenericModel{
	@Id
	public String id;
	
	@Column(name="link_name")
	public String name;//链接显示名称
	
	@Column(name="link_url")
	public String url;// 链接的url
	
	@Column(name="status",columnDefinition="int default 1")
	public int status;// 状态0：未发布，1：发布
	
	@Column(name = "sequence")
	public long sequence; //链接顺序
	
	@Column(name="category_id")
	public String categoryId;//链接类别id
	
	
	public Link() {
		this.status = 1;
		this.id = Codec.UUID();
		this.sequence=new Date().getTime();
	}
	
	/**
	 * 获取链接类别
	 * @return
	 */
	public LinkCategory getLinkCategory() {
		return LinkCategory.find("id", this.categoryId).first();
	}
	
	public static void deleteById(String id){
	   Link.delete("id", id);
	  
	}
	
	/**
	 * 获取链接列表，默认每一页5条信息
	 * 
	 * @param searchKey
	 *            搜索关键字
	 * @param curPage
	 *            当前页
	 * @return
	 */
	public static List<Link> findLinks(String searchKey, int curPage) {
		if (StringUtils.isEmpty(searchKey)) {
			return Link.all().fetch(curPage, 5);
		} else {
			return Link.find("link_name like ?", "%" + searchKey + "%").fetch(
					curPage, 5);
		}
	}

	/**
	 * 获取pageBean
	 * 
	 * @param searchKey
	 * @param curPage
	 * @return
	 */
	public static PageBean getPageBean(String searchKey, int curPage) {
		long total = 0;
		if (StringUtils.isEmpty(searchKey))
			total = Link.count();
		else
			total = Link.find("link_name like ?", "%" + searchKey + "%").fetch()
					.size();
		return PageBean.getInstance(curPage, total, 5);
	}
	
	/**
	 * 是否显示
	 * 
	 * @return
	 */
	public boolean isShow() {
		return this.status == 1;
	}
}

package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.jpa.GenericModel;
import play.libs.Codec;


/**
 * 链接分类表
 * @author 吴泽标
 *
 */

@Entity
@Table(name = "link_category")
public class LinkCategory extends GenericModel {
	@Id
	public String id;

	@Column(name = "category_name")
	public String name;//类别名称

	@Column(name = "sequence")
	public long sequence; //链接顺序

	@Column(name = "status",columnDefinition="int default 1")
	public int status;// 状态，0：不显示，1：显示

	public LinkCategory() {
		this.id = Codec.UUID();
		this.status = 1;
		this.sequence = new Date().getTime();
	}

	
	 public String getCommunityCoursesStr(){
	      List<Link> list = Link.find("categoryId=?",this.id).fetch();
	      String rsStr = "[";
	      
	      for (Link link : list) {
	        rsStr+=link.toStringLink()+",";
          }
	      rsStr.substring(0, rsStr.lastIndexOf(","));
	      rsStr+="]";
	      
	      return rsStr;
	      
	  }
	
	
	public static void deleteById(String id) {
		Link.delete("categoryId", id);
		LinkCategory.delete("id", id);
	}

	/**
	 * 是否显示
	 * 
	 * @return
	 */
	public boolean isShow() {
		return this.status == 1;
	}

	/**
	 * 获得前面的类目
	 * 
	 * @return
	 */
	public LinkCategory getFrontCategory() {
		return LinkCategory.find("sequence<? ORDER BY sequence DESC",
				this.sequence).first();
	}

	/**
	 * 获得后面的类别
	 * 
	 * @return
	 */
	public LinkCategory getBackCategory() {
		return LinkCategory
				.find("sequence>? ORDER BY sequence ", this.sequence).first();
	}

	public List<Link> getLinks(boolean isVisable) {
		if (isVisable) {
			return Link.find("status=1 AND categoryId=?",
					this.id).fetch();
		} else {
			return Link.find("categoryId=?", this.id).fetch();
		}
	}
}

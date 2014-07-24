package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.GenericModel;
import play.libs.Codec;
import utils.PageBean;
import utils.StringUtils;
/*
 * 租房信息表
 * @author john
 */
@Entity
@Table(name = "house")
public class House extends GenericModel{
	@Id
	public String id;
	
	//1为隐藏，2为显示
	@Column(name="status",columnDefinition="int default 1")
	public int status;
	
	//1代表待审核，2为审核不通过，3为审核通过，管理员添加的默认为3
	@Column(name="audit",columnDefinition="int default 1")
	public int audit;
	
	@ManyToOne
	public User author;
	
	@Column(name = "author_phone")
	public String authorPhone;
	
	@Column(name = "photo_url")
	public String photoUrl;
	
	@Column(name = "address")
	public String address;
	
	@Column(name = "details")
	public String details;
	
	@Column(name = "price")
	public String price;
	
	@Lob
	@Column(name = "message")
	public String message;
	
	//初始化构造方法
	public House(){
		this.id = Codec.UUID();
		this.status=1;
		this.audit=1;
	}
	//查询房子信息
	//关键字 	当前页
	public static List<House> findHouses(String searchKey, int curPage) {
		if (StringUtils.isEmpty(searchKey)) {
			return House.all().fetch(curPage, 5);
		} else {
			return House.find("address like ?", "%" + searchKey + "%").fetch(
					curPage, 5);
		}
	}
	//pagebean
	public static PageBean getPageBean(String searchKey, int curPage) {
		long total = 0;
		if (StringUtils.isEmpty(searchKey))
			total = House.count();
		else
			total = House.find("address like ?", "%" + searchKey + "%").fetch()
					.size();
		return PageBean.getInstance(curPage, total, 5);
	}
	//是否显示
	public boolean isShow(){
		return status==2;
	}
}

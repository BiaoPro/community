package models;

import java.util.Date;
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
	//出租方
		@ManyToOne
		public User author;
	//0为隐藏，1为显示
	@Column(name="status",columnDefinition="int default 1")
	public int status;
	
	//1代表待审核，2为审核不通过，3为审核通过，管理员添加的默认为3
	@Column(name="audit",columnDefinition="int default 1")
	public int audit;
	
	//出租方式
	@Column(name = "way")
	public String way;
	//您的身份
	public String indentity;
	//联系人
	public String contacts;
	//联系电话
	public String phone;
	//小区
	@Column(name="community")
	public String community;
	//地址
	@Column(name = "address")
	public String address;
	//户型
	@Column(name = "bed_room")
	public int bedRoom;
	@Column(name = "living_rom")
	public int livingRoom;
	@Column(name = "bath_room")
	public int bathRoom;
	public double square;
	//楼层
	@Column(name = "current_floor")
	public int currentFloor;
	@Column(name = "count_floor")
	public int countFloor;
	//房屋情况
	@Column(name = "decoration_situation")
	public String decorationSituation;
	@Column(name = "class_situation")
	public String classSituation;
	//家电配备
	public String equipment;
	//租金
	@Column(name = "price")
	public double price;
	//描述
	@Column(name = "details")
	public String details;
	//照片
	@Column(name = "photo_url")
	public String photoUrl;
	//发布时间
	public Date date;
	//初始化构造方法
	public House(){
		this.id = Codec.UUID();
		this.date=new Date();
		this.status=1;
		this.audit=1;
	}
	//查询房子信息
	//关键字 	当前页
	public static List<House> findHouses(String key,String searchKey, int curPage) {
		if (StringUtils.isEmpty(searchKey)) {
			return House.all().fetch(curPage, 5);
		} else {
			return House.find(key+" like ?",searchKey).fetch(
					curPage, 5);
		}
	}
	//pagebean
	public static PageBean getPageBean(String key,String searchKey, int curPage) {
		long total = 0;
		if (StringUtils.isEmpty(searchKey))
			total = House.count();
		else
			total = House.find(key+" like ?",searchKey).fetch()
					.size();
		return PageBean.getInstance(curPage, total, 5);
	}
	//是否显示
	public boolean isShow(){
		return status==2;
	}
}

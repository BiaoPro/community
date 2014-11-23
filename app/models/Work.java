package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.GenericModel;
import play.libs.Codec;
/*
 * 招工信息表
 * @author zhangxiangpeng
 */
@Entity
@Table(name="work")
public class Work extends GenericModel {
	@Id
	public String id;
	//1代表待审核，2为审核不通过，3为审核通过，管理员添加的默认为3
	@Column(name="audit",columnDefinition="int default 1")
	public int audit;
	//1为隐藏，2为显示
	@Column(name="status",columnDefinition="int default 1")
	public int status;
	@ManyToOne
	public User author;//用户
	public String title;//标题
	public String unit;//====公司
	public String position;//职位类别
	public String number;// 招聘人数
	public String degree;// 学历
	public String year;// 工作年限
	public String sex;// 性别
	public int minage;//最小年龄
	public int maxage;//最大年龄
	public double minsalary;//月薪
	public double maxsalary;//月薪
	public String details;//职位描述
	public String linkman;//联系人
	public String phone;//联系电话
	public Date date;//发布时间
	public Work(){//初始化构造方法
		this.id = Codec.UUID();
		this.date=new Date();
		this.status=1;
		this.audit=1;
	}
}

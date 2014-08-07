package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.GenericModel;
/*
 * 招工信息表
 * @author john
 */
@Entity
@Table(name="work")
public class Work extends GenericModel {
	@Id
	public String id;
	
	@ManyToOne
	public User author;
	
	public String phone;
	
	public String unit;
	public String salary;
	
	@Lob
	@Column(name = "details")
	public String details;
		
	//1代表待审核，2为审核不通过，3为审核通过，管理员添加的默认为3
	@Column(name="audit",columnDefinition="int default 1")
	public int audit;

	//1为隐藏，2为显示
	@Column(name="status",columnDefinition="int default 1")
	public int status;
}

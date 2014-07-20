package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.data.validation.Unique;
import play.db.jpa.GenericModel;
import play.libs.Codec;

/*
 * 系统参数表
 * 用于记录系统特定参数
 */

@Entity
@Table(name = "SystemParameter")
public class SystemParameter  extends GenericModel{
	
	@Id
	public String id;
	
	@Column(name = "showActNum")
	public int showActNum;//主页活动显示条数
	
	@Column(name = "indexName")
	public  String indexName;//主页网站名称
	
	@Column(name = "copyright")
	public  String copyright;//版权所有
	
	@Column(name = "address")
	public  String address;//地址
	
	@Column(name = "telephone")
	public String telephone;//电话
	
	@Column(name = "mailbox")
	public String mailbox;//邮箱
	
	
	
	public SystemParameter() {
		this.id = Codec.UUID();
		this.showActNum=4;
		this.indexName="教师发展中心";
		this.copyright="华南农业大学教务处";
		this.address="华南农业大学行政办公楼三楼 广州市天河区五山路483号 510642";
		this.telephone="";
		this.mailbox="";

				
	}

	/**
	 * 判断特定参数是否存在
	 */
	public  boolean isParaExist(String ParaName){
		return SystemParameter.count("? !=null",ParaName)>0;
	}
	
	
	public static SystemParameter getSystemParameter(){
		if(SystemParameter.count()>0) {
			return (SystemParameter) SystemParameter.findAll().get(0);
		} else {
			return null;
		}
	}
	
	
	
}

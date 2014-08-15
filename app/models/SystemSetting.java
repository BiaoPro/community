package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.data.validation.Unique;
import play.db.jpa.GenericModel;
import play.libs.Codec;

/**
 * 
 * 系统参数表
 * 用于记录系统特定参数
 * @author 吴泽标
 *
 */
@Entity
@Table(name = "Setting")
public class SystemSetting  extends GenericModel{
	
	@Id
	public String id;

	@Column(name = "index_name")
	public  String indexName;//主页网站名称
	
	@Column(name = "copyright")
	public  String copyright;//版权所有
	
	@Column(name = "address")
	public  String address;//地址
	
	@Column(name = "telephone")
	public String telephone;//电话
	
	@Column(name = "mailbox")
	public String mailbox;//邮箱
	
	
	
	public SystemSetting() {
	  
		this.id = Codec.UUID();
		this.indexName="新广州人社区服务";
		this.copyright="天河区新广州人社区服务中心";
		this.address="华南农业大学 广州市天河区五山路483号 510642";
		this.telephone="";
		this.mailbox="";

				
	}

	/**
	 * 判断特定参数是否存在
	 */
	public  boolean isParaExist(String ParaName){
		return SystemSetting.count("? !=null",ParaName)>0;
	}
	
	
	/**
     * 获取系统参数
     */
	public static SystemSetting getSetting(){
		if(SystemSetting.count()>0) {
			return (SystemSetting) SystemSetting.findAll().get(0);
		} else {
		  SystemSetting sp = new SystemSetting();
		  sp.save();
		  return sp;
		}
	}
	
	
	
}

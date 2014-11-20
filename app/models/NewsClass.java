package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.jpa.GenericModel;
import play.libs.Codec;

/*
 * @author:kingda
 * @description:newsClass model class
 */
@Entity
@Table(name = "new_class")
public class NewsClass extends GenericModel{
	@Id
	@Column(name="new_class_id")
	public String newClassId;
	@Column(name="new_class_type")
	public String newClassType;
	public NewsClass(){
		this.newClassId=Codec.UUID();
	}
	
	/*
	 * 
	 * @description 得到newsClass对象
	 * @param String[] newsClassId字符串组
	 * @return 对象链表
	 */
	public static List getNewsClassByIds(List ids){
		if(ids==null){
			return null;
		}
		List list = new ArrayList();
		for(int i=0;i<ids.size();i++){
			NewsClass newsClass = NewsClass.findById(ids.get(i));
			list.add(newsClass);
		}
		return list;
	}
	
	
	
	
}

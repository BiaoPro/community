package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.jpa.GenericModel;
import play.libs.Codec;

/**
 * 问题性质表
 * @author 吴泽标
 *
 */
@Entity
@Table(name = "question_type")
public class Character extends GenericModel {
  
  @Id
  public String id;
  
  @Column(name = "character_name")
  public String character_name; //类别名称
  
  @Column(name = "remark")
  public String remark; //备注
  
  
  public Character(){
    this.id = Codec.UUID(); 
  }
  
  

}

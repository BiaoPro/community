package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.jpa.GenericModel;
import play.libs.Codec;

/**
 * 问题类别表
 * @author 吴泽标
 *
 */
@Entity
@Table(name = "question_type")
public class QuestionType extends GenericModel {
  
  @Id
  public String id;
  
  @Column(name = "type_name")
  public String type_name; //类别名称
  
  @Column(name = "remark")
  public String remark; //备注
  
  
  public QuestionType(){
    this.id = Codec.UUID(); 
  }
  
  

}

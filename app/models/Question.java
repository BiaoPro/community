package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.jpa.GenericModel;
import play.libs.Codec;



/**
 * 问题表
 * @author 吴泽标
 *
 */
@Entity
@Table(name = "question")
public class Question extends GenericModel {
  
  @Id
  public String id;

  @Column(name = "character_id")
  public String character_id;//问题性质id （建议、申述、维权、其他）
  
  @Column(name = "type_id")
  public String type_id;//问题类别id（生活、住宿、交通之类）
  
  @Column(name = "name")
  public String name;//标题

  @Column(name = "content")
  public String content;//内容
  
  @Column(name = "status")
  public String status;//状态 0-不显示 1-显示
  
  @Column(name = "Remark")
  public String remark;// 备注
  
  public Question(){
    this.id = Codec.UUID();
  }
  
  
  
  

}

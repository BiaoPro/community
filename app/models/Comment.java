package models;

import javax.persistence.Column;
import javax.persistence.Id;

import play.db.jpa.GenericModel;
import play.libs.Codec;

/**
 * 问题评论、回复表
 * @author 吴泽标
 *
 */
public class Comment extends GenericModel {
  
  @Id
  public String id;
  
  @Column(name = "question_id")
  public String question_id;//回复问题id
  
  @Column(name = "content")
  public String content;//回复内容
  
  @Column(name = "user_type")
  public String user_type;  //1-热心市民 2-热心社工 3-专业律师
  
  public Comment(){
    this.id = Codec.UUID();
  }

}

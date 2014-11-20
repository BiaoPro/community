package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.jpa.GenericModel;
import play.libs.Codec;
/**
 * 课程信息表
 * @author 吴泽标
 *
 */
@Entity
@Table(name="Course")
public class Course extends GenericModel{
  
  @Id
  public String id;
  
  @Column(name="category_id")
  public String categoryId;//课程类别
  
  @Column(name="title")
  public String title;//课程标题
  
  @Column(name="body")
  public String body;//课程正文
  
  @Column(name="phone_number")
  public String phoneNumber;//联系人电话
  
  @Column(name="start_time")
  public String startTime;//开始时间
  
  @Column(name="end_time")
  public int endTime;//结束时间
  
  
  @Column(name="place")
  public String place;//开课地点
  

  @Column(name="author_id")
  public String authorId;//发布用户id
  
  
  @Column(name="audit")
  public String audit;//0代表待审核，-1为审核不通过，1为审核通过,2为后台插入
  
  
  @Column(name="status")
  public String status;//0-不显示 1-显示
  
  
  public void Course(){
    this.id = Codec.UUID();
  }
  
  
  
  
  

}

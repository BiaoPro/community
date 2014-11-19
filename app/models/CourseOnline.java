package models;

import javax.persistence.Column;
import javax.persistence.Id;

import play.db.jpa.GenericModel;
import play.libs.Codec;


/**
 * 在线课程信息表
 * @author 吴泽标
 *
 */

public class CourseOnline  extends GenericModel{
  
  @Id
  public String id;
  
  @Column(name="Category_id")
  public String Category_id;//课程类别
  
  @Column(name="title")
  public String title;//课程名称
  
  @Column(name="info")
  public String info;//课程概述
  
  @Column(name="url")
  public String url;//课程视频链接地址
  
  @Column(name="author_id")
  public String author_id;//发布用户id
  
  
  @Column(name="audit")
  public String audit;//0代表待审核，-1为审核不通过，1为审核通过,2为后台插入
  
  
  @Column(name="status")
  public String status;//0-不显示 1-显示
  
  
  public void CourseOnline(){
    this.id = Codec.UUID();
  }

}

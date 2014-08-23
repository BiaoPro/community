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
  
  @Column(name="type_id")
  public String typeId;//课程类别
  
  @Column(name="name")
  public String name;//课程名称
  
  @Column(name="course_Info")
  public String courseInfo;//课程概述
  
  @Column(name="course_url")
  public String courseUrl;//课程视频链接地址
  
  @Column(name="remark")
  public String remark;//备注
  
  
  public void CourseOnline(){
    this.id = Codec.UUID();
  }

}

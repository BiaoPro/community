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
  
  @Column(name="type_id")
  public String typeId;//课程类别
  
  @Column(name="name")
  public String name;//课程名称
  
  @Column(name="start_time")
  public String startTime;//开始时间
  
  @Column(name="total_time")
  public int totalTime;//持续时间(分钟)
  
  @Column(name="course_Info")
  public String courseInfo;//课程概述
  
  @Column(name="place")
  public String place;//开课地点
  
  @Column(name="organizer")
  public String organizer;//承办单位
  
  @Column(name="teacher")
  public String teacher;//授课老师
  
  @Column(name="teacher_info")
  public String teacherInfo;//授课老师信息
  
  @Column(name="linkman")
  public String linkman;//组织课程联系人
  
  @Column(name="linkman_phone")
  public String linkmanPhone;//联系人电话
  
  @Column(name="remark")
  public String remark;//备注
  
  
  public void Course(){
    this.id = Codec.UUID();
  }
  
  
  

}

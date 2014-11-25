package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.jpa.GenericModel;
import play.libs.Codec;
import utils.enumvalue.ConfigValue;
/**
 * 课程信息表
 * @author 吴泽标
 *
 */
@Entity
@Table(name="course_category")
public class CourseCategory extends GenericModel{
  
  @Id
  public String id;
  
  @Column(name="category_name")
  public String name;//课程名称
  
  @Column(name = "sequence")
  public long sequence; //链接顺序

  @Column(name = "type",columnDefinition="int default 2")
  public int type;// 1在线视频，2社区发布
  
  @Column(name = "status",columnDefinition="int default 1")
  public int status;// 状态，0：不显示，1：显示
  
  
  public CourseCategory(){
    this.id = Codec.UUID();
    this.sequence = new Date().getTime();
    this.type = 2;
    this.status = 1;
  }
  
  public CourseCategory(String name,int type){
    this.id = Codec.UUID();
    this.sequence = new Date().getTime();
    this.type = type;
    this.status = 1;
    this.name = name;
  }
  
  public static void deleteById(String id) {
    Course.delete("categoryId", id);
    CourseOnline.delete("categoryId", id);
    CourseCategory.delete("id", id);
}
  
//  /**
//   * 根据课程类别id返回CourseCategory
//   * @param account
//   * @return
//   */
//  public static CourseCategory findById(String id) {
//    return CourseCategory.find("id = ?", i).first();
//  }

/**
 * 是否显示
 * 
 * @return
 */
public boolean isShow() {
    return this.status == 1;
}


/**
 * 是否在线课程
 * 
 * @return
 */
public boolean isOnlineCourse() {
    return this.type == 1;
}
/**
 * 是否社区课堂
 * 
 * @return
 */
public boolean isCommunityCourse() {
    return this.type == 2;
}

/**
 * 获得前面的类目
 * 
 * @return
 */
public CourseCategory getFrontCategory(int type) {
    return CourseCategory.find("type=? and sequence<? ORDER BY sequence DESC",
            type,this.sequence).first();
}

/**
 * 获得后面的类别
 * 
 * @return
 */
public CourseCategory getBackCategory(int type) {
    return CourseCategory
            .find("type=? and sequence>? ORDER BY sequence ", type, this.sequence).first();
}

/**获取类目下的课程列表
 * @param size
 * @return
 */
public List getCourses(String size) {
  
    if (this.type ==ConfigValue.COURSE_ONLINE  ) {
        return CourseOnline.find("status=1 AND categoryId=? where LIMIT 0,?",this.id,size).fetch();
    }else if(this.type ==ConfigValue.COURSE_COMMUNITY  ) {
        return Course.find("status=1 AND categoryId=? where LIMIT 0, ?", this.id, size).fetch();
    }
    return Course.find("status=1 AND categoryId=? where LIMIT 0, ?", this.id, size).fetch();
    
}
  
  
  
  
  

}

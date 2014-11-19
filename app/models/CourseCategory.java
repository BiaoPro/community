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
 * 获得前面的类目
 * 
 * @return
 */
public CourseCategory getFrontCategory() {
    return CourseCategory.find("sequence<? ORDER BY sequence DESC",
            this.sequence).first();
}

/**
 * 获得后面的类别
 * 
 * @return
 */
public CourseCategory getBackCategory() {
    return CourseCategory
            .find("sequence>? ORDER BY sequence ", this.sequence).first();
}

public List<Course> getCourses(boolean isVisable) {
    if (isVisable) {
        return Course.find("status=1 AND categoryId=?",
                this.id).fetch();
    } else {
        return Course.find("categoryId=?", this.id).fetch();
    }
}
  
  
  
  
  

}

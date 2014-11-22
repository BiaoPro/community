package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.jpa.GenericModel;
import play.libs.Codec;
import utils.PageBean;
import utils.StringUtils;
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
  public int audit;//0代表待审核，-1为审核不通过，1为审核通过,2为后台插入
  
  
  @Column(name="status")
  public int status;//0-不显示 1-显示
  
  
  public void Course(){
    this.id = Codec.UUID();
  }
  
  /**
   * 获取课堂类别
   * @return
   */
  public CourseCategory getCourseCategory() {
      return CourseCategory.find("id", this.categoryId).first();
  }
  
  public static void deleteById(String id){
     Course.delete("id", id);
    
  }
  
  /**
   * 获取链接列表，默认每一页5条信息
   * 
   * @param searchKey
   *            搜索关键字
   * @param curPage
   *            当前页
   * @return
   */
  public static List<Course> findCourses(String searchKey, int curPage) {
      if (StringUtils.isEmpty(searchKey)) {
          return Course.all().fetch(curPage, 5);
      } else {
          return Course.find("link_name like ?", "%" + searchKey + "%").fetch(
                  curPage, 5);
      }
  }

  /**
   * 获取pageBean
   * 
   * @param searchKey
   * @param curPage
   * @return
   */
  public static PageBean getPageBean(String searchKey, int curPage) {
      long total = 0;
      if (StringUtils.isEmpty(searchKey))
          total = Course.count();
      else
          total = Course.find("link_name like ?", "%" + searchKey + "%").fetch()
                  .size();
      return PageBean.getInstance(curPage, total, 5);
  }
  
  /**
   * 是否显示
   * 
   * @return
   */
  public boolean isShow() {
    return this.status == 1;
  }
  
  
  

}

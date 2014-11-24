package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.jpa.GenericModel;
import play.libs.Codec;
import utils.DateUtils;
import utils.PageBean;
import utils.StringUtils;


/**
 * 在线课程信息表
 * @author 吴泽标
 *
 */
@Entity
@Table(name="course_online")
public class CourseOnline  extends GenericModel{
  
  @Id
  public String id;
  
  @Column(name="category_id")
  public String categoryId;//课程类别
  
  @Column(name="title")
  public String title;//课程名称
  
  @Column(name="info")
  public String info;//课程概述
  
  @Column(name="photo")
  public String photo;//课程概述
  
  @Column(name="url")
  public String url;//课程视频链接地址
  
  @Column(name="author_id")
  public String authorId;//发布用户id
  
  
  @Column(name="audit_id")
  public String auditId;//审核人id
  
  @Column(name="audit")
  public int audit;//0代表待审核，-1为审核不通过，1为审核通过,2为后台插入
  
  
  @Column(name="status")
  public int status;//0-不显示 1-显示
  
  @Column(name="pub_time")
  public long pubTime;//发布时间
  
  
  public CourseOnline(){
    this.id = Codec.UUID();
    this.audit = 0;
    this.status = 1;
    this.pubTime = getSystemTime();
  }
  
  /**
   * 获取课堂类别
   * @return
   */
  public CourseCategory getCourseCategory() {
      return CourseCategory.find("id", this.categoryId).first();
  }
  
  /**
   * 获取课堂类别名称
   * @return
   */
  public String getCategoryName() {
      return getCourseCategory()==null?"":getCourseCategory().name;
  }
  
  /**
   * 获取作者名称
   * @return
   */
  public String getAuthorName() {
    
     User vo = User.find("id", this.authorId).first();
     if(vo == null) return "";
     else return vo.rname;
     
  }
  
  /**
   * 获取审核人名称
   * @return
   */
  public String getAuditName() {
    
     User vo = User.find("id", this.auditId).first();
     if(vo == null) return "";
     else return vo.rname;
     
  }
  
  public static void deleteById(String id){
     CourseOnline.delete("id", id);
    
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
  
  
  /**获取图片路径
   * @return
   */
  public String getPhoto() {
    
    if (this.photo != null && !"".equals(this.photo)) {
      return this.photo;
    }
    return "";
  }
  
  
  /**
   * 获取系统时间
   * 
   * @return long
   */
  private long getSystemTime() {
      Date time = new Date();
      return time.getTime();
  }
  
  /**
   * 获取转换后的发布时间
   * 
   * @return long
   */
  private String showPubTime() {
      return DateUtils.getDateTimeStr(pubTime);
  }

  
  
  public List<CourseOnline> getTopCourse(int size){
      return CourseOnline.find("").fetch(size);
  }
  
}

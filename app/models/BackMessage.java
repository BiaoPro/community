/**
 * 文件创建时间：2014年11月21日
 */
package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.jpa.GenericModel;
import play.libs.Codec;
import utils.DateUtils;

/**
 * @author biao
 *
 */
@Entity
@Table(name = "back_message")
public class BackMessage extends GenericModel {
  
  @Id
  @Column(name="id")
  public String id;
  @Column(name="message")
  public String message;
  @Column(name = "pub_time",columnDefinition="BIGINT(20) default 0")
  public long pubTime;//发布时间
  
  public BackMessage(){
      this.id=Codec.UUID();
      this.pubTime=getSystemTime();
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
}

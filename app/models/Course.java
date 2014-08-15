package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.jpa.GenericModel;
/**
 * 链接表
 * @author 吴泽标
 *
 */
@Entity
@Table(name="Course")
public class Course extends GenericModel{
  
  @Id
  public String id;

}

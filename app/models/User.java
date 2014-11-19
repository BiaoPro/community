package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import play.data.validation.Password;
import play.data.validation.Unique;
import play.db.jpa.GenericModel;
import play.libs.Codec;

@Entity
@Table(name = "user")
public class User extends GenericModel {
    @Id
    public String id;
   
    @Column(name = "type")
    public int type; //用户类型：3- 管理员、2-社工、1-普通用户

    @Column(name = "account")
    public String account;//账号
    
    @Password
    @Column(name = "password")
    public String password;//密码
    
    //用户状态，1为正常，2为冻结
    public int status;
    
    
    public String rname;// 真实姓名
    public String prc;// 身份证号
    @Column(name = "sex",columnDefinition="int default 1")
	public int sex;//性别 1-男 2-女
	public int age;//年龄
	public String introduce;//简介
	public String photo;//照片路径
    
    
    @OneToMany
    List<House> house;
    @OneToMany
    List<Work> work;
    @OneToMany
    List<News> news;
    
    public User(){
      this.id = Codec.UUID();
      this.status=1;
    }
    /**
     * 用户是否存在
     * @param account
     * @param password
     * @return
     */
    public static boolean isExist(String account, String password) {
      if(User.count("account=? AND password=?", account,password) > 0) {
          return true;
      }
      return false;
    }
	/**
	 * 用户是否存在
	 * @param account
	 * @param password
	 * @return
	 */
	public static boolean isExist(String account, String password,int type) {
		if(User.count("account=? AND password=? AND type=?", account,password,type) > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 用户是否管理员
	 * @return
	 */
	public boolean isAdmin() {
		return this.type==3;
	}
	/**
	 * 用户是否社工管理员
	 * @return
	 */
	public boolean isSocialAdmin(){
		return this.type==2;
	}
	/**
	 * 是否普通用户
	 * @return
	 */
	public boolean isUser(){
		return this.type==1;
	}
	/**
	 * 用户是否被冻结
	 * @return
	 */
	public boolean isFreeze(){
		return this.status==2;
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
   * 根据用户帐号返回User
   * @param account
   * @return
   */
  public static User findByAccount(String account) {
    return User.find("account = ?", account).first();
  }

  
  /**
   * 获得userInfo
   * 
   * @return userInfo
   */
  public UserInfo getUserInfo() {
      return UserInfo.find("userId", this.id).first();

  }

  /**
   * 根据帐号和密码查找用户
   * 
   * @param account
   *            帐号
   * @param password
   *            密码
   * @return user
   */
  public static User findUser(String account, String password) {
      User user = User.find("account=? AND password=?", account, password)
              .first();
      return user;
  }

}


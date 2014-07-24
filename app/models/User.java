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
    public int type;

    @Column(name = "account")
    public String account;
    
    @Password
    @Column(name = "password")
    public String password;
    
    //用户状态，1为正常，2为冻结
    public int status;
    
    
    public String rname;// 真实姓名
	public int sex;
	public int age;
	public String introduce;
	public String photo;
    
    
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
	/*
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
	
	/*
	 * 用户是否管理员
	 * @return
	 */
	public boolean isAdmin() {
		return this.type==3;
	}
	/*
	 * 用户是否社工管理员
	 * @return
	 */
	public boolean isSocialAdmin(){
		return this.type==2;
	}
	/*
	 * 是否普通用户
	 * @return
	 */
	public boolean isUser(){
		return this.type==1;
	}
	/*
	 * 用户是否被冻结
	 * @return
	 */
	public boolean isFreeze(){
		return this.status==2;
	}


}


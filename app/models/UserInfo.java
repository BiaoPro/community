package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.jpa.GenericModel;
import play.libs.Codec;
import play.mvc.Scope.Session;
import utils.DateUtils;
import utils.SessionManager;
import utils.enumvalue.Sex;
import utils.enumvalue.UserRoleEnum;

@Entity
@Table(name = "user_info")
public class UserInfo extends GenericModel {

	/**
	 * 用户状态
	 */
	public static final int LOGIN = 0; // 登录
	public static final int LOGOUT = 1; // 注销

	@Id
	public String id;

	@Column(name = "number")
	public String number;// 工号

	@Column(name = "user_name")
	public String name;

	@Column(name = "sex")
	public int sex;// 性别 0：女，1：男

	@Column(name = "birthday")
	public long birthday;

	@Column(name = "identity")
	public String identity;// 身份证号码

	@Column(name = "position")
	public String position;// 职务

	@Column(name = "cellphone")
	public String cellphone;// 移动电话

	@Column(name = "telphone")
	public String telphone;// 固话

	@Column(name = "email")
	public String email;// 邮箱

	@Column(name = "user_category")
	public int userCategory;// 用户类别0：普通用户，1：文章管理员，2：超级管理员

	@Column(name = "registration_time")
	public long registrationTime;// 注册时间

	@Column(name = "login_num")
	public long loginNum;// 登录总次数

	@Column(name = "last_login_time",columnDefinition="BIGINT(20) default 0")
	public long lastLoginTime;// 最后登录时间

	@Column(name = "total_login_time",columnDefinition="BIGINT(20) default 0")
	public long totalLoginTime;// 总共登录次数

	@Column(name = "user_id")
	public String userId;

	@Column(name = "title_id")
	public String titleId;

	@Column(name = "department_id")
	public String departmentId;

	@Column(name = "credit",columnDefinition="int default 0")
	public int credit;

	public UserInfo(String userId, String number) {
		// TODO 自动生成的构造函数存根
		this.id = Codec.UUID();
		this.userId = userId;
		this.number = number;
		this.registrationTime = getSystemTime();
		this.userCategory = UserRoleEnum.TEACHER.getValue();
		this.totalLoginTime = 0;
		this.lastLoginTime = 0;
		this.sex = Sex.MALE.getValue();
		this.credit = 0;
	}

	public UserInfo(String userId, String number, String userName) {
		// TODO 自动生成的构造函数存根
		this.id = Codec.UUID();
		this.userId = userId;
		this.number = number;
		this.name = userName;
		this.registrationTime = getSystemTime();
		this.userCategory = UserRoleEnum.TEACHER.getValue();
		this.totalLoginTime = 0;
		this.lastLoginTime = 0;
		this.sex = Sex.MALE.getValue();
	}

	/**
	 * 通过userId获得userInfo
	 * 
	 * @param id
	 * @return
	 */
	public static UserInfo getUserInfoByUserId(String userId) {
		return (UserInfo.find("userId=?", userId)).first();

	}

	/**
	 * 判断用户是否为管理员
	 * 
	 * @return
	 */
	public boolean isAdmin() {
		return UserRoleEnum.isAdmin(userCategory);
	}

	/**
	 * 判断用户是否为文章发布员
	 * 
	 * @return
	 */
	public boolean isPublisher() {
		return UserRoleEnum.isPublisher(userCategory);
	}

	/**
	 * 判断用户是否为教师用户
	 * 
	 * @return
	 */
	public boolean isTeacher() {
		return UserRoleEnum.isTeacher(userCategory);
	}

	/**
	 * 返回用户的职务
	 * 
	 * @return string
	 */
	public String showUserCategory() {
		return UserRoleEnum.getRoleName(this.userCategory);
	}

	/**
	 * 根据用户状态更新用户的信息
	 * 
	 * @param status
	 */
	public void updateUserInfo(int status) {
		if (status == LOGIN) {
			loginNum++;
			lastLoginTime = getSystemTime();
			save();
		} else if (status == LOGOUT) {
			totalLoginTime = totalLoginTime + getSystemTime() - lastLoginTime;
			save();
		}
	}

	/**
	 * 获取上次登录时间
	 * 
	 * @return string
	 */
	public String showLastLoginTime() {
		return DateUtils.getDateStr(lastLoginTime);
	}

	/**
	 * 获取总登录时间(分钟)
	 * 
	 * @return string
	 */
	public String showTotalLoginTime() {
		return (new Date(totalLoginTime)).getMinutes() + "分钟";
	}

	/**
	 * 获取注册时间
	 * 
	 * @return string
	 */
	public String showRegistrationTime() {
		return DateUtils.getDateStr(registrationTime);
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
	 * 获取出生年月日字符串
	 * 
	 * @return
	 */
	public String getBirthdayStr() {
		return DateUtils.getDateStr(this.birthday);
	}

}

package utils.enumvalue;

/**
 * 用户类型
 * 
 * @author leaf
 * 
 */
public enum UserRoleEnum {
	TEACHER(0, "教师用户"), PUBLISHER(1, "文章发布员"), ADMIN(2, "管理员");// 普通用户类型
	private int value;
	private String roleName;

	UserRoleEnum(int value, String roleName) {
		this.value = value;
		this.roleName = roleName;
	}
	
	/**
	 * 返回用户类型的名称
	 * @param value
	 * @return
	 */
	public static String getRoleName(int value) {
		UserRoleEnum[] types = values();
		if(value < types.length) {
			return types[value].getRoleName();
		} else {
			return null;
		}
	}
	
	/**
	 * 判断类型（管理员）
	 * @param value
	 * @return
	 */
	public static boolean isAdmin(int value) {
		if(value == ADMIN.getValue()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断类型（文章发布者）
	 * @param value
	 * @return
	 */
	public static boolean isPublisher(int value) {
		if(value == PUBLISHER.getValue()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断类型（老师）
	 * @param value
	 * @return
	 */
	public static boolean isTeacher(int value) {
		if(value == TEACHER.getValue()) {
			return true;
		}
		return false;
	}
	
	public int getValue() {
		return value;
	}
	
	public String getRoleName() {
		return roleName;
	}
}

package utils;

/**
 * 字符工具类
 * @author biao
 *
 */
public class StringUtils {
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if(str == null || str.trim().equals("")) {
			return true;
		}
		return false;
	}
}

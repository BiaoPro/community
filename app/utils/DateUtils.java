package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间格式化工具
 * 
 * @author 
 * 
 */
public class DateUtils {
	/**
	 * 获得格式化的时间格式化
	 * @param time
	 * @return
	 */
	public static String getDateStr(long time) {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date(time));
	}
	
	/**
	 * 获得格式化的时间格式化
	 * @param time
	 * @return
	 */
	public static String getDateTimeStr(long time) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(time));
	}
	
	/**
	 * 获取时间（String转long）
	 * @param dateStr
	 * @return
	 */
	public static long getTimeByDateStr(String dateStr) {
		Date date = getDateByDateStr(dateStr);
		return getTimeByDate(date);
	}
	
	/**
	 * 获取时间（Date转long）
	 * @param date
	 * @return
	 */
	public static long getTimeByDate(Date date) {
		if(date == null) {
			return 0;
		} else {
			return date.getTime();
		}
	}

	/**
	 * 通过字符串解析得到Date
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date getDateByDateStr(String dateStr) {
		try {
			SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			return SDF.parse(dateStr);
		} catch (ParseException e) {}
		try {
			SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
			return SDF.parse(dateStr);
		} catch (ParseException e) {}
		return null;
	}
	
	/**
     * 判断日期格式是否符合yyyy-MM-dd或者yyyy-MM-dd hh:mm
     *
     * @param dateStr
     * @return
     */
    public static boolean isDateStr(String dateStr) {
        if (dateStr==null||"".equals(dateStr)) {
            return false;
        }
        
        boolean isFormat = false;
        try {
        	new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        	isFormat = true;
        } catch (ParseException e) {}
        try {
        	new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(dateStr);
        	isFormat = true;
        } catch (ParseException e) {}
        return isFormat;
    }
}

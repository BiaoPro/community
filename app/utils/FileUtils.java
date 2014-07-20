package utils;

import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FileUtils {
	public static final int IMG_LIMIT_HEIGHT = 200;
	public static final int IMG_LIMIT_WIDTH = 200;

	/**
	 * 获取文件后缀名,包括点
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileExtension(String fileName) {
		String sufix = null;
		if (fileName != null) {
			String temp = fileName.trim();
			int dot = temp.lastIndexOf(".");
			if (dot >= 0 || dot > temp.length()) {
				sufix = temp.substring(dot + 1, temp.length());
			}
		}
		return sufix;
	}

	/**
	 * 获取文件名，出去后缀名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileNameWithoutExtension(String fileName) {
		String name = null;
		if (fileName != null) {
			String temp = fileName.trim();
			int dot = temp.lastIndexOf(".");
			if (dot >= 0) {
				name = temp.substring(0, dot);
			}
		}
		return name;
	}
	
	/**
	 * 获取图片名称
	 */
	public static List getImageFilesName(String realpath, List files) {

		File realFile = new File(realpath);
		if (realFile.isDirectory()) {
			File[] subfiles = realFile.listFiles();
			for (File file : subfiles) {
				if (file.isDirectory()) {
					getImageFilesName(file.getAbsolutePath(), files);
				} else {
					if (isImageType(file.getName())) {
						files.add(file.getName());
					}
				}
			}
		}
		return files;
	}
	
	/**
	 * 是否图片
	 * @param file
	 * @return
	 */
	public static boolean isImageType(File file) {
		if(file == null) {
			return false;
		}
		
		return isImageType(file.getName());
	}
	/**
	 * 是否图片
	 * @param fileName
	 * @return
	 */
	public static boolean isImageType(String fileName) {
		String[] fileType = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };
		Iterator<String> type = Arrays.asList(fileType).iterator();
		while (type.hasNext()) {
			String t = type.next();
			if (fileName.endsWith(t)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断是否视频
	 * @param fileName
	 * @return
	 */
	public static boolean isVedio(String fileName) {
		String[] fileType = { ".mp4", ".swf", ".flv", ".avi"};
		Iterator<String> type = Arrays.asList(fileType).iterator();
		while (type.hasNext()) {
			String t = type.next();
			if (fileName.endsWith(t)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断是否word文件
	 * @param fileName
	 * @return
	 */
	public static boolean isWord(String fileName) {
		String[] fileType = { ".doc", "docx"};
		Iterator<String> type = Arrays.asList(fileType).iterator();
		while (type.hasNext()) {
			String t = type.next();
			if (fileName.endsWith(t)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断是否ppt
	 * @param fileName
	 * @return
	 */
	public static boolean isPPT(String fileName) {
		String[] fileType = { ".ppt", "pptx"};
		Iterator<String> type = Arrays.asList(fileType).iterator();
		while (type.hasNext()) {
			String t = type.next();
			if (fileName.endsWith(t)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断是否pdf
	 * @param fileName
	 * @return
	 */
	public static boolean isPDF(String fileName) {
		String[] fileType = { ".pdf"};
		Iterator<String> type = Arrays.asList(fileType).iterator();
		while (type.hasNext()) {
			String t = type.next();
			if (fileName.endsWith(t)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 删除文件
	 * @param fileName
	 * @return
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if(!file.exists()) {
			return false;
		} else {
			file.delete();
			return true;
		}
	}
	
	/**
	 * 删除文件夹
	 * @param dirName
	 * @return
	 */
	public static void deleteDir(String dirName) {
		File dir = new File(dirName);
		if(dir.isDirectory()) {
			File[] files = dir.listFiles();
			for(File file : files) {
				if(file.isDirectory()) {
					deleteDir(file.getAbsolutePath());
				} else {
					deleteFile(file.getAbsolutePath());
				}
			}
		}
		dir.delete();
	}
}

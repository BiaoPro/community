package utils;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * @author kingda
 * @description:定义kindEditor的上传逻辑，依赖于uploader
 */
public class KindEditorUpload {

	private String dirName;//文件类别
	private String fileName;//文件名称
	private File file;//文件
	//定义允许上传的文件扩展名
	private HashMap<String, String> extMap = new HashMap<String, String>();
	
	public KindEditorUpload(String dirName,String fileName,File file){
		this.file = file;
		this.dirName=dirName;
		this.fileName=fileName;
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
	}
	
	public Map upload(){
		if(!checkExt()){
			return this.getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。");
		}
		Uploader uploader = new Uploader("../","public/images/upload/"+dirName+"/");
		uploader.upload(file);
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("error", 0);
		obj.put("url",uploader.getUrl());
		return obj;
		
	}
	/*
	 * @desription:是否允许该扩展名
	 */
	public boolean checkExt(){
		
		
		//检查扩展名
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("error", 1);
			map.put("message","上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。");
			return false;
		}
		return true;
	}
	
	private Map getError(String message) {
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("error", 1);
		obj.put("message", message);
		return obj;
	}
}

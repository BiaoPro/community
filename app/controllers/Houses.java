package controllers;

import play.*;
import play.mvc.*;
import utils.*;
import utils.enumvalue.*;

import java.io.File;
import java.util.*;
import models.*;
/*
 * 出租房信息
 * @author zhuangxiangpeng
 */
@With(UserSecures.class)
public class Houses extends Controller {
	public static void index(){
		render();
	}
	public static void addHouse(){
		render();
	}
    /*
     * 显示详细的租房信息
     * @param id
     */
    public static void showHouseInfo(String id){
    	House house=House.findById(id);
    	String[] photo = house.photoUrl.toString().split("\\.\\$\\.");
    	render(house,photo);
    }
    /*
     * 显示租房信息列表
     * 
     */
    public static void showHouses(){
    	int curPage=Integer.parseInt(params.get("page")==null?"1":params.get("page"));
    	PageBean pageBean=House.getPageBean("", curPage);
    	List<House> houseList=House.findHouses("",curPage);
    	render(houseList,pageBean);
    }
    /*
     * 保存租房信息
     * @param house
     * @param photeUrl
     */
    public static void saveHouse(House house,File[] photoUrl){
    	
        if (photoUrl != null) {
        	if (!StringUtils.isEmpty(house.photoUrl)) {//删除原来的图片
        		String[] a = house.photoUrl.split("\\.\\$\\.");
        		for(int i=0;i<a.length;i++)
                FileUtils.deleteFile(a[i]);
            }
          String insertUrl="";
          String baseUrl = Config.DEFAULT_BASE_URL;
          String savePath = Config.HOUSE_PHOTO_PATH;
          Uploader uploader = new Uploader(baseUrl, savePath);
          for(int i=0;i<photoUrl.length;i++){//循环上传图片
          uploader.upload(photoUrl[i]);
          if (uploader.getState() == FileUploadState.SUCCESS) { // 文件上传成功
            insertUrl+=uploader.getUrl()+".$.";//插入图片路径到house表中         
          } else {
              flash.error("上传失败");
              index();
          }
        }
          house.photoUrl = insertUrl;
        }
        house.author=SessionManager.getLoginedUser(session);
    	String[] eqs = params.getAll("equipment");//多选家电配备
    	String equipment="";
    	for(int i=0;i<eqs.length;i++){
    		equipment+=eqs[i]+",";
    	}
    	house.equipment=equipment;
          house.save();
          flash.error("上传成功");
          index();
    	}
    /*
     * 删除租房信息
     * @param id
     */
    public static void deleteHouse(String id){
    	House.delete("id", id);
    	showHouses();
    }
}

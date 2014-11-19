package controllers;

import play.*;
import play.mvc.*;
import utils.FileUtils;
import utils.PageBean;
import utils.StringUtils;
import utils.Uploader;
import utils.enumvalue.Config;
import utils.enumvalue.FileUploadState;

import java.io.File;
import java.util.*;
import models.*;
/*
 * 出租房信息
 * @author zhuangxiangpeng
 */
public class Houses extends Controller {
	public static void index(){
		render();
	}
    public static void rentHouses(){
    	render();
    }
    public static void showHousesInfo(){
    	
    	render();
    }
    public static void houses(){
    	int curPage=Integer.parseInt(params.get("page"));
    	PageBean pageBean=House.getPageBean("", curPage);
    	List<House> houseList=House.findHouses("",curPage);
    	render(houseList,pageBean);
    }
    /*
     * 增加租房信息
     * @param house
     * @param photeUrl
     */
    public static void addHouse(House house,File photoUrl){
    	
    	System.out.println(photoUrl.getAbsolutePath());
        if (photoUrl != null) {
        	
          if (!StringUtils.isEmpty(house.photoUrl)) {
              FileUtils.deleteFile(house.photoUrl);
          }
          
          String baseUrl = Config.DEFAULT_BASE_URL;
          String savePath = Config.HOUSE_PHOTO_PATH;
          Uploader uploader = new Uploader(baseUrl, savePath);
          System.out.println("savePath:"+savePath);
          uploader.upload(photoUrl);

          if (uploader.getState() == FileUploadState.SUCCESS) {
              // 文件上传成功
            house.photoUrl = uploader.getUrl();

          } else {
              flash.error("上传失败");
              render();
          }
        }
      //多选家电配备
    	String[] eqs = params.getAll("equipment");
    	String equipment="";
    	for(int i=0;i<eqs.length;i++){
    		equipment+=eqs[i]+",";
    	}
    	house.equipment=equipment;
          house.save();
          render(house);
    }

}

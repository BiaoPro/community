package controllers;

import play.*;
import play.mvc.*;
import utils.FileUtils;
import utils.PageBean;
import utils.StringUtils;
import utils.Uploader;

import java.util.*;
import models.*;
/*
 * 出租房信息
 * @author zhuangxiangpeng
 */
public class Houses extends Controller {
  
    public static void rentHouses(){
    	render();
    }
    public static void showHousesInfo(){
    	public static void saveResource(Resource resource, File file) {
    		if(resource.outLink == 0){
    			if (file != null) {
    				if (!StringUtils.isEmpty(resource.url)) {
    					FileUtils.deleteFile(resource.getFilepath());
    					FileUtils.deleteFile(resource.getPDFTempFilePath());
    					FileUtils.deleteDir(resource.getSWFTempFilePath());
    				}
    				String baseUrl = Config.DEFAULT_BASE_URL;
    				String savePath = Config.CURRICULUM_RESOURCES_PATH;
    				Uploader uploader = new Uploader(baseUrl, savePath);
    				uploader.upload(file);

    				if (uploader.getState() == FileUploadState.SUCCESS) {
    					// 文件上传成功
    					resource.url = uploader.getUrl();
    					
    					if(!FileUtils.isVedio(resource.url)) {
    						DOC2SWFUtil dp = new DOC2SWFUtil(resource.getFilepath(),
    								resource.getPDFTempFilePath(),
    								resource.getSWFTempFilePath());
    						dp.start();
    					}

    				} else {
    					flash.error("上传失败");
    					showCurriculumInfo(resource.curriculumId);
    				}
    			} else {
    				flash.error("上传失败");
    				showCurriculumInfo(resource.curriculumId);
    			}
    		}
    		else {
    			if (StringUtils.isEmpty(resource.name) ||StringUtils.isEmpty(resource.url) ||StringUtils.isEmpty(resource.type)) {
    				flash.error("上传失败");
    				showCurriculumInfo(resource.curriculumId);
    				
    			}
    			if(StringUtils.isEmpty(resource.description)) {
    				resource.description = null;
    			}
    		}
    		resource.save();
    		showCurriculumInfo(resource.curriculumId);
    	}
    	render();
    }
    public static void houses(){
    	int curPage=Integer.parseInt(params.get("page"));
    	PageBean pageBean=House.getPageBean("", curPage);
    	List<House> houseList=House.findHouses("",curPage);
    	render(houseList,pageBean);
    }
    public static void addHouse(){
    	House house=new House();
    	house.way = params.get("way");
    	house.indentity=params.get("indentity");
    	house.contacts=params.get("contacts");
    	house.phone=params.get("phone");
    	house.community=params.get("community");   	
    	house.address=params.get("address");
    	house.bedRoom=Integer.parseInt(params.get("bedRoom"));
    	house.livingRoom=Integer.parseInt(params.get("livingRoom"));
    	house.bathRoom=Integer.parseInt(params.get("bathRoom"));
    	house.square=Double.parseDouble(params.get("square"));
    	house.currentFloor=Integer.parseInt(params.get("currentFloor"));
    	house.countFloor=Integer.parseInt(params.get("countFloor"));
    	house.decorationSituation=params.get("decorationSituation");
    	house.classSituation=params.get("classSituation");
    	//多选家电配备
    	String[] eqs = params.getAll("equipment");
    	String equipment="";
    	for(int i=0;i<eqs.length;i++){
    		equipment+=eqs[i]+",";
    	}
    	house.equipment=equipment;
    	house.price=Double.parseDouble(params.get("price"));
    	house.details=params.get("details");
    	house.photoUrl=params.get("photoUrl");
    	house.save();
    	render(house);
    }

}

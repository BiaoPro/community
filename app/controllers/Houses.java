package controllers;

import play.*;
import play.mvc.*;
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
    	render();
    }
    public static void houses(){
    	int id=Integer.parseInt(params.get("id"));
    	List<House> houseList=House.findHouses("",id);
    	render(houseList);
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

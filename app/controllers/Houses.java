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

  
    public static void index() {
      render();
    }

	public static void test(){
		List<House> h=House.findHouses(null, 1);
		render(h);
	}
	//增加房子信息
	public static void addHouse(){
		render();
	}
	//保存房子信息
	public static void saveHouse(House house){
		house.save();
		showAllHouse();
	}
	//删除房子信息
	public static void delHouse(String id){
		House house=House.findById(id);
		house.delete();
		test();
	}
	//显示房子信息
	public static void showAllHouse(){
		List<House> h=House.findHouses(null, 1);
		render(h);
	}
	//修改房子信息
	public static void updateHouse(){
		
	}

}

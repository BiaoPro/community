package controllers;

import java.util.List;

import models.House;
import play.mvc.Controller;
import utils.PageBean;

public class BackHouses extends Controller {
	/*
	 * 租房信息审核
	 */
	public static void housesVerify(){
		int curPage=Integer.parseInt(params.get("page")==null?"1":params.get("page"));
		int total = House.find("audit", 1).fetch().size();//总数
		PageBean.getInstance(curPage, total, 5);
	
    	List<House> houseList=House.find("audit", 1).fetch(curPage, 5);//list对象
    	render(houseList);
	}
	/*
	 * 通过审核、
	 * @param id
	 */
	public static void housesVerifySuccess(String id){
		House house=House.findById(id);
		house.audit=3;
		house.save();
		housesVerify();
	}
	/*
	 * 不通过审核、
	 * @param id
	 */
	public static void housesVerifyFailure(String id){
		House house=House.findById(id);
		house.audit=2;
		house.save();
		housesVerify();
	}
	public static void housesManager(){
		render();
	}
	public static void housesHistory(){
		render();
	}
}

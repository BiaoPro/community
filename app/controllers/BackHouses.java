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
    	PageBean pageBean=House.getPageBean("audit","1",curPage);
    	List<House> houseList=House.findHouses("audit","1",curPage);
    	render(houseList,pageBean);
	}
	public static void housesManager(){
		render();
	}
	public static void housesHistory(){
		render();
	}
}

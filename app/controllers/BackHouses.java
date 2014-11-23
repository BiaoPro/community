package controllers;

import java.util.List;

import models.House;
import play.mvc.Controller;
import utils.PageBean;
import utils.SessionManager;

public class BackHouses extends Controller {
	/*
	 * 租房信息审核
	 */
	public static void housesVerify(){
		int curPage=Integer.parseInt(params.get("curPage")==null?"1":params.get("curPage"));
		int total = House.find("audit", 1).fetch().size();//总数
		PageBean pageBean=PageBean.getInstance(curPage, total, 5);
	
    	List<House> houseList=House.find("audit", 1).fetch(curPage, 5);//list对象
    	render(houseList,pageBean);
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
	/*
	 * 删除租房信息
	 * @param id
	 */
	public static void housesDelete(String id){
		House house=House.findById(id);
		house.delete();
		housesManager();
	}
	public static void housesHistoryDelete(String id){
		House house=House.findById(id);
		house.delete();
		housesHistory();
	}
	/*
	 * 展示审核的详细信息
	 * @param id
	 */
	public static void showHouseInfo(String id){
		House house=House.findById(id);
    	String[] photo = house.photoUrl.toString().split("\\.\\$\\.");
    	render(house,photo);
	}
	/*
	 * 修改租房信息
	 * @param id
	 */
	public static void editHouseInfo(String id){
		House house=House.findById(id);
    	String[] photo = house.photoUrl.toString().split("\\.\\$\\.");
    	render(house,photo);
	}
	/*
	 * 管理租房信息
	 */
	public static void housesManager(){
		int curPage=Integer.parseInt(params.get("curPage")==null?"1":params.get("curPage"));
		int total = House.all().fetch().size();//总数
		PageBean pageBean=PageBean.getInstance(curPage, total, 5);
	
    	List<House> houseList=House.all().fetch(curPage, 5);;//list对象
    	render(houseList,pageBean);
	}
	/*
	 * 修改显示状态
	 * @param id
	 * @param status
	 */
	public static void changeStatus(String id,int status){
		House house=House.findById(id);
		house.status=status;
		house.save();
		housesManager();
	}
	public static void changeHistoryStatus(String id,int status){
		House house=House.findById(id);
		house.status=status;
		house.save();
		housesHistory();
	}
	/*
	 * 历史发布信息
	 * 
	 */
	public static void housesHistory(){
		int curPage=Integer.parseInt(params.get("curPage")==null?"1":params.get("curPage"));
		String author=SessionManager.getLoginedId(session);
		List<House> houseList=House.find("author_id", author).fetch(curPage,5);
		int total = House.find("author_id", author).fetch().size();//总数
		PageBean pageBean=PageBean.getInstance(curPage, total, 5);
		render(houseList,pageBean);
	}
}

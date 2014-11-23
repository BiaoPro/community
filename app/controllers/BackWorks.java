package controllers;

import java.util.List;

import models.Work;
import play.mvc.Controller;
import utils.PageBean;
import utils.SessionManager;

public class BackWorks extends Controller {
	/*
	 * 租房信息审核
	 */
	public static void worksVerify(){
		int curPage=Integer.parseInt(params.get("curPage")==null?"1":params.get("curPage"));
		int total = Work.find("audit", 1).fetch().size();//总数
		PageBean pageBean=PageBean.getInstance(curPage, total, 5);
	
    	List<Work> workList=Work.find("audit", 1).fetch(curPage, 5);//list对象
    	render(workList,pageBean);
	}
	/*
	 * 通过审核、
	 * @param id
	 */
	public static void worksVerifySuccess(String id){
		Work work=Work.findById(id);
		work.audit=3;
		work.save();
		worksVerify();
	}
	/*
	 * 不通过审核、
	 * @param id
	 */
	public static void worksVerifyFailure(String id){
		Work work=Work.findById(id);
		work.audit=2;
		work.save();
		worksVerify();
	}
	/*
	 * 删除租房信息
	 * @param id
	 */
	public static void worksDelete(String id){
		Work work=Work.findById(id);
		work.delete();
		worksManager();
	}
	public static void worksHistoryDelete(String id){
		Work work=Work.findById(id);
		work.delete();
		worksHistory();
	}
	/*
	 * 展示审核的详细信息
	 * @param id
	 */
	public static void showWorkInfo(String id){
		Work work=Work.findById(id);
    	render(work);
	}
	/*
	 * 修改租房信息
	 * @param id
	 */
	public static void editWorkInfo(String id){
		/*Work work=Work.findById(id);
    	render(work);*/
		render();
	}
	/*
	 * 管理租房信息
	 */
	public static void worksManager(){
		int curPage=Integer.parseInt(params.get("curPage")==null?"1":params.get("curPage"));
		int total = Work.all().fetch().size();//总数
		PageBean pageBean=PageBean.getInstance(curPage, total, 5);
	
    	List<Work> workList=Work.all().fetch(curPage, 5);;//list对象
    	render(workList,pageBean);
	}
	/*
	 * 修改显示状态
	 * @param id
	 * @param status
	 */
	public static void changeStatus(String id,int status){
		Work work=Work.findById(id);
		work.status=status;
		work.save();
		worksManager();
	}
	public static void changeHistoryStatus(String id,int status){
		Work work=Work.findById(id);
		work.status=status;
		work.save();
		worksHistory();
	}
	/*
	 * 历史发布信息
	 * 
	 */
	public static void worksHistory(){
		int curPage=Integer.parseInt(params.get("curPage")==null?"1":params.get("curPage"));
		String author=SessionManager.getLoginedId(session);
		List<Work> workList=Work.find("author_id", author).fetch(curPage,5);
		int total = Work.find("author_id", author).fetch().size();//总数
		PageBean pageBean=PageBean.getInstance(curPage, total, 5);
		render(workList,pageBean);
	}
}

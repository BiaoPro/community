package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

public class ShowHouses extends Controller{
	// 展示租房首页
  	public static void Houses(){
		render();
	}
	// 展示单个租房信息页
	public static void showHousesInfo(){
		render();
	}
}

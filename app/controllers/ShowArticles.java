package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

public class ShowArticles extends Controller{
	// 展示新闻主页
	public static void News(){
		render();
	}
	// 展示单个新闻
	public static void showNewsInfo(){
		render();
	}
}

package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        render();
    }
    
    public static void test() {
      
      String str="hello";
      render(str);
  }

  	public static void news(){
		render();
	}

}
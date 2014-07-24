package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

public class Houses extends Controller {
	public static void test(){
		List<House> h=House.findHouses(null, 1);
		render(h);
	}
}

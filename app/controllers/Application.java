package controllers;

import play.*;
import play.mvc.*;
import utils.SessionManager;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
      SessionManager.setFooter(session);

        render();
    }
    
    
    public static void manager() {
        SessionManager.setFooter(session);

        Users.index();
    }
    
   


}
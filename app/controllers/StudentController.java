package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class StudentController extends Controller{
	
	public Result homePage(){
		return ok("Home page");
	}

}

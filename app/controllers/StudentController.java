package controllers;

import play.Logger;
import play.filters.csrf.AddCSRFToken;
import play.filters.csrf.RequireCSRFCheck;
import play.mvc.Controller;
import play.mvc.Result;

public class StudentController extends Controller{
	
	public Result homePage(){
		return redirect(routes.StudentController.signUpPage());
	}
	
	@AddCSRFToken
	public Result signUpPage(){
		//return ok(views.html.student.student_signup.render());
		return ok(views.html.student.publicdashboard.render());
	}
	
	@RequireCSRFCheck
	public Result processSignUp(){
		Logger.info("Request cookies"+request().hasHeader("PLAY_SESSION"));
		Logger.info("Request header detail"+request()._underlyingHeader());
		return ok("sign up completed");
	}
}

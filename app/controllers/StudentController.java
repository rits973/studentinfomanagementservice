package controllers;

import java.util.Optional;

import javax.inject.Inject;

import bean.student.StudentSignupBean;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.filters.csrf.AddCSRFToken;
import play.filters.csrf.CSRF;
import play.filters.csrf.RequireCSRFCheck;
import play.mvc.Controller;
import play.mvc.Result;

public class StudentController extends Controller{
	@Inject
	FormFactory formFactory;

	public Result homePage(){
		return ok(views.html.student.publicdashboard.render());
	}

	@AddCSRFToken
	public Result signUpPage(){

		return ok(views.html.student.student_signup.render());

	}

	//@RequireCSRFCheck
	public Result processSignUp(){
		final Optional<CSRF.Token> token = CSRF.getToken(request());
		//Logger.info("Request cookies"+request().hasHeader("PLAY_SESSION"));
		final Form<StudentSignupBean> filledForm = this.formFactory.form(StudentSignupBean.class).bindFromRequest();
		Logger.info("Request header detail"+filledForm);
		/*
		final AppUser appUser = new AppUser();
		appUser.firstName = bean.firstName;
		appUser.email = bean.email.trim().toLowerCase();
		//appUser.password = BCrypt.hashpw(bean.password, BCrypt.gensalt());
		appUser.role = Role.STUDENT;
		appUser.phoneNumber=bean.phoneNumber.trim();
		appUser.save();*/
		return ok("sign up completed");
	}

	@AddCSRFToken
	public Result getLoginPage(){
		return ok(views.html.student.student_login.render());
	}

	@RequireCSRFCheck
	public Result processLogin(){
		return ok(views.html.student.student_login.render());
	}
}

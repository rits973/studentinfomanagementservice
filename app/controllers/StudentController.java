package controllers;

import javax.inject.Inject;

import org.mindrot.jbcrypt.BCrypt;

import bean.student.StudentSignupBean;
import models.AppUser;
import models.Role;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.filters.csrf.AddCSRFToken;
import play.filters.csrf.RequireCSRFCheck;
import play.mvc.Controller;
import play.mvc.Result;
import util.Constants;

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

	@RequireCSRFCheck
	public Result processSignUp(){
		final Form<StudentSignupBean> filledForm = this.formFactory.form(StudentSignupBean.class).bindFromRequest();

		final StudentSignupBean bean= filledForm.get();
		final AppUser appUser = new AppUser();
		appUser.firstName = bean.firstName;
		appUser.lastName=bean.lastName;
		appUser.fullName=bean.firstName+" "+bean.lastName;
		appUser.email = bean.email.trim().toLowerCase();
		appUser.password = BCrypt.hashpw(bean.password, BCrypt.gensalt());
		appUser.role = Role.STUDENT;
		appUser.phoneNumber=bean.phoneNumber.trim();
		appUser.save();
		return ok("sign up completed");
	}

	@AddCSRFToken
	public Result getLoginPage(){
		return ok(views.html.student.student_login.render());
	}

	@RequireCSRFCheck
	public Result processLogin(){
		final Form<StudentSignupBean> filledForm = this.formFactory.form(StudentSignupBean.class).bindFromRequest();
		final StudentSignupBean bean= filledForm.get();
		final String email = bean.getLoginEmail().trim().toLowerCase();
		if (!email.isEmpty()) {
			final AppUser user = AppUser.find.where().eq("email", email).findUnique();
			session().clear();
			if(user != null) {
				if(bean.matchPassword(user.password)) {
					if(user.role.equals(Role.STUDENT)){
						Logger.info("-----"+email);
						session(Constants.AUTHENTICATED_USER_ID, user.id + "");
						session(Constants.AUTHENTICATED_USER_ROLE, user.role + "");
						return redirect(routes.StudentController.getStudentDashBoard());
					}
				}else{
					return redirect(routes.StudentController.getLoginPage());
				}
			}
			else{
				return redirect(routes.StudentController.getLoginPage());
			}
		}

		return ok(views.html.student.student_login.render());
	}

	public Result getStudentDashBoard(){
		return ok("You are Logged in student");
	}
	public Result processLogout(){
		session().clear();
		return ok("You are Logged out Successfully");
	}
}

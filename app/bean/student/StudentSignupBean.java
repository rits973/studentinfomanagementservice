package bean.student;

import java.io.Serializable;

import org.mindrot.jbcrypt.BCrypt;

public class StudentSignupBean implements Serializable{


	private static final long serialVersionUID = 1L;

	public String firstName;

	public String lastName;

	public String fullName;

	public String phoneNumber;

	public String email;

	public String password;




	public String getLoginEmail() {
		return this.email;
	}
	public String getPassword() {
		return this.password;
	}
	public Boolean matchPassword(final String uPassword) {
		if (BCrypt.checkpw(this.getPassword(), uPassword)) {
			return true;
		} else {
			return false;
		}
	}


}

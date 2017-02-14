package models;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.avaje.ebean.Model;

import play.data.validation.Constraints.Email;

@Entity
public class AppUser extends BaseEntity {

	public String firstName;

	public String lastName;

	public String fullName;

	public String phoneNumber;

	@Email
	@Column(unique = true)
	public String email;

	@Column(columnDefinition = "TEXT")
	public String password;

	public Role role;

	public static Model.Finder<Long, AppUser> find = new Model.Finder<Long, AppUser>(AppUser.class);


}

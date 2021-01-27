package com.nw.spring.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserForm {

	@NotNull
	@Pattern(regexp = "(\\S){2,}.*", message = "Full name must contains at least two characters.")
	private String fullName;
	
	@NotNull
	@Email(message = "Invalid email address.")
	private String email;
	
	@NotNull
	@Pattern(regexp = "(\\S){6,20}", message = "Password must have 6-20 now-whitespace characters")
	private String password;
	
	@NotNull(message = "Password do not match.")
	private String verifyPassword;
	
	public UserForm() {
		// TODO Auto-generated constructor stub
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		checkPasswordForRegistration();
	}

	public String getVerifyPassword() {
		return verifyPassword;
	}

	public void setVerifyPassword(String verifyPassword) {
		this.verifyPassword = verifyPassword;
		checkPasswordForRegistration();
	}
	
	protected void checkPasswordForRegistration() {
		if (!getPassword().equals(verifyPassword)) {
			verifyPassword = null;
		}
	}
}

package com.nw.spring.models;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

@Entity
public class User extends AbstractEntity {

	@Column(name = "full_name")
	// @NotBlank(message = "Full name may not be blank.")
	private String fullName;

	@NotBlank
	private String email;
	
	@NotBlank
	private String password;

	@Transient
	private Boolean enabled = true;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(@NotBlank String fullName, @NotBlank String email, @NotBlank String password) {
		if (fullName == null || fullName.length() == 0) {
			throw new IllegalArgumentException("Full name may not be blank.");
		}
		if (email == null || email.length() == 0 || !isValidEmail(email)) {
			throw new IllegalArgumentException("Email may not be blank.");
		}
		if (password == null || password.length() == 0) {
			throw new IllegalArgumentException("Password may not be blank.");
		}
		this.fullName = fullName;
		this.email = email;
		this.password = password;
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
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<String> getRoles() {
		ArrayList<String> roles = new ArrayList<>();
		roles.add("ROLE_USER");
		return roles;
	}

	private static boolean isValidEmail(String email) {
		Pattern pattern = Pattern.compile("\\S+@\\S+");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}

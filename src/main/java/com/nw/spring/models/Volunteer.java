package com.nw.spring.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Volunteer extends AbstractEntity {
	
	@Column(name = "first_name")
	@Size(min = 3, message = "Volunteer first name must be at leat 3 characters.")
	private String firstName;
	
	@Column(name = "last_name")
	@Size(min = 3, message = "Volunteer first name must be at leat 3 characters.")
	private String lastName;
	
	@NotBlank(message = "Please fill your education and certificate.")
	private String education;
	
	@Column(name = "phone_number")
	@NotBlank(message = "Please fill your phone number.")
	private String phoneNumber;
	
	@ManyToMany(mappedBy = "volunteers")
	private List<Event> events;
	
	public Volunteer() {
		// TODO Auto-generated constructor stub
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}
}

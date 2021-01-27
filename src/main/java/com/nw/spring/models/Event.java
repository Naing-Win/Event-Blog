package com.nw.spring.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Event extends AbstractEntity {

	@NotBlank(message = "Please fill event title.")
	private String title;

	@NotBlank(message = "Please fill location.")
	private String location;
	private String description;

	@Column(name = "start_date")
	// @NotBlank(message = "Please fill start date.")
	//@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime startDate;

	@ManyToMany
	@JoinTable(name = "event_volunteer", joinColumns = @JoinColumn(name = "event_uid"), inverseJoinColumns = @JoinColumn(name = "volunteer_uid"))
	private List<Volunteer> volunteers = new ArrayList<Volunteer>();

	public Event() {
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public List<Volunteer> getVolunteers() {
		return volunteers;
	}

	public void setVolunteers(List<Volunteer> volunteers) {
		this.volunteers = volunteers;
	}

	public void addVolunteer(Volunteer vol) {
		volunteers.add(vol);
	}

	public void addAllVolunteers(List<Volunteer> vols) {
		volunteers.addAll(vols);
	}

	public String getVolunteersFormatted() {
		List<String> nameList = getVolunteers().stream().map(v -> v.getFullName()).collect(Collectors.toList());
		return String.join(", ", nameList);
	}
}

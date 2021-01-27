package com.nw.spring.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nw.spring.models.Event;
import com.nw.spring.repos.EventRepository;
import com.nw.spring.repos.VolunteerRepository;

@Controller
@RequestMapping(value = {"/", "/events"})
public class EventController extends AbstractBaseController {
	
	@Autowired
	private VolunteerRepository volunteerRepository;
	@Autowired
	private EventRepository eventRepository;
	
	@ModelAttribute
	public void volunteersAttribute(Model model) {
		model.addAttribute("volunteers", volunteerRepository.findAll());
	}
	
	@GetMapping
	public String listEvents(Model model) {
		List<Event> events = eventRepository.findAll();
		model.addAttribute("events", events);
		return "event/list";
	}
	
	@GetMapping("/create")
	public String displayCreateEventForm(Model model) {
		model.addAttribute(new Event());
		model.addAttribute("title", "Create Event");
		//model.addAttribute("volunteers", volunteerRepository.findAll());
		return "event/create_update";
	}
	
	@PostMapping("/create")
	public String processCreateEventForm(@Valid Event event, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return "event/create_update";
		}
		eventRepository.save(event);
		attributes.addFlashAttribute(MESSAGE_KEY, "success|Created event: " + event.getTitle());
		return "redirect:/";
	}
	
	@GetMapping("/detail/{uid}")
	public String displayEventDetail(@PathVariable int uid, Model model) {
		model.addAttribute("title", "Event Detail");
		Event event = eventRepository.findById(uid).orElseThrow(() -> new IllegalArgumentException("Not found"));
		model.addAttribute("event", event);
		return "event/detail";
	}
	
	@GetMapping("/update/{uid}")
	public String editEvent(@PathVariable int uid, Model model) {
		model.addAttribute("title", "Update Event");
		Event event = eventRepository.findById(uid).orElseThrow(() -> new IllegalArgumentException("Not found"));
		model.addAttribute("event", event);
		return "event/create_update";
	}
	
	@PostMapping("/update/{uid}")
	public String processUpdateEvent(@PathVariable int uid, @Valid Event event, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return "event/create_update";
		}
		Event e = eventRepository.findById(uid).orElseThrow(() -> new IllegalArgumentException("Not found."));
		e.setUid(event.getUid());
		e.setVolunteers(event.getVolunteers());
		e.setTitle(event.getTitle());
		e.setDescription(event.getDescription());
		e.setLocation(event.getLocation());
		e.setStartDate(event.getStartDate());
		eventRepository.save(e);
		attributes.addFlashAttribute(MESSAGE_KEY, "success|Event updated " + event.getTitle());
		return "redirect:/event/detail/" + event.getUid();
	}
	
	@PostMapping("/delete/{uid}")
	public String deleteEvent(@PathVariable int uid, RedirectAttributes attributes) {
		Event event = eventRepository.findById(uid).orElseThrow(() -> new IllegalArgumentException("Not found: "));
		if (event != null) {
			eventRepository.delete(event);
			attributes.addFlashAttribute(MESSAGE_KEY, "success|Event deleted: " + uid);
			return "redirect:/";
		} else {
			attributes.addFlashAttribute(MESSAGE_KEY, "warning|Event does not exit: " + uid);
			return "redirect:/";
		}
	}
}

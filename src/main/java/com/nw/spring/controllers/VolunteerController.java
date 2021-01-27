package com.nw.spring.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nw.spring.models.Volunteer;
import com.nw.spring.repos.VolunteerRepository;

@Controller
@RequestMapping(value = {"volunteers"})
public class VolunteerController extends AbstractBaseController {

	@Autowired
	private VolunteerRepository volunteerRepository;
	
	@GetMapping
	public String listVolunteers(Model model) {
		model.addAttribute("title", "Volunteers");
		model.addAttribute("volunteers", volunteerRepository.findAll());
		return "volunteer/list";
	}
	
	@GetMapping("/create")
	public String displayCreateVolunteerForm(Model model, HttpServletRequest req) {
		model.addAttribute("title", "Create Volunteer");
		model.addAttribute(new Volunteer());
		model.addAttribute("actionUrl", req.getRequestURI());
		return "volunteer/create_update";
	}

	@PostMapping("/create")
	public String processVolunteerForm(@Valid @ModelAttribute Volunteer volunteer, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return "volunteer/create_update";
		}
		volunteerRepository.save(volunteer);
		attributes.addFlashAttribute(MESSAGE_KEY, "success|New volunteer added: " + volunteer.getFullName());
		return "redirect:/volunteers";
	}
}

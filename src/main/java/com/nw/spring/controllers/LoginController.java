package com.nw.spring.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nw.spring.form.UserForm;
import com.nw.spring.models.User;
import com.nw.spring.services.UserService;

@Controller
public class LoginController extends AbstractBaseController {

	@Autowired
	private UserService userService;
	
	@ModelAttribute("user")
	public User getLoggedInUser(Principal principal) {
		if (principal != null) {
			return userService.findByEmail(principal.getName());
		}
		return null;
	}
	
	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute(new UserForm());
		model.addAttribute("title", "Register");
		return "register";
	}
	
	@PostMapping("/register")
	public String processRegister(@ModelAttribute @Valid UserForm userForm, Errors errors) {
		if (errors.hasErrors()) {
			return "register";
		}
		try {
			userService.save(userForm);
		} catch (Exception e) {
			// TODO: handle exception
			errors.rejectValue("email", "email.alreadyexists", e.getMessage());
			return "register";
		}
		return "redirect:/";
	}
	
	@GetMapping(value = "/login")
    public String processLogin(Model model, Principal user, String error, String logout) {

        if (user != null)
            return "redirect:/";

        if (error != null)
            model.addAttribute(MESSAGE_KEY, "danger|Username and password are invalid");

        if (logout != null)
            model.addAttribute(MESSAGE_KEY, "info|You have been logged out");

        return "login";
    }
}

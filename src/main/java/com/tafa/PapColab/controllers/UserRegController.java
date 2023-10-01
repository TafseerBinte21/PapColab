package com.tafa.PapColab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tafa.PapColab.controllers.dto.UserRegDto;

import com.tafa.PapColab.services.UserServiceImpl;

@Controller
@RequestMapping("/register")
public class UserRegController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	// public UserRegController(UserService userService) {
	// super();
	// this.userService = userService;
	// }

	@ModelAttribute("user")
	public UserRegDto userRegistrationDto() {
		return new UserRegDto();
	}

	@GetMapping
	public String showRegistrationForm() {
		return "register";
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegDto registrationDto) {
		// System.out.println(registrationDto+"tafa");
		userServiceImpl.save(registrationDto);
		return "redirect:/register?success";
	}

}
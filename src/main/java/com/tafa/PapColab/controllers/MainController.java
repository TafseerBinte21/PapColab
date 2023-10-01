package com.tafa.PapColab.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tafa.PapColab.models.Post;
import com.tafa.PapColab.models.User;
import com.tafa.PapColab.repository.UserRepository;
import com.tafa.PapColab.services.PostServiceImpl;
import com.tafa.PapColab.services.UserServiceImpl;

@Controller
public class MainController {
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserServiceImpl userServiceImpl;

	@Autowired
	PostServiceImpl postServiceImpl;

	@GetMapping("/")
	public String home() {
		return "main";
	}

	@GetMapping("/user/login")
	public String login() {
		return "login";
	}

	@GetMapping("/admin/login")
	public String adminlogin() {
		return "adminlogin";
	}

	@GetMapping("/user/feed")
	public String feed(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByEmail(auth.getName());
		model.addAttribute("user", user);
		List<Post> posts = postServiceImpl.getPostsByUserId(user.getId());
		posts.addAll(postServiceImpl.getFeed(user));
		for (Long collabId : user.getCollaboratorIds()) {
			posts.addAll(postServiceImpl.getPostsByUserId(collabId));
		}
		model.addAttribute("posts", posts);
		return "feed";
	}

	@GetMapping("/adminfeed")
	public String adminfeed() {
		return "adminfeed";
	}

	@GetMapping("/user/post")
	public String post() {
		return "post";
	}

	
	@GetMapping("/stat")
	public String stat() {
		return "stat";
	}

}
package com.tafa.PapColab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.tafa.PapColab.controllers.dto.CommentDto;
import com.tafa.PapColab.models.Comment;
import com.tafa.PapColab.models.Post;
import com.tafa.PapColab.models.User;
import com.tafa.PapColab.repository.CommentRepository;
import com.tafa.PapColab.repository.UserRepository;
import com.tafa.PapColab.services.PostServiceImpl;
import com.tafa.PapColab.services.UserServiceImpl;

@Controller
public class PostController {
	@Autowired
	private PostServiceImpl postServiceImpl;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CommentRepository commentRepository;

	@GetMapping("/user/post/{id}")
	public String viewPost(@PathVariable Long id, Model model) {
		Long postId = Long.valueOf(id);
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userRepository.findByEmail(auth.getName());
			model.addAttribute("user", user);
			Post post = postServiceImpl.findPostById(postId);
			model.addAttribute("post", post);
			model.addAttribute("comments", commentRepository.findByPostId(postId));
		} catch (Exception e) {
			System.out.println("Post not found by id.");
			e.printStackTrace();
		}
		return "post";
	}

	@PostMapping("/user/post/{id}")
	public String addComment(@PathVariable Long id, @ModelAttribute("postDto") CommentDto commentDto, Model model) {
		Long postId = Long.valueOf(id);
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userRepository.findByEmail(auth.getName());
			Post post = postServiceImpl.findPostById(postId);
			commentDto.setPost(post);
			commentDto.setUser(user);
			Comment comment = new Comment(commentDto.getComment(), commentDto.getPost(), commentDto.getUser());
			commentRepository.save(comment);
		} catch (Exception e) {
			System.out.println("Post not found by id.");
			e.printStackTrace();
		}
		return "redirect:/user/post/" + id;
	}

	@PostMapping("/user/post/statistics/{postId}")
	public String getStatistics(@PathVariable Long postId, Model model) {
		Long id = Long.valueOf(postId);
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userRepository.findByEmail(auth.getName());
			Post post = postServiceImpl.findPostById(id);
		} catch (Exception e) {
			System.out.println("Post not found by id.");
			e.printStackTrace();
		}
		return "redirect:/user/post/" + id;
	}

	@GetMapping("/user/post/delete/{id}")
	public String deletePost(@PathVariable Long id) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByEmail(auth.getName());
		Post post = postServiceImpl.findPostById(id);
		if (post != null) {
			if (post.getUser().getId() == user.getId()) {
				postServiceImpl.deletePost(id);
			}
		}
		// Redirect to a success page or another appropriate page
		return "redirect:/user/profile/" + user.getId(); // Redirect to the user's feed, for example
	}

}

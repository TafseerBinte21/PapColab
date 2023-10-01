package com.tafa.PapColab.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tafa.PapColab.models.Post;
import com.tafa.PapColab.models.User;
import com.tafa.PapColab.repository.PostRepository;
import com.tafa.PapColab.repository.UserRepository;

@Controller
public class AdminController {
	


   @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;


    @GetMapping("/admin/userlist")
    public String listUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "userlist";
    }

    @GetMapping("/admin/postlist")
    public String listPosts(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        
        model.addAttribute("totalPosts", posts.size()); // Total number of posts
        return "postlist"; // Return the name of the HTML template for admin post list
    }

     @PostMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        // Check if the user exists
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            // Delete the user
            userRepository.delete(user);
        }

        // Redirect back to the user list after deletion
        return "redirect:/admin/userlist";
    }

    
}

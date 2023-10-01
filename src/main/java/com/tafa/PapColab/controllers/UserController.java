package com.tafa.PapColab.controllers;

import com.tafa.PapColab.controllers.dto.PostDto;
import com.tafa.PapColab.models.Post;
import com.tafa.PapColab.models.User;
import com.tafa.PapColab.repository.UserRepository;
import com.tafa.PapColab.services.PostServiceImpl;
import com.tafa.PapColab.services.UserServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private PostServiceImpl postServiceImpl;

    @GetMapping("/user/profile")
    public String profile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        User user = userServiceImpl.getUserByEmail(username);
        model.addAttribute("user", user);
        model.addAttribute("researcher", user);
        List<Post> posts = postServiceImpl.getPostsByUserId(user.getId());
        model.addAttribute("posts", posts);
        model.addAttribute("sentRequestIds", user.getSentRequestIds());
        model.addAttribute("collaboratorIds", user.getCollaboratorIds());
        model.addAttribute("receivedRequestIds", user.getReceivedRequestIds());
        model.addAttribute("receivedRequests", user.getReceivedRequests());
        return "profile";
    }

    @GetMapping("/user/profile/{id}")
    public String getProfile(@PathVariable Long id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        User user = userServiceImpl.getUserByEmail(username);
        User researcher = userServiceImpl.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("researcher", researcher);
        List<Post> posts = postServiceImpl.getPostsByUserId(researcher.getId());
        model.addAttribute("posts", posts);
        model.addAttribute("hasSentRequest", user.getSentRequestIds().contains(researcher.getId()));
        model.addAttribute("isCollaborator", user.getCollaboratorIds().contains(researcher.getId()));
        model.addAttribute("hasReceivedRequest", user.getReceivedRequestIds().contains(researcher.getId()));
        model.addAttribute("sentRequests", user.getSentRequests());
        model.addAttribute("collaborations", user.getCollaborations());
        model.addAttribute("receivedRequests", user.getReceivedRequests());
        return "profile";
    }

    // Endpoint to display the form for creating a new post
    @GetMapping("/user/userfeed")
    public String showCreatePostForm(Model model) {
        model.addAttribute("postDto", new PostDto());
        return "userfeed";
    }

    // Endpoint to handle the form submission and save the new post
    @PostMapping("/user/userfeed")
    public String createPost(@ModelAttribute("postDto") PostDto postDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        // Call the service to create and save the post

        postDto.setUser(user);
        postServiceImpl.createPost(postDto);

        // Redirect to a success page or another appropriate page
        return "redirect:/user/profile/" + user.getId(); // Redirect to the user's feed, for example
    }

    @RequestMapping("/user/feed/delete/{id}")
    public String deletePost(@PathVariable String id, Model model) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        User user = userServiceImpl.getUserByEmail(username);
        userServiceImpl.deletePostById(user, Long.valueOf(id));
        return "redirect:/user/feed";
    }

    @PostMapping("/user/collaborate/{id}")
    public String collaborate(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        User researcher = userServiceImpl.getUserById(id);
        userServiceImpl.requestCollaboration(user, researcher);
        // Redirect to a success page or another appropriate page
        return "redirect:/user/profile/{id}"; // Redirect to the user's feed, for example
    }

    @PostMapping("/user/collaborate/cancel/{id}")
    public String cancelCollaboration(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        User researcher = userServiceImpl.getUserById(id);
        userServiceImpl.cancelCollaboration(user, researcher);
        // Redirect to a success page or another appropriate page
        return "redirect:/user/profile/{id}"; // Redirect to the user's feed, for example
    }

    @PostMapping("/user/collaborate/accept/{id}")
    public String acceptCollaboration(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        User researcher = userServiceImpl.getUserById(id);
        userServiceImpl.acceptCollaboration(user, researcher);
        // Redirect to a success page or another appropriate page
        return "redirect:/user/profile/{id}"; // Redirect to the user's feed, for example
    }

    @PostMapping("/user/collaborate/reject/{id}")
    public String rejectCollaboration(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        User researcher = userServiceImpl.getUserById(id);
        userServiceImpl.rejectCollaboration(user, researcher);
        // Redirect to a success page or another appropriate page
        return "redirect:/user/profile/{id}"; // Redirect to the user's feed, for example
    }

    @PostMapping("/user/collaborate/disconnect/{id}")
    public String disconnectCollaboration(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        User researcher = userServiceImpl.getUserById(id);
        userServiceImpl.disconnect(user, researcher);
        // Redirect to a success page or another appropriate page
        return "redirect:/user/profile/{id}"; // Redirect to the user's feed, for example
    }

}
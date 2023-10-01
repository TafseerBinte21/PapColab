package com.tafa.PapColab.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.tafa.PapColab.models.Post;
import com.tafa.PapColab.services.PostService;

@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private PostService postService;

    @GetMapping ("/user/search")
    public String search() {
        return "search";
    }

    @PostMapping
    public String searchResult(@RequestParam(value = "post") String post, Model model) {
        if (!post.isEmpty()) {
            List<Post> posts = postService.findpostBykeyword(post);
            System.out.println(posts);
            model.addAttribute("posts", posts);
        }
        return "search";
    }
}

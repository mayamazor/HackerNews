package com.mayamazor.hackernewsAPI.api.controller;

import com.mayamazor.hackernewsAPI.api.model.Post;
import com.mayamazor.hackernewsAPI.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    // Create a new post
    @PostMapping("/posts")  
    public Post createPost(@RequestBody Post post) {  
        return postService.savePost(post);  
    }

    // Get all posts
    @GetMapping("/posts")  
    public List<Post> getAllPosts() {  
        return postService.getAllPosts();  
    }

}

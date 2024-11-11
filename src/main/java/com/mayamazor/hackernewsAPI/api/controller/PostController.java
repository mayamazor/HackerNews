package com.mayamazor.hackernewsAPI.api.controller;

import com.mayamazor.hackernewsAPI.api.model.Post;
import com.mayamazor.hackernewsAPI.dto.UpdatePostRequest;
import com.mayamazor.hackernewsAPI.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    // Create a new post
    @PostMapping("/posts")  
    public Post createPost(@RequestBody Post post)
    { 
        return saveAndUpdateCache(post);
    }

    // Get all posts
    @GetMapping("/posts")  
    public List<Post> getAllPosts() 
    {  
        return postService.getAllPosts();  
    }
    
    // update an existing post text
    @PutMapping("/posts/{id}")  
    public Post updatePostText(@PathVariable int id,  @RequestBody Post request)
    {
        Optional<Post> optionalPost = postService.getPostById(id);
        if(optionalPost.isPresent())
        {
            Post post = optionalPost.get(); 
            post.setText(request.getText());
            return saveAndUpdateCache(post);
        }
        else
        {
            throw new RuntimeException("Post not found with id " + id);
        }
    }

    // // Upvote an existing post
    @Transactional
    @PutMapping("/posts/{id}/upvote")
    public Post upvotePost(@PathVariable int id)
    {
        Optional<Post> optionalPost = postService.getPostById(id);
        if (optionalPost.isPresent())
        {
            Post post = optionalPost.get();
            post.addUpvote(); 
            return saveAndUpdateCache(post);
        }
        else
        {
            throw new RuntimeException("Post not found with id " + id); 
        }
    }

     // // Downvote an existing post
     @Transactional
     @PutMapping("/posts/{id}/downvote")
     public Post downvotePost(@PathVariable int id)
     {
         Optional<Post> optionalPost = postService.getPostById(id);
         if (optionalPost.isPresent())
         {
            Post post = optionalPost.get();
            post.addDownvote();
            return saveAndUpdateCache(post);
         }
         else
         {
            throw new RuntimeException("Post not found with id " + id); 
         }
     }

    @GetMapping("/posts/top")
    public List<Post> getTopPosts()
    {
        return postService.getTopPosts();
    }
    

    //clear
    @DeleteMapping("/posts/cache/clear")
    public ResponseEntity<String> clearPostsCache()
    {
        postService.clearCache();
        return ResponseEntity.ok("Cache cleared successfully.");
    }

    //update all
    public Post saveAndUpdateCache(Post post) 
    {
        Post savedPost = postService.savePost(post);
        postService.updatePosts();
        postService.updateTopPosts();
        return savedPost;
    }
}

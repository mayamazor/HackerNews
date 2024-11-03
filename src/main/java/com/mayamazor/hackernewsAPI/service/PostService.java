package com.mayamazor.hackernewsAPI.service;

import com.mayamazor.hackernewsAPI.api.model.Post;
import com.mayamazor.hackernewsAPI.repository.PostRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;  

    // Create a new post
    public Post savePost(Post post) {  
        return postRepository.save(post);
    }

    // Get all posts
    public List<Post> getAllPosts() { 
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(int id)
    {
        return postRepository.findById(id);
    }

    public List<Post> getTopPosts() 
    {
        List<Post> posts = postRepository.findAll();

       
        return posts.stream()
                .filter(post -> post.getCreationTime() != null) // Ensure creationTime is not null
                .sorted((p1, p2) -> Double.compare(p2.getComputedScore(), p1.getComputedScore())) // Sort by computed score descending
                .collect(Collectors.toList());
    }
        
    /*List<Post>posts = postRepository.findAll();

    for(Post post : posts)
    {
        if (post.getCreationTime() == null)
        {
            continue;
        }
        post.setComputedScore(calculatePostScore(post));
    }
    return posts.stream()
            .sorted((p1, p2) -> Double.compare(p2.getComputedScore(), p1.getComputedScore()))
            .collect(Collectors.toList());
    */
}
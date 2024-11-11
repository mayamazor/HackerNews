package com.mayamazor.hackernewsAPI.service;

import com.mayamazor.hackernewsAPI.api.model.Post;
import com.mayamazor.hackernewsAPI.repository.PostRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;  
    @Autowired
    private CacheManager cacheManager; 

    // Create a new post
    // @CacheEvict(value = "Posts")
    @CachePut(value = "Posts", key = "#post.id") 
    public Post savePost(Post post)
    {  
        return postRepository.save(post);
    }

    // Get all posts
    @Cacheable(value = "posts", key = "'allPosts'")
    public List<Post> getAllPosts()
    {
        System.out.println("get all posts!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return postRepository.findAll();
    }

    // Get post by ID 
    public Optional<Post> getPostById(int id)
    {
        return postRepository.findById(id);
    }

    //Get top posts
    @Cacheable(value = "posts", key = "'topPosts'")
    public List<Post> getTopPosts() 
    {
        System.out.println("get topp posts!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        List<Post> posts = postRepository.findAll();       
        return posts.stream()
                .filter(post -> post.getCreationTime() != null) // Ensure creationTime is not null
                .sorted((p1, p2) -> Double.compare(p2.getComputedScore(), p1.getComputedScore())) // Sort by computed score descending
                .collect(Collectors.toList());
    }
        

      // Update all
      @CachePut(value = "posts", key = "'allPosts'")
      public List<Post> updatePosts() 
      {
        return postRepository.findAll();
      }
    // Update cache after voting
    @CachePut(value = "posts", key = "'topPosts'")
    public List<Post> updateTopPosts() 
    {
        return getTopPosts(); 
    }
   
    //delete post from cache
    @CacheEvict(value = "posts", allEntries = true)
    public void deletePost(int id) 
    {
        postRepository.deleteById(id);
    }

    //clear
    public void clearCache() 
    {
        if (cacheManager.getCache("posts") != null)
        {
            cacheManager.getCache("posts").clear();
            System.out.println("Cache for 'posts' has been cleared.");
        }
        else
        {
            System.out.println("Cache for 'posts' does not exist.");
        }
    }
}
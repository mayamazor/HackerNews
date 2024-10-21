package com.mayamazor.hackernewsAPI.service;

import com.mayamazor.hackernewsAPI.api.model.Post;
import com.mayamazor.hackernewsAPI.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;  // Change StudentRepository to PostRepository

    // Create a new post
    public Post savePost(Post post) {  // Change saveStudent to savePost
        return postRepository.save(post);
    }

    // Get all posts
    public List<Post> getAllPosts() {  // Change getAllStudents to getAllPosts
        return postRepository.findAll();
    }

    // Other methods can be similarly modified
}
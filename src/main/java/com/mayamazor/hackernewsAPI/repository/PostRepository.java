package com.mayamazor.hackernewsAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mayamazor.hackernewsAPI.api.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    
}

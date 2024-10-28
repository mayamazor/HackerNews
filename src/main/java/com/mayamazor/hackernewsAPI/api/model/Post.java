package com.mayamazor.hackernewsAPI.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.time.LocalDateTime;

import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "posts")
public class Post
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  
    @Column(nullable = false) 
    private String name;   
    @Column(nullable = false)                
    private String text; 
    @Column(nullable = false)             
    private LocalDateTime creationTime; 
    private int upvotes;              
    private int downvotes;    

    /*?? */  
    @Transient
    private double computedScore;      
    /*?? */  

    public Post()
    {
        this.creationTime = LocalDateTime.now();
        this.computedScore = 0;
    }
    public Post(String name, String text)
    {
        this.id = id;
        this.name = name;
        this.text = text;
        this.creationTime = LocalDateTime.now();
        this.upvotes = 0;
        this.downvotes = 0;
        this.computedScore = 0;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }
/*********************************************************************************************/
    public double getComputedScore()
    {
        return computedScore;
    }  

    public void  setComputedScore(double computedScore)
    {
        this.computedScore = computedScore;
    }    
/*********************************************************************************************/
    public void addUpvote() 
    {
        this.upvotes++;
    }

    // Downvote method
    public void addDownvote() {
        this.downvotes++;
    }

}

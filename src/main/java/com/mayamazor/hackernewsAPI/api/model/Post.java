package com.mayamazor.hackernewsAPI.api.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;


@Entity
@Table(name = "posts")
public class Post implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    //////////////////////////////////////////
    @Override
    public String toString() 
    {
        return String.valueOf(this.id); // Assuming `id` is unique for each post
    }
////////////////////////////////////////////////////////////////

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  

    @NotNull
    @Size(min = 1)
    @Column(nullable = false) 
    private String name;

    @NotNull
    @Size(min = 1)
    @Column(nullable = false)                
    private String text; 

    @CreatedDate
    @Column(nullable = false, updatable = false)        
    private LocalDateTime creationTime;

    @Min(value = 0)
    private int voteCount;

    @NotNull
    @Column(nullable = false)  
    private String link;

    private double computedScore = 0;

    public Post()
    {
        this.creationTime = LocalDateTime.now();
    }
    
    public Post(String name, String text, String link)
    {
        this.name = name;
        this.text = text;
        this.link = link;
        this.voteCount = 0;
        this.creationTime = LocalDateTime.now();
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

    public void setCreationTime()
    {
        if (this.creationTime == null)
        {
            this.creationTime = LocalDateTime.now();
        }
    }

    public int getVoteCount() {
        return voteCount;
    }

     public void addUpvote() 
    {
        ++this.voteCount;
    }

    // Downvote method
    public void addDownvote() {
        this.voteCount = Math.max(0, --this.voteCount);
    }


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
/*********************************************************************************************/
    public double getComputedScore()
    {
        return computedScore;
    }  

    @PreUpdate
    public void updateComputedScore()
    {
        if (this.creationTime == null) {
            throw new IllegalStateException("Creation time cannot be null");
        }
        long hoursSinceCreation = Duration.between(this.creationTime, LocalDateTime.now()).toHours(); 
        this.computedScore= voteCount / Math.pow((hoursSinceCreation + 2), 1.5);
    }    
/*********************************************************************************************/
}

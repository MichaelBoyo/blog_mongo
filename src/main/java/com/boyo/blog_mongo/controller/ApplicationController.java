package com.boyo.blog_mongo.controller;

import com.boyo.blog_mongo.data.models.Blog;
import com.boyo.blog_mongo.data.models.User;
import com.boyo.blog_mongo.dtos.requests.AddArticleRequest;
import com.boyo.blog_mongo.dtos.requests.CommentRequest;
import com.boyo.blog_mongo.dtos.requests.LoginRequest;
import com.boyo.blog_mongo.dtos.requests.RegisterUserRequest;
import com.boyo.blog_mongo.services.BlogAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApplicationController {
    @Autowired
    private BlogAppService blogAppService;

    @PostMapping("/user")
    public ResponseEntity<?> registerUSer(@RequestBody RegisterUserRequest request) {
        blogAppService.registerUser(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            return new ResponseEntity<>(blogAppService.login(loginRequest), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/article")
    public ResponseEntity<?> addArticle(@RequestBody AddArticleRequest request) {
        return new ResponseEntity<>(blogAppService.addArticle(request), HttpStatus.OK);
    }

    @PatchMapping("/comment")
    public ResponseEntity<?> addComment(@RequestBody CommentRequest commentRequest) {
        return new ResponseEntity<>(blogAppService.addComment(commentRequest), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable String id) {
        return blogAppService.getUserById(id);
    }

    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return blogAppService.getUserByUserName(username);
    }

    @GetMapping("/blog/{userId}")
    public Blog getBlog(@PathVariable String userId) {
        return blogAppService.getBlog(userId);
    }

}

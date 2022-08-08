package com.boyo.blog_mongo.controller;

import com.boyo.blog_mongo.data.models.User;
import com.boyo.blog_mongo.dtos.requests.*;
import com.boyo.blog_mongo.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/user")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest request) {
        return new ResponseEntity<>(applicationService.registerUser(request),HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(applicationService.login(loginRequest), HttpStatus.OK);
    }


    @PutMapping("/article")
    public ResponseEntity<?> addArticle(@RequestBody AddArticleRequest request) {
        return new ResponseEntity<>(applicationService.addArticle(request), HttpStatus.OK);
    }

    @PatchMapping("/comment")
    public ResponseEntity<?> addComment(@RequestBody CommentRequest commentRequest) {
        return new ResponseEntity<>(applicationService.addComment(commentRequest), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id) {
        return new ResponseEntity<>(applicationService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        return new ResponseEntity<>(applicationService.getUserByUserName(username), HttpStatus.OK);
    }

    @GetMapping("/blog/{userId}")
    public ResponseEntity<?> getBlog(@PathVariable String userId) {
        return new ResponseEntity<>(applicationService.getBlog(userId), HttpStatus.OK);
    }

    @PatchMapping("/user")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequest request) {
        return new ResponseEntity<>(applicationService.updateUser(request), HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        return new ResponseEntity<>(applicationService.deleteUser(id), HttpStatus.OK);
    }

}

package com.boyo.blog_mongo.controller;


import com.boyo.blog_mongo.data.models.User;
import com.boyo.blog_mongo.dtos.requests.LoginRequest;
import com.boyo.blog_mongo.dtos.requests.RegisterUserRequest;
import com.boyo.blog_mongo.dtos.requests.UpdateUserRequest;
import com.boyo.blog_mongo.dtos.responses.RegisterUserResponse;
import com.boyo.blog_mongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public RegisterUserResponse registerUSer(@RequestBody RegisterUserRequest request) {
        return userService.saveUser(request);
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try{
            return new ResponseEntity<>(userService.login(loginRequest), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable String id) {
        return userService.getUser(id);
    }



    @PatchMapping("/user")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequest request) {
        try {
            return new ResponseEntity<>(userService.updateUser(request), HttpStatus.OK);
        } catch (Exception err) {
            return new ResponseEntity<>(err.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        try {
            return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
        } catch (Exception err) {
            return new ResponseEntity<>(err.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

package com.boyo.blog_mongo.controller;


import com.boyo.blog_mongo.data.models.User;
import com.boyo.blog_mongo.dtos.requests.UpdateUserRequest;
import com.boyo.blog_mongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;




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

package com.boyo.blog_mongo.services;

import com.boyo.blog_mongo.data.models.User;
import com.boyo.blog_mongo.dtos.requests.LoginRequest;
import com.boyo.blog_mongo.dtos.requests.RegisterUserRequest;
import com.boyo.blog_mongo.dtos.requests.UpdateUserRequest;
import com.boyo.blog_mongo.dtos.responses.RegisterUserResponse;
import com.boyo.blog_mongo.dtos.responses.UserAndResponse;

public interface UserService {
    UserAndResponse saveUser(RegisterUserRequest registerUserRequest);

    RegisterUserResponse updateUser(UpdateUserRequest updateUserRequest);

    RegisterUserResponse deleteUser(String id);

    User getUser(String id);

    User reSave(User user);

    User getUserByUsername(String username);

    User login(LoginRequest loginRequest);

    void clearDatabase();

    long getNumberOfUsers();
}

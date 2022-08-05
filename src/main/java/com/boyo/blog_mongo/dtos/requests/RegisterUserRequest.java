package com.boyo.blog_mongo.dtos.requests;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String username;
    private String password;
}

package com.boyo.blog_mongo.dtos.requests;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String id;
    private String username;
    private String password;
}

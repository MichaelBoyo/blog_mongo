package com.boyo.blog_mongo.dtos.responses;

import com.boyo.blog_mongo.data.models.User;
import lombok.Data;

@Data
public class UserAndResponse extends User {
    private User user;
    private RegisterUserResponse response;
    public UserAndResponse(User savedUser, RegisterUserResponse resp) {
        user = savedUser;
        response= resp;
    }
}

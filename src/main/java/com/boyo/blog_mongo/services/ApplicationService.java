package com.boyo.blog_mongo.services;

import com.boyo.blog_mongo.data.models.Article;
import com.boyo.blog_mongo.data.models.Blog;
import com.boyo.blog_mongo.data.models.User;
import com.boyo.blog_mongo.dtos.requests.*;
import com.boyo.blog_mongo.dtos.responses.RegisterUserResponse;

import java.util.List;


public interface ApplicationService {
    String addArticle(AddArticleRequest request);

    String addComment(CommentRequest commentRequest);
    List<Article> articlesByAUser(String userId);

    void clearDatabases();

    User registerUser(RegisterUserRequest request);

    User getUser(String michael);

    User login(LoginRequest loginRequest);

    User getUserById(String id);

    User getUserByUserName(String username);

    Blog getBlog(String userId);

    RegisterUserResponse updateUser(UpdateUserRequest request);

    RegisterUserResponse deleteUser(String id);
}

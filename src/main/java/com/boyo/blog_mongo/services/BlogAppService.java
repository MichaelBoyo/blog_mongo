package com.boyo.blog_mongo.services;

import com.boyo.blog_mongo.data.models.Article;
import com.boyo.blog_mongo.dtos.requests.AddArticleRequest;
import com.boyo.blog_mongo.dtos.requests.CommentRequest;

import java.util.List;


public interface BlogAppService {
    String addArticle(AddArticleRequest request);

    String addComment(CommentRequest commentRequest);
    List<Article> articlesByAUser(String userId);
}

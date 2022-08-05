package com.boyo.blog_mongo.services;

import com.boyo.blog_mongo.data.models.Article;
import com.boyo.blog_mongo.dtos.requests.ArticleRequest;

import java.util.List;


public interface ArticleService {
    Article saveArticle(ArticleRequest articleRequest);

    String updateArticle(String id, ArticleRequest articleRequest);

    String deleteArticle(String id);

    Article getArticle(String id);

    void reSave(Article article);

}

package com.boyo.blog_mongo.dtos.requests;

import lombok.Data;

@Data
public class ArticleRequest {
    private String title;
    private String body;
}

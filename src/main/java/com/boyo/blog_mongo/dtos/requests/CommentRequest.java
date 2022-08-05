package com.boyo.blog_mongo.dtos.requests;

import lombok.Data;

@Data
public class CommentRequest {
    private String articleId;
    private String comment;
}

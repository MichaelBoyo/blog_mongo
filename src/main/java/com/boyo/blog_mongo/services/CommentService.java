package com.boyo.blog_mongo.services;

import com.boyo.blog_mongo.data.models.Comment;
import com.boyo.blog_mongo.dtos.requests.CommentRequest;

public interface CommentService {
    Comment saveComment(CommentRequest commentRequest);

    String updateComment(String id, CommentRequest commentRequest);

    String deleteComment(String id);

    Comment getComment(String id);
}

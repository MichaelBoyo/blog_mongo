package com.boyo.blog_mongo.utils;

import com.boyo.blog_mongo.data.models.Article;
import com.boyo.blog_mongo.data.models.Blog;
import com.boyo.blog_mongo.data.models.Comment;
import com.boyo.blog_mongo.data.models.User;
import com.boyo.blog_mongo.dtos.requests.*;

import java.util.Objects;


public class Mapper {

    public static void mapRequestToUSer(RegisterUserRequest registerUserRequest, User user) {
        user.setUsername(registerUserRequest.getUsername());
    }

    public static void mapArticleReQuestToArticle(ArticleRequest articleRequest, Article article) {
        if (articleRequest.getTitle() != null && !Objects.equals(articleRequest.getTitle(), "")) {
            article.setTitle(articleRequest.getTitle());
        }
        if (articleRequest.getBody() != null && !Objects.equals(articleRequest.getBody(), "")) {
            article.setBody(articleRequest.getBody());
        }


    }

    public static void mapBlogRequestToBlog(BlogRequest blogRequest, Blog blog) {
        if (blogRequest.getBlogName() != null && !Objects.equals(blogRequest.getBlogName(), "")) {
            blog.setBlogName(blogRequest.getBlogName());
        }
    }

    public static void mapCommentRequestToComment(CommentRequest commentRequest, Comment comment) {
        if (commentRequest.getComment() != null && !Objects.equals(commentRequest.getComment(), "")) {
            comment.setBody(commentRequest.getComment());
        }
    }

    public static void MapUpdateRequestToUser(UpdateUserRequest request, User user) {
        if (request.getUsername() != null && !Objects.equals(request.getUsername(), "")) {
            user.setUsername(request.getUsername());
        }
    }

    public static void mapAddArticleReqToArticleReq(ArticleRequest articleRequest, AddArticleRequest request) {
        articleRequest.setTitle(request.getTitle());
        articleRequest.setBody(request.getBody());
    }

    public static void mapAddArticleReqToBlogRequest(AddArticleRequest request, BlogRequest blogRequest) {
        blogRequest.setBlogName(request.getBlogName());
    }
}

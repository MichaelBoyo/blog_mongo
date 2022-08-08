package com.boyo.blog_mongo.services;


import com.boyo.blog_mongo.data.models.Blog;
import com.boyo.blog_mongo.dtos.requests.BlogRequest;

public interface BlogService {
    Blog saveBlog(BlogRequest blogRequest);

    String updateBlog(String id, BlogRequest blogRequest);

    String deleteBlog(String id);

    Blog getBlog(String id);

    void reSave(Blog blog);

    Blog findBlogByName(String blogName);

    void clearDatabase();
}

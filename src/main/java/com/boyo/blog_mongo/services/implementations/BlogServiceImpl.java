package com.boyo.blog_mongo.services.implementations;

import com.boyo.blog_mongo.data.models.Blog;
import com.boyo.blog_mongo.data.repositories.BlogRepository;
import com.boyo.blog_mongo.dtos.requests.BlogRequest;
import com.boyo.blog_mongo.exceptions.BlogNotFoundException;
import com.boyo.blog_mongo.services.BlogService;
import com.boyo.blog_mongo.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog saveBlog(BlogRequest blogRequest) {
        Blog blog = new Blog();
        Mapper.mapBlogRequestToBlog(blogRequest, blog);
        return blogRepository.save(blog);
    }

    @Override
    public String updateBlog(String id, BlogRequest blogRequest) {
        var blog = getBlog(id);
        Mapper.mapBlogRequestToBlog(blogRequest, blog);
        blogRepository.save(blog);
        return "updated successfully";
    }

    @Override
    public String deleteBlog(String id) {
        var blog = getBlog(id);
        blogRepository.delete(blog);
        return "deleted successfully";
    }

    @Override
    public Blog getBlog(String id) {
        return blogRepository.findById(id).orElseThrow(
                () -> new BlogNotFoundException("blog with id-> " + id + " not found"));
    }

    @Override
    public void reSave(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public Blog findBlogByName(String blogName) {
        return blogRepository.findByBlogName(blogName);
    }

    @Override
    public void clearDatabase() {
        blogRepository.deleteAll();
    }
}

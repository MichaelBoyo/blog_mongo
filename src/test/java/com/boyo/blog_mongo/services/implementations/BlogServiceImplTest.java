package com.boyo.blog_mongo.services.implementations;

import com.boyo.blog_mongo.data.models.Blog;
import com.boyo.blog_mongo.dtos.requests.BlogRequest;
import com.boyo.blog_mongo.exceptions.BlogNotFoundException;
import com.boyo.blog_mongo.services.BlogService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlogServiceImplTest {
    Blog blog;
    @Autowired
    private BlogService blogService;

    @BeforeEach
    void setUp() {
        BlogRequest blogRequest = new BlogRequest();
        blogRequest.setBlogName("test blog");
        blog = blogService.saveBlog(blogRequest);
    }

    @AfterEach
    void tearDown() {
        blogService.clearDatabase();
    }

    @Test
    void saveBlog() {
        assertNotNull(blog);
        assertEquals(blog.getBlogName(), "test blog");
    }

    @Test
    void updateBlog() {
        BlogRequest blogRequest = new BlogRequest();
        blogRequest.setBlogName("updated blog");

        blogService.updateBlog(blog.getId(),blogRequest);
        blog = blogService.getBlog(blog.getId());
        assertEquals(blog.getBlogName(), "updated blog");
    }

    @Test
    void deleteBlog() {
        blogService.deleteBlog(blog.getId());
        assertThrows(BlogNotFoundException.class,
                ()->blogService.getBlog(blog.getId()));
    }

}
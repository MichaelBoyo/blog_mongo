package com.boyo.blog_mongo.data.repositories;

import com.boyo.blog_mongo.data.models.Blog;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BlogRepositoryTest {
    @Autowired
    private BlogRepository blogRepository;
    @AfterEach
    void tearDown() {
        blogRepository.deleteAll();
    }

    @Test
    void findByBlogName() {
        Blog blog = new Blog();
        blog.setBlogName("test blog");
        blogRepository.save(blog);
        assertNotNull(blogRepository.findByBlogName("test blog"));
        assertEquals("test blog", blogRepository.findByBlogName("test blog").getBlogName());
    }
}
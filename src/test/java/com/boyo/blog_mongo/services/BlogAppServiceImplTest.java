package com.boyo.blog_mongo.services;

import com.boyo.blog_mongo.dtos.requests.AddArticleRequest;
import com.boyo.blog_mongo.dtos.requests.ArticleRequest;
import com.boyo.blog_mongo.dtos.requests.RegisterUserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BlogAppServiceImplTest {

    @Autowired
    BlogAppService blogAppService;



    @BeforeEach
    void setUp() {
        blogAppService.clearDatabases();
    }

    @Test
    void addArticle() {
        var request = new RegisterUserRequest();
        request.setUsername("michael");
        request.setPassword("1234");
        blogAppService.registerUser(request);

        var user = blogAppService.getUser("michael");
        var articleRequest = new AddArticleRequest();
        articleRequest.setUsername(user.getUsername());
        articleRequest.setTitle("love");
        articleRequest.setBody("love is beautiful");
        blogAppService.addArticle(articleRequest);

        assertEquals(1, blogAppService.articlesByAUser(user.getId()).size());
        assertEquals("love",blogAppService.articlesByAUser(user.getId()).get(0).getTitle());
    }

    @Test
    void addComment() {
    }

    @Test
    void articlesByAUser() {
    }
}
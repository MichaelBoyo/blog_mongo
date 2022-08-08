package com.boyo.blog_mongo.services.implementations;

import com.boyo.blog_mongo.data.models.Article;
import com.boyo.blog_mongo.dtos.requests.ArticleRequest;
import com.boyo.blog_mongo.exceptions.ArticleNotFoundException;
import com.boyo.blog_mongo.services.ArticleService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ArticleServiceImplTest {
    @Autowired
    private ArticleService articleService;

    @AfterEach
    void tearDown() {
        articleService.clearDatabase();
    }

    @Test
    void saveArticleTest() {
        ArticleRequest request = new ArticleRequest();
        request.setTitle("test title");
        request.setBody("test body");
        var article = articleService.saveArticle(request);
        assertEquals(article.getBody(),"test body");
        assertEquals(article.getTitle(),"test title");
    }

    @Test
    void updateArticle() {
        ArticleRequest request = new ArticleRequest();
        request.setTitle("test title");
        request.setBody("test body");
        var article = articleService.saveArticle(request);
        request.setTitle("edited title");
        request.setBody("edited body");
        articleService.updateArticle(article.getId(),request);

        article = articleService.getArticle(article.getId());

        assertEquals(article.getBody(),"edited body");
        assertEquals(article.getTitle(),"edited title");
    }

    @Test
    void deleteArticle() {
        ArticleRequest request = new ArticleRequest();
        request.setTitle("test title");
        request.setBody("test body");
        var article = articleService.saveArticle(request);

        articleService.deleteArticle(article.getId());
        assertThrows(ArticleNotFoundException.class,
                ()->articleService.getArticle(article.getId()));

    }

    @Test
    void getArticle() {
        ArticleRequest request = new ArticleRequest();
        request.setTitle("test title");
        request.setBody("test body");
        var article = articleService.saveArticle(request);
        assertNotNull(articleService.getArticle(article.getId()));
        assertEquals("test title",articleService.getArticle(article.getId()).getTitle());
    }


    @Test
    void clearDatabase() {
        ArticleRequest request = new ArticleRequest();
        request.setTitle("test title");
        request.setBody("test body");
        var article = articleService.saveArticle(request);

        request.setTitle("test title1");
        request.setBody("test body1");
        articleService.saveArticle(request);

        request.setTitle("test title2");
        request.setBody("test body2");
        articleService.saveArticle(request);

        articleService.clearDatabase();

        assertEquals(0,articleService.getAllArticles().size());
    }
}
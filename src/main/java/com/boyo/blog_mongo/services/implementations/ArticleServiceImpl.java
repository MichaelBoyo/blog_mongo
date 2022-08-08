package com.boyo.blog_mongo.services.implementations;

import com.boyo.blog_mongo.data.models.Article;
import com.boyo.blog_mongo.data.repositories.ArticleRepository;
import com.boyo.blog_mongo.dtos.requests.ArticleRequest;
import com.boyo.blog_mongo.exceptions.ArticleNotFoundException;
import com.boyo.blog_mongo.services.ArticleService;
import com.boyo.blog_mongo.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Article saveArticle(ArticleRequest articleRequest) {
        Article article = new Article();

        Mapper.mapArticleReQuestToArticle(articleRequest, article);
        return articleRepository.save(article);

    }

    @Override
    public String updateArticle(String id, ArticleRequest articleRequest) {
        var article = getArticle(id);
        Mapper.mapArticleReQuestToArticle(articleRequest, article);
        articleRepository.save(article);
        return "updated successfully";
    }

    @Override
    public String deleteArticle(String id) {
        var article = getArticle(id);
        articleRepository.delete(article);
        return "Deleted Successfully";
    }

    @Override
    public Article getArticle(String id) {
        return articleRepository.findById(id).orElseThrow(
                () -> new ArticleNotFoundException("article with id-> " + id + " not found"));
    }

    @Override
    public void reSave(Article article) {
        articleRepository.save(article);
    }

    @Override
    public void clearDatabase() {
        articleRepository.deleteAll();
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }
}

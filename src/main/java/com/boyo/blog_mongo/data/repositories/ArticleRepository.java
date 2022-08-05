package com.boyo.blog_mongo.data.repositories;

import com.boyo.blog_mongo.data.models.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {
}

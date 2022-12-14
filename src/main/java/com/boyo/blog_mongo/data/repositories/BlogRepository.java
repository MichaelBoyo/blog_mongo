package com.boyo.blog_mongo.data.repositories;

import com.boyo.blog_mongo.data.models.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends MongoRepository<Blog, String> {
    Blog findByBlogName(String blogName);
}

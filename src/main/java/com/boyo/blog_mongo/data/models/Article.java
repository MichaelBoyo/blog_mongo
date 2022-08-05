package com.boyo.blog_mongo.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document("article")
public class Article {
    @Id
    private String id;
    private String title;
    private String body;
    @DBRef
    private List<Comment> comments = new ArrayList<>();
}

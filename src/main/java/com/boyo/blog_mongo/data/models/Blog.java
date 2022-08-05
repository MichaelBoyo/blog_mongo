package com.boyo.blog_mongo.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document("blog")
public class Blog {
    @Id
    private String  id;
    private String blogName;
    @DBRef
    private List<Article> articles = new ArrayList<>();
}

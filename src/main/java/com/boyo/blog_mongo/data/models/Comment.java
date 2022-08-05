package com.boyo.blog_mongo.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("comment")
public class Comment {
    @Id
    private String  id;
    private String body;
}

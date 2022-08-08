package com.boyo.blog_mongo.services.implementations;

import com.boyo.blog_mongo.dtos.requests.CommentRequest;
import com.boyo.blog_mongo.exceptions.CommentNotFoundException;
import com.boyo.blog_mongo.services.CommentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CommentServiceImplTest {
    @Autowired
    private CommentService commentService;

    @AfterEach
    void tearDown() {
        commentService.clearDatabase();
    }

    @Test
    void saveComment() {
        var request = new CommentRequest();
        request.setComment("test comment");
        var comment = commentService.saveComment(request);
        assertNotNull(comment);
        assertEquals("test comment", comment.getBody());
    }

    @Test
    void updateComment() {
        var request = new CommentRequest();
        request.setComment("test comment");
        var comment = commentService.saveComment(request);
        request.setComment("comment update");
        commentService.updateComment(comment.getId(),request);

        comment = commentService.getComment(comment.getId());

        assertEquals(comment.getBody(),"comment update");
    }

    @Test
    void deleteComment() {
        var request = new CommentRequest();
        request.setComment("test comment");
        var comment = commentService.saveComment(request);

        commentService.deleteComment(comment.getId());
        assertThrows(CommentNotFoundException.class,
                ()->commentService.getComment(comment.getId()));

    }
}
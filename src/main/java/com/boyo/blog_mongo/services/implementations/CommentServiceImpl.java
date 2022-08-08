package com.boyo.blog_mongo.services.implementations;

import com.boyo.blog_mongo.data.models.Comment;
import com.boyo.blog_mongo.data.repositories.CommentRepository;
import com.boyo.blog_mongo.dtos.requests.CommentRequest;
import com.boyo.blog_mongo.exceptions.CommentNotFoundException;
import com.boyo.blog_mongo.services.CommentService;
import com.boyo.blog_mongo.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment saveComment(CommentRequest commentRequest) {
        Comment comment = new Comment();
        Mapper.mapCommentRequestToComment(commentRequest, comment);
        return commentRepository.save(comment);
    }

    @Override
    public String updateComment(String id, CommentRequest commentRequest) {
        var comment = getComment(id);
        Mapper.mapCommentRequestToComment(commentRequest, comment);
        return "comment updated successfully";
    }

    @Override
    public String deleteComment(String id) {
        var comment = getComment(id);
        commentRepository.delete(comment);
        return "comment deleted successfully";
    }

    @Override
    public Comment getComment(String id) {
        return commentRepository.findById(id).orElseThrow(
                () -> new CommentNotFoundException("comment with id-> " + id + " not found"));
    }

    @Override
    public void clearDatabase() {
        commentRepository.deleteAll();
    }
}

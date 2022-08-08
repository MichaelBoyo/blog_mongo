package com.boyo.blog_mongo.services;

import com.boyo.blog_mongo.data.models.Article;
import com.boyo.blog_mongo.data.models.Blog;
import com.boyo.blog_mongo.data.models.User;
import com.boyo.blog_mongo.dtos.requests.*;
import com.boyo.blog_mongo.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogAppServiceImpl implements BlogAppService {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    @Override
    public String addArticle(AddArticleRequest request) {
        var articleRequest = new ArticleRequest();
        Mapper.mapAddArticleReqToArticleReq(articleRequest, request);

        var article = articleService.saveArticle(articleRequest);
        var user = userService.getUserByUsername(request.getUsername());
        var blog = user.getBlog();

        blog.getArticles().add(article);
        blogService.reSave(blog);
        user.setBlog(blog);
        userService.reSave(user);
        return "Article added Successfully";
    }

    @Override
    public String addComment(CommentRequest commentRequest) {
        var article = articleService.getArticle(commentRequest.getArticleId());
        var comment = commentService.saveComment(commentRequest);

        article.getComments().add(comment);
        articleService.reSave(article);
        return "comment added successfully";
    }

    @Override
    public List<Article> articlesByAUser(String userId) {
        var user = userService.getUser(userId);
        return user.getBlog().getArticles();
    }

    @Override
    public void clearDatabases() {
        articleService.clearDatabase();
        userService.clearDatabase();
        blogService.clearDatabase();
        commentService.clearDatabase();
    }

    @Override
    public void registerUser(RegisterUserRequest request) {
        var user = userService.saveUser(request).getUser();

        BlogRequest blogRequest = new BlogRequest();
        blogRequest.setBlogName(request.getBlogName());
        Blog blog = blogService.saveBlog(blogRequest);
        user.setBlog(blog);
        userService.reSave(user);
    }

    @Override
    public User getUser(String email) {
        return userService.getUserByUsername(email);
    }

    @Override
    public User login(LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @Override
    public User getUserById(String id) {
        return userService.getUser(id);
    }

    @Override
    public User getUserByUserName(String username) {
        return userService.getUserByUsername(username);
    }

    @Override
    public Blog getBlog(String userId) {
        return userService.getUser(userId).getBlog();
    }
}

package com.boyo.blog_mongo.services.implementations;

import com.boyo.blog_mongo.data.models.User;
import com.boyo.blog_mongo.data.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void saveUserTest() {
        User user = new User();
        var savedUser = userRepository.save(user);
        assertThat(savedUser.getId(), is(notNullValue()));
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void getUser() {
    }

    @Test
    void reSave() {
    }

    @Test
    void getUserByUsername() {
    }
}
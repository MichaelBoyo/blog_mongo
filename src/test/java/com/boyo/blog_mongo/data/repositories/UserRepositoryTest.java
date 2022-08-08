package com.boyo.blog_mongo.data.repositories;

import com.boyo.blog_mongo.data.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private  UserRepository userRepository;
    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void findUserByUsername() {
        User user = new User();
        user.setUsername("michael");
        userRepository.save(user);
        assertNotNull(userRepository.findUserByUsername("michael"));
        assertEquals("michael", userRepository.findUserByUsername("michael").get().getUsername());
    }
}
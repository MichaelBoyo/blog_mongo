package com.boyo.blog_mongo.services.implementations;

import com.boyo.blog_mongo.dtos.requests.RegisterUserRequest;
import com.boyo.blog_mongo.dtos.requests.UpdateUserRequest;
import com.boyo.blog_mongo.exceptions.UserNotFoundException;
import com.boyo.blog_mongo.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService.clearDatabase();
    }

    @Test
    void saveUserTest() {
        var request = new RegisterUserRequest();
        request.setUsername("michael");
        request.setPassword("1234");
        var savedUser = userService.saveUser(request).getUser();
        assertThat(savedUser.getId(), is(notNullValue()));
    }

    @Test
    void getUserTest() {
        var request = new RegisterUserRequest();
        request.setUsername("michael");
        request.setPassword("1234");
        var savedUser = userService.saveUser(request).getUser();
        assertThat(savedUser.getId(), is(notNullValue()));

        assertEquals("michael", userService.getUserByUsername("michael").getUsername());
    }

    @Test
    void updateUserTest() {
        var request = new RegisterUserRequest();
        request.setUsername("michael");
        request.setPassword("1234");
        var savedUser = userService.saveUser(request).getUser();
        assertThat(savedUser.getId(), is(notNullValue()));
        assertEquals("michael", userService.getUserByUsername("michael").getUsername());

        var update = new UpdateUserRequest();
        update.setId(savedUser.getId());
        update.setUsername("kiki");
        userService.updateUser(update);
        assertNotEquals("michael",userService.getUser(savedUser.getId()).getUsername());
        assertEquals("kiki", userService.getUserByUsername("kiki").getUsername());
        assertEquals(1L, userService.getNumberOfUsers());


    }

    @Test
    void deleteUser() {
        var request = new RegisterUserRequest();
        request.setUsername("michael");
        request.setPassword("1234");
        var savedUser = userService.saveUser(request).getUser();
        userService.deleteUser(savedUser.getId());

        assertThrows(UserNotFoundException.class,
                ()->userService.getUser(savedUser.getId()));
        assertEquals(0,userService.getNumberOfUsers());
    }


    @Test
    void throwExceptionTest() {
        var request = new RegisterUserRequest();
        request.setUsername("michael");
        request.setPassword("1234");
        var savedUser = userService.saveUser(request).getUser();
        userService.deleteUser(savedUser.getId());

        assertThrows(UserNotFoundException.class,
                ()->userService.getUser(savedUser.getId()));
        assertEquals(0,userService.getNumberOfUsers());
    }

    @Test
    void getUserByUsername() {
        var request = new RegisterUserRequest();
        request.setUsername("michael");
        request.setPassword("1234");
        var savedUser = userService.saveUser(request).getUser();

        assertEquals(savedUser.getUsername(),userService.getUserByUsername("michael").getUsername());
    }
}
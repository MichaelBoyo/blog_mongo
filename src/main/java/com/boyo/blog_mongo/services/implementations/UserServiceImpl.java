package com.boyo.blog_mongo.services.implementations;

import com.boyo.blog_mongo.data.models.User;
import com.boyo.blog_mongo.data.repositories.UserRepository;
import com.boyo.blog_mongo.dtos.requests.LoginRequest;
import com.boyo.blog_mongo.dtos.requests.RegisterUserRequest;
import com.boyo.blog_mongo.dtos.requests.UpdateUserRequest;
import com.boyo.blog_mongo.dtos.responses.RegisterUserResponse;
import com.boyo.blog_mongo.exceptions.InvalidCredentialException;
import com.boyo.blog_mongo.exceptions.UserAlreadyExistException;
import com.boyo.blog_mongo.exceptions.UserNotFoundException;
import com.boyo.blog_mongo.services.UserService;
import com.boyo.blog_mongo.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public RegisterUserResponse saveUser(RegisterUserRequest registerUserRequest) {
        if (userRepository.findAll().stream().anyMatch(
                (user -> user.getUsername().equals(registerUserRequest.getUsername())))) {
            throw new UserAlreadyExistException("email " + registerUserRequest.getUsername() + " already exist");
        }

        User user = new User();
        user.setPassword(registerUserRequest.getPassword());
        Mapper.mapRequestToUSer(registerUserRequest, user);
        var savedUSer = userRepository.save(user);
        return new RegisterUserResponse(savedUSer.getUsername() + " registered successfully");
    }

    @Override
    public RegisterUserResponse updateUser(UpdateUserRequest request) {
        var user = getUser(request.getId());
        if (request.getPassword() != null && !Objects.equals(request.getPassword(), "")) {
            user.setPassword(request.getPassword());
        }
        Mapper.MapUpdateRequestToUser(request, user);
        userRepository.save(user);
        return new RegisterUserResponse("updated successfully");
    }

    @Override
    public RegisterUserResponse deleteUser(String  id) {
        return new RegisterUserResponse("deleted successfully");
    }

    @Override
    public User getUser(String id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("user with id-> " + id + " not found"));
    }

    @Override
    public void reSave(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(
                () -> new UserNotFoundException("user with username -> " + username + " not found"));
    }

    @Override
    public User login(LoginRequest loginRequest) {
        var user = getUserByUsername(loginRequest.getUsername());
        if (user == null) {
            throw new InvalidCredentialException("username" + loginRequest.getUsername() + " doesnt exist");
        }
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new InvalidCredentialException("wrong password");
        }
        return user;
    }
}

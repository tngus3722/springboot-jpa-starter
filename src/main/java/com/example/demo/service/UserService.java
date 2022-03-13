package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {

    void SignIn(User user) throws Exception;

    User getMe() throws Exception;

    List<User> getAllUser(Integer page, Integer limit) throws Exception;

    void updateUser(User user) throws Exception;

    void deleteUser(User user) throws Exception;
}

package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.wrapper.UserWrapper;

import java.util.List;

public interface UserService {
    void SignIn(User user);
    User getMe();
    List<User> getAllUser();
    void updateUser(User user);
    void updateUser2(User user);
    void updateUser3(User user);
    void deleteUser(User user);
    UserWrapper getSubQueryAndGetDTO();
    User getSubQueryAndGetEntity();
}

package com.example.demo.service;

import com.example.demo.model.User;
import java.util.List;

public interface UserService {
    void SignIn(User user);
    User getMe();
    List<User> getAllUser();
    void updateUser(User user);
    void deleteUser(User user);
}

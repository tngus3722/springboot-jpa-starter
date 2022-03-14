package com.example.demo.service;

import com.example.demo.dto.request.UserSignUpRequest;
import com.example.demo.dto.request.UserUpdateRequest;
import com.example.demo.dto.response.UserResponse;
import java.util.List;

public interface UserService {

    UserResponse singUp(UserSignUpRequest userSignUpRequest);

    UserResponse getMe(Long userId);

    List<UserResponse> getUsers(Integer page, Integer limit);

    UserResponse updateUser(Long userId, UserUpdateRequest userUpdateRequest);

    void deleteUser(Long userId);
}

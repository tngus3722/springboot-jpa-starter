package com.example.demo.controller;

import com.example.demo.dto.request.UserSignUpRequest;
import com.example.demo.dto.request.UserUpdateRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.service.UserService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    @ApiOperation(value = "회원 가입", notes = "회원 가입")
    public ResponseEntity<UserResponse> singUp(@RequestBody @Valid UserSignUpRequest userSignUpRequest) {
        return new ResponseEntity(userService.singUp(userSignUpRequest), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    @ApiOperation(value = "단일 회원 조회 ", notes = "단일 회원 조회")
    public ResponseEntity<UserResponse> getMe(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(userService.getMe(userId), HttpStatus.OK);
    }

    @GetMapping("/users")
    @ApiOperation(value = "모든 회원 조회", notes = "모든 회원 조회")
    public ResponseEntity<List<UserResponse>> getUsers(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        return new ResponseEntity<>(userService.getUsers(page, limit), HttpStatus.OK);
    }

    @PutMapping("/user/{userId}")
    @ApiOperation(value = "회원 수정", notes = "회원 수정")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("userId") Long userId,
            @RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        return new ResponseEntity<>(userService.updateUser(userId, userUpdateRequest), HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}")
    @ApiOperation(value = "회원 삭제", notes = "회원 삭제")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity(HttpStatus.OK);
    }
}

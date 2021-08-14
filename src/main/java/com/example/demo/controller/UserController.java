package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/user")
    @ApiOperation(value = "회원 가입", notes = "회원 가입")
    public ResponseEntity test(@RequestBody User user) throws Exception {
        userService.SignIn(user);
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @GetMapping("/user")
    @ApiOperation(value = "단일 회원 조회 ", notes = "단일 회원 조회")
    public ResponseEntity<User> getMe() throws Exception {
        return new ResponseEntity<User>(userService.getMe(), HttpStatus.OK);
    }

    @GetMapping("/users")
    @ApiOperation(value = "모든 회원 조회", notes = "모든 회원 조회")
    public ResponseEntity<List<User>> getUsers(@RequestParam Integer page, @RequestParam Integer limit) throws Exception {
        return new ResponseEntity<List<User>>(userService.getAllUser(page, limit), HttpStatus.OK);
    }

    @PutMapping("/user")
    @ApiOperation(value = "회원 수정", notes = "회원 수정")
    public ResponseEntity updateUser(@RequestBody User user) throws Exception {
        userService.updateUser(user);
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @DeleteMapping("/user")
    @ApiOperation(value = "회원 삭제", notes = "회원 삭제")
    public ResponseEntity deleteUser(@RequestBody User user) throws Exception {
        userService.deleteUser(user);
        return new ResponseEntity(null, HttpStatus.OK);
    }
}

package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.wrapper.UserWrapper;
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
    @ApiOperation(value ="회원 가입" , notes = "회원 가입")
    public ResponseEntity test(@RequestBody User user){
        userService.SignIn(user);
        return new ResponseEntity( null, HttpStatus.OK);
    }

    @GetMapping("/user")
    @ApiOperation(value ="회원 가입" , notes = "회원 가입")
    public ResponseEntity<User> getMe(){
        return new ResponseEntity<User>( userService.getMe(), HttpStatus.OK);
    }

    @GetMapping("/users")
    @ApiOperation(value ="회원 가입" , notes = "회원 가입")
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<List<User>>( userService.getAllUser(), HttpStatus.OK);
    }

    @PutMapping("/user")
    @ApiOperation(value ="회원 가입" , notes = "회원 가입")
    public ResponseEntity updateUser(@RequestBody User user){
        userService.updateUser(user);
        return new ResponseEntity( null, HttpStatus.OK);
    }

    @PutMapping("/user2")
    @ApiOperation(value ="회원 가입" , notes = "회원 가입")
    public ResponseEntity updateUser2(@RequestBody User user){
        userService.updateUser2(user);
        return new ResponseEntity( null, HttpStatus.OK);
    }

    @PutMapping("/user3")
    @ApiOperation(value ="회원 가입" , notes = "회원 가입")
    public ResponseEntity updateUser3(@RequestBody User user){
        userService.updateUser3(user);
        return new ResponseEntity( null, HttpStatus.OK);
    }

    @DeleteMapping("/user")
    @ApiOperation(value ="회원 가입" , notes = "회원 가입")
    public ResponseEntity deleteUser(@RequestBody User user){
        userService.deleteUser(user);
        return new ResponseEntity( null, HttpStatus.OK);
    }


    @GetMapping("/test")
    @ApiOperation(value ="회원 가입" , notes = "회원 가입")
    public ResponseEntity<UserWrapper> test(){
        return new ResponseEntity<UserWrapper>( userService.getSubQueryAndGetDTO(), HttpStatus.OK);
    }
}

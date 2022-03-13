package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

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

    @KafkaListener(topics = "TOPIC", groupId = "test")
    public void consume(String message) throws IOException {
        System.out.println("스트링 : " + String.format("Consumed message : %s", message));
    }

    @Resource
    private KafkaTemplate<String, String> kafkaProducer;

    @GetMapping("/kafka/test")
    public void produce() throws Exception {
        kafkaProducer.send("TOPIC", new ObjectMapper().writeValueAsString("testFromSpringboot"));
    }


}

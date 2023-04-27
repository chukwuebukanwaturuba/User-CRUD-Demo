package com.example.restapicruddemo.controller;

import com.example.restapicruddemo.dto.UserDTO;
import com.example.restapicruddemo.entity.User;
import com.example.restapicruddemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public User registerUser(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }
}

package com.victor.stockalarms.controller;

import com.victor.stockalarms.dto.UserDTO;
import com.victor.stockalarms.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public void createUser(@RequestBody final UserDTO userDTO) {
        userService.saveUser(userDTO);
    }

}
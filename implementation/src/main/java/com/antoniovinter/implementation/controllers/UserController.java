package com.antoniovinter.implementation.controllers;

import com.antoniovinter.api.dto.UserDto;
import com.antoniovinter.implementation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class UserController implements com.antoniovinter.api.controller.UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<UserDto> getAllUsers(){
        return userService.findAllUsers();
    }

    @Override
    public UserDto getUserById(@PathVariable int id){
        return userService.findById(id);
    }

    @Override
    public UserDto getUserByName(@RequestParam(required = false) String value){
        return userService.findByName(value);
    }

    @Override
    public UserDto addUser(UserDto user) {
        return userService.addUser(user);
    }


}

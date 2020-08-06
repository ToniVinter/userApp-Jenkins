package com.antoniovinter.api.controller;

import com.antoniovinter.api.dto.UserDto;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RequestMapping("/api/users")
public interface UserController {
    @GetMapping()
    public List<UserDto> getAllUsers();

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable int id);

    @GetMapping("/name")
    public UserDto getUserByName(@RequestParam(required = false) String value);

    @PostMapping()
    public UserDto addUser(@RequestBody UserDto user);
}

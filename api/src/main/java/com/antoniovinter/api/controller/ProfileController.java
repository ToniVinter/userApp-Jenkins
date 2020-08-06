package com.antoniovinter.api.controller;

import com.antoniovinter.api.dto.ProfileDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("profile")
public interface ProfileController {
    @GetMapping("")
    public List<ProfileDto> getAllProfiles();

    @GetMapping("/{id}")
    public ProfileDto getProfileById(@PathVariable int id);

    @GetMapping("/user")
    public ProfileDto getProfileByUserName(@RequestParam String name);

    @PostMapping("/user/save")
    public ProfileDto saveProfile(@RequestBody ProfileDto profile, @RequestParam String name);

    @DeleteMapping("/{id}")
    public ProfileDto deleteProfile(@PathVariable int id);

    @PutMapping("/{id}")
    public ProfileDto updateProfile(@PathVariable int id, @RequestBody ProfileDto profileDto);
}

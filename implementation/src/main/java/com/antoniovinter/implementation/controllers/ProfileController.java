package com.antoniovinter.implementation.controllers;

import com.antoniovinter.api.dto.ProfileDto;
import com.antoniovinter.implementation.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProfileController implements com.antoniovinter.api.controller.ProfileController {

    private ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Override
    public List<ProfileDto> getAllProfiles() {
        return profileService.findAllProfiles();
    }

    @Override
    public ProfileDto getProfileById(int id) {
        return profileService.findById(id);
    }

    @Override
    public ProfileDto getProfileByUserName(String name) {
        return profileService.findProfileByUser(name);
    }

    @Override
    public ProfileDto saveProfile(ProfileDto profile, String name) {
        return profileService.save(profile,name);
    }

    @Override
    public ProfileDto deleteProfile(int id) {
        return profileService.delete(id);
    }

    @Override
    public ProfileDto updateProfile(int id, ProfileDto profileDto) {
        return profileService.update(profileDto, id);
    }
}

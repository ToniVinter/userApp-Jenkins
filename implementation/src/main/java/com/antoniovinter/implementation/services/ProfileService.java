package com.antoniovinter.implementation.services;

import com.antoniovinter.api.dto.ProfileDto;
import com.antoniovinter.implementation.dao.repository.ProfileRepository;
import com.antoniovinter.implementation.dao.repository.UserRepository;
import com.antoniovinter.implementation.domain.Profile;
import com.antoniovinter.implementation.domain.User;
import com.antoniovinter.implementation.exception.ProfileNotFound;
import com.antoniovinter.implementation.exception.UserNotFound;
import com.antoniovinter.implementation.services.converter.ProfileConverter;
import com.antoniovinter.implementation.services.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {
    private final ProfileRepository profileRepo;
    private final ProfileConverter profileConverter;
    private final UserConverter userConverter;
    private final UserRepository userRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepo, ProfileConverter profileConverter, UserConverter userConverter, UserRepository userRepository) {
        this.profileRepo = profileRepo;
        this.profileConverter = profileConverter;
        this.userConverter = userConverter;
        this.userRepository = userRepository;
    }

    public ProfileDto findById(int id) {
        Profile profile = profileRepo.findById(id)
                .orElseThrow(() -> new ProfileNotFound("profile with id not found, id: " + id));
        return profileConverter.ProfileToDto(profile);
    }

    public List<ProfileDto> findAllProfiles() {
        return profileRepo.findAll()
                .stream()
                .map(profileConverter::ProfileToDto)
                .collect(Collectors.toList());

    }

    public ProfileDto findProfileByUser(String name) {
        Profile profile = profileRepo.findByUserName(name);
        if (profile != null) {
            return profileConverter.ProfileToDto(profile);
        } else {
            throw new ProfileNotFound("Profile not found");
        }
    }

    public ProfileDto save(ProfileDto profileDto, String name) {
        User user = userRepository.findByName(name);
        System.out.println(user);
        Profile profile = profileConverter.DtoToProfile(profileDto);
        profile.setUser(user);
        user.setProfile(profile);
        System.out.println(profile);
        profileRepo.save(profile);
        return profileConverter.ProfileToDto(profile);
    }

    public ProfileDto update(ProfileDto profileDto, int id) {
        Profile newProfile = profileRepo.findById(id).orElseThrow(() -> new ProfileNotFound("profile not found"));
        newProfile.setAge(profileDto.getAge());
        newProfile.setBio(profileDto.getBio());
        User user = userConverter.DtoToUser(profileDto.getUser());
        newProfile.setUser(user);

        Profile savedProfile = profileRepo.save(newProfile);

        return profileConverter.ProfileToDto(savedProfile);
    }

    public ProfileDto delete(int id) {
        Profile profile = profileRepo.findById(id).orElseThrow(() -> new UserNotFound("user not found"));
        profileRepo.delete(profile);
        System.out.println(profile);
        return profileConverter.ProfileToDto(profile);
    }


}

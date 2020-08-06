package com.antoniovinter.implementation.services.converter;

import com.antoniovinter.api.dto.ProfileDto;
import com.antoniovinter.api.dto.UserDto;
import com.antoniovinter.implementation.domain.Profile;
import com.antoniovinter.implementation.domain.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProfileConverter {
    public ProfileDto ProfileToDto(Profile profile){
        ModelMapper modelMapper = new ModelMapper();
        ProfileDto profileDto = modelMapper.map(profile, ProfileDto.class);
        return profileDto;
    }

    public Profile DtoToProfile(ProfileDto profileDto){
        ModelMapper modelMapper = new ModelMapper();
        Profile profile = modelMapper.map(profileDto, Profile.class);
        return profile;
    }


}

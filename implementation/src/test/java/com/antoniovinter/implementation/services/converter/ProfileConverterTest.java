package com.antoniovinter.implementation.services.converter;

import com.antoniovinter.api.dto.ProfileDto;
import com.antoniovinter.api.dto.UserDto;
import com.antoniovinter.implementation.domain.Profile;
import com.antoniovinter.implementation.domain.User;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ProfileConverterTest {



    @InjectMocks
    ProfileConverter profileConverter;


    @Test
    @DisplayName("When converting user to UserDto, return UserDto")
    public void userToDto() {
        Profile profile = new Profile("dsa",32);

        ProfileDto profileDto = profileConverter.ProfileToDto(profile);

        assertThat(profile.getAge()).isEqualTo(profileDto.getAge());
    }


    @Test
    @DisplayName("When converting user to UserDto, return UserDto")
    public void dtoToUser() {
        ProfileDto profileDto = new ProfileDto("dsa",32,null);

         Profile profile= profileConverter.DtoToProfile(profileDto);

        assertThat(profile.getAge()).isEqualTo(profileDto.getAge());
    }
}

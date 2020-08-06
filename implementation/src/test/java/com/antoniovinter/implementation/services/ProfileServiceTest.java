package com.antoniovinter.implementation.services;

import com.antoniovinter.api.dto.PostDto;
import com.antoniovinter.api.dto.ProfileDto;
import com.antoniovinter.implementation.dao.repository.ProfileRepository;
import com.antoniovinter.implementation.dao.repository.UserRepository;
import com.antoniovinter.implementation.domain.Post;
import com.antoniovinter.implementation.domain.Profile;
import com.antoniovinter.implementation.domain.User;
import com.antoniovinter.implementation.exception.PostNotFound;
import com.antoniovinter.implementation.exception.ProfileNotFound;
import com.antoniovinter.implementation.exception.UserNotFound;
import com.antoniovinter.implementation.services.converter.ProfileConverter;
import com.antoniovinter.implementation.services.converter.UserConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ProfileServiceTest {

    private Profile expected;

    @InjectMocks
    private ProfileService profileService;

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private ProfileConverter profileConverter;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserConverter userConverter;

    @Before
    public void init(){
        expected = new Profile("daf",32);
        MockitoAnnotations.initMocks(this);
    }


    @Test
    @DisplayName("When profile with id is found, return it")
    public void findPostById() {

        when(profileRepository.findById(anyInt())).thenReturn(Optional.of(expected));
        when(profileConverter.ProfileToDto(any())).thenReturn(new ProfileDto(expected.getBio(), expected.getAge(),null));
        System.out.println(profileService.findById(anyInt()));

        ProfileDto result = profileService.findById(anyInt());

        assertThat(expected.getAge()).isEqualTo(result.getAge());
    }

    @Test(expected = ProfileNotFound.class)
    @DisplayName("When profile with id is not found, throw ProfileNotFound")
    public void findPostByIdNull(){
        when(profileRepository.findById(anyInt())).thenReturn(Optional.empty());

        profileService.findById(anyInt());
    }

    @Test
    public void whenFindingAllProfiles_shouldReturnListOfProfilesDto(){
        List<Profile> profiles = Collections.singletonList(expected);

        when(profileRepository.findAll()).thenReturn(profiles);
        when(profileConverter.ProfileToDto(any())).thenReturn(new ProfileDto(expected.getBio(),expected.getAge(),null));

        List<ProfileDto> profileDtos = profileService.findAllProfiles();

        assertThat(profiles.contains(profileDtos.get(0)));
    }

    @Test
    public void whenSavingProfile_thenReturnIt(){
        when(userRepository.findByName(anyString())).thenReturn(new User("Ana","ASd",null,null));
        when(profileConverter.DtoToProfile(any())).thenReturn(expected);
        when(profileRepository.save(any())).thenReturn(expected);
        when(profileConverter.ProfileToDto(any())).thenReturn(new ProfileDto(expected.getBio(),expected.getAge(),null));

        ProfileDto profileDto = profileService.save(new ProfileDto(expected.getBio(),expected.getAge(),null),anyString());

        assertThat(profileDto.getAge()).isEqualTo(expected.getAge());
    }

    @Test
    public void withGivenNameFromUser_shouldReturnProfileDto(){

        when(profileRepository.findByUserName(anyString())).thenReturn(expected);
        when(profileConverter.ProfileToDto(any())).thenReturn(new ProfileDto(expected.getBio(),expected.getAge(),null));

        ProfileDto profileDto = profileService.findProfileByUser("fsd");

        assertThat(expected.getAge()).isEqualTo(profileDto.getAge());
    }

    @Test(expected = ProfileNotFound.class)
    public void withGivenNameFromUser_shouldThrowProfileNotFound(){
        profileService.findProfileByUser("adsa");

    }

    @Test
    public void whenUpdatingProfile_shouldReturnTheNewProfile(){
        when(profileRepository.findById(anyInt())).thenReturn(Optional.of(expected));
        when(userConverter.DtoToUser(any())).thenReturn(new User("sad","dsad",null,null));
        when(profileRepository.save(any())).thenReturn(new Profile("das",13));
        when(profileConverter.ProfileToDto(any())).thenReturn(new ProfileDto("das",13,null));

        ProfileDto profileDto = profileService.update(new ProfileDto(expected.getBio(),expected.getAge(),null),anyInt());

        assertThat(expected.getAge()).isEqualTo(expected.getAge());
    }

    @Test
    public void whenDeleteAProfile_shouldReturnIt(){
        when(profileRepository.findById(anyInt())).thenReturn(Optional.of(expected));

        profileService.delete(1);

        verify(profileRepository, times(1)).delete(expected);
    }




}

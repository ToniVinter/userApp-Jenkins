package com.antoniovinter.implementation.services;

import com.antoniovinter.api.dto.UserDto;
import com.antoniovinter.implementation.dao.repository.UserRepository;
import com.antoniovinter.implementation.domain.User;
import com.antoniovinter.implementation.exception.UserNotFound;
import com.antoniovinter.implementation.services.converter.UserConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private User expected;

    @Before
    public void init(){
        expected = new User("Max","password",null,null);
    }

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserConverter userConverter;



    @Test
    public void whenCallingGetAllUsersreturnListOfAllUsers(){

        List<User> expectedList = new ArrayList<>();
        expectedList.add(expected);
        when(userRepository.findAll()).thenReturn(expectedList);
        when(userConverter.UserToDto(any())).thenReturn(new UserDto(expected.getName(),expected.getPassword()));

        List<UserDto> actualUsers = userService.findAllUsers();

        assertThat(actualUsers.get(0).getName()).isEqualTo(expectedList.get(0).getName());
    }
    @Test
    public void whenSearchingByIdThenReturnUserWithId() {


        when(userRepository.findById(anyInt())).thenReturn(java.util.Optional.of(expected));
        when(userConverter.UserToDto(any())).thenReturn(new UserDto(expected.getName(), expected.getPassword()));

        UserDto result = userService.findById(4);
        System.out.println(result);
        assertThat(expected.getName()).isEqualTo(result.getName());
    }

    @Test(expected = UserNotFound.class)
    public void withGivenId_shouldReturnUserNotFoundException() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

        userService.findById(anyInt());
    }

    @Test
    public void whenSearchingByName_ThenReturnEntity(){

        when(userRepository.findByName(anyString())).thenReturn(expected);
        when(userConverter.UserToDto(any())).thenReturn(new UserDto(expected.getName(),expected.getPassword()));

        UserDto actual = userService.findByName(anyString());

        assertThat(expected.getName()).isEqualTo(actual.getName());


    }

    @Test(expected = UserNotFound.class)
    public void withGivenName_shouldReturnUserNotFound(){

        when(userRepository.findByName(anyString())).thenReturn(null);

        userService.findByName(anyString());
    }

    @Test
    public void withGivenUserDto_ShouldSaveItAndReturn(){
        User expected = new User("Ana","adfd", null, null);
        UserDto userDto = new UserDto(expected.getName(),expected.getPassword());
        when(userRepository.save(any(User.class))).thenReturn(expected);
        when(userConverter.UserToDto(expected)).thenReturn(userDto);


        UserDto actual = userService.addUser(userDto);


        assertThat(expected.getName()).isEqualTo(actual.getName());
    }

    @Test(expected = UserNotFound.class)
    public void withGivenUserDto_ShouldReturnUserNotFound(){
        userService.addUser(null);
    }

    @Test
    public void withGivenUserandId_ShouldUpdateAndReturnUser(){
        int id = 1;
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(new User()));
        when(userRepository.save(any(User.class))).thenReturn(expected);

        User actual = userService.replaceUser(expected,1);

        assertThat(expected.getName()).isEqualTo(actual.getName());

    }







}

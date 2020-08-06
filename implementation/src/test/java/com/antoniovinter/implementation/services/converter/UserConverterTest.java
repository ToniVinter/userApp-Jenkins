package com.antoniovinter.implementation.services.converter;

import com.antoniovinter.api.dto.UserDto;
import com.antoniovinter.implementation.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserConverterTest {



    @InjectMocks
    UserConverter userConverter;


    @Test
    @DisplayName("When converting user to UserDto, return UserDto")
    public void userToDto() {
        User user = new User("dsa","fsdf",null,null);

        UserDto userDto = userConverter.UserToDto(user);

        assertThat(user.getName()).isEqualTo(userDto.getName());
    }

    @Test
    @DisplayName("When converting user to UserDto, return UserDto")
    public void DtoToUser() {
        UserDto userDto = new UserDto("dsa","fsdf");

        User user = userConverter.DtoToUser(userDto);

        assertThat(user.getName()).isEqualTo(userDto.getName());
    }
}

package com.antoniovinter.implementation.controllers;

import com.antoniovinter.api.dto.UserDto;
import com.antoniovinter.implementation.domain.User;
import com.antoniovinter.implementation.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("When get all users, return a list with them")
    public void getAllUsers() throws Exception {
        UserDto userDto = new UserDto("Melinda", "asd");
        List<UserDto> users = Arrays.asList(userDto);
        doReturn(users).when(userService).findAllUsers();

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/users"))
                .andExpect(jsonPath("$[0].name", Matchers.is("Melinda")));

    }

    @Test
    public void getUserById() throws Exception {
        UserDto userDto = new UserDto("adf","sdff");

        when(userService.findById(1)).thenReturn(userDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("adf"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("sdff"))
                .andExpect(status().isOk());

    }

    @Test
    public void getUserByName() throws Exception {
        UserDto userDto = new UserDto("adf","sdff");

        when(userService.findByName("adf")).thenReturn(userDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/name?value=adf"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("adf"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("sdff"))
                .andExpect(status().isOk());


    }

    @Test
    public void saveUser() throws Exception {
        User user = new User("John","myPassword",null, null);
        UserDto userDto = new UserDto(user.getName(),user.getPassword());
        when(userService.addUser(any(UserDto.class))).thenReturn(userDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                .content(new ObjectMapper().writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("myPassword"));

    }

}

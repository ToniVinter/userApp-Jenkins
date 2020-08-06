package com.antoniovinter.implementation.services;

import com.antoniovinter.api.dto.UserDto;
import com.antoniovinter.implementation.dao.repository.UserRepository;
import com.antoniovinter.implementation.domain.User;
import com.antoniovinter.implementation.exception.UserNotFound;
import com.antoniovinter.implementation.services.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository repo;
    private UserConverter userConverter;

    @Autowired
    public UserService(UserRepository repo, UserConverter userConverter) {
        this.repo = repo;
        this.userConverter = userConverter;
    }

    public UserDto findById(int id){
        User user = repo.findById(id)
                .orElseThrow(() -> new UserNotFound("user with id not found, id: " + id));
        return userConverter.UserToDto(user);
    }

    public UserDto findByName(String name){
        User user = repo.findByName(name);
        if(user !=null){
            return userConverter.UserToDto(user);
        }else{
            throw new UserNotFound("user with name not found, name: " + name);
        }
    }

    public List<UserDto> findAllUsers(){
        List<UserDto> userDtos = repo.findAll().stream()
                .map(user -> userConverter.UserToDto(user))
                .collect(Collectors.toList());

        return userDtos;
    }

    public UserDto addUser(UserDto userDto){
        if(userDto !=null){
            System.out.println(userDto + "------------");
            repo.save(userConverter.DtoToUser(userDto));
            return userDto;
        }else{
            throw new UserNotFound("user can't be added");
        }
    }

    public User replaceUser(User newUser, int id){
        User user = repo.findById(id)
                .orElseThrow( () -> new UserNotFound("user with id not found"));
        user.setName(newUser.getName());
        user.setPassword(newUser.getPassword());
        repo.save(user);
        return user;
    }
}


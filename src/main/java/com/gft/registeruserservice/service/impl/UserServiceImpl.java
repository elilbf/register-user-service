package com.gft.registeruserservice.service.impl;

import com.gft.registeruserservice.dto.UserDTO;
import com.gft.registeruserservice.exception.UserAlreadyExistsException;
import com.gft.registeruserservice.model.Ability;
import com.gft.registeruserservice.model.User;
import com.gft.registeruserservice.repository.UserRepository;
import com.gft.registeruserservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final static String DELIMITER = "'|";

    @Autowired
    private UserRepository userRepository;

    public void createUser(UserDTO userDTO){
         var user = User.builder()
                 .name(userDTO.getName())
                 .email(userDTO.getEmail())
                 .birthDate(userDTO.getBirthDate())
                 .address(userDTO.getAddress())
                 .abilities(User.parseAbilities(userDTO.getAbilities())).build();

        if(Objects.isNull(userRepository.findUserByNameAndEmail(userDTO.getName(), userDTO.getEmail()))){
            userRepository.save(user);
        } else {
            throw new UserAlreadyExistsException(HttpStatus.BAD_REQUEST, "The user: " + user.getName() + " already exists. Try register a new user.");
        }

    }

    public List<UserDTO> findAllUsers(){
        return userRepository.findAll().stream().map(u -> UserDTO.builder()
                .name(u.getName())
                .email(u.getEmail())
                .birthDate(u.getBirthDate())
                .abilities(u.getAbilities().stream().map(Ability::getDescription).toList())
                .build()
        ).toList();
    }

    public User findUserById(long id){
        return null;
    }

    public void deleteUserById(long id){
    }

    public void deleteAllUsers(){
        userRepository.deleteAll();
    }
}

package com.gft.registeruserservice.service.impl;

import com.gft.registeruserservice.dto.UserDTO;
import com.gft.registeruserservice.exception.UserAlreadyExistsException;
import com.gft.registeruserservice.exception.UserNotFoundException;
import com.gft.registeruserservice.model.User;
import com.gft.registeruserservice.repository.UserRepository;
import com.gft.registeruserservice.service.UserService;
import com.gft.registeruserservice.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(UserDTO userDTO){
        UserUtil.validatePayloadFields(userDTO);

         var user = User.builder()
                 .name(userDTO.getName())
                 .email(userDTO.getEmail())
                 .birthDate(userDTO.getBirthDate())
                 .address(userDTO.getAddress())
                 .abilities(User.parseAbilities(userDTO.getAbilities())).build();

        if(Objects.isNull(userRepository.findUserByNameAndEmail(userDTO.getName(), userDTO.getEmail()))){
            userRepository.save(user);
        } else {
            throw new UserAlreadyExistsException(HttpStatus.BAD_REQUEST,
                    "The user: " + user.getName() + " already exists. Try register a new user.");
        }
    }

    public List<UserDTO> findAllUsers(){
        return userRepository.findAll().stream().map(UserDTO::parseUser).toList();
    }

    public UserDTO findUserById(Long id){
        var user = userRepository.findById(id);

        if(user.isPresent()){
            return UserDTO.parseUser(user.get());
        }

        throw new UserNotFoundException(HttpStatus.NOT_FOUND, "User with ID: " + id + " not found!");
    }

    public void deleteUserById(Long id){
            userRepository.deleteById(id);
    }

}

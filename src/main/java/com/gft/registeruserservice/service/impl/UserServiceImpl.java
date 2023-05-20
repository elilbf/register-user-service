package com.gft.registeruserservice.service.impl;

import com.gft.registeruserservice.dto.UserDTO;
import com.gft.registeruserservice.exception.UserAlreadyExistsException;
import com.gft.registeruserservice.model.User;
import com.gft.registeruserservice.repository.UserRepository;
import com.gft.registeruserservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(UserDTO userDTO){
         var user = User.builder()
                 .name(userDTO.getName())
                 .email(userDTO.getEmail())
                 .birthDate(userDTO.getBirthDate())
                 .address(userDTO.getAddress())
                 .habilities(userDTO.getAbilities().toString()).build();

        if(Objects.isNull(userRepository.findUserByNameAndEmail(userDTO.getName(), userDTO.getEmail()))){
            userRepository.save(user);
        } else {
            throw new UserAlreadyExistsException(HttpStatus.BAD_REQUEST,"The user: " + userDTO.getName() + " already exists. Try register a new user.");
        }

    }

    public List<UserDTO> findAllUsers(){
        var user = userRepository.findAll();

        return UserDTO.builder()
                .name(user.get)

        return userRepository.findAll();
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

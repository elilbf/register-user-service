package com.gft.registeruserservice.service.impl;

import com.gft.registeruserservice.exception.UserAlreadyExistsException;
import com.gft.registeruserservice.model.User;
import com.gft.registeruserservice.repository.UserRepository;
import com.gft.registeruserservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(User user){
        if(Objects.isNull(userRepository.findUserByNameAndEmail(user.getName(), user.getEmail()))){
            userRepository.save(user);
        } else {
            throw new UserAlreadyExistsException(HttpStatus.BAD_REQUEST,"The user: " + user.getName() + " already exists. Try register a new user.");
        }

    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User findUserById(long id){
        return null;
    }

    public void deleteUserById(long id){
    }

    public void deleteAllUsers(){

    }
}

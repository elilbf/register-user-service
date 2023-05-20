package com.gft.registeruserservice.controller;

import com.gft.registeruserservice.dto.UserDTO;
import com.gft.registeruserservice.exception.UserAlreadyExistsException;
import com.gft.registeruserservice.model.User;
import com.gft.registeruserservice.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User", description = "This is an API to create, get and delete users")
@RestController
@RequestMapping("user-register-service/api/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO){
        userService.createUser(userDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/users/findAllUsers")
    public ResponseEntity<List<UserDTO>> findAllUsers(){
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> findUserById(@PathVariable("userId") long userId){
//        return new ResponseEntity<>(userService.findUserById(), HttpStatus.OK);
        return null;
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity deleteUserById(@PathVariable("userId") long userId){
        return null;
    }

    @DeleteMapping("/users")
    public ResponseEntity deleteAllUsers(){
        userService.deleteAllUsers();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

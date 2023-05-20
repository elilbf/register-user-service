package com.gft.registeruserservice.controller;

import com.gft.registeruserservice.model.User;
import com.gft.registeruserservice.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User", description = "This is an API to create, get and delete users")
@RestController
@RequestMapping("user-register-service/api/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<HttpStatus> createUser(@RequestBody User user){
        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/user/findAllUsers")
    public ResponseEntity<List<User>> findAllUsers(){
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> findUserById(@PathVariable("userId") long userId){
//        return new ResponseEntity<>(userService.findUserById(), HttpStatus.OK);
        return null;
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity deleteUserById(@PathVariable("userId") long userId){
        return null;
    }

    @DeleteMapping("/user")
    public ResponseEntity deleteAllUsers(){
        userService.deleteAllUsers();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

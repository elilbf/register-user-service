package com.gft.registeruserservice.controller;

import com.gft.registeruserservice.model.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User", description = "This is an API to create, get and delete users")
@RestController
@RequestMapping("/api/")
public class UserController {


    @PostMapping("/user")
    public ResponseEntity createUser(@RequestBody User user){
        System.out.println(user);
        return null;
    }

    @GetMapping("/findAllUsers")
    public ResponseEntity<List<User>> findAllUsers(){
        return null;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> findUserById(@PathVariable("userId") long userId){
        return null;
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity deleteUserById(@PathVariable("userId") long userId){
        return null;
    }

    @DeleteMapping("/user")
    public ResponseEntity deleteAllUsers(){
        return null;
    }

}

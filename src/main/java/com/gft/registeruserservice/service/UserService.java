package com.gft.registeruserservice.service;

import com.gft.registeruserservice.model.User;

import java.util.List;

public interface UserService {

    void createUser(User user);

    List<User> findAllUsers();

    User findUserById(long id);

    void deleteUserById(long id);

    void deleteAllUsers();

}

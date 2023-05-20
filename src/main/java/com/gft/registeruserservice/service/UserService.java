package com.gft.registeruserservice.service;

import com.gft.registeruserservice.dto.UserDTO;
import com.gft.registeruserservice.model.User;

import java.util.List;

public interface UserService {

    void createUser(UserDTO userDTO);

    List<UserDTO> findAllUsers();

    User findUserById(long id);

    void deleteUserById(long id);

    void deleteAllUsers();

}

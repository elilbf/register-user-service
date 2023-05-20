package com.gft.registeruserservice.service;

import com.gft.registeruserservice.dto.UserDTO;

import java.util.List;

public interface UserService {

    void createUser(UserDTO userDTO);

    List<UserDTO> findAllUsers();

    UserDTO findUserById(Long id);

    void deleteUserById(Long id);

}

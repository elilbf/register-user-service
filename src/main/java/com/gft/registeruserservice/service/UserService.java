package com.gft.registeruserservice.service;

import com.gft.registeruserservice.dto.UserDTO;

import java.util.List;

public interface UserService {

    void createUser(UserDTO userDTO);

    void updateUser(Long id, UserDTO userDTO);

    List<UserDTO> findAllUsers();

    UserDTO findUserIfExistsById(Long id);

    void deleteUserById(Long id);

}

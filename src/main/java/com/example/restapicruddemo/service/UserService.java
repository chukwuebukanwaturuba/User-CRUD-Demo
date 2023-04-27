package com.example.restapicruddemo.service;

import com.example.restapicruddemo.dto.UserDTO;
import com.example.restapicruddemo.entity.User;

public interface UserService {
    User createUser (UserDTO userDTO);

    UserDTO updateUser (UserDTO userDTO, Long id);

    void deleteUser (Long id);

    User loginUser(UserDTO userDTO);
}

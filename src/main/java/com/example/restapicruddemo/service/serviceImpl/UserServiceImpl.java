package com.example.restapicruddemo.service.serviceImpl;

import com.example.restapicruddemo.dto.UserDTO;
import com.example.restapicruddemo.entity.User;
import com.example.restapicruddemo.repository.UserRepo;
import com.example.restapicruddemo.service.UserService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    @Override
    public User createUser(UserDTO userDTO) {

        User newUser = User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();

        return userRepo.save(newUser);
    }

    public UserDTO update(User user) {

        UserDTO newUser = UserDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();

        return newUser;
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Long id) {
        User updateUser = userRepo.findById(id).orElseThrow(()-> new RuntimeException("User does not exist in the database"));

        if (StringUtils.isBlank(userDTO.getFirstName()) || StringUtils.isBlank(userDTO.getLastName())
                || StringUtils.isBlank(userDTO.getEmail()) || StringUtils.isBlank(userDTO.getPassword())) {
            throw new IllegalArgumentException("Invalid input data");
        }

        updateUser.setFirstName(userDTO.getFirstName());
        updateUser.setLastName(userDTO.getLastName());
        updateUser.setEmail(userDTO.getEmail());
        updateUser.setPassword(userDTO.getPassword());

        updateUser = userRepo.save(updateUser);
        return  update(updateUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepo.findById(id).orElseThrow(()-> new RuntimeException("User does not exist in the database"));
        userRepo.delete(user);
    }

    @Override
    public User loginUser(UserDTO userDTO) {
        return userRepo.findUserByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
    }


}

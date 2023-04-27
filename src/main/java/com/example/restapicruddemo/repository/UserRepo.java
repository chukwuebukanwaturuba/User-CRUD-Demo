package com.example.restapicruddemo.repository;

import com.example.restapicruddemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findUserByEmailAndPassword(String email, String password);
}

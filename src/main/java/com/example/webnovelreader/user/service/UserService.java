package com.example.webnovelreader.user.service;


import com.example.webnovelreader.user.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User saveUser(User user);
    User getUser(String username);
    List<User> getUsers();

    boolean existsByUsername(String username);
}

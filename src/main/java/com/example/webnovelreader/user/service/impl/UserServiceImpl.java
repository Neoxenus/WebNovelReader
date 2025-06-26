package com.example.webnovelreader.user.service.impl;

import com.example.webnovelreader.user.entities.User;
import com.example.webnovelreader.user.repo.UserRepository;
import com.example.webnovelreader.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor @Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    @Transactional
    public User saveUser(User user) {
        log.info("Saving new user {} to the database", user.getUserName());
        return userRepository.save(user);
    }

    @Override
    public User getUser(String userName) {
        log.info("Getting user {} from database", userName);
        return userRepository.findByUserName(userName);
    }

    @Override
    public List<User> getUsers() {
        log.info("Getting all users from database");
        return userRepository.findAll();
    }
}

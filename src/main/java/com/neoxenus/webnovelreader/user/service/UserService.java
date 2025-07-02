package com.neoxenus.webnovelreader.user.service;


import com.neoxenus.webnovelreader.user.entities.dtos.UserCreateRequest;
import com.neoxenus.webnovelreader.user.entities.dtos.UserDto;
import com.neoxenus.webnovelreader.user.entities.dtos.UserUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public interface UserService {
    UserDto saveUser(UserCreateRequest user);


    Optional<UserDto> getUser(Long id);
    List<UserDto> getUsers();

    UserDto updateUser(Long id, UserUpdateRequest user) throws NoSuchElementException;
    boolean existsByUsername(String username);

    void deleteUser(Long id);
}

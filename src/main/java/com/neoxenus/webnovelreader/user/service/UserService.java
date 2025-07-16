package com.neoxenus.webnovelreader.user.service;


import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;
import com.neoxenus.webnovelreader.exceptions.UsernameExistsException;
import com.neoxenus.webnovelreader.user.entities.dtos.UserCreateRequest;
import com.neoxenus.webnovelreader.user.entities.dtos.UserDto;
import com.neoxenus.webnovelreader.user.entities.dtos.UserUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserDto saveUser(UserCreateRequest user) throws UsernameExistsException;


    UserDto getUser(Long id) throws NoSuchEntityException;
    List<UserDto> getUsers();

    UserDto updateUser(Long id, UserUpdateRequest user) throws NoSuchEntityException, UsernameExistsException;
    boolean existsByUsername(String username);

    void deleteUser(Long id);
}

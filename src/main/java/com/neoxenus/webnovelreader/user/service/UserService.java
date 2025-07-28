package com.neoxenus.webnovelreader.user.service;


import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;
import com.neoxenus.webnovelreader.exceptions.UsernameExistsException;
import com.neoxenus.webnovelreader.user.entity.User;
import com.neoxenus.webnovelreader.user.dto.request.UserCreateRequest;
import com.neoxenus.webnovelreader.user.dto.UserDto;
import com.neoxenus.webnovelreader.user.dto.request.UserUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserDto saveUser(UserCreateRequest user) throws UsernameExistsException;

    void initAdmin();
    User getCurrentUser();

    UserDto getUser(Long id) throws NoSuchEntityException;
    List<UserDto> getUsers();

    UserDto updateUser(Long id, UserUpdateRequest user) throws NoSuchEntityException, UsernameExistsException;

    void deleteUser(Long id);
}

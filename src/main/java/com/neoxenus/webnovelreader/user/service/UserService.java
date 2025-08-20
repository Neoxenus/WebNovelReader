package com.neoxenus.webnovelreader.user.service;


import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;
import com.neoxenus.webnovelreader.exceptions.UsernameExistsException;
import com.neoxenus.webnovelreader.user.dto.ImageDto;
import com.neoxenus.webnovelreader.user.dto.UserDto;
import com.neoxenus.webnovelreader.user.dto.request.UserCreateRequest;
import com.neoxenus.webnovelreader.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserDto saveUser(UserCreateRequest user) throws UsernameExistsException;

    void initAdmin();
    User getCurrentUser();
    User findById(Long id);
    UserDto getUser(Long id) throws NoSuchEntityException;
    List<UserDto> getUsers();

    void deleteUser(Long id);

    ImageDto getAvatar(Long id);
}

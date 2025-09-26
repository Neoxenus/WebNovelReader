package com.neoxenus.webnovelreader.user.service;


import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;
import com.neoxenus.webnovelreader.exceptions.UsernameExistsException;
import com.neoxenus.webnovelreader.user.dto.response.ImageDtoResponse;
import com.neoxenus.webnovelreader.user.dto.response.UserDtoResponse;
import com.neoxenus.webnovelreader.user.dto.request.UserCreateDtoRequest;
import com.neoxenus.webnovelreader.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserDtoResponse saveUser(UserCreateDtoRequest user) throws UsernameExistsException;

    void initAdmin();
    User getCurrentUser();
    User findById(Long id);
    UserDtoResponse getUser(Long id) throws NoSuchEntityException;
    List<UserDtoResponse> getUsers();

    void deleteUser(Long id);

    ImageDtoResponse getAvatar(Long id);
}

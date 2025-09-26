package com.neoxenus.webnovelreader.user.service;

import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;
import com.neoxenus.webnovelreader.exceptions.UsernameExistsException;
import com.neoxenus.webnovelreader.user.dto.response.UserDtoResponse;
import com.neoxenus.webnovelreader.user.dto.request.UserUpdateDtoRequest;

public interface UserSettingsService {
    UserDtoResponse updateUser(Long id, UserUpdateDtoRequest userUpdateDtoRequest) throws NoSuchEntityException, UsernameExistsException;
}

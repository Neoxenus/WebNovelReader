package com.neoxenus.webnovelreader.user.service;

import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;
import com.neoxenus.webnovelreader.exceptions.UsernameExistsException;
import com.neoxenus.webnovelreader.user.dto.UserDto;
import com.neoxenus.webnovelreader.user.dto.request.UserUpdateRequest;

public interface UserSettingsService {
    UserDto updateUser(Long id, UserUpdateRequest userUpdateRequest) throws NoSuchEntityException, UsernameExistsException;
}

package com.neoxenus.webnovelreader.user.mapper;

import com.neoxenus.webnovelreader.user.entities.User;
import com.neoxenus.webnovelreader.user.entities.dtos.UserCreateRequest;
import com.neoxenus.webnovelreader.user.entities.dtos.UserDto;
import com.neoxenus.webnovelreader.user.entities.dtos.UserUpdateRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {
    UserDto mapUserToDto(User user);

    List<UserDto> mapUserToDto(List<User> users);

    User mapCreateRequestToUser(UserCreateRequest user);

    User mapUpdateRequestToUser(User user, UserUpdateRequest userUpdateRequest);
}

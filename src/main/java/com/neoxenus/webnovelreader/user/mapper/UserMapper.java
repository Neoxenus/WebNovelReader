package com.neoxenus.webnovelreader.user.mapper;

import com.neoxenus.webnovelreader.user.entity.User;
import com.neoxenus.webnovelreader.user.dto.request.UserCreateRequest;
import com.neoxenus.webnovelreader.user.dto.UserDto;
import com.neoxenus.webnovelreader.user.dto.request.UserUpdateRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {
    UserDto toDto(User user);

    List<UserDto> toDto(List<User> users);

    User toUser(UserCreateRequest user);

    User toUser(User user, UserUpdateRequest userUpdateRequest);
}

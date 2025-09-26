package com.neoxenus.webnovelreader.user.mapper;

import com.neoxenus.webnovelreader.user.entity.User;
import com.neoxenus.webnovelreader.user.dto.request.UserCreateDtoRequest;
import com.neoxenus.webnovelreader.user.dto.response.UserDtoResponse;
import com.neoxenus.webnovelreader.user.dto.request.UserUpdateDtoRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {
    UserDtoResponse toDto(User user);

    List<UserDtoResponse> toDto(List<User> users);

    User toUser(UserCreateDtoRequest user);

    User toUser(User user, UserUpdateDtoRequest userUpdateDtoRequest);
}

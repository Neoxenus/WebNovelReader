package com.neoxenus.webnovelreader.user.mapper.impl;

import com.neoxenus.webnovelreader.user.dto.response.UserDtoResponse;
import com.neoxenus.webnovelreader.user.dto.request.UserCreateDtoRequest;
import com.neoxenus.webnovelreader.user.dto.request.UserUpdateDtoRequest;
import com.neoxenus.webnovelreader.user.entity.User;
import com.neoxenus.webnovelreader.user.enums.UserRole;
import com.neoxenus.webnovelreader.user.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class UserMapperImpl implements UserMapper {
    public UserDtoResponse toDto(User user){
        if(user == null)
            return null;
        return UserDtoResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles())
                .createdAt(user.getCreatedAt())
                .hasAvatar(user.getAvatar() != null)
                .build();
    }

    public List<UserDtoResponse> toDto(List<User> users){
        if(users == null)
            return null;
        return users.stream().map(this::toDto).toList();
    }

    public User toUser(UserCreateDtoRequest user){
        if(user == null)
            return null;
        return User.builder()
                .username(user.username())
                .email(user.email())
                .password(user.password())
                .roles(Set.of(UserRole.USER))
                .build();
    }

    public User toUser(User user, UserUpdateDtoRequest request){
        if(user == null || request == null)
            return null;
        user.setAvatar(request.avatar());
        user.setAvatarMimeType(request.mimeType());
        return user;
    }
}

package com.neoxenus.webnovelreader.user.mapper.impl;

import com.neoxenus.webnovelreader.user.dto.UserDto;
import com.neoxenus.webnovelreader.user.dto.request.UserCreateRequest;
import com.neoxenus.webnovelreader.user.dto.request.UserUpdateRequest;
import com.neoxenus.webnovelreader.user.entity.User;
import com.neoxenus.webnovelreader.user.enums.UserRole;
import com.neoxenus.webnovelreader.user.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class UserMapperImpl implements UserMapper {
    public UserDto toDto(User user){
        if(user == null)
            return null;
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles())
                .createdAt(user.getCreatedAt())
                .hasAvatar(user.getAvatar() != null)
                .build();
    }

    public List<UserDto> toDto(List<User> users){
        if(users == null)
            return null;
        return users.stream().map(this::toDto).toList();
    }

    public User toUser(UserCreateRequest user){
        if(user == null)
            return null;
        return User.builder()
                .username(user.username())
                .email(user.email())
                .password(user.password())
                .roles(Set.of(UserRole.USER))
                .build();
    }

    public User toUser(User user, UserUpdateRequest request){
        if(user == null || request == null)
            return null;
        user.setAvatar(request.avatar());
        user.setAvatarMimeType(request.mimeType());
        return user;
    }
}

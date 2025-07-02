package com.neoxenus.webnovelreader.user.mapper;

import com.neoxenus.webnovelreader.user.entities.User;
import com.neoxenus.webnovelreader.user.entities.UserRole;
import com.neoxenus.webnovelreader.user.entities.dtos.UserCreateRequest;
import com.neoxenus.webnovelreader.user.entities.dtos.UserDto;
import com.neoxenus.webnovelreader.user.entities.dtos.UserUpdateRequest;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserMapper {
    public UserDto mapUserToDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public User mapCreateRequestToUser(UserCreateRequest user){
        return User.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(Set.of(UserRole.USER))
                .build();
    }

    public User mapUpdateRequestToUser(User user, UserUpdateRequest userUpdateRequest){
        user.setUsername(userUpdateRequest.getUsername());
        user.setEmail(userUpdateRequest.getEmail());
        user.setPassword(userUpdateRequest.getPassword());
        user.setRoles(userUpdateRequest.getRoles());
        return user;
    }
}

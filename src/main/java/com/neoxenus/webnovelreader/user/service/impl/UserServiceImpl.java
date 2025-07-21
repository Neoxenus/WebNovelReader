package com.neoxenus.webnovelreader.user.service.impl;

import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;
import com.neoxenus.webnovelreader.exceptions.UsernameExistsException;
import com.neoxenus.webnovelreader.user.entity.User;
import com.neoxenus.webnovelreader.user.dto.request.UserCreateRequest;
import com.neoxenus.webnovelreader.user.dto.UserDto;
import com.neoxenus.webnovelreader.user.dto.request.UserUpdateRequest;
import com.neoxenus.webnovelreader.user.mapper.UserMapper;
import com.neoxenus.webnovelreader.user.repo.UserRepository;
import com.neoxenus.webnovelreader.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserMapper userMapper;

    @Override
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof UsernamePasswordAuthenticationToken) {
            log.info("Current user is logged in. Getting user info...");
            String username = (String) auth.getPrincipal();
            return userRepository.findByUsername(username);
        }
        log.info("Current user is not logged in");
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database");
        }

        Collection<SimpleGrantedAuthority> authorities = user.getRoles().stream().map(e -> new SimpleGrantedAuthority(e.name())).toList();
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);

    }

    @Override
    @Transactional
    public UserDto saveUser(UserCreateRequest user) throws UsernameExistsException {
        if(userRepository.existsByUsername(user.username())){
            log.info("Username {} already exists", user.username());
            throw new UsernameExistsException("Username " + user.username() + " already exists");
        }

        log.info("Saving new user {} to the database", user.username());

        User userEntity = userMapper.toUser(user);
        userEntity.setPassword(bCryptPasswordEncoder.encode(user.password()));
        userEntity = userRepository.save(userEntity);
        userRepository.flush();
        return userMapper.toDto(userEntity);
    }

    @Override
    public UserDto getUser(Long id) {
        log.info("Getting user with id {} from database", id);
        User user = userRepository.findById(id).orElseThrow(
                () -> new NoSuchEntityException("No user for an id: " + id)
        );
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> getUsers() {
        log.info("Getting all users from database");
        return userMapper.toDto(userRepository.findAll());
    }

    @Override
    @Transactional
    public UserDto updateUser(Long id, UserUpdateRequest userUpdateRequest) throws NoSuchEntityException, UsernameExistsException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User userByUsername = userRepository.findByUsername(userUpdateRequest.username());
            if(userByUsername != null && !userByUsername.getId().equals(id))
                throw new UsernameExistsException("Username " + userUpdateRequest.username() + " already exists");

            User user = userMapper.toUser(optionalUser.get(), userUpdateRequest);
            user.setPassword(bCryptPasswordEncoder.encode(userUpdateRequest.password()));
            return userMapper.toDto(userRepository.save(user));
        } else {
            throw new NoSuchEntityException("No user for such and id");
        }
    }



    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


}

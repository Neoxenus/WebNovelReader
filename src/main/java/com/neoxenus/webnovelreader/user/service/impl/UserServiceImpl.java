package com.neoxenus.webnovelreader.user.service.impl;

import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;
import com.neoxenus.webnovelreader.exceptions.UsernameExistsException;
import com.neoxenus.webnovelreader.user.entities.User;
import com.neoxenus.webnovelreader.user.entities.dtos.UserCreateRequest;
import com.neoxenus.webnovelreader.user.entities.dtos.UserDto;
import com.neoxenus.webnovelreader.user.entities.dtos.UserUpdateRequest;
import com.neoxenus.webnovelreader.user.mapper.UserMapper;
import com.neoxenus.webnovelreader.user.repo.UserRepository;
import com.neoxenus.webnovelreader.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
        if(userRepository.existsByUsername(user.getUsername())){
            log.info("Username {} already exists", user.getUsername());
            throw new UsernameExistsException("Username " + user.getUsername() + " already exists");
        }

        log.info("Saving new user {} to the database", user.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User userEntity = userMapper.toUser(user);
        userEntity = userRepository.save(userEntity);
        userRepository.flush();
        return userMapper.toDto(userEntity);
    }

    @Override
    public Optional<UserDto> getUser(Long id) {
        log.info("Getting user with id {} from database", id);
        return userRepository.findById(id).map(userMapper::toDto);
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
            User userByUsername = userRepository.findByUsername(userUpdateRequest.getUsername());
            if(userByUsername != null && !userByUsername.getId().equals(id))
                throw new UsernameExistsException("Username " + userUpdateRequest.getUsername() + " already exists");

            userUpdateRequest.setPassword(bCryptPasswordEncoder.encode(userUpdateRequest.getPassword()));
            User user = userMapper.toUser(optionalUser.get(), userUpdateRequest);
            return userMapper.toDto(userRepository.save(user));
        } else {
            throw new NoSuchEntityException("No user for such and id");
        }
    }

    @Override
    public boolean existsByUsername(String userName) {
        return userRepository.existsByUsername(userName);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


}

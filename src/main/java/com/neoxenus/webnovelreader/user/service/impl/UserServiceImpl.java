package com.neoxenus.webnovelreader.user.service.impl;

import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;
import com.neoxenus.webnovelreader.exceptions.UsernameExistsException;
import com.neoxenus.webnovelreader.user.dto.ImageDto;
import com.neoxenus.webnovelreader.user.dto.UserDto;
import com.neoxenus.webnovelreader.user.dto.request.UserCreateRequest;
import com.neoxenus.webnovelreader.user.entity.User;
import com.neoxenus.webnovelreader.user.enums.UserRole;
import com.neoxenus.webnovelreader.user.event.UserCreatedEvent;
import com.neoxenus.webnovelreader.user.mapper.UserMapper;
import com.neoxenus.webnovelreader.user.repo.UserRepository;
import com.neoxenus.webnovelreader.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
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
import java.util.Set;

@Service @RequiredArgsConstructor @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserMapper userMapper;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof UsernamePasswordAuthenticationToken) {
            log.info("Current user is logged in. Getting user info...");
            String username = (String) auth.getPrincipal();
            log.info("Current user is {}", username);
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
    public UserDto saveUser(UserCreateRequest request) throws UsernameExistsException {
        if(userRepository.existsByUsername(request.username())){
            log.info("Username {} already exists", request.username());
            throw new UsernameExistsException("Username " + request.username() + " already exists");
        }

        log.info("Saving new user {} to the database", request.username());

        User userEntity = userMapper.toUser(request);
        userEntity.setPassword(bCryptPasswordEncoder.encode(request.password()));

        User currentUser = getCurrentUser();
        if(currentUser != null
                && currentUser.getRoles().contains(UserRole.ADMIN)
                && Boolean.TRUE.equals(request.isAdmin())) {
            userEntity.getRoles().add(UserRole.ADMIN);
        }

        userEntity = userRepository.save(userEntity);
        userRepository.flush();

        eventPublisher.publishEvent(new UserCreatedEvent(this, userEntity));

        return userMapper.toDto(userEntity);
    }

    @Override
    public void initAdmin() {
        boolean adminExists = userRepository.existsByUsername("admin");
        if(!adminExists) {
            User admin = userRepository.save(User.builder()
                    .username("admin")
                    .password(bCryptPasswordEncoder.encode("admin"))
                    .email("admin@admin")
                    .roles(Set.of(UserRole.ADMIN, UserRole.USER))
                    .build());
            eventPublisher.publishEvent(new UserCreatedEvent(this, admin));
        }
    }

    @Override
    public UserDto getUser(Long id) {
        return userMapper.toDto(findById(id));
    }

    @Override
    public User findById(Long id) {
        log.info("Getting user with id {} from database", id);
        return userRepository.findById(id).orElseThrow(
                () -> new NoSuchEntityException("No user for an id: " + id)
        );
    }

    @Override
    public List<UserDto> getUsers() {
        log.info("Getting all users from database");
        return userMapper.toDto(userRepository.findAll());
    }





    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public ImageDto getAvatar(Long id) {
        User user = findById(id);
        return new ImageDto(user.getAvatarMimeType(), user.getAvatar());
    }


}

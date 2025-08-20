package com.neoxenus.webnovelreader.user.service.impl;

import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;
import com.neoxenus.webnovelreader.exceptions.UsernameExistsException;
import com.neoxenus.webnovelreader.user.dto.UserDto;
import com.neoxenus.webnovelreader.user.dto.request.UserUpdateRequest;
import com.neoxenus.webnovelreader.user.entity.User;
import com.neoxenus.webnovelreader.user.mapper.UserMapper;
import com.neoxenus.webnovelreader.user.repo.UserRepository;
import com.neoxenus.webnovelreader.user.service.UserSettingsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserSettingsServiceImpl implements UserSettingsService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDto updateUser(Long id, UserUpdateRequest request) throws NoSuchEntityException, UsernameExistsException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            validateAvatar(request.avatar());

            User user = userMapper.toUser(optionalUser.get(), request);
            user = userRepository.save(user);

            return userMapper.toDto(user);
        } else {
            throw new NoSuchEntityException("No user for such and id");
        }
    }

    private void validateAvatar(byte[] avatarBytes) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(avatarBytes)) {
            BufferedImage img = ImageIO.read(bais);

            if (img == null) {
                throw new IllegalArgumentException("File is not a valid image");
            }

            int width = img.getWidth();
            int height = img.getHeight();

            if (width > 256 || height > 256) {
                throw new IllegalArgumentException(
                        "Avatar must be below 200x200 resolution"
                );
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid image format", e);
        }
    }
}

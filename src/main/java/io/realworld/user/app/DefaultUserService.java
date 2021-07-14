package io.realworld.user.app;

import io.realworld.common.security.JwtTokenProvider;
import io.realworld.user.api.dto.UserCreateRequestDto;
import io.realworld.user.api.dto.UserResponseDto;
import io.realworld.user.api.dto.UserUpdateRequestDto;
import io.realworld.user.app.dto.Mappers;
import io.realworld.user.app.exception.UserAlreadyExist;
import io.realworld.user.app.exception.UserNotFoundException;
import io.realworld.user.domain.User;
import io.realworld.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    final private UserRepository userRepository;
    final private JwtTokenProvider jwtTokenProvider;

    @Override
    public UserResponseDto createUser(UserCreateRequestDto dto) {
        Optional<User> optionalUser = userRepository.findByProfile_Username(dto.getUsername());
        if(optionalUser.isPresent()) {
            throw new UserAlreadyExist();
        }

        User user = userRepository.save(dto.toEntity());

        return Mappers.toUserCreateResponseDto(user, jwtTokenProvider.createToken(user.getId()));
    }

    @Override
    @Transactional(readOnly = true)
    public User getCurrentUser() {
        // ToDo: service 분리하는게 좋아보임.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) {
            throw new UserNotFoundException();
        }

        long userId = Long.parseLong(authentication.getName());

        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByProfile_Username(username);
    }

    @Override
    public User updateUser(UserUpdateRequestDto dto) {
        return userRepository.save(dto.toEntity());
    }

}

package io.realworld.user.app;

import io.realworld.user.api.dto.UserCreateRequestDto;
import io.realworld.user.api.dto.UserResponseDto;
import io.realworld.user.api.dto.UserUpdateRequestDto;
import io.realworld.user.app.dto.Mappers;
import io.realworld.user.app.exception.UserAlreadyExist;
import io.realworld.user.domain.User;
import io.realworld.user.domain.repository.UserRepository;
import io.realworld.user.domain.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    final private UserRepository userRepository;
    final private JwtService jwtService;

    @Override
    public UserResponseDto createUser(UserCreateRequestDto dto) {
        Optional<User> optionalUser = userRepository.findByProfile_Username(dto.getUsername());
        if(optionalUser.isPresent()) {
            throw new UserAlreadyExist();
        }

        User user = userRepository.save(dto.toEntity());

        return Mappers.toUserCreateResponseDto(user, jwtService.createToken(user));
    }

    @Override
    @Transactional(readOnly = true)
    public User getCurrentUser() {
        return null;
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
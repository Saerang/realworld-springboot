package io.realworld.user.app;

import io.realworld.user.api.UserService;
import io.realworld.user.api.dto.UserCreateRequestDto;
import io.realworld.user.api.dto.UserResponseDto;
import io.realworld.user.api.dto.UserUpdateRequestDto;
import io.realworld.user.app.dto.UserMappers;
import io.realworld.user.domain.User;
import io.realworld.user.domain.repository.UserRepository;
import io.realworld.user.domain.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class DefaultUserService implements UserService {

    final private UserRepository userRepository;
    final private JwtService jwtService;

    @Override
    public UserResponseDto createUser(UserCreateRequestDto dto) {
        User user = userRepository.save(dto.toEntity());
        return UserMappers.toUserCreateResponseDto(user, jwtService.createToken(user));
    }

    @Override
    @Transactional(readOnly = true)
    public User getCurrentUser() {
        return null;
    }

    @Override
    public User updateUser(UserUpdateRequestDto dto) {
        return userRepository.save(dto.toEntity());
    }


}

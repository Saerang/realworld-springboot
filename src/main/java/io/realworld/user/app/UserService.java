package io.realworld.user.app;

import io.realworld.user.api.dto.UserCreateRequestDto;
import io.realworld.user.api.dto.UserLoginRequestDto;
import io.realworld.user.api.dto.UserResponseDto;
import io.realworld.user.api.dto.UserUpdateRequestDto;
import io.realworld.user.domain.User;

import java.util.Optional;

public interface UserService {

    UserResponseDto createUser(UserCreateRequestDto dto);

    UserResponseDto getCurrentUser();

    Optional<User> findUserByUsername(String username);

    UserResponseDto updateUser(UserUpdateRequestDto dto);

    UserResponseDto login(UserLoginRequestDto dto);

    User findCurrentUser();

    User getUserById(long userId);
}

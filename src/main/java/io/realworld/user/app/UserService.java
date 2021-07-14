package io.realworld.user.app;

import io.realworld.user.api.dto.UserCreateRequestDto;
import io.realworld.user.api.dto.UserResponseDto;
import io.realworld.user.api.dto.UserUpdateRequestDto;
import io.realworld.user.domain.User;

import java.util.Optional;

public interface UserService {

    UserResponseDto createUser(UserCreateRequestDto dto);

    User getCurrentUser();

    Optional<User> findUserByUsername(String username);

    User updateUser(UserUpdateRequestDto dto);
}

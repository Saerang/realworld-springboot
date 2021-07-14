package io.realworld.user.api;

import io.realworld.user.api.dto.UserCreateRequestDto;
import io.realworld.user.api.dto.UserResponseDto;
import io.realworld.user.api.dto.UserUpdateRequestDto;
import io.realworld.user.domain.User;

public interface UserService {

    UserResponseDto createUser(UserCreateRequestDto dto);

    User getCurrentUser();

    User updateUser(UserUpdateRequestDto dto);
}

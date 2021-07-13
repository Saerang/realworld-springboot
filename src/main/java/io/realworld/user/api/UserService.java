package io.realworld.user.api;

import io.realworld.user.api.dto.UserCreateRequestDto;
import io.realworld.user.api.dto.UserCreateResponseDto;
import io.realworld.user.api.dto.UserUpdateRequestDto;
import io.realworld.user.domain.User;

public interface UserService {

    UserCreateResponseDto createUser(UserCreateRequestDto dto);

    User getCurrentUser();

    User updateUser(UserUpdateRequestDto dto);
}

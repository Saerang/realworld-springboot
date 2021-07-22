package io.realworld.user.app;

import io.realworld.user.api.dto.UserCreateRequestDto;
import io.realworld.user.api.dto.UserLoginRequestDto;
import io.realworld.user.api.dto.UserResponseDto;
import io.realworld.user.api.dto.UserUpdateRequestDto;
import io.realworld.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(UserCreateRequestDto dto);

    User getCurrentUser();

    User getUserByUsername(String username);

    User updateUser(UserUpdateRequestDto dto);

    User login(UserLoginRequestDto dto);

    User getUserById(long userId);

    List<User> getUsersByIds(List<Long> userIds);
}

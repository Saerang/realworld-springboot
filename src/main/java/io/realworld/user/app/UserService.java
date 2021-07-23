package io.realworld.user.app;

import io.realworld.user.api.dto.UserCreateRequestDto;
import io.realworld.user.api.dto.UserLoginRequestDto;
import io.realworld.user.api.dto.UserUpdateRequestDto;
import io.realworld.user.domain.User;

import java.util.List;

public interface UserService {

    User createUser(UserCreateRequestDto dto);

    User getCurrentUser();

    User getUserByUsername(String username);

    User updateUser(UserUpdateRequestDto dto, long userId);

    User login(UserLoginRequestDto dto);

    User getUserById(long userId);

    List<User> getUsersByIds(List<Long> userIds);
}

package io.realworld.user.app.dto;

import io.realworld.user.api.dto.UserCreateResponseDto;
import io.realworld.user.domain.User;

public class UserMappers {

    public static UserCreateResponseDto toUserCreateResponseDto(User user, String password) {
        return UserCreateResponseDto.builder()
                .username(user.getProfile().getUsername())
                .email(user.getEmail())
                .password(password)
                .build();
    }
}

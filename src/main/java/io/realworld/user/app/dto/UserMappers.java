package io.realworld.user.app.dto;

import io.realworld.user.api.dto.UserResponseDto;
import io.realworld.user.domain.User;

public class UserMappers {
    public static UserResponseDto toUserCreateResponseDto(User user, String token) {
        return UserResponseDto.builder()
                .email(user.getEmail())
                .username(user.getProfile().getUsername())
                .bio(user.getProfile().getBio())
                .image(user.getProfile().getImage())
                .token(token)
                .build();
    }
}

package io.realworld.user.app.dto;

import io.realworld.user.api.dto.ProfileResponseDto;
import io.realworld.user.api.dto.UserResponseDto;
import io.realworld.user.domain.User;

public class Mappers {
    public static UserResponseDto toUserCreateResponseDto(User user, String token) {
        UserResponseDto.UserDto userDto = UserResponseDto.UserDto.builder()
                .email(user.getEmail())
                .username(user.getProfile().getUsername())
                .bio(user.getProfile().getBio())
                .image(user.getProfile().getImage())
                .token(token)
                .build();

        return UserResponseDto.builder().user(userDto).build();
    }

    public static ProfileResponseDto toProfileResponseDto(User user, boolean isFollowing) {
        ProfileResponseDto.ProfileDto profileDto = ProfileResponseDto.ProfileDto.builder()
                .bio(user.getProfile().getBio())
                .username(user.getProfile().getUsername())
                .image(user.getProfile().getImage())
                .following(isFollowing)
                .build();

        return ProfileResponseDto.builder().profile(profileDto).build();
    }
}

package io.realworld.user.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private UserDto user;

    @Getter
    public static class UserDto {
        private final String email;
        private final String token;
        private final String username;
        private final String bio;
        private final String image;

        @Builder
        public UserDto(String email, String token, String username, String bio, String image) {
            this.email = email;
            this.token = token;
            this.username = username;
            this.bio = bio;
            this.image = image;
        }
    }

    @Builder
    public UserResponseDto(UserDto user) {
        this.user = user;
    }
}

package io.realworld.user.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class UserUpdateRequestDto {
    @JsonProperty("user")
    private UserDto userDto;

    @Builder
    public UserUpdateRequestDto(UserDto userDto) {
        this.userDto = userDto;
    }

    @ToString
    @Getter
    @NoArgsConstructor
    public static class UserDto {
        private String email;
        private String username;
        private String password;
        private String image;
        private String bio;

        @Builder
        public UserDto(String email, String username, String password, String image, String bio) {
            this.email = email;
            this.username = username;
            this.password = password;
            this.image = image;
            this.bio = bio;
        }
    }
}
